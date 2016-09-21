package com.smart.oo.domain.res;

import java.util.List;

public class GjOrderGetDetailResult {

	private String code;
	private String msg;
	private List<GjSaleOrderVO> orders;

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

	public List<GjSaleOrderVO> getOrders() {
		return orders;
	}

	public void setOrders(List<GjSaleOrderVO> orders) {
		this.orders = orders;
	}

}
