package com.smart.oo.action.airf;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.comm.entity.AirFlightEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOUtils;
import com.smart.framework.base.BaseAction;
import com.smart.oo.domain.GetFlightByLineDomain;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;

@Results({ @Result(name = "trigger", location = "/jsp/build/airf/trigger.jsp") })
public class AirFlightAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7298682902135697818L;
	@Autowired
	private FactoryServiceImpl factoryService;
	private static Integer LINECOUNT = 0;
	private static Integer OKCOUNT = 0;

	public String toPage() {
		request.setAttribute("msg", "航段总数：" + LINECOUNT + ";初始化执行：" + OKCOUNT
				+ ";还剩余：" + (LINECOUNT - OKCOUNT));
		return "trigger";
	}

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

	public void loadShareFlt() {
		List<AirFlightEntity> enlist = getShareList();
		String filePath = com.smart.comm.utils.FileUtils.getFilePath(
				"jsp/files/share", "share_" + System.currentTimeMillis());
		com.smart.comm.utils.FileUtils.writeHeader(filePath, "航司", "航班号", "舱位",
				"实际承运", "实际承运航班号", "起飞机场", "到达城市");
		StringBuffer bodyBuf = new StringBuffer();
		if (enlist != null && enlist.size() > 0) {
			Iterator<AirFlightEntity> iterator = enlist.iterator();
			while (iterator.hasNext()) {
				AirFlightEntity vo = iterator.next();
				String cabins = vo.getCabins();
				if (cabins != null) {
					String[] cwarr = cabins.split(",");
					for (String c : cwarr) {
						com.smart.comm.utils.FileUtils.writeBody(
								bodyBuf,
								vo.getAir(),
								vo.getFno(),
								c,
								vo.getSno().length() > 2 ? (vo.getSno()
										.replace("*", "").trim()
										.substring(0, 2)) : "", vo.getSno(), vo
										.getDep(), vo.getArr());

					}
					cwarr = null;
				}
				iterator.remove();
			}
		}
		com.smart.comm.utils.FileUtils.xiefile(bodyBuf, filePath);
		String zipFilePath = com.smart.comm.utils.FileUtils.writeEnd(filePath);
		zipFilePath = "jsp" + zipFilePath.split("jsp")[1];
		this.writeStream("<a href='../" + zipFilePath
				+ "' name='share_loads_id001x'>" + "点击这下载./" + zipFilePath
				+ "</a>", "utf-8");
	}

	public void init() {

		List<KeyValEntity> keyvals = null;
		try {
			keyvals = factoryService.getIkeyValService().findDataList(
					OOComms.CACHE_KEY_VAL_DATAS_KEY, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		KeyValEntity val = null;
		if (keyvals != null && keyvals.size() > 0) {
			for (KeyValEntity v : keyvals) {
				if (OOComms.GET_API_COMM_URL.equalsIgnoreCase(v.getK())) {
					val = v;
					break;
				}
			}
		}
		if (val == null) {
			return;
		}
		List<BaseOfficeEntity> offs = null;
		try {
			offs = factoryService.getBaseOfficeService().findDataList(
					OOComms.CACHE_OFF_VAL_DATAS_NAME,
					OOComms.CACHE_OFF_VAL_DATAS_KEY, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseOfficeEntity off = null;
		if (offs != null && offs.size() > 0) {
			for (BaseOfficeEntity b : offs) {
				if (b.getOfftypes() != null && 1 == b.getIsu()
						&& b.getOfftypes().toUpperCase().contains("AV")) {
					off = b;
					break;
				}
			}
		}
		if (off == null) {
			return;
		}
		// 得到全国航线
		List<String> lines = null;
		try {
			lines = OOUtils.readFileByLine(OOUtils.getFileUri("txt",
					"lines.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (lines == null || lines.size() == 0) {
			return;
		}
		String depDate = DateUtils.formatDate(DateUtils.addDate(new Date(), 7));
		LINECOUNT = lines.size();
		OKCOUNT = 0;
		for (String l : lines) {
			String[] shuz = l.trim().split("	");
			if (shuz.length > 1) {
				String depCode = shuz[0].trim();
				String arrCode = shuz[1].trim();
				GetFlightByLineDomain line = new GetFlightByLineDomain();
				line.setDepCode(depCode.trim().toUpperCase());
				line.setArrCode(arrCode.trim().toUpperCase());
				line.setDepDate(depDate);
				factoryService.getAirFlightService().saveDatas(line, off, val);
			}
			OKCOUNT++;
		}
	}
}
