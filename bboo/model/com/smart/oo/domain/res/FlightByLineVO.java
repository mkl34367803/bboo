package com.smart.oo.domain.res;

import java.util.List;

public class FlightByLineVO {

	private String carrier;
	private String depCode;
	private String arrCode;
	private String depDate;
	private String flightNum;
	private String startTime;
	private String endTime;
	private String flyTime;
	private String departureTn;
	private String destinationTn;
	private String hasMeal;
	private String planestyle;
	private String carrierExtra;
	private String flightNumExtra;
	private boolean isShare;
	private String isStop;
	private List<CabinVO> cabins;

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
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

	public String getFlyTime() {
		return flyTime;
	}

	public void setFlyTime(String flyTime) {
		this.flyTime = flyTime;
	}

	public String getDepartureTn() {
		return departureTn;
	}

	public void setDepartureTn(String departureTn) {
		this.departureTn = departureTn;
	}

	public String getDestinationTn() {
		return destinationTn;
	}

	public void setDestinationTn(String destinationTn) {
		this.destinationTn = destinationTn;
	}

	public String getHasMeal() {
		return hasMeal;
	}

	public void setHasMeal(String hasMeal) {
		this.hasMeal = hasMeal;
	}

	public String getPlanestyle() {
		return planestyle;
	}

	public void setPlanestyle(String planestyle) {
		this.planestyle = planestyle;
	}

	public String getCarrierExtra() {
		return carrierExtra;
	}

	public void setCarrierExtra(String carrierExtra) {
		this.carrierExtra = carrierExtra;
	}

	public String getFlightNumExtra() {
		return flightNumExtra;
	}

	public void setFlightNumExtra(String flightNumExtra) {
		this.flightNumExtra = flightNumExtra;
	}

	public List<CabinVO> getCabins() {
		return cabins;
	}

	public void setCabins(List<CabinVO> cabins) {
		this.cabins = cabins;
	}

	public boolean isShare() {
		return isShare;
	}

	public void setShare(boolean isShare) {
		this.isShare = isShare;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

}
