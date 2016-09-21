package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.framework.base.Page;
import com.smart.oo.from.BuyOrderParamVo;
import com.smart.oo.from.BuyOrderProfit;
import com.smart.oo.from.BuyOrderSummaryVo;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.from.PurchaseInfoVo;

public interface IGJBuyOrderService {
	/**
	 * 拆分订单
	 * @return
	 */
	public boolean splitOrder(String id,String[] passengerIDs) throws Exception;
	/**
	 * 保存采购信息
	 * @param id
	 * @param purchaseNo
	 * @param pnrCode
	 * @param payWay
	 * @param payAccount
	 * @param transactionNo
	 * @param printPrice
	 * @param backPoint
	 * @param afterPoint
	 * @param stickingPoint
	 * @param reward
	 * @param price
	 * @param cost
	 * @param oilFee
	 * @param taxFee
	 * @param profit
	 * @param allprice
	 * @throws Exception
	 */
	public void savePurchaseInfo(PurchaseInfoVo purchaseInfoVo) throws Exception;
	//通过订单号查询买入订单；
	public GjBuyOrderEntity queyOrderByID(String id) throws Exception;
	
	/**
	 * 更新pnr
	 * @param id
	 * @param pnrCode
	 * @return
	 * @throws Exception
	 */
	public boolean updatePnrCode(String id, String pnrCode) throws Exception;
	/**
	 * 通过创单时间来查询买入订单。
	 * @return 买入订单list
	 * @throws Exception
	 */
	public List<GjBuyOrderEntity> queryByCreateTime(String startTime,String endTime) throws Exception;
	/**
	 * 获取按采购地汇总的，利润信息
	 * @return
	 * @throws Exception
	 */
	public List<BuyOrderProfit> getProfitByPurchasePlace() throws Exception;
	/**
	 * 通过第三方接口创单
	 * @return
	 * @throws Exception
	 */
	public Boolean createOrderByThirdInterface(String productPriceId,GjSaleOrderEntity gjSaleOrderEntity) throws Exception;
	/**
	 * 通过第三方接口支付
	 * @param gjSaleOrderEntity
	 * @return
	 * @throws Exception
	 */
	public Boolean payOrderByThirdInterface(GjBuyOrderEntity gjBuyOrderEntity) throws Exception;
	
	/**
	 * 通过采购渠道统计
	 * @return
	 * @throws Exception
	 */
	public List<OrderChartsVo> statisticByPurchasePlaceCh(OrderChartsVo orderChartsVo) throws Exception;
	/**
	 * 通过id来更新字段
	 * @param gjBuyOrderEntity
	 * @return
	 * @throws Exception
	 */
	public void updateById(GjBuyOrderEntity gjBuyOrderEntity) throws Exception;
	/**
	 * 通过分页查询买入订单的数据
	 * @param page
	 * @param buyOrderParamVo
	 * @return
	 * @throws Exception
	 */
	public List<BuyOrderSummaryVo> queryOrderSummaryByPage(Page page,BuyOrderParamVo buyOrderParamVo) throws Exception;
	/**
	 * 已经线下支付的订单进行处理
	 * @param orderId
	 * @throws Exception
	 */
	public void alreadyPay(String orderId) throws Exception;
	/**
	 * 通过bsp的etdz创建订单
	 * @param orderId
	 * @param baseOfficeId
	 * @return
	 * @throws Exception
	 */
	public boolean createOrderByEtdz(String orderId,String baseOfficeId,String productPriceId) throws Exception;
}
