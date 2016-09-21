package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.SwitchEntity;
import com.smart.framework.base.Page;

public interface ISwitchDao {
	/**
	 * 通过条件查询Switch实体,单条件查询，需要什么查询条件，就往属性里面赋值，
	 * 不需要的条件不赋值
	 * @return
	 * @throws Exception
	 */
	public List<SwitchEntity>queryByMkey(SwitchEntity switchEntity) throws Exception;
	
	/**
	 * 保存
	 * @param switchEntity
	 * @throws Exception
	 */
	void saveSwitch(SwitchEntity switchEntity) throws Exception;
	
	/**
	 * 修改
	 * @param switchEntity
	 * @throws Exception
	 */
	void updateSwitch(SwitchEntity switchEntity) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteSwitch(Integer id) throws Exception;
	
	/**
	 * 分页查询
	 * @param switchEntity
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<SwitchEntity>queryByPage(SwitchEntity switchEntity, Page page) throws Exception;
}
