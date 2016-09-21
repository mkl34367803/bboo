package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 记录系统操作日志
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_sys_log")
public class SysLogEntity {

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	@Column(name = "mno", length = 40)
	private String mno;
	/**
	 * ORDER_LOG
	 */
	@Column(name = "logType", length = 50)
	private String logType;
	@Column(name = "foreginKey", length = 60)
	private String foreginKey;
	/**
	 * 格式 ： 用户名：[动作]内容 （例如：张三：[出票]执行了导单锁单操作，订单被导入订单系统 ；
	 * 王五：[出票]执行了出票操作，订单状态待出票改成出票中）
	 */
	@Column(name = "contents", length = 300)
	private String contents;
	@Column(name = "visitip", length = 20)
	private String visitip;
	@Column(name = "userName", length = 35)
	private String userName;
	@Column(name = "operatime", length = 30)
	private String operatime;

	public String getId() {
		return id;
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

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getForeginKey() {
		return foreginKey;
	}

	public void setForeginKey(String foreginKey) {
		this.foreginKey = foreginKey;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getVisitip() {
		return visitip;
	}

	public void setVisitip(String visitip) {
		this.visitip = visitip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperatime() {
		return operatime;
	}

	public void setOperatime(String operatime) {
		this.operatime = operatime;
	}

}
