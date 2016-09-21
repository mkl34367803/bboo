package com.smart.oo.action.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.smart.entity.Resource;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.from.ResourceVo;
import com.smart.oo.service.IGjResourceService;
import com.smart.service.permission.ResourceService;

/**
 * http://127.0.0.1:8080/bboo/resource/gj-resource!queryResource.do
 * 
 * @author Administrator
 * 
 */
// @Namespace("/")
@Results({
		@Result(name = "success", location = "/jsp/build/resourceManage.jsp"),
		@Result(name = "error", location = "/login.jsp"),
		@Result(name = "logout", location = "/login.jsp", type = "redirect") })
public class GjResourceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@javax.annotation.Resource(name="GjResouceServiceImpl")
	private IGjResourceService resourceService;
	@Autowired
	private ResourceService resourceService2;
	public void queryResource() throws Exception {
		try {
			System.out.println("ddddddddddddddddddddddddddddddddddd");
			String startpage = request.getParameter("page"); // 注意这里是getParameter方法
			String rows = request.getParameter("rows");
			Page page = new Page();
			page.setStartPage(Integer.parseInt(startpage));
			page.setPageSize(Integer.parseInt(rows));
			// 获取查询参数
			String resourceId = request.getParameter("resourceId");
			String name = request.getParameter("name");
			String sort = request.getParameter("sort");
			String type = request.getParameter("type");
			String value = request.getParameter("value");
			String pid = request.getParameter("pid");
			ResourceVo resourceParam = null;
			if (StringUtils.isNotEmpty(resourceId)
					|| StringUtils.isNotEmpty(name)
					|| StringUtils.isNotEmpty(sort)
					|| StringUtils.isNotEmpty(type)
					|| StringUtils.isNotEmpty(value)
					|| StringUtils.isNotEmpty(pid)) {
				resourceParam = new ResourceVo();
				if (StringUtils.isNotEmpty(resourceId)) {
					resourceParam.setResource_id(Integer.parseInt(resourceId));
				}
				if (StringUtils.isNotEmpty(name)) {
					resourceParam.setName(name);
				}
				if (StringUtils.isNotEmpty(sort)) {
					resourceParam.setSort(Integer.parseInt(sort));
				}
				if (StringUtils.isNotEmpty(sort)) {
					resourceParam.setType(type);
				}
				if (StringUtils.isNotEmpty(sort)) {
					resourceParam.setValue(value);
				}
				if (StringUtils.isNotEmpty(pid)) {
					resourceParam.setPid(Integer.parseInt(pid));
				}
			}
			@SuppressWarnings({ "rawtypes", "unused" })
			List example=resourceService2.queryResource(page);
			System.out.println(example);
			List<Resource> list = resourceService.queryResource(page,
					resourceParam);
			SimplePropertyFilter filter = new SimplePropertyFilter();
			String str = JSON.toJSONString(list, filter,
					SerializerFeature.DisableCircularReferenceDetect);// 防止循环引用
			JSONObject result = new JSONObject();
			// JSONArray jsonArray=new JSONArray();

			result.put("rows", str);
			result.put("total", page.getTotalCount());
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveOrUpdateResource() throws Exception {
		try {
			System.out.println("ddddddddddaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			Resource resource = new Resource();
			resource.setDescription(request.getParameter("description"));
			resource.setName(request.getParameter("name"));
			String sort = request.getParameter("sort");
			if(StringUtils.isNotEmpty(sort)){
			resource.setSort(Integer.parseInt(sort));
			}
			resource.setType(request.getParameter("type"));
			resource.setValue(request.getParameter("value"));
			String pid = request.getParameter("parentID");
			Resource parent = null;
			if (pid != null) {
				parent = resourceService.getResourceByID(Integer.parseInt(pid));
			}
			resource.setParent(parent);
			String resourceId = request.getParameter("resourceId");
			if (StringUtils.isEmpty(resourceId)) {
				resourceService.saveResourse(resource);
			} else {
				resource.setResourceId(Integer.parseInt(resourceId));
				resourceService.updateResource(resource);
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", "保存或者更新数据成功数据");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("errorMsg", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}

	public void deleteResource() throws Exception {
		try {
			System.out.println("deleteaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			String delIds = request.getParameter("delIds");
			String str[] = delIds.split(",");
			int delNums = 0;
			delNums = resourceService.deleteResourceByBatch(Arrays.asList(str));
			JSONObject result = new JSONObject();
			if (delNums > 0) {
				result.put("success", "true");
				result.put("delNums", delNums);
			} else {
				result.put("errorMsg", "删除失败");
			}
			PrintWriter writer;
			writer = response.getWriter();
			writer.println(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("errorMsg", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}

	public Resource transformToResource(ResourceVo resourceVo) {
		Resource resource = new Resource();
		resource.setResourceId(resourceVo.getResource_id());
		resource.setDescription(resourceVo.getDescription());
		resource.setName(resourceVo.getName());
		resource.setSort(resourceVo.getSort());
		resource.setType(resourceVo.getType());
		resource.setValue(resourceVo.getValue());
		if (resourceVo.getPid() != null) {
			// resourceService.
		}
		return resource;
	}

	public void getResource() {
		try {
			String startpage = request.getParameter("page"); // 注意这里是getParameter方法
			String rows = request.getParameter("rows");
			Page page = new Page();
			page.setStartPage(Integer.parseInt(startpage));
			page.setPageSize(Integer.parseInt(rows));
			List<Resource> list = resourceService.queryAllResource(page, null);
			SimplePropertyFilter filter = new SimplePropertyFilter();
			String str = JSON.toJSONString(list, filter,
					SerializerFeature.DisableCircularReferenceDetect);// 防止循环引用
			JSONObject result = new JSONObject();
			// JSONArray jsonArray=new JSONArray();
			result.put("rows", str);
			result.put("total", page.getTotalCount());
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getParentResource() {
		try {
			List<Resource> list = resourceService.getParentResource();
			SimplePropertyFilter filter = new SimplePropertyFilter();
			String str = JSON.toJSONString(list, filter,
					SerializerFeature.DisableCircularReferenceDetect);// 防止循环引用
			PrintWriter out = response.getWriter();
			out.println(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
