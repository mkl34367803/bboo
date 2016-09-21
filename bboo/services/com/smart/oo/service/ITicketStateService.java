package com.smart.oo.service;

import java.util.List;
import java.util.Map;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.entity.TicketFileEntity;
import com.smart.comm.entity.TicketStateEntity;
import com.smart.framework.base.Page;
import com.smart.oo.vo.DetrInfoResVO;

public interface ITicketStateService {

	DetrInfoResVO detrByApi(TicketStateEntity ticket,
			Map<String, KeyValEntity> kv, List<BaseAccountEntity> accounts,
			List<BaseOfficeEntity> offobjs, String rawType);

	String saveData(TicketStateEntity ticket) throws Exception;

	void saveData(List<TicketStateEntity> tickets) throws Exception;

	void saveData(List<TicketStateEntity> tickets, TicketFileEntity Entity)
			throws Exception;

	int updateData(TicketStateEntity ticket) throws Exception;
	
	int updateDataById(TicketStateEntity ticket) throws Exception;

	List<TicketStateEntity> findDatas(TicketStateEntity ticket, Page p)
			throws Exception;

	List<TicketStateEntity> findDatas(TicketStateEntity ticket)
			throws Exception;

}
