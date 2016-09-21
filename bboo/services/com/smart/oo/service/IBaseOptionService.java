package com.smart.oo.service;

import java.util.List;

import com.smart.entity.Option;
import com.smart.framework.base.Page;

public interface IBaseOptionService {

	/**
	 * 查找所有Option
	 * @param option
	 * @return
	 * @throws Exception
	 */
	List<Option> queryOptionList(Option option) throws Exception;
	
	/**
	 * 更新
	 * @param option
	 * @return 
	 * @throws Exception
	 */
	void updateOption(Option option) throws Exception;
	
	/**
	 * 保存
	 * @param option
	 * @throws Exception
	 */
	void saveOption(Option option) throws Exception;
	
	/**
	 * 删除
	 * @param option
	 * @throws Exception
	 */
	void deleteOptionById(Integer id) throws Exception;
	
	/**
	 * 分页查找
	 * @param page
	 * @param option
	 * @return
	 * @throws Exception
	 */
	List<Option> queryOptionsByPage(Page page, Option option) throws Exception;
	
}
