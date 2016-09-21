package com.smart.oo.domain.res;

public class AirPriceVO implements Comparable<AirPriceVO> {

	/**
	 * 所有人合计 票面价
	 */
	private Double ticketPar;

	/**
	 * 单个人 支付价 或者 销售价(不含税)
	 */
	private Double disPrice;

	/**
	 * 所有人合计 支付金额(含税)
	 */
	private Double payPrice;
	/**
	 * 所有人合计 税费
	 */
	private Double tax;

	/**
	 * 票面价/单个人
	 */
	private Double farePrice;

	/**
	 * 返点/单个人
	 */
	private Double rebates;

	/**
	 * 奖励返点/单个人
	 */
	private Double promotion = 0d;

	/**
	 * 后返点/单个人
	 */
	private Double after = 0d;

	/**
	 * 奖金(返现)/单个人 (易宝返回的是合计 但是已经做了处理成单人)
	 */
	private Double fixedFee = 0d;

	/**
	 * 佣金 或 手续费 平台收取/单个人
	 */
	private Double yj;

	/**
	 * 基础运价/单个人
	 */
	private Double baseRate = 0d;

	/**
	 * 运价类型（ 淘宝；QW：全网最低价产品 JX：精选产品 JP：金牌产品 HS：航司产品 KUFEI：酷飞产品 ） OTHER :其它 PB2B
	 * 517等平台
	 */
	private String priceType;

	private int bjx(AirPriceVO o) {
		int comInt = 0;
		if (o.getDisPrice() != null) {
			if (this.getDisPrice() != null) {
				Double thisprice = this.getDisPrice();
				Double objprice = o.getDisPrice();
				if (thisprice != null && objprice != null) {
					double xiangc = thisprice - objprice;
					if (xiangc > 0) {
						comInt = (int) (xiangc) + 1;
					} else {
						comInt = (int) (xiangc) - 1;
					}
				}
			}
		}
		return comInt;
	}

	@Override
	public int compareTo(AirPriceVO o) {
		// TODO Auto-generated method stub
		int comInt = 0;
		comInt = this.bjx(o);
		return (comInt != 0 ? this.bjx(o) : comInt);
	}

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

	public Double getFixedFee() {
		return fixedFee;
	}

	public void setFixedFee(Double fixedFee) {
		this.fixedFee = fixedFee;
	}

}
