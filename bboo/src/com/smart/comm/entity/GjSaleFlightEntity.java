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
 * 销售航班信息表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_gj_saleflight")
public class GjSaleFlightEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4285735025951203292L;

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
	 * 航程序号
	 */
	@Column(name = "sequence")
	private Integer sequence;

	/**
	 * 承运 CZ
	 */
	@Column(name = "carrier", length = 3)
	private String carrier;

	/**
	 * 航司中文 南方航空
	 */
	@Column(name = "airCodeCh", length = 35)
	private String airCodeCh;

	/**
	 * 航班号
	 */
	@Column(name = "flightNum", length = 10)
	private String flightNum;

	/**
	 * 共享航班号
	 */
	@Column(name = "shareNum", length = 10)
	private String shareNum;

	/**
	 * 是否共享航班 Y:是,N:否 T其它
	 */
	@Column(name = "isShared", length = 3)
	private String isShared;
	/**
	 * 舱位
	 */
	@Column(name = "cabin", length = 5)
	private String cabin;
	/**
	 * 舱位剩余座位数 默认 -1
	 */
	@Column(name = "cabinNum", length = 5)
	private Integer cabinNum;
	/**
	 * 舱位等级
	 */
	@Column(name = "subClass", length = 15)
	private String subClass;
	/**
	 * 儿童舱位
	 */
	@Column(name = "chdCabin", length = 15)
	private String chdCabin;
	/**
	 * 机型
	 */
	@Column(name = "planeModule", length = 20)
	private String planeModule;

	/**
	 * 航站楼
	 */
	@Column(name = "terminal", length = 20)
	private String terminal;
	/**
	 * 出发机场三字码
	 */
	@Column(name = "depAircode", length = 5)
	private String depAircode;
	/**
	 * 中文
	 */
	@Column(name = "depAircodeCh", length = 45)
	private String depAircodeCh;
	/**
	 * 到达机场三字码
	 */
	@Column(name = "arrAircode", length = 5)
	private String arrAircode;
	/**
	 * 到达机场三字码 中文
	 */
	@Column(name = "arrAircodeCh", length = 45)
	private String arrAircodeCh;
	/**
	 * 出发日期
	 */
	@Column(name = "departureDate", length = 15)
	private String departureDate;
	/**
	 * 起飞时间
	 */
	@Column(name = "departureTime", length = 10)
	private String departureTime;
	/**
	 * 到达日期
	 */
	@Column(name = "arrivalDate", length = 15)
	private String arrivalDate;
	/**
	 * 降落时间
	 */
	@Column(name = "arrivalTime", length = 10)
	private String arrivalTime;

	/**
	 * 航段类型1 去程 2 回程
	 */
	@Column(name = "segmentType")
	private Integer segmentType;
	/**
	 * 票面价 是指航段分段价格，也是验价 所使用的价格
	 */
	@Column(name = "printPrice")
	private Double printPrice;
	/**
	 * AV 文本信息
	 */
	@Column(name = "avText", length = 500)
	private String avText;
	/**
	 * AV时间
	 */
	@Column(name = "avTime", length = 30)
	private String avTime;
	/**
	 * 低仓位 例如： QA
	 */
	@Column(name = "lowspace", length = 30)
	private String lowspace;
	/**
	 * 创建时间
	 */
	@Column(name = "addTime", length = 30)
	private String addTime;

	/************************************/
	/**
	 * PAT 票面价 是指航段分段价格，也是验价 所使用的价格
	 */
	@Column(name = "selfPrintPrice")
	private Double selfPrintPrice;
	/**
	 * 实际出票舱位
	 */
	@Column(name = "actCabin", length = 10)
	private String actCabin;
	/**
	 * 实际出票航班号
	 */
	@Column(name = "actFltno", length = 10)
	private String actFltno;
	/**
	 * 实际出票起飞日期
	 */
	@Column(name = "actDept", length = 30)
	private String actDept;
	/**
	 * RT PNR PAT 价格
	 */
	@Column(name = "actPrice", length = 30)
	private String actPrice;
	/**
	 * RT PNR PAT 价格
	 */
	@Column(name = "actTax", length = 30)
	private String actTax;
	/**
	 * RT PNR PAT 价格
	 */
	@Column(name = "actYq", length = 30)
	private String actYq;
	/**
	 * 有几个价格
	 */
	@Column(name = "priceCount")
	private Integer priceCount;

	/************************************/
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

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public String getPlaneModule() {
		return planeModule;
	}

	public void setPlaneModule(String planeModule) {
		this.planeModule = planeModule;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
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

	public Double getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(Double printPrice) {
		this.printPrice = printPrice;
	}

	public String getAvText() {
		return avText;
	}

	public void setAvText(String avText) {
		this.avText = avText;
	}

	public String getAvTime() {
		return avTime;
	}

	public void setAvTime(String avTime) {
		this.avTime = avTime;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getCabinNum() {
		return cabinNum;
	}

	public void setCabinNum(Integer cabinNum) {
		this.cabinNum = cabinNum;
	}

	public Double getSelfPrintPrice() {
		return selfPrintPrice;
	}

	public void setSelfPrintPrice(Double selfPrintPrice) {
		this.selfPrintPrice = selfPrintPrice;
	}

	public String getAirCodeCh() {
		return airCodeCh;
	}

	public void setAirCodeCh(String airCodeCh) {
		this.airCodeCh = airCodeCh;
	}

	public String getDepAircodeCh() {
		return depAircodeCh;
	}

	public void setDepAircodeCh(String depAircodeCh) {
		this.depAircodeCh = depAircodeCh;
	}

	public String getArrAircodeCh() {
		return arrAircodeCh;
	}

	public void setArrAircodeCh(String arrAircodeCh) {
		this.arrAircodeCh = arrAircodeCh;
	}

	public GjSaleOrderEntity getOrder() {
		return order;
	}

	public void setOrder(GjSaleOrderEntity order) {
		this.order = order;
	}

	public String getChdCabin() {
		return chdCabin;
	}

	public void setChdCabin(String chdCabin) {
		this.chdCabin = chdCabin;
	}

	public String getIsShared() {
		return isShared;
	}

	public void setIsShared(String isShared) {
		this.isShared = isShared;
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

	public String getActDept() {
		return actDept;
	}

	public void setActDept(String actDept) {
		this.actDept = actDept;
	}

	public String getActPrice() {
		return actPrice;
	}

	public void setActPrice(String actPrice) {
		this.actPrice = actPrice;
	}

	public String getActTax() {
		return actTax;
	}

	public void setActTax(String actTax) {
		this.actTax = actTax;
	}

	public String getActYq() {
		return actYq;
	}

	public void setActYq(String actYq) {
		this.actYq = actYq;
	}

	public Integer getPriceCount() {
		return priceCount;
	}

	public void setPriceCount(Integer priceCount) {
		this.priceCount = priceCount;
	}

	public String getLowspace() {
		return lowspace;
	}

	public void setLowspace(String lowspace) {
		this.lowspace = lowspace;
	}

}
