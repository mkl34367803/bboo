package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.SysLogEntity;

public interface ISysLogService {
	/**
	 * 保存系统日志
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
