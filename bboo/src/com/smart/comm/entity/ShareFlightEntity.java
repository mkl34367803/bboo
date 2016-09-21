package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_share_flight")
public class ShareFlightEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4932406001816335629L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "carrier", length = 10)
	private String carrier;
	@Column(name = "flightNo", length = 10)
	private String flightNo;
	@Column(name = "cabin", length = 10)
	private String cabin;
	@Column(name = "shareCode", length = 10)
	private String shareCode;
	@Column(name = "shareNo", length = 10)
	private String shareNo;
	@Column(name = "shareCabin", length = 10)
	private String shareCabin;
	@Column(name = "depCode", length = 10)
	private String depCode;
	@Column(name = "arrCode", length = 10)
	private String arrCode;
	@Column(name = "price", length = 10)
	private String price;
	@Column(name = "backPoint", length = 10)
	private String backPoint;
	/**
	 * 返点 定额
	 */
	@Column(name = "fixedFee", length = 10)
	private String fixedFee;
	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getShareNo() {
		return shareNo;
	}

	public void setShareNo(String shareNo) {
		this.shareNo = shareNo;
	}

	public String getShareCabin() {
		return shareCabin;
	}

	public void setShareCabin(String shareCabin) {
		this.shareCabin = shareCabin;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBackPoint() {
		return backPoint;
	}

	public void setBackPoint(String backPoint) {
		this.backPoint = backPoint;
	}

	public String getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(String fixedFee) {
		this.fixedFee = fixedFee;
	}

}
