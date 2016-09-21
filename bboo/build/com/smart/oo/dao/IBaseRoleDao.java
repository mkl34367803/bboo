package com.smart.oo.dao;

import java.util.List;

import com.smart.entity.Role;

public interface IBaseRoleDao {

	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	List<Role> getRoleList() throws Exception;
	
	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	List<Role> queryRoleList(Role role) throws Exception;
	
	/**
	 * 保存
	 * @param role
	 * @throws Exception
	 */
	void saveRole(Role role) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteRole(Integer id) throws Exception;
	
	/**
	 * 修改
	 * @param role
	 * @throws Exception
	 */
	void updateRole(Role role) throws Exception;
	
}
