package com.smart.oo.action.tool;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;

public class KeyValueAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5025816207931394155L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;

	/**
	 * 分页查询
	 */
	public void queryKeyValues(){
		try {
			String startpage = request.getParameter("page"); // 注意这里是getParameter方法
			String rows = request.getParameter("rows");
			String id = request.getParameter("searchID");
			String k = request.getParameter("searchK");
			
			Page page = null;
			if(StringUtils.isNotBlank(startpage) && StringUtils.isNotBlank(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			KeyValEntity keyValEntity = new KeyValEntity();
			if (StringUtilsc.isNotEmpty(id)) {
				keyValEntity.setId(Integer.parseInt(id));
			}
			if (StringUtilsc.isNotEmpty(k)) {
				keyValEntity.setK(k);
			}
			
			List<KeyValEntity> keyValEntities = factoryServiceImpl.getIkeyValService().queryKeyValues(page, keyValEntity);
			String str=JSON.toJSONString(keyValEntities);
			JSONObject result = new JSONObject();
			result.put("success", "查询成功");
			result.put("rows", str);
			result.put("total", page.getTotalCount());
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
			String id = request.getParameter("id");
			String k = request.getParameter("k");
			String v = request.getParameter("v");
			String describe = request.getParameter("describe");
			
			JSONObject jsonObject=new JSONObject();
			if (StringUtilsc.isNotEmptyAndNULL(id)) {// 修改
				KeyValEntity keyValEntity = this.factoryServiceImpl.getIkeyValService().queryById(Integer.parseInt(id));
				if (!k.equals(keyValEntity.getK())) {
					KeyValEntity checkKeyValue = this.factoryServiceImpl.getIkeyValService().queryByKey(k);
					if (checkKeyValue != null) {
						jsonObject.put("error", "key重复");
						this.writeStream(jsonObject, "utf-8");
						return;
					}
					keyValEntity.setK(k);
				}
				keyValEntity.setV(v);
				keyValEntity.setDescribe(describe);
				this.factoryServiceImpl.getIkeyValService().updateData(keyValEntity);
				jsonObject.put("success", "更新成功");
				info("keyValue更新成功，id为："+id);
			} else {// 保存
				KeyValEntity checkKeyValue = this.factoryServiceImpl.getIkeyValService().queryByKey(k);
				if (checkKeyValue != null) {
					jsonObject.put("error", "key重复");
				} else {
					String ctm = DateFormat.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
					KeyValEntity keyValEntity = new KeyValEntity();
					keyValEntity.setK(k);
					keyValEntity.setV(v);
					keyValEntity.setDescribe(describe);
					keyValEntity.setCtm(ctm);
					this.factoryServiceImpl.getIkeyValService().saveData(keyValEntity);
					jsonObject.put("success", "保存成功");
					info("keyValue保存成功");
				}
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
	public void deleteKeyValue() {
		try {
			String id = request.getParameter("id");
			JSONObject jsonObject=new JSONObject();
			if (StringUtilsc.isNotEmptyAndNULL(id)) {
				this.factoryServiceImpl.getIkeyValService().deleteData(id);
				jsonObject.put("success", "删除成功");
				info("keyValue删除成功，id为："+id);
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
	
	private void info(String msg){
		OOLogUtil.info(msg, KeyValueAction.class, null);
	}
}
