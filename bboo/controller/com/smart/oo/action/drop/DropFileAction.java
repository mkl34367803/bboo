package com.smart.oo.action.drop;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.entity.DropDataEntity;
import com.smart.comm.entity.DropFileEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOUtils;
import com.smart.comm.utils.ObjectToObiectUtils;
import com.smart.entity.User;
import com.smart.excel.report.read.Configuration;
import com.smart.excel.report.read.ExcelConfiguration;
import com.smart.excel.report.read.ReadExcel;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.report.OrderFlightForDropVO;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

/**
 * 导入降舱信息
 * 
 * @author eric
 * 
 */
@Results({ @Result(name = "imp_dropdatas", location = "/jsp/build/drop/ImpDropData.jsp") })
public class DropFileAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541721909521879555L;

	private File file;

	private String fileFileName;

	private String fileContentType;

	private static final String UPLOADER_FILE_PATH = "/jsp/files/drops/";

	@Autowired
	private FactoryServiceImpl servicef;

	public String toFile() {

		return "imp_dropdatas";
	}

	public void trigger() {

		this.isPage = false;
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		String id = this.getParameter("id");
		String scanStatus = this.getParameter("scanStatus");
		if ("1".equals(scanStatus)) {
			this.writeStream("文件正在执行查询...", "utf-8");
		} else {
			DropFileEntity ticke = new DropFileEntity();
			ticke.setId(id);
			ticke.setMno(mno);
			ticke.setScanStatus(1);
			int isok = -1;
			try {
				isok = servicef.getDropFileService().updateData(ticke);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.writeStream(OOUtils.exceptionToString(e), "utf-8");
				return;
			}
			if (isok > 0) {
				this.writeStream("触发成功", "utf-8");
			} else {
				this.writeStream("操作数据库异常，请重试或联系管理员", "utf-8");
			}
		}
		return;
	}

	public void detail() {
		this.isPage = false;
		String id = this.getParameter("id");
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		DropFileEntity ticke = new DropFileEntity();
		ticke.setId(id);
		ticke.setMno(mno);
		List<DropFileEntity> list = null;
		try {
			list = servicef.getDropFileService().findDatas(ticke);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			DropFileEntity en = list.get(0);
			if (en != null) {
				String contents = com.smart.comm.utils.FileUtils
						.getJspContents("jsp/build/ticket", "detail.html",
								"utf-8");
				try {
					contents = contents
							.replace("fileno", en.getFileno())
							.replace(
									"dataSpecification",
									en.getDataSpecification() != null ? en
											.getDataSpecification() : "")
							.replace(
									"scanSpecification",
									en.getScanSpecification() != null ? en
											.getScanSpecification() : "")
							.replace("fileName", en.getFileName())
							.replace(
									"scanStatus",
									en.getScanStatus() == 0 ? "待查询" : en
											.getScanStatus() == 1 ? "查询中" : en
											.getScanStatus() == 2 ? "查询完成" : "")
							.replace(
									"lastTime",
									en.getLastTime() != null ? en.getLastTime()
											: "")
							.replace("count", String.valueOf(en.getCount()))
							.replace("operator", en.getOperator())
							.replace("createTime", en.getCreateTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.writeStream(contents, "utf-8");
			} else {
				this.writeStream("异常，请重新查询", "utf-8");
			}
		} else {
			this.writeStream("链接数据库异常，请重新操作！", "utf-8");
		}
	}

	public String impdata() {

		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		if (file == null) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000001);
			return "imp_dropdatas";
		}
		long filesize = file.length();
		if (filesize > 1024 * 1024 * 5) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000002);
			return "imp_dropdatas";
		}
		if (fileFileName == null || !fileFileName.trim().endsWith(".xls")) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000005);
			return "imp_dropdatas";
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
			request.setAttribute(
					OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000003
							+ OOUtils.exceptionToString(e1));
			return "imp_dropdatas";
		}
		Configuration config = null;
		List<Object> list = null;
		try {
			config = Configuration.getConfiguration(OrderFlightForDropVO.class);
			if (config != null) {
				ExcelConfiguration ecf = config.getExcelConfig();
				list = new ReadExcel(ecf, newFile.getAbsolutePath())
						.getExcelData(1, 0);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			request.setAttribute(
					OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000006
							+ OOUtils.exceptionToString(e1));
			return "imp_dropdatas";
		}
		if (null == list || list.size() < 1) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000004);
			return "imp_dropdatas";
		}
		// 判断数据合法性
		int totale = list.size();
		int save = 0;
		int errror = 0;
		String errMsg = "";
		Iterator<Object> iter = list.iterator();
		List<DropDataEntity> tickets = new ArrayList<DropDataEntity>();
		while (iter.hasNext()) {
			try {
				OrderFlightForDropVO ticketno = (OrderFlightForDropVO) iter
						.next();
				List<DropDataEntity> ens = ObjectToObiectUtils
						.toDropDataEntity(ticketno, user, fileno);
				if (ens != null && ens.size() > 0) {
					tickets.addAll(ens);
					save++;
				} else {
					errror++;
					errMsg = "orderNo:" + ticketno.getOrderNo() + ";pnr:"
							+ ticketno.getPnr();
				}
				iter.remove();
			} catch (Exception e) {
				errror++;
				errMsg = OOUtils.exceptionToString(e);
				if (errMsg != null && errMsg.length() > 50) {
					errMsg = errMsg.substring(0, 50);
				}
			}
		}
		DropFileEntity fileEn = new DropFileEntity();
		fileEn.setId(UniqId.getInstance().getStrId());
		fileEn.setMno(mno);
		fileEn.setFileName(fileFileName);
		fileEn.setFilePath(UPLOADER_FILE_PATH + newFileName);
		fileEn.setFileSize(filesize);
		fileEn.setCount(totale);
		fileEn.setDataSpecification("合计：" + totale + ",OK:" + save + ",ERR:"
				+ errror + ";" + (errror > 0 ? errMsg : ""));
		fileEn.setScanSpecification("");
		fileEn.setOperator(user.getName());
		fileEn.setFileno(fileno);
		fileEn.setScanStatus(0);
		fileEn.setCreateTime(DateUtils.getDateys());
		try {
			servicef.getDropDataService().saveData(tickets, fileEn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute(
					OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000007
							+ OOUtils.exceptionToString(e));
			return "imp_dropdatas";
		}
		request.setAttribute(OOComms.SUC_001, OOComms.IMP_TICKET_NO_SUC_000001);
		return "imp_dropdatas";
	}

	public String querylist() {
		this.page.setPageSize(14);
		String impdate = this.getParameter("impdate");
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		DropFileEntity ticke = new DropFileEntity();
		if (StringUtils.isNotEmpty(impdate)) {
			ticke.setCreateTime(impdate);
			request.setAttribute("impdate", impdate);
		}
		ticke.setMno(mno);
		List<DropFileEntity> list = null;
		try {
			list = servicef.getDropFileService().findDatas(ticke, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute(OOComms.ERR_001, OOComms.DB_OP_ERR_000001
					+ OOUtils.exceptionToString(e));
		}
		request.setAttribute("list", list);
		return "imp_dropdatas";
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

}
