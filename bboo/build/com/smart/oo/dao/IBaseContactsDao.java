package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.BaseContactsEntity;
import com.smart.framework.base.Page;

public interface IBaseContactsDao {

	/**
	 * 查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<BaseContactsEntity> queryContacts(BaseContactsEntity entity) throws Exception;
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<BaseContactsEntity> queryByPage(BaseContactsEntity entity, Page page) throws Exception;
	
	/**
	 * 保存
	 * @param entity
	 * @throws Exception
	 */
	void saveContact(BaseContactsEntity entity) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteContact(Integer id) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @throws Exception
	 */
	void updateContact(BaseContactsEntity entity) throws Exception;
}
