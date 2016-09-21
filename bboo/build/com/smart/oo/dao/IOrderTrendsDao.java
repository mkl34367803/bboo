package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.OrderTrendsEntity;

public interface IOrderTrendsDao {

	int saveData(OrderTrendsEntity entity) throws Exception;

	List<GjSaleFlightEntity> readAfterTakeOffFlight(String startTime,
			String endTime) throws Exception;
}
