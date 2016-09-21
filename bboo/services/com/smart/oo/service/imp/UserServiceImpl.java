package com.smart.oo.service.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.Role;
import com.smart.entity.User;
import com.smart.framework.base.Page;
import com.smart.framework.security.SecurityContext;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IUserService;
import com.smart.utils.SecurityUtils;

@Transactional
@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public String updatePassword(String oldPassword, String newPassword)
			throws Exception {
		User user=SecurityContext.getUser();
		if(user!=null){
			Integer userId = user.getUserId();
			String pwd = this.factoryDao.getUserDao().queryUserById(userId).getPassword();
			String oldPwdMd5 = SecurityUtils.getMD5(oldPassword);
			if(pwd.equals(oldPwdMd5)){
				String newPasswordMD5 = SecurityUtils.getMD5(newPassword);
				this.factoryDao.getUserDao().updatePassword(userId, newPasswordMD5);
			} else {
				return "oldPassError";
			}
		} else {
			return "userNull";
		}
		return null;
	}

	@Override
	public User queryUserById(Integer id) throws Exception {
		return this.factoryDao.getUserDao().queryUserById(id);
	}

	@Override
	public List<User> queryUsers(User user, Page page) throws Exception {
		return this.factoryDao.getUserDao().queryUsers(user, page);
	}

	@Override
	public void updateUserStr(User user) throws Exception {
		this.factoryDao.getUserDao().updateUserStr(user);
	}
	
	@Override
	public String updateUser(User newUser) throws Exception {
		User user = queryUserById(newUser.getUserId());
		// 判断用户名是否唯一
		if (!newUser.getLoginName().equals(user.getLoginName())) {
			User qUser = new User();
			user.setLoginName(newUser.getLoginName());
			List<User> queryUsers = queryUsers(qUser);
			if (queryUsers.size() > 0) {
				return "该登录名重复";
			}
		}
		
		/*插入roleId*/
		/*if (StringUtilsc.isNotEmpty(roleIds)) {
			String[] roleIdStrs = roleIds.split(",");
			if (!StringUtilsc.isNumeric(roleIdStrs.toString())) {
				return "roleId为数字和\",\"";
			}
			Set<Role> roles = new HashSet<Role>();
			for (String roleId : roleIdStrs) {
				Role role = this.factoryDao.getRoleDao().queryById(Integer.parseInt(roleId));
				if (null != role) {
					roles.add(role);
				} else {
					return "roleId不存在";
				}
			}
			newUser.setRoles(roles);
		} else {
			newUser.setRoles(null);
		}*/
		this.factoryDao.getUserDao().updateUserStr(newUser);
		return null;
	}

	@Override
	public void deleteUser(Integer userId) throws Exception {
		/*User user = this.factoryDao.getUserDao().queryUserById(userId);
		if (null != user && null != user.getRoles()) {
			user.setRoles(null);
			this.factoryDao.getUserDao().updateUser(user);
		}*/
		this.factoryDao.getUserDao().deleteRoleUser(userId);
		this.factoryDao.getUserDao().deleteUser(userId);
	}

	@Override
	public String saveUser(User user, String roleIds) throws Exception {
		// 检查登录名是否唯一
		User qUser = new User();
		qUser.setLoginName(user.getLoginName());
		List<User> queryUsers = this.queryUsers(qUser);
		if (queryUsers.size() > 0) {
			return "该登录名重复";
		}
		
		/*插入roleId*/
		if (StringUtilsc.isNotEmpty(roleIds)) {
			String[] roleIdStrs = roleIds.split(",");
			if (!StringUtilsc.isNumeric(roleIdStrs.toString())) {
				return "roleId为数字和\",\"";
			}
			Set<Role> roles = new HashSet<Role>();
			for (String roleId : roleIdStrs) {
				Role role = this.factoryDao.getRoleDao().queryById(Integer.parseInt(roleId));
				if (null != role) {
					roles.add(role);
				} else {
					return "roleId不存在: "+roleId;
				}
			}
			user.setRoles(roles);
		}
		
		User curUser = SecurityContext.getUser();
		Integer curFkmercid = curUser.getFkmercid();
		user.setFkmercid(curFkmercid);
		user.setMert(curUser.getMert());
		this.factoryDao.getUserDao().saveUser(user);
		return null;
	}

	@Override
	public List<User> queryUsers(User user) throws Exception {
		return this.factoryDao.getUserDao().queryUsers(user);
	}

}