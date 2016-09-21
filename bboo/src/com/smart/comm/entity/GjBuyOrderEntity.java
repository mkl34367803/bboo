package com.smart.comm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_gj_buyorder")
public class GjBuyOrderEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -777082179326964147L;

	@OneToMany(targetEntity = GjBuyFlightEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	@OrderBy("id desc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<GjBuyFlightEntity> flights = new HashSet<GjBuyFlightEntity>(0);
	@OneToMany(targetEntity = GjBuyPassengerEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	@OrderBy("id desc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<GjBuyPassengerEntity> passengers = new HashSet<GjBuyPassengerEntity>(
			0);

	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;

	// @Temporal(TemporalType.TIMESTAMP)
	// @Transient
	@Column(name = "timestamp",columnDefinition="timestamp",insertable=false,updatable=false)
	private String timestamp;

	@Column(name = "stamptime", insertable = false)
	private Long stamptime = System.currentTimeMillis();
	/**
	 * 订单表主键
	 */
	@Column(name = "fkoid", length = 55)
	private String fkoid;

	/**
	 * B2C网站入库号
	 */
	@Column(name = "extOrderID", length = 55)
	private String extOrderID;

	/**
	 * @订单号
	 */
	@Column(name = "purchaseNo", length = 55)
	private String purchaseNo;

	/**
	 * 改签订单的原订单
	 */
	@Column(name = "oldOrderNo", length = 55)
	private String oldOrderNo;

	/**
	 * 航程类型 S：单程 D：往返
	 */
	@Column(name = "flightType", length = 5)
	private String flightType;
	/**
	 * 订单状态 0 订座成功等待支付(人工) 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30 退票申请中 31
	 * 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60 支付待确认（支付失败）
	 * 61 暂缓出票（出票失败） 62 订单超时 -1其它 99 订座成功等待支付（系统） 70退票留票中 80 审核未通过，拒绝退票 81
	 * 审核未通过，拒绝改签
	 */
	@Column(name = "status", length = 15)
	private String status;

	/**
	 * 航段数目
	 */
	@Column(name = "airlineCount")
	private Integer airlineCount;

	/**
	 * 儿童数目
	 */
	@Column(name = "childrenCount")
	private Integer childrenCount;
	/**
	 * 成人数目
	 */
	@Column(name = "adultsCount")
	private Integer adultsCount;
	/**
	 * 乘客总数
	 */
	@Column(name = "passengerCount")
	private Integer passengerCount;

	/**
	 * 保险费用
	 */
	@Column(name = "bxFee")
	private Double bxFee;
	/**
	 * 保险份数
	 */
	@Column(name = "bxCount")
	private Integer bxCount;

	/**
	 * 采购政策ID
	 */
	@Column(name = "policyId", length = 55)
	private String policyId;

	/**
	 * 采购政策Code
	 */
	@Column(name = "policyCode", length = 55)
	private String policyCode;

	/**
	 * 出票紧急度 1、临近转出时间 （距离转出小于30分钟） 2、催出票 （urgencyTimes>0） 3、AV舱位不足5个
	 * (由于前段数据不足，故暂时废弃) 4、临近PNR ADTK （距离ADTK大于3小时） 5、出票超时长规范 （进入超过1小时）
	 */
	@Column(name = "urgency", length = 5)
	private Integer urgency;

	/**
	 * 催出票次数
	 */
	@Column(name = "urgeTimes", length = 30)
	private Integer urgeTimes;

	/**
	 * 政策来源
	 */
	@Column(name = "productSource", length = 55)
	private String productSource;

	/**
	 * PNR
	 */
	@Column(name = "pnrCode", length = 20)
	private String pnrCode;

	/**
	 * PNR 大编
	 */
	@Column(name = "pnrCodeBig", length = 10)
	private String pnrCodeBig;
	/**
	 * 记录状态
	 */
	@Column(name = "pnrStatus", length = 30)
	private String pnrStatus;

	/**
	 * 退票规则
	 */
	@Column(name = "returnTicketRule", length = 1000)
	private String returnTicketRule;
	/**
	 * 改期规则
	 */
	@Column(name = "changeDateRule", length = 1000)
	private String changeDateRule;

	/**
	 * 行李额@100
	 */
	@Column(name = "luggageRule", length = 1000)
	private String luggageRule;
	/**
	 * 其他说明
	 */
	@Column(name = "statement", length = 1000)
	private String statement;

	/**
	 * @支付方式 10 快钱非担保 2 支付宝非担保 1 财付通非担保 11 快钱担保 12 支付宝担保 13 财付通担保 -1其它
	 */
	@Column(name = "payWay", length = 5)
	private String payWay;
	/**
	 * @支付交易流水号
	 */
	@Column(name = "transactionNo", length = 55)
	private String transactionNo;
	/**
	 * 付款的商户号(支付宝，财付通等第三方返回的字段)
	 */
	@Column(name = "merchantNo", length = 60)
	private String merchantNo;
	/**
	 * 退款原因
	 */
	@Column(name = "refundCause", length = 100)
	private String refundCause;

	/**
	 * 联系人姓名
	 */
	@Column(name = "contactName", length = 30)
	private String contactName;
	/**
	 * 联系人手机@20
	 */
	@Column(name = "contactMob", length = 20)
	private String contactMob;
	/**
	 * 联系人电话
	 */
	@Column(name = "contactTel", length = 12)
	private String contactTel;
	/**
	 * 联系人邮箱
	 */
	@Column(name = "contactEmail", length = 30)
	private String contactEmail;
	/**
	 * 是否包含行程单
	 */
	@Column(name = "journeySheet", length = 10)
	private String journeySheet;
	/**
	 * 行程单号
	 */
	@Column(name = "journeySheetNo", length = 55)
	private String journeySheetNo;
	/**
	 * 行程单投递方式 AIR 机场自取 EMS EMS邮递 GET 市内自取 PJN 不要报销凭证 PJS 邮寄行程单 SND 市内配送
	 * 空表示不需要配送
	 */
	@Column(name = "journeySheetWay", length = 10)
	private String journeySheetWay;
	/**
	 * 行程单价格
	 */
	@Column(name = "journeySheetPrice", length = 10)
	private String journeySheetPrice;
	/**
	 * 行程单状态
	 */
	@Column(name = "journeySheetStatu", length = 10)
	private String journeySheetStatu;
	/**
	 * 收件人
	 */
	@Column(name = "receiver", length = 30)
	private String receiver;
	/**
	 * 邮政编码
	 */
	@Column(name = "postcode", length = 10)
	private String postcode;
	/**
	 * 投递地址
	 */
	@Column(name = "address", length = 100)
	private String address;
	/**
	 * 出票备注
	 */
	@Column(name = "remark", length = 1000)
	private String remark;
	/**
	 * 生成订单时间
	 */
	@Column(name = "createTime", length = 30)
	private String createTime;

	/**
	 * 订单最后更新日期
	 */
	@Column(name = "lastUpdated", length = 30)
	private String lastUpdated;
	/**
	 * 出票时间
	 */
	@Column(name = "ticketDate", length = 30)
	private String ticketDate;

	/***********************************/
	/**
	 * 币种 RMB
	 */
	@Column(name = "currency", length = 10)
	private String currency;
	/**
	 * 订单状态 0 订座成功等待支付(人工) 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30 退票申请中 31
	 * 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60 支付待确认（支付失败）
	 * 61 暂缓出票（出票失败） 62 订单超时 -1其它 99 订座成功等待支付（系统） 70退票留票中 80 审核未通过，拒绝退票 81
	 * 审核未通过，拒绝改签
	 */
	@Column(name = "slfStatus", length = 5)
	private String slfStatus;

	/**
	 * SELF 添加时间
	 */
	@Column(name = "addtime", length = 30)
	private String addtime;

	/**
	 * 锁定操作人
	 */
	@Column(name = "lockUser", length = 30)
	private String lockUser;

	/**
	 * SELF 备注
	 */
	@Column(name = "slfRemark", length = 300)
	private String slfRemark;

	/**
	 * N：国内 I：国际
	 */
	@Column(name = "flightClass", length = 3)
	private String flightClass;

	/**
	 * 订单分离等操作后的关联的原始订单ID (分离订单，新 ID生成规则，基于乘客分离 ID+P 基于航段分离 ID+F 基于换开分离 ID+O)
	 */
	@Column(name = "oldId", length = 55)
	private String oldId;

	/**
	 * @采购地
	 */
	@Column(name = "purchasePlace", length = 10)
	private String purchasePlace;

	/**
	 * &采购地 中文
	 */
	@Column(name = "purchasePlaceCh", length = 30)
	private String purchasePlaceCh;

	/**
	 * 供应商 官网 代理商
	 */
	@Column(name = "supplier", length = 30)
	private String supplier;

	/**
	 * 0成人 1儿童 2婴儿 3成人+儿童 4成人+婴儿 5成人+儿童+婴儿 -1其他
	 */
	@Column(name = "pnrType", length = 3)
	private Integer pnrType;

	@Column(name = "pnrTypeEn", length = 10)
	private String pnrTypeEn;

	/**
	 * PNR 文本
	 */
	@Column(name = "pnrText", length = 2000)
	private String pnrText;

	/**
	 * AV 文本
	 */
	@Column(name = "avText", length = 500)
	private String avText;

	/**
	 * 舱位信息描述
	 */
	@Column(name = "cabinDes", length = 55)
	private String cabinDes;

	/**
	 * PNR 预定的OFFICE号
	 */
	@Column(name = "pnrOffNo", length = 10)
	private String pnrOffNo;

	/**
	 * 提取PNR 的OFFICE号
	 */
	@Column(name = "rtOffno", length = 10)
	private String rtOffno;
	/**
	 * BSP 出票的OFFICE号 或者是供应出票的OFFICE号
	 */
	@Column(name = "etdzOffno", length = 10)
	private String etdzOffno;
	/**
	 * 打票机台数
	 */
	@Column(name = "etdzNo")
	private Integer etdzNo;
	/**
	 * 航班剩余座位数
	 */
	@Column(name = "seats", length = 3)
	private Integer seats;

	/**
	 * 政策类型
	 */
	@Column(name = "priceType", length = 30)
	private String priceType;

	/**
	 * 采购账号信息 账号|密码|KEY|...
	 */
	@Column(name = "purchaseAccount", length = 55)
	private String purchaseAccount;

	/**
	 * @支付账号信息 账号|密码|KEY|...
	 */
	@Column(name = "payAccount", length = 55)
	private String payAccount;

	/**
	 * 采购账户表ID
	 */
	@Column(name = "accountid")
	private Integer accountid;

	/**
	 * 出票方式
	 */
	@Column(name = "drawWay", length = 10)
	private String drawWay;

	/**
	 * @票面价
	 */
	@Column(name = "printPrice")
	private Double printPrice;

	/**
	 * @返点
	 */
	@Column(name = "backPoint")
	private Double backPoint;
	/**
	 * @后返
	 */
	@Column(name = "afterPoint")
	private Double afterPoint;

	/**
	 * @贴点
	 */
	@Column(name = "stickingPoint")
	private Double stickingPoint;

	/**
	 * @奖励金额(返现)
	 */
	@Column(name = "reward")
	private Double reward;

	/**
	 * @支付机票款(单个人) printPrice*（1-backPoint/100）-reward
	 */
	@Column(name = "cost")
	private Double cost;
	/**
	 * @ 结算价=支付机票款（订单那边有个计算公式）+基建+燃油/个
	 */
	@Column(name = "payPirce")
	private Double payPirce;
	/**
	 * @燃油
	 */
	@Column(name = "oilFee")
	private Double oilFee;
	/**
	 * @基建
	 */
	@Column(name = "taxFee")
	private Double taxFee;
	/**
	 * 销售价 合计
	 */
	@Column(name = "salePrice")
	private Double salePrice;

	/**
	 * 利润 =销售价salePrice-
	 * （printPrice*（1-（backPoint+afterPoint+stickingPoint）/100）
	 * -reward+oliFee+tax）*人数
	 */
	@Column(name = "lr")
	private Double lr;
	/**
	 * @ 结算总价（所有人支付总价） （price+tax）*人数
	 */
	@Column(name = "allprice")
	private Double allprice;

	/**
	 * 承运 CZ
	 */
	@Column(name = "carrier", length = 10, nullable = false)
	private String carrier;

	public Set<GjBuyFlightEntity> getFlights() {
		return flights;
	}

	public void setFlights(Set<GjBuyFlightEntity> flights) {
		this.flights = flights;
	}

	public Set<GjBuyPassengerEntity> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<GjBuyPassengerEntity> passengers) {
		this.passengers = passengers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkoid() {
		return fkoid;
	}

	public void setFkoid(String fkoid) {
		this.fkoid = fkoid;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	public String getSlfRemark() {
		return slfRemark;
	}

	public void setSlfRemark(String slfRemark) {
		this.slfRemark = slfRemark;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
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

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public String getPurchaseAccount() {
		return purchaseAccount;
	}

	public void setPurchaseAccount(String purchaseAccount) {
		this.purchaseAccount = purchaseAccount;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getDrawWay() {
		return drawWay;
	}

	public void setDrawWay(String drawWay) {
		this.drawWay = drawWay;
	}

	public Double getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(Double printPrice) {
		this.printPrice = printPrice;
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

	public Double getAllprice() {
		return allprice;
	}

	public void setAllprice(Double allprice) {
		this.allprice = allprice;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
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

	public Double getPayPirce() {
		return payPirce;
	}

	public void setPayPirce(Double payPirce) {
		this.payPirce = payPirce;
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

	public String getPnrTypeEn() {
		return pnrTypeEn;
	}

	public void setPnrTypeEn(String pnrTypeEn) {
		this.pnrTypeEn = pnrTypeEn;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getEtdzOffno() {
		return etdzOffno;
	}

	public void setEtdzOffno(String etdzOffno) {
		this.etdzOffno = etdzOffno;
	}

	public Integer getEtdzNo() {
		return etdzNo;
	}

	public void setEtdzNo(Integer etdzNo) {
		this.etdzNo = etdzNo;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Long getStamptime() {
		return stamptime;
	}

	public void setStamptime(Long stamptime) {
		this.stamptime = stamptime;
	}

}
