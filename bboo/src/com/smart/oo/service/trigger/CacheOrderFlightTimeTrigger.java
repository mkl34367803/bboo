package com.smart.oo.service.trigger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.OrderTrendsEntity;
import com.smart.comm.utils.OOComms;
import com.smart.comm.utils.OOLogUtil;
import com.smart.oo.domain.FlightDynamicsDomain;
import com.smart.oo.domain.res.DynamicsVO;
import com.smart.oo.domain.res.FlightDynamicsResult;
import com.smart.oo.service.api.AllApi;
import com.smart.oo.service.imp.FactoryServiceImpl;
import com.smart.utils.StringUtils;

@Service("CacheOrderFlightTimeTrigger")
public class CacheOrderFlightTimeTrigger {

	@Autowired
	private FactoryServiceImpl serviceFactory;

	public void syncdata() {
		List<GjSaleFlightEntity> flights = null;
		try {
			flights = readFlight();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flights == null || flights.size() == 0) {
			OOLogUtil.info("该时间节点无可查询的数据", CacheOrderFlightTimeTrigger.class,
					null);
			return;
		}
		String url = getURL();
		if (!StringUtils.isNotEmpty(url)) {
			OOLogUtil.info("任务开启需要先配置API接口地址",
					CacheOrderFlightTimeTrigger.class, null);
			return;
		}
		Set<String> ids = new HashSet<String>();
		Iterator<GjSaleFlightEntity> iterator = flights.iterator();
		while (iterator.hasNext()) {
			GjSaleFlightEntity f = iterator.next();
			if (!ids.contains(f.getId())) {
				FlightDynamicsResult dynRs = getTrends(f, url);
				for (Iterator<GjSaleFlightEntity> it = flights.iterator(); it
						.hasNext();) {
					GjSaleFlightEntity nf = it.next();
					if (isequals(f, nf)) {
						doing(nf, dynRs);
						ids.add(nf.getId());
					}
				}
			}
		}
		ids.clear();
		flights = null;
	}

	private boolean isequals(GjSaleFlightEntity f, GjSaleFlightEntity nf) {
		if (f.getFlightNum().equalsIgnoreCase(nf.getFlightNum())
				&& f.getDepAircode().equalsIgnoreCase(nf.getDepAircode())
				&& f.getArrAircode().equalsIgnoreCase(nf.getArrAircode())) {
			return true;
		}
		return false;
	}

	private void doing(GjSaleFlightEntity f, FlightDynamicsResult dynRs) {
		List<OrderTrendsEntity> list = null;
		try {
			list = doTrends(dynRs, f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list != null) {
			try {
				serviceFactory.getOrderTrendsService().saveData(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String getURL() {
		List<KeyValEntity> keyvals = null;
		try {
			keyvals = serviceFactory.getIkeyValService().findDataList(
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
			return null;
		}
		return val.getV();
	}

	private FlightDynamicsResult getTrends(GjSaleFlightEntity f, String url) {
		AllApi api = new AllApi();
		FlightDynamicsDomain fdv = new FlightDynamicsDomain();
		fdv.setDepCode(f.getDepAircode().trim());
		fdv.setArrCode(f.getArrAircode().trim());
		fdv.setDepDate(f.getDepartureDate());
		fdv.setIsGetCache(1);
		FlightDynamicsResult frs = null;
		try {
			frs = api.getFlightDynamics(fdv, url, "query_hbdt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return frs;
	}

	private List<OrderTrendsEntity> doTrends(FlightDynamicsResult frs,
			GjSaleFlightEntity f) throws Exception {
		List<OrderTrendsEntity> list = new ArrayList<OrderTrendsEntity>();
		String datetime = com.smart.utils.DateUtils.getDateys();
		if (frs != null && frs.getDatas() != null) {
			List<DynamicsVO> dyvos = frs.getDatas();
			for (DynamicsVO fdy : dyvos) {
				if (f.getFlightNum().equalsIgnoreCase(fdy.getFlightNo())
						|| (f.getShareNum() != null && f.getShareNum()
								.equalsIgnoreCase(fdy.getFlightNo()))) {
					OrderTrendsEntity t = new OrderTrendsEntity();
					t.setActArrTime(fdy.getActADateTime());
					t.setActDepTime(fdy.getActDDateTime());
					t.setArrCode(fdy.getAAirportCode());
					t.setCreateTime(datetime);
					t.setDepCode(fdy.getDAirportCode());
					t.setDepDate(fdy.getDDate());
					t.setFkid(f.getId());
					t.setFlightNo(fdy.getFlightNo());
					t.setPlanArrTime(fdy.getPlanADateTime());
					t.setPlanDepTime(fdy.getPlanDDateTime());
					t.setStatus(fdy.getStatus());
					list.add(t);
				}
			}
		}
		return list;
	}

	private List<GjSaleFlightEntity> readFlight() throws Exception {
		String startTime = com.smart.utils.DateUtils.formatDate(
				DateUtils.addMinutes(new Date(), -9 * 60),
				"yyyy-MM-dd HH:mm:ss");
		String endTime = com.smart.utils.DateUtils.formatDate(
				DateUtils.addMinutes(new Date(), -9 * 60 + 60),
				"yyyy-MM-dd HH:mm:ss");
		return serviceFactory.getOrderTrendsService().readAfterTakeOffFlight(
				startTime, endTime);
	}

}
