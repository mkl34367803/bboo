package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.BaseRefundEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IBaseRefundDao;
import com.smart.utils.StringUtils;

@Repository("BBOOBaseRefundDaoImpl")
public class BaseRefundDaoImpl extends BaseDAO<BaseRefundEntity, String> implements IBaseRefundDao {

	private static final long serialVersionUID = -3699989841394387489L;

	@Override
	public List<BaseRefundEntity> queryList(BaseRefundEntity entity)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("from BaseRefundEntity where 1=1");
		if (StringUtils.isNotEmpty(entity.getId())) {
			sb.append(" and id=?");
			params.add(entity.getId());
		}
		if (StringUtils.isNotEmpty(entity.getFkid())) {
			sb.append(" and fkid=?");
			params.add(entity.getFkid());
		}
		if (entity.getState() != null) {
			sb.append(" and state=?");
			params.add(entity.getState());
		}
		if (StringUtils.isNotEmpty(entity.getOrderStatus())) {
			sb.append(" and orderStatus=?");
			params.add(entity.getOrderStatus());
		}
		
		return this.find(sb.toString(), params.toArray());
	}

	@Override
	public void saveEntity(BaseRefundEntity entity) throws Exception {
		this.save(entity);
	}

	@Override
	public void updateEntity(BaseRefundEntity entity) throws Exception {
		this.update(entity);
	}

	@Override
	public void deleteById(String id) throws Exception {
		this.delete(id);
	}

	@Override
	public BaseRefundEntity queryById(String id) throws Exception {
		String hql = "from BaseRefundEntity where id='"+id+"'";
		return this.findUnique(hql);
	}

	@Override
	public void updateOrderStatus(String id, String orderStatus)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("update BaseRefundEntity set orderStatus = ?");
		params.add(orderStatus);
		sb.append(" where id = ?");
		params.add(id);
		this.executeHql(sb.toString(), params.toArray());
	}

	@Override
	public List<Object[]> queryFlightVos(String id) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("select");
		sb.append(" sf.id,sf.departure_time,sf.dep_aircode,sf.arrival_time,sf.arr_aircode,sf.departure_date,sf.cabin,sf.flight_num,sf.cabin_num,sf.lowspace");
		sb.append(" from t_base_refund br");
		sb.append(" left join t_refund ref on ref.fkrid=br.id");
		sb.append(" left join t_gj_saleflight sf on sf.id=ref.fltid");
		sb.append(" where br.id=?");
		params.add(id);
		return this.findBySQL(sb.toString(), params.toArray());
	}

	@Override
	public void updateLocker(String id, String locker) throws Exception {
		List<Object> params = new ArrayList<Object>();
		String hql = "update BaseRefundEntity set locker = ? where id = ?";
		params.add(locker);
		params.add(id);
		this.executeHql(hql, params.toArray());
	}

}
