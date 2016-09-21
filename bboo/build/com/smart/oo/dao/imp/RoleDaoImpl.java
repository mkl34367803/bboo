package com.smart.oo.dao.imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.Role;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IRoleDao;

@Repository("RoleDaoImpl")
public class RoleDaoImpl extends BaseDAO<Role, Serializable> implements IRoleDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6321048886098003352L;

	@Override
	public List<Role> queryRoles(Role role) throws Exception {
	    StringBuffer hql = new StringBuffer();
	    hql.append("from Role");
	    boolean flag = false;
	    if (null != role.getRoleId()) {
			hql.append(" where roleId="+role.getRoleId());
			flag = true;
		}
	    if (null != role.getName()) {
			if (flag) {
				hql.append(" and name='"+role.getName()+"'");
			} else {
				hql.append(" where name='"+role.getName()+"'");
				flag = true;
			}
		}
	    if (null != role.getCode()) {
			if (flag) {
				hql.append(" and code='"+role.getCode()+"'");
			} else {
				hql.append(" where code='"+role.getCode()+"'");
				flag = true;
			}
		}
	    if (null != role.getDescription()) {
			if (flag) {
				hql.append(" and description='"+role.getDescription()+"'");
			} else {
				hql.append(" where description='"+role.getDescription()+"'");
				flag = true;
			}
		}
		return this.find(hql.toString());
	}

	@Override
	public void saveRole(Role role) throws Exception {
		this.save(role);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		StringBuffer hql = new StringBuffer();
	    hql.append("update Role");
	    hql.append(" set name='"+role.getName()+"'");
	    hql.append(" , code='"+role.getCode()+"'");
	    hql.append(" , description='"+role.getDescription()+"'");
    	hql.append(" where roleId="+role.getRoleId());
		this.find(hql.toString());
	}

	@Override
	public Role queryById(Integer id) throws Exception {
		return this.findUnique("from Role where roleId="+id);
	}

}
