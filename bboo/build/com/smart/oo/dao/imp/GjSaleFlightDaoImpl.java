package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.utils.ProduceHqlUtil;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IGjSaleFlightDao;

@Repository("GjSaleFlightDaoImpl")
public class GjSaleFlightDaoImpl extends BaseDAO<GjSaleFlightEntity, String> implements IGjSaleFlightDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2784818303754655704L;

	@Override
	public List<GjSaleFlightEntity> queryByFkid(String fkid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from GjSaleFlightEntity where fkid = ");
		sb.append(fkid);
		return this.find(sb.toString());
	}


	@Override
	public List<GjSaleFlightEntity> queryByOrderNo(String fkid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from GjSaleFlightEntity where fkid = ");
		sb.append(fkid);
		sb.append(" order by departureDate,departureTime");
		return this.find(sb.toString());
	}



	@Override
	public void saveSaleFlight(GjSaleFlightEntity flight) throws Exception {
		this.save(flight);
	}


	@Override
	public void updateById(GjSaleFlightEntity gjSaleFlightEntity) throws Exception {
		String hql=ProduceHqlUtil.getUpdateByIdHql(gjSaleFlightEntity);
		this.executeHql(hql);
	}


	@Override
	public GjSaleFlightEntity queryById(String id) throws Exception {
		String hql = "from GjSaleFlightEntity where id='"+id+"'";
		return this.findUnique(hql);
	}
}
