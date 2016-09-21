package com.smart.oo.action.configure;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.smart.entity.Role;
import com.smart.framework.base.BaseAction;
import com.smart.oo.service.imp.FactoryServiceImpl;

public class BaseRoleAction extends BaseAction {

	private static final long serialVersionUID = 5902449687900109480L;
	
	@Autowired
	private FactoryServiceImpl factoryService;
	
	public void getAllRole() {
		JSONObject result = new JSONObject();
		try {
			List<Role> roleList = this.factoryService.getBaseRoleService().getRoleList();
			if (roleList == null || roleList.isEmpty()) {
				result.put("list", "[]");
			} else {
				String list = parseToJson(roleList);
				result.put("list", list);
			}
			result.put(SUCCESS, "查询成功");
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(ERROR, e.toString());
		}
		this.writeStream(result, "utf-8");
	}
	
	/**
	 * 将获取对象转化为json
	 * @param options
	 * @return
	 */
	private String parseToJson(List<Role> roleList) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int i = 0;
		for (Role role : roleList) {
			sb.append("{\"roleId\": \""+role.getRoleId()+"\",");
			sb.append("\"name\": \""+role.getName()+"\",");
			sb.append("\"code\": \""+role.getCode()+"\",");
			sb.append("\"description\": \""+role.getDescription()+"\"}");
			if(i < roleList.size() -1) {
				sb.append(",");
			}
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
}
