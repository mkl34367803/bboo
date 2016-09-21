package com.smart.oo.domain;

public class IeBookFlightVO {

	private String sequence;
	private String flightNo;
	/**
	 * 舱位
	 */
	private String subClass;
	/**
	 * 出发机场
	 */
	private String DPort;
	private String APort;
	private String depTime;
	private String carrier;

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public String getDPort() {
		return DPort;
	}

	public void setDPort(String dPort) {
		DPort = dPort;
	}

	public String getAPort() {
		return APort;
	}

	public void setAPort(String aPort) {
		APort = aPort;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

}
