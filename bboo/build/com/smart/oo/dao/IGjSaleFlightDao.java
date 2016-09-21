package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSaleFlightEntity;

public interface IGjSaleFlightDao {

	/**
	 * 根据订单表主键查询销售航班信息
	 * @param fkid
	 * @return
	 */
	List<GjSaleFlightEntity> queryByFkid(String fkid) throws Exception;

	


	/**
	 * 根据订单表主键查询销售航班信息,按照航班起飞日期排序，起飞日期相同，按照起飞时间排序
	 * @param fkid
	 * @return
	 */
	public List<GjSaleFlightEntity> queryByOrderNo(String fkid) throws Exception;
	
	/**
	 * 保存航班信息
	 * @param flight
	 * @throws Exception
	 */
	public void saveSaleFlight(GjSaleFlightEntity flight) throws Exception;
	
	/**
	 * 通过id更新字段
	 * @param gjSaleFlightEntity
	 * @throws Exception
	 */
	public void updateById(GjSaleFlightEntity gjSaleFlightEntity) throws Exception;
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	GjSaleFlightEntity queryById(String id) throws Exception;

}
