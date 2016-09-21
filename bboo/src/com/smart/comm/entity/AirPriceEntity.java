package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 航司公布运价运价表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_air_price")
public class AirPriceEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5875502621796354914L;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 80)
	private String id;
	@Column(name = "a", length = 50)
	private String a;
	@Column(name = "n", length = 50)
	private String n;
	@Column(name = "s", length = 50)
	private String s;

	/**
	 * -----------------------------517NA--------------------------------------
	 * ------
	 */
	@Column(name = "keyID", length = 45)
	private String keyID;
	@Column(name = "renderID", length = 45)
	private String renderID;
	@Column(name = "travleType")
	private Integer travleType;
	@Column(name = "weekLimit")
	private Integer weekLimit;
	@Column(name = "stopTime", length = 35)
	private String stopTime;
	@Column(name = "priceType")
	private Integer priceType;
	/**
	 * -------------------------------------------------------------------------
	 */
	@Column(name = "keys", length = 35)
	private String keys; // ZHHRBSYX20154558555555

	@Column(name = "carrier", length = 5)
	private String carrier;
	@Column(name = "depCode", length = 5)
	private String depCode;// 起始
	@Column(name = "arrCode", length = 5)
	private String arrCode;// 到达
	@Column(name = "baseCabin", length = 5)
	private String baseCabin;// 舱位
	@Column(name = "beginDate", length = 25)
	private String beginDate; // 有效期开始
	@Column(name = "endDate", length = 25)
	private String endDate; // 有效期开始
	@Column(name = "cabin", length = 5)
	private String cabin;// 舱位
	@Column(name = "price")
	private Double price;// 票面价
	@Column(name = "basePrice")
	private Double basePrice;// 票面价
	@Column(name = "discount")
	private Double discount;
	@Column(name = "predaysStart")
	private Integer predaysStart;// 提前开始天数天数
	@Column(name = "predaysEnd")
	private Integer predaysEnd;// 提前结束天数
	/**
	 * 1删除 0有效
	 */
	@Column(name = "isDelete")
	private Integer isDelete;

	@Column(name = "createTime", length = 25)
	private String createTime;// 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getBaseCabin() {
		return baseCabin;
	}

	public void setBaseCabin(String baseCabin) {
		this.baseCabin = baseCabin;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getPredaysStart() {
		return predaysStart;
	}

	public void setPredaysStart(Integer predaysStart) {
		this.predaysStart = predaysStart;
	}

	public Integer getPredaysEnd() {
		return predaysEnd;
	}

	public void setPredaysEnd(Integer predaysEnd) {
		this.predaysEnd = predaysEnd;
	}

	public String getKeyID() {
		return keyID;
	}

	public void setKeyID(String keyID) {
		this.keyID = keyID;
	}

	public String getRenderID() {
		return renderID;
	}

	public void setRenderID(String renderID) {
		this.renderID = renderID;
	}

	public Integer getTravleType() {
		return travleType;
	}

	public void setTravleType(Integer travleType) {
		this.travleType = travleType;
	}

	public Integer getWeekLimit() {
		return weekLimit;
	}

	public void setWeekLimit(Integer weekLimit) {
		this.weekLimit = weekLimit;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
