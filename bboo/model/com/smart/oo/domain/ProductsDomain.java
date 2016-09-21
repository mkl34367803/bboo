package com.smart.oo.domain;

import java.util.List;

public class ProductsDomain {

	private String requestId;
	private String pnrContent;
	private String patContent;
	private Integer tripType;// (0:单程/1:往返/2:单程及往返 3多程)
	private String pnr;
	private String bigPnr;// 大编码
	private String linkman;
	private String tel;
	private String proxyip;
	private String proxyport;
	private Integer psgCount;// 乘机人数目
	private List<FlightLegsVO> flights;
	private List<FreightPriceVO> price;
	private List<TakePeopleVO> peoples;
	private List<AccountVO> accounts;

	/**
	 * CTRIP_DIRECT 携程直连
	 */
	private String dataFormat;
	
	/**
	 * CTRIP_HS 携程航司
	 */
	private String productType;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getPnrContent() {
		return pnrContent;
	}

	public void setPnrContent(String pnrContent) {
		this.pnrContent = pnrContent;
	}

	public String getPatContent() {
		return patContent;
	}

	public void setPatContent(String patContent) {
		this.patContent = patContent;
	}

	public Integer getTripType() {
		return tripType;
	}

	public void setTripType(Integer tripType) {
		this.tripType = tripType;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getBigPnr() {
		return bigPnr;
	}

	public void setBigPnr(String bigPnr) {
		this.bigPnr = bigPnr;
	}

	public List<FlightLegsVO> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightLegsVO> flights) {
		this.flights = flights;
	}

	public List<FreightPriceVO> getPrice() {
		return price;
	}

	public void setPrice(List<FreightPriceVO> price) {
		this.price = price;
	}

	public List<TakePeopleVO> getPeoples() {
		return peoples;
	}

	public void setPeoples(List<TakePeopleVO> peoples) {
		this.peoples = peoples;
	}

	public List<AccountVO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountVO> accounts) {
		this.accounts = accounts;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProxyip() {
		return proxyip;
	}

	public void setProxyip(String proxyip) {
		this.proxyip = proxyip;
	}

	public String getProxyport() {
		return proxyport;
	}

	public void setProxyport(String proxyport) {
		this.proxyport = proxyport;
	}

	public Integer getPsgCount() {
		return psgCount;
	}

	public void setPsgCount(Integer psgCount) {
		this.psgCount = psgCount;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
