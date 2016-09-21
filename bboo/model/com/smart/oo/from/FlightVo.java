package com.smart.oo.from;

import java.io.Serializable;

import javax.persistence.Column;

public class FlightVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 起飞时间
	 */
	private String departureTime;
	/**
	 * 起飞城市三字码
	 */
	private String depAircode;
	/**
	 * 到达时间
	 */
	private String arrivalTime;
	/**
	 * 到达城市三字码
	 */
	private String arrAircode;
	/**
	 * 航班起飞日期
	 */
	private String departureDate;
	/**
	 * 舱位
	 */
	private String cabin;
	/**
	 * 航班号
	 */
	private String flightNum;
	/**
	 *	舱位数
	 */
	private String cabinNum;
	/**
	 * 是否为共享航班
	 */
	private String isShared;
	/**
	 * 低仓位 例如： QA
	 */
	private String lowspace;
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getFlightNum() {
		return flightNum;
	}
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getDepAircode() {
		return depAircode;
	}
	public void setDepAircode(String depAircode) {
		this.depAircode = depAircode;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getArrAircode() {
		return arrAircode;
	}
	public void setArrAircode(String arrAircode) {
		this.arrAircode = arrAircode;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsShared() {
		return isShared;
	}
	public void setIsShared(String isShared) {
		this.isShared = isShared;
	}
	public String getCabinNum() {
		return cabinNum;
	}
	public void setCabinNum(String cabinNum) {
		this.cabinNum = cabinNum;
	}
	public String getLowspace() {
		return lowspace;
	}
	public void setLowspace(String lowspace) {
		this.lowspace = lowspace;
	}
}
