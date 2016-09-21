package com.smart.comm.entity;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;



import com.smart.comm.utils.BBOOObject;

/**
 * 销售利润报表
 * 
 * @author eric
 * 
 */
@Entity
@Table(name = "t_finance_sale")
public class FinanceSaleEntity extends BBOOObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8321410615157862307L;
	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0, length = 55)
	private String id;
	@Column(name = "mno", length = 40)
	private String mno;
	/**
	 * 文件号
	 */
	@Column(name = "fileno", length = 50)
	private String fileno;// 文件号
	@Column(name = "xiaoShouPojo", length = 50)
	private String xiaoShouPojo;// 销售平台
	@Column(name = "orderNO", length = 50)
	private String orderNO;// 订单号
	@Column(name = "orderCreateDate", length = 50)
	private String orderCreateDate;// 订单生成时间
	@Column(name = "PNR", length = 50)
	private String PNR; // PNR
	@Column(name = "chengJiRenName", length = 80)
	private String chengJiRenName;// 乘机人姓名
	@Column(name = "fareNO", length = 150)
	private String fareNO; // 票号
	@Column(name = "chuPiaoDate", length = 50)
	private String chuPiaoDate;// 出票时间
	@Column(name = "fightDate", length = 60)
	private String fightDate;// 起飞时间
	@Column(name = "travlTyle", length = 5)
	private String travlTyle;// 航程类型
	@Column(name = "chengJiRenType", length = 5)
	private String chengJiRenType; // 乘机人类型
	@Column(name = "startCode", length = 10)
	private String startCode; // 始发地三字码
	@Column(name = "arriveCode", length = 10)
	private String arriveCode; // 目的地三字码
	@Column(name = "startCodeToAriveCode", length = 80)
	private String startCodeToAriveCode;// 出发到达机场
	@Column(name = "chengYunRen", length = 30)
	private String chengYunRen;// 承运人
	@Column(name = "fightNO", length = 50)
	private String fightNO; // 航班号
	@Column(name = "cabin", length =20)
	private String cabin; // 舱位
	@Column(name = "chengRenPice")
	private Double chengRenPice; // 成人票面价
	@Column(name = "chengRenJiJian")
	private Double chengRenJiJian; // 成人基建费
	@Column(name = "chengRenRanYouShui")
	private Double chengRenRanYouShui; // 成人燃油税
	@Column(name = "kuaiDi")
	private Double kuaiDi;// 快递费
	@Column(name = "kuaiDiChengBen")
	private Double kuaiDiChengBen;// 快递成本
	@Column(name = "yongJin")
	private Double yongJin;// 佣金
	@Column(name = "surePrice")
	private Double surePrice;// 机票实收款
	@Column(name = "shouKuaiDate", length = 50)
	private String shouKuaiDate;// 收款日期
	@Column(name = "shouKuanAccount", length =100)
	private String shouKuanAccount;// 收款账户
	@Column(name = "chuaPiaoRen", length = 50)
	private String chuaPiaoRen;// 出票员
	@Column(name = "beiZhuFirst", length = 1000)
	private String beiZhuFirst;// 备注1
	@Column(name = "baoJiaTyle", length = 60)
	private String baoJiaTyle; // 报价类型
	@Column(name = "orderStatus", length = 40)
	private String orderStatus;// 订单状态
	@Column(name = "chuPiaoSelf", length = 40)
	private String chuPiaoSelf;// 自动出票
	@Column(name = "money")
	private Double money;// 实际支付成本
	@Column(name = "price")
	private Double price;// 实际采购票面价
	@Column(name = "caiGaoProjo", length = 50)
	private String caiGaoProjo;// 采购平台
	@Column(name = "projoName", length = 50)
	private String projoName;// 产品名称
	@Column(name = "csoShuJu")
	private Double csoShuJu;// cso数据
	@Column(name = "houFan")
	private Double houFan;// 后返
	@Column(name = "shiJiChengBen")
	private Double shiJiChengBen;// 实际成本
	@Column(name = "liRun")
	private Double liRun;// 利润
	@Column(name = "beiZhuSecond", length = 1000)
	private String beiZhuSecond;// 备注2
	@Column(name = "CSOBeiZhu", length = 1000)
	private String CSOBeiZhu; // CS0备注
	@Column(name = "beiZhuLast", length = 1000)
	private String beiZhuLast;// 备注3
	@Column(name = "jiaoYiType", length = 50)
	private String jiaoYiType;// 交易方式

	@Column(name = "impTime", length = 40)
	private String impTime;// 导入日期
	@Column(name = "adRate")
	private Double adRate;// 广告费
    

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getXiaoShouPojo() {
		return xiaoShouPojo;
	}

	public void setXiaoShouPojo(String xiaoShouPojo) {
		this.xiaoShouPojo = xiaoShouPojo;
	}

	public String getOrderNO() {
		return orderNO;
	}

	public void setOrderNO(String orderNO) {
		this.orderNO = orderNO;
	}

	public String getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(String orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public String getPNR() {
		return PNR;
	}

	public void setPNR(String pNR) {
		PNR = pNR;
	}

	public String getChengJiRenName() {
		return chengJiRenName;
	}

	public void setChengJiRenName(String chengJiRenName) {
		this.chengJiRenName = chengJiRenName;
	}

	public String getFareNO() {
		return fareNO;
	}

	public void setFareNO(String fareNO) {
		this.fareNO = fareNO;
	}

	public String getChuPiaoDate() {
		return chuPiaoDate;
	}

	public void setChuPiaoDate(String chuPiaoDate) {
		this.chuPiaoDate = chuPiaoDate;
	}

	public String getFightDate() {
		return fightDate;
	}

	public void setFightDate(String fightDate) {
		this.fightDate = fightDate;
	}

	public String getTravlTyle() {
		return travlTyle;
	}

	public void setTravlTyle(String travlTyle) {
		this.travlTyle = travlTyle;
	}

	public String getChengJiRenType() {
		return chengJiRenType;
	}

	public void setChengJiRenType(String chengJiRenType) {
		this.chengJiRenType = chengJiRenType;
	}

	public String getStartCode() {
		return startCode;
	}

	public void setStartCode(String startCode) {
		this.startCode = startCode;
	}

	public String getArriveCode() {
		return arriveCode;
	}

	public void setArriveCode(String arriveCode) {
		this.arriveCode = arriveCode;
	}

	public String getStartCodeToAriveCode() {
		return startCodeToAriveCode;
	}

	public void setStartCodeToAriveCode(String startCodeToAriveCode) {
		this.startCodeToAriveCode = startCodeToAriveCode;
	}

	public String getChengYunRen() {
		return chengYunRen;
	}

	public void setChengYunRen(String chengYunRen) {
		this.chengYunRen = chengYunRen;
	}

	public String getFightNO() {
		return fightNO;
	}

	public void setFightNO(String fightNO) {
		this.fightNO = fightNO;
	}

	public Double getChengRenPice() {
		return chengRenPice;
	}

	public void setChengRenPice(Double chengRenPice) {
		this.chengRenPice = chengRenPice;
	}

	public Double getChengRenJiJian() {
		return chengRenJiJian;
	}

	public void setChengRenJiJian(Double chengRenJiJian) {
		this.chengRenJiJian = chengRenJiJian;
	}

	public Double getChengRenRanYouShui() {
		return chengRenRanYouShui;
	}

	public void setChengRenRanYouShui(Double chengRenRanYouShui) {
		this.chengRenRanYouShui = chengRenRanYouShui;
	}

	public Double getKuaiDi() {
		return kuaiDi;
	}

	public void setKuaiDi(Double kuaiDi) {
		this.kuaiDi = kuaiDi;
	}

	public Double getKuaiDiChengBen() {
		return kuaiDiChengBen;
	}

	public void setKuaiDiChengBen(Double kuaiDiChengBen) {
		this.kuaiDiChengBen = kuaiDiChengBen;
	}

	public Double getYongJin() {
		return yongJin;
	}

	public void setYongJin(Double yongJin) {
		this.yongJin = yongJin;
	}

	public Double getSurePrice() {
		return surePrice;
	}

	public void setSurePrice(Double surePrice) {
		this.surePrice = surePrice;
	}

	public String getShouKuaiDate() {
		return shouKuaiDate;
	}

	public void setShouKuaiDate(String shouKuaiDate) {
		this.shouKuaiDate = shouKuaiDate;
	}

	public String getShouKuanAccount() {
		return shouKuanAccount;
	}

	public void setShouKuanAccount(String shouKuanAccount) {
		this.shouKuanAccount = shouKuanAccount;
	}

	public String getChuaPiaoRen() {
		return chuaPiaoRen;
	}

	public void setChuaPiaoRen(String chuaPiaoRen) {
		this.chuaPiaoRen = chuaPiaoRen;
	}

	public String getBeiZhuFirst() {
		return beiZhuFirst;
	}

	public void setBeiZhuFirst(String beiZhuFirst) {
		this.beiZhuFirst = beiZhuFirst;
	}

	public String getBaoJiaTyle() {
		return baoJiaTyle;
	}

	public void setBaoJiaTyle(String baoJiaTyle) {
		this.baoJiaTyle = baoJiaTyle;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getChuPiaoSelf() {
		return chuPiaoSelf;
	}

	public void setChuPiaoSelf(String chuPiaoSelf) {
		this.chuPiaoSelf = chuPiaoSelf;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCaiGaoProjo() {
		return caiGaoProjo;
	}

	public void setCaiGaoProjo(String caiGaoProjo) {
		this.caiGaoProjo = caiGaoProjo;
	}

	public String getProjoName() {
		return projoName;
	}

	public void setProjoName(String projoName) {
		this.projoName = projoName;
	}

	public Double getCsoShuJu() {
		return csoShuJu;
	}

	public void setCsoShuJu(Double csoShuJu) {
		this.csoShuJu = csoShuJu;
	}

	public Double getHouFan() {
		return houFan;
	}

	public void setHouFan(Double houFan) {
		this.houFan = houFan;
	}

	public Double getShiJiChengBen() {
		return shiJiChengBen;
	}

	public void setShiJiChengBen(Double shiJiChengBen) {
		this.shiJiChengBen = shiJiChengBen;
	}

	public Double getLiRun() {
		return liRun;
	}

	public void setLiRun(Double liRun) {
		this.liRun = liRun;
	}

	public String getBeiZhuSecond() {
		return beiZhuSecond;
	}

	public void setBeiZhuSecond(String beiZhuSecond) {
		this.beiZhuSecond = beiZhuSecond;
	}

	public String getCSOBeiZhu() {
		return CSOBeiZhu;
	}

	public void setCSOBeiZhu(String cSOBeiZhu) {
		CSOBeiZhu = cSOBeiZhu;
	}

	public String getBeiZhuLast() {
		return beiZhuLast;
	}

	public void setBeiZhuLast(String beiZhuLast) {
		this.beiZhuLast = beiZhuLast;
	}

	public String getJiaoYiType() {
		return jiaoYiType;
	}

	public void setJiaoYiType(String jiaoYiType) {
		this.jiaoYiType = jiaoYiType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getCabin() {
		return cabin;
	}

	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	public String getImpTime() {
		return impTime;
	}

	public void setImpTime(String impTime) {
		this.impTime = impTime;
	}

	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public Double getAdRate() {
		return adRate;
	}

	public void setAdRate(Double adRate) {
		this.adRate = adRate;
	}
	
	
}
