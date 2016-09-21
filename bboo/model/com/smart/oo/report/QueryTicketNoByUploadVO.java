package com.smart.oo.report;

import com.smart.excel.report.base.AColumn;
import com.smart.excel.report.base.AExcel;

@AExcel(sheetIndex = 0)
public class QueryTicketNoByUploadVO {

	@AColumn(title = "票号")
	private String ticketNo;
	@AColumn(title = "出票日期")
	private String ticketDate;
	@AColumn(title = "起飞日期")
	private String departureDate;
	@AColumn(title = "乘客姓名")
	private String passengerName;
	@AColumn(title = "备注")
	private String remark;

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
