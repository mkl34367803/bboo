package com.smart.oo.dao.imp;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.YuanQiaoEntity;
import com.smart.comm.utils.ProduceHqlUtil;
import com.smart.framework.base.BaseDAO;
@Repository("YuanQiaoDaoImpl")
public class YuanQiaoDaoImpl extends BaseDAO<YuanQiaoEntity, String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void saveEntity(YuanQiaoEntity entity) throws Exception{
		this.save(entity);
	}
	
	public void updateById(YuanQiaoEntity entity) throws Exception{
		String hql=ProduceHqlUtil.getUpdateByIdHql(entity);
		this.executeHql(hql);
	}
}
