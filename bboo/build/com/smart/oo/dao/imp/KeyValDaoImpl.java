package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.KeyValEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IKeyValDao;

@Repository("GKeyValDaoImpl")
public class KeyValDaoImpl extends BaseDAO<KeyValEntity, Integer> implements
		IKeyValDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7742456930395952452L;

	public KeyValDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<KeyValEntity> findDataList() throws Exception {
		// TODO Auto-generated method stub
		return this.find("from KeyValEntity where 1=1 ");
	}

	@Override
	public void saveData(KeyValEntity v) throws Exception {
		this.save(v);
	}

	@Override
	public void updateData(KeyValEntity v) throws Exception {
		this.executeHql("update KeyValEntity set k='"+v.getK()+"' , v='"+v.getV()+"' , describe='"+v.getDescribe()+"' where id='"+v.getId()+"'");
	}

	@Override
	public void deleteData(String id) throws Exception {
		this.executeHql("delete KeyValEntity where id='"+id+"'");
	}

	@Override
	public KeyValEntity queryById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return this.findUnique("from KeyValEntity where id='" + id + "'");
	}

	@Override
	public List<KeyValEntity> queryKeyValues(Page page,
			KeyValEntity keyValEntity) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from KeyValEntity");
		boolean flag = false;
		if(null != keyValEntity.getId()){
			hql.append(" where id='"+keyValEntity.getId()+"'");
			flag = true;
		}
		if(null != keyValEntity.getK()){
			if(flag) {
				hql.append(" and k='"+keyValEntity.getK()+"'");
			} else {
				hql.append(" where k='"+keyValEntity.getK()+"'");
				flag = true;
			}
		}
		if(null != keyValEntity.getV()){
			if(flag) {
				hql.append(" and v='"+keyValEntity.getV()+"'");
			} else {
				hql.append(" where v='"+keyValEntity.getV()+"'");
				flag = true;
			}
		}
		if(null != keyValEntity.getDescribe()){
			if(flag) {
				hql.append(" and describe='"+keyValEntity.getDescribe()+"'");
			} else {
				hql.append(" where describe='"+keyValEntity.getDescribe()+"'");
				flag = true;
			}
		}
		return this.find(hql.toString(), page);
	}

	@Override
	public KeyValEntity queryByKey(String key) throws Exception {
		return this.findUnique("from KeyValEntity where k='"+key+"'");
	}

}
