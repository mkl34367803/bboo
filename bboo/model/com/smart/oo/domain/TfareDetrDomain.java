package com.smart.oo.domain;

import com.bbang.api.BbangObject;

public class TfareDetrDomain extends BbangObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4054809650184660069L;
	private TfareAccVO account;
	private String instructionValue;// 指令值(必填项(票号),格式:9992401111111)
	private String instructionType;// 指令类型(必填项(固定类型值：TN、NM、NI、CN、PP),格式:TN)

	public TfareAccVO getAccount() {
		return account;
	}

	public void setAccount(TfareAccVO account) {
		this.account = account;
	}

	public String getInstructionValue() {
		return instructionValue;
	}

	public void setInstructionValue(String instructionValue) {
		this.instructionValue = instructionValue;
	}

	public String getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}

}
