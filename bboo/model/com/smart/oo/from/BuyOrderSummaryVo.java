package com.smart.oo.from;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;


public class BuyOrderSummaryVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 创单时间
	 */
	private String createTime;
	/**
	 * SELF 添加时间
	 */
	private String addtime;
	/**
	 * 航程类型
	 */
	private String flightType;
	/**
	 * pnr
	 */
	private String pnrCode;

	/**
	 * 乘客总人数
	 */
	private Integer passengerCount;
	/**
	 * 订单状态
	 */
	private String slfStatus;
	/**
	 * 采购订单号
	 */
	private String purchaseNo;
	/**
	 * @采购地
	 */
	private String purchasePlace;
	/**
	 * &采购地 中文
	 */
	private String purchasePlaceCh;
	/**
	 * @票面价
	 */
	private Double printPrice;
	
	/**
	 * @支付机票款(单个人) printPrice*（1-backPoint/100）-reward
	 */
	private Double cost;
	/**
	 * @ 结算价=支付机票款（订单那边有个计算公式）+基建+燃油/个
	 */
	private Double payPirce;
	/**
	 * 订单总价
	 */
	private Double allprice;
	/**------------------------------下面开始全部是saleorder里面的信息------------------------------------------***/
	/**
	 * 分销商 T 淘宝 Q 去哪儿 C 携程 H 航班管家 K 酷讯 N 途牛 O 同程
	 */
	private String distributor;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 分销商 对应的店铺名称
	 */
	private String shopName;

	/**
	 * 分销商店铺 中文
	 */
	private String shopNameCh;
	/**
	 * 改签订单的原订单
	 */
	private String oldOrderNo;
	/**
	 * 锁定操作人
	 */
	private String lockUser;
	/**
	 * 航班类型，I国际，N国内
	 */
	private String flightClass;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public Double getAllprice() {
		return allprice;
	}
	public void setAllprice(Double allprice) {
		this.allprice = allprice;
	}
	public Integer getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}
	public String getSlfStatus() {
		return slfStatus;
	}
	public void setSlfStatus(String slfStatus) {
		this.slfStatus = slfStatus;
	}
	public String getLockUser() {
		return lockUser;
	}
	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}
	public String getPurchaseNo() {
		return purchaseNo;
	}
	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getPurchasePlace() {
		return purchasePlace;
	}
	public void setPurchasePlace(String purchasePlace) {
		this.purchasePlace = purchasePlace;
	}
	public Double getPrintPrice() {
		return printPrice;
	}
	public void setPrintPrice(Double printPrice) {
		this.printPrice = printPrice;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getPayPirce() {
		return payPirce;
	}
	public void setPayPirce(Double payPirce) {
		this.payPirce = payPirce;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getShopNameCh() {
		return shopNameCh;
	}
	public void setShopNameCh(String shopNameCh) {
		this.shopNameCh = shopNameCh;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getOldOrderNo() {
		return oldOrderNo;
	}
	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
	}
	public String getPurchasePlaceCh() {
		return purchasePlaceCh;
	}
	public void setPurchasePlaceCh(String purchasePlaceCh) {
		this.purchasePlaceCh = purchasePlaceCh;
	}
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
