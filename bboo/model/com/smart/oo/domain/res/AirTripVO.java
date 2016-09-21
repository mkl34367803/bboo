package com.smart.oo.domain.res;

public class AirTripVO {

	/**
	 * 航班号 多个航班用“/”分隔
	 */
	private String airNo;

	/**
	 * 航班号 多个航班用“/”分隔
	 */
	private String shareAirNo;
	/**
	 * 到达时间 2016-03-30 06:20:00
	 */
	private String airDstTime;
	/**
	 * 起飞时间 2016-03-30 06:20:00
	 */
	private String airOrgTime;
	/**
	 * 舱位 多个航班用“/”分隔
	 */
	private String bunkNo;
	private String dstCity;
	private String orgCity;
	private String dstCode;
	private String orgCode;

	private String orgCodeCh;
	private String dstCodeCh;
	/**
	 * 舱位等级 0-头等舱；1-商务舱；2-经济舱
	 */
	private String subclass;
	/**
	 * 退改签
	 */
	private String tgq;

	/**
	 * 舱位剩余座位数
	 */
	private String amount;

	private String depTerm;

	private String arrTerm;

	private String flightType;

	public String getAirNo() {
		return airNo;
	}

	public void setAirNo(String airNo) {
		this.airNo = airNo;
	}

	public String getAirDstTime() {
		return airDstTime;
	}

	public void setAirDstTime(String airDstTime) {
		this.airDstTime = airDstTime;
	}

	public String getDstCity() {
		return dstCity;
	}

	public void setDstCity(String dstCity) {
		this.dstCity = dstCity;
	}

	public String getAirOrgTime() {
		return airOrgTime;
	}

	public void setAirOrgTime(String airOrgTime) {
		this.airOrgTime = airOrgTime;
	}

	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	public String getBunkNo() {
		return bunkNo;
	}

	public void setBunkNo(String bunkNo) {
		this.bunkNo = bunkNo;
	}

	public String getTgq() {
		return tgq;
	}

	public void setTgq(String tgq) {
		this.tgq = tgq;
	}

	public String getDstCode() {
		return dstCode;
	}

	public void setDstCode(String dstCode) {
		this.dstCode = dstCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getShareAirNo() {
		return shareAirNo;
	}

	public void setShareAirNo(String shareAirNo) {
		this.shareAirNo = shareAirNo;
	}

	public String getDepTerm() {
		return depTerm;
	}

	public void setDepTerm(String depTerm) {
		this.depTerm = depTerm;
	}

	public String getArrTerm() {
		return arrTerm;
	}

	public void setArrTerm(String arrTerm) {
		this.arrTerm = arrTerm;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getOrgCodeCh() {
		return orgCodeCh;
	}

	public void setOrgCodeCh(String orgCodeCh) {
		this.orgCodeCh = orgCodeCh;
	}

	public String getDstCodeCh() {
		return dstCodeCh;
	}

	public void setDstCodeCh(String dstCodeCh) {
		this.dstCodeCh = dstCodeCh;
	}

}
