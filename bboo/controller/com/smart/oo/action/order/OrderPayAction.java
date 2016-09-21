package com.smart.oo.action.order;


import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.action.syslog.SysLogAction;
import com.smart.oo.from.BuyOrderParamVo;
import com.smart.oo.from.BuyOrderSummaryVo;
import com.smart.oo.service.imp.FactoryServiceImpl;

@Results({
	@Result(name = "orderPayDeal", location = "/jsp/build/order/orderPayDeal.jsp"),
	@Result(name = "orderPayListInfo", location = "/jsp/build/order/orderPayListInfo.jsp")
	})
public class OrderPayAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private FactoryServiceImpl factoryService;
	
	@Resource(name="SysLogAction")
	private SysLogAction sysLog;
	// 添加订单日志
	public void addSysLogMsg(String foreginKey, String contents) throws Exception{
		User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
		String visitip = request.getRemoteAddr();
		sysLog.addSysLog(user, visitip, foreginKey, "ORDER_LOG", contents);
	}
	/**
	 * 处理页面的拆分订单，主要是拆分同一订单里面有成人和儿童的情况
	 */
	public void queryOrderSummaryByPage(){
		try {
			String flightClass = request.getParameter("flightClass"); // 必传字段
			String slfStatus=request.getParameter("slfStatus");
			String startDate = request.getParameter("startDate"); // 表单的东西，传过来只少是个空串。
			String endDate = request.getParameter("endDate");
			String purchaseStartDate = request.getParameter("purchaseStartDate"); // 表单的东西，传过来只少是个空串。
			String purchaseEndDate = request.getParameter("purchaseEndDate");
			String purchaseNo = request.getParameter("purchaseNo"); //采购订单号
			String flightType = request.getParameter("flightType");
			String orderNo = request.getParameter("orderNo");
			String pnrCode = request.getParameter("pnrCode");
			String shopNameCh = request.getParameter("shopNameCh");
			String allShopNameCh = request.getParameter("allShopNameCh");
			/*--------------------------订单查询时涉及到的表单参数-----------------------------*/
			String page = request.getParameter("page");
			if (StringUtils.isEmpty(page)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "查询页为必填字段");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			Page pageEntity = new Page();
			pageEntity.setPageSize(20); // 20个订单做为一页
			pageEntity.setStartPage(Integer.parseInt(page));
			// 要从下面的user信息中取商户号mno，查询的时候带上商户mno
			User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
			BuyOrderParamVo buyOrderParamVo=new BuyOrderParamVo();
			buyOrderParamVo.setMno(user.getMert().getMno());
			buyOrderParamVo.setFlightClass(flightClass);
			buyOrderParamVo.setSlfStatus(slfStatus);
			buyOrderParamVo.setStartDate(startDate);
			buyOrderParamVo.setEndDate(endDate);
			buyOrderParamVo.setPurchaseNo(purchaseNo);
			buyOrderParamVo.setPurchaseStartDate(purchaseStartDate);
			buyOrderParamVo.setPurchaseEndDate(purchaseEndDate);
			buyOrderParamVo.setFlightType(flightType);
			buyOrderParamVo.setOrderNo(orderNo);
			buyOrderParamVo.setPnrCode(pnrCode);
			buyOrderParamVo.setShopNameCh(shopNameCh);
			List<BuyOrderSummaryVo> list=factoryService.getGjBuyOrderService().queryOrderSummaryByPage(pageEntity, buyOrderParamVo);
			JSONObject jsonObject=new JSONObject();
			String jsonString=JSON.toJSONString(list);
			jsonObject.put("list",jsonString);
			jsonObject.put("total",pageEntity.getTotalCount());
			jsonObject.put("success","查询订单详细成功");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	
	public void payOrder(){
		try {
			String id = request.getParameter("id"); // 必传字段
			GjBuyOrderEntity gjBuyOrderEntity=factoryService.getGjBuyOrderService().queyOrderByID(id);
			Boolean result=factoryService.getGjBuyOrderService().payOrderByThirdInterface(gjBuyOrderEntity);
			JSONObject jsonObject=new JSONObject();
			if(result){
				jsonObject.put("success","支付成功");
			}else{
				jsonObject.put("error","支付失败");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error",e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	/**
	 * 订单支付处理页面
	 * 
	 * @return
	 */
	public String orderPayDeal() {
		try {
			String id = request.getParameter("id");
			User user = SecurityContext.getUser();
			String lockUser =user.getName();
			if (StringUtilsc.isNotEmpty(id)) {
				GjSaleOrderEntity gjSaleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
				if (null != gjSaleOrderEntity) {
					// 人工判断是否有并发情况出现。回到订单列表页面
					if (StringUtils.isNotEmpty(gjSaleOrderEntity.getLockUser())&&!lockUser.equals(gjSaleOrderEntity.getLockUser())) {
						return "orderPayListInfo";
					}
					//先更新订单状态比较好！
					Boolean isUpdate=factoryService.getSaleOrderService().updateLockUser(id, lockUser);
					if(!isUpdate){
						//更新锁定人失败，说明并发了，有人先更新了数据库
						return "orderPayListInfo";
					}
					request.setAttribute("orderDetail", gjSaleOrderEntity);
					GjBuyOrderEntity gjBuyOrderEntity=this.factoryService.getGjBuyOrderService().queyOrderByID(id);
					request.setAttribute("buyOrderEntity", gjBuyOrderEntity);
					addSysLogMsg(id, "进入支付处理页面");
					System.out.println(ServletActionContext.getRequest().getContextPath());
				} else {
					request.setAttribute("error", "没有查到该订单");
				}
			} else {
				request.setAttribute("error", "id参数为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderPayDeal";
	}
	
	/**
	 * 已经线下支付的订单进行处理，回填订单状态。
	 */
	public void alreadyPay(){
		try {
			String orderId = request.getParameter("orderId"); // 必传字段
			factoryService.getGjBuyOrderService().alreadyPay(orderId);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success","支付成功");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error",e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
}
