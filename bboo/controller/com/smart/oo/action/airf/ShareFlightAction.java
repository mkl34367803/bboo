package com.smart.oo.action.airf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.entity.AirFlightEntity;
import com.smart.comm.entity.ShareFlightEntity;
import com.smart.framework.base.BaseAction;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;

@Results({ @Result(name = "trigger", location = "/jsp/build/airf/trigger.jsp") })
public class ShareFlightAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -512901876951865746L;
	@Autowired
	private FactoryServiceImpl factoryService;

	private List<AirFlightEntity> getShareList() {
		List<AirFlightEntity> enlist = null;
		try {
			enlist = factoryService.getAirFlightService().findShares(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enlist;
	}

	/**
	 * 初始化价格
	 */
	public void initPrice() {
		// 清空原来的数据
		try {
			// 匹配主飞舱位
			factoryService.getShareFlightService().updateShareCabin();
			// 匹配舱位运价
			factoryService.getShareFlightService().updatePrice();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.writeStream("初始化任务执行结束", "UTF-8");

	}

	public void initShare() {

		List<AirFlightEntity> enlist = getShareList();
		if (enlist != null && enlist.size() > 0) {
			Iterator<AirFlightEntity> iterator = enlist.iterator();
			List<ShareFlightEntity> list = new ArrayList<ShareFlightEntity>();
			while (iterator.hasNext()) {
				AirFlightEntity vo = iterator.next();
				String cabins = vo.getCabins();
				String shareCode = vo.getSno().length() > 2 ? (vo.getSno()
						.replace("*", "").trim().substring(0, 2)) : "";
				if (cabins != null) {
					String[] cwarr = cabins.split(",");
					for (String c : cwarr) {
						if (StringUtils.isNotEmpty(c)) {
							ShareFlightEntity e = new ShareFlightEntity();
							e.setArrCode(vo.getArr().toUpperCase());
							e.setBackPoint("0");
							e.setCabin(c.trim().toUpperCase());
							e.setCarrier(vo.getAir().toUpperCase());
							e.setCreateTime(DateUtils.getDateys());
							e.setDepCode(vo.getDep().toUpperCase());
							e.setFixedFee("0");
							e.setFlightNo(vo.getFno().toUpperCase());
							e.setPrice("0");
							e.setShareCabin("");
							e.setShareCode(shareCode);
							e.setShareNo(vo.getSno());
							list.add(e);
						}
					}
					cwarr = null;
				}
				iterator.remove();
			}
			// 保存 共享航班基础数据
			if (list != null && list.size() > 0) {
				// 清空原来的数据
				try {
					factoryService.getShareFlightService().deleteAll();
					factoryService.getShareFlightService().saveData(list);
					// 匹配主飞舱位
					factoryService.getShareFlightService().updateShareCabin();
					// 匹配舱位运价
					factoryService.getShareFlightService().updatePrice();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			this.writeStream("初始化任务执行结束", "UTF-8");
		}
	}

}
