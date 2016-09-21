package com.smart.oo.domain.res;

import java.util.List;

public class ProductRootResult {

	private String executeMsg;
	/**
	 * SUCCESS FAIL
	 */
	private String executeStatus;
	/**
	 * 请求ID
	 */
	private String requestId;
	/**
	 * S0 成功 F1失败
	 */
	private String executeCode;

	private List<ProductElementVO> rlts;

	public String getExecuteMsg() {
		return executeMsg;
	}

	public void setExecuteMsg(String executeMsg) {
		this.executeMsg = executeMsg;
	}

	public String getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(String executeStatus) {
		this.executeStatus = executeStatus;
	}

	public String getExecuteCode() {
		return executeCode;
	}

	public void setExecuteCode(String executeCode) {
		this.executeCode = executeCode;
	}

	public List<ProductElementVO> getRlts() {
		return rlts;
	}

	public void setRlts(List<ProductElementVO> rlts) {
		this.rlts = rlts;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}
