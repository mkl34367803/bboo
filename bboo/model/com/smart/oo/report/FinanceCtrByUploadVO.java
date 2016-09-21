package com.smart.oo.report;

import com.smart.excel.report.base.AColumn;
import com.smart.excel.report.base.AExcel;

@AExcel
public class FinanceCtrByUploadVO {
	@AColumn(title="销售平台")
	private String xiaoShouPojo;// 销售平台
	@AColumn(title="订单号")
	private String orderNO;// 订单号
	@AColumn(title=  "订单生成时间")
	private String orderCreateDate;// 订单生成时间
	@AColumn(title=  "PNR"   )
	private String PNR; // PNR
	@AColumn(title=  "乘机人姓名"   )
	private String chengJiRenName;// 乘机人姓名
	@AColumn(title=  "票号"   )
	private String fareNO; // 票号
	@AColumn(title=  "出票时间"   )
	private String chuPiaoDate;// 出票时间
	@AColumn(title=  "起飞时间"   )
	private String fightDate;// 起飞时间
	@AColumn(title=  "航程类型"   )
	private String travlTyle;// 航程类型
	@AColumn(title=  "乘机人类型"   )
	private String chengJiRenType; // 乘机人类型
	@AColumn(title=  "出发到达机场"   )
	private String startCodeToAriveCode;// 出发到达机场
	@AColumn(title=  "承运人"   )
	private String chengYunRen;// 承运人
	@AColumn(title=  "航班号"   )
	private String fightNO; // 航班号
	@AColumn(title=  "舱位"   )
	private String cabin; // 舱位
	@AColumn(title=  "成人票面价格")
	private String chengRenPice; // 成人票面价
	@AColumn(title=  "成人机建费")
	private String chengRenJiJian; // 成人基建费
	@AColumn(title=  "成人燃油税")
	private String chengRenRanYouShui; // 成人燃油税
	@AColumn(title=  "快递费")
	private String kuaiDi;// 快递费
	@AColumn(title=  "快递成本")
	private String kuaiDiChengBen;// 快递成本
	@AColumn(title=  "佣金")
	private String yongJin;// 佣金
	@AColumn(title=  "机票实收款")
	private String surePrice;// 机票实收款
	@AColumn(title=  "收款日期"   )
	private String shouKuaiDate;// 收款日期
	@AColumn(title=  "收款账户"  )
	private String shouKuanAccount;// 收款账户
	@AColumn(title=  "出票员"   )
	private String chuaPiaoRen;// 出票员
	@AColumn(title=  "备注1"   )
	private String beiZhuFirst;// 备注1
	@AColumn(title=  "报价类型"   )
	private String baoJiaTyle; // 报价类型
	@AColumn(title=  "订单状态"   )
	private String orderStatus;// 订单状态
	@AColumn(title=  "自动出票"   )
	private String chuPiaoSelf;// 自动出票
	@AColumn(title=  "实际支付成本")
	private String money;// 金额
	@AColumn(title=  "实际采购票面价")
	private String price;// 票面价
	@AColumn(title=  "采购平台"   )
	private String caiGaoProjo;// 采购平台
	@AColumn(title=  "支付账户"   )
	private String payAccount;// 支付账户
	@AColumn(title=  "cso数据")
	private String csoShuJu;// cso数据
	@AColumn(title=  "后返")
	private String houFan;// 后返
	@AColumn(title=  "实际成本")
	private String shiJiChengBen;// 实际成本
	@AColumn(title=  "利润")
	private String liRun;// 利润
	@AColumn(title=  "备注2"   )
	private String beiZhuSecond;// 备注2
	@AColumn(title=  "CS0备注"   )
	private String CSOBeiZhu; // CS0备注
	@AColumn(title=  "备注3"   )
	private String beiZhuLast;// 备注3
	@AColumn(title=  "交易方式"   )
	private String jiaoYiType;// 交易方式
	@AColumn(title = "广告费")
	private String adRate;
	
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
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getChengRenPice() {
		return chengRenPice;
	}
	public void setChengRenPice(String chengRenPice) {
		this.chengRenPice = chengRenPice;
	}
	public String getChengRenJiJian() {
		return chengRenJiJian;
	}
	public void setChengRenJiJian(String chengRenJiJian) {
		this.chengRenJiJian = chengRenJiJian;
	}
	public String getChengRenRanYouShui() {
		return chengRenRanYouShui;
	}
	public void setChengRenRanYouShui(String chengRenRanYouShui) {
		this.chengRenRanYouShui = chengRenRanYouShui;
	}
	public String getKuaiDi() {
		return kuaiDi;
	}
	public void setKuaiDi(String kuaiDi) {
		this.kuaiDi = kuaiDi;
	}
	public String getKuaiDiChengBen() {
		return kuaiDiChengBen;
	}
	public void setKuaiDiChengBen(String kuaiDiChengBen) {
		this.kuaiDiChengBen = kuaiDiChengBen;
	}
	public String getYongJin() {
		return yongJin;
	}
	public void setYongJin(String yongJin) {
		this.yongJin = yongJin;
	}
	public String getSurePrice() {
		return surePrice;
	}
	public void setSurePrice(String surePrice) {
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
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCaiGaoProjo() {
		return caiGaoProjo;
	}
	public void setCaiGaoProjo(String caiGaoProjo) {
		this.caiGaoProjo = caiGaoProjo;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getCsoShuJu() {
		return csoShuJu;
	}
	public void setCsoShuJu(String csoShuJu) {
		this.csoShuJu = csoShuJu;
	}
	public String getHouFan() {
		return houFan;
	}
	public void setHouFan(String houFan) {
		this.houFan = houFan;
	}
	public String getShiJiChengBen() {
		return shiJiChengBen;
	}
	public void setShiJiChengBen(String shiJiChengBen) {
		this.shiJiChengBen = shiJiChengBen;
	}
	public String getLiRun() {
		return liRun;
	}
	public void setLiRun(String liRun) {
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
	public String getAdRate() {
		return adRate;
	}
	public void setAdRate(String adRate) {
		this.adRate = adRate;
	}
	
	
	
}
