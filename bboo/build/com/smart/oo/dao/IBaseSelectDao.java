package com.smart.oo.dao;

import java.util.List;

import com.smart.entity.Select;
import com.smart.framework.base.Page;

public interface IBaseSelectDao {

	/**
	 * 查询所有Select
	 * @return
	 */
	List<Select> querySelects() throws Exception;
	
	/**
	 * 通过ID查找Select
	 * @param id
	 * @return
	 */
	Select querySelectById(Integer id) throws Exception;
	
	/**
	 * 更新Select
	 * @param select
	 */
	void updateSelect(Select select) throws Exception;
	
	/**
	 * 保存Select
	 * @param select
	 */
	void saveSelect(Select select) throws Exception;
	
	/**
	 * 删除Select
	 * @param selectId
	 * @throws Exception
	 */
	void deleteSelectById(Integer selectId) throws Exception;
	
	/**
	 * 分页查找
	 * @param page
	 * @param option
	 * @return
	 * @throws Exception
	 */
	List<Select> querySelectsByPage(Page page, Select select) throws Exception;
	
	/**
	 * 查找
	 * @param option
	 * @return
	 * @throws Exception
	 */
	List<Select> querySelects(Select select) throws Exception;
}
