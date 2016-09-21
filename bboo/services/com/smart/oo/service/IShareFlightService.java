package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.ShareFlightEntity;

public interface IShareFlightService {

	Integer saveData(ShareFlightEntity e) throws Exception;

	void updateShareCabin() throws Exception;

	void updatePrice() throws Exception;

	void deleteAll() throws Exception;

	void saveData(List<ShareFlightEntity> elist) throws Exception;

	List<ShareFlightEntity> findShareDatas(ShareFlightEntity e)
			throws Exception;
}
