package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.BaseRefundEntity;

public interface IBaseRefundDao {

	/**
	 * 查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<BaseRefundEntity> queryList(BaseRefundEntity entity) throws Exception;
	
	/**
	 * 添加
	 * @param entity
	 * @throws Exception
	 */
	void saveEntity(BaseRefundEntity entity) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @throws Exception
	 */
	void updateEntity(BaseRefundEntity entity) throws Exception;
	
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteById(String id) throws Exception;
	
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BaseRefundEntity queryById(String id) throws Exception;
	
	/**
	 * 更新订单状态
	 * @param id
	 * @param orderStatus
	 * @throws Exception
	 */
	void updateOrderStatus(String id, String orderStatus) throws Exception;
	
	/**
	 * 通过id查询航班信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<Object[]> queryFlightVos(String id) throws Exception;
	
	/**
	 * 更新锁定人
	 * @param id
	 * @param locker
	 * @throws Exception
	 */
	void updateLocker(String id, String locker) throws Exception;
}
