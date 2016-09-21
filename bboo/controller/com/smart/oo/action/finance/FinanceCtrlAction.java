package com.smart.oo.action.finance;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.utils.DownloadExcelUtils;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.OOUtils;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

/**
 * 财务报表管理的FinanceCtrlAction
 * 
 * @author eric
 * 
 */
public class FinanceCtrlAction extends BaseAction {
	private static final long serialVersionUID = 1189714785961056834L;

	@Autowired
	private FactoryServiceImpl servicef;
	
	// 分页查询
	public void queryByPage() {
		try {
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			String fileno = this.getParameter("fileno");
			
			String xiaoShouProjo = request.getParameter("xiaoShouProjo");
			String fareNO = request.getParameter("fareNO");
			String chuPiaoDate = request.getParameter("chuPiaoDate");// 出票日期
			String toChuPiaoDate = request.getParameter("toChuPiaoDate");// 至	
			String fightDate = request.getParameter("fightDate");// 起飞时间fightDate
			String toFightDate = request.getParameter("toFightDate");// 至
			String chuPiaoRen = request.getParameter("chuPiaoRen");
			String caiGouProjo = request.getParameter("caiGouProjo");
			String orderNO = request.getParameter("orderNO");
			String fileNO = request.getParameter("fileNO");
			
			JSONObject jsonObject = new JSONObject();
			long time = 5 * 24 * 60 * 60 * 1000;
			Date sdate = null;
			Date edate = null;
			if (StringUtils.isNotEmpty(chuPiaoDate) && StringUtils.isNotEmpty(toChuPiaoDate)) {
				sdate = DateFormat.getStringDate(chuPiaoDate, "yyyy-MM-dd");
				edate = DateFormat.getStringDate(toChuPiaoDate, "yyyy-MM-dd");
				long es = edate.getTime() - sdate.getTime();
				if (es > time) {
					jsonObject.put(ERROR, "出票日期间隔时间必须小于5天");
					this.writeStream(jsonObject, "utf-8");
					return;
				}
			}
			if (StringUtils.isNotEmpty(fightDate) && StringUtils.isNotEmpty(toFightDate)) {
				sdate = DateFormat.getStringDate(fightDate, "yyyy-MM-dd");
				edate = DateFormat.getStringDate(toFightDate, "yyyy-MM-dd");
				long es = edate.getTime() - sdate.getTime();
				if (es > time) {
					jsonObject.put(ERROR, "起飞日期间隔时间必须小于5天");
					this.writeStream(jsonObject, "utf-8");
					return;
				}
			}
			boolean cDate = StringUtils.isNotEmpty(chuPiaoDate) && StringUtils.isNotEmpty(toChuPiaoDate);
			boolean fDate = StringUtils.isNotEmpty(fightDate) && StringUtils.isNotEmpty(toFightDate);
			if (!(cDate || fDate)) {
				jsonObject.put(ERROR, "请输入出票日期和起飞日期");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			
			Page page = null;
			if(StringUtils.isNotEmpty(startpage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			
			FinanceSaleEntity fs = new FinanceSaleEntity();
			
			String[] xiaoShouProjos = null;
			if (StringUtils.isNotEmpty(xiaoShouProjo)) {
				xiaoShouProjos = xiaoShouProjo.split(",");
				if (xiaoShouProjos.length == 1) {
					fs.setXiaoShouPojo(xiaoShouProjos[0]);
					xiaoShouProjos = null;
				}
			}
			fs.setFareNO(fileno);
			fs.setMno(mno);
			fs.setFareNO(fareNO);
			fs.setChuPiaoDate(chuPiaoDate);
			fs.setChuaPiaoRen(chuPiaoRen);
			fs.setCaiGaoProjo(caiGouProjo);
			fs.setFightDate(fightDate);
			fs.setOrderNO(orderNO);
			fs.setFileno(fileNO);
			
			FinanceSaleEntity fs2 = null;
			if (StringUtils.isNotEmpty(toChuPiaoDate) || StringUtils.isNotEmpty(toFightDate)) {
				fs2 = new FinanceSaleEntity();
				fs.setFightDate(toFightDate);
				fs2.setChuPiaoDate(toChuPiaoDate);
			}
			
			List<FinanceSaleEntity> fslist = this.servicef.getFinanceSaleService().getCount(fs, fs2, xiaoShouProjos);
			page.setTotalCount(fslist.size());
			fslist = this.servicef.getFinanceSaleService().findFinanceSaleByCondition(fs, page, fs2, xiaoShouProjos);
			
			if (fslist.size() > 0) {
				String jsonString = JSON.toJSONString(fslist);
				jsonObject.put("list", jsonString);
			} else {
				jsonObject.put("list", "[]");
			}
			jsonObject.put("success", "success");
			jsonObject.put("total", page.getTotalCount());
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	// 下载Excel
	public void downloadExcel() {
		try {
			
			String fileno = this.getParameter("fileno");
			
			String xiaoShouProjo = request.getParameter("xiaoShouProjo");
			String fareNO = request.getParameter("fareNO");
			String chuPiaoDate = request.getParameter("chuPiaoDate");// 出票日期
			String toChuPiaoDate = request.getParameter("toChuPiaoDate");// 至	
			String fightDate = request.getParameter("fightDate");// 起飞时间fightDate
			String toFightDate = request.getParameter("toFightDate");// 至
			String chuPiaoRen = request.getParameter("chuPiaoRen");
			String caiGouProjo = request.getParameter("caiGouProjo");
			String orderNO=request.getParameter("orderNO");
			String fileNO=request.getParameter("fileNO");
			
			JSONObject jsonObject = new JSONObject();
			long time = 5 * 24 * 60 * 60 * 1000;
			Date sdate = null;
			Date edate = null;
			if (StringUtils.isNotEmpty(chuPiaoDate) && StringUtils.isNotEmpty(toChuPiaoDate)) {
				sdate = DateFormat.getStringDate(chuPiaoDate, "yyyy-MM-dd");
				edate = DateFormat.getStringDate(toChuPiaoDate, "yyyy-MM-dd");
				long es = edate.getTime() - sdate.getTime();
				if (es > time) {
					jsonObject.put(ERROR, "出票日期间隔时间必须小于5天");
					this.writeStream(jsonObject, "utf-8");
					return;
				}
			}
			if (StringUtils.isNotEmpty(fightDate) && StringUtils.isNotEmpty(toFightDate)) {
				sdate = DateFormat.getStringDate(fightDate, "yyyy-MM-dd");
				edate = DateFormat.getStringDate(toFightDate, "yyyy-MM-dd");
				long es = edate.getTime() - sdate.getTime();
				if (es > time) {
					jsonObject.put(ERROR, "起飞日期间隔时间必须小于5天");
					this.writeStream(jsonObject, "utf-8");
					return;
				}
			}
			boolean cDate = StringUtils.isNotEmpty(chuPiaoDate) && StringUtils.isNotEmpty(toChuPiaoDate);
			boolean fDate = StringUtils.isNotEmpty(fightDate) && StringUtils.isNotEmpty(toFightDate);
			if (!(cDate || fDate)) {
				jsonObject.put(ERROR, "请输入出票日期和起飞日期");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			
			FinanceSaleEntity fs = new FinanceSaleEntity();
			
			String[] xiaoShouProjos = null;
			if (StringUtils.isNotEmpty(xiaoShouProjo)) {
				xiaoShouProjos = xiaoShouProjo.split(",");
				if (xiaoShouProjos.length == 1) {
					fs.setXiaoShouPojo(xiaoShouProjos[0]);
					xiaoShouProjos = null;
				}
			}
			
			fs.setFareNO(fileno);
			fs.setMno(mno);
			fs.setXiaoShouPojo(xiaoShouProjo);
			fs.setFareNO(fareNO);
			fs.setChuPiaoDate(chuPiaoDate);
			fs.setChuaPiaoRen(chuPiaoRen);
			fs.setCaiGaoProjo(caiGouProjo);
			fs.setFightDate(fightDate);
			fs.setOrderNO(orderNO);
			fs.setFileno(fileNO);
			
			FinanceSaleEntity fs2 = null;
			if (StringUtils.isNotEmpty(toChuPiaoDate) || StringUtils.isNotEmpty(toFightDate)) {
				fs2 = new FinanceSaleEntity();
				fs.setFightDate(toFightDate);
				fs2.setChuPiaoDate(toChuPiaoDate);
			}
			
			List<FinanceSaleEntity> fslist = this.servicef.getFinanceSaleService().download(fs, fs2);
			
			if (null != fslist && fslist.size() > 0) {
				//String file_name = DateFormat.getSysdateString("yyyyMMdd"+"利润报表");
				String file_name = "profitReport";
				String filePath = DownloadExcelUtils.getFilePath("jsp/files/finance", file_name);
				
				DownloadExcelUtils.writeToExcel(filePath, fslist);
				
				String zipFilePath = DownloadExcelUtils.writeEnd(filePath);
				zipFilePath = "jsp" + zipFilePath.split("jsp")[1];
				
				info(file_name+"下载成功");
				
				jsonObject.put("zipFilePath", zipFilePath);
			} else {
				jsonObject.put("error", "数据为空");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	
	// 获取利润--通过采购平台
	public void getProfit() {
		try {
			
			String xiaoShouProjo = request.getParameter("xiaoShouProjo");
			String fareNO = request.getParameter("fareNO");
			String chuPiaoDate = request.getParameter("chuPiaoDate");// 出票日期
			String toChuPiaoDate = request.getParameter("toChuPiaoDate");// 至	
			String fightDate = request.getParameter("fightDate");// 起飞时间fightDate
			String toFightDate = request.getParameter("toFightDate");// 至
			String chuPiaoRen = request.getParameter("chuPiaoRen");
			String caiGouProjo = request.getParameter("caiGouProjo");
			String orderNO=request.getParameter("orderNO");
			String fileNO=request.getParameter("fileNO");
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			
			FinanceSaleEntity fs = new FinanceSaleEntity();
			String[] xiaoShouProjos = null;
			if (StringUtils.isNotEmpty(xiaoShouProjo)) {
				xiaoShouProjos = xiaoShouProjo.split(",");
				if (xiaoShouProjos.length == 1) {
					fs.setXiaoShouPojo(xiaoShouProjos[0]);
					xiaoShouProjos = null;
				}
			}
			fs.setMno(mno);
			fs.setXiaoShouPojo(xiaoShouProjo);
			fs.setFareNO(fareNO);
			fs.setChuPiaoDate(chuPiaoDate);
			fs.setChuaPiaoRen(chuPiaoRen);
			fs.setCaiGaoProjo(caiGouProjo);
			fs.setFightDate(fightDate);
			fs.setOrderNO(orderNO);
			fs.setFileno(fileNO);
			
			FinanceSaleEntity fs2 = new FinanceSaleEntity();
			fs2.setChuPiaoDate(toChuPiaoDate);
			fs.setFightDate(toFightDate);
			
			List<Object> list = this.servicef.getFinanceSaleService().statisticsProfit(fs, fs2, xiaoShouProjos);
			JSONObject jsonObject = new JSONObject();
			if (list != null && list.size() > 0) {
				String jsonString = JSON.toJSONString(list);
				jsonObject.put("list", jsonString);
			} else {
				jsonObject.put("list", "[]");
			}
			jsonObject.put("success", "success");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}

	/**
	 * 获取采购平台
	 */
	public void getCaiGaoProjos() {
		try {
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			String xiaoShouPojo = request.getParameter("xiaoShouPojo");
			FinanceSaleEntity financeSale = new FinanceSaleEntity();
			if (StringUtils.isNotEmpty(xiaoShouPojo)) {
				financeSale.setXiaoShouPojo(xiaoShouPojo);
			}
			financeSale.setMno(mno);
			List<String> caiGouProjos = this.servicef.getFinanceSaleService().queryCaiGouPojos(financeSale);
			JSONObject jsonObject = new JSONObject();
			if (caiGouProjos != null && caiGouProjos.size() > 0) {
				String result = JSONArray.fromObject(caiGouProjos).toString();
				jsonObject.put("list", result);
			} else {
				jsonObject.put("list", "[]");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	/**
	 * 获取销售平台
	 */
	public void getXiaoShouPojos() {
		try {
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			String caiGaoProjo = request.getParameter("caiGaoProjo");
			FinanceSaleEntity financeSale = new FinanceSaleEntity();
			if (StringUtils.isNotEmpty(caiGaoProjo)) {
				financeSale.setCaiGaoProjo(caiGaoProjo);
			}
			financeSale.setMno(mno);
			List<String> xiaoShouPojos = this.servicef.getFinanceSaleService().queryXiaoShouPojos(financeSale);
			JSONObject jsonObject = new JSONObject();
			if (caiGaoProjo != null && xiaoShouPojos.size() > 0) {
				String result = JSON.toJSONString(xiaoShouPojos);
				jsonObject.put("list", result);
			} else {
				jsonObject.put("list", "[]");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, FinanceCtrlAction.class, null);
	}


}
