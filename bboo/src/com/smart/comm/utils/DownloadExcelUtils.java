package com.smart.comm.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.entity.ReportFormsEntity;
import com.smart.oo.from.OrderReportVo;
import com.smart.utils.HttpUtils;
import com.smart.utils.ZipFileUtil;

/**
 * 报表----Excel
 * @author Administrator
 *
 */
public class DownloadExcelUtils {
	
	/**
	 * FinanceCtrl中的报表
	 * @param filePath
	 * @param fsList
	 * @param profitList
	 */
	public static void writeToExcel(String filePath, List<FinanceSaleEntity> fsList) {
		try {
			String[] title = { "销售平台", "订单号", "订单生成时间", "PNR", "乘机人姓名", "票号",
					"出票时间", "起飞时间", "航程类型", "乘机人类型", "出发到达机场", "承运人", "航班号",
					"舱位", "成人票面价格", "成人机建费", "成人燃油税", "快递费", "快递成本", "佣金", "广告费",
					"机票实收款", "收款日期", "收款账户", "出票员", "备注1", "报价类型", "订单状态",
					"自动出票", "实际支付成本", "实际采购票面价", "采购平台", "支付账户", "cso数据", "后返", "实际成本",
					"利润", "备注2", "CS0备注", "备注3", "交易方式" };
			
			// 开始时间
			long start = System.currentTimeMillis();
			
			File file = new File(filePath);
			
			// 创建Excel工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			
			List<List<FinanceSaleEntity>> listSub = getSubList(fsList, 65000);
			
			List<FinanceSaleEntity> list = null;
			int len = listSub.size();
			
			// 采购
			Map<String, Map<String, Double>> buySummary = new HashMap<String, Map<String,Double>>();
			// 销售
			Map<String, Map<String, Double>> saleSummary = new HashMap<String, Map<String,Double>>();
			
			for (int n = 0; n < len; n++) {
				list =  listSub.get(n);
				
				// 创建工作表
				WritableSheet sheet = workbook.createSheet("sheet"+n, 0);
				
				Label label = null;
				jxl.write.Number number = null;
				WritableCellFormat bodyFormat = getLabelFormat();
				WritableCellFormat numFormat = getNumberFormat();
				
				// 插入标题
				for (int i = 0; i < title.length; i++) {
					// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z 
					// 在Label对象的子对象中指明单元格的位置和内容 
					//label = new Label(i, 0, title[i]);
					label = new Label(i, 0, title[i], getHeader());
					sheet.addCell(label);
					sheet.setColumnView(i, 10);
				}
				
				// 填充数据
				int row = 1;
				Iterator<FinanceSaleEntity> it = list.iterator();
				while (it.hasNext()) {
					FinanceSaleEntity f = it.next();
					
					getProfit(buySummary, f, f.getCaiGaoProjo());
					getProfit(saleSummary, f, f.getXiaoShouPojo());
					
					int col = 0;
					insertLabel(sheet, label, col++, row, f.getXiaoShouPojo(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getOrderNO(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getOrderCreateDate(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getPNR(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getChengJiRenName(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getFareNO(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getChuPiaoDate(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getFightDate(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getTravlTyle(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getChengJiRenType(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getStartCodeToAriveCode(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getChengYunRen(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getFightNO(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getCabin(), bodyFormat);
					
					insertNumber(sheet, number, col++, row, f.getChengRenPice(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getChengRenJiJian(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getChengRenRanYouShui(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getKuaiDi(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getKuaiDiChengBen(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getYongJin(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getAdRate(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getSurePrice(), numFormat);
					
					insertLabel(sheet, label, col++, row, f.getShouKuaiDate(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getShouKuanAccount(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getChuaPiaoRen(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getBeiZhuFirst(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getBaoJiaTyle(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getOrderStatus(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getChuPiaoSelf(), bodyFormat);
					
					insertNumber(sheet, number, col++, row, f.getMoney(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getPrice(), numFormat);
					
					insertLabel(sheet, label, col++, row, f.getCaiGaoProjo(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getProjoName(), bodyFormat);
					
					insertNumber(sheet, number, col++, row, f.getCsoShuJu(), numFormat);
					
					insertNumber(sheet, number, col++, row, f.getHouFan(), numFormat);
					
					// 实际成本
					//insertFormula(sheet, col++, row, "SUM(AD"+(row+1)+"-AI"+(row+1)+"+T"+(row+1)+"+U"+(row+1)+")", numFormat);
					insertNumber(sheet, number, col++, row, f.getShiJiChengBen(), numFormat);
					
					// 利润
					//insertFormula(sheet, col++, row, "SUM(V"+(row+1)+"-AJ"+(row+1)+"+R"+(row+1)+"-S"+(row+1)+")", numFormat);
					insertNumber(sheet, number, col++, row, f.getLiRun(), numFormat);
					
					insertLabel(sheet, label, col++, row, f.getBeiZhuSecond(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getCSOBeiZhu(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getBeiZhuLast(), bodyFormat);
					
					insertLabel(sheet, label, col++, row, f.getJiaoYiType(), bodyFormat);
					
					row++;
					it.remove();
				}
				
				row += 3;
				if (n == (len -1)) {
					row += writeBuyProfit(sheet, row, buySummary);
					row += 3;
					row += writeSaleProfit(sheet, row, saleSummary);
				}
				
			}
			
			
			workbook.write();
			workbook.close();
			long end = System.currentTimeMillis();
			info("-完成该操作共用的时间是"+(end-start)+"毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ReportForms中的报表
	 * @param filePath
	 * @param fsList
	 * @param profitList
	 * @param reportFormsEntities
	 */
	public static void writeReportToExcel(String filePath, List<FinanceSaleEntity> fsList, List<ReportFormsEntity> reportFormsEntities) {
		try {
			List<String> titleList = new ArrayList<String>();
			List<String> fieldList = new ArrayList<String>();
			for (ReportFormsEntity reportForms : reportFormsEntities) {
				titleList.add(reportForms.getDescription());
				fieldList.add(reportForms.getFieldName());
			}
			
			// 开始时间
			long start = System.currentTimeMillis();
			
			File file = new File(filePath);
			
			// 创建Excel工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			
			List<List<FinanceSaleEntity>> listSub = getSubList(fsList, 65000);
			
			List<FinanceSaleEntity> list = null;
			// 采购
			Map<String, Map<String, Double>> buySummary = new HashMap<String, Map<String,Double>>();
			// 销售
			Map<String, Map<String, Double>> saleSummary = new HashMap<String, Map<String,Double>>();
			
			int len = listSub.size();
			for (int n = 0; n < len; n++) {
				list =  listSub.get(n);
				
				// 创建工作表
				WritableSheet sheet = workbook.createSheet("sheet"+n, 0);
				
				Label label = null;
				jxl.write.Number number = null;
				WritableCellFormat bodyFormat = getLabelFormat();
				WritableCellFormat numFormat = getNumberFormat();
				
				// 插入标题
				for (int i = 0; i < titleList.size(); i++) {
					label = new Label(i, 0, titleList.get(i), getHeader());
					sheet.addCell(label);
					sheet.setColumnView(i, 10);
				}
				
				// 填充数据
				int row = 1;
				Iterator<FinanceSaleEntity> it = list.iterator();
				while (it.hasNext()) {
					FinanceSaleEntity f = (FinanceSaleEntity) it.next();

					getProfit(buySummary, f, f.getCaiGaoProjo());
					getProfit(saleSummary, f, f.getXiaoShouPojo());
					
					int c = 0;
					for (String  filed : fieldList) {
						if ("fileno".equalsIgnoreCase(filed)) {// 文件号
							insertLabel(sheet, label, c++, row, f.getFileno(), bodyFormat);
						} else if ("xiaoShouPojo".equalsIgnoreCase(filed)) {// 销售平台
							insertLabel(sheet, label, c++, row, f.getXiaoShouPojo(), bodyFormat);
						} else if ("orderNO".equalsIgnoreCase(filed)) {// 订单号
							insertLabel(sheet, label, c++, row, f.getOrderNO(), bodyFormat);
						} else if ("orderCreateDate".equalsIgnoreCase(filed)) {// 订单生成时间
							insertLabel(sheet, label, c++, row, f.getOrderCreateDate(), bodyFormat);
						} else if ("PNR".equalsIgnoreCase(filed)) {// PNR
							insertLabel(sheet, label, c++, row, f.getPNR(), bodyFormat);
						} else if ("chengJiRenName".equalsIgnoreCase(filed)) {// 乘机人姓名
							insertLabel(sheet, label, c++, row, f.getChengJiRenName(), bodyFormat);
						} else if ("fareNO".equalsIgnoreCase(filed)) {// 票号
							insertLabel(sheet, label, c++, row, f.getFareNO(), bodyFormat);
						} else if ("chuPiaoDate".equalsIgnoreCase(filed)) {// 出票时间
							insertLabel(sheet, label, c++, row, f.getChuPiaoDate(), bodyFormat);
						}  else if ("fightDate".equalsIgnoreCase(filed)) {// 起飞时间
							insertLabel(sheet, label, c++, row, f.getFightDate(), bodyFormat);
						} else if ("travlTyle".equalsIgnoreCase(filed)) {// 航程类型
							insertLabel(sheet, label, c++, row, f.getTravlTyle(), bodyFormat);
						} else if ("chengJiRenType".equalsIgnoreCase(filed)) {// 乘机人类型
							insertLabel(sheet, label, c++, row, f.getChengJiRenType(), bodyFormat);
						} else if ("startCode".equalsIgnoreCase(filed)) {// 始发地三字码
							insertLabel(sheet, label, c++, row, f.getStartCode(), bodyFormat);
						} else if ("arriveCode".equalsIgnoreCase(filed)) {// 目的地三字码
							insertLabel(sheet, label, c++, row, f.getArriveCode(), bodyFormat);
						} else if ("startCodeToAriveCode".equalsIgnoreCase(filed)) {// 出发到达机场
							insertLabel(sheet, label, c++, row, f.getStartCodeToAriveCode(), bodyFormat);
						} else if ("chengYunRen".equalsIgnoreCase(filed)) {// 承运人
							insertLabel(sheet, label, c++, row, f.getChengYunRen(), bodyFormat);
						} else if ("fightNO".equalsIgnoreCase(filed)) {// 航班号
							insertLabel(sheet, label, c++, row, f.getFightNO(), bodyFormat);
						} else if ("cabin".equalsIgnoreCase(filed)) {// 舱位
							insertLabel(sheet, label, c++, row, f.getCabin(), bodyFormat);
						} else if ("chengRenPice".equalsIgnoreCase(filed)) {// 成人票面价
							insertNumber(sheet, number, c++, row, f.getChengRenPice(), numFormat);
						} else if ("chengRenJiJian".equalsIgnoreCase(filed)) {// 成人基建费
							insertNumber(sheet, number, c++, row, f.getChengRenJiJian(), numFormat);
						} else if ("chengRenRanYouShui".equalsIgnoreCase(filed)) {// 成人燃油税
							insertNumber(sheet, number, c++, row, f.getChengRenRanYouShui(), numFormat);
						} else if ("kuaiDi".equalsIgnoreCase(filed)) {// 快递费
							insertNumber(sheet, number, c++, row, f.getKuaiDi(), numFormat);
						} else if ("kuaiDiChengBen".equalsIgnoreCase(filed)) {// 快递成本
							insertNumber(sheet, number, c++, row, f.getKuaiDiChengBen(), numFormat);
						} else if ("yongJin".equalsIgnoreCase(filed)) {// 佣金
							insertNumber(sheet, number, c++, row, f.getYongJin(), numFormat);
						} else if ("surePrice".equalsIgnoreCase(filed)) {// 机票实收款
							insertNumber(sheet, number, c++, row, f.getSurePrice(), numFormat);
						} else if ("shouKuaiDate".equalsIgnoreCase(filed)) {// 收款日期
							insertLabel(sheet, label, c++, row, f.getShouKuaiDate(), bodyFormat);
						} else if ("shouKuanAccount".equalsIgnoreCase(filed)) {// 收款账户
							insertLabel(sheet, label, c++, row, f.getShouKuanAccount(), bodyFormat);
						} else if ("chuaPiaoRen".equalsIgnoreCase(filed)) {// 出票员
							insertLabel(sheet, label, c++, row, f.getChuaPiaoRen(), bodyFormat);
						} else if ("beiZhuFirst".equalsIgnoreCase(filed)) {// 备注1
							insertLabel(sheet, label, c++, row, f.getBeiZhuFirst(), bodyFormat);
						} else if ("baoJiaTyle".equalsIgnoreCase(filed)) {// 报价类型
							insertLabel(sheet, label, c++, row, f.getBaoJiaTyle(), bodyFormat);
						} else if ("orderStatus".equalsIgnoreCase(filed)) {// 订单状态
							insertLabel(sheet, label, c++, row, f.getOrderStatus(), bodyFormat);
						} else if ("chuPiaoSelf".equalsIgnoreCase(filed)) {// 自动出票
							insertLabel(sheet, label, c++, row, f.getChuPiaoSelf(), bodyFormat);
						} else if ("money".equalsIgnoreCase(filed)) {// 金额
							insertNumber(sheet, number, c++, row, f.getMoney(), numFormat);
						} else if ("price".equalsIgnoreCase(filed)) {// 票面价
							insertNumber(sheet, number, c++, row, f.getPrice(), numFormat);
						} else if ("caiGaoProjo".equalsIgnoreCase(filed)) {// 采购平台
							insertLabel(sheet, label, c++, row, f.getCaiGaoProjo(), bodyFormat);
						} else if ("projoName".equalsIgnoreCase(filed)) {// 产品名称
							insertLabel(sheet, label, c++, row, f.getProjoName(), bodyFormat);
						} else if ("csoShuJu".equalsIgnoreCase(filed)) {// cso数据
							insertNumber(sheet, number, c++, row, f.getCsoShuJu(), numFormat);
						} else if ("houFan".equalsIgnoreCase(filed)) {// 后返
							insertNumber(sheet, number, c++, row, f.getHouFan(), numFormat);
						} else if ("shiJiChengBen".equalsIgnoreCase(filed)) {// 实际成本
							insertNumber(sheet, number, c++, row, f.getShiJiChengBen(), numFormat);
						} else if ("liRun".equalsIgnoreCase(filed)) {// 利润
							insertNumber(sheet, number, c++, row, f.getLiRun(), numFormat);
						} else if ("beiZhuSecond".equalsIgnoreCase(filed)) {// 备注2
							insertLabel(sheet, label, c++, row, f.getBeiZhuSecond(), bodyFormat);
						} else if ("CSOBeiZhu".equalsIgnoreCase(filed)) {// CS0备注
							insertLabel(sheet, label, c++, row, f.getCSOBeiZhu(), bodyFormat);
						} else if ("beiZhuLast".equalsIgnoreCase(filed)) {// 备注3
							insertLabel(sheet, label, c++, row, f.getBeiZhuLast(), bodyFormat);
						} else if ("jiaoYiType".equalsIgnoreCase(filed)) {// 交易方式
							insertLabel(sheet, label, c++, row, f.getJiaoYiType(), bodyFormat);
						} else if ("impTime".equalsIgnoreCase(filed)) {// 导入日期
							insertLabel(sheet, label, c++, row, f.getImpTime(), bodyFormat);
						}  else if ("adRate".equalsIgnoreCase(filed)) {// 广告费
							insertLabel(sheet, label, c++, row, f.getAdRate(), bodyFormat);
						} else {
							insertLabel(sheet, label, c++, row, "error", bodyFormat);
						}
					}
					row++;
					it.remove();
				}
				
				row += 3;
				if (n == (len -1)) {
					// 插入利润
					row += writeBuyProfit(sheet, row, buySummary);
					row += 3;
					row += writeSaleProfit(sheet, row, saleSummary);
				}
				
			}
			
			
			workbook.write();
			workbook.close();
			long end = System.currentTimeMillis();
			info("-完成该操作共用的时间是"+(end-start)+"毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取利润
	 * @param summary
	 * @param fs
	 * @param pojo
	 * @return
	 */
	public static Map<String, Map<String, Double>> getProfit(Map<String, Map<String, Double>> summary, FinanceSaleEntity fs, String pojo) {
		if (summary.containsKey(pojo)) {
			Map<String, Double> sum = summary.get(pojo);
			// 数量
			sum.put("count", sum.get("count")+1);
			// 机票实收款
			sum.put("surePrice", sum.get("surePrice")+fs.getSurePrice());
			//实际成本=采购-后返+佣金+广告费
			Double shiji = fs.getMoney() - fs.getHouFan() + fs.getYongJin() + fs.getAdRate();
			sum.put("shiJi", sum.get("shiJi")+shiji);
			// 快递成本
			sum.put("kuaiDiCB", sum.get("kuaiDiCB")+fs.getKuaiDiChengBen());
			// 快递费
			sum.put("kuaiDi", sum.get("kuaiDi")+fs.getKuaiDi());
			// 广告费
			sum.put("adRate", sum.get("adRate")+fs.getAdRate());
			//利润=机票实收+快递费-快递成本-实际成本
			Double lr = fs.getSurePrice() + fs.getKuaiDi() -fs.getKuaiDiChengBen() - shiji;
			sum.put("lr", sum.get("lr")+lr);
			
			summary.put(pojo, sum);
		} else {
			Map<String, Double> sum = new HashMap<String, Double>();
			// 数量
			sum.put("count", 1.0);
			// 机票实收款
			sum.put("surePrice", fs.getSurePrice());
			//实际成本=采购-后返+佣金
			Double shiji = fs.getMoney() - fs.getHouFan() + fs.getYongJin() + fs.getAdRate();
			sum.put("shiJi", shiji);
			// 快递成本
			sum.put("kuaiDiCB", fs.getKuaiDiChengBen());
			// 快递费
			sum.put("kuaiDi", fs.getKuaiDi());
			// 广告费
			sum.put("adRate", fs.getAdRate());
			//利润=机票实收+快递费-快递成本-实际成本
			Double lr = fs.getSurePrice() + fs.getKuaiDi() -fs.getKuaiDiChengBen() - shiji;
			sum.put("lr", lr);
			
			summary.put(pojo, sum);
		}
		return summary;
	}
	
	/**
	 * 销售平台利润
	 * @param sheet
	 * @param r
	 * @param profitList
	 */
	public static int writeSaleProfit(WritableSheet sheet, int row, Map<String, Map<String, Double>> summary) {
		summary = sortMapByKey(summary);
		int r = row;
		// 插入利润
		String[] profitTitle = {"销售平台", "张数", "机票实收款", "机票成本", "快递成本", "快递费", "换开利润", "广告费", "净利润"};
		
		Label label = null;
		jxl.write.Number number = null;
		WritableCellFormat bodyFormat = getLabelFormat();
		WritableCellFormat numFormat = getNumberFormat();
		
		// 插入标题
		for (int i = 0; i < profitTitle.length; i++) {
			insertLabel(sheet, label, i, r, profitTitle[i], getProfitHeader());
		} 
		r ++;
		// 插入内容
		/*Double sumCount = 0.0;
		Double sumSurePrice = 0.0;
		Double sumShiJi = 0.0;
		Double sumKuaiDiCB = 0.0;
		Double sumKuaiDi = 0.0;
		Double sumLr = 0.0;*/
		Set<String> keySet = summary.keySet();
		Object[] keys = keySet.toArray();
		Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		Arrays.sort(keys, com);
		for (Object obj : keys) {
			String key = obj.toString();
			Map<String, Double> sum = summary.get(key);
			int i = 0;
			// surePrice shiJi kuaiDiCB kuaiDi lr
			insertLabel(sheet, label, i++, r, key, bodyFormat);
			insertNumber(sheet, number, i++, r, sum.get("count"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("surePrice"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("shiJi"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("kuaiDiCB"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("kuaiDi"), numFormat);
			insertNumber(sheet, number, i++, r, 0, numFormat);//换开利润
			insertNumber(sheet, number, i++, r, sum.get("adRate"), numFormat);//广告费adRate
			//insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sum.get("lr")));
			insertFormula(sheet, i++, r, "SUM(C"+(r+1)+"-D"+(r+1)+"+F"+(r+1)+"-E"+(r+1)+"+G"+(r+1)+")", numFormat);
			/*sumCount += sum.get("count");
			sumSurePrice += sum.get("surePrice");
			sumShiJi += sum.get("shiJi");
			sumKuaiDiCB += sum.get("kuaiDiCB");
			sumKuaiDi += sum.get("kuaiDi");
			sumLr += sum.get("lr");*/
			r++;
		}
		int i = 0;
		insertLabel(sheet, label, i++, r, "总计", getTotalLabelFormat());
		/*insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumCount), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumSurePrice), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumShiJi), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumKuaiDiCB), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumKuaiDi), getTotalBody());
		insertLabel(sheet, label, i++, r, "", getTotalBody());
		insertLabel(sheet, label, i++, r, "", getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumLr), getTotalBody());*/
		
		WritableCellFormat totalNumber = getTotalNumberFormat();
		
		insertFormula(sheet, i++, r, "SUM(B"+(row+2)+":B"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(C"+(row+2)+":C"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(D"+(row+2)+":D"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(E"+(row+2)+":E"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(F"+(row+2)+":F"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(G"+(row+2)+":G"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(H"+(row+2)+":H"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(I"+(row+2)+":I"+(r)+")", totalNumber);
		r ++;
		
		return r - row;
	}
	
	/**
	 * 采购平台利润
	 * @param sheet
	 * @param r
	 */
	public static int writeBuyProfit(WritableSheet sheet, int row, Map<String, Map<String, Double>> summary) {
		summary = sortMapByKey(summary);
		int r = row;
		// 插入利润
		String[] profitTitle = {"采购平台", "张数", "机票实收款", "机票实际成本", "利润"};
		
		Label label = null;
		jxl.write.Number number = null;
		WritableCellFormat bodyFormat = getLabelFormat();
		WritableCellFormat numFormat = getNumberFormat();
		
		// 插入标题
		for (int i = 0; i < profitTitle.length; i++) {
			insertLabel(sheet, label, i, r, profitTitle[i], getProfitHeader());
		}
		r++;
		// 插入内容
		/*Double sumCount = 0.0;
		Double sumSurePrice = 0.0;
		Double sumShiJi = 0.0;
		Double sumLr = 0.0;*/
		//Set<String> keys = summary.keySet();
		Set<String> keySet = summary.keySet();
		Object[] keys = keySet.toArray();
		Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		Arrays.sort(keys, com);
		for (Object obj : keys) {
			String key = obj.toString();
			Map<String, Double> sum = summary.get(key);
			int i = 0;
			// surePrice shiJi kuaiDiCB kuaiDi lr
			insertLabel(sheet, label, i++, r, key, bodyFormat);
			insertNumber(sheet, number, i++, r, sum.get("count"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("surePrice"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("shiJi"), numFormat);
			insertNumber(sheet, number, i++, r, sum.get("lr"), numFormat);
			/*sumCount += sum.get("count");
			sumSurePrice += sum.get("surePrice");
			sumShiJi += sum.get("shiJi");
			sumLr += sum.get("lr");*/
			r++;
		}
		int i = 0;
		insertLabel(sheet, label, i++, r, "总计", getTotalLabelFormat());
		/*insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumCount), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumSurePrice), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumShiJi), getTotalBody());
		insertLabel(sheet, label, i++, r, StringUtilsc.formatDoubleStr(sumLr), getTotalBody());*/
		
		WritableCellFormat totalNumber = getTotalNumberFormat();
		
		insertFormula(sheet, i++, r, "SUM(B"+(row+2)+":B"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(C"+(row+2)+":C"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(D"+(row+2)+":D"+(r)+")", totalNumber);
		insertFormula(sheet, i++, r, "SUM(E"+(row+2)+":E"+(r)+")", totalNumber);
		r++;
		
		return r - row;
	}
	
	/**
	 * OrderReport
	 * @param filePath
	 * @param orderReportList
	 */
	public static void writeOrderReportToExcel(String filePath, List<OrderReportVo> orderReportList) {
		try {
			String[] title = { "航班类型", "销售平台", "店铺名称", "外部订单号", "采购订单号", "创单日期", "预订PNR", 
					"出票PNR", "出票日期", "航程类型", "承运人", "航班号", "出发地-目的地", "起飞日期", "起飞时间",
					"舱位", "乘机人", "乘机人类型", "票号", "销售票面价", "销售机建", "销售燃油", "销售价", "销售价（含税）",
					"销售舱位", "收款方式", "收款账户", "收款合计含税", "采购地", "采购票面价", "采购机建", "采购燃油", 
					"返点", "后返", "促销返点", "采购价", "采购价（含税）", "采购价-后返(含税)", "采购价-后返-促销费(含税)", 
					"返钱", "促销费", "采购付款商户号", "采购支付交易号", "利润(一)", "利润（二）", "利润（三）", "利润（四）", 
					"佣金", "快递费", "保险份额", "保险金额", "保险利润", "是否换开", "出票office号", "出票状态", "出票员", 
					"出票备注", "政策ID", "政策人员", "政策备注" };
			
			// 开始时间
			long start = System.currentTimeMillis();
			
			File file = new File(filePath);
			
			// 创建Excel工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			
			List<List<OrderReportVo>> listSub = getSubList(orderReportList, 65000);
			
			List<OrderReportVo> list = null;
			
			Label label = null;
			jxl.write.Number number = null;
			WritableCellFormat bodyFormat = getLabelFormat();
			WritableCellFormat numFormat = getNumberFormat();
			
			int len = listSub.size();
			for (int n = 0; n < len; n++) {
				list =  listSub.get(n);
				
				// 创建工作表
				WritableSheet sheet = workbook.createSheet("sheet"+n, 0);
				// 插入标题
				for (int i = 0; i < title.length; i++) {
					// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z 
					// 在Label对象的子对象中指明单元格的位置和内容 
					insertLabel(sheet, label, i, 0, title[i], getHeader());
					sheet.setColumnView(i, 13);
				}
				
				// 填充数据
				int row = 1;
				
				OrderReportVo checkVo = null;
				StringBuffer depArr = new StringBuffer();
				StringBuffer flightNum = new StringBuffer();
				StringBuffer departureDate = new StringBuffer();
				StringBuffer departureTime = new StringBuffer();
				StringBuffer cabin = new StringBuffer();
				
				Iterator<OrderReportVo> it = list.iterator();
				String formula = null;
				while (it.hasNext()) {
					OrderReportVo vo = it.next();
					
					if (checkVo == null) {
						checkVo = vo;
						depArr.append(vo.getDepAircode()+"-"+vo.getArrAircode());
						
						flightNum.append(vo.getFlightNum());
						
						departureDate.append(vo.getDepartureDate());
						
						departureTime.append(vo.getDepartureTime());
						
						cabin.append(vo.getCabin());
					} else if (checkVo.getBpid().equals(vo.getBpid())) {
						row --;
						depArr.append("; ");
						depArr.append(vo.getDepAircode()+"-"+vo.getArrAircode());
						
						flightNum.append("; ");
						flightNum.append(vo.getFlightNum());
						
						departureDate.append("; ");
						departureDate.append(vo.getDepartureDate());
						
						departureTime.append("; ");
						departureTime.append(vo.getDepartureTime());
						
						cabin.append("; ");
						cabin.append(vo.getCabin());
						
						row --;
					} else {
						depArr.setLength(0);
						depArr.append(vo.getDepAircode()+"-"+vo.getArrAircode());
						
						flightNum.setLength(0);
						flightNum.append(vo.getFlightNum());
						
						departureDate.setLength(0);
						departureDate.append(vo.getDepartureDate());
						
						departureTime.setLength(0);
						departureTime.append(vo.getDepartureTime());
						
						cabin.setLength(0);
						cabin.append(vo.getCabin());
					}
					
					int col = 0;
					// 航班类型 航班类型N：国内 I：国际
					if ("N".equals(vo.getFlightClass())) {
						insertLabel(sheet, label, col++, row, "国内", bodyFormat);
					} else if ("I".equals(vo.getFlightClass())) {
						insertLabel(sheet, label, col++, row, "国际", bodyFormat);
					} else {
						insertLabel(sheet, label, col++, row, "", bodyFormat);
					}
					// 销售平台
					insertLabel(sheet, label, col++, row, vo.getDistributorCh(), bodyFormat);
					// 店铺名称
					insertLabel(sheet, label, col++, row, vo.getShopNameCh(), bodyFormat);
					// 外部订单号
					insertLabel(sheet, label, col++, row, vo.getOrderNo(), bodyFormat);
					// 采购订单号
					insertLabel(sheet, label, col++, row, vo.getBillId(), bodyFormat);
					// 创单日期
					insertLabel(sheet, label, col++, row, vo.getCreateTime(), bodyFormat);
					// 预订PNR
					insertLabel(sheet, label, col++, row, vo.getBoPnrCode(), bodyFormat);
					// 出票PNR
					insertLabel(sheet, label, col++, row, vo.getSoPnrCode(), bodyFormat);
					// 出票日期
					insertLabel(sheet, label, col++, row, vo.getTicketDate(), bodyFormat);
					// 航程类型  S：单程 D：往返 T其它 L 联程 X 多程
					insertLabel(sheet, label, col++, row, getFlightType(vo.getFlightType()), bodyFormat);
					// 承运人
					insertLabel(sheet, label, col++, row, vo.getCarrier(), bodyFormat);
					// 航班号
					//insertLabel(sheet, label, col++, row, vo.getFlightNum(), bodyFormat);
					insertLabel(sheet, label, col++, row, flightNum.toString(), bodyFormat);
					// 出发地-抵达地
					//insertLabel(sheet, label, col++, row, vo.getDepAircode()+"-"+vo.getArrAircode(), bodyFormat);
					insertLabel(sheet, label, col++, row, depArr.toString(), bodyFormat);
					// 起飞日期
					insertLabel(sheet, label, col++, row, departureDate.toString(), bodyFormat);
					//insertLabel(sheet, label, col++, row, vo.getDepartureDate(), bodyFormat);
					// 起飞时间
					insertLabel(sheet, label, col++, row, departureTime.toString(), bodyFormat);
					//insertLabel(sheet, label, col++, row, vo.getDepartureTime(), bodyFormat);
					// 舱位
					insertLabel(sheet, label, col++, row, cabin.toString(), bodyFormat);
					//insertLabel(sheet, label, col++, row, vo.getCabin(), bodyFormat);
					// 乘机人
					insertLabel(sheet, label, col++, row, vo.getName(), bodyFormat);
					// 乘机人类型  -1 留学生 0 成人 1 儿童  -2其他
					insertLabel(sheet, label, col++, row, getAgeType(vo.getAgeType()), bodyFormat);
					// 票号
					insertLabel(sheet, label, col++, row, vo.getEticketNum(), bodyFormat);
					// 销售票面价
					insertNumber(sheet, number, col++, row, vo.getSpPrice(), numFormat);
					// 销售机建
					insertNumber(sheet, number, col++, row, vo.getSpTaxFee(), numFormat);
					// 销售燃油
					insertNumber(sheet, number, col++, row, vo.getSpOilFee(), numFormat);
					// 销售价
					insertNumber(sheet, number, col++, row, vo.getSpCost(), numFormat);
					// 销售价（含税） =  销售价 + 销售基建 + 销售燃油
					formula = "SUM(W"+(row+1)+"+U"+(row+1)+"+V"+(row+1)+")";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 销售舱位
					insertLabel(sheet, label, col++, row, vo.getCabin(), bodyFormat);
					// 收款方式
					insertLabel(sheet, label, col++, row, getPayWay(vo.getPayWay()), bodyFormat);
					// 收款账户
					insertLabel(sheet, label, col++, row, vo.getAccountInfo(), bodyFormat);
					// 收款合计含税
					Double saleAndTax = vo.getPrice() + vo.getSpTaxFee() + vo.getSpOilFee();
					insertNumber(sheet, number, col++, row, saleAndTax * vo.getPassengerCount(), numFormat);
					// 采购地
					insertLabel(sheet, label, col++, row, vo.getPurchasePlaceCh(), bodyFormat);
					// 采购票面价
					insertNumber(sheet, number, col++, row, vo.getBpPrintPrice(), numFormat);
					// 采购机建
					insertNumber(sheet, number, col++, row, vo.getBpTaxFee(), numFormat);
					// 采购燃油
					insertNumber(sheet, number, col++, row, vo.getBpOilFee(), numFormat);
					// 返点
					insertNumber(sheet, number, col++, row, vo.getBackPoint(), numFormat);
					// 后返
					insertNumber(sheet, number, col++, row, vo.getAfterPoint(), numFormat);
					// 促销返点
					insertNumber(sheet, number, col++, row, vo.getStickingPoint(), numFormat);
					// 采购价
					insertNumber(sheet, number, col++, row, vo.getBpCost(), numFormat);
					// 采购价（含税） = 采购价 + 采购机建 + 采购燃油
					formula = "SUM(AJ"+(row+1)+"+AE"+(row+1)+"+AF"+(row+1)+")";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 采购价-后返(含税)  = 采购价 - (采购票面价 * 后返) / 100
					Double buyAndTax = vo.getPrice() + vo.getBpTaxFee() + vo.getBpOilFee();
					formula = "SUM("+buyAndTax+"-AD"+(row+1)+"*(AH"+(row+1)+")/100)";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 采购价-后返-促销费(含税)  = 采购价-后返(含税) - (采购票面价 * 促销返点) / 100
					formula = "SUM("+buyAndTax+"-AD"+(row+1)+"*(AH"+(row+1)+")/100-AD"+(row+1)+"*(AI"+(row+1)+")/100)";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 返钱  = 采购票面价 * 后返 / 100
					formula = "SUM(AD"+(row+1)+"*(AH"+(row+1)+")/100)";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 促销费  = 采购票面价 * 促销返点 / 100
					formula = "SUM(AD"+(row+1)+"*(AI"+(row+1)+")/100)";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 采购付款商户号
					insertLabel(sheet, label, col++, row, vo.getBoMerchantNo(), bodyFormat);
					// 采购支付交易号
					insertLabel(sheet, label, col++, row, vo.getTransactionNo(), bodyFormat);
					// 利润(一) = 销售价（含税）- 采购价（含税）- 佣金
					formula = "SUM("+saleAndTax+"-"+buyAndTax+"-AV"+(row+1)+")";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 利润（二）= 销售价（含税） - (采购价（含税） - 采购票面价 * 后返 / 100) - 佣金
					formula = "SUM("+saleAndTax+"-("+buyAndTax+"-AD"+(row+1)+"*(AH"+(row+1)+")/100)-AV"+(row+1)+")";
					insertFormula(sheet, col++, row, "SUM(X"+(row+1)+"-(AL"+(row+1)+"-AD"+(row+1)+"*(AH"+(row+1)+")/100)-"+"AV"+(row+1)+")", numFormat);
					// 利润（三）= 销售价（含税） - ((采购价（含税） - 采购票面价 * 后返 / 100 - (采购票面价 * 促销返点 / 100)) - 佣金
					formula = "SUM("+saleAndTax+"-(("+buyAndTax+"-AD"+(row+1)+"*(AH"+(row+1)+")/100)-(AD"+(row+1)+"*(AI"+(row+1)+")/100))-AV"+(row+1)+")";
					insertFormula(sheet, col++, row, formula, numFormat);
					// 利润（四）
					insertLabel(sheet, label, col++, row, "", bodyFormat);
					// 佣金
					insertLabel(sheet, label, col++, row, vo.getCommission(), bodyFormat);
					// 快递费
					insertLabel(sheet, label, col++, row, vo.getJourneySheetPrice(), bodyFormat);
					// 保险份额
					insertLabel(sheet, label, col++, row, vo.getBoBxCount(), bodyFormat);
					// 保险金额
					insertLabel(sheet, label, col++, row, vo.getBoBxFee(), bodyFormat);
					// 保险利润
					Double bxFeeLr = (vo.getSoBxFee()-vo.getBoBxFee()) * vo.getBxCount();
					insertLabel(sheet, label, col++, row, bxFeeLr, bodyFormat);
					// 是否换开  0 正常 1换开新订单 2已经换开
					insertLabel(sheet, label, col++, row, getOpenStatus(vo.getOpenStatus()), bodyFormat);
					// 出票office号
					insertLabel(sheet, label, col++, row, vo.getRtOffno(), bodyFormat);
					// 出票状态
					insertLabel(sheet, label, col++, row, getSlfStatus(vo.getSlfStatus()), bodyFormat);
					// 出票员
					insertLabel(sheet, label, col++, row, vo.getLockUser(), bodyFormat);
					// 出票备注
					insertLabel(sheet, label, col++, row, vo.getSlfRemark(), bodyFormat);
					// 政策ID
					insertLabel(sheet, label, col++, row, vo.getPolicyId(), bodyFormat);
					// 政策人员
					insertLabel(sheet, label, col++, row, "", bodyFormat);
					// 政策备注
					insertLabel(sheet, label, col++, row, vo.getStatement(), bodyFormat);
					
					row++;
//					checkVo = vo;
					it.remove();
				}
				
			}
			
			workbook.write();
			workbook.close();
			long end = System.currentTimeMillis();
			info("-完成该操作共用的时间是"+(end-start)+"毫秒");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入单元格数据
	 * @param sheet
	 * @param col
	 * @param row
	 * @param msg
	 */
	public static void insertLabel(WritableSheet sheet, Label label, Integer col, Integer row, Object obj, WritableCellFormat format) {
		try {
			label = new Label(col, row, formatStr(obj), format);
			sheet.addCell(label);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入数字
	 * @param sheet
	 * @param col
	 * @param row
	 * @param d
	 */
	public static void insertNumber(WritableSheet sheet, jxl.write.Number num , Integer col, Integer row, Object obj, WritableCellFormat format) {
		try {
			Double d = Double.parseDouble(obj.toString());
			num = new jxl.write.Number(col, row, d, format);
			sheet.addCell(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入公式
	 * @param sheet
	 * @param col
	 * @param row
	 * @param formula
	 */
	public static void insertFormula(WritableSheet sheet, Integer col, Integer row, String formula, WritableCellFormat format) {
		try {
			Formula form = new Formula(col, row, formula, format);
			sheet.addCell(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//头样式
	private static WritableCellFormat getHeader() {
		try {
			WritableFont font = new WritableFont(WritableFont.TIMES, 11 ,WritableFont.BOLD);//定义字体
			font.setColour(Colour.RED);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			//format.setBackground(Colour.YELLOW);
			//format.setWrap(true);
			return format;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 利润头样式
	private static WritableCellFormat getProfitHeader() {
		try {
			WritableFont font = new WritableFont(WritableFont.TIMES, 11 ,WritableFont.BOLD);//定义字体
			font.setColour(Colour.RED);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setBackground(Colour.YELLOW);
			return format;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Number样式
	public static WritableCellFormat getNumberFormat() {
		try {
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#0.00");
			WritableCellFormat format = new WritableCellFormat(nf);
			WritableFont font = new WritableFont(WritableFont.TIMES, 11 ,WritableFont.NO_BOLD);//定义字体
			font.setColour(Colour.BLACK);
			format.setFont(font);
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			
			return format;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// Label样式
	private static WritableCellFormat getLabelFormat() {
		try {
			WritableFont font = new WritableFont(WritableFont.TIMES, 11 ,WritableFont.NO_BOLD);//定义字体
			font.setColour(Colour.BLACK);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			//format.setWrap(true);
			return format;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 利润--总计--Label样式
	private static WritableCellFormat getTotalLabelFormat() {
		try {
			WritableFont font = new WritableFont(WritableFont.TIMES, 10 ,WritableFont.NO_BOLD);//定义字体
			font.setColour(Colour.BLACK);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setBackground(Colour.YELLOW);
			return format;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 利润--总计--Number样式
	private static WritableCellFormat getTotalNumberFormat() {
		try {
			jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#0.00");
			WritableCellFormat format = new WritableCellFormat(nf);
			WritableFont font = new WritableFont(WritableFont.TIMES, 10 ,WritableFont.NO_BOLD);//定义字体
			font.setColour(Colour.BLACK);
			format.setFont(font);
			format.setAlignment(jxl.format.Alignment.LEFT);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
			format.setBackground(Colour.YELLOW);
			return format;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 格式化
	private static String formatStr(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	public static String getFilePath(String fileDir, String aircode) {
		String file = "";
		try {
			file = URLDecoder.decode(HttpUtils.class.getClassLoader()
					.getResource("").toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		file = file.substring(5, file.indexOf("WEB-INF")) + fileDir + "/";
		String fileName = aircode + "_" + "$" + System.currentTimeMillis()
				+ ".xls";
		file = file + fileName;
		return file;
	}
	
	public static String writeEnd(String fileUrl) {
		String zipFile = ZipFileUtil.compressGzipFile(fileUrl);
		File f = new File(fileUrl);
		f.delete();
		return zipFile;
	}
	
	
	/**
	 * 拆分List 
	 * @param
	 * @param
	 * @return
	 */
	public static <T> List<List<T>> getSubList(List<T> listObj, int groupNum) {
		List<List<T>> resultList = new ArrayList<List<T>>();
		int loopCount = (listObj.size() % groupNum == 0) ? (listObj.size() / groupNum) : ((listObj.size() / groupNum)+1);
		for (int i = 0; i < loopCount; i++) {
			int startNum = i * groupNum;
			int endNum = (i+1) * groupNum;
			if (i == loopCount - 1) {
				endNum = listObj.size();
			}
			List<T> listObjSub = listObj.subList(startNum, endNum);
			resultList.add(listObjSub);
		}
		
		return resultList;
	}
	
	/**
	 * Map排序
	 * @param summary
	 * @return
	 */
	public static Map<String, Map<String, Double>> sortMapByKey(Map<String, Map<String, Double>> summary) {
		if (summary == null || summary.isEmpty()) {
			return null;
		}
		Map<String, Map<String, Double>> sortedMap = new TreeMap<String, Map<String, Double>>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return o1.compareTo(o2);
			}
		});
		sortedMap.putAll(summary);
		return sortedMap;
	}

	
	
	/**
	 * 航程类型
	 * @param flightType
	 * @return
	 */
	public static String getFlightType(String flightType) {
		if ("S".equals(flightType)) {
			return "单程";
		} else if ("D".equals(flightType)) {
			return "往返";
		} else if ("T".equals(flightType)) {
			return "其它";
		} else if ("L".equals(flightType)) {
			return "联程";
		} else if ("X".equals(flightType)) {
			return "多程";
		} 
		return "";
	}
	
	/**
	 * 支付方式
	 * @param payWay
	 * @return
	 */
	public static String getPayWay(String payWay) {
		if ("10".equals(payWay)) {
			return "快钱非担保";
		} else if ("2".equals(payWay)) {
			return "支付宝非担保";
		} else if ("1".equals(payWay)) {
			return "财付通非担保";
		} else if ("11".equals(payWay)) {
			return "快钱担保";
		} else if ("12".equals(payWay)) {
			return "支付宝担保";
		} else if ("13".equals(payWay)) {
			return "财付通担保";
		} else if ("-1".equals(payWay)) {
			return "其它";
		}
		return "";
	}
	
	/**
	 * 出票状态
	 * @param slfStatus
	 * @return
	 */
	public static String getSlfStatus(String slfStatus) {
		if("0".equals(slfStatus)) {
			return "订座成功等待支付";
		 } else if("1".equals(slfStatus)) {
			return "支付成功等待出票";
		 } else if("2".equals(slfStatus)) {
			return "出票完成";
		 } else if("5".equals(slfStatus)) {
			return "出票中";
		 } else if("12".equals(slfStatus)) {
			return "订单取消";
		 } else if("20".equals(slfStatus)) {
			return "等待座位确认";
		 } else if("30".equals(slfStatus)) {
			return "退票申请中";
		 } else if("31".equals(slfStatus)) {
			return "退票完成等待退款";
		 } else if("39".equals(slfStatus)) {
			return "退款完成";
		 } else if("40".equals(slfStatus)) {
			return "改签申请中";
		 } else if("42".equals(slfStatus)) {
			return "改签完成";
		 } else if("50".equals(slfStatus)) {
			return "未出票申请退款";
		 } else if("51".equals(slfStatus)) {
			return "订座成功等待价格确认";
		 } else if("60".equals(slfStatus)) {
			return "支付待确认";
		 } else if("61".equals(slfStatus)) {
			return "暂缓出票";
		 } else if("62".equals(slfStatus)) {
			return "订单超时";
		 } else if("-1".equals(slfStatus)) {
			return "其它";
		 }
		return "";
	}
	
	public static String getOpenStatus(int openStatus) {
		if (0 == openStatus) {
			return "正常";
		} else if (1 == openStatus) {
			return "换开新订单";
		} else if (2 == openStatus) {
			return "已经换开";
		} else {
			return "error";
		}
	}
	
	public static String getAgeType(int ageType) {
		// 乘机人类型  -1 留学生 0 成人 1 儿童  -2其他
		if (0 == ageType) {
			return "成人";
		} else if (1 == ageType) {
			return "儿童";
		} else if (-1 == ageType) {
			return "留学生";
		} else if (-2 == ageType) {
			return "其他";
		}
		return "error";
	}
	
	private static void info(String msg){
		OOLogUtil.info(msg, DownloadExcelUtils.class, null);
	}
	
}
