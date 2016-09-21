package com.smart.oo.domain;

public class IeBookPnrVO {

	private String id;
	/**
	 * 对应订单表 里面的PnrTypeEn 字段
	 */
	private String pnrType;
	private String pnrNo;
	/**
	 * PNR 记录的出票状 态 1:成功 3:失败（拒单填3） 设置失败将不更新订单
	 */
	private Integer PNRResultStatus;

	/**
	 * 订位OfficeNo
	 */
	private String officeNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPnrType() {
		return pnrType;
	}

	public void setPnrType(String pnrType) {
		this.pnrType = pnrType;
	}

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public Integer getPNRResultStatus() {
		return PNRResultStatus;
	}

	public void setPNRResultStatus(Integer pNRResultStatus) {
		PNRResultStatus = pNRResultStatus;
	}

	public String getOfficeNo() {
		return officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
}
