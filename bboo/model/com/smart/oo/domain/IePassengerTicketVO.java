package com.smart.oo.domain;

import java.util.List;

public class IePassengerTicketVO {

	private String id;
	private String passengerName;
	private String certNo;
	private List<String> tnos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public List<String> getTnos() {
		return tnos;
	}

	public void setTnos(List<String> tnos) {
		this.tnos = tnos;
	}

}
