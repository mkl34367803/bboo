package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 开关
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "zs_switch")
public class SwitchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "mno", length = 40)
	private String mno;
	@Column(name = "onoff")
	private Integer onoff; // 1 开启 0 关闭
	@Column(name = "validity", length = 25)
	private String validity;// 日期有效期
	@Column(name = "ctm", length = 25)
	private String ctm;// 创建日期
	/**
	 * QUNAR_XUNJIA_MASTER_ON 去哪儿询价总开关 CTRIP_XUNJIA_MASTER_ON 携程询价总开关
	 * TAOBAO_XUNJIA_MASTER_ON 淘宝询价总开关 QUNAR_I_SYNC_ORDER_ON 同步去哪儿国际订单
	 * CTRIP_I_SYNC_ORDER_ON TAOBAO_I_SYNC_ORDER_ON QUNAR_N_SYNC_ORDER_ON
	 * CTRIP_N_SYNC_ORDER_ON TAOBAO_N_SYNC_ORDER_ON
	 * 
	 */
	@Column(name = "mkey", length = 30)
	private String mkey;
	@Column(name = "describe", length = 100)
	private String describe;// 描述

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOnoff() {
		return onoff;
	}

	public void setOnoff(Integer onoff) {
		this.onoff = onoff;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getCtm() {
		return ctm;
	}

	public void setCtm(String ctm) {
		this.ctm = ctm;
	}

	public String getMkey() {
		return mkey;
	}

	public void setMkey(String mkey) {
		this.mkey = mkey;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

}
