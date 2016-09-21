package com.smart.oo.from;

import java.io.Serializable;


public class BuyOrderProfit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 利润
	 */
	private Double lr;
	/**
	 * 采购地
	 */
	private String purchasePlace;
	public Double getLr() {
		return lr;
	}
	public void setLr(Double lr) {
		this.lr = lr;
	}
	public String getPurchasePlace() {
		return purchasePlace;
	}
	public void setPurchasePlace(String purchasePlace) {
		this.purchasePlace = purchasePlace;
	}

}
