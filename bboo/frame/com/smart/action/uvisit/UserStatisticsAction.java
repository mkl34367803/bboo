package com.smart.action.uvisit;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.entity.UserVisitStatisticsEntity;
import com.smart.framework.base.BaseAction;
import com.smart.service.uvisit.UserVisitStatisticsServiceImpl;
import com.smart.vo.OperationCountVo;

@Results({ @Result(name = "queryUserLoginStatistics", location = "/jsp/uvisit/QueryOperationRecord.jsp") // 查询用户访问记录
})
public class UserStatisticsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7312139033512362220L;
	@Autowired
	private UserVisitStatisticsServiceImpl uvisitservice;

	/**
	 * 查询用户访问记录
	 * 
	 * @throws Exception
	 */
	public String queryUserLoginStatistics() throws Exception {

		String ip = request.getParameter("ip"); // ip
		String userName = request.getParameter("userName");// 用户名
		String startTime = request.getParameter("start_Time"); // 创建时间 开始
		String endTime = request.getParameter("end_Time"); // 创建时间 结束
		OperationCountVo vo = new OperationCountVo();
		vo.setIp(ip);
		vo.setUserName(userName);
		vo.setStartTime(startTime);
		vo.setEndTime(endTime);
		List<UserVisitStatisticsEntity> userLoginStatis = uvisitservice
				.queryOperationRecord(vo, page);
		request.setAttribute("userLoginStatis", userLoginStatis);
		request.setAttribute("ip", ip);
		request.setAttribute("userName", userName);
		request.setAttribute("start_Time", startTime);
		request.setAttribute("end_Time", endTime);

		return "queryUserLoginStatistics";
	}

}
