package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_ticket_state")
public class TicketStateEntity extends BBOOObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8166317161278073916L;
	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 商户号
	 */
	@Column(name = "mno", length = 40)
	private String mno;
	/**
	 * 票号
	 */
	@Column(name = "no", length = 20)
	private String no;

	@Column(name = "certno", length = 25)
	private String certno;
	/**
	 * 订单号
	 */
	@Column(name = "oid", length = 50)
	private String oid;
	/**
	 * 导入 乘客名字
	 */
	@Column(name = "passengerName", length = 100)
	private String passengerName;
	/**
	 * 匹配 乘客名字2
	 */
	@Column(name = "passengerName2", length = 100)
	private String passengerName2;
	/**
	 * 导入备注信息
	 */
	@Column(name = "remark", length = 300)
	private String remark;
	/**
	 * 导入 起飞日期
	 */
	@Column(name = "takeOff", length = 25)
	private String takeOff;
	/**
	 * 导入 出票日期
	 */
	@Column(name = "outDate", length = 25)
	private String outDate;
	/**
	 * 文件号
	 */
	@Column(name = "fileno", length = 50)
	private String fileno;// 文件号
	/**
	 * 客票状态 OPEN,REFUND
	 */
	@Column(name = "state", length = 100)
	private String state;
	/**
	 * 查询票号状态返回的异常信息
	 */
	@Column(name = "messages", length = 1000)
	private String messages;
	/**
	 * 最后一次扫描时间
	 */
	@Column(name = "lastime", length = 25)
	private String lastime;

	/**
	 * 航班信息 fno,dep,arr,date,dtm,cabin|
	 */
	@Column(name = "fltinfo", length = 150)
	private String fltinfo;
	/**
	 * 查询状态 0 等待查询 1查询失败 2查询成功
	 */
	@Column(name = "queryok")
	private Integer queryok;
	/**
	 * 航班延误信息
	 */
	@Column(name = "delayInfo", length = 150)
	private String delayInfo;
	@Column(name = "planDepTime", length = 10)
	private String planDepTime;
	@Column(name = "planArrTime", length = 10)
	private String planArrTime;
	@Column(name = "actDepTime", length = 10)
	private String actDepTime;
	@Column(name = "actArrTime", length = 10)
	private String actArrTime;
	@Column(name = "ztms", length = 10)
	private String ztms;// 1计划 2延误 3取消 4起飞 5到达 6其它
	@Column(name = "ywtype")
	private Integer ywtype;// 1 延误可能性
	@Column(name = "delayState")
	private Integer delayState;// 1计划 2延误 3取消 4起飞 5到达 6其它
	/**
	 * 创建时间
	 */
	@Column(name = "ctm", length = 25)
	private String ctm;// 创建日期

	@Column(name = "plans", length = 10)
	private String plans;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassengerName2() {
		return passengerName2;
	}

	public void setPassengerName2(String passengerName2) {
		this.passengerName2 = passengerName2;
	}

	public String getTakeOff() {
		return takeOff;
	}

	public void setTakeOff(String takeOff) {
		this.takeOff = takeOff;
	}

	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastime() {
		return lastime;
	}

	public void setLastime(String lastime) {
		this.lastime = lastime;
	}

	public String getFltinfo() {
		return fltinfo;
	}

	public void setFltinfo(String fltinfo) {
		this.fltinfo = fltinfo;
	}

	public Integer getQueryok() {
		return queryok;
	}

	public void setQueryok(Integer queryok) {
		this.queryok = queryok;
	}

	public String getCtm() {
		return ctm;
	}

	public void setCtm(String ctm) {
		this.ctm = ctm;
	}

	public String getDelayInfo() {
		return delayInfo;
	}

	public void setDelayInfo(String delayInfo) {
		this.delayInfo = delayInfo;
	}

	public String getPlanDepTime() {
		return planDepTime;
	}

	public void setPlanDepTime(String planDepTime) {
		this.planDepTime = planDepTime;
	}

	public String getPlanArrTime() {
		return planArrTime;
	}

	public void setPlanArrTime(String planArrTime) {
		this.planArrTime = planArrTime;
	}

	public String getActDepTime() {
		return actDepTime;
	}

	public void setActDepTime(String actDepTime) {
		this.actDepTime = actDepTime;
	}

	public String getActArrTime() {
		return actArrTime;
	}

	public void setActArrTime(String actArrTime) {
		this.actArrTime = actArrTime;
	}

	public String getZtms() {
		return ztms;
	}

	public void setZtms(String ztms) {
		this.ztms = ztms;
	}

	public Integer getYwtype() {
		return ywtype;
	}

	public void setYwtype(Integer ywtype) {
		this.ywtype = ywtype;
	}

	public Integer getDelayState() {
		return delayState;
	}

	public void setDelayState(Integer delayState) {
		this.delayState = delayState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getCertno() {
		return certno;
	}

	public void setCertno(String certno) {
		this.certno = certno;
	}

	public String getPlans() {
		return plans;
	}

	public void setPlans(String plans) {
		this.plans = plans;
	}

}
