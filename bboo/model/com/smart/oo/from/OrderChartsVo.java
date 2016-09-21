package com.smart.oo.from;

public class OrderChartsVo {
	// 搜索条件
	private String startCreateDate;
	private String endCreateDate;
	// 航班类型N：国内 I：国际
	private String flightClass;
	
	// 航司
	private String carrier;
	
	// 采购渠道
	private String purchasePlaceCh;
	
	// 出票员
	private String operator;
	
	// 乘机人类型
	private String ageDes;
	
	// 分销渠道    分销商
	private String distributor;
	
	// 店铺名称 分销商 对应的店铺名称
	private String shopName;
	
	// 日期
	private String createTime;
	
	// 票量
	private String ticketNum;
	// 利润
	private String profit;
	// 销售额
	private String saleroom;

	public String getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(String startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getPurchasePlaceCh() {
		return purchasePlaceCh;
	}

	public void setPurchasePlaceCh(String purchasePlaceCh) {
		this.purchasePlaceCh = purchasePlaceCh;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAgeDes() {
		return ageDes;
	}

	public void setAgeDes(String ageDes) {
		this.ageDes = ageDes;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getSaleroom() {
		return saleroom;
	}

	public void setSaleroom(String saleroom) {
		this.saleroom = saleroom;
	}

}
