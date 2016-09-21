package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_local_policy")
public class LocalPolicyEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6756784628027825044L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	/**
	 * 政策ID号
	 */
	@Column(name = "policyId", length = 45, nullable = false)
	private String policyId;
	/**
	 * 政策ID 01
	 */
	@Column(name = "pidExt01", length = 45)
	private String pidExt01;
	/**
	 * 供应商ID
	 */
	@Column(name = "vid", length = 45)
	private String vid;
	/**
	 * 商户号
	 */
	@Column(name = "mno", length = 45, nullable = false)
	private String mno;

	/**
	 * 政策 出票OFFICE号
	 */
	@Column(name = "offNo", length = 20, nullable = false)
	private String offNo;
	/**
	 * 旅行有效期开始
	 */
	@Column(name = "travelTimeBegin", length = 30, nullable = false)
	private String travelTimeBegin;
	/**
	 * 旅行有效期截至
	 */
	@Column(name = "travelTimeEnd", length = 30, nullable = false)
	private String travelTimeEnd;

	/**
	 * 工作有效期开始 2016-03-03 00:00
	 */
	@Column(name = "workTimeBegin", length = 30, nullable = false)
	private String workTimeBegin;
	/**
	 * 工作有效期截至 2016-03-03 23:50
	 */
	@Column(name = "workTimeEnd", length = 30, nullable = false)
	private String workTimeEnd;

	/**
	 * 退票有效期开始 2016-03-03 00:00
	 */
	@Column(name = "refundTimeBegin", length = 30, nullable = false)
	private String refundTimeBegin;
	/**
	 * 退票有效期截至 2016-03-03 23:50
	 */
	@Column(name = "refundTimeEnd", length = 30, nullable = false)
	private String refundTimeEnd;

	/**
	 * 班期限制:1/2/3/4/5/6/7
	 */
	@Column(name = "weekLimit", length = 20)
	private String weekLimit;
	/**
	 * 乘客限制(FIT:散客/TEA:团队/FT:散客及团队)
	 */
	@Column(name = "psgLimit", length = 15)
	private String psgLimit;

	/**
	 * S:单程/R:往返/SR:单程及往返 T:中转
	 */
	@Column(name = "tripLimit", length = 15)
	private String tripLimit;
	/**
	 * 政策类型 BSP B2B
	 */
	@Column(name = "policyLimit")
	private String policyLimit;
	/**
	 * ADU 成人 CHD 儿童 INF 婴儿
	 */
	@Column(name = "psgType", length = 15)
	private String psgType;
	/**
	 * 航司二字代码
	 */
	@Column(name = "carrier", length = 5, nullable = false)
	private String carrier;
	/**
	 * 航班号限制类型:0:不限制 / 1:适用航班号 / 2:限制（排除）航班号
	 */
	@Column(name = "flightLimitType")
	private Integer flightLimitType;
	/**
	 * 限制航班号:政策对应航班号，根据FlightLimitTyp字段确定是包含或排除 9762/9856
	 */
	@Column(name = "flightNum", length = 1000)
	private String flightNum;

	/**
	 * 限制政策舱位 用/分割
	 */
	@Column(name = "cabinLimit", length = 150, nullable = false)
	private String cabinLimit;

	/**
	 * 限制政策始发城市 用/分割 全国999
	 */
	@Column(name = "orgLimit", length = 1000, nullable = false)
	private String orgLimit;

	/**
	 * 限制政策目的城市 用/分割 全国999
	 */
	@Column(name = "dstLimit", length = 1000, nullable = false)
	private String dstLimit;

	/**
	 * 1 换编码 2 原编码
	 */
	@Column(name = "isChangePnr")
	private Integer isChangePnr;

	/**
	 * 政策类型(N:普通政策/W:特殊外放政策/T:特殊政策/A:全国政策/ L最低价)
	 */
	@Column(name = "rateType", length = 10)
	private String rateType;

	/**
	 * 返点/单个人
	 */
	@Column(name = "rebates", nullable = false)
	private Double rebates;

	/**
	 * 奖励返点/单个人
	 */
	@Column(name = "promotion", nullable = false)
	private Double promotion = 0d;

	/**
	 * 后返点/单个人
	 */
	@Column(name = "after", nullable = false)
	private Double after = 0d;

	/**
	 * 奖金(返现)/单个人
	 */
	@Column(name = "fixedFee", nullable = false)
	private Double fixedFee = 0d;

	/**
	 * 备注
	 */
	@Column(name = "remark", length = 500)
	private String remark;

	@Column(name = "operator", length = 30)
	private String operator;
	/**
	 * 0适用 1停用
	 */
	@Column(name = "isuse", nullable = false)
	private Long isuse;

	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPidExt01() {
		return pidExt01;
	}

	public void setPidExt01(String pidExt01) {
		this.pidExt01 = pidExt01;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getOffNo() {
		return offNo;
	}

	public void setOffNo(String offNo) {
		this.offNo = offNo;
	}

	public String getTravelTimeBegin() {
		return travelTimeBegin;
	}

	public void setTravelTimeBegin(String travelTimeBegin) {
		this.travelTimeBegin = travelTimeBegin;
	}

	public String getTravelTimeEnd() {
		return travelTimeEnd;
	}

	public void setTravelTimeEnd(String travelTimeEnd) {
		this.travelTimeEnd = travelTimeEnd;
	}

	public String getWorkTimeBegin() {
		return workTimeBegin;
	}

	public void setWorkTimeBegin(String workTimeBegin) {
		this.workTimeBegin = workTimeBegin;
	}

	public String getWorkTimeEnd() {
		return workTimeEnd;
	}

	public void setWorkTimeEnd(String workTimeEnd) {
		this.workTimeEnd = workTimeEnd;
	}

	public String getRefundTimeBegin() {
		return refundTimeBegin;
	}

	public void setRefundTimeBegin(String refundTimeBegin) {
		this.refundTimeBegin = refundTimeBegin;
	}

	public String getRefundTimeEnd() {
		return refundTimeEnd;
	}

	public void setRefundTimeEnd(String refundTimeEnd) {
		this.refundTimeEnd = refundTimeEnd;
	}

	public String getWeekLimit() {
		return weekLimit;
	}

	public void setWeekLimit(String weekLimit) {
		this.weekLimit = weekLimit;
	}

	public String getPsgLimit() {
		return psgLimit;
	}

	public void setPsgLimit(String psgLimit) {
		this.psgLimit = psgLimit;
	}

	public String getTripLimit() {
		return tripLimit;
	}

	public void setTripLimit(String tripLimit) {
		this.tripLimit = tripLimit;
	}

	public String getPolicyLimit() {
		return policyLimit;
	}

	public void setPolicyLimit(String policyLimit) {
		this.policyLimit = policyLimit;
	}

	public String getPsgType() {
		return psgType;
	}

	public void setPsgType(String psgType) {
		this.psgType = psgType;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Integer getFlightLimitType() {
		return flightLimitType;
	}

	public void setFlightLimitType(Integer flightLimitType) {
		this.flightLimitType = flightLimitType;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getCabinLimit() {
		return cabinLimit;
	}

	public void setCabinLimit(String cabinLimit) {
		this.cabinLimit = cabinLimit;
	}

	public String getOrgLimit() {
		return orgLimit;
	}

	public void setOrgLimit(String orgLimit) {
		this.orgLimit = orgLimit;
	}

	public String getDstLimit() {
		return dstLimit;
	}

	public void setDstLimit(String dstLimit) {
		this.dstLimit = dstLimit;
	}

	public Integer getIsChangePnr() {
		return isChangePnr;
	}

	public void setIsChangePnr(Integer isChangePnr) {
		this.isChangePnr = isChangePnr;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public Double getRebates() {
		return rebates;
	}

	public void setRebates(Double rebates) {
		this.rebates = rebates;
	}

	public Double getPromotion() {
		return promotion;
	}

	public void setPromotion(Double promotion) {
		this.promotion = promotion;
	}

	public Double getAfter() {
		return after;
	}

	public void setAfter(Double after) {
		this.after = after;
	}

	public Double getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(Double fixedFee) {
		this.fixedFee = fixedFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getIsuse() {
		return isuse;
	}

	public void setIsuse(Long isuse) {
		this.isuse = isuse;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
