package com.smart.oo.action.ticket;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.entity.TicketFileEntity;
import com.smart.comm.entity.TicketStateEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOUtils;
import com.smart.comm.utils.ObjectToObiectUtils;
import com.smart.entity.User;
import com.smart.excel.report.read.Configuration;
import com.smart.excel.report.read.ExcelConfiguration;
import com.smart.excel.report.read.ReadExcel;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.report.QueryTicketNoByUploadVO;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

/**
 * 查询客票状态
 * 
 * @author eric
 * 
 */
@Results({ @Result(name = "imp_tickets", location = "/jsp/build/ticket/imp_tickets.jsp") })
public class TicketCtrlAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1189714785961056834L;

	private File file;

	private String fileFileName;

	private String fileContentType;

	@Autowired
	private FactoryServiceImpl servicef;

	private static final String UPLOADER_FILE_PATH = "/jsp/files/tickets/";

	public void down() {

		this.isPage = false;
		String fileno = this.getParameter("fn");
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		if (StringUtils.isEmpty(fileno)) {
			this.writeStream("指定下载文件号不能为空！", "utf-8");
		} else {

			TicketFileEntity ticke = new TicketFileEntity();
			ticke.setFileno(fileno);
			ticke.setMno(mno);
			List<TicketFileEntity> list = null;
			try {
				list = servicef.getIticketFileService().findDatas(ticke);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (list.size() > 0) {
				for (TicketFileEntity t : list) {
					if (1 == t.getScanStatus()) {
						this.writeStream("查询还没结束，请稍后下载", "utf-8");
						return;
					}
					if (0 == t.getScanStatus()) {
						this.writeStream("您需要先触发票号查询", "utf-8");
						return;
					}
				}
			}
			TicketStateEntity ep = new TicketStateEntity();
			ep.setFileno(fileno);
			ep.setMno(mno);
			List<TicketStateEntity> enlist = null;
			try {
				enlist = servicef.getIticketStateService().findDatas(ep);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (enlist == null || enlist.size() == 0) {
				this.writeStream("该文件无可以下载的数据", "utf-8");
				return;
			}
			try {
				String filePath = com.smart.comm.utils.FileUtils.getFilePath(
						"jsp/files/tickets",
						fileno + "_" + System.currentTimeMillis());
				com.smart.comm.utils.FileUtils.writeHeader(filePath, "票号",
						"状态", "乘客1", "乘客2", "证件号码", "起飞日期", "出票日期", "查询时间",
						"导入时间", "信息", "备注", "航班信息", "计划起飞", "实际起飞", "计划到达",
						"实际到达", "延误状态1", "延误状态2", "延误说明");
				StringBuffer bodyBuf = new StringBuffer();
				if (enlist != null && enlist.size() > 0) {
					Iterator<TicketStateEntity> iterator = enlist.iterator();
					while (iterator.hasNext()) {
						TicketStateEntity vo = iterator.next();
						String delaySte = "";
						String ywt = "";
						if (vo.getDelayState() != null) {
							// 1计划 2延误 3取消 4起飞 5到达 6其它
							if (vo.getDelayState() == 1) {
								delaySte = "计划";
							} else if (vo.getDelayState() == 2) {
								delaySte = "延误";
							} else if (vo.getDelayState() == 3) {
								delaySte = "取消";
							} else if (vo.getDelayState() == 4) {
								delaySte = "起飞";
							} else if (vo.getDelayState() == 5) {
								delaySte = "到达";
							} else if (vo.getDelayState() == 6) {
								delaySte = "其它";
							} else {
								delaySte = String.valueOf(vo.getDelayState());
							}
						}
						if (vo.getYwtype() != null && 1 == vo.getYwtype()) {
							ywt = "可能延误";
						}
						String tno = vo.getNo().trim();
						tno = tno.replace("-", "");
						tno = tno.substring(0, 3) + "-"
								+ tno.substring(3, tno.length());
						com.smart.comm.utils.FileUtils.writeBody(
								bodyBuf,
								tno,
								vo.getState() != null ? vo.getState() : "",
								vo.getPassengerName() != null ? vo
										.getPassengerName() : "",
								vo.getPassengerName2() != null ? vo
										.getPassengerName2() : "",
								vo.getCertno() != null ? vo.getCertno() : "",
								vo.getTakeOff() != null ? vo.getTakeOff() : "",
								vo.getOutDate() != null ? vo.getOutDate() : "",
								vo.getLastime() != null ? vo.getLastime() : "",
								vo.getCtm(),
								vo.getMessages() != null ? vo.getMessages()
										: "",
								vo.getRemark() != null ? vo.getRemark() : "",
								vo.getFltinfo() != null ? vo.getFltinfo() : "",
								vo.getPlanDepTime() != null ? vo
										.getPlanDepTime() : "",
								vo.getActDepTime() != null ? vo.getActDepTime()
										: "",
								vo.getPlanArrTime() != null ? vo
										.getPlanArrTime() : "",
								vo.getActArrTime() != null ? vo.getActArrTime()
										: "", delaySte,
								vo.getZtms() != null ? vo.getZtms() : "", ywt);
						iterator.remove();
					}
				}
				com.smart.comm.utils.FileUtils.xiefile(bodyBuf, filePath);
				String zipFilePath = com.smart.comm.utils.FileUtils
						.writeEnd(filePath);
				zipFilePath = "jsp" + zipFilePath.split("jsp")[1];
				this.writeStream("<a href='../" + zipFilePath
						+ "' name='tickets_loads_id001x'>" + "点击这下载./"
						+ zipFilePath + "</a>", "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void detail() {

		this.isPage = false;
		String id = this.getParameter("id");
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		TicketFileEntity ticke = new TicketFileEntity();
		ticke.setId(id);
		ticke.setMno(mno);
		List<TicketFileEntity> list = null;
		try {
			list = servicef.getIticketFileService().findDatas(ticke);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null && list.size() > 0) {
			TicketFileEntity en = list.get(0);
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

	public void trigger() {

		this.isPage = false;
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		String id = this.getParameter("id");
		String scanStatus = this.getParameter("scanStatus");
		if ("1".equals(scanStatus)) {
			this.writeStream("文件正在执行查询...", "utf-8");
		} else {
			TicketFileEntity ticke = new TicketFileEntity();
			ticke.setId(id);
			ticke.setMno(mno);
			ticke.setScanStatus(1);
			int isok = -1;
			try {
				isok = servicef.getIticketFileService().updateData(ticke);
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

	public String query() {

		return "imp_tickets";
	}

	public String querylist() {

		this.page.setPageSize(14);
		String impdate = this.getParameter("impdate");
		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		TicketFileEntity ticke = new TicketFileEntity();
		if (StringUtils.isNotEmpty(impdate)) {
			ticke.setCreateTime(impdate);
			request.setAttribute("impdate", impdate);
		}
		ticke.setMno(mno);
		List<TicketFileEntity> list = null;
		try {
			list = servicef.getIticketFileService().findDatas(ticke, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute(OOComms.ERR_001, OOComms.DB_OP_ERR_000001
					+ OOUtils.exceptionToString(e));
		}
		request.setAttribute("list", list);
		return "imp_tickets";
	}

	public String imptickets() {

		User user = SecurityContext.getUser();
		String mno = OOUtils.getUserMerchant(user);
		if (file == null) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000001);
			return "imp_tickets";
		}
		long filesize = file.length();
		if (filesize > 1024 * 1024 * 5) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000002);
			return "imp_tickets";
		}
		if (fileFileName == null || !fileFileName.trim().endsWith(".xls")) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000005);
			return "imp_tickets";
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
			return "imp_tickets";
		}
		Configuration config = null;
		List<Object> list = null;
		try {
			config = Configuration
					.getConfiguration(QueryTicketNoByUploadVO.class);
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
			return "imp_tickets";
		}
		if (null == list || list.size() < 1) {
			request.setAttribute(OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000004);
			return "imp_tickets";
		}
		int totale = list.size();
		int save = 0;
		int errror = 0;
		String errMsg = "";
		Iterator<Object> iter = list.iterator();
		List<TicketStateEntity> tickets = new ArrayList<TicketStateEntity>();
		while (iter.hasNext()) {
			try {
				QueryTicketNoByUploadVO ticketno = (QueryTicketNoByUploadVO) iter
						.next();
				List<TicketStateEntity> ens = ObjectToObiectUtils
						.toTicketStateEntity(ticketno, user, fileno);
				if (ens != null && ens.size() > 0) {
					tickets.addAll(ens);
					save++;
				} else {
					errror++;
					errMsg = "tno:" + ticketno.getTicketNo() + ";psg:"
							+ ticketno.getPassengerName();
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
		TicketFileEntity fileEn = new TicketFileEntity();
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
			servicef.getIticketStateService().saveData(tickets, fileEn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			request.setAttribute(
					OOComms.ERR_001,
					OOComms.IMP_TICKET_NO_ERR_000007
							+ OOUtils.exceptionToString(e));
			return "imp_tickets";
		}
		request.setAttribute(OOComms.SUC_001, OOComms.IMP_TICKET_NO_SUC_000001);
		return "imp_tickets";
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
