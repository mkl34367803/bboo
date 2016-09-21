package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.GjSaleFlightEntity;
import com.smart.comm.entity.OrderTrendsEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IOrderTrendsDao;

@Repository("BBOOOrderTrendsDaoImpl")
public class OrderTrendsDaoImpl extends BaseDAO<OrderTrendsEntity, Integer>
		implements IOrderTrendsDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6096178519337171997L;

	public OrderTrendsDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int saveData(OrderTrendsEntity entity) throws Exception {
		// TODO Auto-generated method stub
		return this.save(entity);
	}

	@Override
	public List<GjSaleFlightEntity> readAfterTakeOffFlight(String startTime,
			String endTime) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer(
				"select f.id,f.dep_aircode,f.arr_aircode,f.departure_date,f.flight_num,f.share_num from t_gj_saleflight f left join t_order_trends t");
		buf.append(" on f.id=t.fkid");
		buf.append(" where f.arrival_date+' '+f.arrival_time<=? ");
		buf.append(" and f.arrival_date+' '+f.arrival_time>=?");
		buf.append(" and t.fkid is null");
		List<Object[]> objs = this.findBySQL(buf.toString(), new Object[] {
				endTime, startTime });
		List<GjSaleFlightEntity> list = new ArrayList<GjSaleFlightEntity>();
		if (objs != null) {
			for (Object[] obj : objs) {
				GjSaleFlightEntity f = new GjSaleFlightEntity();
				f.setId(obj[0].toString());
				f.setDepAircode(obj[1].toString());
				f.setArrAircode(obj[2].toString());
				f.setDepartureDate(obj[3].toString());
				f.setFlightNum(obj[4] != null ? obj[4].toString() : "");
				f.setShareNum(obj[5] != null ? obj[5].toString() : "");
				list.add(f);
			}
		}
		return list;
	}

}
