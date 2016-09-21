package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.SysLogEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.ISysLogDao;
@Repository("SysLogDaoImpl")
public class SysLogDaoImpl extends BaseDAO<SysLogEntity, String> implements ISysLogDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String saveSysLog(SysLogEntity sysLogEntity) throws Exception {
		return this.save(sysLogEntity);
	}

	@Override
	public List<SysLogEntity> querySysLogByForeginKey(String foreginKey) throws Exception {
		String hql = "from SysLogEntity where foreginKey='"+foreginKey+"'";
		return this.find(hql);
	}

}
