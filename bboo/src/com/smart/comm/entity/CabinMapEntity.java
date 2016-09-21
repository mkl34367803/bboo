package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 共享航班仓位设置表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_cabin_map")
public class CabinMapEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3811643649461439118L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "carrier", length = 10)
	private String carrier;
	@Column(name = "cabin", length = 10)
	private String cabin;
	@Column(name = "shareCode", length = 10)
	private String shareCode;
	@Column(name = "shareCabin", length = 10)
	private String shareCabin;
	@Column(name = "shareNums", length = 200)
	private String shareNums;
	@Column(name = "depCode", length = 100)
	private String depCode;
	@Column(name = "arrCode", length = 100)
	private String arrCode;
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

	public String getShareCode() {
		return shareCode;
	}

	public void setShareCode(String shareCode) {
		this.shareCode = shareCode;
	}

	public String getShareCabin() {
		return shareCabin;
	}

	public void setShareCabin(String shareCabin) {
		this.shareCabin = shareCabin;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getShareNums() {
		return shareNums;
	}

	public void setShareNums(String shareNums) {
		this.shareNums = shareNums;
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

}
