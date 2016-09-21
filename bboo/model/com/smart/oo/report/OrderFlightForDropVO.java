package com.smart.oo.report;

import com.smart.excel.report.base.AColumn;
import com.smart.excel.report.base.AExcel;

@AExcel(sheetIndex = 0)
public class OrderFlightForDropVO {

	@AColumn(title = "订单号")
	private String orderNo;
	@AColumn(title = "编码")
	private String pnr;
	@AColumn(title = "乘客类型")
	private String psgType;
	@AColumn(title = "始发城市")
	private String depCode;
	@AColumn(title = "到达城市")
	private String arrCode;
	@AColumn(title = "起飞日期")
	private String depDate;
	@AColumn(title = "起飞时间")
	private String depTime;
	@AColumn(title = "航班号")
	private String fligthNum;
	@AColumn(title = "舱位")
	private String cabinCode;
	@AColumn(title = "出票日期")
	private String ticketDate;
	@AColumn(title = "备注")
	private String remark;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getFligthNum() {
		return fligthNum;
	}

	public void setFligthNum(String fligthNum) {
		this.fligthNum = fligthNum;
	}

	public String getCabinCode() {
		return cabinCode;
	}

	public void setCabinCode(String cabinCode) {
		this.cabinCode = cabinCode;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPsgType() {
		return psgType;
	}

	public void setPsgType(String psgType) {
		this.psgType = psgType;
	}

}
