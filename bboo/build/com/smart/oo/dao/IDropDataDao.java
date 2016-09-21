package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.DropDataEntity;

public interface IDropDataDao {

	String saveData(DropDataEntity data) throws Exception;

	List<DropDataEntity> findDistinctFlight(DropDataEntity data)
			throws Exception;
	
	List<DropDataEntity> findFlight(DropDataEntity data)
			throws Exception;

}
