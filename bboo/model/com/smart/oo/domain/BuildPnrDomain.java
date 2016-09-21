package com.smart.oo.domain;

import java.util.List;

public class BuildPnrDomain {

	private String url;
	private String appKey;
	private String userId;
	private String requestIP;
	private String termId;
	private String office;

	private List<PnrFlightVO> flt;
	private List<PnrPassengerVO> psg;
	private String tktlMinutes = "180";
	private String tktlTime;
	private String linkman;// 联系人
	private String linktel;// 联系电话
	private String linkphone;// 联系电话
	private String remark;
	private String apply; // LL SA HK
	private String isiorn;// I国际 N国内

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

	public List<PnrFlightVO> getFlt() {
		return flt;
	}

	public void setFlt(List<PnrFlightVO> flt) {
		this.flt = flt;
	}

	public List<PnrPassengerVO> getPsg() {
		return psg;
	}

	public void setPsg(List<PnrPassengerVO> psg) {
		this.psg = psg;
	}

	public String getTktlMinutes() {
		return tktlMinutes;
	}

	public void setTktlMinutes(String tktlMinutes) {
		this.tktlMinutes = tktlMinutes;
	}

	public String getTktlTime() {
		return tktlTime;
	}

	public void setTktlTime(String tktlTime) {
		this.tktlTime = tktlTime;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinktel() {
		return linktel;
	}

	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApply() {
		return apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getIsiorn() {
		return isiorn;
	}

	public void setIsiorn(String isiorn) {
		this.isiorn = isiorn;
	}

	public String getLinkphone() {
		return linkphone;
	}

	public void setLinkphone(String linkphone) {
		this.linkphone = linkphone;
	}

}
