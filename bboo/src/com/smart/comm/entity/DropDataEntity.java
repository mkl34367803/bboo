package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_drop_data")
public class DropDataEntity extends BBOOObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4220421381938308786L;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 80)
	private String id;
	/**
	 * 商户号
	 */
	@Column(name = "mno", length = 40)
	private String mno;
	/**
	 * SZXPEKCZ2016-09-30
	 */
	@Column(name = "indexKey", length = 40)
	private String indexKey;
	/**
	 * 文件号
	 */
	@Column(name = "fileno", length = 50)
	private String fileno;// 文件号
	/**
	 * 数据来源 RIMP 报表导入 OIMP 订单同步
	 */
	@Column(name = "source", length = 10)
	private String source;
	@Column(name = "flightType", length = 10)
	private String flightType;
	@Column(name = "orderId", length = 50)
	private String orderId;
	@Column(name = "orderNo", length = 55)
	private String orderNo;
	@Column(name = "pnrCode", length = 30)
	private String pnrCode;
	@Column(name = "carrier", length = 10)
	private String carrier;
	@Column(name = "flightNo", length = 15)
	private String flightNo;
	@Column(name = "depCode", length = 10)
	private String depCode;
	@Column(name = "arrCode", length = 10)
	private String arrCode;
	@Column(name = "DDate", length = 25)
	private String DDate;
	@Column(name = "DTime", length = 10)
	private String DTime;
	@Column(name = "cabin", length = 5)
	private String cabin;
	@Column(name = "remark", length = 100)
	private String remark;
	@Column(name = "avtxt", length = 100)
	private String avtxt;
	@Column(name = "avtime", length = 25)
	private String avtime;
	/**
	 * 出票日期
	 */
	@Column(name = "ticketime", length = 25)
	private String ticketime;
	/**
	 * ADT CHD
	 */
	@Column(name = "psgType", length = 10)
	private String psgType;
	/**
	 * 创建时间
	 */
	@Column(name = "createTime", length = 25)
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public String getDDate() {
		return DDate;
	}

	public void setDDate(String dDate) {
		DDate = dDate;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDTime() {
		return DTime;
	}

	public void setDTime(String dTime) {
		DTime = dTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTicketime() {
		return ticketime;
	}

	public void setTicketime(String ticketime) {
		this.ticketime = ticketime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getIndexKey() {
		return indexKey;
	}

	public void setIndexKey(String indexKey) {
		this.indexKey = indexKey;
	}

	public String getAvtxt() {
		return avtxt;
	}

	public void setAvtxt(String avtxt) {
		this.avtxt = avtxt;
	}

	public String getAvtime() {
		return avtime;
	}

	public void setAvtime(String avtime) {
		this.avtime = avtime;
	}

	public String getPsgType() {
		return psgType;
	}

	public void setPsgType(String psgType) {
		this.psgType = psgType;
	}

}
