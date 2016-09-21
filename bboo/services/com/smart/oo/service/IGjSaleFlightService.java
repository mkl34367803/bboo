package com.smart.oo.service;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSaleFlightEntity;

public interface IGjSaleFlightService {

	/**
	 * 根据订单表主键查询销售航班信息
	 * @param fkid
	 * @return
	 */
	List<GjSaleFlightEntity> queryByFkid(String fkid) throws Exception;
	
	public void savefliht(GjSaleFlightEntity entity)throws Exception;
	
	int flightById (String fligthId);
}
