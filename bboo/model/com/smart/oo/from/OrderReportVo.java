package com.smart.oo.from;

public class OrderReportVo {
	// GjBuyOrderEntity
	private String extOrderID;// B2C网站入库号
	private String purchaseNo;// @订单号
	private String oldOrderNo;// 改签订单的原订单
	private String flightType;// 航程类型 S：单程 D：往返
	private Integer airlineCount;// 航段数目
	// private Integer childrenCount;//儿童数目
	// private Integer adultsCount;//成人数目
	private Integer passengerCount;// 乘客总数
	private Double boBxFee;//保险费用
	private Integer boBxCount;//保险份数
	private String boPnrCode;// PNR
	private String boPnrCodeBig;// PNR 大编
	private String payWay;// @支付方式 10 快钱非担保 2 支付宝非担保 1 财付通非担保 11 快钱担保 12 支付宝担保  13 财付通担保 -1其它
	private String transactionNo;// @支付交易流水号
	private String createTime;// 生成订单时间
	private String ticketDate;// 出票时间
	private String flightClass;// 航班类型N：国内 I：国际
	//private String oldId;// 订单分离等操作后的关联的原始订单ID (分离订单，新 ID生成规则，基于乘客分离 ID+P 基于航段分离 ID+F 基于换开分离 ID+O)
	private String purchasePlace;// @采购地
	private String purchasePlaceCh;// 采购地 中文
	private String supplier;// 供应商 官网 代理商
	private String cabinDes;// 舱位信息描述
	private Integer policyType;// 政策类型
	//private Double boPrintPrice;//票面价
	private Double backPoint;// 返点
	private Double afterPoint;// @后返
	private Double stickingPoint;// @贴点
	private Double reward;// @奖励金额
	// private Double price;// @支付机票款(单个人) *（1-backPoint/100）-reward
	// private Double payPirce;//@ 结算价=支付机票款（订单那边有个计算公式）+基建+燃油
	private Double salePrice;// 销售价
	private Double lr;// 利润 销售价- （printPrice*（1-（backPoint+afterPoint+stickingPoint）/100）-reward）
	private Double boAllprice;// @ 结算总价（所有人支付总价） （price+tax）*人数
	private String slfRemark;//SELF 备注
	private String boMerchantNo;//付款的商户号(支付宝，财付通等第三方返回的字段)
	private String status;//订单状态
	private String slfStatus;//SELF 订单状态

	// GjSaleOrderEntity
	private String extOid;// 分销平台对应的订单ID 可以为空
	private String orderNo;// 订单号
	private Double adultPrice;//成人价
	private Double adultTax;//成人税
	private Double childPrice;//儿童价
	private Double childTax;//儿童税
	private Double soBxFee;//保险费用
	private String soPnrCode;// PNR
	private String soPnrCodeBig;//PNR 大编
	private String billId;// 子订单号
	private String shopName;// 分销商 对应的店铺名称
	private String shopNameCh;// 分销商店铺 中文
	private String distributor;// 分销商 T 淘宝 Q 去哪儿 C 携程 H 航班管家 K 酷讯 N 途牛 O 同程
	private String distributorCh;// 分销商 中文
	private String rtOffno;//提取PNR 的OFFICE号
	private Integer openStatus;//0 正常 1换开新订单 2已经换开
	private String openDes;//换票描述 K 允许换开 N 不允许
	private Double buyPrice;//换票上一次采购价 （票款+税费） 合计
	private String policyId;// 采购政策ID
	private String policyCode;// 采购政策Code
	private String productSource;// 政策来源
	private String operator;//操作员
	private String oldPnrCode;//历史PNR PNR,PNR,PNR
	private Double oldAllprice;
	private Double soAllprice;//订单总价旧值//订单总价@拆分订单时要变化
	private String accountInfo;//账号信息 账号|密码|KEY|...
	private String mno;//商户号
	private String journeySheetPrice;//行程单价格
	private String lockUser;//锁定操作人
	private String statement;//其他说明

	// GjBuyPassengerEntity
	private String bpid;// id
	private String name;// 乘客姓名
	private String gender;// 性别 M 男 F 女
	private Integer ageType;// 乘客类别 -1 留学生 0 成人 1 儿童 2婴儿 -2其他
	private String ageDes;// 乘机人类型描述 儿童 青年 老年 中年
	private String eticketNum;// 电子票号
	private Double bpPrintPrice;// @票面价 所有航段累加（和订单那边的票面价一样，也是printPrice字段）
	private Double bpOilFee;// 燃油
	private Double bpTaxFee;// 基建
	private Double bpPayPirce;// @ 结算价=支付机票款（订单那边有个计算公式）+基建+燃油（机票支付款是由票面和返点计算出来的）
	private Double bpCost;//结算票面价(采购的机票款 不含税费)

	// GjSalePassengerEntity
	private Integer bxCount;// 保险数
	private String insNo;// 保险单号
	private Double spCost;// 销售底价
	private Double spOilFee;// 燃油
	private Double spTaxFee;// 基建
	private Double price;// 政策投放的票面价
	private Double commission;// 佣金
	private Double spPrintPrice;// 票面价 所有航段累加(这个字段没什么用，导入的时候不会传值过来)
	private Double spPrice;//政策投放的票面价

	// GjSaleFlightEntity
	private Integer sequence;// 航程序号
	private String carrier;// 承运 CZ
	private String airCodeCh;// 航司中文 南方航空
	private String flightNum;// 航班号
	private String cabin;// 舱位
	private String depAircode;// 出发机场三字码
	private String depAircodeCh;// 中文
	private String arrAircode;// 到达机场三字码
	private String arrAircodeCh;// 到达机场三字码 中文
	private String departureDate;// 出发日期
	private String departureTime;// 起飞时间
	private String arrivalDate;// 到达日期
	private String arrivalTime;// 降落时间
	private Integer segmentType;// 航段类型1 去程 2 回程
	// private Double printPrice;//票面价 是指航段分段价格，也是验价 所使用的价格
	private String addTime;// 创建时间
	private Double selfPrintPrice;// PAT 票面价 是指航段分段价格，也是验价 所使用的价格

	public String getExtOrderID() {
		return extOrderID;
	}

	public void setExtOrderID(String extOrderID) {
		this.extOrderID = extOrderID;
	}

	public String getPurchaseNo() {
		return purchaseNo;
	}

	public void setPurchaseNo(String purchaseNo) {
		this.purchaseNo = purchaseNo;
	}

	public String getOldOrderNo() {
		return oldOrderNo;
	}

	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public Integer getAirlineCount() {
		return airlineCount;
	}

	public void setAirlineCount(Integer airlineCount) {
		this.airlineCount = airlineCount;
	}

	public Integer getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}

	public Double getBoBxFee() {
		return boBxFee;
	}

	public void setBoBxFee(Double boBxFee) {
		this.boBxFee = boBxFee;
	}

	public Integer getBoBxCount() {
		return boBxCount;
	}

	public void setBoBxCount(Integer boBxCount) {
		this.boBxCount = boBxCount;
	}

	public String getBoPnrCode() {
		return boPnrCode;
	}

	public void setBoPnrCode(String boPnrCode) {
		this.boPnrCode = boPnrCode;
	}

	public String getBoPnrCodeBig() {
		return boPnrCodeBig;
	}

	public void setBoPnrCodeBig(String boPnrCodeBig) {
		this.boPnrCodeBig = boPnrCodeBig;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getPurchasePlace() {
		return purchasePlace;
	}

	public void setPurchasePlace(String purchasePlace) {
		this.purchasePlace = purchasePlace;
	}

	public String getPurchasePlaceCh() {
		return purchasePlaceCh;
	}

	public void setPurchasePlaceCh(String purchasePlaceCh) {
		this.purchasePlaceCh = purchasePlaceCh;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCabinDes() {
		return cabinDes;
	}

	public void setCabinDes(String cabinDes) {
		this.cabinDes = cabinDes;
	}

	public Integer getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}

	public Double getBackPoint() {
		return backPoint;
	}

	public void setBackPoint(Double backPoint) {
		this.backPoint = backPoint;
	}

	public Double getAfterPoint() {
		return afterPoint;
	}

	public void setAfterPoint(Double afterPoint) {
		this.afterPoint = afterPoint;
	}

	public Double getStickingPoint() {
		return stickingPoint;
	}

	public void setStickingPoint(Double stickingPoint) {
		this.stickingPoint = stickingPoint;
	}

	public Double getReward() {
		return reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getLr() {
		return lr;
	}

	public void setLr(Double lr) {
		this.lr = lr;
	}

	public Double getBoAllprice() {
		return boAllprice;
	}

	public void setBoAllprice(Double boAllprice) {
		this.boAllprice = boAllprice;
	}

	public String getExtOid() {
		return extOid;
	}

	public void setExtOid(String extOid) {
		this.extOid = extOid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Double getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(Double adultPrice) {
		this.adultPrice = adultPrice;
	}

	public Double getAdultTax() {
		return adultTax;
	}

	public void setAdultTax(Double adultTax) {
		this.adultTax = adultTax;
	}

	public Double getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(Double childPrice) {
		this.childPrice = childPrice;
	}

	public Double getChildTax() {
		return childTax;
	}

	public void setChildTax(Double childTax) {
		this.childTax = childTax;
	}

	public Double getSoBxFee() {
		return soBxFee;
	}

	public void setSoBxFee(Double soBxFee) {
		this.soBxFee = soBxFee;
	}

	public String getSoPnrCode() {
		return soPnrCode;
	}

	public void setSoPnrCode(String soPnrCode) {
		this.soPnrCode = soPnrCode;
	}

	public String getSoPnrCodeBig() {
		return soPnrCodeBig;
	}

	public void setSoPnrCodeBig(String soPnrCodeBig) {
		this.soPnrCodeBig = soPnrCodeBig;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNameCh() {
		return shopNameCh;
	}

	public void setShopNameCh(String shopNameCh) {
		this.shopNameCh = shopNameCh;
	}

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	public String getDistributorCh() {
		return distributorCh;
	}

	public void setDistributorCh(String distributorCh) {
		this.distributorCh = distributorCh;
	}

	public String getRtOffno() {
		return rtOffno;
	}

	public void setRtOffno(String rtOffno) {
		this.rtOffno = rtOffno;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public String getOpenDes() {
		return openDes;
	}

	public void setOpenDes(String openDes) {
		this.openDes = openDes;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSlfStatus() {
		return slfStatus;
	}

	public void setSlfStatus(String slfStatus) {
		this.slfStatus = slfStatus;
	}

	public String getOldPnrCode() {
		return oldPnrCode;
	}

	public void setOldPnrCode(String oldPnrCode) {
		this.oldPnrCode = oldPnrCode;
	}

	public Double getOldAllprice() {
		return oldAllprice;
	}

	public void setOldAllprice(Double oldAllprice) {
		this.oldAllprice = oldAllprice;
	}

	public Double getSoAllprice() {
		return soAllprice;
	}

	public void setSoAllprice(Double soAllprice) {
		this.soAllprice = soAllprice;
	}

	public String getBpid() {
		return bpid;
	}

	public void setBpid(String bpid) {
		this.bpid = bpid;
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

	public String getEticketNum() {
		return eticketNum;
	}

	public void setEticketNum(String eticketNum) {
		this.eticketNum = eticketNum;
	}

	public Double getBpPrintPrice() {
		return bpPrintPrice;
	}

	public void setBpPrintPrice(Double bpPrintPrice) {
		this.bpPrintPrice = bpPrintPrice;
	}

	public Double getBpOilFee() {
		return bpOilFee;
	}

	public void setBpOilFee(Double bpOilFee) {
		this.bpOilFee = bpOilFee;
	}

	public Double getBpTaxFee() {
		return bpTaxFee;
	}

	public void setBpTaxFee(Double bpTaxFee) {
		this.bpTaxFee = bpTaxFee;
	}

	public Double getBpPayPirce() {
		return bpPayPirce;
	}

	public void setBpPayPirce(Double bpPayPirce) {
		this.bpPayPirce = bpPayPirce;
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

	public Double getSpCost() {
		return spCost;
	}

	public void setSpCost(Double spCost) {
		this.spCost = spCost;
	}

	public Double getSpOilFee() {
		return spOilFee;
	}

	public void setSpOilFee(Double spOilFee) {
		this.spOilFee = spOilFee;
	}

	public Double getSpTaxFee() {
		return spTaxFee;
	}

	public void setSpTaxFee(Double spTaxFee) {
		this.spTaxFee = spTaxFee;
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

	public Double getSpPrintPrice() {
		return spPrintPrice;
	}

	public void setSpPrintPrice(Double spPrintPrice) {
		this.spPrintPrice = spPrintPrice;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getAirCodeCh() {
		return airCodeCh;
	}

	public void setAirCodeCh(String airCodeCh) {
		this.airCodeCh = airCodeCh;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
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

	public String getDepAircodeCh() {
		return depAircodeCh;
	}

	public void setDepAircodeCh(String depAircodeCh) {
		this.depAircodeCh = depAircodeCh;
	}

	public String getArrAircode() {
		return arrAircode;
	}

	public void setArrAircode(String arrAircode) {
		this.arrAircode = arrAircode;
	}

	public String getArrAircodeCh() {
		return arrAircodeCh;
	}

	public void setArrAircodeCh(String arrAircodeCh) {
		this.arrAircodeCh = arrAircodeCh;
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

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getSegmentType() {
		return segmentType;
	}

	public void setSegmentType(Integer segmentType) {
		this.segmentType = segmentType;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Double getSelfPrintPrice() {
		return selfPrintPrice;
	}

	public void setSelfPrintPrice(Double selfPrintPrice) {
		this.selfPrintPrice = selfPrintPrice;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getJourneySheetPrice() {
		return journeySheetPrice;
	}

	public void setJourneySheetPrice(String journeySheetPrice) {
		this.journeySheetPrice = journeySheetPrice;
	}

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getSlfRemark() {
		return slfRemark;
	}

	public void setSlfRemark(String slfRemark) {
		this.slfRemark = slfRemark;
	}

	public String getBoMerchantNo() {
		return boMerchantNo;
	}

	public void setBoMerchantNo(String boMerchantNo) {
		this.boMerchantNo = boMerchantNo;
	}

	public Double getSpPrice() {
		return spPrice;
	}

	public void setSpPrice(Double spPrice) {
		this.spPrice = spPrice;
	}

	public Double getBpCost() {
		return bpCost;
	}

	public void setBpCost(Double bpCost) {
		this.bpCost = bpCost;
	}
	
}
