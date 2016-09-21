package com.smart.oo.action.tool;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BookRuleEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.OOUtils;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class BookRuleAction extends BaseAction {

	private static final long serialVersionUID = 6645166940329520537L;
	@Autowired
	private FactoryServiceImpl factoryService;

	public void queryByPage() {
		try {
			String shopName = request.getParameter("shopName");
			String startPage = request.getParameter("startPage");
			String rows = request.getParameter("rows");
			
			Page page = null;
			if (StringUtils.isNotEmpty(startPage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startPage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			BookRuleEntity entity = new BookRuleEntity();
			entity.setMno(mno);
			entity.setShopName(shopName);
			
			JSONObject result = new JSONObject();
			List<BookRuleEntity> list = this.factoryService.getBookRuleService().queryByPage(entity, page);
			String str = JSON.toJSONString(list);
			result.put("list", str);
			result.put(SUCCESS, "查询成功");
			info("查询成功");
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
			String shopName = request.getParameter("shopName");//不能为空
			String policyType = request.getParameter("policyType");//不能为空
			String bookChannel = request.getParameter("bookChannel");
			String isDrop = request.getParameter("isDrop");
			String isuse = request.getParameter("isuse");
			String lr = request.getParameter("lr");
			String includeRule = request.getParameter("includeRule");
			String ignoredRules = request.getParameter("ignoredRules");
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			BookRuleEntity entity = new BookRuleEntity();
			entity.setMno(mno);
			entity.setShopName(shopName);
			entity.setPolicyType(policyType);
			entity.setBookChannel(bookChannel);
			if (StringUtils.isNotEmpty(isDrop)) {
				entity.setIsDrop(Long.parseLong(isDrop));
			}
			if (StringUtils.isNotEmpty(isuse)) {
				entity.setIsuse(Long.parseLong(isuse));
			}
			if (StringUtils.isNotEmpty(lr)) {
				entity.setLr(Double.parseDouble(lr));
			}
			entity.setIncludeRule(includeRule);
			entity.setIgnoredRules(ignoredRules);
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				entity.setId(Integer.parseInt(id));
				this.factoryService.getBookRuleService().updateBookRule(entity);
				result.put(SUCCESS, "修改成功");
				info("修改成功");
			} else {
				entity.setCreateTime(DateFormat.getStandardSysdate());
				this.factoryService.getBookRuleService().saveBookRule(entity);
				result.put(SUCCESS, "添加成功");
				info("添加成功");
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
				this.factoryService.getBookRuleService().deleteBookRule(Integer.parseInt(id));
				result.put(SUCCESS, "删除成功");
				info("删除成功");
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
		OOLogUtil.info(msg, BookRuleAction.class, null);
	}
}
