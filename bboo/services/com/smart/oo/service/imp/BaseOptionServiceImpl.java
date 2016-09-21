package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.Option;
import com.smart.framework.base.Page;
import com.smart.oo.cache.CacheService;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseOptionService;

@Service("BaseOptionServiceImpl")
public class BaseOptionServiceImpl extends CacheService<Option> implements IBaseOptionService {
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<Option> queryOptionList(Option option) throws Exception {
		// TODO Auto-generated method stub
		return factoryDaoImpl.getBaseOptionDao().queryOptionList(option);
	}

	@Override
	public void updateOption(Option option) throws Exception {
		factoryDaoImpl.getBaseOptionDao().updateOption(option);
	}

	@Override
	public void saveOption(Option option) throws Exception {
		factoryDaoImpl.getBaseOptionDao().saveOption(option);
	}

	@Override
	public void deleteOptionById(Integer id) throws Exception {
		factoryDaoImpl.getBaseOptionDao().deleteOptionById(id);
	}

	@Override
	public List<Option> queryOptionsByPage(Page page, Option option)
			throws Exception {
		return this.factoryDaoImpl.getBaseOptionDao().queryOptionsByPage(page, option);
	}

}
