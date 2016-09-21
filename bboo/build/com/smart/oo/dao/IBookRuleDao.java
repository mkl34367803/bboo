package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.BookRuleEntity;
import com.smart.framework.base.Page;

public interface IBookRuleDao {
	
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
	 * 获取所有的订票规则
	 * @return
	 * @throws Exception
	 */
	public List<BookRuleEntity> getAll() throws Exception;

}
