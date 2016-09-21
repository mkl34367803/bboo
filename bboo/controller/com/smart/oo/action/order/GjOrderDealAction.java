package com.smart.oo.action.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.action.syslog.SysLogAction;
import com.smart.oo.domain.res.DistributionModifyOrderInfoResult;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.from.PurchaseInfoVo;
import com.smart.oo.from.ReservePnrVo;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.JsonPluginsUtil;

public class GjOrderDealAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FactoryServiceImpl factoryService;
	
	@Resource(name="SysLogAction")
	private SysLogAction sysLog;
	/**
	 * 处理页面的拆分订单，主要是拆分同一订单里面有成人和儿童的情况
	 */
	public void splitOrder(){
		try {
			String id=request.getParameter("id");
			String[] passengerIDs=request.getParameterValues("passengerIDs");
			if(StringUtils.isNotEmpty(id)&&passengerIDs!=null&&passengerIDs.length>0){
				factoryService.getSaleOrderService().splitOrder(id, passengerIDs);
				addSysLogMsg(id, "拆分订单");
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("success",id); //将id返回回去
				this.writeStream(jsonObject, "utf-8");
			}else{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id和passengerID都是必填字段");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			try {
				this.writeStream(e.toString(), "utf-8");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 保存采购信息，同时复制卖出订单到买入订单表中去。
	 */
	public void savePurchaseInfo(){
		try {
			String id=request.getParameter("id");  //订单id
			String purchaseNo = request.getParameter("purchaseNo");
			String pnrCode = request.getParameter("pnrCode");
			String payWay = request.getParameter("payWay");
			String payAccount = request.getParameter("payAccount");
			String transactionNo = request.getParameter("transactionNo");
			String printPrice = request.getParameter("printPrice");
			String backPoint = request.getParameter("backPoint");
			String afterPoint = request.getParameter("afterPoint");
			String stickingPoint = request.getParameter("stickingPoint");
			String reward = request.getParameter("reward");
			String price = request.getParameter("price");//支付机票款(单个人) printPrice*（1-backPoint/100）-reward
//			String tax = request.getParameter("tax"); //税费==基建+燃油
			String payPirce = request.getParameter("payPirce");  //结算价
			String oilFee = request.getParameter("oilFee"); //燃油费
			String taxFee = request.getParameter("taxFee"); //基建费（乘客里面还有个printprice）
			String lr = request.getParameter("lr");
			String allprice = request.getParameter("allprice");
			String purchasePlace = request.getParameter("purchasePlace");
			String purchasePlaceCh = request.getParameter("purchasePlaceCh");
			String slfRemark = request.getParameter("slfRemark");
			PurchaseInfoVo purchaseInfoVo=new PurchaseInfoVo(id, purchaseNo, pnrCode, payWay, payAccount, transactionNo, printPrice, backPoint, afterPoint, stickingPoint, reward, price, payPirce, oilFee, taxFee, lr, allprice,purchasePlace,purchasePlaceCh,slfRemark);
			factoryService.getGjBuyOrderService().savePurchaseInfo(purchaseInfoVo);
			addSysLogMsg(id, "保存采购信息");
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", "保存采购信息成功");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", "保存采购信息失败");
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	/**
	 * 更新票号和pnr
	 * @return
	 */
	public void updateTicketNumAndPnr(){
		try {
			JSONObject jsonObj = new JSONObject();
			
			String passengers = request.getParameter("salePassengers");
			if (StringUtilsc.isNotEmptyAndNULL(passengers)) {
				JSONObject passObject = JSONObject.fromObject(passengers);
				String orderId = passObject.getString("orderId");
				String pnrCode = passObject.getString("pnrCode");
				String isUpdateIntefaceStr = passObject.getString("isUpdateInteface");
				
				JSONArray passArray = passObject.getJSONArray("passengers");
				
				boolean flag = this.factoryService.getSaleOrderService().updateEticketAndPnr(orderId, pnrCode, passArray);
				if (flag) {
					info("订单状态更新成功，orderId="+orderId);
					addSysLogMsg(orderId, "订单状态更新为出票成功");
				}
				jsonObj.put("success", "更新成功");
				
				if(isUpdateAllETicketNum(orderId)) {
					// 是否更新第三方接口
					boolean isUpdateInteface = false;
					if (StringUtilsc.isNotEmpty(isUpdateIntefaceStr)) {
						isUpdateInteface = Boolean.parseBoolean(isUpdateIntefaceStr);
					}
					
					if (isUpdateInteface) {
						String result = bookEticketNo(orderId);
						if (result == "") {
							jsonObj.put("error", "第三方接口票号失败");
						} else {
							JSONObject resultObj = JSONObject.fromObject(result);
							boolean isuss = (Boolean) resultObj.get("isuss");
							if (!isuss) {
								jsonObj.put("error", resultObj.get("msg").toString());
							} else {
								jsonObj.put("success", "第三方接口票号成功");
								info("第三方接口票号成功,orderId="+orderId);
								this.factoryService.getSaleOrderService().updateStatus(orderId, BBOOConstants.GjSaleOrderEntity_STATUS_TWO);
							}
							info("票号更新成功");
							addSysLogMsg(orderId, "status订单状态更新为出票成功");
						}
					}
				}
				this.writeStream(jsonObj, "utf-8");
			} else {
				jsonObj.put("error", "数据获取失败");
				this.writeStream(jsonObj, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	/**
	 * 是否更新所有票号
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private boolean isUpdateAllETicketNum(String id) throws Exception {
		GjSaleOrderEntity saleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
		Set<GjSalePassengerEntity> salePassengerEntities = saleOrderEntity.getPassengers();
		int i = 0;
		for (GjSalePassengerEntity gjSalePassengerEntity : salePassengerEntities) {
			if (StringUtilsc.isNotEmptyAndNULL(gjSalePassengerEntity.getEticketNum())) {
				i++;
			}
		}
		if(i == salePassengerEntities.size()){
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * 更新订单状态
	 * @return
	 */
	public void updateSlfStatus(){
		try {
			String id = request.getParameter("id");
			String slfStatus = request.getParameter("slfStatus");
			if (StringUtilsc.isNotEmpty(id) && StringUtilsc.isNotEmpty(slfStatus)) {
				factoryService.getSaleOrderService().updateSlfStatus(id, slfStatus);
				info("订单状态更新成功");
				String slfStatusCh = getSlfStatusCH(slfStatus);
				addSysLogMsg(id, "订单状态更新为"+slfStatusCh+"。");
				this.writeStream("success", "utf-8");
			} else {
				info("订单状态更新失败，订单ID为："+id);
				this.writeStream("订单状态更新失败", "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 添加订单日志
	public void addSysLogMsg(String foreginKey, String contents) throws Exception{
		User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
		String visitip = request.getRemoteAddr();
		sysLog.addSysLog(user, visitip, foreginKey, "ORDER_LOG", contents);
	}
	
	// 获得订单状态--中文
	public String getSlfStatusCH(String slfStatus){
		switch (Integer.parseInt(slfStatus)) {
		case 0:
			return "订座成功等待支付";
		case 2:
			return "出票完成";
		case 5:
			return "出票中";
		case 12:
			return "订单取消";
		case 20:
			return "等待座位确认";
		case 30:
			return " 退票申请中";
		case 31:
			return "退票完成等待退款";
		case 39:
			return "退款完成";
		case 40:
			return "改签申请中";
		case 42:
			return "改签完成";
		case 50:
			return "未出票申请退款";
		case 51:
			return "订座成功等待价格确认";
		case 61:
			return "暂缓出票";
		case 62:
			return "订单超时";
		case -1:
			return "其它";
		default:
			return null;
		}
	}
	
	/**
	 * 回填第三方票号
	 * @throws Exception
	 */
	private String bookEticketNo(String id) throws Exception {

		GjSaleOrderEntity saleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
		GjBuyOrderEntity buyOrderEntity = this.factoryService.getGjBuyOrderService().queyOrderByID(id);
		
		Integer accountId = saleOrderEntity.getAccountid();
		BaseAccountEntity baseAccountEntity = this.factoryService.getBaseAccountService().queryBaseAccountByID(accountId);
		DistributionModifyOrderInfoResult rs = factoryService.getAllApiService().modifyOrderByThirdInterface(saleOrderEntity, buyOrderEntity, baseAccountEntity);
		//System.out.println(rs != null ? JsonPluginsUtil.beanToJson(rs) : "");
		return rs != null ? JsonPluginsUtil.beanToJson(rs) : "";

	}

	
	public void info(String msg){
		OOLogUtil.info(msg, GjOrderDetailAction.class, null);
	}
	/**
	 * 修改pnr同时修改大编码，和pnrtext文本信息
	 */
	public void modifyPnrAndProductData(){
		try {
			//订单导入的时候，rt了一次，进入页面后rt查政策适用换成的pnrTxt
			request.getSession().setAttribute("isUsePnrTxtCache", true);
			String id = request.getParameter("id");
			String pnrCode = request.getParameter("pnrCode");
			if(StringUtils.isEmpty(id)||StringUtils.isEmpty(pnrCode)){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id和pnrCode都不能为空");
				this.writeStream(jsonObject, "utf-8");
			}else{
				Boolean bool=factoryService.getSaleOrderService().modifyPnr(id,pnrCode);
				if(bool){
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", id);
					jsonObject.put("success", "修改pnr同时查询b2b运价信息成功");
					this.writeStream(jsonObject, "utf-8");
				}else{
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("error", "服务器端有操作失败了");
					this.writeStream(jsonObject, "utf-8");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	/**
	 * 刷新政策信息，直接从第三方接口拉取政策信息
	 * 如果没有拉取到，不删除原来的政策信息
	 */
	public void refreshProductDataByThirdInterface(){
		try {
			String id = request.getParameter("id");
			//String pnrCode = request.getParameter("pnrCode");
			GjSaleOrderEntity gjSaleOrderEntity=new GjSaleOrderEntity();
			if(StringUtils.isEmpty(id)){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id不能为空");
				this.writeStream(jsonObject, "utf-8");
			}else{
				gjSaleOrderEntity.setId(id);
				//获取政策信息前没有做过rt查询，所以这里不使用缓存pnrtext文本信息查询政策信息
				List<ProductPriceEntity> list=factoryService.getProductPriceService().obtainProductByThirdAuthInterface(id,false);
				String jsonString = JSON.toJSONString(list);
				GjSaleOrderEntity gjOrderEntity=factoryService.getSaleOrderService().queryOrderByID(id);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("list", jsonString);
				jsonObject.put("id", id);
				jsonObject.put("gettime", gjOrderEntity.getGettime());
				jsonObject.put("success", "查询b2b运价信息成功");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	public void getsdOfficeBymno(){
		try {
			
			User user = SecurityContext.getUser();
			String mno=user.getMert().getMno(); //获取用户的商户号
			if(StringUtils.isEmpty(mno)){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "未获取到用户的商户号");
				this.writeStream(jsonObject, "utf-8");
				return;
			}else{
				BaseOfficeEntity baseOfficeEntity=new BaseOfficeEntity();
				baseOfficeEntity.setMno(mno);
				baseOfficeEntity.setOfftypes(BBOOConstants.BaseOfficeEntity_offtypes_SD);
				baseOfficeEntity.setIsu(BBOOConstants.BaseOfficeEntity_ISU_ONE);
				List<BaseOfficeEntity> baseOfficeEntities=factoryService.getBaseOfficeService().findDataList();
				List<BaseOfficeEntity> result=null;
				if(baseOfficeEntities!=null&&baseOfficeEntities.size()>0){
					result=new ArrayList<BaseOfficeEntity>();
					for(BaseOfficeEntity entity:baseOfficeEntities){
						if(mno.equals(entity.getMno())&&entity.getOfftypes().contains(BBOOConstants.BaseOfficeEntity_offtypes_SD)&&BBOOConstants.BaseOfficeEntity_ISU_ONE.equals(entity.getIsu())){
							result.add(entity);
						}
					}
				}
				String jsonString=JSON.toJSONString(result);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("list", jsonString);
				jsonObject.put("success", "查询office信息成功");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	/**
	 * reservePnrCode
	 */
	public void reservePnrCode(){
		try {
			//订单导入的时候，rt了一次，进入页面后rt查政策适用换成的pnrTxt
			request.getSession().setAttribute("isUsePnrTxtCache", true);
			String param=request.getParameter("param");
			ReservePnrVo reservePnrVo=JSON.parseObject(param, ReservePnrVo.class);
			String orderId=reservePnrVo.getId();
			if(StringUtils.isNotEmpty(orderId)){
				boolean bool=factoryService.getSaleOrderService().reservePnrCode(reservePnrVo);
				if(bool){
					GjSaleOrderEntity result=factoryService.getSaleOrderService().queryOrderByID(orderId);
					if(result!=null){
						GetPnrInfoResult rtrs=factoryService.getAllApiService().getRtByThirdInterface(result, false);
						factoryService.getSaleOrderService().updatePnrTxtAndBigPnrByRtResult(result, rtrs);
//						BaseAccountEntity baseAccountEntity=factoryService.getBaseAccountService().queryBaseAccountByID(result.getAccountid());
//						if(baseAccountEntity!=null){
//							factoryService.getSaleOrderService().updateBigpnrAndImportPolicy(baseAccountEntity, result);
//						}
					}
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", reservePnrVo.getId());
					jsonObject.put("success", "预定pnr成功");
					this.writeStream(jsonObject, "utf-8");
				}else{
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", reservePnrVo.getId());
					jsonObject.put("error", "预定pnr失败");
					this.writeStream(jsonObject, "utf-8");
				}
			}else{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "order id cannot be empty");
				this.writeStream(jsonObject, "utf-8");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	public void createOrder(){
		try {
			String orderId=request.getParameter("orderId");
			String productPriceId=request.getParameter("productPriceId");
			String payType=request.getParameter("payType");
			String baseOfficeId=request.getParameter("baseOfficeId");
			if(StringUtils.isEmpty(orderId)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "order id cannot be empty");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			if(StringUtils.isEmpty(productPriceId)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "productPriceId cannot be empty");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			if(StringUtils.isEmpty(payType)){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "payType cannot be empty");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			if(!StringUtils.isEmpty(baseOfficeId)){
				//如果这个东西不为空，说明是bsp的etdz出票，走另外一个流程，（下面流程都不走了）
				boolean result=factoryService.getGjBuyOrderService().createOrderByEtdz(orderId, baseOfficeId, productPriceId);
				JSONObject jsonObject=new JSONObject();
				if(result){
					jsonObject.put("success", "创建订单成功");
				}else{
					jsonObject.put("error", "创建订单失败");
				}
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			GjSaleOrderEntity gjSaleOrderEntity=factoryService.getSaleOrderService().queryOrderByID(orderId);
			if(gjSaleOrderEntity==null){
				throw new RuntimeException("未查询到相关订单");
			}
			GjBuyOrderEntity gjBuyOrderEntity=factoryService.getGjBuyOrderService().queyOrderByID(orderId);
			if(gjBuyOrderEntity!=null){
				throw new RuntimeException("该订单已经创建过，请刷新后重试！");
			}
			Boolean createOrderResult=factoryService.getGjBuyOrderService().createOrderByThirdInterface(productPriceId, gjSaleOrderEntity);
			if(createOrderResult){
				if(payType.equals("notPay")){
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("success", "创建订单成功");
					this.writeStream(jsonObject, "utf-8");
					
				}else if(payType.equals("directPay")){
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("success", "创建订单成功");
					jsonObject.put("directPay", "直接支付");
					this.writeStream(jsonObject, "utf-8");
				}else if(payType.equals("autoPay")){
					GjBuyOrderEntity paramVo=new GjBuyOrderEntity();
					paramVo.setId(orderId);
					paramVo.setSlfStatus(BBOOConstants.GjBuyOrderEntity_slfStatus_ninetynine);
					factoryService.getGjBuyOrderService().updateById(paramVo);
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("success", "创建订单成功");
					this.writeStream(jsonObject, "utf-8");
				}
			}else{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "创建订单失败");
				this.writeStream(jsonObject, "utf-8");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
}
