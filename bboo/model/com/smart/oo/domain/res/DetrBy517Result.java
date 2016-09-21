package com.smart.oo.domain.res;

import java.util.List;

public class DetrBy517Result {

	private String msg;
	private String code;
	private String data;
	private List<DetrDetail> detrs;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<DetrDetail> getDetrs() {
		return detrs;
	}

	public void setDetrs(List<DetrDetail> detrs) {
		this.detrs = detrs;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
