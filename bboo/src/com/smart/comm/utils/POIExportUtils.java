package com.smart.comm.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.smart.oo.from.RefundReportVo;
import com.smart.utils.StringUtils;

public class POIExportUtils {

	public static String writeRefundToExcel(String filePath, List<RefundReportVo> refundList, String operate) {
		try {
			File file = new File(filePath);
			
			String[] type = new String[2];
			if ("refund".equals(operate)) {// 退票
				type[0] = "理论";
				type[1] = "实际";
			} else if ("reserve".equals(operate)) {// 留票
				type[0] = "客人";
				type[1] = "供应";
			} else {
				return "operate不能为空";
			}
			String headers[] = {"销售平台", "采购平台", "订单号", "订单生成时间", "订单状态", "PNR", "乘机人姓名", "乘机人类型", "票号", "出票时间", "航程类型", "航班号", "航司", "舱位", 
					"实际航班号", "实际舱位", "出发到达机场", "起飞时间", "退票时间", "操作人", "是否延误", "退票人数", type[0]+"舱位票面价", type[0]+"退票票面", type[0]+"燃油", type[0]+"基建", "" 
					+type[0]+"退票费率", type[0]+"退票费", type[0]+"退款金额", type[0]+"退款金额（含税）", type[1]+"舱位票面价", type[1]+"退票票面", type[1]+"燃油", ""
					+type[1]+"基建", type[1]+"退票费率", type[1]+"退票费", type[1]+"退款金额", type[1]+"退款金额（含税）", "利润"};
			
			// 开始时间
			long start = System.currentTimeMillis();
			
			// 创建Excel工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			List<List<RefundReportVo>> listSub = getSubList(refundList, 65000);
			int sheetNum = 0;
			for (List<RefundReportVo> list : listSub) {
				sheetNum ++;
				//创建Excel工作表对象 
				HSSFSheet sheet = workbook.createSheet("sheet"+sheetNum);
				// 插入头
				for (int i = 0; i < headers.length; i++) {
					addHearderCell(workbook, sheet, 0, i, headers[i]);
					sheet.setColumnWidth((short) i, (short)(3500));
				}
				
				int rowNum = 1;// 行
				// 插入内容
				for (RefundReportVo vo : list) {
					int colNum = 0;// 列
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getDistributorCh());
					
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getPurchasePlaceCh());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getOrderNo());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getOrderCreateDate());
					addBodyCell(workbook, sheet, rowNum, colNum++, getOrderStatusCh(vo.getOrderStatus()));
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getPnrCode());
					
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getName());
					addBodyCell(workbook, sheet, rowNum, colNum++, getAgeTypeCh(vo.getAgeType()));
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getEticketNum());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getAddTime());
					addBodyCell(workbook, sheet, rowNum, colNum++, getFlightTypeCh(vo.getFlightType()));
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getFlightNum());
					
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getCarrier());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getCabin());
					addBodyCell(workbook, sheet, rowNum, colNum++, StringUtils.isNotEmpty(vo.getActFltno()) ? vo.getActFltno() : vo.getFlightNum());
					addBodyCell(workbook, sheet, rowNum, colNum++, StringUtils.isNotEmpty(vo.getActCabin()) ? vo.getActCabin() : vo.getCabin());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getDepAircode()+"-"+vo.getArrAircode());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getDepartureDate()+" "+vo.getDepartureTime());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getRefundTime());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getOperator());
					addBodyCell(workbook, sheet, rowNum, colNum++, getIsdelayCh(vo.getIsdelay()));
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getPcount());
					
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getFace());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getBaseFace());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getYq());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getTax());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getRate());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getFee());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getRefund());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getActRefund());
					
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getFace2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getBaseFace2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getYq2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getTax2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getRate2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getFee2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getRefund2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getActRefund2());
					addBodyCell(workbook, sheet, rowNum, colNum++, vo.getLr());
					rowNum++;
				}
				workbook.write(new FileOutputStream(file));
				workbook.cloneSheet(sheetNum-1);
			}
			
			// 开始时间
			long end = System.currentTimeMillis();
			info("下载花费时间为："+(end-start));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	private static void addHearderCell(HSSFWorkbook workbook, HSSFSheet sheet, int rowNum, int colNum, String str) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short) 300);
		HSSFCell cell = row.createCell((short) colNum);
		HSSFCellStyle style = getHeaderStyle(workbook);
		cell.setCellStyle(style);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(str == null ? "" : str);
	}
	private static void addBodyCell(HSSFWorkbook workbook, HSSFSheet sheet, int rowNum, int colNum, String str) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell((short) colNum);
		HSSFCellStyle style = getBodyStyle(workbook);
		cell.setCellStyle(style);
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(str == null ? "" : str);
	}
	private static void addBodyCell(HSSFWorkbook workbook, HSSFSheet sheet, int rowNum, int colNum, double d) throws Exception {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell((short) colNum);
		HSSFCellStyle style = getBodyStyle(workbook);
		cell.setCellStyle(style);
		cell.setCellValue(d);
	}
	
	private static HSSFCellStyle getHeaderStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setFillForegroundColor(HSSFColor.YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		font.setFontHeightInPoints((short) 11);
		style.setFont(font);
		return style;
	}
	
	private static HSSFCellStyle getBodyStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		return style;
	}
	
	
	/**
	 * 拆分List 
	 * @param listObj
	 * @param groupNum
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
	 * 获取航程类型 中文
	 * @param flightType
	 * @return 航程类型 S：单程 D：往返 T其它 L 联程 X 多程
	 */
	public static String getFlightTypeCh(String flightType) {
		if("S".equals(flightType)) {
			return "单程";
		} else if("D".equals(flightType)) {
			return "往返";
		} else if("T".equals(flightType)) {
			return "其他";
		} else if("L".equals(flightType)) {
			return "联程";
		} else if("X".equals(flightType)) {
			return "多程";
		}
		return flightType;
	}
	
	public static String getIsdelayCh(String isdelay) {
		if ("1".equals(isdelay)) {
			return "正常";
		} else if ("2".equals(isdelay)) {
			return "延误";
		}
		return isdelay;
	}
	
	/**
	 *  订单状态
	 */
	public static String getOrderStatusCh(String orderStatus) {
		if (StringUtils.isNotEmpty(orderStatus)) {
			int os = Integer.parseInt(orderStatus);
			switch (os) {
			case 0:
				return "订座成功等待支付(人工)";
			case 1:
				return "支付成功等待出票";
			case 2:
				return "出票完成";
			case 5:
				return "出票中";
			case 12:
				return "订单取消";
			case 20:
				return "等待座位确认";
			case 30:
				return "退票申请中";
			case 31:
				return "退票完成等待退款";
			case 39:
				return "退款完成";
			case 40:
				return "改签申请中";
			case 42:
				return "改签完成";
			case 50:
				return "未出票申请退款";
			case 51:
				return "订座成功等待价格确认";
			case 60:
				return "支付待确认（支付失败）";
			case 61:
				return "暂缓出票（出票失败）";
			case 62:
				return "订单超时";
			case 70:
				return "退票留票中";
			case 99:
				return "订座成功等待支付（系统）";
			case -1:
				return "其它";
			default:
				return orderStatus;
			}
		}
		return "";
	}
	
	/**
	 * 乘机人类型  乘客类别 -1 留学生 0 成人 1 儿童 2婴儿 -2其他
	 * @param ageType
	 * @return
	 */
	public static String getAgeTypeCh(String ageType) {
		if (StringUtils.isNotEmpty(ageType)) {
			switch (Integer.parseInt(ageType)) {
			case  -1:
				return "留学生";
			case 0:
				return "成人";
			case 1:
				return "儿童";
			case 2:
				return "婴儿";
			case -2:
				return "其他";
			default:
				return ageType;
			}
		}
		return "";
	}
	
	private static void info(String msg){
		OOLogUtil.info(msg, POIExportUtils.class, null);
	}
}
