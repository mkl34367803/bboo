package com.smart.oo.service;

import java.util.List;

import com.smart.entity.Role;

public interface IBaseRoleService {

	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	List<Role> getRoleList() throws Exception;
	
	/**
	 * 保存
	 * @param role
	 * @throws Exception
	 */
	void saveRole(Role role) throws Exception;
}
