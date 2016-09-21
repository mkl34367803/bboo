package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.ShareFlightEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IShareFlightDao;

@Repository("BBOOShareFlightDaoImpl")
public class ShareFlightDaoImpl extends BaseDAO<ShareFlightEntity, Integer>
		implements IShareFlightDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4714937664896024617L;

	public ShareFlightDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateShareCabin() throws Exception {
		// 匹配空数据
		StringBuffer buf = new StringBuffer(
				"update t_share_flight  set share_cabin=c.share_cabin from t_cabin_map c where t_share_flight.carrier=c.carrier and t_share_flight.cabin=c.cabin and t_share_flight.share_code=c.share_code ");
		StringBuffer whereBuf = new StringBuffer();
		whereBuf.append(" and ISNULL(c.share_nums,'')='' ");
		whereBuf.append(" and ISNULL(c.dep_code,'')='' ");
		whereBuf.append(" and ISNULL(c.arr_code,'')='' ");
		this.executeSql(buf.toString() + " " + whereBuf.toString());
		// 匹配航线
		whereBuf = new StringBuffer();
		whereBuf.append(" and charindex(t_share_flight.dep_code,c.dep_code)>0 ");
		whereBuf.append(" and charindex(t_share_flight.arr_code,c.arr_code)>0 ");
		this.executeSql(buf.toString() + " " + whereBuf.toString());
		// 匹配航班号
		whereBuf = new StringBuffer();
		whereBuf.append(" and charindex(t_share_flight.share_no,c.share_nums)>0 ");
		this.executeSql(buf.toString() + " " + whereBuf.toString());
	}

	@Override
	public void updatePrice() throws Exception {
		StringBuffer buf = new StringBuffer(
				"update t_share_flight set price=p.price from (select top 2000000 * from t_air_price order by price desc) p where t_share_flight.share_code=p.carrier and t_share_flight.share_cabin=p.cabin and t_share_flight.dep_code=p.dep_code and t_share_flight.arr_code=p.arr_code");
		this.executeSql(buf.toString());
	}

	@Override
	public Integer saveData(ShareFlightEntity e) throws Exception {
		// TODO Auto-generated method stub
		return this.save(e);
	}

	@Override
	public List<ShareFlightEntity> findShareDatas(ShareFlightEntity e)
			throws Exception {
		// TODO Auto-generated method stub
		return this
				.find("from ShareFlightEntity where 1=1 order by carrier,depCode,arrCode,id desc");
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		this.executeSql("truncate table t_share_flight");
	}

}
