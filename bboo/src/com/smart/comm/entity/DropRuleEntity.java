package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_drop_rule")
public class DropRuleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "carrier", length = 10)
	private String carrier;
	@Column(name = "cabin", length = 10)
	private String cabin;
	/**
	 * 1 允许降舱 2不允许
	 */
	@Column(name = "isDrop")
	private Integer isDrop;

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

	public Integer getIsDrop() {
		return isDrop;
	}

	public void setIsDrop(Integer isDrop) {
		this.isDrop = isDrop;
	}

}
