package com.smart.oo.action.rule;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.CabinRuleEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class CabinRuleAction extends BaseAction {

	private static final long serialVersionUID = 2429456370979241992L;
	
	@Autowired
	private FactoryServiceImpl factoryService;

	public void queryByPage() {
		JSONObject result = new JSONObject();
		try {
			String carrier = request.getParameter("carrier");
			String cabin = request.getParameter("cabin");
			String startValidity = request.getParameter("startValidity");
			String endValidity = request.getParameter("endValidity");

			String startPage = request.getParameter("startPage");
			String pageSize = request.getParameter("pageSize");
			
			Page page = null;
			if (StringUtils.isNotEmpty(startPage) && StringUtils.isNotEmpty(pageSize)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startPage));
				page.setPageSize(Integer.parseInt(pageSize));
			}
			
			CabinRuleEntity entity = new CabinRuleEntity();
			entity.setCarrier(carrier);
			entity.setCabin(cabin);
			entity.setStartValidity(startValidity);
			entity.setEndValidity(endValidity);
			List<CabinRuleEntity> crList = this.factoryService.getCabinRuleService().queryByPage(entity, page);
			String list = "";
			if (crList != null && crList.size() > 0) {
				list = JSON.toJSONString(crList);
			} else {
				list = "[]";
			}
			result.put(SUCCESS, "查询成功");
			result.put("total", page.getTotalCount());
			result.put("list", list);
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void queryList() {
		JSONObject result = new JSONObject();
		try {
			String carrier = request.getParameter("carrier");
			String cabin = request.getParameter("cabin");
			String startValidity = request.getParameter("startValidity");
			String endValidity = request.getParameter("endValidity");
			
			CabinRuleEntity entity = new CabinRuleEntity();
			entity.setCarrier(carrier);
			entity.setCabin(cabin);
			entity.setStartValidity(startValidity);
			entity.setEndValidity(endValidity);
			List<CabinRuleEntity> crList = this.factoryService.getCabinRuleService().queryList(entity);
			String list = "";
			if (crList != null && crList.size() > 0) {
				list = JSON.toJSONString(crList);
			} else {
				list = "[]";
			}
			result.put("list", list);
			result.put(SUCCESS, "查询成功");
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void saveOrUpdate() {
		JSONObject result = new JSONObject();
		try {
			String id = request.getParameter("id");
			User user = SecurityContext.getUser();
			String name = user.getName();
			CabinRuleEntity entity = getEntity();
			entity.setLastOperator(name);
			if (StringUtils.isNotEmpty(id)) {
				entity.setId(Integer.parseInt(id));
				this.factoryService.getCabinRuleService().updateEntity(entity);
				result.put(SUCCESS, "修改成功");
				info(id+"修改成功");
			} else {
				entity.setCreateTime(DateFormat.getStandardSysdate());
				this.factoryService.getCabinRuleService().saveEntity(entity);
				result.put(SUCCESS, "保存成功");
				info("保存成功");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	private CabinRuleEntity getEntity() throws Exception {
		String carrier = request.getParameter("carrier");
		String cabin = request.getParameter("cabin");
		String returnRule = request.getParameter("returnRule");
		String changeRule = request.getParameter("changeRule");
		String endorsement = request.getParameter("endorsement");
		String voidDay = request.getParameter("voidDay");
		String startValidity = request.getParameter("startValidity");
		String endValidity = request.getParameter("endValidity");
		
		CabinRuleEntity entity = new CabinRuleEntity();
		entity.setCarrier(carrier.toUpperCase());
		entity.setCabin(cabin.toUpperCase());
		entity.setReturnRule(returnRule);
		entity.setChangeRule(changeRule);
		entity.setEndorsement(endorsement);
		entity.setEndValidity(endValidity);
		entity.setVoidDay(Integer.parseInt(voidDay));
		entity.setStartValidity(startValidity);
		entity.setEndValidity(endValidity);
		return entity;
	}
	
	public void delete() {
		JSONObject result = new JSONObject();
		try {
			String id = request.getParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				this.factoryService.getCabinRuleService().deleteById(Integer.parseInt(id));
				info(id+"删除成功");
				result.put(SUCCESS, "删除成功");
			} else {
				result.put(ERROR, "id为空");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	private void info(String msg) {
		OOLogUtil.info(msg, CabinRuleAction.class, null);
	}
}
