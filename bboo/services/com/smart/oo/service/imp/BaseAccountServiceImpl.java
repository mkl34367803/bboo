package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBaseAccountDao;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseAccountService;
@Service("BaseAccountServiceImpl")
public class BaseAccountServiceImpl implements IBaseAccountService {
	@Autowired
	private FactoryDaoImpl factoryDao;
	@Override
	public List<BaseAccountEntity> queryBaseAccount(Page page,
			BaseAccountEntity baseAccountEntity) throws Exception {
		return factoryDao.getBaseAccountDao().queryBaseAccount(page, baseAccountEntity);
	}

	@Override
	public Integer queryCountAccount(BaseAccountEntity baseAccountEntity)
			throws Exception {
		return factoryDao.getBaseAccountDao().queryCountAccount(baseAccountEntity);
	}

	@Override
	public Integer saveAndUpdateBaseAccount(BaseAccountEntity baseAccountEntity)
			throws Exception {
		return factoryDao.getBaseAccountDao().saveAndUpdateBaseAccount(baseAccountEntity);
	}

	@Override
	public List<BaseAccountEntity> queryBaseAccountByMno(String mno)
			throws Exception {
		return factoryDao.getBaseAccountDao().queryBaseAccountByMno(mno);
	}

	@Override
	public BaseAccountEntity queryBaseAccountByID(Integer id) throws Exception {
		return factoryDao.getBaseAccountDao().queryBaseAccountByID(id);
	}

	@Override
	public List<BaseAccountEntity> queryBaseAccounts() throws Exception {
		// TODO Auto-generated method stub
		return factoryDao.getBaseAccountDao().queryBaseAccounts();
	}

}
