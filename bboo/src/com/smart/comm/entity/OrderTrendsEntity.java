package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 销售订单的航班动态信息
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_order_trends")
public class OrderTrendsEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -48137380878095397L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	/**
	 * 航班表ID号
	 */
	@Column(name = "fkid", length = 55)
	private String fkid;
	@Column(name = "flightNo", length = 8)
	private String flightNo;
	@Column(name = "depCode", length = 5)
	private String depCode;
	@Column(name = "arrCode", length = 5)
	private String arrCode;
	@Column(name = "depDate", length = 12)
	private String depDate;
	@Column(name = "planDepTime", length = 10)
	private String planDepTime;
	@Column(name = "planArrTime", length = 10)
	private String planArrTime;
	@Column(name = "actDepTime", length = 10)
	private String actDepTime;
	@Column(name = "actArrTime", length = 10)
	private String actArrTime;
	@Column(name = "status")
	private Integer status;// 1计划 2延误 3取消 4起飞 5到达 6其它
	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFkid() {
		return fkid;
	}

	public void setFkid(String fkid) {
		this.fkid = fkid;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDepCode() {
		return depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getArrCode() {
		return arrCode;
	}

	public void setArrCode(String arrCode) {
		this.arrCode = arrCode;
	}

	public String getDepDate() {
		return depDate;
	}

	public void setDepDate(String depDate) {
		this.depDate = depDate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
