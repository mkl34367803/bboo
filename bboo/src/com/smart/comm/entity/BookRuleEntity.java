package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 出票规则
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_book_rule")
public class BookRuleEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6665215366305185141L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	/**
	 * 商户号
	 */
	@Column(name = "mno", length = 40)
	private String mno;
	/**
	 * QUNAR_O_SALE CTRIP_O_SALE TAOBAO_O_SALE KUXUN_O_SALE HBGJ_O_SALE
	 * TUNIU_O_SALE TAOBAO_N_SALE QUNAR_N_SALE （下拉菜单）
	 */
	@Column(name = "shopName", length = 20)
	private String shopName;

	/**
	 * 政策类型 1 NFD 2 清仓产品 3 商旅产品 5 中转产品 6 包机 7 切位 8 航司 VIP 卡 10000 普通 供应商 OpenAPI
	 * 对外接口规范 86 10001 规则运价 10 金牌 11 远期 13竞价 14特价 15特惠 16 让利 17 特殊 -1其它 （下拉菜单）
	 */
	@Column(name = "policyType", length = 20)
	private String policyType;
	/**
	 * CP_517NA CP_BAITOUR CP_8000YI CP_51BOOK CP_19E CP_LH800 CP_AIR_B2B_EBAO
	 * CP_LOCALBSP CP_QUA 多个用,号分开
	 */
	@Column(name = "bookChannel", length = 200)
	private String bookChannel;
	/**
	 * 是否可以降低舱位 1可以 2不可以
	 */
	@Column(name = "isDrop")
	private Long isDrop;
	/**
	 * 1可用 2禁用
	 */
	@Column(name = "isuse")
	private Long isuse;
	/**
	 * 最大允许出票利润 例如 ： -5
	 */
	@Column(name = "lr")
	private Double lr;
	/**
	 * 包含规则
	 */
	@Column(name = "includeRule", length = 100)
	private String includeRule;
	/**
	 * 排除规则
	 */
	@Column(name = "ignoredRules", length = 100)
	private String ignoredRules;

	@Column(name = "createTime", length = 25)
	private String createTime;

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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getBookChannel() {
		return bookChannel;
	}

	public void setBookChannel(String bookChannel) {
		this.bookChannel = bookChannel;
	}

	public Long getIsDrop() {
		return isDrop;
	}

	public void setIsDrop(Long isDrop) {
		this.isDrop = isDrop;
	}

	public Double getLr() {
		return lr;
	}

	public void setLr(Double lr) {
		this.lr = lr;
	}

	public String getIncludeRule() {
		return includeRule;
	}

	public void setIncludeRule(String includeRule) {
		this.includeRule = includeRule;
	}

	public String getIgnoredRules() {
		return ignoredRules;
	}

	public void setIgnoredRules(String ignoredRules) {
		this.ignoredRules = ignoredRules;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIsuse() {
		return isuse;
	}

	public void setIsuse(Long isuse) {
		this.isuse = isuse;
	}

}
