package com.smart.oo.service;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.oo.from.OrderReportVo;

public interface IOrderReportService {

	/**
	 *  查询
	 * 
	 * @param page
	 * @return X
	 * @throws SqlException
	 */
	List<OrderReportVo> getSaleOrderList(OrderReportVo from, OrderReportVo voDate) throws Exception;

}
