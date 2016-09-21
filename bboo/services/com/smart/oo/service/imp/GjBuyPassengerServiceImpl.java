package com.smart.oo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.comm.entity.GjBuyPassengerEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IGjBuyPassengerService;

@Transactional
@Service("GjBuyPassengerServiceImpl")
public class GjBuyPassengerServiceImpl implements IGjBuyPassengerService {
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public String savePassenger(GjBuyPassengerEntity gjBuyPassengerEntity) throws Exception {
		return factoryDao.getGjBuyPassengerDao().savePassenger(gjBuyPassengerEntity);
	}

	@Override
	public boolean updateTicketNum(String id, String eticketNum) throws Exception {
		return this.factoryDao.getGjBuyPassengerDao().updateTicketNum(id, eticketNum);
	}

}
