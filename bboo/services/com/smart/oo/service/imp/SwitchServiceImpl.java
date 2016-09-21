package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.SwitchEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.ISwitchService;
@Service("SwitchServiceImpl")
public class SwitchServiceImpl implements ISwitchService{

	@Autowired
	private FactoryDaoImpl factoryDaoImpl;
	@Override
	public List<SwitchEntity> queryByMkey(SwitchEntity switchEntity) throws Exception {
		return factoryDaoImpl.getSwitchDao().queryByMkey(switchEntity);
	}
	@Override
	public void saveSwitch(SwitchEntity switchEntity) throws Exception {
		this.factoryDaoImpl.getSwitchDao().saveSwitch(switchEntity);
	}
	@Override
	public void updateSwitch(SwitchEntity switchEntity) throws Exception {
		this.factoryDaoImpl.getSwitchDao().updateSwitch(switchEntity);
	}
	@Override
	public void deleteSwitch(Integer id) throws Exception {
		this.factoryDaoImpl.getSwitchDao().deleteSwitch(id);
	}
	@Override
	public List<SwitchEntity> queryByPage(SwitchEntity switchEntity, Page page)
			throws Exception {
		return this.factoryDaoImpl.getSwitchDao().queryByPage(switchEntity, page);
	}


}
