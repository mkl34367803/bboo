package com.smart.action.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.Resource;
import com.smart.entity.User;
import com.smart.entity.UserVisitStatisticsEntity;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Constants;
import com.smart.framework.security.SecurityContext;
import com.smart.service.permission.ResourceService;
import com.smart.service.user.UserService;
import com.smart.service.uvisit.UserVisitStatisticsServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.UniqId;

/**
 * 用户登录ACTION
 * 
 * @Description
 * @author ERICH
 * @ClassName: LoginAction
 * @since 2012-12-16 上午12:31:35
 * @Version 1.0
 */
@Namespace("/")
@Results({
		@Result(name = "success", location = "/jsp/index.jsp", type = "redirect"),
		@Result(name = "error", location = "/login.jsp"),
		@Result(name = "logout", location = "/login.jsp", type = "redirect") })
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserVisitStatisticsServiceImpl uvisit;

	public String execute() throws SqlException {

		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		request.setAttribute("loginName", loginName);
		request.setAttribute("password", password);
		// 校验验证码是否合法
		String image = request.getParameter("image");
		String sessionImage = (String) request.getSession().getAttribute(
				Constants.VERIFICATION_CODE);
		if (image != null && sessionImage != null
				&& !sessionImage.toUpperCase().equals(image.toUpperCase())) {
			request.setAttribute("message", "验证码不正确！");
			return "error";
		}
		String message;
		message = userService.login(loginName, password);
		request.setAttribute("message", message);
		if (message != null) {
			System.out.println("message: " + message);
			request.setAttribute("message", message);
			return "error";
		}
		if (request.getSession().getAttribute(
				Constants.SESSION_KEY_CURRENT_USER_MENU) == null) {
			List<Resource> menuList = resourceService
					.getMenuList(SecurityContext.getUser().getUserId());
			request.getSession().setAttribute(
					Constants.SESSION_KEY_CURRENT_USER_MENU, menuList);
		}
		try {
			recodeIP(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	/**
	 * 记录用户访问信息
	 * 
	 * @param arg0
	 * @throws Exception
	 */
	private void recodeIP(HttpServletRequest request) throws Exception {
		User user = SecurityContext.getUser();
		String userName = "";
		if (user != null) {
			userName = user.getLoginName();
		}
		String nowTime = DateUtils.getDateys();
		UserVisitStatisticsEntity userLoginCount = new UserVisitStatisticsEntity();
		userLoginCount.setIp(request.getRemoteAddr());
		userLoginCount.setUserName(userName != null ? userName : "");
		userLoginCount.setLastTime(nowTime);
		// 查看同一ip,username是否已有记录，有则修改次数，无则添加
		int upstate = uvisit.updateUserLoginStatistics(userLoginCount);
		if (upstate == 0) {
			userLoginCount.setId(UniqId.getInstance().getStrId());
			userLoginCount.setCreateTime(nowTime);
			userLoginCount.setTimes(1);
			uvisit.saveUserLoginStatistics(userLoginCount);
		}

	}

	/**
	 * 退出系统
	 * 
	 * @return
	 */
	public String logout() {
		super.isPage = false;
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
