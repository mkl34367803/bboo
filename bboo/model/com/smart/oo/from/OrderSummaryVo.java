package com.smart.oo.from;

import java.io.Serializable;
import java.util.List;


public class OrderSummaryVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 订单编号
	 */
	private String orderNo;
	/**
	 * 创单时间
	 */
	private String createTime;
	/**
	 * 航程类型
	 */
	private String flightType;
	/**
	 * 起飞到达时间，和起飞到达城市三字码
	 */
	private List<FlightVo> flightVos;
	/**
	 * pnr
	 */
	private String pnrCode;
	/**
	 * 订单总价
	 */
	private Double allprice;
	/**
	 * 乘客总人数
	 */
	private Integer passengerCount;
	/**
	 * 订单状态
	 */
	private String slfStatus;
	/**
	 * 锁定人
	 */
	private String lockUser;
	/**
	 * 分销商 T 淘宝 Q 去哪儿 C 携程 H 航班管家 K 酷讯 N 途牛 O 同程
	 */
	private String distributor;
	/**
	 * PNR NO位时间
	 */
	private String pnrNoTime;
	/**
	 * 留票备注
	 */
	private String leaveRemark;
	/**
	 * 携程的主订单号id
	 */
	private String billId;
	/**
	 * 分销商 对应的店铺名称
	 */
	private String shopName;

	/**
	 * 分销商店铺 中文
	 */
	private String shopNameCh;
	/**
	 * 改签订单的原订单
	 */
	private String oldOrderNo;
	/**
	 * 政策类型 1 NFD 2 清仓产品 3 商旅产品 5 中转产品 6 包机 7 切位 8 航司 VIP 卡 10000 普通 供应商 OpenAPI
	 * 对外接口规范 86 10001 规则运价 10 金牌 11 远期 13竞价 14特价 15特惠 16 让利 17 特殊 -1其它
	 */
	private Integer policyType;
	/**
	 *   1 自动出票 2初始状态
	 */
	private Integer isAuto;
	/**
	 * 服务说明
	 */
	private String statement;
	/**
	 * 0成人 1儿童 2婴儿 3成人+儿童 4成人+婴儿 5成人+儿童+婴儿 -1其他
	 */
	private Integer pnrType;
	/**
	 *  1有底舱 2正常（考虑到以后要排序，有底舱的排到前面）
	 */
	private Integer isLowspace;
	
	/*---------------------------------*/
	/**
	 * BaseRefund表的id
	 */
	private String brid;
	/**
	 * 分销商中文
	 */
	private String distributeCh;
	
	/**
	 * 是否是新预定的PNR 1新预定
	 */
	private Integer isNewCode;
	/**
	 * 航班剩余座位数
	 */
	private Integer seats;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFlightType() {
		return flightType;
	}
	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}
	public List<FlightVo> getFlightVos() {
		return flightVos;
	}
	public void setFlightVos(List<FlightVo> flightVos) {
		this.flightVos = flightVos;
	}
	public String getPnrCode() {
		return pnrCode;
	}
	public void setPnrCode(String pnrCode) {
		this.pnrCode = pnrCode;
	}
	public Double getAllprice() {
		return allprice;
	}
	public void setAllprice(Double allprice) {
		this.allprice = allprice;
	}
	public Integer getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}
	public String getSlfStatus() {
		return slfStatus;
	}
	public void setSlfStatus(String slfStatus) {
		this.slfStatus = slfStatus;
	}
	public String getLockUser() {
		return lockUser;
	}
	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
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
	public String getOldOrderNo() {
		return oldOrderNo;
	}
	public void setOldOrderNo(String oldOrderNo) {
		this.oldOrderNo = oldOrderNo;
	}
	public Integer getPolicyType() {
		return policyType;
	}
	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}
	public Integer getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(Integer isAuto) {
		this.isAuto = isAuto;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public Integer getPnrType() {
		return pnrType;
	}
	public void setPnrType(Integer pnrType) {
		this.pnrType = pnrType;
	}
	public Integer getIsLowspace() {
		return isLowspace;
	}
	public void setIsLowspace(Integer isLowspace) {
		this.isLowspace = isLowspace;
	}
	public String getBrid() {
		return brid;
	}
	public void setBrid(String brid) {
		this.brid = brid;
	}
	public String getDistributeCh() {
		return distributeCh;
	}
	public void setDistributeCh(String distributeCh) {
		this.distributeCh = distributeCh;
	}
	public Integer getIsNewCode() {
		return isNewCode;
	}
	public void setIsNewCode(Integer isNewCode) {
		this.isNewCode = isNewCode;
	}
	public Integer getSeats() {
		return seats;
	}
	public void setSeats(Integer seats) {
		this.seats = seats;
	}
}
