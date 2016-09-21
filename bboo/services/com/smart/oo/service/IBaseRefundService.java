package com.smart.oo.service;

import java.util.List;
import java.util.Set;

import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.RefundEntity;

public interface IBaseRefundService {

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
	 * 保存BaseRefund和Refund
	 * @param baseRefundEntity
	 * @param refundEntity
	 * @throws Exception
	 */
	void saveRefundAndBaseRefund(BaseRefundEntity baseRefundEntity, Set<RefundEntity> refundList, String basefundId) throws Exception;
	
	/**
	 * 通过id查询并锁定
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
	 * 更新锁定人
	 * @param id
	 * @param locker
	 * @throws Exception
	 */
	void updateLocker(String id, String locker) throws Exception;
}
