package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.Select;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseSelectService;

@Service("BaseSelectServiceImpl")
public class BaseSelectServiceImpl implements IBaseSelectService {

	@Autowired
	private FactoryDaoImpl factoryDaoImpl;
	
	@Override
	public List<Select> querySelects() throws Exception {
		return this.factoryDaoImpl.getBaseSelectDao().querySelects();
	}

	@Override
	public Select querySelectById(Integer id) throws Exception {
		return this.factoryDaoImpl.getBaseSelectDao().querySelectById(id);
	}

	@Override
	public void updateSelect(Select select) throws Exception {
		this.factoryDaoImpl.getBaseSelectDao().updateSelect(select);
	}

	@Override
	public void saveSelect(Select select) throws Exception {
		this.factoryDaoImpl.getBaseSelectDao().saveSelect(select);
	}
	
	@Override
	public void deleteSelectById(Integer selectId) throws Exception {
		this.factoryDaoImpl.getBaseSelectDao().deleteSelectById(selectId);
	}

	@Override
	public List<Select> querySelectsByPage(Page page, Select select)
			throws Exception {
		return this.factoryDaoImpl.getBaseSelectDao().querySelectsByPage(page, select);
	}

	@Override
	public List<Select> querySelects(Select select) throws Exception {
		return this.factoryDaoImpl.getBaseSelectDao().querySelects(select);
	}

}
