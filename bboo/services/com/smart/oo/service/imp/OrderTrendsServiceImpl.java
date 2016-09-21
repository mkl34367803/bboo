package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.OrderTrendsEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IOrderTrendsService;

@Service("BBOOOrderTrendsServiceImpl")
public class OrderTrendsServiceImpl implements IOrderTrendsService {

	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public int saveData(OrderTrendsEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return factoryDao.getOrderTrendsDao().saveData(entity);
	}

	@Override
	public void saveData(List<OrderTrendsEntity> entitys) throws Exception {
		// TODO Auto-generated method stub
		if (entitys != null && entitys.size() > 0) {
			for (OrderTrendsEntity entity : entitys) {
				factoryDao.getOrderTrendsDao().saveData(entity);
			}
		}

	}

	@Override
	public List<GjSaleFlightEntity> readAfterTakeOffFlight(String startTime,
			String endTime) throws Exception {
		// TODO Auto-generated method stub
		return factoryDao.getOrderTrendsDao().readAfterTakeOffFlight(startTime,
				endTime);
	}

}
