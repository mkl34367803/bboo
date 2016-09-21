package com.smart.oo.action.refund;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.GjBuyFlightEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjBuyPassengerEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.comm.entity.RefundEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.action.syslog.SysLogAction;
import com.smart.oo.domain.FlightDynamicsDomain;
import com.smart.oo.domain.res.FlightDynamicsResult;
import com.smart.oo.domain.res.GDSResult;
import com.smart.oo.from.OrderSummaryVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.JsonPluginsUtil;
import com.smart.utils.StringUtils;

@Results({@Result(name = "refundQuery", location = "/jsp/build/refound/refundQuery.jsp"),
	@Result(name = "reserve", location = "/jsp/build/refound/reserve.jsp")})
public class BaseRefundAction extends BaseAction {

	private static final long serialVersionUID = -2431156752348436226L;
	
	@Autowired
	private FactoryServiceImpl factoryService;

	@Resource(name="SysLogAction")
	 private SysLogAction sysLog;
	
	/**
	 * 查询留票订单
	 */
	public void queryOrdersByPage() {
		try {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String carrier = request.getParameter("carrier");
			String pnrCode = request.getParameter("pnrCode");
			String orderNo = request.getParameter("orderNo");
			
			String orderStatus = request.getParameter("orderStatus");
			String state = request.getParameter("state");
			String startRefund = request.getParameter("startRefund");
			String endRefund = request.getParameter("endRefund");
			
			String startPage = request.getParameter("page");
			String pageSize = request.getParameter("pageSize");
			
			Page page = new Page();
			JSONObject jsonObject = new JSONObject();
			if(StringUtils.isNotEmpty(pageSize) && StringUtils.isNotEmpty(startPage)) {
				page.setPageSize(Integer.parseInt(pageSize)); // 30个订单做为一页
				page.setStartPage(Integer.parseInt(startPage));
			} else {
				jsonObject.put("error", "页面为空");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			
			RefundVo vo = new RefundVo();
			vo.setStartDate(startDate);
			vo.setEndDate(endDate);
			vo.setCarrier(carrier);
			vo.setPnrCode(pnrCode);
			vo.setOrderNo(orderNo);
			vo.setStartRefundDate(startRefund);
			vo.setEndRefundData(endRefund);
			
			if (StringUtils.isNotEmpty(state)) {
				vo.setState(Integer.parseInt(state));
			}
			vo.setOrderStatus(orderStatus);
			List<OrderSummaryVo> list = factoryService.getSaleOrderService().queryRefundOrders(vo, page);
			String jsonList = "";
			if (null != list && list.size() > 0) {
				jsonList = JSON.toJSONString(list);
			} else {
				jsonList = "[]";
			}
			jsonObject.put("total", page.getTotalCount());
			jsonObject.put("list", jsonList);
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	// 查询留票订单
	public String queryOrderByID() {
		request.setAttribute("operator", "reserve");
		try {
			String brid = request.getParameter("brid");
			if (StringUtils.isNotEmpty(brid)) {
				BaseRefundEntity baseRefundEntity = this.factoryService.getBaseRefundService().queryById(brid);
				if (baseRefundEntity == null) {
					request.setAttribute(ERROR, "该brid的BaseRefund不存在");
					return "refundQuery";
				}
				String fkid = baseRefundEntity.getFkid();
				GjSaleOrderEntity saleOrderEntity = this.factoryService.getSaleOrderService().queryOrderByID(fkid);
				if (null == saleOrderEntity) {
					request.setAttribute(ERROR, "该订单销售表数据不存在");
					return "refundQuery";
				}
				GjBuyOrderEntity buyOrderEntity=this.factoryService.getGjBuyOrderService().queyOrderByID(fkid);
				if (null == buyOrderEntity) {
					request.setAttribute(ERROR, "该订单采购表数据不存在");
					return "refundQuery";
				}
				
				// 更新锁定人
				User user = SecurityContext.getUser();
				String locker = user.getName();
				if (null == baseRefundEntity.getLocker() || !locker.equals(baseRefundEntity.getLocker())) {
					this.factoryService.getBaseRefundService().updateLocker(brid, locker);
				}
				
				Set<RefundEntity> refundList = baseRefundEntity.getRefunds();
				if (null != refundList && refundList.size() > 0) {
					getRefundFlightAndPsg(refundList, saleOrderEntity, buyOrderEntity);
					
					// 查询留票信息
					/*BaseRefundEntity checkBr = new BaseRefundEntity();
					checkBr.setId(brid);
					checkBr.setState(2);
					List<BaseRefundEntity> baseRefundList = this.factoryService.getBaseRefundService().queryList(checkBr);
					if (null != baseRefundList && baseRefundList.size() > 0) {
						request.setAttribute("reservedRefundList", baseRefundList);
					}*/
					request.setAttribute("baseRefund", baseRefundEntity);
					request.setAttribute("orderDetail", saleOrderEntity);
					request.setAttribute("buyOrderEntity", buyOrderEntity);
					return "reserve";
				} else {
					request.setAttribute("error", "退票表数据不存在");
				}
			} else {
				request.setAttribute(ERROR, "brid不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(ERROR, e.toString());
		}
		return "refundQuery";
	}
	
	/**
	 * 获取留票表中的航班和乘客信息
	 * 并将其保存到saleOrderEntity和buyOrderEntity
	 * @param refundList
	 * @param saleOrderEntity
	 * @param buyOrderEntity
	 */
	private void getRefundFlightAndPsg(Set<RefundEntity> refundList, GjSaleOrderEntity saleOrderEntity, GjBuyOrderEntity buyOrderEntity) {
		Set<GjSaleFlightEntity> sfList = saleOrderEntity.getFlights();
		Set<GjSalePassengerEntity> spList = saleOrderEntity.getPassengers();
		Set<GjBuyFlightEntity> bfList = buyOrderEntity.getFlights();
		Set<GjBuyPassengerEntity> bpList = buyOrderEntity.getPassengers();
		
		Set<GjSaleFlightEntity> rsfList = new HashSet<GjSaleFlightEntity>();
		Set<GjSalePassengerEntity> rspList = new HashSet<GjSalePassengerEntity>();
		Set<GjBuyFlightEntity> rbfList = new HashSet<GjBuyFlightEntity>();
		Set<GjBuyPassengerEntity> rbpList = new HashSet<GjBuyPassengerEntity>();
		
		for (RefundEntity refundEntity : refundList) {
			String psgid = refundEntity.getPsgid();
			String fltid = refundEntity.getFltid();
			
			for (GjSaleFlightEntity saleFlightEntity : sfList) {
				if (fltid.equals(saleFlightEntity.getId())) {
					rsfList.add(saleFlightEntity);
				}
			}
			for (GjSalePassengerEntity salePassengerEntity : spList) {
				if (psgid.equals(salePassengerEntity.getId())) {
					rspList.add(salePassengerEntity);
				}
			}
			for (GjBuyFlightEntity buyFlightEntity : bfList) {
				if (fltid.equals(buyFlightEntity.getId())) {
					rbfList.add(buyFlightEntity);
				}
			}
			for (GjBuyPassengerEntity buyPassengerEntity : bpList) {
				if (psgid.equals(buyPassengerEntity.getId())) {
					rbpList.add(buyPassengerEntity);
				}
			}
		}
		saleOrderEntity.setPassengerCount(rspList.size());
		saleOrderEntity.setPassengers(rspList);
		saleOrderEntity.setFlights(rsfList);
		buyOrderEntity.setPassengerCount(rbpList.size());
		buyOrderEntity.setPassengers(rbpList);
		buyOrderEntity.setFlights(rbfList);
	}
	
	/**
	 * 锁定
	 */
	public void updateLocker() {
		try {
			String id = request.getParameter("id");
			String locker = request.getParameter("lockUser");
			if (StringUtilsc.isNotEmptyAndNULL(id)) {
				this.factoryService.getBaseRefundService().updateLocker(id, locker);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("success", "解锁成功");
				if(StringUtilsc.isNotEmpty(locker)){
					addSysLogMsg(id, locker+"锁定留票订单。");
					info(locker+"锁定留票订单");
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
	 * 查询航班时刻信息
	 */
	public void getFlightDynamics() {
		try {
			JSONObject result = new JSONObject();

			String depCode = request.getParameter("depCode");
			String arrCode = request.getParameter("arrCode");
			String depDate = request.getParameter("depDate");
			//String fligntNum = request.getParameter("fligntNum");
			
			if (StringUtils.isNotEmpty(depCode) && StringUtils.isNotEmpty(arrCode) && StringUtils.isNotEmpty(depDate)) {
				FlightDynamicsDomain fdv = new FlightDynamicsDomain();
				fdv.setDepCode(depCode);
				fdv.setArrCode(arrCode);
				fdv.setDepDate(depDate);
				//fdv.setFlightNum(fligntNum);
				fdv.setIsGetCache(1);
				
				FlightDynamicsResult frs = this.factoryService.getAllApiService().getFlightDynamics(fdv);
				String list = "";
				if (frs != null) {
					list = JsonPluginsUtil.beanToJson(frs);
				} else {
					list = "[]";
				}
				//System.out.println(list);
				result.put("list", list);
			} else {
				result.put(ERROR, "出发机场、到达机场和出发时间不能为空.");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	
	/**
	 * 获取rt信息
	 */
	public void getEtermInfo() {
		try {
			//id表示订单表中的id
			String id = request.getParameter("id");
			//passenger表里面的id字段
			String passengerId = request.getParameter("passengerId");
			GjBuyOrderEntity buyOrderEntity = this.factoryService.getGjBuyOrderService().queyOrderByID(id);
			if (buyOrderEntity != null) {
				BaseAccountEntity baseAccountEntity = factoryService.getBaseAccountService().queryBaseAccountByID(buyOrderEntity.getAccountid());
				String appkey="";
				String cmds="";
				BaseOfficeEntity paramVo=new BaseOfficeEntity();
				String mno=baseAccountEntity.getMno();
				String office = baseAccountEntity.getOffice();
				paramVo.setMno(mno);
				paramVo.setOffice(office);
				List<BaseOfficeEntity> baseOfficeEntities = factoryService.getBaseOfficeService().queryByParamVo(paramVo);
				if (baseOfficeEntities == null || baseOfficeEntities.size() < 1) {
					//携程的订单rt不需要带office信息
					throw new RuntimeException("未查询到该商户对应的office信息");
				}
				appkey=baseOfficeEntities.get(0).getAppkey();
				Set<GjBuyPassengerEntity> buyPassengerEntities=buyOrderEntity.getPassengers();
				Iterator< GjBuyPassengerEntity> passengerIterator=buyPassengerEntities.iterator();
				String pnrCode=null;
				while(passengerIterator.hasNext()){
					GjBuyPassengerEntity buyPassengerEntity = passengerIterator.next();
					if(passengerId.equals(buyPassengerEntity.getId())){
						pnrCode=buyPassengerEntity.getPnr();
						break;
					}
				}
				if(BBOOConstants.GjSaleOrderEntity_flightClass_I.equals(buyOrderEntity.getFlightClass())){
					cmds="RT:" + pnrCode+"^QTE:/"+buyOrderEntity.getCarrier();
				}else{
					//国内的要单独处理
					cmds="RT:" + pnrCode+"^PAT:A";
				}
				GDSResult rs = null;
				rs = factoryService.getAllApiService().getCmdsByThirdInterface(appkey, cmds);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("success", "查询成功");
				if(BBOOConstants.GjSaleOrderEntity_flightClass_I.equals(buyOrderEntity.getFlightClass())){
					jsonObject.put("eterm","RT:" +buyOrderEntity.getPnrCode()+"\r"+rs.getTxt()[0]+"\r"+"QTE:/"+buyOrderEntity.getCarrier()+"\r"+rs.getTxt()[1]);
				}else{
					jsonObject.put("eterm","RT:" +buyOrderEntity.getPnrCode()+"\r"+rs.getTxt()[0]+"\r"+"PAT:A\r"+rs.getTxt()[1]);
				}
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	// 添加订单日志
	public void addSysLogMsg(String foreginKey, String contents) throws Exception{
		User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
		String visitip = request.getRemoteAddr();
		sysLog.addSysLog(user, visitip, foreginKey, "REFUND_LOG", contents);
	}

	public static void info(String msg){
		OOLogUtil.info(msg, BaseRefundAction.class, null);
	}
	
}
