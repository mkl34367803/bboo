package com.smart.comm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_product_data")
public class ProductDataEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -277751850708227350L;

	@OneToMany(targetEntity = ProductPriceEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	@OrderBy("disPrice asc,payPrice asc,id desc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<ProductPriceEntity> priceList = new HashSet<ProductPriceEntity>();
	@OneToMany(targetEntity = ProductFlightEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	@OrderBy("id desc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<ProductFlightEntity> tripList = new HashSet<ProductFlightEntity>();
	@OneToMany(targetEntity = ProductPassengerEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
	@OrderBy("id asc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<ProductPassengerEntity> psgList = new HashSet<ProductPassengerEntity>();

	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 订单表主键
	 */
	@Column(name = "fkid", length = 55)
	private String fkid;
	/**
	 * 政策ID
	 */
	@Column(name = "pid", length = 45)
	private String pid;
	/**
	 * 政策账号ID （原样返回）
	 */
	@Column(name = "accountId", length = 35)
	private String accountId;
	/**
	 * 账号
	 */
	@Column(name = "account", length = 30)
	private String account;
	/**
	 * 政策ID 01
	 */
	@Column(name = "pidExt01", length = 45)
	private String pidExt01;
	/**
	 * 政策ID 02
	 */
	@Column(name = "pidExt02", length = 45)
	private String pidExt02;
	/**
	 * 政策ID 03
	 */
	@Column(name = "pidExt03", length = 45)
	private String pidExt03;
	/**
	 * 供应商ID
	 */
	@Column(name = "vid", length = 45)
	private String vid;
	/**
	 * 政策 出票OFFICE号
	 */
	@Column(name = "offNo", length = 20)
	private String offNo;
	/**
	 * 旅行有效期开始
	 */
	@Column(name = "travelTimeBegin", length = 30)
	private String travelTimeBegin;
	/**
	 * 旅行有效期截至
	 */
	@Column(name = "travelTimeEnd", length = 30)
	private String travelTimeEnd;

	/**
	 * 工作有效期开始 2016-03-03 00:00
	 */
	@Column(name = "workTimeBegin", length = 30)
	private String workTimeBegin;
	/**
	 * 工作有效期截至 2016-03-03 23:50
	 */
	@Column(name = "workTimeEnd", length = 30)
	private String workTimeEnd;

	/**
	 * 退票有效期开始 2016-03-03 00:00
	 */
	@Column(name = "refundTimeBegin", length = 30)
	private String refundTimeBegin;
	/**
	 * 退票有效期截至 2016-03-03 23:50
	 */
	@Column(name = "refundTimeEnd", length = 30)
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
	 * ADU 成人 CHD 儿童 INF 婴儿
	 */
	@Column(name = "psgType", length = 15)
	private String psgType;

	/**
	 * AIRB2B 517NA 51BOOK 8000YI 19E BAITOUR SELFGW QUNAR QUA CTRIP 380 SELFB2B
	 * JINRI PIAOMENG
	 */
	@Column(name = "productSource", length = 15)
	private String productSource;

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
	@Column(name = "cabinLimit", length = 150)
	private String cabinLimit;

	/**
	 * 限制政策始发城市 用/分割
	 */
	@Column(name = "orgLimit", length = 1000)
	private String orgLimit;

	/**
	 * 限制政策目的城市 用/分割
	 */
	@Column(name = "dstLimit", length = 1000)
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
	 * 出票速度
	 */
	@Column(name = "speed", length = 20)
	private String speed;

	/**
	 * 乘机人数
	 */
	@Column(name = "psgCount")
	private Integer psgCount = 1;

	/**
	 * 返回的 异常 信息
	 */
	@Column(name = "msgInfo", length = 500)
	private String msgInfo;

	/**
	 * 备注
	 */
	@Column(name = "remark", length = 1000)
	private String remark;

	/**
	 * 缓存时间
	 */
	@Column(name = "cacheTime", length = 28)
	private String cacheTime;

	/**
	 * 格式：LOW-只出低价、HIGH-只出高价、BOTH 高、低价都支
	 */
	@Column(name = "horl", length = 10)
	private String horl;
	@Column(name = "createTime", length = 38)
	private String createTime;

	public Set<ProductPriceEntity> getPriceList() {
		return priceList;
	}

	public void setPriceList(Set<ProductPriceEntity> priceList) {
		this.priceList = priceList;
	}

	public Set<ProductFlightEntity> getTripList() {
		return tripList;
	}

	public void setTripList(Set<ProductFlightEntity> tripList) {
		this.tripList = tripList;
	}

	public Set<ProductPassengerEntity> getPsgList() {
		return psgList;
	}

	public void setPsgList(Set<ProductPassengerEntity> psgList) {
		this.psgList = psgList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkid() {
		return fkid;
	}

	public void setFkid(String fkid) {
		this.fkid = fkid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPidExt01() {
		return pidExt01;
	}

	public void setPidExt01(String pidExt01) {
		this.pidExt01 = pidExt01;
	}

	public String getPidExt02() {
		return pidExt02;
	}

	public void setPidExt02(String pidExt02) {
		this.pidExt02 = pidExt02;
	}

	public String getPidExt03() {
		return pidExt03;
	}

	public void setPidExt03(String pidExt03) {
		this.pidExt03 = pidExt03;
	}

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
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

	public String getPsgType() {
		return psgType;
	}

	public void setPsgType(String psgType) {
		this.psgType = psgType;
	}

	public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
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

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Integer getPsgCount() {
		return psgCount;
	}

	public void setPsgCount(Integer psgCount) {
		this.psgCount = psgCount;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCacheTime() {
		return cacheTime;
	}

	public void setCacheTime(String cacheTime) {
		this.cacheTime = cacheTime;
	}

	public String getHorl() {
		return horl;
	}

	public void setHorl(String horl) {
		this.horl = horl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
