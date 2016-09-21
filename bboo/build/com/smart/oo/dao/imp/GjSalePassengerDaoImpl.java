package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.GjSalePassengerEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IGjSalePassengerDao;

@Repository("GjSalePassengerDaoImpl")
public class GjSalePassengerDaoImpl extends BaseDAO<GjSalePassengerEntity, String> implements IGjSalePassengerDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1082159934491656342L;

	@Override
	public List<GjSalePassengerEntity> queryByFkid(String fkid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from GjSalePassengerEntity where fkid = ");
		sb.append(fkid);
		return this.find(sb.toString());
	}


	@Override
	public void saveSalePassenger(GjSalePassengerEntity gjSalePassengerEntity)
			throws Exception {
		this.save(gjSalePassengerEntity);
	}



	@Override
	public void passadd(GjSalePassengerEntity passentity) throws SqlException {
		this.save(passentity);
		
	}



	@Override
	public boolean updateTicketNum(String id, String eticketNum) throws Exception {
		String hql = "update GjSalePassengerEntity set eticketNum='"+eticketNum+"' where id='"+id+"'";			
		if(this.executeHql(hql) <= 0){
			return false;
		}
		return true;
	}


	@Override
	public List<GjSalePassengerEntity> queryByFkids(List<String> fkids) throws Exception {
		StringBuffer stringBuffer=new StringBuffer();
		for(int i=0;i<fkids.size();i++){
			stringBuffer.append("'"+fkids.get(i)+"'");
			if(i!=fkids.size()-1){
				stringBuffer.append(",");
			}
		}
		List<Object[]> passengers= this.findBySQL("select id,name,card_num from t_gj_salepassenger where fkid in("+stringBuffer.toString()+")");
		List<GjSalePassengerEntity> gjSalePassengerEntities=null;
		if(passengers!=null&&passengers.size()>0){
			gjSalePassengerEntities=new ArrayList<GjSalePassengerEntity>();
			for(Object[] objects:passengers){
				GjSalePassengerEntity gjSalePassengerEntity=new GjSalePassengerEntity();
				gjSalePassengerEntity.setId(objects[0].toString());
				if(objects[1]!=null){
					gjSalePassengerEntity.setName(objects[1].toString());
				}
				if(objects[2]!=null){
					gjSalePassengerEntity.setCardNum(objects[2].toString());
				}
				gjSalePassengerEntities.add(gjSalePassengerEntity);
			}
			
		}
		return gjSalePassengerEntities;
	}


	@Override
	public void deleteById(String id) throws Exception {
		this.executeHql("delete GjSalePassengerEntity where id='"+id+"'");
	}


}
