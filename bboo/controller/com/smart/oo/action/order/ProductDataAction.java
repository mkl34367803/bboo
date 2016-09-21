package com.smart.oo.action.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.ProductDataEntity;
import com.smart.comm.entity.ProductFlightEntity;
import com.smart.comm.entity.ProductPassengerEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.JsonPluginsUtil;
import com.smart.utils.StringUtils;

public class ProductDataAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Autowired
	 private FactoryServiceImpl factoryService;
	 
	 /**
	  * 通过订单id去查询b2b运价信息
	  */
	 public void queryProductDataByOrderId() {
			try {
				//订单导入的时候，rt了一次，进入页面后rt查政策适用换成的pnrTxt
				Object object=request.getSession().getAttribute("isUsePnrTxtCache");
				boolean isUsePnrTxtCache=false;
				if(object!=null){
					isUsePnrTxtCache=(Boolean)object;
				}
				String orderId = request.getParameter("orderId"); // 表单的东西，传过来只少是个空串。
				if(orderId!=null){
				//List<ProductDataEntity> list=factoryService.getProductDataService().getProductDataByFkid(orderId);
				List<ProductPriceEntity> list=null;
				if(isUsePnrTxtCache){
					/**如果isUsePnrTxtCache为true，说明是第一次获取政策，或者说是pnr修改后，相当于也是第一次获取政策，这个时候需要删除原来的政策
					*即需要删除旧编码的政策，所以直接调用下面这个方法
					*/
					list=factoryService.getProductPriceService().obtainProductByThirdAuthInterface(orderId,isUsePnrTxtCache);
				}else{
					list=factoryService.getProductPriceService().obtainProductPriceByOrderId(orderId,isUsePnrTxtCache);
				}
				request.getSession().setAttribute("isUsePnrTxtCache",false); //将这个session置空
				  if(list!=null&&list.size()>0){
					  for(ProductPriceEntity productPriceEntity:list){
						  ProductDataEntity productDataEntity=productPriceEntity.getProduct();
						  productDataEntity.setPriceList(null);
						  Set<ProductPassengerEntity>  psgLIst=  productDataEntity.getPsgList();
						  for(ProductPassengerEntity p:psgLIst){
							  p.setProduct(null);
						  }
						  Set<ProductFlightEntity>  fltLIst=  productDataEntity.getTripList();
						  for(ProductFlightEntity p:fltLIst){
							  p.setProduct(null);
						  }
					  }
					  
				  }
				String jsonString = JSON.toJSONString(list);
//					Gson gson=new Gson();
//					String jsonString=gson.toJson(list);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("list", jsonString);
					GjSaleOrderEntity gjOrderEntity=factoryService.getSaleOrderService().queryOrderByID(orderId);
					jsonObject.put("gettime", gjOrderEntity.getGettime());
					jsonObject.put("success", "查询b2b运价信息成功");
					this.writeStream(jsonObject, "utf-8");
				}else{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("error", "订单号不能为空");
					this.writeStream(jsonObject, "utf-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", e.toString());
				this.writeStream(jsonObject, "utf-8");
			}
	 }
	 
	 
	 /**
	  * 通过id查询productPrice表中的价格信息
	  */
	 public void getProductPriceById(){
		 try {
			 String id = request.getParameter("id");
			 if(StringUtils.isNotEmpty(id)){
				ProductPriceEntity productPriceEntity= factoryService.getProductPriceService().getProductPriceById(id);
				String jsonString=JSON.toJSONString(productPriceEntity);
				//如果是政策来源本地bsp，那么查询一下office表，获取本地bsp的所有office信息
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("productPriceEntity", jsonString);
				
				if(productPriceEntity!=null&&BBOOConstants.ProductDataEntity_productSource_LOCALBSP.equals(productPriceEntity.getProduct().getProductSource())){
					User user=SecurityContext.getUser();
					String mno=user.getMert().getMno();
					List<BaseOfficeEntity> list=factoryService.getBaseOfficeService().findDataList();
					Iterator<BaseOfficeEntity> iterator=list.iterator();
					while(iterator.hasNext()){
						BaseOfficeEntity baseOfficeEntity=iterator.next();
						if(!mno.equals(baseOfficeEntity.getMno())||!baseOfficeEntity.getOfftypes().contains(BBOOConstants.BaseOfficeEntity_offtypes_ETDZ)||!BBOOConstants.BaseOfficeEntity_ISU_ONE.equals(baseOfficeEntity.getIsu())){
							iterator.remove();
						}
					}
					jsonObject.put("baseOfficeEntities", JSON.toJSONString(list));
					
				}
				jsonObject.put("success", "查询b2b运价信息成功");
				this.writeStream(jsonObject, "utf-8");
			 }else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "productPriceId不能为空");
				this.writeStream(jsonObject, "utf-8");
			 }
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	 }
}
