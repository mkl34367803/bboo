package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "zs_file_info")
public class FileInfoEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7301739154719689996L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "mno", length = 40)
	private String mno;
	@Column(name = "filePath", length = 150)
	private String filePath;
	@Column(name = "fileType", length = 50)
	private String fileType;
	@Column(name = "fileDes", length = 50)
	private String fileDes;
	@Column(name = "fileSize")
	private int fileSize;
	@Column(name = "operator", length = 35)
	private String operator;
	@Column(name = "iproxy", length = 30)
	private String iproxy;
	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileDes() {
		return fileDes;
	}

	public void setFileDes(String fileDes) {
		this.fileDes = fileDes;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getIproxy() {
		return iproxy;
	}

	public void setIproxy(String iproxy) {
		this.iproxy = iproxy;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

}
