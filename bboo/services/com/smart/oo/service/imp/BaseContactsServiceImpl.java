package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseContactsEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseContactsService;

@Service("BBOOBaseContactsServiceImpl")
public class BaseContactsServiceImpl implements IBaseContactsService {
	
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public List<BaseContactsEntity> queryContacts(BaseContactsEntity entity)
			throws Exception {
		return this.factoryDao.getBaseContactsDao().queryContacts(entity);
	}

	@Override
	public List<BaseContactsEntity> queryByPage(BaseContactsEntity entity,
			Page page) throws Exception {
		return this.factoryDao.getBaseContactsDao().queryByPage(entity, page);
	}

	@Override
	public void saveContact(BaseContactsEntity entity) throws Exception {
		this.factoryDao.getBaseContactsDao().saveContact(entity);
	}

	@Override
	public void deleteContact(Integer id) throws Exception {
		this.factoryDao.getBaseContactsDao().deleteContact(id);
	}

	@Override
	public void updateContact(BaseContactsEntity entity) throws Exception {
		this.factoryDao.getBaseContactsDao().updateContact(entity);
	}

}
