package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.TicketFileEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.ITicketFileService;

@Service("GTicketFileServiceImpl")
public class TicketFileServiceImpl implements ITicketFileService {

	@Autowired
	private FactoryDaoImpl daof;

	@Override
	public String saveData(TicketFileEntity ticke) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketFileDao().saveData(ticke);
	}

	@Override
	public void saveData(List<TicketFileEntity> tickes) throws Exception {
		// TODO Auto-generated method stub
		if (tickes != null && tickes.size() > 0) {
			for (TicketFileEntity t : tickes) {
				daof.getIticketFileDao().saveData(t);
			}
		}
	}

	@Override
	public int updateData(TicketFileEntity ticke) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketFileDao().updateData(ticke);
	}

	@Override
	public List<TicketFileEntity> findDatas(TicketFileEntity ticke, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketFileDao().findDatas(ticke, p);
	}

	@Override
	public List<TicketFileEntity> findDatas(TicketFileEntity ticke)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketFileDao().findDatas(ticke);
	}

	@Override
	public int updateDataById(TicketFileEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIticketFileDao().updateDataById(ticket);
	}

}
