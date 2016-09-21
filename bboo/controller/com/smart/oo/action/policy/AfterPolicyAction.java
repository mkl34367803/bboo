package com.smart.oo.action.policy;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.OOUtils;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class AfterPolicyAction extends BaseAction {

	private static final long serialVersionUID = -5148095365861278601L;
	@Autowired
	private FactoryServiceImpl factoryService;
	
	// 分页查询
	public void queryByPage() {
		try {
			JSONObject result = new JSONObject();
			
			String startPage = request.getParameter("startPage");
			String rows = request.getParameter("rows");
			String carrier = request.getParameter("carrier");
			String productType = request.getParameter("productType");
			
			Page page = null;
			if (StringUtils.isNotEmpty(startPage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startPage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			AfterPolicyEntity entity = new AfterPolicyEntity();
			entity.setMno(mno);
			if (StringUtils.isNotEmpty(carrier)) {
				entity.setCarrier(carrier);
			}
			if (StringUtils.isNotEmpty(productType)) {
				entity.setProductType(productType);
			}
			List<AfterPolicyEntity> afterPolicyEntities = this.factoryService.getAfterPolicyService().findListForPage(entity, page);
			if (null != afterPolicyEntities && afterPolicyEntities.size() > 0) {
				String list = JSON.toJSONString(afterPolicyEntities);
				result.put("list", list);
			} else {
				result.put("list", "[]");
			}
			result.put(SUCCESS, result);
			result.put("total", page.getTotalCount());
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	// 保存和修改
	public void saveOrUpdate() {
		try {
			String id = request.getParameter("id");
			String channel = request.getParameter("channel");
			String productType = request.getParameter("productType");
			String carrier = request.getParameter("carrier");
			String after = request.getParameter("after");
			String channelCh = request.getParameter("channelCh");
			String productTypeCh = request.getParameter("productTypeCh");
			
			User user = SecurityContext.getUser();
			String mno = OOUtils.getUserMerchant(user);
			
			AfterPolicyEntity entity = new AfterPolicyEntity();
			entity.setMno(mno);
			entity.setChannel(channel);
			entity.setChannelCh(channelCh);
			if (StringUtils.isNotEmpty(productType)) {
				entity.setProductType(productType);
				entity.setProductTypeCh(productTypeCh);
			}
			if (StringUtils.isNotEmpty(carrier)) {
				entity.setCarrier(carrier.toUpperCase());
			}
			if (StringUtils.isNotEmpty(after)) {
				entity.setAfter(Double.parseDouble(after));
			}
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				entity.setId(Integer.parseInt(id));
				this.factoryService.getAfterPolicyService().updateData(entity);
				result.put(SUCCESS, "修改成功");
				info(id+"修改成功");
			} else {
				entity.setCreateTime(DateFormat.getStandardDate(new Date()));
				this.factoryService.getAfterPolicyService().saveData(entity);
				result.put(SUCCESS, "保存成功");
				info("保存成功");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put(ERROR, e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	// 删除
	public void delete() {
		try {
			JSONObject result = new JSONObject();
			
			String id = request.getParameter("id");
			
			if(StringUtils.isNotEmpty(id)) {
				this.factoryService.getAfterPolicyService().deleteByID(Integer.parseInt(id));
				result.put(SUCCESS, "删除成功");
				info(id + "删除成功");
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
		OOLogUtil.info(msg, AfterPolicyAction.class, null);
	}

}
