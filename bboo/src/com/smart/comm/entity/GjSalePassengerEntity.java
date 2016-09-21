package com.smart.comm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 旅客信息表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_gj_salepassenger")
@org.hibernate.annotations.Entity(dynamicUpdate = true, dynamicInsert = true)
public class GjSalePassengerEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4699423460472456845L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = GjSaleOrderEntity.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fkid")
	private GjSaleOrderEntity order;

	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 订单表主键
	 */
	@Column(name = "fkid", length = 55, insertable = false, updatable = false)
	private String fkid;

	/**
	 * 分销平台对应的乘客ID 可以为空
	 */
	@Column(name = "extId", length = 55)
	private String extId;

	/**
	 * 序号
	 */
	@Column(name = "pindex")
	private Integer pindex;

	/**
	 * 乘客姓名
	 */
	@Column(name = "name", length = 100)
	private String name;

	/**
	 * 性别 M 男 F 女
	 */
	@Column(name = "gender", length = 3)
	private String gender;

	/**
	 * 乘客类别 -1 留学生 0 成人 1 儿童 2婴儿 -2其他
	 */
	@Column(name = "ageType")
	private Integer ageType;

	/**
	 * 乘机人类型描述 儿童 青年 老年 中年
	 */
	@Column(name = "ageDes", length = 6)
	private String ageDes;

	/**
	 * 生日
	 */
	@Column(name = "birthday", length = 15)
	private String birthday;

	/**
	 * 0 不是当月生日 1当月生日
	 */
	@Column(name = "isbirday")
	private Integer isbirday;

	/**
	 * 户籍所在地
	 */
	@Column(name = "household", length = 30)
	private String household;

	/**
	 * 国籍
	 */
	@Column(name = "nationality", length = 30)
	private String nationality;

	/**
	 * 证件类型 PP 护照 HX 回乡证 TB 台胞证 GA 港澳通行证 HY 国际海员证 NI身份证 XS 学生证 JR 军人证 JS 驾驶证 TW
	 * 台湾通行证 SB 士兵证 LN 临时身份证 HK 户口簿 JG 警官证 TH 其它 CS 出生证明
	 */
	@Column(name = "cardType", length = 15)
	private String cardType;

	/**
	 * 证件号码
	 */
	@Column(name = "cardNum", length = 20)
	private String cardNum;

	/**
	 * 证件有效期
	 */
	@Column(name = "cardExpired", length = 30)
	private String cardExpired;

	/**
	 * 证件签发国家
	 */
	@Column(name = "cardIssuePlace", length = 30)
	private String cardIssuePlace;

	/**
	 * 保险数
	 */
	@Column(name = "bxCount")
	private Integer bxCount;

	/**
	 * 保险单号
	 */
	@Column(name = "insNo", length = 55)
	private String insNo;

	/**
	 * 电子票号
	 */
	@Column(name = "eticketNum", length = 18)
	private String eticketNum;

	/**
	 * 票号校验状态
	 */
	@Column(name = "ticketStatus", length = 15)
	private String ticketStatus;

	/**
	 * 票号校验状态信息
	 */
	@Column(name = "ticketStatusInfo", length = 35)
	private String ticketStatusInfo;

	/**
	 * 最后更新时间
	 */
	@Column(name = "lastUpdated", length = 30)
	private String lastUpdated;
	/**
	 * 销售底价
	 */
	@Column(name = "cost")
	private Double cost;
	/**
	 * 燃油
	 */
	@Column(name = "oilFee")
	private Double oilFee;
	/**
	 * 基建
	 */
	@Column(name = "taxFee")
	private Double taxFee;
	/**
	 * 政策投放的票面价
	 */
	@Column(name = "price")
	private Double price;

	/**
	 * 佣金
	 */
	@Column(name = "commission")
	private Double commission;
	/**
	 * 票面价 所有航段累加(这个字段没什么用，导入的时候不会传值过来)
	 */
	@Column(name = "printPrice")
	private Double printPrice;

	/**
	 * 创建时间
	 */
	@Column(name = "addTime", length = 30)
	private String addTime;

	/**
	 * 小编码
	 */
	@Column(name = "pnr", length = 15)
	private String pnr;

	/**
	 * 大编码
	 */
	@Column(name = "bpnr", length = 15)
	private String bpnr;

	/**
	 * 是否删除
	 */
	@Column(name = "isDelete", columnDefinition = "bit default 0 ", nullable = true)
	private Boolean isDelete;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkid() {
		return fkid;
	}

	public void setFkid(String fkid) {
		this.fkid = fkid;
	}

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

	public Double getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(Double printPrice) {
		this.printPrice = printPrice;
	}

	public GjSaleOrderEntity getOrder() {
		return order;
	}

	public void setOrder(GjSaleOrderEntity order) {
		this.order = order;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getIsbirday() {
		return isbirday;
	}

	public void setIsbirday(Integer isbirday) {
		this.isbirday = isbirday;
	}

	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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
