package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.ProductDataEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.ProductRootResult;

public interface IProductDataService {
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

	/**
	 * 将政策信息存储到数据库中去
	 * 
	 * @param productRootResults
	 * @param gjSaleOrderEntity
	 * @return
	 */
	public boolean insertPolicyToDB(List<ProductRootResult> productRootResults,
			GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 将获取到的政策信息插入到数据库中，同时修改订单表中某些字段
	 * @param gjSaleOrderEntity
	 * @param productRootResults
	 * @throws Exception
	 */
	public boolean insertProductAndUpdateOrderFields(GjSaleOrderEntity gjSaleOrderEntity,List<ProductRootResult> productRootResults) throws Exception;
}
