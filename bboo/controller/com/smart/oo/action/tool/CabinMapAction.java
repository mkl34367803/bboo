package com.smart.oo.action.tool;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.smart.comm.entity.CabinMapEntity;
import com.smart.comm.utils.OOLogUtil;
import com.smart.framework.base.BaseAction;
import com.smart.framework.base.Page;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

public class CabinMapAction extends BaseAction {

	private static final long serialVersionUID = -8682986943366956576L;
	
	@Autowired
	private FactoryServiceImpl factoryServiceImpl;

	public void queryByPage(){
		try {
			String carrier = request.getParameter("carrier");
			String cabin = request.getParameter("cabin");
			String shareCode = request.getParameter("shareCode");
			String startpage = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			Page page = null;
			if(StringUtils.isNotEmpty(startpage) && StringUtils.isNotEmpty(rows)) {
				page = new Page();
				page.setStartPage(Integer.parseInt(startpage));
				page.setPageSize(Integer.parseInt(rows));
			}
			
			CabinMapEntity cm = new CabinMapEntity();
			cm.setCabin(cabin);
			cm.setCarrier(carrier);
			cm.setShareCode(shareCode);
			
			List<CabinMapEntity> cabinMaps = this.factoryServiceImpl.getCabinMapService().findByPage(cm, page);
			JSONObject result = new JSONObject();
			if (null!= cabinMaps && cabinMaps.size() > 0) {
				String cabinMapsStr = JSON.toJSONString(cabinMaps);
				result.put("list", cabinMapsStr);
			} else {
				result.put("list", "[]");
			}
			result.put(SUCCESS, "查询成功");
			result.put("total", page.getTotalCount());
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void saveOrUpdate() {
		try {
			String id = request.getParameter("id");
			String carrier = request.getParameter("carrier");
			String cabin = request.getParameter("cabin");
			String shareCode = request.getParameter("shareCode");
			String shareCabin = request.getParameter("shareCabin");
			String shareNums = request.getParameter("shareNums");
			String depCode = request.getParameter("depCode");
			String arrCode = request.getParameter("arrCode");
			
			CabinMapEntity cm = new CabinMapEntity();
			cm.setCarrier(carrier.toUpperCase());
			cm.setCabin(cabin.toUpperCase());
			cm.setShareCode(shareCode.toUpperCase());
			cm.setShareCabin(shareCabin.toUpperCase());
			cm.setShareNums(shareNums);
			cm.setDepCode(depCode.toUpperCase());
			cm.setArrCode(arrCode.toUpperCase());
			
			JSONObject result = new JSONObject();
			if (StringUtils.isNotEmpty(id)) {	// 更新
				cm.setId(Integer.parseInt(id));
				this.factoryServiceImpl.getCabinMapService().updateCabinMap(cm);
				result.put(SUCCESS, "更新成功");
				info("CabinMap更新成功，id为："+id);
			} else {	// 保存
				cm.setCreateTime(DateFormat.getStandardDate(new Date()));
				this.factoryServiceImpl.getCabinMapService().saveData(cm);
				result.put(SUCCESS, "保存成功");
				info("CabinMap保存成功");
			}
			this.writeStream(result, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	public void delete() {
		try {
			String id = request.getParameter("id");
			JSONObject jsonObject=new JSONObject();
			if (StringUtils.isNotEmpty(id)) {
				this.factoryServiceImpl.getCabinMapService().deleteById(Integer.parseInt(id));
				jsonObject.put("success", "删除成功");
				info("CabinMap删除成功，id为："+id);
			} else {				
				jsonObject.put("error", "ID不存在");
			}
			this.writeStream(jsonObject, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("error", e.toString());
			this.writeStream(result, "utf-8");
		}
	}
	
	private void info(String msg){
		OOLogUtil.info(msg, CabinMapAction.class, null);
	}
}
