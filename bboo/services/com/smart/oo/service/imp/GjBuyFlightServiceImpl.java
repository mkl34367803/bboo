package com.smart.oo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.GjBuyFlightEntity;
import com.smart.oo.dao.IGjBuyFlightDao;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IGjBuyFlightService;
@Service("GjBuyFlightServiceImpl")
public class GjBuyFlightServiceImpl implements IGjBuyFlightService {
	@Autowired
	private FactoryDaoImpl factoryDao;
	@Override
	public String saveFlight(GjBuyFlightEntity gjBuyFlightEntity) throws Exception {
		return factoryDao.getGjBuyFlightDao().saveFlight(gjBuyFlightEntity);
	}
	
}
