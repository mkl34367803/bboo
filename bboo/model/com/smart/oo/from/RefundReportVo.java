package com.smart.oo.from;

public class RefundReportVo {
	/**
	 * SELF 订单状态 0 订座成功等待支付(人工) 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30
	 * 退票申请中 31 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60
	 * 支付待确认（支付失败） 61 暂缓出票（出票失败） 62 订单超时 -1其它 99 订座成功等待支付（系统） 70退票留票中
	 */
	private String orderStatus;
	// 报表数据
	private String purchasePlaceCh;// 采购地 中文

	private String distributorCh;// 分销商 中文
	private String shopNameCh;// 分销商店铺 中文
	private String orderNo;// 订单号
	private String orderCreateDate;// 订单生成时间
	private String flightType;// 航程类型 S：单程 D：往返 T其它 L 联程 X 多程
	private String pnrCode;// PNR

	private String name;// 乘客姓名
	private String ageType;// 乘客类别 -1 留学生 0 成人 1 儿童 2婴儿 -2其他
	private String eticketNum;// 电子票号
	private String addTime;// 创建时间 出票时间

	private String carrier;// 承运 CZ
	private String flightNum;// 航班号
	private String shareNum;// 共享航班号
	private String actCabin;// 实际出票舱位
	private String actFltno;// 实际出票航班号
	private String cabin;// 舱位
	private String depAircode;// 出发机场三字码
	private String arrAircode;// 到达机场三字码
	private String departureDate;// 出发日期
	private String departureTime;// 起飞时间

	private Double face;// 舱位票面价
	private Double baseFace;// 退票的票面 或者 Y舱票面
	private Double yq;// 燃油
	private Double tax;// 机建
	private Double rate;// 退票费率
	private Double fee;// 退票费 baseFace*(1-rate/100) 或者 Yprice*(1-rate/100)
	private Double refund;// 退款金额 （销售价-fee +yq+tax） 如果 销售价-fee<0 则按0计算
							// ------------
							// { 上级退款 （采购金额-fee +yq+tax） 如果 采购金额-fee<0 则按0计算}
	private Double actRefund;// 实际退款金额 含税

	private Double face2;// 舱位票面价
	private Double baseFace2;// 退票的票面 或者 Y舱票面
	private Double yq2;// 燃油
	private Double tax2;// 机建
	private Double rate2;// 退票费率
	private Double fee2;// 退票费 baseFace*(1-rate/100) 或者 Yprice*(1-rate/100)
	private Double refund2;// 退款金额 （销售价-fee +yq+tax） 如果 销售价-fee<0 则按0计算
							// ------------
							// { 上级退款 （采购金额-fee +yq+tax） 如果 采购金额-fee<0 则按0计算}
	private Double actRefund2;// 实际退款金额 含税

	private Double lr;

	private Long pcount;// 退票的乘机人数 默认1
	private String operator;// 操作人
	private String remark;
	private String isdelay;// 1正常 2延误
	private String refundTime;// 三种解释 1客户提交退票时间 2提交航司退票时间 3航司退款时间

	public String getPurchasePlaceCh() {
		return purchasePlaceCh;
	}

	public void setPurchasePlaceCh(String purchasePlaceCh) {
		this.purchasePlaceCh = purchasePlaceCh;
	}

	public String getDistributorCh() {
		return distributorCh;
	}

	public void setDistributorCh(String distributorCh) {
		this.distributorCh = distributorCh;
	}

	public String getShopNameCh() {
		return shopNameCh;
	}

	public void setShopNameCh(String shopNameCh) {
		this.shopNameCh = shopNameCh;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(String orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getPnrCode() {
		return pnrCode;
	}

	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeType() {
		return ageType;
	}

	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}

	public String getEticketNum() {
		return eticketNum;
	}

	public void setEticketNum(String eticketNum) {
		this.eticketNum = eticketNum;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public String getShareNum() {
		return shareNum;
	}

	public void setShareNum(String shareNum) {
		this.shareNum = shareNum;
	}

	public String getActCabin() {
		return actCabin;
	}

	public void setActCabin(String actCabin) {
		this.actCabin = actCabin;
	}

	public String getActFltno() {
		return actFltno;
	}

	public void setActFltno(String actFltno) {
		this.actFltno = actFltno;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getDepAircode() {
		return depAircode;
	}

	public void setDepAircode(String depAircode) {
		this.depAircode = depAircode;
	}

	public String getArrAircode() {
		return arrAircode;
	}

	public void setArrAircode(String arrAircode) {
		this.arrAircode = arrAircode;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Double getFace() {
		return face;
	}

	public void setFace(Double face) {
		this.face = face;
	}

	public Double getBaseFace() {
		return baseFace;
	}

	public void setBaseFace(Double baseFace) {
		this.baseFace = baseFace;
	}

	public Double getYq() {
		return yq;
	}

	public void setYq(Double yq) {
		this.yq = yq;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getRefund() {
		return refund;
	}

	public void setRefund(Double refund) {
		this.refund = refund;
	}

	public Double getActRefund() {
		return actRefund;
	}

	public void setActRefund(Double actRefund) {
		this.actRefund = actRefund;
	}

	public Double getFace2() {
		return face2;
	}

	public void setFace2(Double face2) {
		this.face2 = face2;
	}

	public Double getBaseFace2() {
		return baseFace2;
	}

	public void setBaseFace2(Double baseFace2) {
		this.baseFace2 = baseFace2;
	}

	public Double getYq2() {
		return yq2;
	}

	public void setYq2(Double yq2) {
		this.yq2 = yq2;
	}

	public Double getTax2() {
		return tax2;
	}

	public void setTax2(Double tax2) {
		this.tax2 = tax2;
	}

	public Double getRate2() {
		return rate2;
	}

	public void setRate2(Double rate2) {
		this.rate2 = rate2;
	}

	public Double getFee2() {
		return fee2;
	}

	public void setFee2(Double fee2) {
		this.fee2 = fee2;
	}

	public Double getRefund2() {
		return refund2;
	}

	public void setRefund2(Double refund2) {
		this.refund2 = refund2;
	}

	public Double getActRefund2() {
		return actRefund2;
	}

	public void setActRefund2(Double actRefund2) {
		this.actRefund2 = actRefund2;
	}

	public Double getLr() {
		return lr;
	}

	public void setLr(Double lr) {
		this.lr = lr;
	}

	public Long getPcount() {
		return pcount;
	}

	public void setPcount(Long pcount) {
		this.pcount = pcount;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsdelay() {
		return isdelay;
	}

	public void setIsdelay(String isdelay) {
		this.isdelay = isdelay;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
