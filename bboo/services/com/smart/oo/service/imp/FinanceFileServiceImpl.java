package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceFileEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IFinanceFileService;


@Transactional
@Service("FinanceFileServiceImpl")
public class FinanceFileServiceImpl implements IFinanceFileService {

	@Autowired
	private FactoryDaoImpl daoFactory;

	@Override
	public List<FinanceFileEntity> findByPage(FinanceFileEntity ff, Page p, String endDate)
			throws SqlException {
		// TODO Auto-generated method stub
		return this.daoFactory.getFinanceFileDao().findByPage(ff, p, endDate);
	}

	@Override
	public void deleteFile(FinanceFileEntity f) throws Exception {
		if (null != f) {
			this.daoFactory.getFinanceFileDao().deleteFile(f.getId(), f.getMno());
			this.daoFactory.getFinanceSaleDao().deleteByFileNo(f.getFileno(), f.getMno());
		}
	}

	@Override
	public  List<FinanceFileEntity> queryFiles(FinanceFileEntity financeFile) throws Exception {
		return this.daoFactory.getFinanceFileDao().queryFiles(financeFile);
	}

	@Override
	public void saveUploadExcelFile(FinanceFileEntity ff) throws SqlException {
		this.daoFactory.getFinanceFileDao().saveUploadExcelFile(ff);
	}

	@Override
	public void updateFinanceFile(FinanceFileEntity fileEntity)
			throws Exception {
		this.daoFactory.getFinanceFileDao().updateFinanceFile(fileEntity);
	}

}
