package com.smart.oo.domain.res;

public class BuildPnrResult {

	/**
	 * 0成功
	 */
	private Integer code;
	private String msg;
	private String txt;
	private String pnr;
	/**
	 * 航段状态 HK1 DK1
	 */
	private String state;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
