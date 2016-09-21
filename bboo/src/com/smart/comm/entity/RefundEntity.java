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
@Table(name = "t_refund")
public class RefundEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3076906710690640079L;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = BaseRefundEntity.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fkrid")
	private BaseRefundEntity base;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 80)
	private String id;
	@Column(name = "fkrid", length = 80, nullable = false,insertable=false,updatable=false)
	private String fkrid;// 退票订单表
	@Column(name = "psgid", length = 80, nullable = false)
	private String psgid;// 乘机人表ID
	@Column(name = "fltid", length = 80, nullable = false)
	private String fltid;// 航班表ID

	/***************************************/
	@Column(name = "face")
	private Double face;// 舱位票面价
	@Column(name = "baseFace")
	private Double baseFace;// 退票的票面 或者 Y舱票面
	@Column(name = "yq")
	private Double yq;// 燃油
	@Column(name = "tax")
	private Double tax;// 机建
	@Column(name = "rate")
	private Double rate;// 退票费率
	@Column(name = "fee")
	private Double fee;// 退票费 baseFace*(1-rate/100) 或者 Yprice*(1-rate/100)
	@Column(name = "refund")
	private Double refund;// 退款金额 （销售价-fee +yq+tax） 如果 销售价-fee<0 则按0计算
							// ------------
							// { 上级退款 （采购金额-fee +yq+tax） 如果 采购金额-fee<0 则按0计算}
	@Column(name = "actRefund")
	private Double actRefund;// 实际退款金额 含税
	@Column(name = "verifyAmount")
	private Double verifyAmount;// 财务审核金额
	@Column(name = "rtype")
	private Integer rtype;// 0自愿 1非志愿
	@Column(name = "isvoid")
	private Integer isvoid;// 0作废 1退票
	@Column(name = "issubmit")
	private Integer issubmit; // 1提交 2未提交

	@Column(name = "face2")
	private Double face2;// 舱位票面价
	@Column(name = "baseFace2")
	private Double baseFace2;// 退票的票面 或者 Y舱票面
	@Column(name = "yq2")
	private Double yq2;// 燃油
	@Column(name = "tax2")
	private Double tax2;// 机建
	@Column(name = "rate2")
	private Double rate2;// 退票费率
	@Column(name = "fee2")
	private Double fee2;// 退票费 baseFace*(1-rate/100) 或者 Yprice*(1-rate/100)
	@Column(name = "refund2")
	private Double refund2;// 退款金额 （销售价-fee +yq+tax） 如果 销售价-fee<0 则按0计算
							// ------------
							// { 上级退款 （采购金额-fee +yq+tax） 如果 采购金额-fee<0 则按0计算}
	@Column(name = "actRefund2")
	private Double actRefund2;// 实际退款金额 含税
	@Column(name = "verifyAmount2")
	private Double verifyAmount2;// 财务审核金额
	@Column(name = "rtype2")
	private Integer rtype2;// 0自愿 1非志愿
	@Column(name = "isvoid2")
	private Integer isvoid2;// 0作废 1退票
	@Column(name = "issubmit2")
	private Integer issubmit2; // 1提交 2未提交
	/***************************************/
	@Column(name = "islock")
	private Integer islock;// 1正常（默认） 2锁定(不允许编辑)
	@Column(name = "createTime", length = 25)
	private String createTime;// 三种解释 1客户提交退票时间 2提交航司退票时间 3航司退款时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkrid() {
		return fkrid;
	}

	public void setFkrid(String fkrid) {
		this.fkrid = fkrid;
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

	public Double getFace() {
		return face;
	}

	public void setFace(Double face) {
		this.face = face;
	}

	public Double getBaseFace() {
		return baseFace;
	}

	public void setBaseFace(Double baseFace) {
		this.baseFace = baseFace;
	}

	public Double getYq() {
		return yq;
	}

	public void setYq(Double yq) {
		this.yq = yq;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getRefund() {
		return refund;
	}

	public void setRefund(Double refund) {
		this.refund = refund;
	}

	public Double getActRefund() {
		return actRefund;
	}

	public void setActRefund(Double actRefund) {
		this.actRefund = actRefund;
	}

	public Double getVerifyAmount() {
		return verifyAmount;
	}

	public void setVerifyAmount(Double verifyAmount) {
		this.verifyAmount = verifyAmount;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public Integer getIsvoid() {
		return isvoid;
	}

	public void setIsvoid(Integer isvoid) {
		this.isvoid = isvoid;
	}

	public Integer getIssubmit() {
		return issubmit;
	}

	public void setIssubmit(Integer issubmit) {
		this.issubmit = issubmit;
	}

	public Double getFace2() {
		return face2;
	}

	public void setFace2(Double face2) {
		this.face2 = face2;
	}

	public Double getBaseFace2() {
		return baseFace2;
	}

	public void setBaseFace2(Double baseFace2) {
		this.baseFace2 = baseFace2;
	}

	public Double getYq2() {
		return yq2;
	}

	public void setYq2(Double yq2) {
		this.yq2 = yq2;
	}

	public Double getTax2() {
		return tax2;
	}

	public void setTax2(Double tax2) {
		this.tax2 = tax2;
	}

	public Double getRate2() {
		return rate2;
	}

	public void setRate2(Double rate2) {
		this.rate2 = rate2;
	}

	public Double getFee2() {
		return fee2;
	}

	public void setFee2(Double fee2) {
		this.fee2 = fee2;
	}

	public Double getRefund2() {
		return refund2;
	}

	public void setRefund2(Double refund2) {
		this.refund2 = refund2;
	}

	public Double getActRefund2() {
		return actRefund2;
	}

	public void setActRefund2(Double actRefund2) {
		this.actRefund2 = actRefund2;
	}

	public Double getVerifyAmount2() {
		return verifyAmount2;
	}

	public void setVerifyAmount2(Double verifyAmount2) {
		this.verifyAmount2 = verifyAmount2;
	}

	public Integer getRtype2() {
		return rtype2;
	}

	public void setRtype2(Integer rtype2) {
		this.rtype2 = rtype2;
	}

	public Integer getIsvoid2() {
		return isvoid2;
	}

	public void setIsvoid2(Integer isvoid2) {
		this.isvoid2 = isvoid2;
	}

	public Integer getIssubmit2() {
		return issubmit2;
	}

	public void setIssubmit2(Integer issubmit2) {
		this.issubmit2 = issubmit2;
	}

	public Integer getIslock() {
		return islock;
	}

	public void setIslock(Integer islock) {
		this.islock = islock;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BaseRefundEntity getBase() {
		return base;
	}

	public void setBase(BaseRefundEntity base) {
		this.base = base;
	}

}
