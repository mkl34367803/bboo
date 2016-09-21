package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.Role;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IRoleService;

@Service("RoleServiceImpl")
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<Role> queryRoles(Role role) throws Exception {
		return this.factoryDaoImpl.getRoleDao().queryRoles(role);
	}

	@Override
	public void saveRole(Role role) throws Exception {
		this.factoryDaoImpl.getRoleDao().saveRole(role);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		this.factoryDaoImpl.getRoleDao().updateRole(role);
	}

	@Override
	public Role queryById(Integer id) throws Exception {
		return this.factoryDaoImpl.getRoleDao().queryById(id);
	}

}
