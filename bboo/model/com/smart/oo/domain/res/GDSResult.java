package com.smart.oo.domain.res;

public class GDSResult {

	private String[] txt;
	/**
	 * 0成功
	 */
	private Integer code;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String[] getTxt() {
		return txt;
	}

	public void setTxt(String[] txt) {
		this.txt = txt;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
