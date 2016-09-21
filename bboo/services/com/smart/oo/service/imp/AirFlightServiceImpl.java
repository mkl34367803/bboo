package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.AirFlightEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.domain.GetFlightByLineDomain;
import com.smart.oo.domain.res.CabinVO;
import com.smart.oo.domain.res.FlightByLineVO;
import com.smart.oo.domain.res.GetFlightByLineResult;
import com.smart.oo.service.IAirFlightService;
import com.smart.oo.service.api.AllApi;
import com.smart.utils.DateUtils;

@Service("BBOOAirFlightServiceImpl")
public class AirFlightServiceImpl implements IAirFlightService {

	@Autowired
	private FactoryDaoImpl daof;

	private GetFlightByLineResult avh(GetFlightByLineDomain line,
			BaseOfficeEntity off, KeyValEntity k) {
		String url = k.getV();
		AllApi api = new AllApi();
		line.setAppKey(off.getAppkey());
		line.setTermId(String.valueOf(System.currentTimeMillis()));
		GetFlightByLineResult rs = null;
		try {
			rs = api.avh(line, url, "gds_for_avh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void saveDatas(GetFlightByLineDomain line, BaseOfficeEntity off,
			KeyValEntity k) {
		GetFlightByLineResult rs = avh(line, off, k);
		if (rs != null && rs.getCount() > 0) {
			String dateTime = DateUtils.getDateys();
			List<FlightByLineVO> flights = rs.getLines();
			List<AirFlightEntity> list = new ArrayList<AirFlightEntity>();
			for (FlightByLineVO f : flights) {
				List<CabinVO> cabins = f.getCabins();
				StringBuffer cbuf = new StringBuffer();
				if (cabins != null && cabins.size() > 0) {
					for (CabinVO c : cabins) {
						cbuf.append(c.getCode() + ",");
					}
				}
				AirFlightEntity d = new AirFlightEntity();
				d.setDep(f.getDepCode());
				d.setArr(f.getArrCode());
				d.setDept(f.getStartTime());
				d.setArrt(f.getEndTime());
				d.setCabins(cbuf != null && cbuf.toString().length() > 1 ? cbuf
						.substring(0, cbuf.length() - 1) : "");
				d.setFno(f.getFlightNum());
				d.setAir(f.getCarrier());
				d.setInat(1);
				d.setIndexkey(f.getDepCode() + f.getArrCode()
						+ f.getFlightNum());
				d.setIsu(1);
				d.setSno(f.getFlightNumExtra());
				d.setIstop(f.getIsStop());
				d.setCt(dateTime);
				list.add(d);
			}
			if (list.size() > 0) {
				for (AirFlightEntity d : list) {
					try {
						daof.getAirFlightDao().deleteData(d);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						daof.getAirFlightDao().saveData(d);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println(line.getDepCode() + line.getArrCode()
					+ line.getDepDate() + ";未获取到航班信息");
		}
	}

	@Override
	public Integer saveData(AirFlightEntity d) throws Exception {
		// TODO Auto-generated method stub
		return daof.getAirFlightDao().saveData(d);
	}

	@Override
	public List<AirFlightEntity> findByLineno(AirFlightEntity e)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getAirFlightDao().findByLineno(e);
	}

	@Override
	public List<AirFlightEntity> findShares(AirFlightEntity e) throws Exception {
		// TODO Auto-generated method stub
		return daof.getAirFlightDao().findShares(e);
	}

	@Override
	public void deleteData(AirFlightEntity e) throws Exception {
		// TODO Auto-generated method stub
		daof.getAirFlightDao().deleteData(e);
	}

}
