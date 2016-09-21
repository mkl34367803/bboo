package com.smart.oo.domain.res;

public class DynamicsVO {

	private String flightNo;
	private String DCityName;
	private String ACityName;
	private String DAirportCode;
	private String AAirportCode;
	private String DTerminal;
	private String ATerminal;
	private String DDate;
	private String planDDateTime;
	private String planADateTime;
	private String actDDateTime;
	private String actADateTime;
	/**
	 * 准点率
	 */
	private String onTimeRate;
	/**
	 * 1计划 2延误 3取消 4起飞 5到达 6其它
	 */
	private Integer status;
	/**
	 * 1计划 2延误 3取消 4起飞 5到达 6其它
	 */
	private String statusCh;

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDCityName() {
		return DCityName;
	}

	public void setDCityName(String dCityName) {
		DCityName = dCityName;
	}

	public String getACityName() {
		return ACityName;
	}

	public void setACityName(String aCityName) {
		ACityName = aCityName;
	}

	public String getDAirportCode() {
		return DAirportCode;
	}

	public void setDAirportCode(String dAirportCode) {
		DAirportCode = dAirportCode;
	}

	public String getAAirportCode() {
		return AAirportCode;
	}

	public void setAAirportCode(String aAirportCode) {
		AAirportCode = aAirportCode;
	}

	public String getDTerminal() {
		return DTerminal;
	}

	public void setDTerminal(String dTerminal) {
		DTerminal = dTerminal;
	}

	public String getATerminal() {
		return ATerminal;
	}

	public void setATerminal(String aTerminal) {
		ATerminal = aTerminal;
	}

	public String getDDate() {
		return DDate;
	}

	public void setDDate(String dDate) {
		DDate = dDate;
	}

	public String getPlanDDateTime() {
		return planDDateTime;
	}

	public void setPlanDDateTime(String planDDateTime) {
		this.planDDateTime = planDDateTime;
	}

	public String getPlanADateTime() {
		return planADateTime;
	}

	public void setPlanADateTime(String planADateTime) {
		this.planADateTime = planADateTime;
	}

	public String getActDDateTime() {
		return actDDateTime;
	}

	public void setActDDateTime(String actDDateTime) {
		this.actDDateTime = actDDateTime;
	}

	public String getActADateTime() {
		return actADateTime;
	}

	public void setActADateTime(String actADateTime) {
		this.actADateTime = actADateTime;
	}

	public String getOnTimeRate() {
		return onTimeRate;
	}

	public void setOnTimeRate(String onTimeRate) {
		this.onTimeRate = onTimeRate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusCh() {
		return statusCh;
	}

	public void setStatusCh(String statusCh) {
		this.statusCh = statusCh;
	}

}
