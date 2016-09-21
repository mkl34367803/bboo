package com.smart.action.role;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.Role;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.service.permission.ResourceService;
import com.smart.service.role.RoleService;
import com.smart.service.systemlog.SystemLogService;

/**
 * 
 * @Description
 * @author ERICH
 * @ClassName: RoleAction
 * @since 2012-12-16 上午12:30:50
 * @Version 1.0
 */
@Results({
		@Result(name = "success", location = "/jsp/role/role_list.jsp"),
		@Result(name = "role_add", location = "/jsp/role/role_add.jsp"),
		@Result(name = "query", location = "/role/role!queryRole.do", type = "redirect"),
		@Result(name = "role_edit", location = "/jsp/role/role_edit.jsp"),
		@Result(name = "role_resource", location = "/jsp/role/role_resource.jsp") })
public class RoleAction extends BaseAction {

	private static final long serialVersionUID = 5336402496588444084L;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private SystemLogService systemLogService;

	/**
	 * 查询角色
	 * 
	 * @return
	 */
	public String queryRole() throws SqlException {

		request.setAttribute("roleList", roleService.getAllRole(page));
		return "success";
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	public String addRole() {
		return "role_add";
	}

	/**
	 * 保存角色
	 * 
	 * @return
	 */
	public String saveRole() throws SqlException {
		String name = request.getParameter("name");
		// String code = request.getParameter("code");
		String description = request.getParameter("description");
		Role role = new Role();
		role.setName(name);
		role.setCode("tp" + System.currentTimeMillis());
		role.setDescription(description);
		roleService.saveRole(role);
		request.setAttribute("roleList", roleService.getAllRole(page));

		return "query";
	}

	/**
	 * 更新角色
	 * 
	 * @return
	 */
	public String updateRole() throws SqlException {
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		Role role = null;
		role = roleService.getRoleByRoleId(roleId);
		role.setName(request.getParameter("name"));
		// role.setCode(request.getParameter("code"));
		role.setDescription(request.getParameter("description"));
		roleService.updateRole(role);
		request.setAttribute("roleList", roleService.getAllRole(page));
		return "query";
	}

	/**
	 * 检查该角色是否拥有用户
	 * 
	 * @return
	 */
	public String checkDeleteRole() throws SqlException {
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		int userSize = 1;
		userSize = roleService.userCount(roleId);
		String resout = "";
		try {
			response.setCharacterEncoding("UTF-8"); // 先指定输出流的编码
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter(); // 再拿到输出对象

			if (userSize == 0) {
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

	/**
	 * 删除角色
	 * 
	 * @return
	 */
	public String deleteRole() throws SqlException {
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		roleService.deleteResourceByRoleId(roleId);
		roleService.deleteRole(roleId);
		systemLogService.saveSystemLog(SecurityContext.getUser(),
				request.getRemoteAddr(), "删除角色[ " + roleId + " ]");

		return this.queryRole();
	}

	/**
	 * 角色资源授权
	 * 
	 * @return
	 */
	public String roleResource() throws SqlException {
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		Role role = null;

		role = roleService.getRoleByRoleId(roleId);
		request.setAttribute("role", role);
		request.setAttribute("hasResourceList", role.getResources());
		request.setAttribute(
				"resourceList",
				resourceService.remove(resourceService.getAllResource(),
						role.getResources()));

		return "role_resource";
	}

	/**
	 * 角色分配资源
	 * 
	 * @return
	 */
	public String distributeResource() throws SqlException {
		int roleId = Integer.parseInt(request.getParameter("roleId"));

		roleService.deleteRoleResourceByRoleId(roleId);
		// 删除角色表

		String resourceIds = request.getParameter("resourceIds");
		if (!resourceIds.equalsIgnoreCase("")) {// 有资源就插入到角色资源表
			String[] resourceIdStr = resourceIds.split(",");// 分解资源字符串
			for (int i = 0; i < resourceIdStr.length; i++) {
				int resourceId = Integer.valueOf(resourceIdStr[i]);

				if (resourceService.isTopMenu(resourceId)) {// 若为顶级菜单
					if (!resourceService.menuHasInsert(roleId, resourceId)) {// 该顶级菜单没有插入,就插入顶级菜单(顶级菜单可能之前作为某一个非顶级菜单插入)
						roleService.insertRoleResource(roleId, resourceId);
					}
				} else if (resourceService.parMenuHasInsert(roleId, resourceId)) {// 不是顶级菜单,且该菜单的顶级菜单已插入,只需插入该菜单
					roleService.insertRoleResource(roleId, resourceId);
				} else {// 插入该菜单和顶级菜单
					roleService.insertRoleResource(roleId, resourceId);
					int perResId = resourceService
							.getParentResourceId(resourceId);// 父菜单
					roleService.insertRoleResource(roleId, perResId);
				}

			}
		}
		SecurityContext.loadSecurityAccessResource(request.getSession()
				.getServletContext());
		return queryRole();
	}

	/**
	 * 角色名称唯一性验证
	 * 
	 * @return
	 */
	public String checkRoleUnique() throws SqlException {
		String resout = "";
		try {
			request.setCharacterEncoding("UTF-8");
			String value = new String(request.getParameter("value").getBytes(
					"ISO-8859-1"), "UTF-8");
			String type = request.getParameter("type");
			int roleId = 0;
			if (type.equalsIgnoreCase("edit")) {
				roleId = Integer.parseInt(request.getParameter("roleId"));
			}
			boolean isUnique = false;
			isUnique = roleService.checkUniqueRoleCode(value, type, roleId);
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
}
