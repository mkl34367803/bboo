package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 航司航班信息表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_air_flight")
public class AirFlightEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2577905099365060082L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "indexkey", length = 25)
	private String indexkey;
	@Column(name = "dep", length = 5)
	private String dep;
	@Column(name = "arr", length = 5)
	private String arr;
	@Column(name = "air", length = 5)
	private String air;
	@Column(name = "fno", length = 10)
	private String fno;
	@Column(name = "sno", length = 10)
	private String sno;
	@Column(name = "dept", length = 10)
	private String dept;
	@Column(name = "arrt", length = 10)
	private String arrt;
	@Column(name = "istop", length = 10)
	private String istop;
	/**
	 * A,B,C,D,E,F,G,H,I,J,K,L,M,N
	 */
	@Column(name = "cabins", length = 100)
	private String cabins;
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

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public Integer getIsu() {
		return isu;
	}

	public void setIsu(Integer isu) {
		this.isu = isu;
	}

	public Integer getInat() {
		return inat;
	}

	public void setInat(Integer inat) {
		this.inat = inat;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getIndexkey() {
		return indexkey;
	}

	public void setIndexkey(String indexkey) {
		this.indexkey = indexkey;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getArrt() {
		return arrt;
	}

	public void setArrt(String arrt) {
		this.arrt = arrt;
	}

	public String getCabins() {
		return cabins;
	}

	public void setCabins(String cabins) {
		this.cabins = cabins;
	}

	public String getIstop() {
		return istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}

}
