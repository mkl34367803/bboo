package com.smart.oo.domain;

public class GetFlightByLineDomain {

	private String url;
	private String appKey;
	private String userId;
	private String requestIP;
	private String termId;
	private String office;
	/**
	 * 起飞
	 */
	private String depCode;
	/**
	 * 到达
	 */
	private String arrCode;
	/**
	 * 航班号
	 */
	private String flightNum;
	/**
	 * 航司
	 */
	private String carrier;
	/**
	 * 起飞日期
	 */
	private String depDate;
	/**
	 * 1查询全部舱位信息 0只查询有座位的
	 */
	private Integer dispSellOut = 1;
	/**
	 * 0不显示共享航班 1显示共享航班信息
	 */
	private Integer dispShare = 1;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 截至时间
	 */
	private String endTime;

	/**
	 * 接口类型
	 */
	private String apiType;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestIP() {
		return requestIP;
	}

	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
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

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public Integer getDispSellOut() {
		return dispSellOut;
	}

	public void setDispSellOut(Integer dispSellOut) {
		this.dispSellOut = dispSellOut;
	}

	public Integer getDispShare() {
		return dispShare;
	}

	public void setDispShare(Integer dispShare) {
		this.dispShare = dispShare;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

}
