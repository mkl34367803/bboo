package com.smart.oo.vo;

import java.io.Serializable;


public class SaleOrderAndPassengerVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * order表中的订单号
	 */
	private String id;
	/**
	 * 订单号
	 */
	private String orderNo;

	/**
	 * 主订单号
	 */
	private String billId;
	/**
	 * accountId
	 */
	private String accountId;
	/**
	 * 乘客表中的id
	 */
	private String passengerId;
	/**
	 * 乘客姓名
	 */
	private String name;
	/**
	 * 证件号码
	 */
	private String cardNum;
	/**
	 * 票号pnrCode
	 */
	private String pnrCode;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
}
