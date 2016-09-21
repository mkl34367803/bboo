package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.RefundEntity;
import com.smart.oo.from.RefundReportVo;
import com.smart.oo.from.RefundVo;

public interface IRefundService {

	/**
	 * 查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	List<RefundEntity> queryRefunds(RefundEntity entity) throws Exception;

	/**
	 * 保存
	 * @param entity
	 * @throws Exception
	 */
	void saveRefound(RefundEntity entity) throws Exception;
	
	/**
	 * 修改
	 * @param entity
	 * @throws Exception
	 */
	void updateRefound(RefundEntity entity) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	void deleteRefound(String id) throws Exception;
	
	/**
	 * 通过BaseRefund表的id查询
	 * @param fkid
	 * @return
	 * @throws Exception
	 */
	List<RefundEntity> queryByFkrid(String fkrid) throws Exception;
	
	/**
	 * 通过BaseRefund表查询
	 * @param br
	 * @return
	 * @throws Exception
	 */
	List<RefundEntity> queryByBaseRefund(BaseRefundEntity br) throws Exception;

	/**
	 * 下载退票表
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	List<RefundReportVo> downloadRefund(RefundVo vo) throws Exception;

}
