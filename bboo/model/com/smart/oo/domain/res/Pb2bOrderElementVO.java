package com.smart.oo.domain.res;

import java.util.List;

public class Pb2bOrderElementVO {

	/**
	 * 采购订单号
	 */
	private String orderNo;

	/**
	 * 销售订单号
	 */
	private String saleNo;

	/**
	 * 政策ID
	 */
	private String pid;

	/**
	 * 政策 出票OFFICE号
	 */
	private String offNo;

	/**
	 * 导入PNR
	 */
	private String pnr;

	/**
	 * 换的新PNR
	 */
	private String newPnr;

	/**
	 * 大编码
	 */
	private String bigPnr;

	/**
	 * 运价信息
	 */
	private List<AirPriceVO> priceList;
	/**
	 * 航程信息
	 */
	private List<AirTripVO> tripList;

	/**
	 * 乘机人信息
	 */
	private List<AirPsgVO> psgList;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOffNo() {
		return offNo;
	}

	public void setOffNo(String offNo) {
		this.offNo = offNo;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getNewPnr() {
		return newPnr;
	}

	public void setNewPnr(String newPnr) {
		this.newPnr = newPnr;
	}

	public List<AirPriceVO> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<AirPriceVO> priceList) {
		this.priceList = priceList;
	}

	public List<AirTripVO> getTripList() {
		return tripList;
	}

	public void setTripList(List<AirTripVO> tripList) {
		this.tripList = tripList;
	}

	public List<AirPsgVO> getPsgList() {
		return psgList;
	}

	public void setPsgList(List<AirPsgVO> psgList) {
		this.psgList = psgList;
	}

	public String getBigPnr() {
		return bigPnr;
	}

	public void setBigPnr(String bigPnr) {
		this.bigPnr = bigPnr;
	}

}
