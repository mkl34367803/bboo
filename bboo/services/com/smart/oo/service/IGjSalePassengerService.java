package com.smart.oo.service;

import java.util.List;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSalePassengerEntity;

public interface IGjSalePassengerService {

	/**
	 * 根据订单表主键查询旅客信息
	 * @param fkid
	 * @return
	 */
	List<GjSalePassengerEntity> queryByFkid(String fkid) throws Exception;

	

	public void savePass(GjSalePassengerEntity pass)throws SqlException;
	
	int deletepassengrById(String passId);

	
	/**
	 * 更新票号
	 * @param id
	 * @param eticketNum 票号
	 * @return
	 * @throws Exception
	 */
	public void updateTicketNum(String id, String eticketNum) throws Exception;
	
	/**
	 * 更新sale表和buy表的票号
	 * @param id
	 * @param eticketNum
	 * @throws Exception
	 */
	void updateSaleAndBuyTicketNum(String id, String eticketNum) throws Exception;

}
