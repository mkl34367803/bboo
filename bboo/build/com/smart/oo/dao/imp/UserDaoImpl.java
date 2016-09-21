package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.User;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IUserDao;
import com.smart.utils.StringUtils;

@Repository("UserDaoImpl")
public class UserDaoImpl extends BaseDAO<User, Integer> implements IUserDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2060820931856451118L;

	@Override
	public void updatePassword(Integer userId, String password) throws Exception {
		List<Object> params = new ArrayList<Object>();
		String hql = "update User set password=? where userId=?";
		params.add(password);
		params.add(userId);
		this.executeHql(hql, params.toArray());
	}

	@Override
	public User queryUserById(Integer id) throws Exception {
		return this.findUnique("from User where id='"+id+"'");
	}

	@Override
	public List<User> queryUsers(User user, Page page) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from User");
		boolean flag = false;
		if (null != user.getUserId()) {
			hql.append(" where id="+user.getUserId());
			flag = true;
		}
		if (null != user.getName()) {
			if (flag) {
				hql.append(" and name='" + user.getName() + "'");
			} else {
				hql.append(" where name='" + user.getName() + "'");
			}
			flag = true;
		}
		if (null != user.getLoginName()) {
			if (flag) {
				hql.append(" and loginName='" + user.getLoginName() + "'");
			} else {
				hql.append(" where loginName='" + user.getLoginName() + "'");
			}
			flag = true;
		}
		if (null != user.getFkmercid()) {
			if (flag) {
				hql.append(" and fkmercid='" + user.getFkmercid() + "'");
			} else {
				hql.append(" where fkmercid='" + user.getFkmercid() + "'");
			}
			flag = true;
		}
		return this.find(hql.toString(), page);
	}

	@Override
	public void updateUserStr(User user) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("update User");
		hql.append(" set deleted='" + user.getDeleted()+"'");
		if (null != user.getName()) {
			hql.append(", name='"+user.getName()+"'");
		}
		if (null != user.getLoginName()) {
			hql.append(", loginName='"+user.getLoginName()+"'");
		}
		if (StringUtils.isNotEmpty(user.getPassword())) {
			hql.append(", password='"+user.getPassword()+"'");
		}
		if (StringUtils.isNotEmpty(user.getSj())) {
			hql.append(", sj='"+user.getSj()+"'");
		} else {
			hql.append(", sj = ''");
		}
		if (null != user.getSsbm()) {
			hql.append(", ssbm='"+user.getSsbm()+"'");
		}
		if (null != user.getSszw()) {
			hql.append(", sszw='"+user.getSszw()+"'");
		}
		if (null != user.getYwm()) {
			hql.append(", ywm='"+user.getYwm()+"'");
		}
		if (null != user.getFkmercid()) {
			hql.append(", fkmercid='"+user.getFkmercid()+"'");
		}
		hql.append(" where userId="+user.getUserId());
		this.executeHql(hql.toString());
	}
	
	@Override
	public void updateUser(User user) throws Exception {
		this.update(user);
	}

	@Override
	public void deleteUser(Integer userId) throws Exception {
		this.executeHql("delete from User where userId=?", new Object[]{userId});
	}

	@Override
	public void saveUser(User user) throws Exception {
		this.save(user);
	}

	@Override
	public List<User> queryUsers(User user) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from User");
		boolean flag = false;
		if (null != user.getUserId()) {
			hql.append(" where id="+user.getUserId());
			flag = true;
		}
		if (null != user.getName()) {
			if (flag) {
				hql.append(" and name='" + user.getName() + "'");
			} else {
				hql.append(" where name='" + user.getName() + "'");
			}
			flag = true;
		}
		if (null != user.getLoginName()) {
			if (flag) {
				hql.append(" and loginName='" + user.getLoginName() + "'");
			} else {
				hql.append(" where loginName='" + user.getLoginName() + "'");
			}
			flag = true;
		}
		if (null != user.getFkmercid()) {
			if (flag) {
				hql.append(" and fkmercid='" + user.getFkmercid() + "'");
			} else {
				hql.append(" where fkmercid='" + user.getFkmercid() + "'");
			}
			flag = true;
		}
		return this.find(hql.toString());
	}

	@Override
	public void deleteRoleUser(Integer userId) throws Exception {
		this.executeSql("delete t_base_role_user where user_id=?", new Object[]{userId});
	}

}
