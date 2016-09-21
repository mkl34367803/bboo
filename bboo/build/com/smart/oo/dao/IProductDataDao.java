package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.ProductDataEntity;

public interface IProductDataDao {
	/**
	 * 级联保存运价信息。（所谓的政策信息）
	 * @param productDataEntity 
	 * @return
	 * @throws Exception
	 */
	public boolean saveProductData(ProductDataEntity productDataEntity) throws Exception;
	/**
	 * 通过fkid获取b2b运价信息
	 * @param fkid
	 * @return
	 * @throws Exception
	 */
	public List<ProductDataEntity> getProductDataByOrderId(String orderId) throws Exception;
	/**
	 * 通过订单id删除订单对应的运价信息
	 * @param orderId
	 * @throws Exception
	 */
	public void deleteProductDataByOrderId(String orderId) throws Exception;
}
