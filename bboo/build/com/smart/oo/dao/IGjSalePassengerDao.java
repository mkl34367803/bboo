package com.smart.oo.dao;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSalePassengerEntity;

public interface IGjSalePassengerDao {

	/**
	 * 根据订单表主键查询旅客信息
	 * @param fkid
	 * @return
	 */
	List<GjSalePassengerEntity> queryByFkid(String fkid) throws Exception;

	
	
	/**
	 * 导入乘客信息
	 * @param gjSalePassengerEntity
	 * @throws Exception
	 */
	public void saveSalePassenger(GjSalePassengerEntity gjSalePassengerEntity) throws Exception;

	
	
	public void passadd(GjSalePassengerEntity passentity)throws SqlException;


	
	/**
	 * 更新票号
	 * @param id
	 * @param eticketNum 票号
	 * @return
	 * @throws Exception
	 */
	public boolean updateTicketNum(String id, String eticketNum) throws Exception;
	/**
	 * 根据订单表主键查询旅客信息
	 * @param fkid
	 * @return
	 */
	List<GjSalePassengerEntity> queryByFkids(List<String> fkids) throws Exception;
	public void deleteById(String id) throws Exception;

}
