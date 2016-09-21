package com.smart.oo.domain.res;

import java.util.List;

public class DetnSegmentVO {

	private String airLine;
	private String flightNo;
	private String depdate;
	private String classx;
	private String seatStatus;
	private String segStatus;
	private String stops;
	private List<DetnLegVO> leg;

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getClassx() {
		return classx;
	}

	public void setClassx(String classx) {
		this.classx = classx;
	}

	public String getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(String seatStatus) {
		this.seatStatus = seatStatus;
	}

	public String getSegStatus() {
		return segStatus;
	}

	public void setSegStatus(String segStatus) {
		this.segStatus = segStatus;
	}

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}

	public List<DetnLegVO> getLeg() {
		return leg;
	}

	public void setLeg(List<DetnLegVO> leg) {
		this.leg = leg;
	}

	public String getDepdate() {
		return depdate;
	}

	public void setDepdate(String depdate) {
		this.depdate = depdate;
	}

}
