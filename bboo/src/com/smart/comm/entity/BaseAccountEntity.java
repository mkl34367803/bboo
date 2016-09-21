package com.smart.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.smart.comm.utils.BBOOObject;

/**
 * 采购
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "zs_base_account")
public class BaseAccountEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8550461325312700762L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	private Integer id;
	@Column(name = "mno", length = 40)
	private String mno;
	@Column(name = "userName", length = 60)
	private String userName;
	/**
	 * 大客户优惠码
	 */
	@Column(name = "bigcid", length = 30)
	private String bigcid;
	/**
	 * 密码
	 */
	@Column(name = "secret", length = 60)
	private String secret;
	/**
	 * 安全KEY
	 */
	@Column(name = "appkey", length = 60)
	private String appkey;
	/**
	 * 安全码
	 */
	@Column(name = "codes", length = 60)
	private String codes;
	/**
	 * SESSION
	 */
	@Column(name = "sessions", length = 100)
	private String sessions;
	/**
	 * 默认提取编码的OFFICE号
	 */
	@Column(name = "office", length = 30)
	private String office;
	/**
	 * 默认预定的OFFICE号
	 */
	@Column(name = "sdoffice", length = 30)
	private String sdoffice;
	/**
	 * 1预定
	 */
	@Column(name = "issd")
	private Integer issd;
	/**
	 * 第三方 请求的URL 地址
	 */
	@Column(name = "url", length = 60)
	private String url;
	/**
	 * 作供应显示 排除店铺
	 */
	@Column(name = "ignoredShopNames", length = 100)
	private String ignoredShopNames;// 淘宝 排除店铺
	/**
	 * QUA QUNAR CTRIP TONGC 获取分销订单接口(QUNAR_O_SALE CTRIP_O_SALE TAOBAO_O_SALE
	 * KUXUN_O_SALE HBGJ_O_SALE TUNIU_O_SALE TONGC_O_SALE TONGC_N_SALE
	 * TAOBAO_N_SALE QUNAR_N_SALE) TFARE_GDS 获取政策（CP_517NA CP_BAITOUR CP_8000YI
	 * CP_51BOOK CP_19E CP_LH800 CP_AIR_B2B_EBAO CP_LOCALBSP CP_QUA）
	 */
	@Column(name = "acctype", length = 15)
	private String acctype;

	/**
	 * 描述 淘宝东方祥云 去哪儿逍客TTS 去哪儿逍客特价 517NA采购
	 */
	@Column(name = "describe", length = 30)
	private String describe;

	/**
	 * 账号种类 1分销 2采购 3其他
	 */
	@Column(name = "acckind")
	private Integer acckind;

	/**
	 * 支付账号
	 */
	@Column(name = "accpay", length = 40)
	private String accpay;

	/**
	 * 支付密码
	 */
	@Column(name = "paypwd", length = 40)
	private String paypwd; // DEC 加密

	/**
	 * 支付类型 ALIPAY TENPAY EPAY WALLET
	 */
	@Column(name = "paytype", length = 15)
	private String paytype;

	/**
	 * 1 可用 2禁用
	 */
	@Column(name = "isu")
	private Integer isu;
	/**
	 * 创建时间
	 */
	@Column(name = "ctime", length = 25)
	private String ctime;
	/**
	 * 接口HOST 211.154.142.162:4005
	 */
	@Column(name = "apiHost", length = 35)
	private String apiHost;
	/**
	 * 接口项目名 ots
	 */
	@Column(name = "apiServiceName", length = 15)
	private String apiServiceName;
	/**
	 * 接口类名 interface-test
	 */
	@Column(name = "apiClassName", length = 25)
	private String apiClassName;
	/**
	 * 接口方法名称
	 */
	@Column(name = "apiMethodName", length = 15)
	private String apiMethodName;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBigcid() {
		return bigcid;
	}

	public void setBigcid(String bigcid) {
		this.bigcid = bigcid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String getSessions() {
		return sessions;
	}

	public void setSessions(String sessions) {
		this.sessions = sessions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIgnoredShopNames() {
		return ignoredShopNames;
	}

	public void setIgnoredShopNames(String ignoredShopNames) {
		this.ignoredShopNames = ignoredShopNames;
	}

	public String getAcctype() {
		return acctype;
	}

	public void setAcctype(String acctype) {
		this.acctype = acctype;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getAcckind() {
		return acckind;
	}

	public void setAcckind(Integer acckind) {
		this.acckind = acckind;
	}

	public String getAccpay() {
		return accpay;
	}

	public void setAccpay(String accpay) {
		this.accpay = accpay;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
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

	public String getPaypwd() {
		return paypwd;
	}

	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public String getApiServiceName() {
		return apiServiceName;
	}

	public void setApiServiceName(String apiServiceName) {
		this.apiServiceName = apiServiceName;
	}

	public String getApiClassName() {
		return apiClassName;
	}

	public void setApiClassName(String apiClassName) {
		this.apiClassName = apiClassName;
	}

	public String getApiMethodName() {
		return apiMethodName;
	}

	public void setApiMethodName(String apiMethodName) {
		this.apiMethodName = apiMethodName;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getSdoffice() {
		return sdoffice;
	}

	public void setSdoffice(String sdoffice) {
		this.sdoffice = sdoffice;
	}

	public Integer getIssd() {
		return issd;
	}

	public void setIssd(Integer issd) {
		this.issd = issd;
	}

}
