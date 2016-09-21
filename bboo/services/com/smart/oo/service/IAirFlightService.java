package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.AirFlightEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.oo.domain.GetFlightByLineDomain;

public interface IAirFlightService {

	void saveDatas(GetFlightByLineDomain line, BaseOfficeEntity off,
			KeyValEntity k);

	Integer saveData(AirFlightEntity d) throws Exception;

	List<AirFlightEntity> findByLineno(AirFlightEntity e) throws Exception;

	List<AirFlightEntity> findShares(AirFlightEntity e) throws Exception;

	void deleteData(AirFlightEntity e) throws Exception;
}
