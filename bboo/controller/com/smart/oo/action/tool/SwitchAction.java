package com.smart.oo.action.tool;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.entity.SwitchEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.Merchant;
import com.smart.entity.Option;
import com.smart.entity.Select;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class SwitchAction extends BaseAction {

	private static final long serialVersionUID = -9181506704698157349L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;
	
	/**
	 * 查询
	 */
	public void querySwitch(){
		try {
			JSONObject result = new JSONObject();
			
			String id = request.getParameter("searchID");
			String companyName = request.getParameter("searchCompanyName");
			String onoff = request.getParameter("searchOnoff");
			String mkey = request.getParameter("searchMkey");
			SwitchEntity switchEntity = new SwitchEntity();
			if (StringUtils.isNotEmpty(id) && StringUtils.isNumeric(id)) {
				switchEntity.setId(Integer.parseInt(id));
			}
			if (StringUtils.isNotEmpty(onoff) && StringUtils.isNumeric(onoff)) {
				switchEntity.setOnoff(Integer.parseInt(onoff));
			}
			if (StringUtils.isNotEmpty(mkey)) {
				switchEntity.setMkey(mkey);
			}
			
			String startpage = request.getParameter("page"); // 注意这里是getParameter方法
			String rows = request.getParameter("rows");
			Page page = null;
			if(StringUtils.isNotEmpty(startpage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			if (StringUtilsc.isNotEmpty(companyName)) {
				Merchant merchant = this.factoryServiceImpl.getMerchantService().queryByCompanyName(companyName);
				if (null != merchant) {
					switchEntity.setMno(merchant.getMno());
				} else{						
					result.put("success", "查询成功");
					result.put("switches", "[]");
					this.writeStream(result, "utf-8");
					return;
				}
			}
			
			List<SwitchEntity> switchEntities = this.factoryServiceImpl.getSwitchService().queryByPage(switchEntity, page);
			String str = parstToJson(switchEntities);
			result.put("success", "查询成功");
			if (null != switchEntities &&switchEntities.size() > 0) {
				result.put("switches", str);
			} else {
				result.put("switches", "[]");
			}
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
	 * 保存或修改
	 */
	public void saveOrUpdate() {
		try {
			String id = request.getParameter("id");
			String mno = request.getParameter("mno");
			String onoff = request.getParameter("onoff");
			String validity = request.getParameter("validity");
			String mkey = request.getParameter("mkey");
			String describe = request.getParameter("describe");
			
			SwitchEntity switchEntity = new SwitchEntity();
			
			switchEntity.setMno(mno);
			switchEntity.setOnoff(Integer.parseInt(onoff));
			switchEntity.setValidity(validity);
			switchEntity.setMkey(mkey);
			switchEntity.setDescribe(describe);
			
			JSONObject jsonObject = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				switchEntity.setId(Integer.parseInt(id));
				
				this.factoryServiceImpl.getSwitchService().updateSwitch(switchEntity);
				jsonObject.put("success", "更新成功");
				info("switch更新成功，id为："+id);
				this.writeStream(jsonObject, "utf-8");
			} else {
				switchEntity.setCtm(DateFormat.getStandardDate(new Date()));
				
				this.factoryServiceImpl.getSwitchService().saveSwitch(switchEntity);
				jsonObject.put("success", "保存成功");
				info("switch保存成功");
				this.writeStream(jsonObject, "utf-8");
			}
			
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
	public void deleteSwitch() {
		try {
			String id = request.getParameter("id");
			JSONObject jsonObject = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				this.factoryServiceImpl.getSwitchService().deleteSwitch(Integer.parseInt(id));
				jsonObject.put("success", "删除成功");
				info("switch删除成功，id为："+id);
				this.writeStream(jsonObject, "utf-8");
			} else {				
				jsonObject.put("error", "ID不存在");
				this.writeStream(jsonObject, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	private String parstToJson(List<SwitchEntity> switchEntities) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			int i = 0;
			Select select = new Select();
			select.setSelect_name(OOComms.SWITCH_MKEY_SELECT_NAME);
			List<Select> selects = this.factoryServiceImpl.getBaseSelectService().querySelects(select);
			List<Option> options = null;
			if (selects != null && selects.size() == 1) {
				Integer selectId = selects.get(0).getSelect_id();
				Option option = new Option();
				option.setSelect_id(selectId);
				
				options = this.factoryServiceImpl.getBaseOptionService().queryOptionList(option);
			}
			for (SwitchEntity switchEntity : switchEntities) {
				sb.append("{\"id\": \""+switchEntity.getId()+"\",");
				sb.append("\"mno\": \""+switchEntity.getMno()+"\",");
				sb.append("\"onoff\": \""+switchEntity.getOnoff()+"\",");
				sb.append("\"validity\": \""+switchEntity.getValidity()+"\",");
				sb.append("\"ctm\": \""+switchEntity.getCtm()+"\",");
				
				sb.append("\"mkey\": \""+switchEntity.getMkey()+"\",");
				
				if (options != null && options.size() > 0) {
					for (Option option2 : options) {
						if (switchEntity.getMkey().equals(option2.getOption_value())) {
							sb.append("\"mkeyCh\": \""+option2.getOption_text()+"\",");
						}
					}
				}
				
				sb.append("\"describe\": \""+switchEntity.getDescribe()+"\",");
				Merchant merchant = new Merchant();
				merchant.setMno(switchEntity.getMno());
				List<Merchant> merchants = this.factoryServiceImpl.getMerchantService().query(merchant);
				if (merchants.size() == 1) {
					sb.append("\"companyName\":\"" + merchants.get(0).getCompanyName()+"\"}");
				} else {
					sb.append("\"companyName\":\"null\"}");
				}
				if(i < switchEntities.size() -1) {
					sb.append(",");
				}
				i++;
			}
			sb.append("]");
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, SwitchAction.class, null);
	}
}
