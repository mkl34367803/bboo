package com.smart.oo.dao;

import java.util.List;

import com.smart.comm.entity.TicketStateEntity;
import com.smart.framework.base.Page;

public interface ITicketStateDao {

	String saveData(TicketStateEntity ticket) throws Exception;

	int updateData(TicketStateEntity ticket) throws Exception;

	int updateDataById(TicketStateEntity ticket) throws Exception;

	List<TicketStateEntity> findDatas(TicketStateEntity ticket, Page p)
			throws Exception;

	List<TicketStateEntity> findDatas(TicketStateEntity ticket)
			throws Exception;
}
