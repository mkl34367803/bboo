package com.smart.comm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 字段表
 **/
@Entity
@Table(name="t_reportform")
public class ReportFormsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;//主键ID
	@Column(name = "mno", length = 40)
	private String mno;//商务号
	@Column(name="isused",length=2)
	private Integer isUsed;//是否可用 0不可用  1表示可用
	@Column(name="mnotype",length=30)
	private String mnoType;//商务类型(1:国内业务2：国际业务)
	@Column(name="createdate",length=40)
	private String createDate;//创建时间
	@Column(name="fieldname",length=40)
	private String fieldName;//字段名
	@Column(name="description",length=30)
	private String description;//字段描述
	
	public String getId() {
		return id;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
	public Integer getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMnoType() {
		return mnoType;
	}
	public void setMnoType(String mnoType) {
		this.mnoType = mnoType;
	}	
	

}
