package com.smart.oo.service;

import com.smart.comm.entity.GjBuyFlightEntity;

public interface IGjBuyFlightService {
	/**
	 * 保存航班信息
	 * @param gjBuyFlightEntity
	 * @return
	 * @throws Excetpiton
	 */
	public String saveFlight(GjBuyFlightEntity gjBuyFlightEntity) throws Exception;
}
