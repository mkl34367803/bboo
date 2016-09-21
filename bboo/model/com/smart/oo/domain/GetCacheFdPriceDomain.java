package com.smart.oo.domain;

import com.bbang.api.BbangObject;

public class GetCacheFdPriceDomain extends BbangObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5035868472965615808L;

	private String dep;
	private String arr;
	private String aircode;
	private String cabin;
	private String dt;

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public String getAircode() {
		return aircode;
	}

	public void setAircode(String aircode) {
		this.aircode = aircode;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

}
