package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.TicketFileEntity;
import com.smart.framework.base.Page;

public interface ITicketFileDao {

	String saveData(TicketFileEntity tickes) throws Exception;

	int updateData(TicketFileEntity tickes) throws Exception;

	int updateDataById(TicketFileEntity ticket) throws Exception;

	List<TicketFileEntity> findDatas(TicketFileEntity tickes, Page p)
			throws Exception;

	List<TicketFileEntity> findDatas(TicketFileEntity tickes) throws Exception;
}
