package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.KeyValEntity;
import com.smart.framework.base.Page;

public interface IKeyValService {

	List<KeyValEntity> findDataList() throws Exception;

	List<KeyValEntity> findDataList(String cacheKey,
			boolean iscache) throws Exception;
	
	/**
	 * 保存
	 * @param v
	 * @throws Exception
	 */
	void saveData(KeyValEntity v) throws Exception;
	
	/**
	 * 修改
	 * @param v
	 * @throws Exception
	 */
	void updateData(KeyValEntity v) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteData(String id) throws Exception;
	
	/**
	 * 查找
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	KeyValEntity queryById(Integer id) throws Exception;
	
	/**
	 * 分页查找
	 * @param page
	 * @param keyValEntity
	 * @return
	 * @throws Exception
	 */
	List<KeyValEntity> queryKeyValues(Page page, KeyValEntity keyValEntity) throws Exception;
	/**
	 * 通过key查询keyvalue实体，前提是key不能再数据库中重复。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public KeyValEntity queryByKey(String key) throws Exception;
}
