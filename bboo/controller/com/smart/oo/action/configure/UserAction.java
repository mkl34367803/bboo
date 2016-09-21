package com.smart.oo.action.configure;

import java.util.List;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.Merchant;
import com.smart.entity.Role;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.SecurityUtils;

public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;

	/**
	 * 修改密码
	 */
	public void updatePassword() {
		try {
			String oldPassword=request.getParameter("oldPassword");
			String newPassword=request.getParameter("newPassword");
			if(StringUtilsc.isNotEmpty(oldPassword) && StringUtilsc.isNotEmpty(newPassword)){
				String result = this.factoryServiceImpl.getUserService().updatePassword(oldPassword, newPassword);
				if (null != result) {
					this.writeStream(result, "utf-8");
				} else {
					this.writeStream(SUCCESS, "utf-8");
					info("密码修改成功");
				}
			} else {
				this.writeStream("passNull", "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.writeStream(e.toString(), "utf-8");
		}
	}
	
	/**
	 * 查询所有用户
	 */
	public void queryUsers() {
		try {
			JSONObject result = new JSONObject();
			
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			String operate = request.getParameter("operate");
			
			String id = request.getParameter("searchId");
			String name = request.getParameter("searchName");
			String loginName = request.getParameter("searchLoginName");
			String companyName = request.getParameter("searchCompanyName");
			
			Page page = null;
			if(StringUtilsc.isNotEmpty(startpage) && StringUtilsc.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			User user = new User();
			
			if (StringUtilsc.isNotEmpty(id)) {
				user.setUserId(Integer.parseInt(id));
			}
			if (StringUtilsc.isNotEmpty(name)) {
				user.setName(name);
			}
			if (StringUtilsc.isNotEmpty(loginName)) {
				user.setLoginName(loginName);
			}
			
			if (operate.equals("employee")) {
				User curUser = SecurityContext.getUser();
				Integer curFkmercid = curUser.getFkmercid();
				user.setFkmercid(curFkmercid);
				
				List<User> users = this.factoryServiceImpl.getUserService().queryUsers(user, page);
				
				String str = parstToJson(users);
				result.put("success", "查询成功");
				result.put("rows", str);
				result.put("total", page.getTotalCount());
				this.writeStream(result, "utf-8");
			} else if (operate.equals("superUser")) {
				if (StringUtilsc.isNotEmpty(companyName)) {
					Merchant merchant = this.factoryServiceImpl.getMerchantService().queryByCompanyName(companyName);
					if (null != merchant) {
						Integer fkmercid = merchant.getMercid();
						user.setFkmercid(fkmercid);
					} else{
						result.put("success", "查询成功");
						result.put("rows", "[]");
						result.put("total", page.getTotalCount());
						this.writeStream(result, "utf-8");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}

	/**
	 * 超级用户管理
	 */
	public void updateDeleted() {
		try {
			String userId = request.getParameter("userId");
			String deleted = request.getParameter("deleted");
			
			JSONObject jsonObject=new JSONObject();
			User user = new User();
			if(StringUtilsc.isNotEmpty(userId) && StringUtilsc.isNotEmpty(deleted)) {
				user.setUserId(Integer.parseInt(userId));
				user.setDeleted(deleted);
				this.factoryServiceImpl.getUserService().updateUserStr(user);
				jsonObject.put("success", "更新成功");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 删除用户
	 */
	public void deleteUser() {
		try {
			JSONObject jsonObject=new JSONObject();
			String userId = request.getParameter("userId");
			if (StringUtilsc.isNotEmpty(userId)) {
				this.factoryServiceImpl.getUserService().deleteUser(Integer.parseInt(userId));
				jsonObject.put("success", "删除成功");
				info("用户删除成功，userId为："+userId);
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 更新
	 */
	public void update(){
		try {
			
			String userId = request.getParameter("userId");
			String name = request.getParameter("name");
			String loginName = request.getParameter("loginName");
			String ssbm = request.getParameter("ssbm");
			String sszw = request.getParameter("sszw");
			String sj = request.getParameter("sj");
			String ywm = request.getParameter("ywm");
			String deleted = request.getParameter("deleted");
			//String password = request.getParameter("password");
			//String roleIds = request.getParameter("roleId");
			
			JSONObject jsonObject=new JSONObject();
			
			if (StringUtilsc.isNotEmpty(userId)) {
				User newUser = new User();
				newUser.setUserId(Integer.parseInt(userId));
				newUser.setName(name);
				newUser.setLoginName(loginName);
				newUser.setSsbm(ssbm);
				newUser.setSszw(sszw);
				newUser.setSj(sj);
				newUser.setYwm(ywm);
				newUser.setDeleted(deleted);
				
				String result = this.factoryServiceImpl.getUserService().updateUser(newUser);
				if (null != result) {
					jsonObject.put(ERROR, result);
				} else {
					jsonObject.put("success", "更新成功");
				}
				info("用户信息更新成功，userId为："+userId);
			} 
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 保存
	 */
	public void save(){
		try {
			String name = request.getParameter("name");
			String loginName = request.getParameter("loginName");
			String ssbm = request.getParameter("ssbm");
			String sszw = request.getParameter("sszw");
			String sj = request.getParameter("sj");
			String ywm = request.getParameter("ywm");
			String deleted = request.getParameter("deleted");
			String password = request.getParameter("password");
			String roleIds = request.getParameter("roleId");
			
			JSONObject jsonObject=new JSONObject();
			User user = new User();
			user.setLoginName(loginName);
			user.setName(name);
			user.setSsbm(ssbm);
			user.setSszw(sszw);
			user.setSj(sj);
			user.setYwm(ywm);
			user.setDeleted(deleted);
			user.setPassword(SecurityUtils.getMD5(password));
			String result = this.factoryServiceImpl.getUserService().saveUser(user, roleIds);
			if (null != result) {
				jsonObject.put(ERROR, result);
			} else {
				jsonObject.put("success", "保存成功");
			}
			info("用户创建成功");

			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	private String parstToJson(List<User> users) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int i = 0;
		for (User user : users) {
			sb.append("{\"userId\": \""+user.getUserId()+"\",");
			sb.append("\"password\": \""+user.getPassword()+"\",");
			sb.append("\"loginName\": \""+user.getLoginName()+"\",");
			sb.append("\"name\": \""+user.getName()+"\",");
			sb.append("\"deleted\": \""+user.getDeleted()+"\",");
			sb.append("\"ssbm\": \""+user.getSsbm()+"\",");
			sb.append("\"sszw\": \""+user.getSszw()+"\",");
			sb.append("\"sj\": \""+user.getSj()+"\",");
			sb.append("\"ywm\": \""+user.getYwm()+"\",");
			sb.append("\"fkmercid\": \""+user.getFkmercid()+"\",");
			Set<Role> roles = user.getRoles();
			sb.append("\"roleId\": \"");
			if (roles.size() > 0) {
				int j = 0;
				for (Role role : roles) {
					sb.append(role.getRoleId());
					if(j < roles.size() -1) {
						sb.append(",");
					}
					j++;
				}
			}
			sb.append("\",");
			Merchant merchant = user.getMert();
			if (null != merchant) {
				sb.append("\"companyName\":\"" + user.getMert().getCompanyName()+"\"}");
			} else {
				sb.append("\"companyName\":\"null\"}");
			}
			if(i < users.size() -1) {
				sb.append(",");
			}
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, UserAction.class, null);
	}
	
}
