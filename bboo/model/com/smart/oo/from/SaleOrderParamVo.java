package com.smart.oo.from;

import java.io.Serializable;

public class SaleOrderParamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 要查询的订单时间，从startDate的00:00点算起
	 */
	private  String startDate;
	/**
	 * 要查询的订单时间，从endDate的24:00点算起
	 */
	private String endDate;
	/**
	 * 联系人手机
	 */
	private String contactMob;
	/**
	 * 航程类型：单程，往返
	 */
	private String flightType;
	/*
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 乘机人姓名
	 */
	private String passengerName;
	/**
	 * 票号
	 */
	private String ticketNum;
	/**
	 * 店铺名称中文
	 */
	private String shopNameCh;
	/**
	 * 订单状态
	 */
	private String orderStatus; 
	/**
	 * 订单来源，I表示国际，N表示国内
	 */
	private String flightClass;
	/**
	 * PNR
	 */
	private String pnrCode;
	/**
	 * 商户号（必传字段）
	 */
	private String mno;
	/**
	 * 航司
	 */
	private String carrier;
	
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
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
	public String getContactMob() {
		return contactMob;
	}
	public void setContactMob(String contactMob) {
		this.contactMob = contactMob;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShopNameCh() {
		return shopNameCh;
	}
	public void setShopNameCh(String shopNameCh) {
		this.shopNameCh = shopNameCh;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public String getMno() {
		return mno;
	}
	public void setMno(String mno) {
		this.mno = mno;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
}
