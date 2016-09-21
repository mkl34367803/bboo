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
 * 改签基础表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_base_change")
public class BaseChangeEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(targetEntity = ChangeEntity.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "base")
	@OrderBy("id asc")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Set<ChangeEntity> refunds = new HashSet<ChangeEntity>(0);
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 80)
	private String id;

	// @Temporal(TemporalType.TIMESTAMP)
	// @Transient
	@Column(name = "timestamp", columnDefinition = "timestamp", insertable = false, updatable = false)
	private String timestamp;

	@Column(name = "stamptime", insertable = false)
	private Long stamptime = System.currentTimeMillis();

	@Column(name = "fkid", length = 80, nullable = false)
	private String fkid;// 订单表ID
	/**
	 * SELF 订单状态 0 订座成功等待支付(人工) 1 支付成功等待出票 2 出票完成 5 出票中 12 订单取消 20 等待座位确认 30
	 * 退票申请中 31 退票完成等待退款 39 退款完成 40 改签申请中 42 改签完成 50 未出票申请退款 51 订座成功等待价格确认 60
	 * 支付待确认（支付失败） 61 暂缓出票（出票失败） 62 订单超时 -1其它 99 订座成功等待支付（系统） 70退票留票中
	 */
	@Column(name = "orderStatus", length = 10)
	private String orderStatus;
	@Column(name = "operator", length = 100)
	private String operator;// 操作人
	@Column(name = "remark", length = 500)
	private String remark;
	@Column(name = "state")
	private Integer state;// 1 改期 2 签转 3 退票重出
	@Column(name = "pcount")
	private Long pcount;// 改签的乘机人数 默认1
	@Column(name = "createTime", length = 25)
	private String createTime;// 三种解释 1客户提交退票时间 2提交航司退票时间 3航司退款时间

	public Set<ChangeEntity> getRefunds() {
		return refunds;
	}

	public void setRefunds(Set<ChangeEntity> refunds) {
		this.refunds = refunds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFkid() {
		return fkid;
	}

	public void setFkid(String fkid) {
		this.fkid = fkid;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getPcount() {
		return pcount;
	}

	public void setPcount(Long pcount) {
		this.pcount = pcount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
