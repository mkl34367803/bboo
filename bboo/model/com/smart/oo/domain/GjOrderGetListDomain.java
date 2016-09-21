package com.smart.oo.domain;

public class GjOrderGetListDomain {

	private GjOrderAccountVO account;
	private String beginTime;// Date 可选 2015-01-13 21:15:59 订单起始日期
	private String endTime;// Date 可选 2015-01-13 21:15:59 订单结束日期
	private String currentPage;// Number 可选 1 当前页码
	/**
	 * 订单状态 0 订座成功等待支付 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30 退票申请中 31
	 * 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60 支付待确认 61
	 * 暂缓出票 62 订单超时 -1其它 70 出票失败 72订单作废
	 */
	private String orderStatus;
	private String pageSize;// Number 可选 20 分页大小
	private String fareSource;//
	/**
	 * N 国内  I国际 默认 国际
	 */
	private String orderType; 
	public GjOrderAccountVO getAccount() {
		return account;
	}

	public void setAccount(GjOrderAccountVO account) {
		this.account = account;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getFareSource() {
		return fareSource;
	}

	public void setFareSource(String fareSource) {
		this.fareSource = fareSource;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
