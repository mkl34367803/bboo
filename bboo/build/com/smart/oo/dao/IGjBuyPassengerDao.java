package com.smart.oo.dao;

import com.smart.comm.entity.GjBuyPassengerEntity;

public interface IGjBuyPassengerDao {
	/**
	 * 保存乘客信息
	 * @param gjBuyPassengerEntity
	 * @return
	 * @throws Exception
	 */
	public String savePassenger(GjBuyPassengerEntity gjBuyPassengerEntity) throws Exception;
	
	/**
	 * 更新票号
	 * @param id
	 * @param eticketNum 票号
	 * @return
	 * @throws Exception
	 */
	public boolean updateTicketNum(String id, String eticketNum) throws Exception;
}
