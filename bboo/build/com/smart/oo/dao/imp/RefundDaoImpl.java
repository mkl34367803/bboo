package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.RefundEntity;
import com.smart.comm.utils.DateUtil;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IRefundDao;
import com.smart.oo.from.RefundVo;
import com.smart.utils.StringUtils;

@Repository("BBOORefundDaoImpl")
public class RefundDaoImpl extends BaseDAO<RefundEntity, String> implements IRefundDao {

	private static final long serialVersionUID = 145371617376123914L;

	@Override
	public List<RefundEntity> queryRefunds(RefundEntity entity)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("from RefundEntity where 1=1 ");
		/*if (StringUtils.isNotEmpty(entity.getFkid())) {
			sb.append(" and fkid=?");
			params.add(entity.getFkid());
		}*/
		if (StringUtils.isNotEmpty(entity.getPsgid())) {
			sb.append(" and psgid=?");
			params.add(entity.getPsgid());
		}
		if (StringUtils.isNotEmpty(entity.getFltid())) {
			sb.append(" and fltid=?");
			params.add(entity.getFltid());
		}
		if (StringUtils.isNotEmpty(entity.getId())) {
			sb.append(" and id=?");
			params.add(entity.getId());
		}
		if (StringUtils.isNotEmpty(entity.getFkrid())) {
			sb.append(" and fkrid=?");
			params.add(entity.getFkrid());
		}
		if (entity.getIslock() != null) {
			sb.append(" and islock=?");
			params.add(entity.getIslock());
		}
		if (StringUtils.isNotEmpty(entity.getCreateTime())) {
			sb.append(" and createTime=?");
			params.add(entity.getCreateTime());
		}
		return this.find(sb.toString(), params.toArray());
	}

	@Override
	public void saveRefound(RefundEntity entity) throws Exception {
		this.save(entity);
	}

	@Override
	public void updateRefound(RefundEntity entity) throws Exception {
		this.update(entity);
	}

	@Override
	public void deleteRefound(String id) throws Exception {
		this.delete(id);
	}

	@Override
	public List<Object[]> queryByFkrid(String fkrid) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select ref.* from t_refund ref");
		sb.append(" left join t_base_refund br on br.id=ref.fkrid");
		sb.append("  where br.id='"+fkrid+"'");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> queryByBaseRefund(BaseRefundEntity br) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("select ref.* from t_refund ref");
		sb.append(" left join t_base_refund br on br.id=ref.fkrid");
		sb.append(" where 1=1");
		if (StringUtils.isNotEmpty(br.getFkid())) {
			sb.append(" and br.fkid = ?");
			params.add(br.getFkid());
		}
		if (null != br.getState()) {
			sb.append(" and br.state = ?");
			params.add(br.getState());
		}
		if (StringUtils.isNotEmpty(br.getOrderStatus())) {
			sb.append(" and br.order_status = ?");
			params.add(br.getOrderStatus());
		}
		return this.findBySQL(sb.toString(), params.toArray());
	}

	@Override
	public List<Object[]> downloadRefund(RefundVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("select bo.purchase_place_ch,");
		sb.append("so.distributor_ch,so.shop_name_ch,so.order_no,so.create_time orderCreateTime,so.flight_type,so.pnr_code,");
		sb.append("sp.name,sp.age_type,sp.eticket_num,sp.add_time,");
		sb.append("sf.carrier,sf.flight_num,sf.share_num,sf.cabin,sf.act_cabin,sf.act_fltno,sf.dep_aircode,sf.arr_aircode,sf.departure_date,sf.departure_time,");
		sb.append("br.operator,br.isdelay,br.pcount,br.create_time refundTime,br.order_status,");
		sb.append("ref.face,ref.base_face,ref.yq,ref.tax,ref.rate,ref.fee,ref.refund,ref.act_refund,");
		sb.append("ref.face2,ref.base_face2,ref.yq2,ref.tax2,ref.rate,ref.fee2,ref.refund2,ref.act_refund2");
		sb.append(" from t_refund ref");
		sb.append(" left join t_base_refund br on br.id=ref.fkrid");
		sb.append(" left join t_gj_saleorder so on so.id=br.fkid");
		sb.append(" left join t_gj_salepassenger sp on sp.id=ref.psgid");
		sb.append(" left join t_gj_saleflight sf on sf.id=ref.fltid");
		sb.append(" left join t_gj_buyorder bo on bo.id=so.id");
		sb.append(" where 1=1");
		if (StringUtils.isNotEmpty(vo.getStartRefundDate()) && StringUtils.isNotEmpty(vo.getEndRefundData())) {
			sb.append(" and br.create_time >= ? and br.create_time < ?");
			params.add(vo.getStartRefundDate());
			params.add(DateUtil.stringDatePlusOne(vo.getEndRefundData()));
		}
		if (null != vo.getState()) {
			sb.append(" and br.state = ?");
			params.add(vo.getState());
		}
		if (StringUtils.isNotEmpty(vo.getOrderStatus())) {
			sb.append(" and br.order_status = ?");
			params.add(vo.getOrderStatus());
		}
		if (StringUtils.isNotEmpty(vo.getStartDepartureDate()) && StringUtils.isNotEmpty(vo.getEndDepartureDate())) {
			sb.append(" and sf.departure_date >= ? and sf.departure_date < ?");
			params.add(vo.getStartDepartureDate());
			params.add(DateUtil.stringDatePlusOne(vo.getEndDepartureDate()));
		}
		if (StringUtils.isNotEmpty(vo.getDistributor())) {
			sb.append(" and so.distributor = ?");
			params.add(vo.getDistributor());
		}
		sb.append(" order by sf.departure_date");
		return this.findBySQL(sb.toString(), params.toArray());
	}


	
}
