package com.smart.oo.from;

import java.io.Serializable;

public class BuyOrderParamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 订单来源，I表示国际，N表示国内
	 */
	private String flightClass;
	/**
	 * 订单状态
	 */
	private String slfStatus; 
	/**
	 * 商户号（必传字段）
	 */
	private String mno;
	/**
	 * 要查询的订单时间，从startDate的00:00点算起
	 */
	private  String startDate;
	/**
	 * 要查询的订单时间，从endDate的24:00点算起
	 */
	private String endDate;
	/**
	 * 要查询的订单时间，从purchaseStartDate的00:00点算起
	 */
	private  String purchaseStartDate;
	/**
	 * 要查询的订单时间，从purchaseEndDate的24:00点算起
	 */
	private String purchaseEndDate;
	/**
	 * 采购订单号
	 */
	private String purchaseNo;
	/**
	 * 航程类型：单程，往返
	 */
	private String flightType;
	/*
	 * 订单号
	 */
	private String orderNo;
	/**
	 * pnr
	 */
	private String pnrCode;
	/**
	 * 店铺名称中文
	 */
	private String shopNameCh;
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
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
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
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
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public String getShopNameCh() {
		return shopNameCh;
	}
	public void setShopNameCh(String shopNameCh) {
		this.shopNameCh = shopNameCh;
	}
	public String getSlfStatus() {
		return slfStatus;
	}
	public void setSlfStatus(String slfStatus) {
		this.slfStatus = slfStatus;
	}
	public String getPurchaseStartDate() {
		return purchaseStartDate;
	}
	public void setPurchaseStartDate(String purchaseStartDate) {
		this.purchaseStartDate = purchaseStartDate;
	}
	public String getPurchaseEndDate() {
		return purchaseEndDate;
	}
	public void setPurchaseEndDate(String purchaseEndDate) {
		this.purchaseEndDate = purchaseEndDate;
	}

	
}
