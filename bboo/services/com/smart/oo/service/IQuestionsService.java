package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.QuestionsEntity;
import com.smart.framework.base.Page;

public interface IQuestionsService {

	/**
	 * 分页查询
	 * @param entity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<QuestionsEntity> queryByPage(QuestionsEntity entity, Page page) throws Exception;
	
	/**
	 * 查询数量
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	Integer countNum(QuestionsEntity entity) throws Exception;
	
	/**
	 * 查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<QuestionsEntity> queryList(QuestionsEntity entity) throws Exception;
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	QuestionsEntity queryById(Integer id) throws Exception;
	
	/**
	 * 保存
	 * @param entity
	 * @throws Exception
	 */
	void saveEntity(QuestionsEntity entity) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @throws Exception
	 */
	void updateEntity(QuestionsEntity entity) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteEntity(Integer id) throws Exception;
	
}
