package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 航司航线数据表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_air_lines")
public class AirLineEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5135847580374642790L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "dep", length = 5)
	private String dep;
	@Column(name = "arr", length = 5)
	private String arr;
	@Column(name = "air", length = 5)
	private String air;
	/**
	 * 1可用 2禁用 3审核
	 */
	@Column(name = "isu")
	private Integer isu;
	/**
	 * 1国内 2国际
	 */
	@Column(name = "inat")
	private Integer inat;
	@Column(name = "ct", length = 25)
	private String ct;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getArr() {
		return arr;
	}

	public void setArr(String arr) {
		this.arr = arr;
	}

	public String getAir() {
		return air;
	}

	public void setAir(String air) {
		this.air = air;
	}

	public Integer getIsu() {
		return isu;
	}

	public void setIsu(Integer isu) {
		this.isu = isu;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public Integer getInat() {
		return inat;
	}

	public void setInat(Integer inat) {
		this.inat = inat;
	}

}
