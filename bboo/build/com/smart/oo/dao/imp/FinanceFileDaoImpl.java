package com.smart.oo.dao.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceFileEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IFinanceFileDao;
import com.smart.utils.StringUtils;

@Repository("FinanceFileDaoImpl")
public class FinanceFileDaoImpl extends BaseDAO<FinanceFileEntity,Serializable> implements IFinanceFileDao {
	private static final long serialVersionUID = 1L;


	@Override
	public void saveUploadExcelFile(FinanceFileEntity ff) throws SqlException {
		this.save(ff);
	}


	@Override
	public List<FinanceFileEntity> findByPage(FinanceFileEntity ff, Page p, String endDate)
			throws SqlException {
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("from FinanceFileEntity where 1=1");
		if (StringUtils.isNotEmpty(ff.getFileName())) {
			buf.append(" and fileName=?");
			params.add(ff.getFileName());
		}
		if (StringUtils.isNotEmpty(ff.getFileno())) {
			buf.append(" and fileno=?");
			params.add(ff.getFileno());
		}
		if (StringUtils.isNotEmpty(ff.getFilePath())) {
			buf.append(" and filePath=?");
			params.add(ff.getFilePath());
		}
		if (ff.getFileSize() != null) {
			buf.append(" and fileSize=?");
			params.add(ff.getFileSize());
		}
		if (StringUtils.isNotEmpty(ff.getOperator())) {
			buf.append(" and operator=?");
			params.add(ff.getOperator());
		}
		
		if (StringUtils.isNotEmpty(ff.getDataSpecification())) {
			buf.append(" and dataSpecification=?");
			params.add(ff.getDataSpecification());
		}
		if (StringUtils.isNotEmpty(ff.getId())) {
			buf.append(" and id=?");
			params.add(ff.getId());
		}
		if (StringUtils.isNotEmpty(ff.getCreateTime()) && null == endDate) {
			buf.append(" and convert(varchar(10), createTime, 120)=?");
			params.add(ff.getCreateTime());
		}
		if (StringUtils.isNotEmpty(ff.getCreateTime()) && null != endDate) {
			buf.append(" and createTime between ? and ?");
			params.add(ff.getCreateTime());
			params.add(endDate);
		}
		
		buf.append(" and mno=?");
		buf.append(" order by id desc");
		params.add(ff.getMno());
		return this.find(buf.toString(), params.toArray(), p);
	}

	
	@Override
	public int deleteFile(String id, String mno) throws SqlException {
		String sql="delete t_finance_file where id="+"'"+id+"'"+"   and mno="+"'"+mno+"'";
	   return  this.executeSql(sql);
	}


	@Override
	public List<FinanceFileEntity> queryFiles(FinanceFileEntity fileEntity)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from FinanceFileEntity where 1=1");
		if (StringUtils.isNotEmpty(fileEntity.getId())) {
			hql.append(" and id='" + fileEntity.getId() + "'");
		}
		if (StringUtils.isNotEmpty(fileEntity.getFileName())) {
			hql.append(" and fileName='" + fileEntity.getFileName() + "'");
		}
		if (StringUtils.isNotEmpty(fileEntity.getFileno())) {
			hql.append(" and fileno='"+fileEntity.getFileno() + "'");
		}
		if (StringUtils.isNotEmpty(fileEntity.getOperator())) {
			hql.append(" and operator='"+fileEntity.getOperator() + "'");
		}
		if (StringUtils.isNotEmpty(fileEntity.getFileType())) {
			hql.append(" and fileType='"+fileEntity.getFileType() + "'");
		}
		if (StringUtils.isNotEmpty(fileEntity.getState())) {
			hql.append(" and state='"+fileEntity.getState() + "'");
		}
		hql.append(" and mno='"+fileEntity.getMno()+"'");
		return this.find(hql.toString());
	}


	@Override
	public void updateFinanceFile(FinanceFileEntity fileEntity)
			throws Exception {
		this.update(fileEntity);
	}
}
