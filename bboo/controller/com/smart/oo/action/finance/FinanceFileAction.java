package com.smart.oo.action.finance;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.FinanceFileEntity;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.OOUtils;
import com.smart.comm.utils.ObjectToObiectUtils;
import com.smart.entity.User;
import com.smart.excel.report.read.Configuration;
import com.smart.excel.report.read.ExcelConfiguration;
import com.smart.excel.report.read.ReadExcel;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.report.FinanceCtrByUploadVO;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

public class FinanceFileAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;
	
	private static final String UPLOADER_FILE_PATH = "/jsp/build/finance/uploadFiles";
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	/**
	 * 上传并解析Excel
	 * @return
	 */
	public void uploadFiles() {
		JSONObject jsonObject = new JSONObject();
		try {
			String fileType = request.getParameter("fileType");
			
			if (StringUtils.isEmpty(fileType)) {
				jsonObject.put("error", "请选择文件类型");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			FinanceFileEntity fileEnt = new FinanceFileEntity();
			fileEnt.setMno(mno);
			fileEnt.setFileName(fileFileName);
			fileEnt.setFileType(fileType);
			
			// 查询数据库是否已存在与上传的文件的文件名相同的financeFile对象
			List<FinanceFileEntity> checkFiles = this.factoryServiceImpl.getFinanceFileService().queryFiles(fileEnt);
			if (null != checkFiles && checkFiles.size() > 0) {
				jsonObject.put(ERROR, "文件已存在");
				this.writeStream(jsonObject, "utf-8");
				return;
			}
			
			if (OOComms.REOPRTTYPE_SALE.equals(fileType)) {
				String result = impFinanceSales();
				if (!SUCCESS.equals(result)) {
					jsonObject.put(ERROR, result);
				} else {
					jsonObject.put(SUCCESS, OOComms.IMP_TICKET_NO_SUC_000001);
				}
			} else if (OOComms.REOPRTTYPE_BOOK.equals(fileType)) {
				jsonObject.put(ERROR, "表不存在");
			} else if (OOComms.REOPRTTYPE_FINANCE.equals(fileType)) {
				jsonObject.put(ERROR, "表不存在");
			} else if (OOComms.REOPRTTYPE_PROFIT.equals(fileType)) {
				jsonObject.put(ERROR, "表不存在");
			} else if (OOComms.REOPRTTYPE_PURCHASE.equals(fileType)) {
				jsonObject.put(ERROR, "表不存在");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}

	/**
	 * 导入FinanceSale表
	 * @return
	 */
	public String impFinanceSales() {

		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		if (file == null) {
			return OOComms.IMP_TICKET_NO_ERR_000001;
		}
		long filesize = file.length();
		if (filesize > 1024 * 1024 * 10) {
			//return OOComms.IMP_TICKET_NO_ERR_000002;
			return "上传的文件不可超过10M";
		}
		if (fileFileName == null || !fileFileName.trim().endsWith(".xls")) {
			return OOComms.IMP_TICKET_NO_ERR_000005;
		}
		String path = ServletActionContext.getServletContext().getRealPath(
				UPLOADER_FILE_PATH);
		String newFileName = null;
		String fileno = String.valueOf(System.currentTimeMillis());
		if (fileFileName != null && fileFileName.lastIndexOf(".") != -1) {
			newFileName = fileFileName.substring(0,
					fileFileName.lastIndexOf("."))
					+ fileno + ".xls";
		} else {
			newFileName = fileFileName;
		}
		File newFile = new File(path, newFileName);
		if (!newFile.getParentFile().exists()) {
			newFile.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(file, newFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return OOComms.IMP_TICKET_NO_ERR_000003 + OOUtils.exceptionToString(e1);
		}
		Configuration config = null;
		List<Object> list = null;
		try {
			config = Configuration
					.getConfiguration(FinanceCtrByUploadVO.class);
			if (config != null) {
				ExcelConfiguration ecf = config.getExcelConfig();
				list = new ReadExcel(ecf, newFile.getAbsolutePath())
						.getExcelData(1, 0);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return OOComms.IMP_TICKET_NO_ERR_000006 + OOUtils.exceptionToString(e1);
		}
		if (null == list || list.size() < 1) {
			return OOComms.IMP_TICKET_NO_ERR_000004;
		}
		int totale = list.size();
		int save = 0;
		int error = 0;
		String errMsg = "";
		Iterator<Object> iter = list.iterator();
		List<FinanceSaleEntity> fsList = new ArrayList<FinanceSaleEntity>();
		while (iter.hasNext()) {
			try {
				FinanceCtrByUploadVO ctr = (FinanceCtrByUploadVO) iter.next();
				FinanceSaleEntity saleEntity = new FinanceSaleEntity();
				String result = ObjectToObiectUtils.toFinanceSaleEntity(ctr, user, fileno, saleEntity);
				if (result != null) {
					if (!"success".equals(result)) {
						return "第"+(save+2)+"行, "+result;
					}
				}
				if ("success".equals(result) && saleEntity != null) {
					fsList.add(saleEntity);
					save++;
				}
				iter.remove();
			} catch (Exception e) {
				e.printStackTrace();
				error++;
				errMsg = OOUtils.exceptionToString(e);
				if (errMsg != null && errMsg.length() > 50) {
					errMsg = errMsg.substring(0, 50);
				}
			}
		}
		if (error > 0) {
			return errMsg;
		}
		FinanceFileEntity fileEn = new FinanceFileEntity();
		fileEn.setId(UniqId.getInstance().getStrId());
		fileEn.setMno(mno);
		fileEn.setFileName(fileFileName);
		fileEn.setFilePath(UPLOADER_FILE_PATH + newFileName);
		fileEn.setFileSize(filesize);
		fileEn.setCount(totale);
		fileEn.setDataSpecification("合计：" + totale + ",OK:" + save + ",ERR:"
				+ error + ";" + (error > 0 ? errMsg : ""));
		fileEn.setOperator(user.getName());
		fileEn.setFileno(fileno);
		fileEn.setFileType(OOComms.REOPRTTYPE_SALE);
		fileEn.setCreateTime(DateUtils.getDate());
		try {
			this.factoryServiceImpl.getFinanceSaleService().saveFinancesSaleAndFinanceFile(fsList, fileEn);
		} catch (Exception e) {
			e.printStackTrace();
			return OOComms.IMP_TICKET_NO_ERR_000007 + OOUtils.exceptionToString(e);
		}
		info(fileFileName+"文件上传成功");
		return SUCCESS;
	}
	

	/**
	 * 分页查询
	 * @return
	 */
	public void queryByPage() {
		JSONObject jsonObject = new JSONObject();
		try {
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			String startDate = this.getParameter("startDate");
			String endDate = this.getParameter("endDate");
			String fileName = this.getParameter("searchFileName");
			String fileNo = this.getParameter("searchFileNo");
			String operator = this.getParameter("searchOperator");
			
			Page page = null;
			if(StringUtils.isNotEmpty(startpage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			FinanceFileEntity financeFile  = new FinanceFileEntity();
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			financeFile.setMno(mno);
			if (StringUtils.isNotEmpty(startDate)) {
				financeFile.setCreateTime(startDate);
			}
			if (StringUtils.isNotEmpty(fileName)) {
				if (!fileName.endsWith(".xls")) {
					fileName += ".xls";
				}
				financeFile.setFileName(fileName);
			}
			if (StringUtils.isNotEmpty(operator)) {
				financeFile.setOperator(operator);
			}
			if (StringUtils.isNotEmpty(fileNo)) {
				financeFile.setFileno(fileNo);
			}
			List<FinanceFileEntity> list = this.factoryServiceImpl.getFinanceFileService().findByPage(financeFile, page, endDate);
			
			if (list.size() > 0) {
				String jsonString = JSON.toJSONString(list);
				jsonObject.put("list", jsonString);
			} else {
				jsonObject.put("list", "[]");
			}
			jsonObject.put("success", "success");
			jsonObject.put("total", page.getTotalCount());
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	

	/**
	 * 删除数据
	 * @return
	 */
	public void deleteFile(){
		JSONObject jsonObject = new JSONObject();
		try {
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			String id = request.getParameter("id");
			String fileNo = request.getParameter("fileno");
			FinanceFileEntity ffe = new FinanceFileEntity();
			
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(fileNo)) {
				ffe.setId(id);
				ffe.setMno(mno);
				ffe.setFileno(fileNo);
				this.factoryServiceImpl.getFinanceFileService().deleteFile(ffe);
				info(fileNo+" 数据删除成功");
				jsonObject.put("success", "文件删除成功");
			} else {
				jsonObject.put("error", "文件不存在");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, FinanceFileAction.class, null);
	}
	
}
