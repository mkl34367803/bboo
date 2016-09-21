package com.smart.action.system;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.framework.base.BaseAction;
import com.smart.service.systemlog.SystemLogService;

/**
 * 系统日志管理
 * @Description
 * @author ERICH
 * @ClassName: LogAction
 * @since 2012-12-16 上午12:31:18
 * @Version 1.0
 */
@Results({
		@Result(name = "success", location = "/jsp/system/system_log.jsp"),
		@Result(name = "export", location = "/user/log!initLog.do", type = "redirect"), })
public class LogAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private SystemLogService systemLogService;

	/**
	 * 系统日志的初始化
	 * 
	 * @return
	 */
	public String initLog() throws SqlException {
		page.setPageSize(10);

		request.setAttribute("systemLogs", systemLogService.initMemberLog(page));

		return "success";
	}

	/**
	 * 按条件查询系统日志信息
	 * 
	 * @return
	 */
	public String findLog() throws SqlException {
		page.setPageSize(10);
		String xm = request.getParameter("xm").trim();
		String sj = request.getParameter("sj").trim();
		String beginTime = request.getParameter("begintime").trim();
		String endTime = request.getParameter("endtime").trim();

		request.setAttribute("xm", xm);
		request.setAttribute("sj", sj);
		request.setAttribute("begintime", beginTime);
		request.setAttribute("endtime", endTime);

		request.setAttribute("systemLogs", systemLogService.findSystemLog(page,
				xm, sj, beginTime, endTime));

		return "success";
	}

}
