package com.smart.oo.dao.imp;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.GjBuyFlightEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IGjBuyFlightDao;
@Repository("GjBuyFlightDaoImpl")
public class GjBuyFlightDaoImpl extends BaseDAO<GjBuyFlightEntity, String> implements IGjBuyFlightDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String saveFlight(GjBuyFlightEntity gjBuyFlightEntity) throws Exception {
		return this.save(gjBuyFlightEntity);
	}

}
