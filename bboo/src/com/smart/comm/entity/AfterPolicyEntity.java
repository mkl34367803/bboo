package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_after_policy")
public class AfterPolicyEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4693333942856574399L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "mno", length = 40)
	private String mno;

	/**
	 * CP_517NA CP_BAITOUR CP_8000YI CP_51BOOK CP_19E CP_LH800 CP_AIR_B2B_EBAO
	 * CP_LOCALBSP CP_QUA （下拉菜单）
	 */
	@Column(name = "channel", length = 20)
	private String channel;

	@Column(name = "channelCh", length = 50)
	private String channelCh;

	/**
	 * JH_GW JH_DL 空表示适用全部 (下拉菜单)
	 */
	@Column(name = "productType", length = 20)
	private String productType;

	@Column(name = "productTypeCh", length = 50)
	private String productTypeCh;

	/**
	 * 空表示全部
	 */
	@Column(name = "carrier", length = 100)
	private String carrier;
	/**
	 * 后返
	 */
	@Column(name = "after")
	private Double after = 0d;
	/**
	 * 创建时间
	 */
	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Double getAfter() {
		return after;
	}

	public void setAfter(Double after) {
		this.after = after;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getChannelCh() {
		return channelCh;
	}

	public void setChannelCh(String channelCh) {
		this.channelCh = channelCh;
	}

	public String getProductTypeCh() {
		return productTypeCh;
	}

	public void setProductTypeCh(String productTypeCh) {
		this.productTypeCh = productTypeCh;
	}

}
