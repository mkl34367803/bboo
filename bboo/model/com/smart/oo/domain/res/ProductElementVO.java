package com.smart.oo.domain.res;

import java.util.List;

/**
 * 返点
 * 
 * @author eric
 * 
 */
public class ProductElementVO implements Comparable<ProductElementVO> {

	private int bj(ProductElementVO o) {
		int comInt = 0;
		if (o.getPriceList() != null && o.getPriceList().size() > 0) {
			if (this.getPriceList() != null && this.getPriceList().size() > 0) {
				Double thisprice = this.getPriceList().get(0).getDisPrice();
				Double objprice = o.getPriceList().get(0).getDisPrice();
				if (thisprice != null && objprice != null) {
					double xiangc = thisprice - objprice;
					if (xiangc > 0) {
						comInt = (int) (xiangc) + 1;
					} else {
						comInt = (int) (xiangc) - 1;
					}
				}
			}
		}
		return comInt;
	}

	@Override
	public int compareTo(ProductElementVO o) {
		// TODO Auto-generated method stub
		int comInt = 0;
		comInt = this.bj(o);
		return (comInt != 0 ? this.bj(o) : comInt);
	}

	/**
	 * 政策ID
	 */
	private String pid;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 政策ID 01
	 */
	private String pidExt01;
	/**
	 * 政策ID 02
	 */
	private String pidExt02;
	/**
	 * 政策ID 03
	 */
	private String pidExt03;
	/**
	 * 政策账号ID （原样返回）
	 */
	private String accountId;
	/**
	 * 供应商ID
	 */
	private String vid;
	/**
	 * 政策 出票OFFICE号
	 */
	private String offNo;
	/**
	 * 旅行有效期开始
	 */
	private String travelTimeBegin;
	/**
	 * 旅行有效期截至
	 */
	private String travelTimeEnd;

	/**
	 * 工作有效期开始 2016-03-03 00:00
	 */
	private String workTimeBegin;
	/**
	 * 工作有效期截至 2016-03-03 23:50
	 */
	private String workTimeEnd;

	/**
	 * 退票有效期开始 2016-03-03 00:00
	 */
	private String refundTimeBegin;
	/**
	 * 退票有效期截至 2016-03-03 23:50
	 */
	private String refundTimeEnd;

	/**
	 * 班期限制:1/2/3/4/5/6/7
	 */
	private String weekLimit;
	/**
	 * 乘客限制(FIT:散客/TEA:团队/FT:散客及团队)
	 */
	private String psgLimit;

	/**
	 * S:单程/R:往返/SR:单程及往返 T:中转
	 */
	private String tripLimit;

	/**
	 * ADU 成人 CHD 儿童 INF 婴儿
	 */
	private String psgType;

	/**
	 * AIRB2B 517NA 51BOOK 8000YI 19E BAITOUR SELFGW QUNAR QUA CTRIP 380 SELFB2B
	 * JINRI PIAOMENG
	 */
	private String productSource;

	/**
	 * 航班号限制类型:0:不限制 / 1:适用航班号 / 2:限制（排除）航班号
	 */
	private Integer flightLimitType;
	/**
	 * 限制航班号:政策对应航班号，根据FlightLimitTyp字段确定是包含或排除 9762/9856
	 */
	private String flightNum;

	/**
	 * 限制政策舱位 用/分割
	 */
	private String cabinLimit;

	/**
	 * 限制政策始发城市 用/分割
	 */
	private String orgLimit;

	/**
	 * 限制政策目的城市 用/分割
	 */
	private String dstLimit;

	/**
	 * TRUE 换编码 FASLE 原编码
	 */
	private Boolean isChangePnr;

	/**
	 * 产品类型 (N:普通政策/W:特殊外放政策/T:特殊政策/A:全国政策/ L最低价)
	 */
	private String rateType;

	/**
	 * 政策类型 BSP B2B TH
	 */
	private String productType;

	/**
	 * 出票速度
	 */
	private String speed;

	/**
	 * 乘机人数
	 */
	private Integer psgCount = 1;

	/**
	 * 运价信息
	 */
	private List<AirPriceVO> priceList;
	/**
	 * 航程信息
	 */
	private List<AirTripVO> tripList;

	/**
	 * 乘机人信息
	 */
	private List<AirPsgVO> psgList;

	/**
	 * 优惠券信息
	 */
	private CouponInfoVO couponInfo;

	/**
	 * 返回的 异常 信息
	 */
	private String msgInfo;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 缓存时间
	 */
	private String cacheTime;

	/**
	 * 格式：LOW-只出低价、HIGH-只出高价、BOTH 高、低价都支 TH 其它
	 */
	private String horl;

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

	public Boolean getIsChangePnr() {
		return isChangePnr;
	}

	public void setIsChangePnr(Boolean isChangePnr) {
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

	public List<AirPriceVO> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<AirPriceVO> priceList) {
		this.priceList = priceList;
	}

	public List<AirTripVO> getTripList() {
		return tripList;
	}

	public void setTripList(List<AirTripVO> tripList) {
		this.tripList = tripList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPsgCount() {
		return psgCount;
	}

	public void setPsgCount(Integer psgCount) {
		this.psgCount = psgCount;
	}

	public List<AirPsgVO> getPsgList() {
		return psgList;
	}

	public void setPsgList(List<AirPsgVO> psgList) {
		this.psgList = psgList;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public CouponInfoVO getCouponInfo() {
		return couponInfo;
	}

	public void setCouponInfo(CouponInfoVO couponInfo) {
		this.couponInfo = couponInfo;
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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
