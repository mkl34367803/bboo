package com.smart.action.common;

import java.util.List;


import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.entity.Resource;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Constants;

/**
 * 左边菜单ACTION
 * @Description
 * @author ERICH
 * @ClassName: LeftAction
 * @since 2012-12-16 上午12:29:38
 * @Version 1.0
 */
@Results( { @Result(name = "success", location = "/jsp/left.jsp") })
public class LeftAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String execute() {

		List<Resource> menuList = (List<Resource>) request.getSession()
				.getAttribute(Constants.SESSION_KEY_CURRENT_USER_MENU);
		if (menuList == null || menuList.size() == 0) {
			return "success";
		}
		String resourceId = request.getParameter("resourceId");
		// 登录进入主页时，左边默认显示顶部第一个菜单的子菜单
		if (resourceId == null) {
			Resource resource = menuList.get(0);
			request.setAttribute("menuList", resource.getChildren());
			request.setAttribute("rootMenuName", resource.getName());
		} else {
			for (Resource resource : menuList) {
				if (resource.getResourceId().toString().equals(resourceId)) {
					request.setAttribute("menuList", resource.getChildren());
					request.setAttribute("rootMenuName", resource.getName());
				}
			}
		}
		return "success";
	}
}
