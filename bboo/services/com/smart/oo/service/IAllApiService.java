package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.entity.ProductPriceEntity;
import com.smart.oo.domain.FlightDynamicsDomain;
import com.smart.oo.domain.res.BuildPnrResult;
import com.smart.oo.domain.res.CreateOrderForPb2bResult;
import com.smart.oo.domain.res.DistributionModifyOrderInfoResult;
import com.smart.oo.domain.res.EtdzResult;
import com.smart.oo.domain.res.FlightDynamicsResult;
import com.smart.oo.domain.res.GDSResult;
import com.smart.oo.domain.res.GetFlightByLineResult;
import com.smart.oo.domain.res.GetPnrInfoResult;
import com.smart.oo.domain.res.GjOrderGetDetailResult;
import com.smart.oo.domain.res.PayOrderForPb2bResult;
import com.smart.oo.domain.res.ProductRootResult;

/**
 * 调用地三方接口需要用到的一些方法
 * @author Administrator
 *
 */
public interface IAllApiService {
	/**
	 * 通过rt指令,调用第三方接口，获取大编码和prntxt
	 * @param baseAccountEntity
	 * @param gjSaleOrderEntity
	 * @param isUsePnrTxtCache 是否使用订单中的pnrtext字段去获取rt信息。如何使用，rt命令不是实施命令，而是缓存结果
	 * @return
	 */
	public GetPnrInfoResult getRtByThirdInterface(GjSaleOrderEntity gjSaleOrderEntity,boolean isUsePnrTxtCache);
	/**
	 * 预定编码
	 * @param gjSaleOrderEntity
	 * @return
	 * @throws Exception
	 */
	public BuildPnrResult reservePnr(GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 通过订单号获取分销商（淘宝，携程，去哪）的订单的详细信息
	 * @param baseAccountEntity
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public GjOrderGetDetailResult getOrderInfoByInteface(BaseAccountEntity baseAccountEntity, String orderNo) throws Exception;
	/**
	 * 调用第三方接口获取avh信息
	 * @return
	 * @throws Exception
	 */
	public GetFlightByLineResult getAvhByThirdInterface(String mno,String office,GjSaleFlightEntity gjSaleFlightEntity) throws Exception;
	/**
	 * 通过第三方接口获取政策信息
	 * @param gjSaleOrderEntity 订单信息
	 * @param rtrs rt指令返回的结果集
	 * @return
	 * @throws Exception
	 */
	public List<ProductRootResult>  getProductDataByThirdAuthInterface(
			GjSaleOrderEntity gjSaleOrderEntity,GetPnrInfoResult rtrs) throws Exception;
	/**
	 * 通过命令的方式查询rt信息,avh信息等等，只要是通过命令查询的，都调这个方法
	 * @param baseOffice表里面的appkey，cmd是你要调用的命令
	 * @return
	 * @throws Exception
	 */
	public GDSResult getCmdsByThirdInterface(String appkey,String cmds) throws Exception;
	/**
	 * 创单的接口
	 * @return
	 * @throws Exception
	 */
	public CreateOrderForPb2bResult createOrderByThirdInterface(ProductPriceEntity productPriceEntity,GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 支付的接口
	 * @param productPriceEntity
	 * @param gjBuyOrderEntity
	 * @return
	 * @throws Exception
	 */
	public PayOrderForPb2bResult payOrderByThirdInterface(GjBuyOrderEntity gjBuyOrderEntity,BaseAccountEntity baseAccountEntity) throws Exception;
	/**
	 * 调用分销商的接口，修改分销商（如携程、去哪儿、淘宝）订单状态
	 * @param saleOrderEntity
	 * @param buyOrderEntity
	 * @param baseAccountEntity
	 * @return
	 * @throws Exception
	 */
	public DistributionModifyOrderInfoResult modifyOrderByThirdInterface(GjSaleOrderEntity saleOrderEntity, GjBuyOrderEntity buyOrderEntity, BaseAccountEntity baseAccountEntity) throws Exception;
	/**
	 * 通过bsp的etdz创建订单，并且直接出票。
	 * @return
	 * @throws Exception
	 */
	public EtdzResult createOrderByEtdz(GjSaleOrderEntity gjSaleOrderEntity,BaseOfficeEntity baseOfficeEntity) throws Exception;
	
	/**
	 * 获取航班时刻信息
	 * @param flightDynamicsDomain
	 * @return
	 * @throws Exception
	 */
	public FlightDynamicsResult getFlightDynamics(FlightDynamicsDomain flightDynamicsDomain) throws Exception;
	
}
