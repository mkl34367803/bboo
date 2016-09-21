package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IGjSalePassengerService;

@Transactional
@Service("GjSalePassengerServiceImpl")
public class GjSalePassengerServiceImpl implements IGjSalePassengerService {
	
	@Autowired
	private FactoryDaoImpl factoryDao;


	@Override
	public List<GjSalePassengerEntity> queryByFkid(String fkid)
			throws Exception {
		// TODO Auto-generated method stub
		return factoryDao.getSalePassengerDao().queryByFkid(fkid);
	}


	@Override
	public void savePass(GjSalePassengerEntity pass) throws SqlException {
		
		factoryDao.getSalePassengerDao().passadd(pass);
	}

	@Override
	public int deletepassengrById(String passId) {
		return 0;
		
		
	}


	@Override
	public void updateTicketNum(String id, String eticketNum) throws Exception {
		this.factoryDao.getSalePassengerDao().updateTicketNum(id, eticketNum);
	}


	@Override
	public void updateSaleAndBuyTicketNum(String id, String eticketNum)
			throws Exception {
		this.factoryDao.getSalePassengerDao().updateTicketNum(id, eticketNum);
		this.factoryDao.getGjBuyPassengerDao().updateTicketNum(id, eticketNum);
	}


}
