package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.AirFlightEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IAirFlightDao;
import com.smart.utils.StringUtils;

@Repository("BBOOAirFlightDaoImpl")
public class AirFlightDaoImpl extends BaseDAO<AirFlightEntity, Integer>
		implements IAirFlightDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6993423748645871009L;

	public AirFlightDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer saveData(AirFlightEntity d) throws Exception {
		// TODO Auto-generated method stub
		return this.save(d);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AirFlightEntity> findByLineno(AirFlightEntity e)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		buf.append("from AirFlightEntity where 1=1 ");
		if (StringUtils.isNotEmpty(e.getDep())) {
			buf.append(" and dep=?");
			list.add(e.getDep());
		}
		if (StringUtils.isNotEmpty(e.getArr())) {
			buf.append(" and arr=?");
			list.add(e.getArr());
		}
		if (StringUtils.isNotEmpty(e.getFno())) {
			buf.append(" and fno=?");
			list.add(e.getFno());
		}
		buf.append(" order by id desc");
		return this.find(buf.toString(), list.toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AirFlightEntity> findShares(AirFlightEntity e) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer();
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		buf.append("from AirFlightEntity where 1=1 and sno<>''");
		if (e != null) {
			if (StringUtils.isNotEmpty(e.getDep())) {
				buf.append(" and dep=?");
				list.add(e.getDep());
			}
			if (StringUtils.isNotEmpty(e.getArr())) {
				buf.append(" and arr=?");
				list.add(e.getArr());
			}
			if (StringUtils.isNotEmpty(e.getFno())) {
				buf.append(" and fno=?");
				list.add(e.getFno());
			}
		}
		buf.append(" order by dep,arr,id desc");
		return this.find(buf.toString(), list.toArray());
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		this.executeSql("truncate table t_air_flight");
	}

	@Override
	public void deleteData(AirFlightEntity e) throws Exception {
		// TODO Auto-generated method stub
		this.executeSql("delete from t_air_flight where indexkey=?",
				new Object[] { e.getIndexkey() });
	}

}
