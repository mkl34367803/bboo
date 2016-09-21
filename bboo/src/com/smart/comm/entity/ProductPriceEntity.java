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
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_product_price")
public class ProductPriceEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 84484137070833321L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductDataEntity.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fkpid")
	private ProductDataEntity product;

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 注册表主键
	 */
	@Column(name = "fkpid", length = 55, insertable = false, updatable = false)
	private String fkpid;
	/**
	 * 所有人合计 票面价
	 */
	@Column(name = "ticketPar")
	private Double ticketPar;

	/**
	 * 单个人 支付价 或者 销售价(不含税)
	 */
	@Column(name = "disPrice")
	private Double disPrice;

	/**
	 * 所有人合计 支付金额(含税)
	 */
	@Column(name = "payPrice")
	private Double payPrice;
	/**
	 * 所有人合计 税费
	 */
	@Column(name = "tax")
	private Double tax;

	/**
	 * 票面价/单个人
	 */
	@Column(name = "farePrice")
	private Double farePrice;

	/**
	 * 返点/单个人
	 */
	@Column(name = "rebates")
	private Double rebates;

	/**
	 * 奖励返点/单个人
	 */
	@Column(name = "promotion")
	private Double promotion = 0d;

	/**
	 * 后返点/单个人
	 */
	@Column(name = "after")
	private Double after = 0d;

	/**
	 * 奖金(返现)/单个人
	 */
	@Column(name = "fixedFee")
	private Double fixedFee = 0d;

	/**
	 * 佣金 或 手续费 平台收取/单个人
	 */
	@Column(name = "yj")
	private Double yj;

	/**
	 * 基础运价/单个人
	 */
	@Column(name = "baseRate")
	private Double baseRate = 0d;

	/**
	 * 运价类型（ 淘宝；QW：全网最低价产品 JX：精选产品 JP：金牌产品 HS：航司产品 KUFEI：酷飞产品 ） OTHER :其它 PB2B
	 * 517等平台
	 */
	@Column(name = "priceType", length = 15)
	private String priceType;

	public Double getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Double payPrice) {
		this.payPrice = payPrice;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getRebates() {
		return rebates;
	}

	public void setRebates(Double rebates) {
		this.rebates = rebates;
	}

	public Double getTicketPar() {
		return ticketPar;
	}

	public void setTicketPar(Double ticketPar) {
		this.ticketPar = ticketPar;
	}

	public Double getPromotion() {
		return promotion;
	}

	public void setPromotion(Double promotion) {
		this.promotion = promotion;
	}

	public Double getAfter() {
		return after;
	}

	public void setAfter(Double after) {
		this.after = after;
	}

	public Double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
	}

	public Double getDisPrice() {
		return disPrice;
	}

	public void setDisPrice(Double disPrice) {
		this.disPrice = disPrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public Double getYj() {
		return yj;
	}

	public void setYj(Double yj) {
		this.yj = yj;
	}

	public Double getFarePrice() {
		return farePrice;
	}

	public void setFarePrice(Double farePrice) {
		this.farePrice = farePrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(Double fixedFee) {
		this.fixedFee = fixedFee;
	}

	public ProductDataEntity getProduct() {
		return product;
	}

	public void setProduct(ProductDataEntity product) {
		this.product = product;
	}

	public String getFkpid() {
		return fkpid;
	}

	public void setFkpid(String fkpid) {
		this.fkpid = fkpid;
	}

}
