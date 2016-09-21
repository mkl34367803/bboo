package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.CabinMapEntity;
import com.smart.framework.base.Page;

public interface ICabinMapDao {

	/**
	 * 添加
	 * @param e
	 * @return
	 * @throws Exception
	 */
	Integer saveData(CabinMapEntity e) throws Exception;

	/**
	 * 查询
	 * @param e
	 * @return
	 * @throws Exception
	 */
	List<CabinMapEntity> findList(CabinMapEntity e) throws Exception;
	
	/**
	 * 分页查询
	 * @param c
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<CabinMapEntity> findByPage(CabinMapEntity c, Page page) throws Exception;
	
	/**
	 * 通过ID删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	void deleteById(Integer id) throws Exception;
	
	/**
	 * 修改
	 * @param c
	 * @return
	 * @throws Exception
	 */
	void updateCabinMap(CabinMapEntity c) throws Exception;
}
