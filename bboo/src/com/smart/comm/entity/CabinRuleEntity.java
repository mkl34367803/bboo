package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 舱位退改签设置表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_cabin_rule")
public class CabinRuleEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2610531709517647772L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "carrier", length = 10)
	private String carrier;
	/**
	 * 多个舱位用逗号隔开 例如：Y,B,C
	 */
	@Column(name = "cabin", length = 10)
	private String cabin;
	/**
	 * 0 表示不允许退票（改期），在退票规则中填入 0，表示不允许退票；在改期规则中 填入 0，表示不允许改期。 0-0-0，表示不收手续费。
	 * 10-0-20，表示起飞前的手续费比例是 10%，起飞后的手续费比例是 20%。 20-2-30，表示起飞前 2 小时之前的手续费比例是
	 * 20%，起飞前 2 小时之后的手续费 比例是 30%。 40-168-80-0-100，表示起飞前 168 小时之前的手续费比例是 40%，起飞前
	 * 168 小时 之内的手续费比例是 80%，起飞后的手续费比例是 100%。
	 */
	@Column(name = "returnRule", length = 30)
	private String returnRule;

	/**
	 * 0 表示不允许退票（改期），在退票规则中填入 0，表示不允许退票；在改期规则中 填入 0，表示不允许改期。 0-0-0，表示不收手续费。
	 * 10-0-20，表示起飞前的手续费比例是 10%，起飞后的手续费比例是 20%。 20-2-30，表示起飞前 2 小时之前的手续费比例是
	 * 20%，起飞前 2 小时之后的手续费 比例是 30%。 40-168-80-0-100，表示起飞前 168 小时之前的手续费比例是 40%，起飞前
	 * 168 小时 之内的手续费比例是 80%，起飞后的手续费比例是 100%。
	 */
	@Column(name = "changeRule", length = 30)
	private String changeRule;
	/**
	 * 是，允许签转 否，不允许签转 默认为“否
	 */
	@Column(name = "endorsement", length = 30)
	private String endorsement;

	/**
	 * 0 当天出票可以作废 1当天出票当天起飞以外航班可以作废 2当天出票1天以外的航班可以作废 -1 不允许作废
	 */
	@Column(name = "voidDay")
	private Integer voidDay;

	/**
	 * 有效期开始 （表单不填写 默认 SET 2000-12-30）
	 */
	@Column(name = "startValidity", length = 25)
	private String startValidity;
	/**
	 * 有效期截止 为空的话 默认 2999-12-30
	 */
	@Column(name = "endValidity", length = 25)
	private String endValidity;

	/**
	 * 最后一次操作人
	 */
	@Column(name = "lastOperator", length = 25)
	private String lastOperator;
	/**
	 * 录入时间
	 */
	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getReturnRule() {
		return returnRule;
	}

	public void setReturnRule(String returnRule) {
		this.returnRule = returnRule;
	}

	public String getChangeRule() {
		return changeRule;
	}

	public void setChangeRule(String changeRule) {
		this.changeRule = changeRule;
	}

	public String getEndorsement() {
		return endorsement;
	}

	public void setEndorsement(String endorsement) {
		this.endorsement = endorsement;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartValidity() {
		return startValidity;
	}

	public void setStartValidity(String startValidity) {
		this.startValidity = startValidity;
	}

	public String getEndValidity() {
		return endValidity;
	}

	public void setEndValidity(String endValidity) {
		this.endValidity = endValidity;
	}

	public String getLastOperator() {
		return lastOperator;
	}

	public void setLastOperator(String lastOperator) {
		this.lastOperator = lastOperator;
	}

	public Integer getVoidDay() {
		return voidDay;
	}

	public void setVoidDay(Integer voidDay) {
		this.voidDay = voidDay;
	}

}
