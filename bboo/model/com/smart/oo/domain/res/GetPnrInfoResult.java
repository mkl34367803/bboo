package com.smart.oo.domain.res;

import java.util.List;

public class GetPnrInfoResult {

	/**
	 * 0成功
	 */
	private Integer code;
	private String msg;
	private String txt;
	private String pnr;
	private String bigPnr;

	private List<RtPnrPriceVO> prices;
	private List<RtPnrLineVO> lines;
	private List<RtPnrPassengerVO> passengers;

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

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getBigPnr() {
		return bigPnr;
	}

	public void setBigPnr(String bigPnr) {
		this.bigPnr = bigPnr;
	}

	public List<RtPnrPriceVO> getPrices() {
		return prices;
	}

	public void setPrices(List<RtPnrPriceVO> prices) {
		this.prices = prices;
	}

	public List<RtPnrLineVO> getLines() {
		return lines;
	}

	public void setLines(List<RtPnrLineVO> lines) {
		this.lines = lines;
	}

	public List<RtPnrPassengerVO> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<RtPnrPassengerVO> passengers) {
		this.passengers = passengers;
	}

}
