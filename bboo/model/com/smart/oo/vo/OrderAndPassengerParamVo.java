package com.smart.oo.vo;

import java.io.Serializable;

import javax.persistence.Column;

public class OrderAndPassengerParamVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 和订单中createTime对应
	 */
	private String startCreateTime;
	/**
	 * 和订单中createTime对应
	 */
	private String endCreateTime;
	/**
	 * 订单状态，1表示支付成功等待出票
	 */
	private String slfStatus;

	/**
	 * N：国内 I：国际
	 */
	private String flightClass;

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getSlfStatus() {
		return slfStatus;
	}

	public void setSlfStatus(String slfStatus) {
		this.slfStatus = slfStatus;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
}
