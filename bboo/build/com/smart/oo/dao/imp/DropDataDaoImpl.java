package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.DropDataEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IDropDataDao;

@Repository("BBOODropDataDaoImpl")
public class DropDataDaoImpl extends BaseDAO<DropDataEntity, String> implements
		IDropDataDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7411088606768532774L;

	@Override
	public String saveData(DropDataEntity data) throws Exception {
		// TODO Auto-generated method stub
		return this.save(data);
	}

	@Override
	public List<DropDataEntity> findDistinctFlight(DropDataEntity data)
			throws Exception {
		// TODO Auto-generated method stub
		List<DropDataEntity> list = new ArrayList<DropDataEntity>();
		StringBuffer buf = new StringBuffer(
				"select distinct dep_code,arr_code,ddate,carrier from t_drop_data where ddate+' '+case when isnull(dtime,'')='' then '23:59' else dtime end>=? and fileno=? order by ddate,carrier,dep_code,arr_code");
		List<Object[]> objs = this.findBySQL(buf.toString(), new Object[] {
				data.getDDate() + ' ' + data.getDTime(), data.getFileno() });
		if (objs != null) {
			for (Object[] obj : objs) {
				DropDataEntity e = new DropDataEntity();
				e.setDepCode(obj[0].toString());
				e.setArrCode(obj[1].toString());
				e.setDDate(obj[2].toString());
				e.setCarrier(obj[3].toString());
				list.add(e);
			}
		}
		return list;
	}

	@Override
	public List<DropDataEntity> findFlight(DropDataEntity data)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer(
				"from DropDataEntity where mno=? and fileno=? and ddate+' '+case when isnull(dtime,'')='' then '23:59' else dtime end>=?");
		return this.find(buf.toString(),
				new Object[] { data.getMno(), data.getFileno(),
						data.getDDate() + ' ' + data.getDTime() });
	}
}
