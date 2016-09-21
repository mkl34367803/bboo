package com.smart.action.user;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.service.role.RoleService;
import com.smart.service.user.UserService;
import com.smart.utils.SecurityUtils;

/**
 * @Project CSO
 * @Author ERICH
 * @Version 1.0
 * @Date 2010-3-9 下午03:37:56
 */
@Results({
		@Result(name = "success", location = "/jsp/user/user_list.jsp"),
		@Result(name = "user_add", location = "/jsp/user/user_add.jsp"),
		@Result(name = "query", location = "/user/user!queryUser.do", type = "redirect"),
		@Result(name = "user_edit", location = "/jsp/user/user_edit.jsp"),
		@Result(name = "updatePw", location = "/jsp/user/updatePw.jsp"),
		@Result(name = "user_role", location = "/jsp/user/user_role.jsp") })
public class UserAction extends BaseAction {
	private static final long serialVersionUID = -744177463594472960L;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * 查询用户
	 * 
	 * @return
	 */
	public String queryUser() throws SqlException {

		request.setAttribute("userList", userService.getAllUser(page));
		return "success";
	}

	public String addUser() {
		return "user_add";
	}

	/**
	 * 保存用户
	 * 
	 * @return
	 */
	public String saveUser() throws SqlException {
		String name = request.getParameter("name"); // 姓名
		String ywm = request.getParameter("ywm"); // 英文名
		String ssbm = request.getParameter("ssbm"); // 所属部门
		String sszw = request.getParameter("sszw"); // 所属职务
		String loginName = request.getParameter("loginName"); // 登录名
		String sj = request.getParameter("sj"); // 手机
		String password = request.getParameter("password"); // 密码
		String deleted = request.getParameter("deleted"); // 是否启用
		User user = new User();
		user.setName(name);
		user.setYwm(ywm);
		user.setSsbm(ssbm);
		user.setSszw(sszw);
		user.setLoginName(loginName);
		user.setSj(sj);
		user.setPassword(SecurityUtils.getMD5(password)); // 密码加密
		user.setDeleted(deleted);
		userService.saveUser(user);
		request.setAttribute("userList", userService.getAllUser(page));

		return "query";
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String deleteUser() throws SqlException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String deleted = request.getParameter("userStatus");
		userService.deletedUser(userId, deleted);
		request.setAttribute("userList", userService.getAllUser(page));
		return "success";
	}

	/**
	 * 编辑用户
	 * 
	 * @return
	 */
	public String editUser() throws SqlException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		request.setAttribute("user", userService.getUserByUserId(userId));
		return "user_edit";
	}

	/**
	 * 更新用户
	 * 
	 * @return
	 */
	public String updateUser() throws SqlException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = null;
		user = userService.getUserByUserId(userId);
		user.setName(request.getParameter("name")); // 姓名
		user.setYwm(request.getParameter("ywm")); // 英文名
		user.setSsbm(request.getParameter("ssbm")); // 所属部门
		user.setSszw(request.getParameter("sszw")); // 所属职务
		user.setLoginName(request.getParameter("loginName")); // 登录名
		user.setSj(request.getParameter("sj")); // 手机
		user.setDeleted(request.getParameter("deleted")); // 是否启用
		String bt1 = request.getParameter("isUpdatePw");
		if (bt1.equalsIgnoreCase("1")) { // 判断是否修改密码
			String password = request.getParameter("password"); // 获得密码
			user.setPassword(SecurityUtils.getMD5(password)); // 密码加密
		}
		userService.updateUser(user);
		request.setAttribute("userList", userService.getAllUser(page));

		return "query";
	}

	/**
	 * 用户分配角色
	 * 
	 * @return
	 */
	public String userRole() throws SqlException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		User user = null;
		user = userService.getUserByUserId(userId);
		request.setAttribute("user", user);
		request.setAttribute("hasRoleList", user.getRoles());
		request.setAttribute(
				"roleList",
				roleService.remove(roleService.getAllRole(),
						user.getRoles()));

		return "user_role";
	}

	/**
	 * 用户分配角色
	 * 
	 * @return
	 */
	public String distributeRole() throws SqlException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		userService.deleteUserRoleByUserId(userId);
		String roleIds = request.getParameter("roleIds");
		if (!roleIds.equals("")) {
			String[] roleIdStr = roleIds.split(",");
			for (int i = 0; i < roleIdStr.length; i++) {
				int roleId = Integer.valueOf(roleIdStr[i]);
				userService.insertUserRole(userId, roleId);
			}
		}
		SecurityContext.loadSecurityAccessResource(request.getSession()
				.getServletContext());
		return queryUser();
	}

	/**
	 * 登陆用户唯一性验证
	 * 
	 * @return
	 */
	public String checkUserUnique() throws SqlException {
		String resout = "";
		try {
			request.setCharacterEncoding("UTF-8");
			String value = new String(request.getParameter("value").getBytes(
					"ISO-8859-1"), "UTF-8");
			String type = request.getParameter("type");
			int userId = 0;
			if (type.equalsIgnoreCase("edit")) {
				userId = Integer.parseInt(request.getParameter("userId"));
			}
			boolean isUnique = false;
			isUnique = userService.checkUniqueLoginName(value, type, userId);
			response.setCharacterEncoding("UTF-8"); // 先指定输出流的编码
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter(); // 再拿到输出对象

			if (isUnique) {
				resout = "pass";
			} else {
				resout = "refuse";
			}
			out.print(resout);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updatePw() throws SqlException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		request.setAttribute("user", userService.getUserByUserId(userId));
		return "updatePw";
	}

	public String updatePassword() throws SqlException {
		String oldPassword = request.getParameter("oldPw"); // 旧密码
		String newPassword = request.getParameter("newPw"); // 新密码
		int userId = Integer.parseInt(request.getParameter("userId")); // 管理员Id

		boolean oldPw = false;
		oldPw = userService.checkPw(oldPassword, userId);
		PrintWriter pw = null;
		HttpServletResponse response = ServletActionContext.getResponse(); // 取response对象
		try {
			pw = response.getWriter();
			if (oldPw) {
				User user;
				user = userService.getUserByUserId(userId);
				user.setPassword(SecurityUtils.getMD5(newPassword.trim()));
				userService.updateUser(user);
				pw.write("1");
			} else {
				pw.write("2");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
