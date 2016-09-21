package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.CabinRuleEntity;
import com.smart.framework.base.Page;

public interface ICabinRuleDao {

	/**
	 * 分页查询
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<CabinRuleEntity> queryByPage(CabinRuleEntity entity, Page page) throws Exception;
	
	/**
	 * 查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<CabinRuleEntity> queryList(CabinRuleEntity entity) throws Exception;
	
	/**
	 * 添加
	 * @param entity
	 * @throws Exception
	 */
	void saveEntity(CabinRuleEntity entity) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @throws Exception
	 */
	void updateEntity(CabinRuleEntity entity) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteById(Integer id) throws Exception;
	
}
