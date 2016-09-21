package com.smart.oo.domain;

public class FlightDynamicsDomain {

	private String depCode;
	private String arrCode;
	private String depDate;
	private String flightNum;
	private String source;
	/**
	 * 1获取缓存数据 2实时查询
	 */
	private Integer isGetCache;

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

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getIsGetCache() {
		return isGetCache;
	}

	public void setIsGetCache(Integer isGetCache) {
		this.isGetCache = isGetCache;
	}

}
