package com.smart.oo.service;

import java.util.List;

import com.smart.comm.entity.TicketFileEntity;
import com.smart.framework.base.Page;

public interface ITicketFileService {

	String saveData(TicketFileEntity ticke) throws Exception;

	void saveData(List<TicketFileEntity> tickes) throws Exception;

	int updateData(TicketFileEntity ticke) throws Exception;
	
	int updateDataById(TicketFileEntity ticket) throws Exception ;

	List<TicketFileEntity> findDatas(TicketFileEntity ticke, Page p)
			throws Exception;

	List<TicketFileEntity> findDatas(TicketFileEntity ticke) throws Exception;

}
