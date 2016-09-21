package com.smart.oo.domain.res;

import com.bbang.api.BbangObject;

/**
 * 旅客信息表
 * 
 * @author eric
 * 
 */

public class GjSalePassengerVO extends BbangObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699423460472456845L;

	/**
	 * 分销平台对应的乘客ID 可以为空
	 */
	private String extId;

	/**
	 * 序号
	 */
	private Integer pindex;

	/**
	 * 乘客姓名
	 */
	private String name;

	/**
	 * 性别 M 男 F 女
	 */
	private String gender;

	/**
	 * 乘客类别 -1 留学生 0 成人 1 儿童 2婴儿 -2其他
	 */
	private Integer ageType;

	/**
	 * 乘机人类型描述 儿童 青年 老年 中年
	 */
	private String ageDes;

	/**
	 * 生日
	 */
	private String birthday;

	/**
	 * 国籍
	 */
	private String nationality;

	/**
	 * 证件类型 PP 护照 HX 回乡证 TB 台胞证 GA 港澳通行证 HY 国际海员证 NI身份证 XS 学生证 JR 军人证 JS 驾驶证 TW
	 * 台湾通行证 SB 士兵证 LN 临时身份证 HK 户口簿 JG 警官证 TH 其它 CS 出生证明
	 */
	private String cardType;

	/**
	 * 证件号码
	 */
	private String cardNum;

	/**
	 * 证件有效期
	 */
	private String cardExpired;

	/**
	 * 证件签发国家
	 */
	private String cardIssuePlace;

	/**
	 * 保险数
	 */
	private Integer bxCount;

	/**
	 * 保险单号
	 */
	private String insNo;

	/**
	 * 电子票号
	 */
	private String eticketNum;

	/**
	 * 票号校验状态
	 */
	private String ticketStatus;

	/**
	 * 票号校验状态信息
	 */
	private String ticketStatusInfo;

	/**
	 * 最后更新时间
	 */
	private String lastUpdated;
	/**
	 * 销售底价 结算价
	 */
	private Double cost;
	/**
	 * 燃油
	 */
	private Double oilFee;
	/**
	 * 基建
	 */
	private Double taxFee;
	/**
	 * 政策投放的票面价
	 */
	private Double price;

	/**
	 * 佣金
	 */
	private Double commission;
	
	private String addTime;
	
	/**
	 * 小编码
	 */
	private String pnr;

	/**
	 * 大编码
	 */
	private String bpnr;

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public Integer getPindex() {
		return pindex;
	}

	public void setPindex(Integer pindex) {
		this.pindex = pindex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAgeType() {
		return ageType;
	}

	public void setAgeType(Integer ageType) {
		this.ageType = ageType;
	}

	public String getAgeDes() {
		return ageDes;
	}

	public void setAgeDes(String ageDes) {
		this.ageDes = ageDes;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardExpired() {
		return cardExpired;
	}

	public void setCardExpired(String cardExpired) {
		this.cardExpired = cardExpired;
	}

	public String getCardIssuePlace() {
		return cardIssuePlace;
	}

	public void setCardIssuePlace(String cardIssuePlace) {
		this.cardIssuePlace = cardIssuePlace;
	}

	public Integer getBxCount() {
		return bxCount;
	}

	public void setBxCount(Integer bxCount) {
		this.bxCount = bxCount;
	}

	public String getInsNo() {
		return insNo;
	}

	public void setInsNo(String insNo) {
		this.insNo = insNo;
	}

	public String getEticketNum() {
		return eticketNum;
	}

	public void setEticketNum(String eticketNum) {
		this.eticketNum = eticketNum;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getTicketStatusInfo() {
		return ticketStatusInfo;
	}

	public void setTicketStatusInfo(String ticketStatusInfo) {
		this.ticketStatusInfo = ticketStatusInfo;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getOilFee() {
		return oilFee;
	}

	public void setOilFee(Double oilFee) {
		this.oilFee = oilFee;
	}

	public Double getTaxFee() {
		return taxFee;
	}

	public void setTaxFee(Double taxFee) {
		this.taxFee = taxFee;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getBpnr() {
		return bpnr;
	}

	public void setBpnr(String bpnr) {
		this.bpnr = bpnr;
	}

}
