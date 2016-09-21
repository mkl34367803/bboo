package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.OrderTrendsEntity;

public interface IOrderTrendsService {

	int saveData(OrderTrendsEntity entity) throws Exception;

	void saveData(List<OrderTrendsEntity> entitys) throws Exception;
	
	List<GjSaleFlightEntity> readAfterTakeOffFlight(String startTime,
			String endTime) throws Exception ;
}
