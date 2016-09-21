package com.smart.oo.action.office;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.utils.DateUtil;
import com.smart.entity.User;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.service.imp.FactoryServiceImpl;

public class BaseOfficeAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	 private FactoryServiceImpl factoryService;
	public void queryBaseOffice(){
		try {
			String startpage = request.getParameter("page"); // 注意这里是getParameter方法
			String rows = request.getParameter("rows");
			String id = request.getParameter("searchID");
			String office = request.getParameter("searchOffice");
			String offtypes = request.getParameter("searchOfftypes");
			String appkey = request.getParameter("searchAppkey");
			String isu = request.getParameter("searchIsu");
			User user = (User) request.getSession().getAttribute(
					"SESSION_KEY_CURRENT_USER");
			String mno = null;
			if (user!=null&&user.getMert() != null) {
				mno = user.getMert().getMno(); // 每个用户对应一个航司的商户号
			}
			Page page = null;
			if (StringUtils.isNotBlank(startpage)
					&& StringUtils.isNotBlank(startpage)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			BaseOfficeEntity baseOfficeEntity=new BaseOfficeEntity();
			baseOfficeEntity.setMno(mno);
			if(StringUtils.isNotEmpty(id)){
				baseOfficeEntity.setId(Integer.parseInt(id));
			}
			if(StringUtils.isNotEmpty(office)){
				baseOfficeEntity.setOffice(office);
			}
			if(StringUtils.isNotEmpty(offtypes)){
				baseOfficeEntity.setOfftypes(offtypes);
			}
			if(StringUtils.isNotEmpty(appkey)){
				baseOfficeEntity.setAppkey(appkey);
			}
			if(StringUtils.isNotEmpty(isu)){
				if(Integer.parseInt(isu)!=0){
					baseOfficeEntity.setIsu(Integer.parseInt(isu));
				}
			}
			List<BaseOfficeEntity> list=factoryService.getBaseOfficeService().queryBaseOffice(page, baseOfficeEntity);
			String str=JSON.toJSONString(list);
			JSONObject result = new JSONObject();
			result.put("success", "查询成功");
			result.put("rows", str);
			result.put("total", page.getTotalCount());
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");// TODO: handle exception
		}
	}
	public void saveOrUpdate(){
		try {
			User user = (User) request.getSession().getAttribute(
					"SESSION_KEY_CURRENT_USER");
			String mno = null;
			if (user!=null&&user.getMert() != null) {
				mno = user.getMert().getMno(); // 每个用户对应一个航司的商户号
			}
			String id = request.getParameter("id"); // 注意这里是getParameter方法
			String office = request.getParameter("office"); // 注意这里是getParameter方法
			String offtypes = request.getParameter("offtypes"); // 注意这里是getParameter方法
			String appkey = request.getParameter("appkey"); // 注意这里是getParameter方法
			String isu = request.getParameter("isu"); // 注意这里是getParameter方法
			String etdzNum = request.getParameter("etdzNum");
			BaseOfficeEntity baseOfficeEntity=new BaseOfficeEntity();
			baseOfficeEntity.setAppkey(appkey);
			baseOfficeEntity.setIsu(Integer.parseInt(isu));
			baseOfficeEntity.setMno(mno);
			baseOfficeEntity.setOffice(office);
			baseOfficeEntity.setOfftypes(offtypes);
			baseOfficeEntity.setEtdzNum(etdzNum != null ? Integer.parseInt(etdzNum) : 0);
			if(StringUtils.isNotEmpty(id)){
				baseOfficeEntity.setId(Integer.parseInt(id));
				factoryService.getBaseOfficeService().updateOffice(baseOfficeEntity);
			}else {
				baseOfficeEntity.setCtime(DateUtil.DateToStr(new java.util.Date()));
				factoryService.getBaseOfficeService().saveOffice(baseOfficeEntity);
			}
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", "保存或更新成功");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");// TODO: handle exception
		}
	}
	
	public void queryOfficeByID(){
		try {
			String id = request.getParameter("id"); // 注意这里是getParameter方法
			BaseOfficeEntity baseOfficeEntity=null;
			if(StringUtils.isNotEmpty(id)){
				baseOfficeEntity=factoryService.getBaseOfficeService().queryOfficeByID(Integer.parseInt(id));
			}
			String str=JSON.toJSONString(baseOfficeEntity);
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", "查询成功");
			jsonObject.put("baseOfficeEntity", str);
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	public void deleteOfficeById(){
		try {
			String id = request.getParameter("id"); // 注意这里是getParameter方法
			if(StringUtils.isNotEmpty(id)){
				factoryService.getBaseOfficeService().deleteOfficeById(Integer.parseInt(id));
			}
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("success", "删除成功");
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
}
