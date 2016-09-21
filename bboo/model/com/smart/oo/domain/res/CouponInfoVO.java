package com.smart.oo.domain.res;

public class CouponInfoVO {

	/**
	 * Number 123活动id
	 */
	private String actId;
	/**
	 * String demo优惠券id
	 */
	private String couponId;
	/**
	 * String 深航30元优惠券活动 活动信息描述
	 */
	private String actDesc;
	/**
	 * Number 1 活动类型
	 */
	private String benefitType;
	/**
	 * Number 30 优惠金额
	 */
	private String benefitValue;
	/**
	 * String 深航30元优惠券活动 卡券信息描述
	 */
	private String couponDesc;
	/**
	 * String 深航30元优惠券活动
	 */
	private String name;
	/**
	 * 优惠券
	 */
	private String coupon;

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getActDesc() {
		return actDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public String getBenefitValue() {
		return benefitValue;
	}

	public void setBenefitValue(String benefitValue) {
		this.benefitValue = benefitValue;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

}
