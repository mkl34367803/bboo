package com.smart.oo.action.tool;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.Select;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;

public class BaseSelectAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7329887889560865089L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;
	
	/**
	 * 分页查询
	 */
	public void querySelectsByPage(){
		try {
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			String selectId = request.getParameter("searchSelectId");
			String selectName = request.getParameter("searchSelectName");
			String createPerson = request.getParameter("searchCreatePerson");
			
			Page page = null;
			if(StringUtils.isNotBlank(startpage) && StringUtils.isNotBlank(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			Select select = new Select();
			if (StringUtilsc.isNotEmpty(selectId)) {
				select.setSelect_id(Integer.parseInt(selectId));
			}
			if (StringUtilsc.isNotEmpty(selectName)) {
				select.setSelect_name(selectName);
			}
			if (StringUtilsc.isNotEmpty(createPerson)) {
				select.setCreate_person(createPerson);
			}
			List<Select> selects = this.factoryServiceImpl.getBaseSelectService().querySelectsByPage(page, select);
			String jsonStr = parseToJson(selects);
			
			JSONObject result = new JSONObject();
			result.put("success", "查询成功");
			result.put("rows", jsonStr);
			result.put("total", page.getTotalCount());
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 保存和修改
	 */
	public void saveOrUpdate() {
		try {
			String selectId = request.getParameter("selectId");
			String selectName = request.getParameter("selectName");
			String descrtption = request.getParameter("descrtption");
			String isDisplay = request.getParameter("isDisplay");
			
			Select select = new Select();
			select.setSelect_name(selectName);
			select.setDescrtption(descrtption);
			select.setIs_display(isDisplay);
			
			JSONObject jsonObject=new JSONObject();
			
			if (StringUtilsc.isNotEmpty(selectId)) {
				select.setSelect_id(Integer.parseInt(selectId));
				this.factoryServiceImpl.getBaseSelectService().updateSelect(select);
				jsonObject.put("success", "更新成功");
				info("select更新成功，selectId为："+selectId);
			} else {
				String createPerson = (String) request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME");
				select.setCreate_person(createPerson);
				String currentDate = DateFormat.getStandardDate(new Date());
				select.setCreate_time(DateFormat.getStandardDate(currentDate));
				select.setLast_edittime(DateFormat.getStandardDate(currentDate));
				this.factoryServiceImpl.getBaseSelectService().saveSelect(select);
				jsonObject.put("success", "保存成功");
				info("select保存成功");
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
	 * 删除
	 */
	public void deleteSelect(){
		try {
			JSONObject jsonObject=new JSONObject();
			String selectId = request.getParameter("selectId");
			if (StringUtilsc.isNotEmpty(selectId)) {
				this.factoryServiceImpl.getBaseSelectService().deleteSelectById(Integer.parseInt(selectId));
				jsonObject.put("success", "删除成功");
				info("select删除成功，selectId为："+selectId);
			} else {				
				jsonObject.put("error", "selectId不存在");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	public void queryBySelectName() {
		try {
			String selectName = request.getParameter("selectName");
			JSONObject jsonObject=new JSONObject();
			if (StringUtils.isNotBlank(selectName)) {
				Select select = new Select();
				select.setSelect_name(selectName);
				List<Select> selects = this.factoryServiceImpl.getBaseSelectService().querySelects(select);
				if (selects != null && selects.size() > 0) {
					jsonObject.put("success", "查询成功");
					jsonObject.put("list", parseToJson(selects));
				} else {
					jsonObject.put("error", "select不存在");
				}
			} else {
				jsonObject.put("error", "selectName为空");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	private String parseToJson(List<Select> selects) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int len = selects.size();
		for (int i = 0; i < len; i++) {
			sb.append("{\"selectId\":\"" + selects.get(i).getSelect_id()+"\",");
			sb.append("\"selectName\":\"" + selects.get(i).getSelect_name()+"\",");
			sb.append("\"descrtption\":\"" + selects.get(i).getDescrtption()+"\",");
			sb.append("\"createTime\":\"" + selects.get(i).getCreate_time()+"\",");
			sb.append("\"createPerson\":\"" + selects.get(i).getCreate_person()+"\",");
			sb.append("\"lastEdittime\":\"" + selects.get(i).getLast_edittime()+"\",");
			sb.append("\"isDisplay\":\"" + selects.get(i).getIs_display()+"\"}");
			if (i < len) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, BaseSelectAction.class, null);
	}
}
