package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IGjSaleFlightService;

@Service("GjSaleFlightServiceImpl")
public class GjSaleFlightServiceImpl implements IGjSaleFlightService{
	
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public List<GjSaleFlightEntity> queryByFkid(String fkid)
			throws Exception {
		// TODO Auto-generated method stub
		return factoryDao.getSaleFlightDao().queryByFkid(fkid);
	}
/*
	@Override
	public void flightAdd(GjSaleFlightEntity flight) throws SqlException {
		this.factoryDao.getSaleFlightDao().flightadd(flight);
		
	}*/

	@Override
	public void savefliht(GjSaleFlightEntity entity) throws Exception {
		this.factoryDao.getSaleFlightDao().saveSaleFlight(entity);
		
	}

	@Override
	public int flightById(String fligthId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
