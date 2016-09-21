package com.smart.oo.service;

import java.util.List;
import java.util.Map;

import com.smart.comm.entity.DropDataEntity;
import com.smart.comm.entity.DropFileEntity;

public interface IDropDataService {

	String saveData(DropDataEntity data) throws Exception;

	List<DropDataEntity> findDistinctFlight(DropDataEntity data)
			throws Exception;

	List<DropDataEntity> findFlight(DropDataEntity data) throws Exception;

	Map<String, List<DropDataEntity>> getQueryLines(DropDataEntity data)
			throws Exception;
	
	void saveData(List<DropDataEntity> data, DropFileEntity entity)
			throws Exception ;

}
