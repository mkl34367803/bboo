package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.ShareFlightEntity;

public interface IShareFlightDao {

	Integer saveData(ShareFlightEntity e) throws Exception;

	void updateShareCabin() throws Exception;

	void updatePrice() throws Exception;

	List<ShareFlightEntity> findShareDatas(ShareFlightEntity e)
			throws Exception;

	void deleteAll() throws Exception;
}
