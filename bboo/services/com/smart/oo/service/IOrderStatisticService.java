package com.smart.oo.service;

import java.util.List;

import com.smart.oo.from.OrderChartsVo;

public interface IOrderStatisticService {

	/**
	 * 统计票量图表
	 * @param vo
	 * @return
	 */
	List<OrderChartsVo> statisticTicketCharts(OrderChartsVo vo, String chartType) throws Exception;
	
	/**
	 * 统计利润图表
	 * @param vo
	 * @return
	 */
	List<OrderChartsVo> statisticProfitCharts(OrderChartsVo vo, String chartType) throws Exception;
	
	/**
	 * 统计销售额图表
	 * @param vo
	 * @param chartType
	 * @return
	 * @throws Exception
	 */
	List<OrderChartsVo> statisticSaleroomCharts(OrderChartsVo vo) throws Exception;
	
	/**
	 * 获取全部票量
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	double getAllTicket(OrderChartsVo vo) throws Exception;
	
	/**
	 * 获取全部利润
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	double getAllProfit(OrderChartsVo vo) throws Exception;
	
	/**
	 * 获取全部销售额
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	double getAllSaleroom(OrderChartsVo vo) throws Exception;
	
	/**
	 * 根据日期统计票量图表
	 * @param vo
	 * @return
	 */
	List<OrderChartsVo> statisticTicketChartsByCrt(OrderChartsVo vo, String chartType) throws Exception;
	
	/**
	 * 根据日期统计利润图表
	 * @param vo
	 * @return
	 */
	List<OrderChartsVo> statisticProfitChartsByCrt(OrderChartsVo vo, String chartType) throws Exception;
}
