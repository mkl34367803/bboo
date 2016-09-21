package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.SysLogEntity;

public interface ISysLogDao {
	
	/**
	 * 添加系统操作日志
	 * @param sysLogEntity
	 * @return
	 * @throws Exception
	 */
	public String saveSysLog(SysLogEntity sysLogEntity) throws Exception;
	
	/**
	 * 通过外键查询系统操作日志
	 * @param foreginKey
	 * @return
	 */
	public List<SysLogEntity> querySysLogByForeginKey(String foreginKey) throws Exception;
}
