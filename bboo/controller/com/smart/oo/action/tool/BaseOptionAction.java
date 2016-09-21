package com.smart.oo.action.tool;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.Option;
import com.smart.entity.Select;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;

public class BaseOptionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5440869450201146555L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;

	/**
	 * 分页查询
	 */
	public void queryOptionsByPage() {
		try {
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			String selectId = request.getParameter("searchSelectId");
			
			Page page = null;
			JSONObject result = new JSONObject();
			if(StringUtils.isNotBlank(startpage) && StringUtils.isNotBlank(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			Option option = new Option();
			if(StringUtilsc.isNotEmptyAndNULL(selectId)) {
				option.setSelect_id(Integer.parseInt(selectId));
				
				List<Option> options = this.factoryServiceImpl.getBaseOptionService().queryOptionsByPage(page, option);
				String str = parseToJson(options);
				
				result.put("success", "查询成功");
				result.put("rows", str);
				result.put("total", page.getTotalCount());
			} else {
				result.put("error", "selectId获取失败");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	/**
	 * 保存和修改
	 */
	public void saveOrUpdate(){
		try {
			String optionId = request.getParameter("optionId");
			String descrtption = request.getParameter("description");
			String isDisplay = request.getParameter("isDisplay");
			String optionName = request.getParameter("optionName");
			String optionText = request.getParameter("optionText");
			String optionValue = request.getParameter("optionValue");
			String selectId = request.getParameter("selectId");
			Option option = new Option();
			option.setDescrtption(descrtption);
			option.setIs_display(isDisplay);
			option.setOption_name(optionName);
			option.setOption_text(optionText);
			option.setOption_value(optionValue);
			
			JSONObject jsonObject=new JSONObject();
			
			if(StringUtilsc.isNotEmpty(selectId)){
				Select select = this.factoryServiceImpl.getBaseSelectService().querySelectById(Integer.parseInt(selectId));
				if(select != null) {
					option.setSelect_id(Integer.parseInt(selectId));
					option.setSelect(select);
				} else {
					jsonObject.put("error", "select_id不存在");
					this.writeStream(jsonObject, "utf-8");
					return ;
				}
			}
			
			if(StringUtilsc.isNotEmpty(optionId)) {
				option.setOption_id(Integer.parseInt(optionId));
				this.factoryServiceImpl.getBaseOptionService().updateOption(option);
				jsonObject.put("success", "更新成功");
				info("option更新成功，optionId为："+optionId);
			} else {
				String createPerson = (String) request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME");
				option.setCreate_person(createPerson);
				String currentDate = DateFormat.getStandardDate(new Date());
				option.setCreate_time(DateFormat.getStandardDate(currentDate));
				option.setLast_edittime(DateFormat.getStandardDate(currentDate));
				this.factoryServiceImpl.getBaseOptionService().saveOption(option);
				jsonObject.put("success", "保存成功");
				info("option保存成功");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除
	 */
	public void deleteOption(){
		try {
			String optionId = request.getParameter("optionId");
			JSONObject jsonObject=new JSONObject();
			if(StringUtilsc.isNotEmpty(optionId)) {
				this.factoryServiceImpl.getBaseOptionService().deleteOptionById(Integer.parseInt(optionId));
				jsonObject.put("success", "删除成功");
				info("option删除成功，optionId为："+optionId);
			} else {				
				jsonObject.put("error", "ID不存在");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	public void queryBySelectId() {
		try {
			String selectId = request.getParameter("selectId");
			JSONObject jsonObject=new JSONObject();
			if (StringUtilsc.isNotEmpty(selectId)) {
				Option option = new Option();
				option.setSelect_id(Integer.parseInt(selectId));
				List<Option> options = this.factoryServiceImpl.getBaseOptionService().queryOptionList(option);
				if (options != null && options.size() > 0) {
					jsonObject.put("list", parseToJson(options));
					jsonObject.put(SUCCESS, "查询成功");
				}
			} else {
				jsonObject.put("error", "selectId为空");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", e.toString());
			this.writeStream(jsonObject, "utf-8");
		}
	}
	
	/**
	 * 将获取对象转化为json
	 * @param options
	 * @return
	 */
	private String parseToJson(List<Option> options) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		int i = 0;
		for (Option option : options) {
			sb.append("{\"optionId\": \""+option.getOption_id()+"\",");
			sb.append("\"selectId\": \""+option.getSelect_id()+"\",");
			sb.append("\"optionName\": \""+option.getOption_name()+"\",");
			sb.append("\"optionValue\": \""+option.getOption_value()+"\",");
			sb.append("\"optionText\": \""+option.getOption_text()+"\",");
			sb.append("\"descrtption\": \""+option.getDescrtption()+"\",");
			sb.append("\"createTime\": \""+option.getCreate_time()+"\",");
			sb.append("\"createPerson\": \""+option.getCreate_person()+"\",");
			sb.append("\"lastEdittime\": \""+option.getLast_edittime()+"\",");
			sb.append("\"isDisplay\": \""+option.getIs_display()+"\"}");
			if(i < options.size() -1) {
				sb.append(",");
			}
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, BaseOptionAction.class, null);
	}
}
