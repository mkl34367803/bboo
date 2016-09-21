package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_ticket_file")
public class TicketFileEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5211734975342778211L;
	/**
	 * 
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	@Column(name = "mno", length = 40)
	private String mno;
	@Column(name = "fileName", length = 100)
	private String fileName;
	@Column(name = "filePath", length = 150)
	private String filePath;
	@Column(name = "fileSize")
	private Long fileSize;
	@Column(name = "count")
	private Integer count;
	/**
	 * 导入数据情况说明
	 */
	@Column(name = "dataSpecification", length = 100)
	private String dataSpecification;
	/**
	 * 查询数据情况说明
	 */
	@Column(name = "scanSpecification", length = 100)
	private String scanSpecification;

	@Column(name = "operator", length = 35)
	private String operator;
	/**
	 * 文件号
	 */
	@Column(name = "fileno", length = 50)
	private String fileno;// 文件号
	/**
	 * 0等待查询 1查询中 2查询完成
	 */
	@Column(name = "scanStatus")
	private Integer scanStatus;
	/**
	 * 创建时间
	 */
	@Column(name = "lastTime", length = 25)
	private String lastTime;
	/**
	 * 创建时间
	 */
	@Column(name = "createTime", length = 25)
	private String createTime;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public Integer getScanStatus() {
		return scanStatus;
	}

	public void setScanStatus(Integer scanStatus) {
		this.scanStatus = scanStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDataSpecification() {
		return dataSpecification;
	}

	public void setDataSpecification(String dataSpecification) {
		this.dataSpecification = dataSpecification;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getScanSpecification() {
		return scanSpecification;
	}

	public void setScanSpecification(String scanSpecification) {
		this.scanSpecification = scanSpecification;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
}
