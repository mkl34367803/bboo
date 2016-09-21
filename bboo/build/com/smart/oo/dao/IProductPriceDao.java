package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.ProductPriceEntity;

public interface IProductPriceDao {
	/**
	 * 通过fkpid获取政策信息
	 * @param fkpids
	 * @return
	 * @throws Exception
	 */
	public List<ProductPriceEntity> getProductPriceByFkpids(String[] fkpids) throws Exception;
	/**
	 * 通过id获取政策信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductPriceEntity getProductPriceById(String id) throws Exception;
	/**
	 * 通过订单号查询价格
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<ProductPriceEntity> getProductPriceByOrderId(String orderId) throws Exception; 
	/**
	 * 通过订单号查询价格
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public List<ProductPriceEntity> getProductPriceByOrderIdBySQL(String orderId) throws Exception;
}
