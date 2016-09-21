package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.AirFlightEntity;

public interface IAirFlightDao {

	Integer saveData(AirFlightEntity d) throws Exception;

	List<AirFlightEntity> findByLineno(AirFlightEntity e) throws Exception;

	List<AirFlightEntity> findShares(AirFlightEntity e) throws Exception;

	void deleteAll() throws Exception;
	
	void deleteData(AirFlightEntity e) throws Exception;
}
