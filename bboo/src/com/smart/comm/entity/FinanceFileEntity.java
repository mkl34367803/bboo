package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 财务导入报表文件
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_finance_file")
public class FinanceFileEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -534839818515800846L;
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

	@Column(name = "operator", length = 35)
	private String operator;
	/**
	 * 文件号
	 */
	@Column(name = "fileno", length = 50)
	private String fileno;// 文件号

	/**
	 * 文件类型 SALE_LR 销售利润报表
	 */
	@Column(name = "fileType", length = 30)
	private String fileType;// 文件类型

	/**
	 * 状态 预留字段
	 */
	@Column(name = "state")
	private String state;

	/**
	 * 创建时间
	 */
	@Column(name = "createTime", length = 30)
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDataSpecification() {
		return dataSpecification;
	}

	public void setDataSpecification(String dataSpecification) {
		this.dataSpecification = dataSpecification;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
