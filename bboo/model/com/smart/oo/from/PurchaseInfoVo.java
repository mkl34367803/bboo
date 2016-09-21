package com.smart.oo.from;

import java.io.Serializable;

public class PurchaseInfoVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String purchaseNo;
	private String pnrCode;
	private String payWay;
	private String payAccount ;
	private String transactionNo;
	private String printPrice ;
	private String backPoint ;
	private String afterPoint;
	private String stickingPoint ;
	private String reward ;
	private String price;//支付机票款(单个人) printPrice*（1-backPoint/100）-reward
//	String tax = request.getParameter("tax"); //税费==基建+燃油
	private String payPirce;
	private String oilFee;
	private String taxFee;
	private String lr;
	private String allprice;
	private String purchasePlace;
	private String purchasePlaceCh;
	private String slfRemark;
	
	public PurchaseInfoVo(String id, String purchaseNo, String pnrCode, String payWay, String payAccount, String transactionNo, String printPrice, String backPoint, String afterPoint,
			String stickingPoint, String reward, String price, String payPirce, String oilFee, String taxFee, String lr, String allprice, String purchasePlace, String purchasePlaceCh, String slfRemark) {
		super();
		this.id = id;
		this.purchaseNo = purchaseNo;
		this.pnrCode = pnrCode;
		this.payWay = payWay;
		this.payAccount = payAccount;
		this.transactionNo = transactionNo;
		this.printPrice = printPrice;
		this.backPoint = backPoint;
		this.afterPoint = afterPoint;
		this.stickingPoint = stickingPoint;
		this.reward = reward;
		this.price = price;
		this.payPirce = payPirce;
		this.oilFee = oilFee;
		this.taxFee = taxFee;
		this.lr = lr;
		this.allprice = allprice;
		this.purchasePlace = purchasePlace;
		this.purchasePlaceCh = purchasePlaceCh;
		this.slfRemark = slfRemark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public String getPayWay() {
		return payWay;
	}
	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getPrintPrice() {
		return printPrice;
	}
	public void setPrintPrice(String printPrice) {
		this.printPrice = printPrice;
	}
	public String getBackPoint() {
		return backPoint;
	}
	public void setBackPoint(String backPoint) {
		this.backPoint = backPoint;
	}
	public String getAfterPoint() {
		return afterPoint;
	}
	public void setAfterPoint(String afterPoint) {
		this.afterPoint = afterPoint;
	}
	public String getStickingPoint() {
		return stickingPoint;
	}
	public void setStickingPoint(String stickingPoint) {
		this.stickingPoint = stickingPoint;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPayPirce() {
		return payPirce;
	}
	public void setPayPirce(String payPirce) {
		this.payPirce = payPirce;
	}
	public String getOilFee() {
		return oilFee;
	}
	public void setOilFee(String oilFee) {
		this.oilFee = oilFee;
	}
	public String getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(String taxFee) {
		this.taxFee = taxFee;
	}
	public String getLr() {
		return lr;
	}
	public void setLr(String lr) {
		this.lr = lr;
	}
	public String getAllprice() {
		return allprice;
	}
	public void setAllprice(String allprice) {
		this.allprice = allprice;
	}
	public String getPurchasePlace() {
		return purchasePlace;
	}
	public void setPurchasePlace(String purchasePlace) {
		this.purchasePlace = purchasePlace;
	}
	public String getPurchasePlaceCh() {
		return purchasePlaceCh;
	}
	public void setPurchasePlaceCh(String purchasePlaceCh) {
		this.purchasePlaceCh = purchasePlaceCh;
	}
	public String getSlfRemark() {
		return slfRemark;
	}
	public void setSlfRemark(String slfRemark) {
		this.slfRemark = slfRemark;
	}
}
