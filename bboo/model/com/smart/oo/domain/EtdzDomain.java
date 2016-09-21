package com.smart.oo.domain;

public class EtdzDomain {

	private String url;
	private String appKey;
	private String userId;
	private String requestIP;
	private String termId;
	private String pnr;
	private Double face;
	private String office;
	/**
	 * 打票机台数
	 */
	private Integer inkpad;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRequestIP() {
		return requestIP;
	}

	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public Double getFace() {
		return face;
	}

	public void setFace(Double face) {
		this.face = face;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Integer getInkpad() {
		return inkpad;
	}

	public void setInkpad(Integer inkpad) {
		this.inkpad = inkpad;
	}

}
