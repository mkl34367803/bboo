package com.smart.comm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_change")
public class ChangeEntity extends BBOOObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4665350176185618811L;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = BaseChangeEntity.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fkcid")
	private BaseChangeEntity base;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 80)
	private String id;
	@Column(name = "fkcid", length = 80, nullable = false, insertable = false, updatable = false)
	private String fkcid;// 改签订单表
	@Column(name = "psgid", length = 80, nullable = false)
	private String psgid;// 乘机人表ID
	@Column(name = "fltid", length = 80, nullable = false)
	private String fltid;// 航班表ID
	/**
	 * 改签的航班号
	 */
	@Column(name = "flightNum", length = 10)
	private String flightNum;
	/**
	 * 改签的舱位
	 */
	@Column(name = "cabin", length = 5)
	private String cabin;
	/**
	 * 改签的出发日期
	 */
	@Column(name = "departureDate", length = 15)
	private String departureDate;
	/**
	 * 改签的起飞时间
	 */
	@Column(name = "departureTime", length = 10)
	private String departureTime;
	/**
	 * 改签的降落时间
	 */
	@Column(name = "arrivalTime", length = 10)
	private String arrivalTime;
	/**
	 * 改签的编码
	 */
	@Column(name = "pnrCode", length = 10)
	private String pnrCode;
	/**
	 * @采购地
	 */
	@Column(name = "purchasePlace", length = 10)
	private String purchasePlace;

	/**
	 * &采购地 中文
	 */
	@Column(name = "purchasePlaceCh", length = 30)
	private String purchasePlaceCh;
	/**
	 * @支付方式
	 */
	@Column(name = "payChannel", length = 55)
	private String payChannel;
	/**
	 * @支付方式
	 */
	@Column(name = "payChannelCh", length = 55)
	private String payChannelCh;
	/**
	 * @收款方式
	 */
	@Column(name = "receivablesChannel", length = 55)
	private String receivablesChannel;
	/**
	 * @收款方式
	 */
	@Column(name = "receivablesChannelCh", length = 55)
	private String receivablesChannelCh;

	/**
	 * @支付账号信息
	 */
	@Column(name = "payAccount", length = 55)
	private String payAccount;
	/**
	 * 支付金额(给供应的钱)
	 */
	@Column(name = "cost")
	private Double cost;
	/**
	 * 收款金额(收客户钱)
	 */
	@Column(name = "amount")
	private Double amount;
	/**
	 * 退票实际收款
	 */
	@Column(name = "refundment")
	private Double refundment;
	/**
	 * 新的电子客票号
	 */
	@Column(name = "ticketNum", length = 25)
	private String ticketNum;

	@Column(name = "createTime", length = 25)
	private String createTime;

	public BaseChangeEntity getBase() {
		return base;
	}

	public void setBase(BaseChangeEntity base) {
		this.base = base;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkcid() {
		return fkcid;
	}

	public void setFkcid(String fkcid) {
		this.fkcid = fkcid;
	}

	public String getPsgid() {
		return psgid;
	}

	public void setPsgid(String psgid) {
		this.psgid = psgid;
	}

	public String getFltid() {
		return fltid;
	}

	public void setFltid(String fltid) {
		this.fltid = fltid;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getPnrCode() {
		return pnrCode;
	}

	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
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

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getPayChannelCh() {
		return payChannelCh;
	}

	public void setPayChannelCh(String payChannelCh) {
		this.payChannelCh = payChannelCh;
	}

	public String getReceivablesChannel() {
		return receivablesChannel;
	}

	public void setReceivablesChannel(String receivablesChannel) {
		this.receivablesChannel = receivablesChannel;
	}

	public String getReceivablesChannelCh() {
		return receivablesChannelCh;
	}

	public void setReceivablesChannelCh(String receivablesChannelCh) {
		this.receivablesChannelCh = receivablesChannelCh;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRefundment() {
		return refundment;
	}

	public void setRefundment(Double refundment) {
		this.refundment = refundment;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
}
