package com.smart.oo.vo;

import java.util.List;

public class DetrInfoResVO {

	/**
	 * 0失败 1成功
	 */
	private String code;
	private String msg;
	private String data;
	private String psgname;
	private String no;
	private String certno;
	private String office;
	private String offcode;
	private String company;
	private Double face;
	private Double tax;
	private Double yq;
	private Double total;
	/**
	 * 
	 */
	private String plan;
	private List<DetrFltVO> flt;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<DetrFltVO> getFlt() {
		return flt;
	}

	public void setFlt(List<DetrFltVO> flt) {
		this.flt = flt;
	}

	public String getPsgname() {
		return psgname;
	}

	public void setPsgname(String psgname) {
		this.psgname = psgname;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getCertno() {
		return certno;
	}

	public void setCertno(String certno) {
		this.certno = certno;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}
