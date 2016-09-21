package com.smart.oo.dao.imp;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.GjBuyPassengerEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IGjBuyPassengerDao;
@Repository("GjBuyPassengerDaoImpl")
public class GjBuyPassengerDaoImpl extends BaseDAO<GjBuyPassengerEntity, String> implements IGjBuyPassengerDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String savePassenger(GjBuyPassengerEntity gjBuyPassengerEntity) throws Exception {
		return this.save(gjBuyPassengerEntity);
	}

	@Override
	public boolean updateTicketNum(String id, String eticketNum) throws Exception {
		String hql = null;
		hql = "update GjBuyPassengerEntity set eticketNum='"+eticketNum+"' where id='"+id+"'";			
		if(this.executeHql(hql) <= 0){
			return false;
		}
		return true;
	}

}
