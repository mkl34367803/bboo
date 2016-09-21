package com.smart.oo.from;

import java.io.Serializable;

public class SaleOrderStatistic implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 商户号
	 */
	private String mno;
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 航司
	 */
	private String carrier;
	/**
	 * 票量
	 */
	private Double ticketNumber;
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public Double getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(Double ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
