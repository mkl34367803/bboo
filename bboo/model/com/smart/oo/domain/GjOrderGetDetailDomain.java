package com.smart.oo.domain;

public class GjOrderGetDetailDomain {

	private GjOrderAccountVO account;
	private String orderNo;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
