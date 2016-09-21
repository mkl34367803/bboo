package com.smart.oo.action.order;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.from.SaleOrderParamVo;
import com.smart.oo.service.imp.FactoryServiceImpl;

public class GjOrderSummaryAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 @Autowired
	 private FactoryServiceImpl factoryService;

	// 分页查询订单的汇总情况
	public void queryOrderByPage() {
		try {
			String flightClass = request.getParameter("flightClass"); // 表单的东西，传过来至少是个空串。
			String startDate = request.getParameter("startDate"); // 表单的东西，传过来至少是个空串。
			String endDate = request.getParameter("endDate");
			String contactMob = request.getParameter("contactMob");
			String flightType = request.getParameter("flightType");
			String orderNo = request.getParameter("orderNo");
			String passengerName = request.getParameter("passengerName");
			String ticketNum = request.getParameter("ticketNum");
			String shopNameCh = request.getParameter("shopNameCh");
			String allShopNameCh = request.getParameter("allShopNameCh");
			String pnrCode = request.getParameter("pnrCode");
			//这句很重要哦，以后，就用session里面的数据查询了。
			System.out.println(request.getSession().getAttribute("shopNameCh"));
			request.getSession().setAttribute("shopNameCh",shopNameCh);
			if("全部店铺".equals(allShopNameCh)){
				request.getSession().removeAttribute("shopNameCh");
			}
			System.out.println(request.getSession().getAttribute("shopNameCh"));
			String orderStatus = request.getParameter("orderStatus");
			SaleOrderParamVo saleOrderParamEntity = null; // 一种策略，Dao层判断为null的时候，就不去查询了
			if (StringUtils.isNotEmpty(flightClass)||StringUtils.isNotEmpty(startDate) || StringUtils.isNotEmpty(endDate) || StringUtils.isNotEmpty(contactMob) || StringUtils.isNotEmpty(flightType) || StringUtils.isNotEmpty(orderNo)
					|| StringUtils.isNotEmpty(passengerName) || StringUtils.isNotEmpty(ticketNum)) {
				saleOrderParamEntity = new SaleOrderParamVo();
				if (StringUtils.isNotEmpty(flightClass)) {
					saleOrderParamEntity.setFlightClass(request.getParameter("flightClass"));
				}
				if (StringUtils.isNotEmpty(startDate)) {
					saleOrderParamEntity.setStartDate(request.getParameter("startDate"));
				}
				if (StringUtils.isNotEmpty(endDate)) {
					saleOrderParamEntity.setEndDate(request.getParameter("endDate"));
				}
				if (StringUtils.isNotEmpty(contactMob)) {
					saleOrderParamEntity.setContactMob(request.getParameter("contactMob"));
				}
				if (StringUtils.isNotEmpty(flightType)) {
					saleOrderParamEntity.setFlightType(request.getParameter("flightType"));
				}
				if (StringUtils.isNotEmpty(orderNo)) {
					saleOrderParamEntity.setOrderNo(request.getParameter("orderNo"));
				}
				if (StringUtils.isNotEmpty(passengerName)) {
					saleOrderParamEntity.setPassengerName(request.getParameter("passengerName"));
				}
				if (StringUtils.isNotEmpty(ticketNum)) {
					saleOrderParamEntity.setTicketNum(request.getParameter("ticketNum"));
				}
				if (StringUtils.isNotEmpty(shopNameCh)) {
					saleOrderParamEntity.setShopNameCh(request.getParameter("shopNameCh"));
				}
				if (StringUtils.isNotEmpty(orderStatus)) {
					saleOrderParamEntity.setOrderStatus(request.getParameter("orderStatus"));
				}
				if (StringUtils.isNotEmpty(pnrCode)) {
					saleOrderParamEntity.setPnrCode(pnrCode);
				}
			}
			/*--------------------------订单查询时涉及到的表单参数-----------------------------*/
			String page = request.getParameter("page");
			if (StringUtils.isEmpty(page)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", "查询页为必填字段");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			Page pageEntity = new Page();
			pageEntity.setPageSize(20); // 30个订单做为一页
			pageEntity.setStartPage(Integer.parseInt(page));
			// 要从下面的user信息中取商户号mno，查询的时候带上商户mno
			User user = (User) request.getSession().getAttribute("SESSION_KEY_CURRENT_USER");
			//List<OrderSummaryVo> list = factoryService.getSaleOrderService().queryOrderSummaryByPage(pageEntity, user, saleOrderParamEntity);
			//List<OrderSummaryVo> list = factoryService.getSaleOrderService().readOrderSummaryByPage(pageEntity, user, saleOrderParamEntity);
			List<GjSaleOrderEntity> list = factoryService.getSaleOrderService().readOrderSummaryByPage1(pageEntity, user, saleOrderParamEntity);
			String jsonString = JSON.toJSONString(list);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("total", pageEntity.getTotalCount());
			jsonObject.put("list", jsonString);
			PrintWriter printWriter = response.getWriter();
			printWriter.println(jsonObject);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("error", e.toString());
				this.writeStream(jsonObject, "utf-8");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
