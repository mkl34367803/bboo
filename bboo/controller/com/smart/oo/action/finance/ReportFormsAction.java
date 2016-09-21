package com.smart.oo.action.finance;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.entity.ReportFormsEntity;
import com.smart.comm.utils.DownloadExcelUtils;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.OOUtils;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

@Results({
	  @Result(name="query",location="/jsp/build/finance/doReportForms.jsp")
})
public class ReportFormsAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	private FactoryServiceImpl servicef;
    
	
	/**
	 * 分页查询
	 * @return
	 */
	public void queryByPage(){
		try {
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			String mnoType=this.request.getParameter("mnoType");
			String isUsed=this.request.getParameter("isUsed");
			
			User user=SecurityContext.getUser();
			String mno=OOUtils.getUserMerchant(user);
			ReportFormsEntity r=new  ReportFormsEntity();
			r.setMno(mno);
			r.setMnoType(mnoType);
			if (StringUtils.isNotEmpty(isUsed)) {
				r.setIsUsed(Integer.parseInt(isUsed));
			}
			List<ReportFormsEntity> rlist=null;
			rlist=this.servicef.getReportFormsService().getCount(r);
			
			Page page = null;
			if(StringUtils.isNotEmpty(startpage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
				page.setTotalCount(rlist.size());
			}
			rlist=this.servicef.getReportFormsService().findByPage(r, page);
			JSONObject result = new JSONObject();
			if(rlist!=null&&rlist.size()>0){
				String str=JSON.toJSONString(rlist);
				result.put("success", "查询成功");
				result.put("rows", str);
				result.put("total", page.getTotalCount());
			} else {
				result.put("success", "查询成功");
				result.put("rows", "[]");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 保存和修改
	 */
	public void saveOrUpdate(){
		try {
			String id = request.getParameter("id");
			String fieldName = request.getParameter("fieldName");
			String mnoType = request.getParameter("mnoType");
			String isUsed = request.getParameter("isUsed");
			String description = request.getParameter("description");
			
			User user=SecurityContext.getUser();
			String mno=OOUtils.getUserMerchant(user);
			
			ReportFormsEntity reportForms = new ReportFormsEntity();;
			
			JSONObject jsonObject=new JSONObject();
			if (StringUtilsc.isNotEmptyAndNULL(id)) {// 修改
				reportForms.setId(id);
				reportForms.setMno(mno);
				reportForms = this.servicef.getReportFormsService().getById(reportForms);
				
				reportForms.setFieldName(fieldName);
				reportForms.setMnoType(mnoType);
				reportForms.setIsUsed(Integer.parseInt(isUsed));
				reportForms.setDescription(description);
				
				this.servicef.getReportFormsService().updateFieldName(reportForms);
				jsonObject.put("success", "更新成功");
				info("ReportFormsEntity更新成功，id为："+id);
			} else {// 保存
				reportForms.setId(UniqId.getInstance().getStrId());
				reportForms.setFieldName(fieldName);
				reportForms.setMnoType(mnoType);
				reportForms.setMno(mno);
				reportForms.setIsUsed(Integer.parseInt(isUsed));
				reportForms.setDescription(description);
				reportForms.setCreateDate(DateFormat.getStandardDate(new Date()));
				
				this.servicef.getReportFormsService().saveFieldName(reportForms);
				jsonObject.put("success", "保存成功");
				info("ReportFormsEntity保存成功");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	// 删除
	public void delete() {
		try {
			String id = request.getParameter("id");
			JSONObject jsonObject=new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				User user=SecurityContext.getUser();
				String mno=OOUtils.getUserMerchant(user);
				this.servicef.getReportFormsService().deleteReportForms(id, mno);
				jsonObject.put("success", "删除成功");
				info("ReportFormsEntity删除成功，id为："+id);
			} else {
				jsonObject.put("error", "ID不存在");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 下载Excel
	 */
	public void downloadExcel() {
		try {
			JSONObject jsonObject = new JSONObject();
			
			String mnoType = request.getParameter("mnoType");
			User user=SecurityContext.getUser();
			String mno=OOUtils.getUserMerchant(user);
			ReportFormsEntity searchReportForms = new ReportFormsEntity();
			searchReportForms.setMno(mno);
			searchReportForms.setMnoType(mnoType);
			searchReportForms.setIsUsed(1);
			List<ReportFormsEntity> reportFormsEntities = this.servicef.getReportFormsService().queryReportForms(searchReportForms);
			if (null != reportFormsEntities && reportFormsEntities.size() > 0) {
				if (OOComms.REOPRTTYPE_SALE.equals(mnoType)) {
					String zipFilePath = downloadSaleExcel(reportFormsEntities);
					if (null == zipFilePath) {
						jsonObject.put("error", "数据为空");
					} else {
						jsonObject.put("zipFilePath", zipFilePath);
					}
				} else if (OOComms.REOPRTTYPE_BOOK.equals(mnoType)) {
					jsonObject.put("error", "不存在");
				} else if (OOComms.REOPRTTYPE_FINANCE.equals(mnoType)) {
					jsonObject.put("error", "不存在");
				} else if (OOComms.REOPRTTYPE_PROFIT.equals(mnoType)) {
					jsonObject.put("error", "不存在");
				} else if (OOComms.REOPRTTYPE_PURCHASE.equals(mnoType)) {
					jsonObject.put("error", "不存在");
				}
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
	
	/**
	 * 下载销售报表----FinanceSale
	 * @param reportFormsEntities
	 * @return
	 * @throws Exception
	 */
	public String downloadSaleExcel (List<ReportFormsEntity> reportFormsEntities) throws Exception {
		FinanceSaleEntity fs = new FinanceSaleEntity();
		List<FinanceSaleEntity> fslist = this.servicef.getFinanceSaleService().download(fs, null);
		if (null != fslist && fslist.size() > 0) {
			//String file_name = DateFormat.getSysdateString("yyyyMMddHHmmss");
			String file_name = "saleReport";
			String filePath = DownloadExcelUtils.getFilePath("jsp/files/finance", file_name);
			
			DownloadExcelUtils.writeReportToExcel(filePath, fslist, reportFormsEntities);
			String zipFilePath = DownloadExcelUtils.writeEnd(filePath);
			zipFilePath = "jsp" + zipFilePath.split("jsp")[1];
			
			info(file_name + "报表下载成功");
			
			return zipFilePath;
		}
		return null;
	}
	
	/**
	 * 根据id获取对象
	 */
	public void getById(){
		User user=SecurityContext.getUser();
		String mno=OOUtils.getUserMerchant(user);
		String id=request.getParameter("id");
		ReportFormsEntity r=null;
		if(StringUtils.isNotEmpty(id)){
			r=new ReportFormsEntity();
			r.setMno(mno);
			r.setId(id);
			try {
				r=this.servicef.getReportFormsService().getById(r);
				response.getWriter().write((JSONObject.fromObject(r)).toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void info(String msg){
		OOLogUtil.info(msg, ReportFormsAction.class, null);
	}
}
