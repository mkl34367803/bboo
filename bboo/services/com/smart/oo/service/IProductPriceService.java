package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.oo.domain.res.ProductRootResult;

public interface IProductPriceService {
	/**
	 * 通过fkpid获取运价信息
	 * @param fkpids
	 * @return
	 * @throws Exception
	 */
	public List<ProductPriceEntity> getProductPriceByFkpids(String[] fkpids) throws Exception;
	/**
	 * 通过订单id获取运价信息(如果从本地数据库中没有获取到运价信息或者本地运价缓存时间>10分钟，那么要从接口获取)
	 * @param orderId
	 * @param isUsePnrTxtCache 是否使用订单中的pnrtext字段去获取rt信息。如何使用，rt命令不是实施命令，而是缓存结果 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProductPriceEntity> obtainProductPriceByOrderId(String orderId,boolean isUsePnrTxtCache) throws Exception;
	/**
	 * 以订单号为出发点，从第三方认证接口获取政策信息
	 * 首先删除db中有的政策信息
	 * 然后rt指令
	 * 然后获取政策指令
	 * 入库
	 * @param orderId 订单id
	 * @param isUsePnrTxtCache 是否使用订单中的pnrtext字段去获取rt信息。如何使用，rt命令不是实施命令，而是缓存结果 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProductPriceEntity> obtainProductByThirdAuthInterface(String orderId,boolean isUsePnrTxtCache) throws Exception;
	/**
	 * 通过id获取政策信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductPriceEntity getProductPriceById(String id) throws Exception;
}
