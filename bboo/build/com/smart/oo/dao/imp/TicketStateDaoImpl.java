package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.TicketStateEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.ITicketStateDao;
import com.smart.utils.StringUtils;

@Repository("GTicketStateDaoImpl")
public class TicketStateDaoImpl extends BaseDAO<TicketStateEntity, String>
		implements ITicketStateDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -862271500757993658L;

	@Override
	public String saveData(TicketStateEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		return this.save(ticket);
	}

	@Override
	public int updateData(TicketStateEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("update TicketStateEntity set id=?");
		params.add(ticket.getId());
		if (StringUtils.isNotEmpty(ticket.getNo())) {
			buf.append(",no=?");
			params.add(ticket.getNo());
		}
		if (StringUtils.isNotEmpty(ticket.getPlans())) {
			buf.append(",plans=?");
			params.add(ticket.getPlans());
		}
		if (StringUtils.isNotEmpty(ticket.getOid())) {
			buf.append(",oid=?");
			params.add(ticket.getOid());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName())) {
			buf.append(",passengerName=?");
			params.add(ticket.getPassengerName());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName2())) {
			buf.append(",passengerName2=?");
			params.add(ticket.getPassengerName2());
		}
		if (StringUtils.isNotEmpty(ticket.getRemark())) {
			buf.append(",remark=?");
			params.add(ticket.getRemark());
		}

		if (StringUtils.isNotEmpty(ticket.getTakeOff())) {
			buf.append(",takeOff=?");
			params.add(ticket.getTakeOff());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(",fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getState())) {
			buf.append(",state=state+?");
			params.add(ticket.getState());
		}
		if (StringUtils.isNotEmpty(ticket.getMessages())) {
			buf.append(",messages=?");
			params.add(ticket.getMessages());
		}
		if (StringUtils.isNotEmpty(ticket.getLastime())) {
			buf.append(",lastime=?");
			params.add(ticket.getLastime());
		}
		if (StringUtils.isNotEmpty(ticket.getFltinfo())) {
			buf.append(",fltinfo=?");
			params.add(ticket.getFltinfo());
		}
		if (ticket.getQueryok() != null) {
			buf.append(",queryok=?");
			params.add(ticket.getQueryok());
		}
		if (StringUtils.isNotEmpty(ticket.getDelayInfo())) {
			buf.append(",delayInfo=?");
			params.add(ticket.getDelayInfo());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanDepTime())) {
			buf.append(",planDepTime=?");
			params.add(ticket.getPlanDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanArrTime())) {
			buf.append(",planArrTime=?");
			params.add(ticket.getPlanArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActDepTime())) {
			buf.append(",actDepTime=?");
			params.add(ticket.getActDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActArrTime())) {
			buf.append(",actArrTime=?");
			params.add(ticket.getActArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getZtms())) {
			buf.append(",ztms=?");
			params.add(ticket.getZtms());
		}
		if (StringUtils.isNotEmpty(ticket.getCtm())) {
			buf.append(", convert(varchar(10), ctm, 120)=?");
			params.add(ticket.getCtm());
		}

		if (ticket.getYwtype() != null) {
			buf.append(",ywtype=?");
			params.add(ticket.getYwtype());
		}
		if (ticket.getDelayState() != null) {
			buf.append(",delayState=?");
			params.add(ticket.getDelayState());
		}
		buf.append(" where id=? and mno=?");
		params.add(ticket.getId());
		params.add(ticket.getMno());
		return this.executeHql(buf.toString(), params.toArray());
	}

	@Override
	public int updateDataById(TicketStateEntity ticket) throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("update TicketStateEntity set id=?");
		params.add(ticket.getId());
		if (StringUtils.isNotEmpty(ticket.getNo())) {
			buf.append(",no=?");
			params.add(ticket.getNo());
		}
		if (StringUtils.isNotEmpty(ticket.getPlans())) {
			buf.append(",plans=?");
			params.add(ticket.getPlans());
		}

		if (StringUtils.isNotEmpty(ticket.getOid())) {
			buf.append(",oid=?");
			params.add(ticket.getOid());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName())) {
			buf.append(",passengerName=?");
			params.add(ticket.getPassengerName());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName2())) {
			buf.append(",passengerName2=?");
			params.add(ticket.getPassengerName2());
		}
		if (StringUtils.isNotEmpty(ticket.getRemark())) {
			buf.append(",remark=?");
			params.add(ticket.getRemark());
		}

		if (StringUtils.isNotEmpty(ticket.getTakeOff())) {
			buf.append(",takeOff=?");
			params.add(ticket.getTakeOff());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(",fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getState())) {
			buf.append(",state=state+?");
			params.add(ticket.getState());
		}
		if (StringUtils.isNotEmpty(ticket.getMessages())) {
			buf.append(",messages=?");
			params.add(ticket.getMessages());
		}
		if (StringUtils.isNotEmpty(ticket.getLastime())) {
			buf.append(",lastime=?");
			params.add(ticket.getLastime());
		}
		if (StringUtils.isNotEmpty(ticket.getFltinfo())) {
			buf.append(",fltinfo=?");
			params.add(ticket.getFltinfo());
		}
		if (ticket.getQueryok() != null) {
			buf.append(",queryok=?");
			params.add(ticket.getQueryok());
		}
		if (StringUtils.isNotEmpty(ticket.getDelayInfo())) {
			buf.append(",delayInfo=?");
			params.add(ticket.getDelayInfo());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanDepTime())) {
			buf.append(",planDepTime=?");
			params.add(ticket.getPlanDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanArrTime())) {
			buf.append(",planArrTime=?");
			params.add(ticket.getPlanArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActDepTime())) {
			buf.append(",actDepTime=?");
			params.add(ticket.getActDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActArrTime())) {
			buf.append(",actArrTime=?");
			params.add(ticket.getActArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getZtms())) {
			buf.append(",ztms=?");
			params.add(ticket.getZtms());
		}
		if (StringUtils.isNotEmpty(ticket.getCtm())) {
			buf.append(", convert(varchar(10), ctm, 120)=?");
			params.add(ticket.getCtm());
		}

		if (ticket.getYwtype() != null) {
			buf.append(",ywtype=?");
			params.add(ticket.getYwtype());
		}
		if (ticket.getDelayState() != null) {
			buf.append(",delayState=?");
			params.add(ticket.getDelayState());
		}
		buf.append(" where id=?");
		params.add(ticket.getId());
		return this.executeHql(buf.toString(), params.toArray());
	}

	@Override
	public List<TicketStateEntity> findDatas(TicketStateEntity ticket, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("from TicketStateEntity where 1=1");

		if (StringUtils.isNotEmpty(ticket.getNo())) {
			buf.append(" and no=?");
			params.add(ticket.getNo());
		}
		if (StringUtils.isNotEmpty(ticket.getOid())) {
			buf.append(" and oid=?");
			params.add(ticket.getOid());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName())) {
			buf.append(" and passengerName=?");
			params.add(ticket.getPassengerName());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName2())) {
			buf.append(" and passengerName2=?");
			params.add(ticket.getPassengerName2());
		}
		if (StringUtils.isNotEmpty(ticket.getRemark())) {
			buf.append(" and remark=?");
			params.add(ticket.getRemark());
		}
		if (StringUtils.isNotEmpty(ticket.getTakeOff())) {
			buf.append(" and takeOff=?");
			params.add(ticket.getTakeOff());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(" and fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getState())) {
			buf.append(" and state=?");
			params.add(ticket.getState());
		}
		if (StringUtils.isNotEmpty(ticket.getMessages())) {
			buf.append(" and messages=?");
			params.add(ticket.getMessages());
		}
		if (StringUtils.isNotEmpty(ticket.getLastime())) {
			buf.append(" and lastime=?");
			params.add(ticket.getLastime());
		}
		if (StringUtils.isNotEmpty(ticket.getFltinfo())) {
			buf.append(" and fltinfo=?");
			params.add(ticket.getFltinfo());
		}
		if (ticket.getQueryok() != null) {
			buf.append(" and queryok=?");
			params.add(ticket.getQueryok());
		}
		if (StringUtils.isNotEmpty(ticket.getDelayInfo())) {
			buf.append(" and delayInfo=?");
			params.add(ticket.getDelayInfo());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanDepTime())) {
			buf.append(" and planDepTime=?");
			params.add(ticket.getPlanDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanArrTime())) {
			buf.append(" and planArrTime=?");
			params.add(ticket.getPlanArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActDepTime())) {
			buf.append(" and actDepTime=?");
			params.add(ticket.getActDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActArrTime())) {
			buf.append(" and actArrTime=?");
			params.add(ticket.getActArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getZtms())) {
			buf.append(" and ztms=?");
			params.add(ticket.getZtms());
		}
		if (StringUtils.isNotEmpty(ticket.getCtm())) {
			buf.append(" and convert(varchar(10), ctm, 120)=?");
			params.add(ticket.getCtm());
		}
		if (ticket.getYwtype() != null) {
			buf.append(" and ywtype=?");
			params.add(ticket.getYwtype());
		}
		if (ticket.getDelayState() != null) {
			buf.append(" and delayState=?");
			params.add(ticket.getDelayState());
		}

		buf.append(" and mno=?");
		buf.append(" order by queryok asc,id desc");
		params.add(ticket.getMno());
		return this.find(buf.toString(), params.toArray(), p);
	}

	@Override
	public List<TicketStateEntity> findDatas(TicketStateEntity ticket)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer("from TicketStateEntity where 1=1");

		if (StringUtils.isNotEmpty(ticket.getNo())) {
			buf.append(" and no=?");
			params.add(ticket.getNo());
		}
		if (StringUtils.isNotEmpty(ticket.getOid())) {
			buf.append(" and oid=?");
			params.add(ticket.getOid());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName())) {
			buf.append(" and passengerName=?");
			params.add(ticket.getPassengerName());
		}
		if (StringUtils.isNotEmpty(ticket.getPassengerName2())) {
			buf.append(" and passengerName2=?");
			params.add(ticket.getPassengerName2());
		}
		if (StringUtils.isNotEmpty(ticket.getRemark())) {
			buf.append(" and remark=?");
			params.add(ticket.getRemark());
		}
		if (StringUtils.isNotEmpty(ticket.getTakeOff())) {
			buf.append(" and takeOff=?");
			params.add(ticket.getTakeOff());
		}
		if (StringUtils.isNotEmpty(ticket.getFileno())) {
			buf.append(" and fileno=?");
			params.add(ticket.getFileno());
		}
		if (StringUtils.isNotEmpty(ticket.getState())) {
			buf.append(" and state=?");
			params.add(ticket.getState());
		}
		if (StringUtils.isNotEmpty(ticket.getMessages())) {
			buf.append(" and messages=?");
			params.add(ticket.getMessages());
		}
		if (StringUtils.isNotEmpty(ticket.getLastime())) {
			buf.append(" and lastime=?");
			params.add(ticket.getLastime());
		}
		if (StringUtils.isNotEmpty(ticket.getFltinfo())) {
			buf.append(" and fltinfo=?");
			params.add(ticket.getFltinfo());
		}
		if (ticket.getQueryok() != null) {
			buf.append(" and queryok=?");
			params.add(ticket.getQueryok());
		}
		if (StringUtils.isNotEmpty(ticket.getDelayInfo())) {
			buf.append(" and delayInfo=?");
			params.add(ticket.getDelayInfo());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanDepTime())) {
			buf.append(" and planDepTime=?");
			params.add(ticket.getPlanDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getPlanArrTime())) {
			buf.append(" and planArrTime=?");
			params.add(ticket.getPlanArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActDepTime())) {
			buf.append(" and actDepTime=?");
			params.add(ticket.getActDepTime());
		}
		if (StringUtils.isNotEmpty(ticket.getActArrTime())) {
			buf.append(" and actArrTime=?");
			params.add(ticket.getActArrTime());
		}
		if (StringUtils.isNotEmpty(ticket.getZtms())) {
			buf.append(" and ztms=?");
			params.add(ticket.getZtms());
		}
		if (StringUtils.isNotEmpty(ticket.getCtm())) {
			buf.append(" and convert(varchar(10), ctm, 120)=?");
			params.add(ticket.getCtm());
		}
		if (ticket.getYwtype() != null) {
			buf.append(" and ywtype=?");
			params.add(ticket.getYwtype());
		}
		if (ticket.getDelayState() != null) {
			buf.append(" and delayState=?");
			params.add(ticket.getDelayState());
		}
		if (StringUtils.isNotEmpty(ticket.getMno())) {
			buf.append(" and mno=?");
			params.add(ticket.getMno());
		}
		buf.append(" order by queryok asc,id desc");
		return this.find(buf.toString(), params.toArray());
	}

}
