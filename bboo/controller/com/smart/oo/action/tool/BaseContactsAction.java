package com.smart.oo.action.tool;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseContactsEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.OOUtils;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class BaseContactsAction extends BaseAction {

	private static final long serialVersionUID = -7279341421725142627L;
	@Autowired
	private FactoryServiceImpl factoryService;
	
	public void queryByPage() {
		try {
			String startPage = request.getParameter("page");
			String rows = request.getParameter("rows");
			String linkman = request.getParameter("linkman");
			String linktel = request.getParameter("linktel");
			String phone = request.getParameter("phone");
			
			Page page = null;
			if (StringUtils.isNotEmpty(startPage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startPage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			BaseContactsEntity entity = new BaseContactsEntity();
			entity.setMno(mno);
			entity.setLinkman(linkman);
			entity.setLinktel(linktel);
			entity.setPhone(phone);
			
			List<BaseContactsEntity> baseContactsList = this.factoryService.getBaseContactsService().queryByPage(entity, page);
			
			JSONObject result = new JSONObject();
			if (baseContactsList != null && baseContactsList.size() > 0) {
				String list = JSON.toJSONString(baseContactsList);
				result.put("list", list);
			} else {
				result.put("list", "[]");
			}
			result.put(SUCCESS, "查询成功");
			result.put("total", page.getTotalCount());
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void saveOrUpdate() {
		try {
			String id = request.getParameter("id");
			String linkman = request.getParameter("linkman");
			String linktel = request.getParameter("linktel");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			BaseContactsEntity entity = new BaseContactsEntity();
			entity.setMno(mno);
			entity.setLinkman(linkman);
			entity.setLinktel(linktel);
			entity.setPhone(phone);
			entity.setEmail(email);
			entity.setAddress(address);
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				entity.setId(Integer.parseInt(id));
				this.factoryService.getBaseContactsService().updateContact(entity);
				result.put(SUCCESS, "修改成功");
				info("BaseContacts修改成功, id 为"+id);
			} else {
				entity.setCreateTime(DateFormat.getCompactSysdate());
				this.factoryService.getBaseContactsService().saveContact(entity);
				result.put(SUCCESS, "添加成功");
				info("BaseContacts添加成功");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void delete() {
		try {
			String id = request.getParameter("id");
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				this.factoryService.getBaseContactsService().deleteContact(Integer.parseInt(id));
				result.put(SUCCESS, "删除成功");
				info("BaseContacts删除成功，id为："+id);
			} else {
				result.put(ERROR, "id为空");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	private void info(String msg) {
		OOLogUtil.info(msg, BaseContactsAction.class, null);
	}
}
