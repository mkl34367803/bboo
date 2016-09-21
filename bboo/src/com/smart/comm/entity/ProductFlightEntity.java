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
@Table(name = "t_product_flight")
public class ProductFlightEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2589621186364068066L;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductDataEntity.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fkpid")
	private ProductDataEntity product;
	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 注册表主键
	 */
	@Column(name = "fkpid", length = 55, insertable = false, updatable = false)
	private String fkpid;
	/**
	 * 航班号 多个航班用“/”分隔
	 */
	@Column(name = "airNo", length = 15)
	private String airNo;

	/**
	 * 航班号 多个航班用“/”分隔
	 */
	@Column(name = "shareAirNo", length = 15)
	private String shareAirNo;
	/**
	 * 到达时间 2016-03-30 06:20:00
	 */
	@Column(name = "airDstTime", length = 30)
	private String airDstTime;
	/**
	 * 起飞时间 2016-03-30 06:20:00
	 */
	@Column(name = "airOrgTime", length = 30)
	private String airOrgTime;
	/**
	 * 舱位 多个航班用“/”分隔
	 */
	@Column(name = "bunkNo", length = 35)
	private String bunkNo;
	@Column(name = "dstCity", length = 15)
	private String dstCity;
	@Column(name = "orgCity", length = 15)
	private String orgCity;
	@Column(name = "dstCode", length = 15)
	private String dstCode;
	@Column(name = "orgCode", length = 15)
	private String orgCode;
	@Column(name = "orgCodeCh", length = 50)
	private String orgCodeCh;
	@Column(name = "dstCodeCh", length = 50)
	private String dstCodeCh;
	/**
	 * 舱位等级 0-头等舱；1-商务舱；2-经济舱
	 */
	@Column(name = "subclass", length = 10)
	private String subclass;
	/**
	 * 退改签
	 */
	@Column(name = "tgq", length = 1000)
	private String tgq;

	/**
	 * 舱位剩余座位数
	 */
	@Column(name = "amount", length = 10)
	private String amount;
	@Column(name = "depTerm", length = 10)
	private String depTerm;
	@Column(name = "arrTerm", length = 10)
	private String arrTerm;
	@Column(name = "flightType", length = 15)
	private String flightType;

	public String getAirNo() {
		return airNo;
	}

	public void setAirNo(String airNo) {
		this.airNo = airNo;
	}

	public String getAirDstTime() {
		return airDstTime;
	}

	public void setAirDstTime(String airDstTime) {
		this.airDstTime = airDstTime;
	}

	public String getDstCity() {
		return dstCity;
	}

	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}

	public String getAirOrgTime() {
		return airOrgTime;
	}

	public void setAirOrgTime(String airOrgTime) {
		this.airOrgTime = airOrgTime;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getBunkNo() {
		return bunkNo;
	}

	public void setBunkNo(String bunkNo) {
		this.bunkNo = bunkNo;
	}

	public String getTgq() {
		return tgq;
	}

	public void setTgq(String tgq) {
		this.tgq = tgq;
	}

	public String getDstCode() {
		return dstCode;
	}

	public void setDstCode(String dstCode) {
		this.dstCode = dstCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getShareAirNo() {
		return shareAirNo;
	}

	public void setShareAirNo(String shareAirNo) {
		this.shareAirNo = shareAirNo;
	}

	public String getDepTerm() {
		return depTerm;
	}

	public void setDepTerm(String depTerm) {
		this.depTerm = depTerm;
	}

	public String getArrTerm() {
		return arrTerm;
	}

	public void setArrTerm(String arrTerm) {
		this.arrTerm = arrTerm;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getOrgCodeCh() {
		return orgCodeCh;
	}

	public void setOrgCodeCh(String orgCodeCh) {
		this.orgCodeCh = orgCodeCh;
	}

	public String getDstCodeCh() {
		return dstCodeCh;
	}

	public void setDstCodeCh(String dstCodeCh) {
		this.dstCodeCh = dstCodeCh;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProductDataEntity getProduct() {
		return product;
	}

	public void setProduct(ProductDataEntity product) {
		this.product = product;
	}

	public String getFkpid() {
		return fkpid;
	}

	public void setFkpid(String fkpid) {
		this.fkpid = fkpid;
	}

}
