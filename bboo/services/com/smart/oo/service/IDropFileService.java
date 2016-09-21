package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.DropFileEntity;
import com.smart.framework.base.Page;

public interface IDropFileService {

	String saveData(DropFileEntity ticke) throws Exception;

	void saveData(List<DropFileEntity> tickes) throws Exception;

	int updateData(DropFileEntity ticke) throws Exception;

	int updateDataById(DropFileEntity ticket) throws Exception;

	List<DropFileEntity> findDatas(DropFileEntity ticke, Page p)
			throws Exception;

	List<DropFileEntity> findDatas(DropFileEntity ticke) throws Exception;

}
