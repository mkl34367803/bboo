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

/**
 * 国际订单表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_gj_saleorder")
public class GjSaleOrderEntity extends BBOOObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3350344655080821835L;

	@OneToMany(targetEntity = GjSaleFlightEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	@OrderBy("id asc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<GjSaleFlightEntity> flights = new HashSet<GjSaleFlightEntity>(0);
	@OneToMany(targetEntity = GjSalePassengerEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	@OrderBy("id asc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<GjSalePassengerEntity> passengers = new HashSet<GjSalePassengerEntity>(
			0);
	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 拆单订单
	 */
	@Column(name = "isSplit")
	private Boolean isSplit;

	// @Temporal(TemporalType.TIMESTAMP)
	// @Transient
	@Column(name = "timestamp", columnDefinition = "timestamp", insertable = false, updatable = false)
	private String timestamp;

	@Column(name = "stamptime", insertable = false)
	private Long stamptime = System.currentTimeMillis();
	/**
	 * 分销平台对应的订单ID 可以为空
	 */
	@Column(name = "extOid", length = 55)
	private String extOid;
	/**
	 * B2C网站入库号
	 */
	@Column(name = "extOrderID", length = 55)
	private String extOrderID;

	/**
	 * 订单号
	 */
	@Column(name = "orderNo", length = 55)
	private String orderNo;

	/**
	 * 子订单号
	 */
	@Column(name = "billId", length = 55)
	private String billId;
	/**
	 * 改签订单的原订单
	 */
	@Column(name = "oldOrderNo", length = 55)
	private String oldOrderNo;

	@Column(name = "departureDate", length = 15)
	private String departureDate;

	@Column(name = "departureTime", length = 10)
	private String departureTime;

	/**
	 * 航程类型 S：单程 D：往返 T其它 L 联程 X 多程
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
	 * 用户中心会员昵称
	 */
	@Column(name = "userName", length = 30)
	private String userName;
	/**
	 * 航段数目
	 */
	@Column(name = "airlineCount")
	private Integer airlineCount;
	/**
	 * 儿童数目@拆分订单时要变化
	 */
	@Column(name = "childrenCount")
	private Integer childrenCount;
	/**
	 * 成人数目@拆分订单时要变化
	 */
	@Column(name = "adultsCount")
	private Integer adultsCount;
	/**
	 * 乘客总数@拆分订单时要变化
	 */
	@Column(name = "passengerCount")
	private Integer passengerCount;
	/**
	 * 成人价（注意这里是销售价）
	 */
	@Column(name = "adultPrice")
	private Double adultPrice;
	/**
	 * 成人税
	 */
	@Column(name = "adultTax")
	private Double adultTax;
	/**
	 * 儿童价
	 */
	@Column(name = "childPrice")
	private Double childPrice;
	/**
	 * 儿童税
	 */
	@Column(name = "childTax")
	private Double childTax;
	/**
	 * 订单总价旧值
	 */
	@Column(name = "oldAllprice")
	private Double oldAllprice;
	/**
	 * 订单总价@拆分订单时要变化
	 */
	@Column(name = "allprice")
	private Double allprice;
	/**
	 * 未支付价格
	 */
	@Column(name = "noPay")
	private Double noPay;
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
	 * 销售政策ID
	 */
	@Column(name = "policyId", length = 55)
	private String policyId;
	/**
	 * 销售政策Code
	 */
	@Column(name = "policyCode", length = 55)
	private String policyCode;

	/**
	 * 出票紧急度 1、临近转出时间 （距离转出小于30分钟） 2、催出票 （urgencyTimes>0） 3、AV舱位不足5个
	 * (由于前段数据不足，故暂时废弃) 4、临近PNR ADTK （距离ADTK大于3小时） 5、出票超时长规范 （进入超过1小时） -1其它
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
	 * 订单来源 self 普通政策 qfare 加价政策 spec 特殊政策 othe 其它 mobile.app.iphone 手机移动端 WEB
	 * PC端
	 */
	@Column(name = "source", length = 50)
	private String source;
	/**
	 * PNR
	 */
	@Column(name = "pnrCode", length = 20)
	private String pnrCode;
	/**
	 * 是否是新预定的PNR 1新预定
	 */
	@Column(name = "isNewCode")
	private Integer isNewCode;

	/**
	 * PNR 大编
	 */
	@Column(name = "pnrCodeBig", length = 10)
	private String pnrCodeBig;

	/**
	 * 记录状态 0 PNR当前有效 1 PNR清理成功 2 PNR清理失败
	 */
	@Column(name = "pnrStatus", length = 30)
	private String pnrStatus;
	/**
	 * 是否需要支付校验
	 */
	@Column(name = "orderNeedConfirm", length = 10)
	private String orderNeedConfirm;
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
	 * 行李额
	 */
	@Column(name = "luggageRule", length = 500)
	private String luggageRule;
	/**
	 * 其他说明
	 */
	@Column(name = "statement", length = 1000)
	private String statement;
	/**
	 * 支付方式 10 快钱非担保 2 支付宝非担保 1 财付通非担保 11 快钱担保 12 支付宝担保 13 财付通担保 -1其它
	 */
	@Column(name = "payWay", length = 5)
	private String payWay;
	/**
	 * 支付交易流水号
	 */
	@Column(name = "transactionNo", length = 55)
	private String transactionNo;
	/**
	 * 资金解冻状态
	 */
	@Column(name = "fzstatus", length = 10)
	private String fzstatus;
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
	 * 联系人手机
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
	 * 用户备注
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
	/**
	 * 资金解冻时间
	 */
	@Column(name = "unfzTime", length = 30)
	private String unfzTime;
	/**
	 * 操作员
	 */
	@Column(name = "operator", length = 30)
	private String operator;
	/**
	 * 航程简述 100长度
	 */
	@Column(name = "orderDesc", length = 100)
	private String orderDesc;

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
	@Column(name = "slfAddtime", length = 30)
	private String slfAddtime;

	/**
	 * 锁定操作人
	 */
	@Column(name = "lockUser", length = 30)
	private String lockUser;

	/**
	 * 历史PNR PNR,PNR,PNR
	 */
	@Column(name = "oldPnrCode", length = 55)
	private String oldPnrCode;

	/**
	 * 销售的PNR
	 */
	@Column(name = "salePnrCode", length = 55)
	private String salePnrCode;

	/**
	 * SELF 备注
	 */
	@Column(name = "slfRemark", length = 300)
	private String slfRemark;

	/**
	 * 航班类型N：国内 I：国际
	 */
	@Column(name = "flightClass", length = 3)
	private String flightClass;

	/**
	 * 订单分离等操作后的关联的原始订单ID (分离订单，新 ID生成规则，基于乘客分离 ID+P 基于航段分离 ID+F 基于换开分离 ID+O)
	 */
	@Column(name = "oldId", length = 55)
	private String oldId;

	/**
	 * 商户号
	 */
	@Column(name = "mno", length = 45)
	private String mno;

	/**
	 * 账号信息 账号|密码|KEY|...
	 */
	@Column(name = "accountInfo", length = 55)
	private String accountInfo;

	/**
	 * 账户表ID
	 */
	@Column(name = "accountid")
	private Integer accountid;

	/**
	 * 分销商 对应的店铺名称
	 */
	@Column(name = "shopName", length = 20)
	private String shopName;

	/**
	 * 分销商店铺 中文
	 */
	@Column(name = "shopNameCh", length = 30)
	private String shopNameCh;

	/**
	 * 分销商 T 淘宝 Q 去哪儿 C 携程 H 航班管家 K 酷讯 N 途牛 O 同程
	 */
	@Column(name = "distributor", length = 3)
	private String distributor;

	/**
	 * 分销商 中文
	 */
	@Column(name = "distributorCh", length = 15)
	private String distributorCh;

	/**
	 * 平台网站或者域名
	 */
	@Column(name = "url", length = 30)
	private String url;

	/**
	 * 0成人 1儿童 2婴儿 3成人+儿童 4成人+婴儿 5成人+儿童+婴儿 -1其他
	 */
	@Column(name = "pnrType", length = 3)
	private Integer pnrType;

	/**
	 * Together
	 */
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
	 * 航班座位数 描述
	 */
	@Column(name = "seatsDes", length = 55)
	private String seatsDes;
	/**
	 * 航班剩余座位数
	 */
	@Column(name = "seats", length = 3)
	private Integer seats;

	/**
	 * 0 正常 1换开新订单 2已经换开
	 */
	@Column(name = "openStatus", length = 3)
	private Integer openStatus;

	/**
	 * 换票描述 K 允许换开 N 不允许
	 */
	@Column(name = "openDes", length = 3)
	private String openDes;

	/**
	 * 换票上一次采购价 （票款+税费） 合计
	 */
	@Column(name = "buyPrice")
	private Double buyPrice;

	/**
	 * 
	 * 政策类型 1 NFD 2 清仓产品 3 商旅产品 5 中转产品 6 包机 7 切位 8 航司VIP卡 10000 普通 10001 规则运价 10
	 * 金牌 11 远期 13竞价 14特价 15特惠 16 让利 17 特殊 30 优选 31 立减 -1其它
	 * 
	 * 10001 规则运价 10002 公布运价 10003 私有运价 10004 供应商直连 10005 申请 10006 包机/切位 10007
	 * 直销 10008 特惠 10009 固定 K 位 10010 清仓产品 10011 公布转私有 10021 包机/切位 10022 申
	 */
	@Column(name = "policyType")
	private Integer policyType;

	/**
	 * 产品类型 中文 往返预付 往返普通 预付产品 包机切位 新包机切位 特价产品 全局产品 普通产品 申请产品 抢单
	 */
	@Column(name = "productType", length = 50)
	private String productType;
	/**
	 * 国内： AirLineMarketing:航司直销 PriorityPackage:优选套餐 BusinessPriority:商务优选
	 * TravelPackage:旅行套餐 国际： Prioritizing:商务优选 Exclusive:旅行套餐
	 */
	@Column(name = "saleType", length = 30)
	private String saleType;
	/**
	 * 旅行代码
	 */
	@Column(name = "specialCode", length = 100)
	private String specialCode;
	/**
	 * 运价基础
	 */
	@Column(name = "fareBasis", length = 50)
	private String fareBasis;

	/**
	 * PNR NO位时间
	 */
	@Column(name = "pnrNoTime", length = 25)
	private String pnrNoTime;

	/**
	 * 留票备注
	 */
	@Column(name = "leaveRemark", length = 100)
	private String leaveRemark;

	/**
	 * 最后一次获取政策时间
	 */
	@Column(name = "gettime", length = 25)
	private String gettime;
	/**
	 * 1 自动出票 2初始状态
	 */
	@Column(name = "isAuto")
	private Integer isAuto;
	/**
	 * 1政策已经缓存 2初始状态
	 */
	@Column(name = "isGet")
	private Integer isGet;
	/**
	 * 承运 CZ
	 */
	@Column(name = "carrier", length = 10, nullable = false)
	private String carrier;

	/**
	 * 1有底舱 2正常（考虑到以后要排序，有底舱的排到前面）
	 */
	@Column(name = "isLowspace")
	private Integer isLowspace;

	/***********************************/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
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

	public String getLockUser() {
		return lockUser;
	}

	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}

	public String getOldPnrCode() {
		return oldPnrCode;
	}

	public void setOldPnrCode(String oldPnrCode) {
		this.oldPnrCode = oldPnrCode;
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

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
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

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
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

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getPolicyType() {
		return policyType;
	}

	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}

	public String getExtOrderID() {
		return extOrderID;
	}

	public void setExtOrderID(String extOrderID) {
		this.extOrderID = extOrderID;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPnrCodeBig() {
		return pnrCodeBig;
	}

	public void setPnrCodeBig(String pnrCodeBig) {
		this.pnrCodeBig = pnrCodeBig;
	}

	public Set<GjSaleFlightEntity> getFlights() {
		return flights;
	}

	public void setFlights(Set<GjSaleFlightEntity> flights) {
		this.flights = flights;
	}

	public Set<GjSalePassengerEntity> getPassengers() {
		return passengers;
	}

	public void setPassengers(Set<GjSalePassengerEntity> passengers) {
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

	@Override
	public String toString() {
		return "GjSaleOrderEntity [flights=" + flights + ", passengers="
				+ passengers + ", id=" + id + ", extOid=" + extOid
				+ ", extOrderID=" + extOrderID + ", orderNo=" + orderNo
				+ ", billId=" + billId + ", oldOrderNo=" + oldOrderNo
				+ ", flightType=" + flightType + ", status=" + status
				+ ", userName=" + userName + ", airlineCount=" + airlineCount
				+ ", childrenCount=" + childrenCount + ", adultsCount="
				+ adultsCount + ", passengerCount=" + passengerCount
				+ ", adultPrice=" + adultPrice + ", adultTax=" + adultTax
				+ ", childPrice=" + childPrice + ", childTax=" + childTax
				+ ", oldAllprice=" + oldAllprice + ", allprice=" + allprice
				+ ", noPay=" + noPay + ", bxFee=" + bxFee + ", bxCount="
				+ bxCount + ", policyId=" + policyId + ", policyCode="
				+ policyCode + ", urgency=" + urgency + ", urgeTimes="
				+ urgeTimes + ", productSource=" + productSource + ", source="
				+ source + ", pnrCode=" + pnrCode + ", pnrCodeBig="
				+ pnrCodeBig + ", pnrStatus=" + pnrStatus
				+ ", orderNeedConfirm=" + orderNeedConfirm
				+ ", returnTicketRule=" + returnTicketRule
				+ ", changeDateRule=" + changeDateRule + ", luggageRule="
				+ luggageRule + ", statement=" + statement + ", payWay="
				+ payWay + ", transactionNo=" + transactionNo + ", fzstatus="
				+ fzstatus + ", refundCause=" + refundCause + ", contactName="
				+ contactName + ", contactMob=" + contactMob + ", contactTel="
				+ contactTel + ", contactEmail=" + contactEmail
				+ ", journeySheet=" + journeySheet + ", journeySheetNo="
				+ journeySheetNo + ", journeySheetWay=" + journeySheetWay
				+ ", journeySheetPrice=" + journeySheetPrice
				+ ", journeySheetStatu=" + journeySheetStatu + ", receiver="
				+ receiver + ", postcode=" + postcode + ", address=" + address
				+ ", remark=" + remark + ", createTime=" + createTime
				+ ", lastUpdated=" + lastUpdated + ", ticketDate=" + ticketDate
				+ ", unfzTime=" + unfzTime + ", operator=" + operator
				+ ", orderDesc=" + orderDesc + ", currency=" + currency
				+ ", slfStatus=" + slfStatus + ", slfAddtime=" + slfAddtime
				+ ", lockUser=" + lockUser + ", oldPnrCode=" + oldPnrCode
				+ ", slfRemark=" + slfRemark + ", flightClass=" + flightClass
				+ ", oldId=" + oldId + ", mno=" + mno + ", accountInfo="
				+ accountInfo + ", shopName=" + shopName + ", shopNameCh="
				+ shopNameCh + ", distributor=" + distributor
				+ ", distributorCh=" + distributorCh + ", url=" + url
				+ ", pnrType=" + pnrType + ", pnrText=" + pnrText + ", avText="
				+ avText + ", cabinDes=" + cabinDes + ", pnrOffNo=" + pnrOffNo
				+ ", rtOffno=" + rtOffno + ", seatsDes=" + seatsDes
				+ ", seats=" + seats + ", openStatus=" + openStatus
				+ ", openDes=" + openDes + ", buyPrice=" + buyPrice
				+ ", policyType=" + policyType + ", specialCode=" + specialCode
				+ ", fareBasis=" + fareBasis + "]";
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getPnrNoTime() {
		return pnrNoTime;
	}

	public void setPnrNoTime(String pnrNoTime) {
		this.pnrNoTime = pnrNoTime;
	}

	public String getLeaveRemark() {
		return leaveRemark;
	}

	public void setLeaveRemark(String leaveRemark) {
		this.leaveRemark = leaveRemark;
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

	public String getGettime() {
		return gettime;
	}

	public void setGettime(String gettime) {
		this.gettime = gettime;
	}

	public Integer getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Integer isAuto) {
		this.isAuto = isAuto;
	}

	public Integer getIsGet() {
		return isGet;
	}

	public void setIsGet(Integer isGet) {
		this.isGet = isGet;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Integer getIsLowspace() {
		return isLowspace;
	}

	public void setIsLowspace(Integer isLowspace) {
		this.isLowspace = isLowspace;
	}

	public Integer getIsNewCode() {
		return isNewCode;
	}

	public void setIsNewCode(Integer isNewCode) {
		this.isNewCode = isNewCode;
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

	public Boolean getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(Boolean isSplit) {
		this.isSplit = isSplit;
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

}
