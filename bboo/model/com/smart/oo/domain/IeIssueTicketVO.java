package com.smart.oo.domain;

import java.util.List;

public class IeIssueTicketVO {

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 子订单号
	 */
	private String billId;

	private List<IeBookPnrVO> pnrInfo;

	private List<IePassengerTicketVO> passengerInfo;

	private List<IeBookFlightVO> fltInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public List<IeBookPnrVO> getPnrInfo() {
		return pnrInfo;
	}

	public void setPnrInfo(List<IeBookPnrVO> pnrInfo) {
		this.pnrInfo = pnrInfo;
	}

	public List<IePassengerTicketVO> getPassengerInfo() {
		return passengerInfo;
	}

	public void setPassengerInfo(List<IePassengerTicketVO> passengerInfo) {
		this.passengerInfo = passengerInfo;
	}

	public List<IeBookFlightVO> getFltInfo() {
		return fltInfo;
	}

	public void setFltInfo(List<IeBookFlightVO> fltInfo) {
		this.fltInfo = fltInfo;
	}

}
