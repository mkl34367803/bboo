package com.smart.oo.action.order;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.SysLogEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.DateUtil;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.oo.action.syslog.SysLogAction;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.service.IBaseAccountService;
import com.smart.oo.service.IGjSaleOrderService;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.UniqId;
@Results({@Result(name="orderDetail", location="/jsp/index.jsp"),
	@Result(name="error",location="/jsp/build/order/orderImport.jsp")})
public class OrderImportAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Autowired
	 private FactoryServiceImpl factoryService;
	 
	@Resource(name="SysLogAction")
	private SysLogAction sysLog;
	
	public void getBaseAccounts() {
		try {
			//首先获取商户号，几乎每个订单页面都要获取商户号
			User user = (User) request.getSession().getAttribute(
					"SESSION_KEY_CURRENT_USER");
			String mno = null;
			if (user!=null&&user.getMert() != null) {
				mno = user.getMert().getMno(); // 每个用户对应一个航司的商户号
			}
			List<BaseAccountEntity> list=factoryService.getBaseAccountService().queryBaseAccountByMno(mno);
//			SysLogEntity sysLogEntity=new SysLogEntity();
//			sysLogEntity.setId(UniqId.getInstance().getStrId());
//			sysLogEntity.setMno(mno);
//			sysLogEntity.setLogType("ORDER_LOG"); //记录订单相关的日志
//			sysLogEntity.setUserName(user.getLoginName());//登录用户的名字
//			sysLogEntity.setOperatime(DateUtil.DateToStr(new Date()));
////			sysLogEntity.setVisitip(visitip);
//			factoryService.getSysLogService().saveSysLog(sysLogEntity);
			String str =JSON.toJSONString(list);
			PrintWriter printWriter=response.getWriter();
			printWriter.println(str);
			printWriter.flush();
			printWriter.close();
//			request.setAttribute("baseAccounts", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void importOrderByOrderID(){
		try {
			//订单导入的时候，rt了一次，进入页面后rt查政策适用换成的pnrTxt
			request.getSession().setAttribute("isUsePnrTxtCache", true);
			this.isPage=false;
			String orderNumber=request.getParameter("orderNo").trim();
			String id=request.getParameter("id");	// id就是baseAccountEntity表中id
			if(StringUtils.isBlank(orderNumber)){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "订单号为必填字段");
				this.writeStream(jsonObject, "utf-8");
				return ;
			}else if(StringUtils.isBlank(id)){
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id为必传字段");
				this.writeStream(jsonObject, "utf-8");
				return ;
			}
			BaseAccountEntity baseAccountEntity =factoryService.getBaseAccountService().queryBaseAccountByID(Integer.parseInt(id));
			//此方法是第二次导单使用的。
			List<GjSaleOrderEntity> gjSaleOrderEntities=factoryService.getSaleOrderService().importOrder(baseAccountEntity,orderNumber,false,false);
			for(GjSaleOrderEntity gjSaleOrderEntity:gjSaleOrderEntities){
				factoryService.getSaleOrderService().autoReservePnr(baseAccountEntity, gjSaleOrderEntity);
				if(StringUtils.isEmpty(gjSaleOrderEntity.getPnrNoTime())||BBOOConstants.GjSaleOrderEntity_pnrNoTime_max.equals(gjSaleOrderEntity.getPnrNoTime())){
					//如果份销商没有传pnrNotime过来，才自己去航信查询。
					factoryService.getSaleOrderService().updatePnrInfoByRtResult(baseAccountEntity, gjSaleOrderEntity);
				}
				//这里增加一个通过rt接口修改pnrText和bigpnr的方法；
				GetPnrInfoResult rtrs=factoryService.getAllApiService().getRtByThirdInterface(gjSaleOrderEntity, false);
				factoryService.getSaleOrderService().updatePnrTxtAndBigPnrByRtResult(gjSaleOrderEntity, rtrs);
			}
			//增加一个入库运价信息的代码。(2016年9月19日，导单的时候不再获取政策信息了)
			//factoryService.getSaleOrderService().updateBigpnrAndImportPolicy(baseAccountEntity, gjSaleOrderEntity);
			String orderID=gjSaleOrderEntities.get(0).getId();
			addSysLogMsg(orderID, "导入订单");
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", orderID);
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			try {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", e.toString());
				this.writeStream(jsonObject, "utf-8");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 通过订单号判断订单在数据中是否存在
	 */
	public void isOrderExist(){
		try {
			String orderNumber=request.getParameter("orderNo").trim();
			String id=request.getParameter("id"); //这个是商户的id
			if(StringUtils.isNotEmpty(orderNumber)){
				List<GjSaleOrderEntity> queryResult=factoryService.getSaleOrderService().queryByOrderNo(orderNumber);
				if(queryResult!=null&&queryResult.size()>0){
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("exist", "导入的订单已经存在了,是否还要继续导入？");
					this.writeStream(jsonObject, "utf-8");
					return;
				}else{
					// accountID就是baseAccountEntity表中id
					
					BaseAccountEntity baseAccountEntity =factoryService.getBaseAccountService().queryBaseAccountByID(Integer.parseInt(id));
					List<GjSaleOrderEntity> gjSaleOrderEntities=factoryService.getSaleOrderService().importOrder(baseAccountEntity,orderNumber,false,true);
					for(GjSaleOrderEntity gjSaleOrderEntity:gjSaleOrderEntities){
						factoryService.getSaleOrderService().autoReservePnr(baseAccountEntity, gjSaleOrderEntity);
						if(StringUtils.isEmpty(gjSaleOrderEntity.getPnrNoTime())||BBOOConstants.GjSaleOrderEntity_pnrNoTime_max.equals(gjSaleOrderEntity.getPnrNoTime())){
							//如果份销商没有传pnrNotime过来，才自己去航信查询。
							factoryService.getSaleOrderService().updatePnrInfoByRtResult(baseAccountEntity, gjSaleOrderEntity);
						}
						//这里增加一个通过rt接口修改pnrText和bigpnr的方法；
						GetPnrInfoResult rtrs=factoryService.getAllApiService().getRtByThirdInterface(gjSaleOrderEntity, false);
						factoryService.getSaleOrderService().updatePnrTxtAndBigPnrByRtResult(gjSaleOrderEntity, rtrs);
					}
					//增加一个入库运价信息的代码。(2016年9月19日，导单的时候不再获取政策信息了)
					//factoryService.getSaleOrderService().updateBigpnrAndImportPolicy(baseAccountEntity, gjSaleOrderEntity);
					String orderID=gjSaleOrderEntities.get(0).getId();
					addSysLogMsg(orderID, "导入订单");
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("notExist", "导入的订单不存在");
					jsonObject.put("success", orderID);
					this.writeStream(jsonObject, "utf-8");
					return;
				}
			}else{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "订单号为必填字段");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	@Deprecated
	public void isOrderExist1(){
		try {
			String orderNumber=request.getParameter("orderNo").trim();
			if(StringUtils.isNotEmpty(orderNumber)){
				List<GjSaleOrderEntity> gjSaleOrderEntities=factoryService.getSaleOrderService().queryByOrderNo(orderNumber);
				if(gjSaleOrderEntities!=null&&gjSaleOrderEntities.size()>0){
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("exist", "导入的订单已经存在了,是否还要继续导入？");
					this.writeStream(jsonObject, "utf-8");
					return;
				}else{
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("notExist", "导入的订单不存在");
					this.writeStream(jsonObject, "utf-8");
					return;
				}
			}else{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "订单号为必填字段");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	// 添加订单日志
	public void addSysLogMsg(String foreginKey, String contents) throws Exception{
		User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
		String visitip = request.getRemoteAddr();
		sysLog.addSysLog(user, visitip, foreginKey, "ORDER_LOG", contents);
	}

}
