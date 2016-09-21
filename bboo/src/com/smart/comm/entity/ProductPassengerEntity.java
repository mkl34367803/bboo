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

@Entity
@Table(name = "t_product_passenger")
public class ProductPassengerEntity extends BBOOObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3810678254756394845L;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = ProductDataEntity.class, cascade = { CascadeType.ALL })
	@JoinColumn(name = "fkpid")
	private ProductDataEntity product;
	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	/**
	 * 注册表主键
	 */
	@Column(name = "fkpid", length = 55, insertable = false, updatable = false)
	private String fkpid;

	@Column(name = "psgName", length = 55)
	private String psgName;
	@Column(name = "certNo", length = 30)
	private String certNo;
	@Column(name = "psgType", length = 15)
	private String psgType;
	@Column(name = "birthday", length = 20)
	private String birthday;

	public String getPsgName() {
		return psgName;
	}

	public void setPsgName(String psgName) {
		this.psgName = psgName;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getPsgType() {
		return psgType;
	}

	public void setPsgType(String psgType) {
		this.psgType = psgType;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public ProductDataEntity getProduct() {
		return product;
	}

	public void setProduct(ProductDataEntity product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFkpid() {
		return fkpid;
	}

	public void setFkpid(String fkpid) {
		this.fkpid = fkpid;
	}

}
