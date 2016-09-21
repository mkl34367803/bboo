package com.smart.oo.domain;

import java.util.List;

import com.smart.oo.domain.res.ProductElementVO;

public class CreateOrderForPb2bDomain {

	private String requestId;
	/**
	 * 销售订单号
	 */
	private String saleNo;
	private String pnrContent;
	private String patContent;
	private Integer tripType;// (0:单程/1:往返/2:单程及往返 3多程)
	private String pnr;
	private String bigPnr;// 大编码
	private String linkman;
	private String tel;
	private String email;
	private String proxyip;
	private String proxyport;
	private Integer psgCount;// 乘机人数目
	private List<AccountVO> accounts;
	private ProductElementVO product;

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

	public List<AccountVO> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountVO> accounts) {
		this.accounts = accounts;
	}

	public ProductElementVO getProduct() {
		return product;
	}

	public void setProduct(ProductElementVO product) {
		this.product = product;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSaleNo() {
		return saleNo;
	}

	public void setSaleNo(String saleNo) {
		this.saleNo = saleNo;
	}

}
