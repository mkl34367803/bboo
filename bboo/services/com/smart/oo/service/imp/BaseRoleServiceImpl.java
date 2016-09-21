package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.Role;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseRoleService;

@Service("BBOOBaseRoleServiceImpl")
public class BaseRoleServiceImpl implements IBaseRoleService {
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<Role> getRoleList() throws Exception {
		return this.factoryDaoImpl.getBaseRoleDaoImpl().getRoleList();
	}

	@Override
	public void saveRole(Role role) throws Exception {
		this.factoryDaoImpl.getBaseRoleDaoImpl().saveRole(role);
	}

}
