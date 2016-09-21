package com.smart.oo.domain.res;

import java.util.List;

public class DetrByTnoResult {

	private Integer code;
	private String msg;
	private String txt;
	private List<DetrDetail> detrs;

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

	public List<DetrDetail> getDetrs() {
		return detrs;
	}

	public void setDetrs(List<DetrDetail> detrs) {
		this.detrs = detrs;
	}
}
