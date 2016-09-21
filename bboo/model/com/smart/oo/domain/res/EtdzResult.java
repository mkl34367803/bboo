package com.smart.oo.domain.res;

import java.util.List;

public class EtdzResult {

	/**
	 * 0成功 其他状态失败
	 */
	private Integer code;
	private String pnr;
	private String msg;
	private String txt;
	private Double price;
	private Double fare;
	private Double tax;
	private Double yq;
	private List<EtdzPassengerVO> psgs;
	private String pnrInfo;
	
	/**
	 * 打票机台数
	 */
	private Integer inkpad;
	private String office;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
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

	public List<EtdzPassengerVO> getPsgs() {
		return psgs;
	}

	public void setPsgs(List<EtdzPassengerVO> psgs) {
		this.psgs = psgs;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getYq() {
		return yq;
	}

	public void setYq(Double yq) {
		this.yq = yq;
	}

	public String getPnrInfo() {
		return pnrInfo;
	}

	public void setPnrInfo(String pnrInfo) {
		this.pnrInfo = pnrInfo;
	}

	public Integer getInkpad() {
		return inkpad;
	}

	public void setInkpad(Integer inkpad) {
		this.inkpad = inkpad;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

}
