package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.BaseContactsEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBaseContactsDao;
import com.smart.utils.StringUtils;

@Repository("BBOOBaseContactsDaoImpl")
public class BaseContactsDaoImpl extends BaseDAO<BaseContactsEntity, Integer> implements IBaseContactsDao {

	private static final long serialVersionUID = 1632282885644125520L;

	@Override
	public List<BaseContactsEntity> queryContacts(BaseContactsEntity entity)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from BaseContactsEntity where 1=1 ");
		if (StringUtils.isNotEmpty(entity.getLinkman())) {
			sb.append(" and linkman='"+entity.getLinkman()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getLinktel())) {
			sb.append(" and linktel='"+entity.getLinktel()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getPhone())) {
			sb.append(" and phone='"+entity.getPhone()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getEmail())) {
			sb.append(" and email='"+entity.getEmail()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getAddress())) {
			sb.append(" and address='"+entity.getAddress()+"'");
		}
		sb.append(" and mno='"+entity.getMno()+"'");
		return this.find(sb.toString());
	}

	@Override
	public List<BaseContactsEntity> queryByPage(BaseContactsEntity entity, Page page)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from BaseContactsEntity where 1=1 ");
		if (StringUtils.isNotEmpty(entity.getLinkman())) {
			sb.append(" and linkman='"+entity.getLinkman()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getLinktel())) {
			sb.append(" and linktel='"+entity.getLinktel()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getPhone())) {
			sb.append(" and phone='"+entity.getPhone()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getEmail())) {
			sb.append(" and email='"+entity.getEmail()+"'");
		}
		if (StringUtils.isNotEmpty(entity.getAddress())) {
			sb.append(" and address='"+entity.getAddress()+"'");
		}
		sb.append(" and mno='"+entity.getMno()+"'");
		return this.find(sb.toString(), page);
	}

	@Override
	public void saveContact(BaseContactsEntity entity) throws Exception {
		this.save(entity);
	}

	@Override
	public void deleteContact(Integer id) throws Exception {
		this.delete(id);
	}

	@Override
	public void updateContact(BaseContactsEntity entity) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update BaseContactsEntity ");
		sb.append(" set linkman='"+entity.getLinkman()+"'");
		sb.append(" , linktel='"+entity.getLinktel()+"'");
		sb.append(" , phone='"+entity.getPhone()+"'");
		sb.append(" , email='"+entity.getEmail()+"'");
		sb.append(" , address='"+entity.getAddress()+"'");
		sb.append(" where id="+entity.getId());
		sb.append(" and mno='"+entity.getMno()+"'");
		this.executeHql(sb.toString());
	}

}
