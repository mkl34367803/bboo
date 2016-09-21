package com.smart.oo.service;

import java.util.List;

import com.smart.entity.User;
import com.smart.framework.base.Page;

public interface IUserService {

	/**
	 * 修改用户密码
	 * @throws Exception
	 */
	public String updatePassword(String oldPassword, String newPassword) throws Exception;
	
	/**
	 * 根据ID查询用户
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User queryUserById(Integer id) throws Exception;
	
	/**
	 * 根据条件查询用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> queryUsers(User user, Page page) throws Exception;
	
	/**
	 * 修改字段
	 * @param user
	 * @throws Exception
	 */
	public void updateUserStr(User user) throws Exception;
	
	/**
	 * 修改
	 * @param user
	 * @throws Exception
	 */
	public String updateUser(User user) throws Exception;
	
	/**
	 * 删除用户
	 * @param userId
	 * @throws Exception
	 */
	public void deleteUser(Integer userId) throws Exception;
	
	/**
	 * 保存用户
	 * @throws Exception
	 */
	public String saveUser(User user, String roleIds) throws Exception;
	
	/**
	 * 查询用户
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> queryUsers(User user) throws Exception;
	
}
