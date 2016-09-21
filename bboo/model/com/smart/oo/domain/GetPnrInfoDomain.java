package com.smart.oo.domain;

public class GetPnrInfoDomain {

	private String url;
	private String appKey;
	private String userId;
	private String requestIP;
	private String termId;
	private String office;

	private String pnrContent;
	private String pnr;
	/**
	 * 0 不PAT 1 PAT
	 */
	private Integer pat;
	/**
	 * N 国内 I国际
	 */
	private String pnrType;

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

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPnrContent() {
		return pnrContent;
	}

	public void setPnrContent(String pnrContent) {
		this.pnrContent = pnrContent;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public Integer getPat() {
		return pat;
	}

	public void setPat(Integer pat) {
		this.pat = pat;
	}

	public String getPnrType() {
		return pnrType;
	}

	public void setPnrType(String pnrType) {
		this.pnrType = pnrType;
	}

}
