package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.Role;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IBaseRoleDao;
import com.smart.utils.StringUtils;

@Repository("BBOOBaseRoleDaoImpl")
public class BaseRoleDaoImpl extends BaseDAO<Role, Integer> implements IBaseRoleDao {

	private static final long serialVersionUID = -3183329312467821254L;

	@Override
	public List<Role> getRoleList() throws Exception {
		return this.find("from Role where 1=1 ");
	}

	@Override
	public void saveRole(Role role) throws Exception {
		this.save(role);
	}

	@Override
	public List<Role> queryRoleList(Role role) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from Role where 1=1 ");
		if (StringUtils.isNotEmpty(role.getName())) {
			sb.append(" and name='"+role.getName()+"'");
		}
		if (StringUtils.isNotEmpty(role.getCode())) {
			sb.append(" and code='"+role.getCode()+"'");
		}
		return this.find(sb.toString());
	}

	@Override
	public void deleteRole(Integer id) throws Exception {
		this.delete(id);
	}

	@Override
	public void updateRole(Role role) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from Role ");
		boolean temp = false;
		if (StringUtils.isNotEmpty(role.getName())) {
			sb.append(" set name='"+role.getName()+"'");
			temp = true;
		}
		if (StringUtils.isNotEmpty(role.getCode())) {
			if (temp) {
				sb.append(", code='"+role.getCode()+"'");
			} else {
				sb.append(" set code='"+role.getCode()+"'");
			}
		}
		this.executeHql(sb.toString());
	}

}
