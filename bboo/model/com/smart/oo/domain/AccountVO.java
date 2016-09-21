package com.smart.oo.domain;

public class AccountVO {

	private Integer accid;
	private String userName;
	/**
	 * 大客户优惠码
	 */
	private String bigcid;
	private String secret;// 密码
	private String appkey;
	private String codes;
	private String sessions;
	private String url;
	private String ignoredShopNames;// 淘宝 排除店铺
	/**
	 * 517NA QUA QUNAR CTRIP 19E 51BOOK BAITOUR 8000YI IBEAV_D AIRGW QF_QUA
	 * QF_QUA_FNO QF_CTRIP QF_IBEAV QF_AIRGW QF_AIRB2B
	 */
	private String acctype;

	/**
	 * 支付账号
	 */
	private String accpay;

	/**
	 * 支付密码
	 */
	private String paypwd; // DEC 加密

	/**
	 * 支付类型 ALIPAY TENPAY EPAY WALLET
	 */
	private String paytype;

	public Integer getAccid() {
		return accid;
	}

	public void setAccid(Integer accid) {
		this.accid = accid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIgnoredShopNames() {
		return ignoredShopNames;
	}

	public void setIgnoredShopNames(String ignoredShopNames) {
		this.ignoredShopNames = ignoredShopNames;
	}

	public String getAcctype() {
		return acctype;
	}

	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}

	public String getAccpay() {
		return accpay;
	}

	public void setAccpay(String accpay) {
		this.accpay = accpay;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getBigcid() {
		return bigcid;
	}

	public void setBigcid(String bigcid) {
		this.bigcid = bigcid;
	}

	public String getPaypwd() {
		return paypwd;
	}

	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}

}
