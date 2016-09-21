package com.smart.oo.from;

public class RefundVo {
	// 查询条件
	private String startDepartureDate;
	private String endDepartureDate;
	private String startRefundDate;
	private String endRefundData;
	private Integer state;// 1 退票 2 留票
	/**
	 * SELF 订单状态 0 订座成功等待支付(人工) 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30
	 * 退票申请中 31 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60
	 * 支付待确认（支付失败） 61 暂缓出票（出票失败） 62 订单超时 -1其它 99 订座成功等待支付（系统） 70退票留票中
	 */
	private String orderStatus;
	private String distributor;// 分销商

	private String startDate;
	private String endDate;
	private String carrier;
	private String pnrCode;
	private String orderNo;

	public String getStartDepartureDate() {
		return startDepartureDate;
	}

	public void setStartDepartureDate(String startDepartureDate) {
		this.startDepartureDate = startDepartureDate;
	}

	public String getEndDepartureDate() {
		return endDepartureDate;
	}

	public void setEndDepartureDate(String endDepartureDate) {
		this.endDepartureDate = endDepartureDate;
	}

	public String getStartRefundDate() {
		return startRefundDate;
	}

	public void setStartRefundDate(String startRefundDate) {
		this.startRefundDate = startRefundDate;
	}

	public String getEndRefundData() {
		return endRefundData;
	}

	public void setEndRefundData(String endRefundData) {
		this.endRefundData = endRefundData;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getPnrCode() {
		return pnrCode;
	}

	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}
