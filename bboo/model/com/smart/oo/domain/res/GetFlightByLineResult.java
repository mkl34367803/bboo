package com.smart.oo.domain.res;

import java.util.List;

public class GetFlightByLineResult {
	/**
	 * 0成功
	 */
	private Integer code;
	private String msg;
	private String txt;
	private String depCode;
	private String arrCode;
	private String depDate;
	private String mileage;
	private Integer count;
	private List<FlightByLineVO> lines;

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

	public List<FlightByLineVO> getLines() {
		return lines;
	}

	public void setLines(List<FlightByLineVO> lines) {
		this.lines = lines;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
