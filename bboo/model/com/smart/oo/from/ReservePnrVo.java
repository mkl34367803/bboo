package com.smart.oo.from;

import java.io.Serializable;
import java.util.List;

public class ReservePnrVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String office;
	private List<FlightVo> flights;
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public List<FlightVo> getFlights() {
		return flights;
	}
	public void setFlights(List<FlightVo> flights) {
		this.flights = flights;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
