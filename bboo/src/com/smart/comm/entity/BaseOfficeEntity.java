package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "zs_base_office")
public class BaseOfficeEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5775478895674614787L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "mno", length = 40)
	private String mno;
	@Column(name = "office", length = 10)
	private String office;
	/**
	 * 打票机台数
	 */
	@Column(name = "etdzNum")
	private Integer etdzNum;
	/**
	 * 用途 描述 RT,AV,PAT,FD,FLT,GDS,SD,ETDZ
	 */
	@Column(name = "offtypes", length = 100)
	private String offtypes;
	@Column(name = "appkey", length = 50)
	private String appkey;
	/**
	 * 1 可用 2禁用
	 */
	@Column(name = "isu")
	private Integer isu;
	@Column(name = "ctime", length = 25)
	private String ctime;

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

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOfftypes() {
		return offtypes;
	}

	public void setOfftypes(String offtypes) {
		this.offtypes = offtypes;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Integer getIsu() {
		return isu;
	}

	public void setIsu(Integer isu) {
		this.isu = isu;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public Integer getEtdzNum() {
		return etdzNum;
	}

	public void setEtdzNum(Integer etdzNum) {
		this.etdzNum = etdzNum;
	}

}
