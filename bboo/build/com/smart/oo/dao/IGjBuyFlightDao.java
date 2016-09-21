package com.smart.oo.dao;

import com.smart.comm.entity.GjBuyFlightEntity;

public interface IGjBuyFlightDao {
	/**
	 * 保存航班信息
	 * @param gjBuyFlightEntity
	 * @return
	 * @throws Excetpiton
	 */
	public String saveFlight(GjBuyFlightEntity gjBuyFlightEntity) throws Exception;
}
