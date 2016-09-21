package com.smart.oo.dao;

import java.util.List;

import com.smart.entity.Role;

public interface IRoleDao {

	/**
	 * 查询所有
	 * @param role
	 * @return
	 * @throws Exception
	 */
	List<Role> queryRoles(Role role) throws Exception;
	
	/**
	 * 保存
	 * @param role
	 * @throws Exception
	 */
	void saveRole(Role role) throws Exception;
	
	/**
	 * 修改
	 * @param role
	 * @throws Exception
	 */
	void updateRole(Role role) throws Exception;
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Role queryById(Integer id) throws Exception;
}
