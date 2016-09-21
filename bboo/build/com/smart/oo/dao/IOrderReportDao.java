package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.oo.from.OrderReportVo;

public interface IOrderReportDao {

	/**
	 * 查询
	 * @param page
	 * @return X
	 * @throws SqlException
	 */
	List<Object[]> getSaleOrderList(OrderReportVo from, OrderReportVo voDate)throws Exception;
	
	
}
