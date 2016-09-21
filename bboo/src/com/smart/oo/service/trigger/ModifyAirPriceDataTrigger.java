package com.smart.oo.service.trigger;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.AirPriceEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOUtils;
import com.smart.oo.domain.GetCacheFdPriceDomain;
import com.smart.oo.domain.res.FDDataVO;
import com.smart.oo.domain.res.GetFdsPriceResult;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.DateUtils;
import com.smart.utils.StringUtils;
import com.smart.utils.UniqId;

/**
 * 更新国内 公布运价
 * 
 * @author eric
 * 
 */
@Service("ModifyAirPriceDataTrigger")
public class ModifyAirPriceDataTrigger {

	@Autowired
	private FactoryServiceImpl serviceFactory;

	public void syncdata() {
		List<String> lines = getLines();
		if (lines != null) {
			syncFdPrice(lines);
		}
	}

	public List<String> getLines() {// 得到全国航线
		List<String> lines = null;
		try {
			lines = OOUtils.readFileByLine(OOUtils.getFileUri("txt",
					"lines.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
	}

	public void syncFdPrice(List<String> lines) {

		if (lines != null && lines.size() > 0) {
			for (String l : lines) {
				String[] shuz = l.trim().split("	");
				if (shuz.length > 1) {
					String depCode = shuz[0].trim();
					String arrCode = shuz[1].trim();
					GetCacheFdPriceDomain requestV = new GetCacheFdPriceDomain();
					requestV.setDep(depCode);
					requestV.setArr(arrCode);
					GetFdsPriceResult rlt = null;
					try {
						rlt = syncFdPrice(requestV);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (rlt != null)
						syncFdPrice(rlt);
				}
			}
		}
	}

	public void syncFdPrice(GetFdsPriceResult rlt) {
		try {
			List<AirPriceEntity> ens = toPubPriceEntity(rlt);
			if (ens != null)
				serviceFactory.getAirPriceService().saveDataAndDelByLine(ens);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<AirPriceEntity> toPubPriceEntity(GetFdsPriceResult rlt) {

		if (rlt != null && rlt.getDatas() != null) {
			List<AirPriceEntity> list = new ArrayList<AirPriceEntity>();
			String currtime = String.valueOf(System.currentTimeMillis());
			for (FDDataVO d : rlt.getDatas()) {
				try {
					AirPriceEntity fd = new AirPriceEntity();
					fd.setId(UniqId.getInstance().getStrId());
					fd.setA("");
					fd.setN("");
					fd.setS("");
					fd.setKeyID("-1");
					fd.setRenderID("-1");
					fd.setTravleType(0);
					fd.setWeekLimit(0);
					fd.setStopTime("");
					fd.setPriceType(0);
					fd.setArrCode(d.getArrc());
					fd.setBaseCabin(d.getBcabin());
					fd.setBasePrice(StringUtils.isNotEmpty(d.getBprice()) ? Double
							.parseDouble(d.getBprice()) : 0f);
					fd.setBeginDate(d.getStartDate());
					fd.setCabin(d.getCabin());
					fd.setCarrier(d.getCarrier());
					fd.setCreateTime(DateUtils.getDateys());
					fd.setDepCode(d.getDepc());
					fd.setDiscount(StringUtils.isNotEmpty(d.getDiscount()) ? Double
							.parseDouble(d.getDiscount()) : 0f);
					fd.setEndDate(d.getEndDate());
					fd.setKeys(currtime);
					fd.setPredaysEnd(0);
					fd.setPredaysStart(0);
					fd.setPrice(StringUtils.isNotEmpty(d.getSprice()) ? Double
							.parseDouble(d.getSprice()) : 0f);
					fd.setIsDelete(0);
					list.add(fd);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return list;
		}
		return null;

	}

	public GetFdsPriceResult syncFdPrice(GetCacheFdPriceDomain l)
			throws Exception {
		GetFdsPriceResult rlstfd = new AllApi().getCacheFdsPrice(l,
				OOComms.BB_API_URL, "ibe_cmd_cache_fds");
		return rlstfd;
	}
}
