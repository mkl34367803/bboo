package com.smart.oo.domain.res;

import com.bbang.api.BbangObject;

public class GetCacheFdPriceResult extends BbangObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045955280833540887L;
	private String msg;
	private String price;
	private String yprice;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getYprice() {
		return yprice;
	}

	public void setYprice(String yprice) {
		this.yprice = yprice;
	}

}
