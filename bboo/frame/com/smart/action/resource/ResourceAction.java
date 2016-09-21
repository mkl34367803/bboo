package com.smart.action.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.Resource;
import com.smart.framework.base.BaseAction;
import com.smart.service.permission.ResourceService;

/**
 * 
 * @Description
 * @author ERICH
 * @ClassName: ResourceAction
 * @since 2012-12-16 上午12:30:40
 * @Version 1.0
 */
@Results({
		@Result(name = "success", location = "/jsp/resource/resource_list.jsp"),
		@Result(name = "query", location = "/resource/resource!queryResource.do", type = "redirect"),
		@Result(name = "resource_add", location = "/jsp/resource/resource_add.jsp") })
public class ResourceAction extends BaseAction {

	private static final long serialVersionUID = -9080565056057929731L;
	@Autowired
	private ResourceService resourceService;

	public String queryResource() throws SqlException {

		request.setAttribute("resourceList",
				resourceService.queryResource(page));

		return "success";
	}

	/**
	 * 添加资源
	 * 
	 * @return
	 */
	public String addResource() throws SqlException {
		List<Resource> resourceList = null;
		resourceList = resourceService.getAllMenu();
		request.setAttribute("menuList", resourceList);
		return "resource_add";
	}

	/**
	 * 保存资源
	 * 
	 * @return
	 */
	public String saveResource() throws SqlException {

		int pid = 0;
		int sort = 0;
		Resource resource = new Resource();
		String name = request.getParameter("name"); // 资源名称
		String value = request.getParameter("value"); // 资源路径
		String type = request.getParameter("type"); // 资源类型
		if (type.equalsIgnoreCase("MENU")
				&& request.getParameter("sort") != null) {
			pid = Integer.parseInt(request.getParameter("pid")); // 资源所属顶级菜单
			sort = Integer.parseInt(request.getParameter("sort"));// 资源优先级
			int maxSort = 0;
			maxSort = resourceService.getMaxSubMenuId(pid);
			// 获取该菜单的子菜单个数
			// int maxSort=5; //获取该菜单的子菜单个数
			if (sort > maxSort) {// 页面传过来的优先级大于子菜单的个数,则该子菜单在该菜单的sort为:maxSort+1
				resource.setSort(maxSort + 1);
			} else {// 将sort及后面的子菜单下移一位,该子菜单在该菜单的sort为:sort
				resource.setSort(sort);

				resourceService.updateResourceSort(sort, pid);

			}
		}
		String description = request.getParameter("description"); // 资源描述
		resource.setName(name);
		resource.setValue(value);
		resource.setType(type);
		resource.setDescription(description);
		if (type.equalsIgnoreCase("MENU")
				&& request.getParameter("sort") != null) {
			Resource perResource = null;

			perResource = resourceService.getResourceByResourceId(pid);

			resource.setParent(perResource);

			resourceService.updateResource(perResource);

		}

		if (type.equalsIgnoreCase("MENU")
				&& request.getParameter("sort") == null) {
			sort = resourceService.getFarMenuNumber();
			// 获得该菜单优先序号
			resource.setSort(sort);
		}
		resourceService.saveResource(resource);
		request.setAttribute("resourceList",
				resourceService.queryResource(page));

		return "query";
	}

	/**
	 * 检查资源名称的唯一性
	 * 
	 * @return
	 */
	public String checkResourceUnique() throws SqlException {
		String resout = "";
		try {
			request.setCharacterEncoding("UTF-8");
			String value = new String(request.getParameter("value").getBytes(
					"ISO-8859-1"), "UTF-8");
			boolean isUnique = false;
			isUnique = resourceService.checkUniqueResourceName(value);
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
