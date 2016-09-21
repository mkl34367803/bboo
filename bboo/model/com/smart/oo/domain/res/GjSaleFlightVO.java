package com.smart.oo.domain.res;

import com.bbang.api.BbangObject;

/**
 * 销售航班信息表
 * 
 * @author eric
 * 
 */

public class GjSaleFlightVO extends BbangObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4285735025951203292L;

	/**
	 * 航程序号
	 */
	private Integer sequence;

	/**
	 * 承运 CZ
	 */
	private String carrier;

	/**
	 * 航司中文 南方航空
	 */
	private String airCodeCh;

	/**
	 * 航班号
	 */
	private String flightNum;

	/**
	 * 共享航班号
	 */
	private String shareNum;

	/**
	 * 是否共享航班 Y:是,N:否 T其它
	 */
	private String IsShared;
	/**
	 * 舱位
	 */
	private String cabin;
	/**
	 * 舱位剩余座位数 默认 -1
	 */
	private Integer cabinNum;
	/**
	 * 舱位等级
	 */
	private String subClass;

	private String chdCabin;

	/**
	 * 机型
	 */
	private String planeModule;

	/**
	 * 航站楼
	 */
	private String terminal;
	/**
	 * 出发机场三字码
	 */
	private String depAircode;
	/**
	 * 中文
	 */
	private String depAircodeCh;
	/**
	 * 到达机场三字码
	 */
	private String arrAircode;
	/**
	 * 到达机场三字码 中文
	 */
	private String arrAircodeCh;
	/**
	 * 出发日期
	 */
	private String departureDate;
	/**
	 * 起飞时间
	 */
	private String departureTime;
	/**
	 * 到达日期
	 */
	private String arrivalDate;
	/**
	 * 降落时间
	 */
	private String arrivalTime;

	/**
	 * 航段类型1 去程 2 回程
	 */
	private Integer segmentType;
	/**
	 * 票面价 是指航段分段价格，也是验价 所使用的价格
	 */
	private Double printPrice;
	/**
	 * AV 文本信息
	 */
	private String avText;
	/**
	 * AV时间
	 */
	private String avTime;
	/**
	 * 创建时间
	 */
	private String addTime;

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

	public String getShareNum() {
		return shareNum;
	}

	public void setShareNum(String shareNum) {
		this.shareNum = shareNum;
	}

	public String getIsShared() {
		return IsShared;
	}

	public void setIsShared(String isShared) {
		IsShared = isShared;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public Integer getCabinNum() {
		return cabinNum;
	}

	public void setCabinNum(Integer cabinNum) {
		this.cabinNum = cabinNum;
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

	public String getChdCabin() {
		return chdCabin;
	}

	public void setChdCabin(String chdCabin) {
		this.chdCabin = chdCabin;
	}

}
