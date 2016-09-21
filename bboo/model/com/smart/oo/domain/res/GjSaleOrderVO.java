package com.smart.oo.domain.res;

import java.util.ArrayList;
import java.util.List;

import com.bbang.api.BbangObject;

/**
 * 国际订单表
 * 
 * @author eric
 * 
 */

public class GjSaleOrderVO extends BbangObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2899851210024603723L;
	private List<GjSaleFlightVO> flights = new ArrayList<GjSaleFlightVO>(0);
	private List<GjSalePassengerVO> passengers = new ArrayList<GjSalePassengerVO>(
			0);
	/**
	 * B2C网站入库号
	 */
	private String extOrderID;

	/**
	 * 分销平台对应的订单ID 可以为空
	 */
	private String extOid;

	/**
	 * 订单号
	 */
	private String orderNo;

	private String departureDate;

	private String departureTime;

	/**
	 * 子订单号
	 */
	private String billId;
	/**
	 * 改签订单的原订单
	 */
	private String oldOrderNo;

	/**
	 * 航程类型 S：单程 D：往返 T其它 L 联程 X 多程
	 */
	private String flightType;
	/**
	 * 订单状态 0 订座成功等待支付 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30 退票申请中 31
	 * 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60 支付待确认 61
	 * 暂缓出票 62 订单超时 -1其它 70 出票失败 72订单作废
	 */
	private String status;
	/**
	 * 用户中心会员昵称
	 */
	private String userName;
	/**
	 * 航段数目
	 */
	private Integer airlineCount;
	/**
	 * 儿童数目
	 */
	private Integer childrenCount;
	/**
	 * 成人数目
	 */
	private Integer adultsCount;
	/**
	 * 乘客总数
	 */
	private Integer passengerCount;
	/**
	 * 成人价
	 */
	private Double adultPrice;
	/**
	 * 成人税
	 */
	private Double adultTax;
	/**
	 * 儿童价
	 */
	private Double childPrice;
	/**
	 * 儿童税
	 */
	private Double childTax;
	/**
	 * 订单总价旧值
	 */
	private Double oldAllprice;
	/**
	 * 订单总价
	 */
	private Double allprice;
	/**
	 * 未支付价格
	 */
	private Double noPay;
	/**
	 * 保险费用
	 */
	private Double bxFee;
	/**
	 * 保险份数
	 */
	private Integer bxCount;
	/**
	 * 销售政策ID
	 */
	private String policyId;
	/**
	 * 销售政策Code
	 */
	private String policyCode;

	/**
	 * 出票紧急度 1、临近转出时间 （距离转出小于30分钟） 2、催出票 （urgencyTimes>0） 3、AV舱位不足5个
	 * (由于前段数据不足，故暂时废弃) 4、临近PNR ADTK （距离ADTK大于3小时） 5、出票超时长规范 （进入超过1小时） -1其它
	 */
	private Integer urgency;

	/**
	 * 催出票次数
	 */
	private Integer urgeTimes;
	/**
	 * 政策来源
	 */
	private String productSource;
	/**
	 * 订单来源 self 普通政策 qfare 加价政策 spec 特殊政策 othe 其它
	 */
	private String source;
	/**
	 * PNR
	 */
	private String pnrCode;

	/**
	 * PNR 大编
	 */
	private String pnrCodeBig;
	
	private String salePnrCode;

	/**
	 * 记录状态 0 PNR当前有效 1 PNR清理成功 2 PNR清理失败
	 */
	private String pnrStatus;
	/**
	 * 是否需要支付校验
	 */
	private String orderNeedConfirm;
	/**
	 * 退票规则
	 */
	private String returnTicketRule;
	/**
	 * 改期规则
	 */
	private String changeDateRule;
	/**
	 * 行李额
	 */
	private String luggageRule;
	/**
	 * 其他说明
	 */
	private String statement;
	/**
	 * 支付方式 10 快钱非担保 2 支付宝非担保 1 财付通非担保 11 快钱担保 12 支付宝担保 13 财付通担保 -1其它
	 */
	private String payWay;
	/**
	 * 支付交易流水号
	 */
	private String transactionNo;
	/**
	 * 资金解冻状态
	 */
	private String fzstatus;
	/**
	 * 退款原因
	 */
	private String refundCause;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系人手机
	 */
	private String contactMob;
	/**
	 * 联系人电话
	 */
	private String contactTel;
	/**
	 * 联系人邮箱
	 */
	private String contactEmail;
	/**
	 * 是否包含行程单
	 */
	private String journeySheet;
	/**
	 * 行程单号
	 */
	private String journeySheetNo;
	/**
	 * 行程单投递方式 AIR 机场自取 EMS EMS邮递 GET 市内自取 PJN 不要报销凭证 PJS 邮寄行程单 SND 市内配送
	 * 空表示不需要配送
	 */
	private String journeySheetWay;
	/**
	 * 行程单价格
	 */
	private String journeySheetPrice;
	/**
	 * 行程单状态
	 */
	private String journeySheetStatu;
	/**
	 * 收件人
	 */
	private String receiver;
	/**
	 * 邮政编码
	 */
	private String postcode;
	/**
	 * 投递地址
	 */
	private String address;
	/**
	 * 用户备注
	 */
	private String remark;
	/**
	 * 生成订单时间
	 */
	private String createTime;
	/**
	 * 订单最后更新日期
	 */
	private String lastUpdated;
	/**
	 * 出票时间
	 */
	private String ticketDate;
	/**
	 * 资金解冻时间
	 */
	private String unfzTime;
	/**
	 * 操作员
	 */
	private String operator;
	/**
	 * 航程简述 100长度
	 */
	private String orderDesc;

	/***********************************/
	/**
	 * 币种 RMB
	 */
	private String currency;
	/**
	 * SELF 订单状态
	 */
	private String slfStatus;

	/**
	 * SELF 添加时间
	 */
	private String slfAddtime;

	/**
	 * N：国内 I：国际
	 */
	private String flightClass;

	/**
	 * 账号信息 账号|密码|KEY|...
	 */
	private String accountInfo;

	/**
	 * 分销商 T 淘宝 Q 去哪儿 C 携程 H 航班管家 K 酷讯 N 途牛 O 同程
	 */
	private String distributor;

	/**
	 * 分销商 中文
	 */
	private String distributorCh;

	/**
	 * 平台网站或者域名
	 */
	private String url;

	/**
	 * 0成人 1儿童 2婴儿 3成人+儿童 4成人+婴儿 5成人+儿童+婴儿 -1其他
	 */
	private Integer pnrType;

	private String pnrTypeEn;

	/**
	 * PNR 文本
	 */
	private String pnrText;

	/**
	 * AV 文本
	 */
	private String avText;

	/**
	 * 舱位信息描述
	 */
	private String cabinDes;

	/**
	 * PNR 预定的OFFICE号
	 */
	private String pnrOffNo;

	/**
	 * 提取PNR 的OFFICE号
	 */
	private String rtOffno;

	/**
	 * 航班座位数 描述
	 */
	private String seatsDes;
	/**
	 * 航班剩余座位数
	 */
	private String seats;

	/**
	 * 0 正常 1换开新订单 2已经换开
	 */
	private Integer openStatus;

	/**
	 * 换票描述 K 允许换开 N 不允许
	 */
	private String openDes;

	/**
	 * 
	 * 政策类型 1 NFD 2 清仓产品 3 商旅产品 5 中转产品 6 包机 7 切位 8 航司VIP卡 10000 普通 10001 规则运价 10
	 * 金牌 11 远期 13竞价 14特价 15特惠 16 让利 17 特殊 30 优选 31 立减 -1其它
	 * 
	 * 10001 规则运价 10002 公布运价 10003 私有运价 10004 供应商直连 10005 申请 10006 包机/切位 10007
	 * 直销 10008 特惠 10009 固定 K 位 10010 清仓产品 10011 公布转私有 10021 包机/切位 10022 申
	 */
	private Integer policyType;

	/**
	 * 产品类型 中文 往返预付 往返普通 预付产品 包机切位 新包机切位 特价产品 全局产品 普通产品 申请产品 抢单
	 */
	private String productType;

	/**
	 * 国内： AirLineMarketing:航司直销 PriorityPackage:优选套餐 BusinessPriority:商务优选
	 * TravelPackage:旅行套餐 国际： Prioritizing:商务优选 Exclusive:旅行套餐
	 */
	private String saleType;

	private String specialCode;

	private String fareBasis;

	private String pnrNoTime;

	public String getExtOrderID() {
		return extOrderID;
	}

	public void setExtOrderID(String extOrderID) {
		this.extOrderID = extOrderID;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAirlineCount() {
		return airlineCount;
	}

	public void setAirlineCount(Integer airlineCount) {
		this.airlineCount = airlineCount;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public Integer getAdultsCount() {
		return adultsCount;
	}

	public void setAdultsCount(Integer adultsCount) {
		this.adultsCount = adultsCount;
	}

	public Integer getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
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

	public Double getOldAllprice() {
		return oldAllprice;
	}

	public void setOldAllprice(Double oldAllprice) {
		this.oldAllprice = oldAllprice;
	}

	public Double getAllprice() {
		return allprice;
	}

	public void setAllprice(Double allprice) {
		this.allprice = allprice;
	}

	public Double getNoPay() {
		return noPay;
	}

	public void setNoPay(Double noPay) {
		this.noPay = noPay;
	}

	public Double getBxFee() {
		return bxFee;
	}

	public void setBxFee(Double bxFee) {
		this.bxFee = bxFee;
	}

	public Integer getBxCount() {
		return bxCount;
	}

	public void setBxCount(Integer bxCount) {
		this.bxCount = bxCount;
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

	public Integer getUrgency() {
		return urgency;
	}

	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
	}

	public Integer getUrgeTimes() {
		return urgeTimes;
	}

	public void setUrgeTimes(Integer urgeTimes) {
		this.urgeTimes = urgeTimes;
	}

	public String getProductSource() {
		return productSource;
	}

	public void setProductSource(String productSource) {
		this.productSource = productSource;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPnrCode() {
		return pnrCode;
	}

	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}

	public String getPnrCodeBig() {
		return pnrCodeBig;
	}

	public void setPnrCodeBig(String pnrCodeBig) {
		this.pnrCodeBig = pnrCodeBig;
	}

	public String getPnrStatus() {
		return pnrStatus;
	}

	public void setPnrStatus(String pnrStatus) {
		this.pnrStatus = pnrStatus;
	}

	public String getOrderNeedConfirm() {
		return orderNeedConfirm;
	}

	public void setOrderNeedConfirm(String orderNeedConfirm) {
		this.orderNeedConfirm = orderNeedConfirm;
	}

	public String getReturnTicketRule() {
		return returnTicketRule;
	}

	public void setReturnTicketRule(String returnTicketRule) {
		this.returnTicketRule = returnTicketRule;
	}

	public String getChangeDateRule() {
		return changeDateRule;
	}

	public void setChangeDateRule(String changeDateRule) {
		this.changeDateRule = changeDateRule;
	}

	public String getLuggageRule() {
		return luggageRule;
	}

	public void setLuggageRule(String luggageRule) {
		this.luggageRule = luggageRule;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
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

	public String getFzstatus() {
		return fzstatus;
	}

	public void setFzstatus(String fzstatus) {
		this.fzstatus = fzstatus;
	}

	public String getRefundCause() {
		return refundCause;
	}

	public void setRefundCause(String refundCause) {
		this.refundCause = refundCause;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMob() {
		return contactMob;
	}

	public void setContactMob(String contactMob) {
		this.contactMob = contactMob;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getJourneySheet() {
		return journeySheet;
	}

	public void setJourneySheet(String journeySheet) {
		this.journeySheet = journeySheet;
	}

	public String getJourneySheetNo() {
		return journeySheetNo;
	}

	public void setJourneySheetNo(String journeySheetNo) {
		this.journeySheetNo = journeySheetNo;
	}

	public String getJourneySheetWay() {
		return journeySheetWay;
	}

	public void setJourneySheetWay(String journeySheetWay) {
		this.journeySheetWay = journeySheetWay;
	}

	public String getJourneySheetPrice() {
		return journeySheetPrice;
	}

	public void setJourneySheetPrice(String journeySheetPrice) {
		this.journeySheetPrice = journeySheetPrice;
	}

	public String getJourneySheetStatu() {
		return journeySheetStatu;
	}

	public void setJourneySheetStatu(String journeySheetStatu) {
		this.journeySheetStatu = journeySheetStatu;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getTicketDate() {
		return ticketDate;
	}

	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}

	public String getUnfzTime() {
		return unfzTime;
	}

	public void setUnfzTime(String unfzTime) {
		this.unfzTime = unfzTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSlfStatus() {
		return slfStatus;
	}

	public void setSlfStatus(String slfStatus) {
		this.slfStatus = slfStatus;
	}

	public String getSlfAddtime() {
		return slfAddtime;
	}

	public void setSlfAddtime(String slfAddtime) {
		this.slfAddtime = slfAddtime;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPnrType() {
		return pnrType;
	}

	public void setPnrType(Integer pnrType) {
		this.pnrType = pnrType;
	}

	public String getPnrText() {
		return pnrText;
	}

	public void setPnrText(String pnrText) {
		this.pnrText = pnrText;
	}

	public String getAvText() {
		return avText;
	}

	public void setAvText(String avText) {
		this.avText = avText;
	}

	public String getCabinDes() {
		return cabinDes;
	}

	public void setCabinDes(String cabinDes) {
		this.cabinDes = cabinDes;
	}

	public String getPnrOffNo() {
		return pnrOffNo;
	}

	public void setPnrOffNo(String pnrOffNo) {
		this.pnrOffNo = pnrOffNo;
	}

	public String getRtOffno() {
		return rtOffno;
	}

	public void setRtOffno(String rtOffno) {
		this.rtOffno = rtOffno;
	}

	public String getSeatsDes() {
		return seatsDes;
	}

	public void setSeatsDes(String seatsDes) {
		this.seatsDes = seatsDes;
	}

	public String getSeats() {
		return seats;
	}

	public void setSeats(String seats) {
		this.seats = seats;
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

	public Integer getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}

	public List<GjSaleFlightVO> getFlights() {
		return flights;
	}

	public void setFlights(List<GjSaleFlightVO> flights) {
		this.flights = flights;
	}

	public List<GjSalePassengerVO> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<GjSalePassengerVO> passengers) {
		this.passengers = passengers;
	}

	public String getExtOid() {
		return extOid;
	}

	public void setExtOid(String extOid) {
		this.extOid = extOid;
	}

	public String getSpecialCode() {
		return specialCode;
	}

	public void setSpecialCode(String specialCode) {
		this.specialCode = specialCode;
	}

	public String getFareBasis() {
		return fareBasis;
	}

	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
	}

	public String getPnrNoTime() {
		return pnrNoTime;
	}

	public void setPnrNoTime(String pnrNoTime) {
		this.pnrNoTime = pnrNoTime;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
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

	public String getPnrTypeEn() {
		return pnrTypeEn;
	}

	public void setPnrTypeEn(String pnrTypeEn) {
		this.pnrTypeEn = pnrTypeEn;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getSalePnrCode() {
		return salePnrCode;
	}

	public void setSalePnrCode(String salePnrCode) {
		this.salePnrCode = salePnrCode;
	}

	/***********************************/

}
