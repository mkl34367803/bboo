package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.framework.base.Page;
import com.smart.oo.from.BuyOrderParamVo;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.from.SaleOrderParamVo;

public interface IGjBuyOrderDao {
	
	/**
	 * 拆分订单
	 * @return
	 */
	public boolean splitOrder() throws Exception;
	/**
	 * 通过id查询订单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public GjBuyOrderEntity queyOrderByID(String id) throws Exception;
	/**
	 * 保存订单
	 * @param gjBuyOrderEntity
	 * @return
	 * @throws Exception
	 */
	public String saveBuyOrder(GjBuyOrderEntity gjBuyOrderEntity) throws Exception;
	/**
	 * 更新订单
	 * @param gjBuyOrderEntity
	 * @return
	 * @throws Exception
	 */
	public void updateBuyOrder(GjBuyOrderEntity gjBuyOrderEntity) throws Exception;
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
	
	public List<Object[]> getProfitByPurchasePlace() throws Exception;
	
	/**
	 * 通过采购渠道统计票量
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticTicketByPurchasePlace(OrderChartsVo orderChartsVo) throws Exception;
	
	/**
	 * 通过航司统计票量
	 * @return
	 * @throws Exception
	 */
	public  List<Object[]> statisticTicketByCarrier(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过出票员统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByOperator(OrderChartsVo vo) throws Exception;
	

	/**
	 * 通过乘机人类型统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByAgeDes(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过分销渠道统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByDistributor(OrderChartsVo vo) throws Exception;

	/**
	 * 通过店铺名称统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByShopName(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过日期统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByCreateTime(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过采购渠道统计利润
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticProfitByPurchasePlace(OrderChartsVo orderChartsVo) throws Exception;
	
	/**
	 * 通过航司统计利润
	 * @return
	 * @throws Exception
	 */
	public  List<Object[]> statisticProfitByCarrier(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过出票员统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByOperator(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过分销渠道统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByDistributor(OrderChartsVo vo) throws Exception;

	/**
	 * 通过店铺名称统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByShopName(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过日期统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByCreateTime(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过航司统计销售额
	 * @param vo
	 * @param chartType
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticSaleroomByCarrier(OrderChartsVo vo) throws Exception;
	
	/**
	 * 查询总票量
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticTicket(OrderChartsVo vo) throws Exception;
	
	/**
	 * 查询总利润
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticProfit(OrderChartsVo vo) throws Exception;
	
	/**
	 * 查询销售额
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticSaleroom(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过航司和创单时间统计票量
	 * @return
	 * @throws Exception
	 */
	public  List<Object[]> statisticTicketByCarrierAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过采购渠道和创单时间统计票量
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticTicketByPurchasePlaceAndCrt(OrderChartsVo orderChartsVo) throws Exception;
	
	/**
	 * 通过出票员和创单时间统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByOperatorAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过乘机人类型和创单时间统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByAgeDesAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过分销渠道和创单时间统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByDistributorAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过店铺名称和创单时间统计票量
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticTicketByShopNameAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过航司和创单时间统计利润
	 * @return
	 * @throws Exception
	 */
	public  List<Object[]> statisticProfitByCarrierAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过采购渠道和创单时间统计利润
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> statisticProfitByPurchasePlaceAndCrt(OrderChartsVo orderChartsVo) throws Exception;
	
	/**
	 * 通过出票员和创单时间统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByOperatorAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过分销渠道和创单时间统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByDistributorAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 通过店铺名称和创单时间统计利润
	 * @param vo
	 * @return
	 */
	public List<Object[]> statisticProfitByShopNameAndCrt(OrderChartsVo vo) throws Exception;
	
	/**
	 * 更新订单状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public boolean updateSlfStatus(String id, String slfStatus) throws Exception;
	/**
	 * 通过id来更新字段
	 * @param gjBuyOrderEntity
	 * @return
	 * @throws Exception
	 */
	public void updateById(GjBuyOrderEntity gjBuyOrderEntity) throws Exception;
	
	public List<Object[]> queryOrderSummaryByPage(Page page,BuyOrderParamVo saleOrderParamEntity) throws Exception;
}
