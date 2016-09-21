package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

@Entity
@Table(name = "t_questions")
public class QuestionsEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7986201063089752844L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	/**
	 * 问题类型 1 问答题 2选择题 3多选题
	 */
	@Column(name = "QType")
	private Integer QType;
	/**
	 * 题目类别 1 技术 2 航空业务 3 财务 4行政
	 */
	@Column(name = "classify")
	private Integer classify;
	/**
	 * 问题
	 */
	@Column(name = "question", length = 500)
	private String question;
	@Column(name = "A", length = 100)
	private String A;
	@Column(name = "B", length = 100)
	private String B;
	@Column(name = "C", length = 100)
	private String C;
	@Column(name = "D", length = 100)
	private String D;
	/**
	 * 选择答案 多个答案，用A,B,C
	 */
	@Column(name = "answerSelect", length = 20)
	private String answerSelect;
	/**
	 * 文本答案
	 */
	@Column(name = "answerTxt", length = 500)
	private String answerTxt;
	/**
	 * 出题人
	 */
	@Column(name = "intoPerson", length = 50)
	private String intoPerson;
	/**
	 * 0可用 1删除
	 */
	@Column(name = "isuse")
	private Integer isuse;
	@Column(name = "createTime", length = 25)
	private String createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQType() {
		return QType;
	}

	public void setQType(Integer qType) {
		QType = qType;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public String getC() {
		return C;
	}

	public void setC(String c) {
		C = c;
	}

	public String getD() {
		return D;
	}

	public void setD(String d) {
		D = d;
	}

	public String getAnswerSelect() {
		return answerSelect;
	}

	public void setAnswerSelect(String answerSelect) {
		this.answerSelect = answerSelect;
	}

	public String getAnswerTxt() {
		return answerTxt;
	}

	public void setAnswerTxt(String answerTxt) {
		this.answerTxt = answerTxt;
	}

	public String getIntoPerson() {
		return intoPerson;
	}

	public void setIntoPerson(String intoPerson) {
		this.intoPerson = intoPerson;
	}

	public Integer getIsuse() {
		return isuse;
	}

	public void setIsuse(Integer isuse) {
		this.isuse = isuse;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
