package com.smart.oo.domain.res;

public class DistributionModifyOrderInfoResult {

	/**
	 * true 回填票号 修改订单状态成功 false 失败
	 */
	private boolean isuss;

	private String code;

	private String msg;

	public boolean isIsuss() {
		return isuss;
	}

	public void setIsuss(boolean isuss) {
		this.isuss = isuss;
	}

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

}
