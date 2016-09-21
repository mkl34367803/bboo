package com.smart.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.InterfaceIp;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.service.systemlog.InterfaceIpService;
import com.smart.service.systemlog.SystemLogService;
import com.smart.utils.Backup;
import com.smart.utils.DateUtils;
import com.smart.utils.FileViewer;

/**
 * 
 * @Description
 * @author ERICH
 * @ClassName: SystemAction
 * @since 2012-12-16 上午12:31:25
 * @Version 1.0
 */
@Results({
		@Result(name = "success", location = "/system/system!queryfile.do", type = "redirect"),
		@Result(name = "queryfile", location = "/jsp/system/databackup.jsp"),
		@Result(name = "queryInterface", location = "/jsp/system/interface.jsp"),
		@Result(name = "saveip", location = "/system/system!queryInterface.do", type = "redirect"),
		@Result(name = "updatezt", location = "/system/system!queryInterface.do", type = "redirect") })
public class SystemAction extends BaseAction {
	@Autowired
	private InterfaceIpService interfaceIpService;
	@Autowired
	private SystemLogService systemLogService;
	private static final long serialVersionUID = 5336402496588444084L;

	public String backup() throws SqlException {
		request.getSession().getServletContext().setAttribute("state", "0");
		request.getSession()
				.getServletContext()
				.setAttribute(
						"datamessage",
						"用户["
								+ SecurityContext.getUser().getName()
								+ "]在 ["
								+ DateUtils.formatDate(new Date(),
										"yyyy/MM/dd hh:mm:ss")
								+ "]执行了数据库备份,操作还在进行中,未完成!");

		systemLogService.saveSystemLog(SecurityContext.getUser(),
				request.getRemoteAddr(), "执行数据备份操作!");
		Backup backup = new Backup();
		backup.exp();
		request.getSession().getServletContext().setAttribute("state", "1");
		request.getSession().getServletContext()
				.setAttribute("datamessage", "");
		return "success";
	}

	public String queryfile() {
		FileViewer fv = new FileViewer();
		List<?> arrayList = fv.listFile("d:/memberdata", "rar", true);
		String message = "";
		if (!arrayList.isEmpty()) {
			for (Iterator<?> i = arrayList.iterator(); i.hasNext();) {
				String temp = (String) i.next();
				message += temp;
			}
		}
		if (!"".equals(message)) {
			message = "memberdata.rar";
		}
		request.setAttribute("filemessage", message);
		return "queryfile";
	}

	public String queryInterface() throws SqlException {

		request.setAttribute("allip", interfaceIpService.queryAllIp());

		return "queryInterface";
	}

	/** 验证用户名 唯一性 规则性 AJAX */
	public String downfile() throws SqlException {
		PrintWriter pw = null;
		HttpServletResponse response = ServletActionContext.getResponse(); // 取response对象
		try {
			systemLogService.saveSystemLog(SecurityContext.getUser(),
					request.getRemoteAddr(), "执行了数据库下载!");
			pw = response.getWriter();
			pw.write("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String saveIp() throws SqlException {
		InterfaceIp ip = new InterfaceIp();
		String addip = request.getParameter("ip");
		ip.setCjr(SecurityContext.getUser().getName());
		ip.setCjsj(new Date());
		ip.setIp(addip);
		ip.setZt(request.getParameter("zt"));
		interfaceIpService.saveIp(ip);
		systemLogService.saveSystemLog(SecurityContext.getUser(),
				request.getRemoteAddr(), "新增接口可访问IP[ " + addip + " ]");

		return "saveip";
	}

	public String updateipzt() throws SqlException {
		String zt = request.getParameter("zt");
		String ipid = request.getParameter("ipid");
		String ip = request.getParameter("ip");
		if (StringUtils.isNotEmpty(ipid) && StringUtils.isNotEmpty(zt)) {
			try {
				interfaceIpService.updateIpZt(Long.valueOf(ipid), zt,
						SecurityContext.getUser().getName());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ("0".equals(zt)) {
			zt = "启用";
		}
		if ("1".equals(zt)) {
			zt = "禁用";
		}
		if ("2".equals(zt)) {
			zt = "删除";
		}
		String log = "更改接口访问IP,IP为:[ " + ip + " ],状态修改为[ " + zt + " ]";
		systemLogService.saveSystemLog(SecurityContext.getUser(),
				request.getRemoteAddr(), log);
		return "updatezt";
	}

}
