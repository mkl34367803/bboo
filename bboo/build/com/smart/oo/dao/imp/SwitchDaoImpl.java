package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.SwitchEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.ISwitchDao;

@Repository("SwitchDaoImpl")
public class SwitchDaoImpl extends BaseDAO<SwitchEntity, Integer> implements
		ISwitchDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<SwitchEntity> queryByMkey(SwitchEntity switchEntity)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from SwitchEntity where 1=1");
		if (switchEntity.getId() != null) {
			hql.append(" and id=" + switchEntity.getId());
		}
		if (switchEntity.getMkey() != null) {
			hql.append(" and mkey='" + switchEntity.getMkey() + "'");
		}
		if (switchEntity.getMno() != null) {
			hql.append(" and mno='" + switchEntity.getMno() + "'");
		}
		if (switchEntity.getOnoff() != null) {
			hql.append(" and onoff='" + switchEntity.getOnoff() + "'");
		}
		List<SwitchEntity> switchEntities = this.find(hql.toString());
		return switchEntities;
	}
	
	@Override
	public void saveSwitch(SwitchEntity entity) throws SqlException {
		this.save(entity);
	}
	
	@Override
	public void updateSwitch(SwitchEntity entity) throws SqlException {
		StringBuffer hql = new StringBuffer();
		hql.append(" update SwitchEntity");
		hql.append(" set mno='"+entity.getMno()+"'");
		hql.append(" , onoff='"+entity.getOnoff()+"'");
		hql.append(" , validity='"+entity.getValidity()+"'");
		hql.append(" , mkey='"+entity.getMkey()+"'");
		hql.append(" , describe='"+entity.getDescribe()+"'");
		hql.append(" where id="+entity.getId());
		this.executeHql(hql.toString());
	}
	
	@Override
	public void deleteSwitch(Integer id) throws SqlException {
		this.delete(id);
	}
	
	

	public static void main(String[] args) {
		System.out.println(new SwitchEntity().getId()); // 默认为null
	}

	@Override
	public List<SwitchEntity> queryByPage(SwitchEntity switchEntity, Page page)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from SwitchEntity where 1=1");
		if (switchEntity.getId() != null) {
			hql.append(" and id=" + switchEntity.getId());
		}
		if (switchEntity.getMkey() != null) {
			hql.append(" and mkey='" + switchEntity.getMkey() + "'");
		}
		if (switchEntity.getMno() != null) {
			hql.append(" and mno='" + switchEntity.getMno() + "'");
		}
		if (switchEntity.getOnoff() != null) {
			hql.append(" and onoff='" + switchEntity.getOnoff() + "'");
		}
		return this.find(hql.toString(), page);
	}

}
