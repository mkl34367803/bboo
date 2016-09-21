package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.CabinRuleEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.ICabinRuleService;

@Service("BBOOCabinRuleServiceImpl")
public class CabinRuleServiceImpl implements ICabinRuleService {
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<CabinRuleEntity> queryByPage(CabinRuleEntity entity, Page page)
			throws Exception {
		return this.factoryDaoImpl.getCabinRuleDao().queryByPage(entity, page);
	}

	@Override
	public List<CabinRuleEntity> queryList(CabinRuleEntity entity)
			throws Exception {
		return this.factoryDaoImpl.getCabinRuleDao().queryList(entity);
	}

	@Override
	public void saveEntity(CabinRuleEntity entity) throws Exception {
		this.factoryDaoImpl.getCabinRuleDao().saveEntity(entity);
	}

	@Override
	public void updateEntity(CabinRuleEntity entity) throws Exception {
		this.factoryDaoImpl.getCabinRuleDao().updateEntity(entity);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		this.factoryDaoImpl.getCabinRuleDao().deleteById(id);
	}

}
