package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.SysLogEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.ISysLogService;
@Service("SysLogServiceImpl")
public class SysLogServiceImpl implements ISysLogService{
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;
	@Override
	public String saveSysLog(SysLogEntity sysLogEntity) throws Exception {
		return factoryDaoImpl.getSysLogDao().saveSysLog(sysLogEntity);
	}
	@Override
	public List<SysLogEntity> querySysLogByForeginKey(String foreginKey)
			throws Exception {
		return this.factoryDaoImpl.getSysLogDao().querySysLogByForeginKey(foreginKey);
	}

}
