package com.smart.oo.action.order;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.SysLogEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.action.syslog.SysLogAction;
import com.smart.oo.from.BuyOrderProfit;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.StringUtils;

@Results({ @Result(name = "orderDetail", location = "/jsp/build/order/order_detail.jsp"), 
	@Result(name = "orderDeal", location = "/jsp/build/order/orderDeal.jsp"), 
	@Result(name = "orderList", location = "/jsp/build/order/orderListInfo.jsp"),
	@Result(name = "orderOutList", location = "/jsp/build/order/orderOutListInfo.jsp"),
	@Result(name = "orderOutDeal", location = "/jsp/build/order/orderOutDeal.jsp"),
	@Result(name = "orderModify", location = "/jsp/build/order/orderModify.jsp"),
	@Result(name = "orderQuery", location = "/jsp/build/order/order_query.jsp"),
	@Result(name = "refundQuery", location = "/jsp/build/refound/refundQuery.jsp"),
	@Result(name = "orderPayList", location = "/jsp/build/order/orderPayListInfo.jsp")})
public class GjOrderDetailAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Autowired
	 private FactoryServiceImpl factoryService;
	 
	 @Resource(name="SysLogAction")
	 private SysLogAction sysLog;


	 /**
	  * 查询销售订单信息
	  * @return
	  */
	public String showOrderDetail() {
		String operate = request.getParameter("operate");
		String flightClass = request.getParameter("flightClass");
		if ("refundQuery".equals(operate)) {
			request.setAttribute("operator", "refund");
		} else if ("reserveQuery".equals(operate)) {
			request.setAttribute("operator", "reserve");
			operate = "refundQuery";
		}
		try {
			String id = request.getParameter("id");
			if (StringUtilsc.isNotEmpty(id)) {
				GjSaleOrderEntity orderDetail = this.factoryService.getSaleOrderService().queryOrderByID(id);
				if (null == orderDetail) {
					request.setAttribute(ERROR, "销售订单为空");
					if (StringUtils.isNotEmpty(flightClass)) {
						request.setAttribute("flightClass", flightClass);
					}
					return operate;
				}
				GjBuyOrderEntity buyOrderEntity = this.factoryService.getGjBuyOrderService().queyOrderByID(id);
				/*if (null == buyOrderEntity) {
					request.setAttribute(ERROR, "采购订单为空");
					if (StringUtils.isNotEmpty(flightClass)) {
						request.setAttribute("flightClass", flightClass);
					}
					return operate;
				}*/
				request.setAttribute("orderDetail", orderDetail);
				info("销售订单查询成功,订单ID为："+id);
				request.setAttribute("buyOrderEntity", buyOrderEntity);
				info("购买订单查询成功,订单ID为："+id);
				
				// 查询日志
				List<SysLogEntity> sysLogEntities = sysLog.querySysLog(id);
				if(null != sysLogEntities && sysLogEntities.size() > 0){
					request.setAttribute("sysLogEntities", sysLogEntities);
				}
				
				// 查询退票信息
				BaseRefundEntity baseRefundEntity = new BaseRefundEntity();
				baseRefundEntity.setFkid(id);
				List<BaseRefundEntity> baseRefundList = this.factoryService.getBaseRefundService().queryList(baseRefundEntity);
				if (null != baseRefundList && baseRefundList.size() > 0) {
					request.setAttribute("baseRefundList", baseRefundList);
				}
				return "orderDetail";
			} else {
				info("ID为空");
				request.setAttribute("ERROR", "id为空");
				if (StringUtils.isNotEmpty(flightClass)) {
					request.setAttribute("flightClass", flightClass);
				}
				return operate;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(ERROR, e.toString());
			if (StringUtils.isNotEmpty(flightClass)) {
				request.setAttribute("flightClass", flightClass);
			}
			return operate;
		}
	}
	
	/**
	 * 订单处理,单击处理按钮的时候，进入这个方法
	 * 
	 * @return
	 */
	public String queryOrderByID() {
		try {
			String id = request.getParameter("id");
			//String lockUser = request.getParameter("lockUser");
			User user = SecurityContext.getUser();
			String lockUser =user.getName();
			//lockUser=new String(lockUser.getBytes("8859_1"), "utf8");
			if (StringUtilsc.isNotEmpty(id)) {
				// List<GjSaleOrderEntity> list =
				// this.factoryService.getSaleOrderService().queryByOrderNo(orderNo);
				GjSaleOrderEntity gjSaleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
				if (null != gjSaleOrderEntity) {
					// 人工判断是否有并发情况出现。回到订单列表页面
					if (StringUtils.isNotEmpty(gjSaleOrderEntity.getLockUser())&&!lockUser.equals(gjSaleOrderEntity.getLockUser())) {
						return "orderList";
					}
					//先更新订单状态比较好！
					Boolean isUpdate=factoryService.getSaleOrderService().updateLockUser(id, lockUser);
					if(!isUpdate){
						//更新锁定人失败，说明并发了，有人先更新了数据库
						return "orderList";
					}
					request.setAttribute("orderDetail", gjSaleOrderEntity);
					GjBuyOrderEntity gjBuyOrderEntity=this.factoryService.getGjBuyOrderService().queyOrderByID(id);
					request.setAttribute("buyOrderEntity", gjBuyOrderEntity);
					addSysLogMsg(id, "进入订单处理页面");
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
		return "orderDeal";
	}

	/**
	 * 解除锁定，和强制解锁的时候，调用这个方法
	 * 
	 * @return
	 */
	public void updateLockUser() {
		try {
			String id = request.getParameter("id");
			String lockUser = request.getParameter("lockUser");
			if (StringUtilsc.isNotEmptyAndNULL(id)) {
				factoryService.getSaleOrderService().updateLockUser(id, lockUser);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("success", "解锁成功");
				if(StringUtilsc.isNotEmpty(lockUser)){
					addSysLogMsg(id, lockUser+"锁定订单。");
					info(lockUser+"锁定订单");
				}
				this.writeStream(jsonObject, "utf-8");
			}else{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id不能为空");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", "服务器出错了");
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	
	/**
	 * 出票中订单处理，订单修改,单击处理、修改按钮的时候，进入方法
	 * 
	 * @return
	 */
	public String queryOrderOutByID() {
		try {
			String id = request.getParameter("id");
			User user = SecurityContext.getUser();
			String lockUser =user.getName();
//			String lockUser = request.getParameter("lockUser");
//			if(lockUser!=null){
//				lockUser=new String(lockUser.getBytes("8859_1"), "utf8");
//			}
			String operate = request.getParameter("operate");
			if (StringUtilsc.isNotEmptyAndNULL(id) && StringUtilsc.isNotEmptyAndNULL(operate)) {
				GjSaleOrderEntity gjSaleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
				if (null != gjSaleOrderEntity) {
					request.setAttribute("orderDetail", gjSaleOrderEntity);
					GjBuyOrderEntity gjBuyOrderEntity=this.factoryService.getGjBuyOrderService().queyOrderByID(id);
					request.setAttribute("buyOrderEntity", gjBuyOrderEntity);
					// 查询日志
					List<SysLogEntity> sysLogEntities = sysLog.querySysLog(id);
					if(null != sysLogEntities && sysLogEntities.size() > 0){
						request.setAttribute("sysLogEntities", sysLogEntities);
					}
					info("订单查询成功,订单ID为："+id);
					if(operate.equals("orderOutDeal")){// 进入出票中订单处理页面
						factoryService.getSaleOrderService().updateLockUser(id, lockUser);
						// 人工判断是否有并发情况出现。回到订单列表页面
//						if (StringUtils.isNotEmpty(gjSaleOrderEntity.getLockUser())&&!lockUser.equals(gjSaleOrderEntity.getLockUser())) {
//							return "orderOutList";
//						}
						return "orderOutDeal";
					} else if(operate.equals("orderModify")){// 进入订单修改页面
						addSysLogMsg(id, "进入订单修改页面。");
						return "orderModify";
					}
				} else {
					request.setAttribute("error", "没有查到该订单");
					info("没有查到该订单,订单ID为："+id);
				}
			} else {
				request.setAttribute("error", "id参数为空");
				info("该订单ID为空,订单ID为："+id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderOutList";
	}

	// 添加订单日志
	public void addSysLogMsg(String foreginKey, String contents) throws Exception{
		User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
		String visitip = request.getRemoteAddr();
		sysLog.addSysLog(user, visitip, foreginKey, "ORDER_LOG", contents);
	}

	public static void info(String msg){
		OOLogUtil.info(msg, GjOrderDetailAction.class, null);
	}
	
	/**
	 * 订单处理,单击处理按钮的时候，进入这个方法
	 * 
	 * @return
	 */
	public void cancelOrderByID() {
		try {
			String id = request.getParameter("id");
			//String lockUser = request.getParameter("lockUser");
			User user = SecurityContext.getUser();
			String lockUser =user.getName();
			//lockUser=new String(lockUser.getBytes("8859_1"), "utf8");
			if (StringUtilsc.isNotEmpty(id)) {
				// List<GjSaleOrderEntity> list =
				// this.factoryService.getSaleOrderService().queryByOrderNo(orderNo);
				GjSaleOrderEntity gjSaleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(id);
				if (null != gjSaleOrderEntity) {
					this.factoryService.getSaleOrderService().cancelOrder(id, lockUser);
					addSysLogMsg(id, "取消订单");
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("success", "取消订单成功");
					this.writeStream(jsonObject, "utf-8");
				} else {
					JSONObject jsonObject=new JSONObject();
					jsonObject.put("error", "未查询到相关订单");
					this.writeStream(jsonObject, "utf-8");
				}
			} else {
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id字段不能为空");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", "服务器出错了："+e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	public void updatePnrNoTime(){
		try {
			String id = request.getParameter("id");
			String pnrNoTime = request.getParameter("pnrNoTime");
			String leaveRemark = request.getParameter("leaveRemark");
			if (StringUtilsc.isNotEmpty(id)) {
				this.factoryService.getSaleOrderService().updatePnrNoTime(id, pnrNoTime, leaveRemark);
				addSysLogMsg(id, "添加pnrNoTime和留票信息");
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("success", "添加pnrNoTime和留票信息成功");
				this.writeStream(jsonObject, "utf-8");
			}else{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("error", "id字段不能为空");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", "服务器出错了："+e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}

	public void getBuyOrderProfit(){
		try {
			List<BuyOrderProfit> buyOrderProfits=this.factoryService.getGjBuyOrderService().getProfitByPurchasePlace();
			String str=JSON.toJSONString(buyOrderProfits);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", "查询成功");
			jsonObject.put("buyOrderProfits", str);
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", "服务器出错了："+e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
}
