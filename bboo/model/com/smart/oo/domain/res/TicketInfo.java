package com.smart.oo.domain.res;

import java.util.List;

public class TicketInfo {

	private String msg;
	private String code;
	private String name;
	private String tno;
	private Double face;
	private Double tax;
	private Double yq;
	private Double total;
	private String office;
	private String offcode;
	private String company;
	private String tgq;
	private List<Lines> line;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public List<Lines> getLine() {
		return line;
	}

	public void setLine(List<Lines> line) {
		this.line = line;
	}

	public Double getFace() {
		return face;
	}

	public void setFace(Double face) {
		this.face = face;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getYq() {
		return yq;
	}

	public void setYq(Double yq) {
		this.yq = yq;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTgq() {
		return tgq;
	}

	public void setTgq(String tgq) {
		this.tgq = tgq;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}
}
