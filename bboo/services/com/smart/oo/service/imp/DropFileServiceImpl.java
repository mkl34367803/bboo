package com.smart.oo.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.DropFileEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IDropFileDao;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IDropFileService;

@Service("GDropFileServiceImpl")
public class DropFileServiceImpl implements IDropFileService {

	@Autowired
	private FactoryDaoImpl daof;

	@Resource(name = "BBOODropFileDaoImpl")
	private IDropFileDao dropFileDao;

	@Override
	public String saveData(DropFileEntity ticke) throws Exception {
		// TODO Auto-generated method stub
		return dropFileDao.saveData(ticke);
	}

	@Override
	public void saveData(List<DropFileEntity> tickes) throws Exception {
		// TODO Auto-generated method stub
		if (tickes != null && tickes.size() > 0) {
			for (DropFileEntity e : tickes) {
				dropFileDao.saveData(e);
			}
		}

	}

	@Override
	public int updateData(DropFileEntity ticke) throws Exception {
		// TODO Auto-generated method stub
		return dropFileDao.updateData(ticke);
	}

	@Override
	public int updateDataById(DropFileEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		return dropFileDao.updateDataById(ticket);
	}

	@Override
	public List<DropFileEntity> findDatas(DropFileEntity ticke, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		return dropFileDao.findDatas(ticke, p);
	}

	@Override
	public List<DropFileEntity> findDatas(DropFileEntity ticke)
			throws Exception {
		// TODO Auto-generated method stub
		return dropFileDao.findDatas(ticke);
	}

}
