package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.DropFileEntity;
import com.smart.framework.base.Page;

public interface IDropFileDao {

	String saveData(DropFileEntity tickes) throws Exception;

	int updateData(DropFileEntity tickes) throws Exception;

	int updateDataById(DropFileEntity ticket) throws Exception;

	List<DropFileEntity> findDatas(DropFileEntity tickes, Page p)
			throws Exception;

	List<DropFileEntity> findDatas(DropFileEntity tickes) throws Exception;
}
