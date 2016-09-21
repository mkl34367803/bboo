package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.BookRuleEntity;
import com.smart.framework.base.Page;

public interface IBookRuleService {

	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<BookRuleEntity> queryByPage(BookRuleEntity entity, Page page) throws Exception;
	
	/**
	 * 添加
	 * @param entity
	 * @throws Exception
	 */
	void saveBookRule(BookRuleEntity entity) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @throws Exception
	 */
	void updateBookRule(BookRuleEntity entity) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteBookRule(Integer id) throws Exception;
	/**
	 * 通过缓存来查询bookentity
	 * @param cacheKey 缓存名字
	 * @param iscache
	 * @return
	 * @throws Exception
	 */
	public List<BookRuleEntity> getAll(String cacheKey, boolean iscache) throws Exception;
}
