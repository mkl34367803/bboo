package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.TicketFileEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.ITicketFileDao;
import com.smart.utils.StringUtils;

@Repository("GTicketFileDaoImpl")
public class TicketFileDaoImpl extends BaseDAO<TicketFileEntity, String>
		implements ITicketFileDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5785743032998194011L;

	@Override
	public String saveData(TicketFileEntity tickes) throws Exception {
		// TODO Auto-generated method stub
		return this.save(tickes);
	}

	@Override
	public int updateData(TicketFileEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("update TicketFileEntity set id=?");
		params.add(ticket.getId());
		if (StringUtils.isNotEmpty(ticket.getFileName())) {
			buf.append(",fileName=?");
			params.add(ticket.getFileName());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(",fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getFilePath())) {
			buf.append(",filePath=?");
			params.add(ticket.getFilePath());
		}
		if (ticket.getFileSize() != null) {
			buf.append(",fileSize=?");
			params.add(ticket.getFileSize());
		}
		if (StringUtils.isNotEmpty(ticket.getOperator())) {
			buf.append(",operator=?");
			params.add(ticket.getOperator());
		}
		if (ticket.getScanStatus() != null) {
			buf.append(",scanStatus=?");
			params.add(ticket.getScanStatus());
		}
		if (StringUtils.isNotEmpty(ticket.getDataSpecification())) {
			buf.append(",dataSpecification=?");
			params.add(ticket.getDataSpecification());
		}
		if (StringUtils.isNotEmpty(ticket.getScanSpecification())) {
			buf.append(",scanSpecification=?");
			params.add(ticket.getScanSpecification());
		}
		if (StringUtils.isNotEmpty(ticket.getLastTime())) {
			buf.append(",lastTime=?");
			params.add(ticket.getLastTime());
		}
		buf.append(" where id=? and mno=?");
		params.add(ticket.getId());
		params.add(ticket.getMno());
		return this.executeHql(buf.toString(), params.toArray());
	}

	@Override
	public int updateDataById(TicketFileEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("update TicketFileEntity set id=?");
		params.add(ticket.getId());
		if (StringUtils.isNotEmpty(ticket.getFileName())) {
			buf.append(",fileName=?");
			params.add(ticket.getFileName());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(",fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getFilePath())) {
			buf.append(",filePath=?");
			params.add(ticket.getFilePath());
		}
		if (ticket.getFileSize() != null) {
			buf.append(",fileSize=?");
			params.add(ticket.getFileSize());
		}
		if (StringUtils.isNotEmpty(ticket.getOperator())) {
			buf.append(",operator=?");
			params.add(ticket.getOperator());
		}
		if (ticket.getScanStatus() != null) {
			buf.append(",scanStatus=?");
			params.add(ticket.getScanStatus());
		}
		if (StringUtils.isNotEmpty(ticket.getDataSpecification())) {
			buf.append(",dataSpecification=?");
			params.add(ticket.getDataSpecification());
		}
		if (StringUtils.isNotEmpty(ticket.getScanSpecification())) {
			buf.append(",scanSpecification=?");
			params.add(ticket.getScanSpecification());
		}
		if (StringUtils.isNotEmpty(ticket.getLastTime())) {
			buf.append(",lastTime=?");
			params.add(ticket.getLastTime());
		}
		buf.append(" where id=?");
		params.add(ticket.getId());
		return this.executeHql(buf.toString(), params.toArray());
	}

	@Override
	public List<TicketFileEntity> findDatas(TicketFileEntity ticket, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("from TicketFileEntity where 1=1");
		if (StringUtils.isNotEmpty(ticket.getFileName())) {
			buf.append(" and fileName=?");
			params.add(ticket.getFileName());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(" and fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getFilePath())) {
			buf.append(" and filePath=?");
			params.add(ticket.getFilePath());
		}
		if (ticket.getFileSize() != null) {
			buf.append(" and fileSize=?");
			params.add(ticket.getFileSize());
		}
		if (StringUtils.isNotEmpty(ticket.getOperator())) {
			buf.append(" and operator=?");
			params.add(ticket.getOperator());
		}
		if (ticket.getScanStatus() != null) {
			buf.append(" and scanStatus=?");
			params.add(ticket.getScanStatus());
		}
		if (StringUtils.isNotEmpty(ticket.getDataSpecification())) {
			buf.append(" and dataSpecification=?");
			params.add(ticket.getDataSpecification());
		}
		if (StringUtils.isNotEmpty(ticket.getId())) {
			buf.append(" and id=?");
			params.add(ticket.getId());
		}
		if (StringUtils.isNotEmpty(ticket.getCreateTime())) {
			buf.append(" and convert(varchar(10), createTime, 120)=?");
			params.add(ticket.getCreateTime());
		}
		if (StringUtils.isNotEmpty(ticket.getLastTime())) {
			buf.append(" and convert(varchar(10), lastTime, 120)=?");
			params.add(ticket.getLastTime());
		}
		buf.append(" and mno=?");
		buf.append(" order by scanStatus asc,id desc");
		params.add(ticket.getMno());
		return this.find(buf.toString(), params.toArray(), p);
	}

	@Override
	public List<TicketFileEntity> findDatas(TicketFileEntity ticket)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("from TicketFileEntity where 1=1");
		if (StringUtils.isNotEmpty(ticket.getFileName())) {
			buf.append(" and fileName=?");
			params.add(ticket.getFileName());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(" and fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getFilePath())) {
			buf.append(" and filePath=?");
			params.add(ticket.getFilePath());
		}
		if (ticket.getFileSize() != null) {
			buf.append(" and fileSize=?");
			params.add(ticket.getFileSize());
		}
		if (StringUtils.isNotEmpty(ticket.getOperator())) {
			buf.append(" and operator=?");
			params.add(ticket.getOperator());
		}
		if (ticket.getScanStatus() != null) {
			buf.append(" and scanStatus=?");
			params.add(ticket.getScanStatus());
		}
		if (StringUtils.isNotEmpty(ticket.getDataSpecification())) {
			buf.append(" and dataSpecification=?");
			params.add(ticket.getDataSpecification());
		}
		if (StringUtils.isNotEmpty(ticket.getId())) {
			buf.append(" and id=?");
			params.add(ticket.getId());
		}
		if (StringUtils.isNotEmpty(ticket.getCreateTime())) {
			buf.append(" and convert(varchar(10), createTime, 120)=?");
			params.add(ticket.getCreateTime());
		}
		if (StringUtils.isNotEmpty(ticket.getLastTime())) {
			buf.append(" and convert(varchar(10), lastTime, 120)=?");
			params.add(ticket.getLastTime());
		}
		if (StringUtils.isNotEmpty(ticket.getMno())) {
			buf.append(" and mno=?");
			params.add(ticket.getMno());
		}
		buf.append(" order by scanStatus asc,id desc");
		return this.find(buf.toString(), params.toArray());
	}

}
