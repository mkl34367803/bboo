package com.smart.oo.domain.res;

import java.util.List;

public class DetnItineraryVO {

	private String pnrno;
	private String airLinePnrNo;
	private String systems;
	private List<DetnTicketInfoVO> tickets;
	private List<DetnPassengerVO> passenger;
	private List<DetnSegmentVO> segment;
	private DetnAgentInfoVO agentinfo;
	private DetnFCSVO fc;
	private List<DetnFareVO> fare;
	private List<DetnNoticeVO> notice;
	private List<DetnAdvertVO> advert;

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getAirLinePnrNo() {
		return airLinePnrNo;
	}

	public void setAirLinePnrNo(String airLinePnrNo) {
		this.airLinePnrNo = airLinePnrNo;
	}

	public String getSystems() {
		return systems;
	}

	public void setSystems(String systems) {
		this.systems = systems;
	}

	public List<DetnTicketInfoVO> getTickets() {
		return tickets;
	}

	public void setTickets(List<DetnTicketInfoVO> tickets) {
		this.tickets = tickets;
	}

	public List<DetnPassengerVO> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<DetnPassengerVO> passenger) {
		this.passenger = passenger;
	}

	public List<DetnSegmentVO> getSegment() {
		return segment;
	}

	public void setSegment(List<DetnSegmentVO> segment) {
		this.segment = segment;
	}

	public List<DetnFareVO> getFare() {
		return fare;
	}

	public void setFare(List<DetnFareVO> fare) {
		this.fare = fare;
	}

	public List<DetnNoticeVO> getNotice() {
		return notice;
	}

	public void setNotice(List<DetnNoticeVO> notice) {
		this.notice = notice;
	}

	public List<DetnAdvertVO> getAdvert() {
		return advert;
	}

	public void setAdvert(List<DetnAdvertVO> advert) {
		this.advert = advert;
	}

	public DetnAgentInfoVO getAgentinfo() {
		return agentinfo;
	}

	public void setAgentinfo(DetnAgentInfoVO agentinfo) {
		this.agentinfo = agentinfo;
	}

	public DetnFCSVO getFc() {
		return fc;
	}

	public void setFc(DetnFCSVO fc) {
		this.fc = fc;
	}

}
