package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.utils.BBOOConstants;
import com.smart.comm.utils.DateUtil;
import com.smart.comm.utils.ProduceHqlUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.entity.User;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IGjSaleOrderDao;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.from.RefundVo;
import com.smart.oo.from.SaleOrderParamVo;
import com.smart.oo.vo.OrderAndPassengerParamVo;
import com.smart.oo.vo.SaleOrderAndPassengerVo;
import com.smart.utils.StringUtils;

@Repository("GjSaleOrderDaoImpl")
public class GjSaleOrderDaoImpl extends BaseDAO<GjSaleOrderEntity, String> implements IGjSaleOrderDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7746396190235757978L;

	public GjSaleOrderDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<GjSaleOrderEntity> queryByOrderNo(String orderNo) throws Exception {
		return this.find("from GjSaleOrderEntity where orderNo='"+orderNo+"'");
	}
	@Deprecated
	@Override
	public List<Object[]> queryOrderSummaryByPage(Page page, User user,SaleOrderParamVo saleOrderParamEntity)
			throws Exception {
		StringBuffer hql=new StringBuffer();
//		hql.append("from GjSaleOrderEntity");
		hql.append("select top 99.9999 percent a.id,a.order_no,a.create_time,a.flight_type,b.departure_time,b.dep_aircode,b.arrival_time,b.arr_aircode,b.departure_date,b.flight_num,a.pnr_code,a.allprice,a.passenger_count,a.slf_status,a.lock_user,b.cabin,a.distributor,a.pnr_no_time,a.leave_remark,a.bill_id,a.shop_name,a.shop_name_ch,a.old_order_no,b.id as flightId,a.departure_date as order_departure_date,a.departure_time as order_departure_time from t_gj_saleorder a left join t_gj_saleflight b on a.id=b.fkid");
		if (user != null && user.getMert() != null && StringUtils.isNotEmpty(user.getMert().getMno())) {
			hql.append(" and a.mno='" + user.getMert().getMno()+"'");
		}
		if (saleOrderParamEntity != null) {
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightClass())) {
				hql.append(" and a.flight_class='" + saleOrderParamEntity.getFlightClass()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getStartDate())) {
				hql.append(" and a.create_time>='" + saleOrderParamEntity.getStartDate()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getEndDate())) {
				hql.append(" and a.create_time<='" + DateUtil.stringDatePlusOne(saleOrderParamEntity.getEndDate())+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getContactMob())) {
				hql.append(" and a.contact_mob='" + saleOrderParamEntity.getContactMob()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightType())) {
				if(saleOrderParamEntity.getFlightType().equals("1")){  //单程
					hql.append(" and a.flight_type='S'");
					
				}else if(saleOrderParamEntity.getFlightType().equals("2")){  //往返
					hql.append(" and a.flight_type='D'");
				}else if(saleOrderParamEntity.getFlightType().equals("3")){//多程
					
				}
//				hql.append(" and a.flight_type='" + saleOrderParamEntity.getFlightType()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderNo())) {
				hql.append(" and (a.order_no='" + saleOrderParamEntity.getOrderNo()+"' or a.bill_id='"+saleOrderParamEntity.getOrderNo()+"')");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getShopNameCh())) {
				hql.append(" and a.shop_name_ch='" + saleOrderParamEntity.getShopNameCh()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderStatus())) {
				hql.append(" and a.slf_status='" + saleOrderParamEntity.getOrderStatus()+"'");
			}
			//这个是从乘客信息表中查的字段
			if(StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())||StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())){
				hql.append(" and a.id in (select c.id from t_gj_saleorder c left join t_gj_salepassenger d on c.id=d.fkid ");
				StringBuffer stringBuffer=new StringBuffer();
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())) {
					stringBuffer.append(" and d.name='" + saleOrderParamEntity.getPassengerName()+"'");
				}
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())) {
					stringBuffer.append(" and d.eticket_num='" + saleOrderParamEntity.getTicketNum()+"'");
				}
				hql.append(stringBuffer.toString().replaceFirst("and", "where"));
				hql.append(")");
			}
		}
		//hql.append(" order by a.pnr_no_time,b.departure_date,b.departure_time,a.id");
		String orderString=" order by case when a.pnr_no_time is null then '9999-12-31' else a.pnr_no_time end,a.departure_date,a.departure_time,a.id";
		//hql.append(" order by case when a.pnr_no_time is null then '9999-12-31' else a.pnr_no_time end,b.departure_date,b.departure_time,a.id");
		hql.append(orderString);
		String conditionhql=hql.toString().replaceFirst("and", "where");
		conditionhql="select top 99.9999 percent * from ("+conditionhql+") as cc where cc.id not in (select aaa.id from(select distinct top  "+page.getPageSize()*(page.getStartPage()-1)+" aa.pnr_no_time,aa.order_departure_date,aa.order_departure_time,aa.id from ("+conditionhql+") as aa order by aa.pnr_no_time,aa.order_departure_date,aa.order_departure_time,aa.id) as aaa) ";
		//这里开始加分页的信息。
		String sql="select * from ("+conditionhql+") as ff where ff.id in (select ddd.id from( select distinct top "+page.getPageSize()+" dd.pnr_no_time,dd.order_departure_date,dd.order_departure_time,dd.id from ("+conditionhql+") as dd order by dd.pnr_no_time,dd.order_departure_date,dd.order_departure_time,dd.id) as ddd) "+" order by case when ff.pnr_no_time is null then '9999-12-31' else ff.pnr_no_time end,ff.departure_date,ff.departure_time,ff.id"; 
//		return this.find(str, page);
//		return this.executeSelectByHQL(str);
		 
		return this.findBySQL(sql);
	}

	@Deprecated
	@Override
	public Integer queryOrderTotal(User user, SaleOrderParamVo saleOrderParamEntity) throws Exception {
		StringBuffer sql=new StringBuffer();
		sql.append("select count(distinct a.id) from  t_gj_saleorder a");
		//sql.append(" and a.is_auto='" + BBOOConstants.GjSaleOrderEntity_isAuto_two+"'");
		if (user != null && user.getMert() != null && StringUtils.isNotEmpty(user.getMert().getMno())) {
			sql.append(" and a.mno='" + user.getMert().getMno()+"'");
		}
		if (saleOrderParamEntity != null) {
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightClass())) {
				sql.append(" and a.flight_class='" + saleOrderParamEntity.getFlightClass()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getStartDate())) {
				sql.append(" and a.create_time>='" + saleOrderParamEntity.getStartDate()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getEndDate())) {
				sql.append(" and a.create_time<='" + DateUtil.stringDatePlusOne(saleOrderParamEntity.getEndDate())+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getContactMob())) {
				sql.append(" and a.contact_mob='" + saleOrderParamEntity.getContactMob()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightType())) {
				if(saleOrderParamEntity.getFlightType().equals("1")){  //单程
					sql.append(" and a.flight_type='S'");
					
				}else if(saleOrderParamEntity.getFlightType().equals("2")){  //往返
					sql.append(" and a.flight_type='D'");
				}else if(saleOrderParamEntity.getFlightType().equals("3")){//多程
					
				}
//				hql.append(" and a.flight_type='" + saleOrderParamEntity.getFlightType()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderNo())) {
				sql.append(" and a.order_no='" + saleOrderParamEntity.getOrderNo()+"' or a.bill_id='"+saleOrderParamEntity.getOrderNo()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getShopNameCh())) {
				sql.append(" and a.shop_name_ch='" + saleOrderParamEntity.getShopNameCh()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderStatus())) {
				sql.append(" and a.slf_status='" + saleOrderParamEntity.getOrderStatus()+"'");
			}
			//这个是从乘客信息表中查的字段
			if(StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())||StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())){
				sql.append(" and a.id in (select c.id from t_gj_saleorder c left join t_gj_salepassenger d on c.id=d.fkid ");
				StringBuffer stringBuffer=new StringBuffer();
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())) {
					stringBuffer.append(" and d.name='" + saleOrderParamEntity.getPassengerName()+"'");
				}
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())) {
					stringBuffer.append(" and d.eticket_num='" + saleOrderParamEntity.getTicketNum()+"'");
				}
				sql.append(stringBuffer.toString().replaceFirst("and", "where"));
				sql.append(")");
			}
		}
		String str=sql.toString().replaceFirst("and", "where");
		return this.countBySQL(str);
	}

	@Override
	public GjSaleOrderEntity queryOrderByID(String id) throws Exception {
		return this.findUnique("from GjSaleOrderEntity so left join fetch so.flights sf left join fetch so.passengers sp where so.id='"+id+"'");
	}


	@Override
	public List<GjSaleOrderEntity> orderlist(GjSaleOrderEntity order,
			Page page, User user) throws SqlException {
		StringBuffer sbf = new StringBuffer();
		List<GjSaleOrderEntity> list = null;
		sbf.append("from GjSaleOrderEntity where 1=1");
		if (order!=null) {
			if (StringUtilsc.isEmpty(order.getAddress())) {
				sbf.append("and address ='" + order.getAddress()+"'");
			}
			if(StringUtilsc.isEmpty(order.getCabinDes())){
				sbf.append("and cabindes = '"+ order.getCabinDes()+"'");
			}
			if (StringUtilsc.isEmpty(order.getContactName())) {
				sbf.append("and contactname = '"+ order.getContactName()+"'");
			}if (StringUtilsc.isEmpty(order.getUserName())) {
				sbf.append("and username = '"+ order.getUserName()+"'");
			}
		}
		list = this.find(sbf.toString(),page);
		return list;
	}

	@Override
	public boolean updateLockUser(String id, String lockUser) throws Exception {
		// TODO Auto-generated method stub
		int result=-1;
		if(lockUser!=null&&!"".equals(lockUser)){
			result= this.executeSql("update t_gj_saleorder set lock_user='"+lockUser+"' where id="+id+" and (lock_user='"+lockUser+"' or lock_user='' or lock_user='null')");
		}else{
			result=this.executeSql("update t_gj_saleorder set lock_user='"+lockUser+"' where id="+id);
		}
		if(result>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateSlfStatus(String id, String slfStatus) throws Exception {
		String hql = "update GjSaleOrderEntity set slfStatus='"+slfStatus+"' where id='"+id+"'";
		int result = this.executeHql(hql);
		if(result>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public String saveOrder(GjSaleOrderEntity gjSaleOrderEntity) throws Exception {
		return this.save(gjSaleOrderEntity);
	}

	@Override
	public boolean updatePnrCode(String id, String pnrCode) throws Exception {
		String hql = "update GjSaleOrderEntity set pnrCode='"+pnrCode+"' where id='"+id+"'";
		GjSaleOrderEntity saleOrderEntity = this.queryOrderByID(id);
		String oldPnrCode = saleOrderEntity.getPnrCode();
		int result = this.executeHql(hql);
		if (result > 0) {
			if(StringUtilsc.isNotEmptyAndNULL(oldPnrCode)){
				this.executeHql("update GjSaleOrderEntity set oldPnrCode='"+oldPnrCode+"' where id='"+id+"'");
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cancelOrder(String id, String lockUser) throws Exception {
		int result=this.executeSql("update t_gj_saleorder set slf_status='12',lock_user='"+lockUser+"' where id="+id);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updatePnrNoTime(String id, String pnrNoTime, String leaveRemark) throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(pnrNoTime)) {
			pnrNoTime="NULL";
		}
		int result=this.executeHql("update GjSaleOrderEntity set pnrNoTime='"+pnrNoTime+"',leaveRemark='"+leaveRemark+"' where id="+id);
		if(result>0){
			return true;
		}else{
			return false;
		}
	}

	
	@Override
	public boolean updateStatus(String id, String status) throws Exception {
		String hql = "update GjSaleOrderEntity set status='"+status+"' where id='"+id+"'";
		int result = this.executeHql(hql);
		if(result>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<GjSaleOrderEntity> queryByCreateTime(String startTime, String endTime) throws Exception {
		List<Object[]> saleOrders= this.findBySQL("select id,order_no,bill_id,accountid from t_gj_saleorder where create_time>='"+startTime+"' and create_time<='"+endTime+"'");
		List<GjSaleOrderEntity> gjSaleOrderEntities=null;
		if(saleOrders!=null&&saleOrders.size()>0){
			gjSaleOrderEntities=new ArrayList<GjSaleOrderEntity>();
			for(Object[] objects:saleOrders){
				GjSaleOrderEntity gjSaleOrderEntity=new GjSaleOrderEntity();
				gjSaleOrderEntity.setId(objects[0].toString());
				gjSaleOrderEntity.setOrderNo(objects[1].toString());
				if(objects[2]!=null){
					gjSaleOrderEntity.setBillId(objects[2].toString());
				}
				if(objects[3]!=null){
					gjSaleOrderEntity.setAccountid(Integer.parseInt(objects[3].toString()));
				}
				gjSaleOrderEntities.add(gjSaleOrderEntity);
			}
		}
		return gjSaleOrderEntities;
	}

	@Override
	public List<SaleOrderAndPassengerVo> queryOrderAndPassengerByCreateTime(OrderAndPassengerParamVo orderAndPassengerParamVo) throws Exception {
		String sql="select so.id,so.order_no,so.bill_id,so.accountid,sp.id as passengerId,sp.name,sp.card_num,so.pnr_code from t_gj_saleorder so left join t_gj_salepassenger sp on so.id=sp.fkid where create_time>='"+orderAndPassengerParamVo.getStartCreateTime()+"' and create_time<='"+orderAndPassengerParamVo.getEndCreateTime()+"' and slf_status='"+orderAndPassengerParamVo.getSlfStatus()+"' and flight_class='"+orderAndPassengerParamVo.getFlightClass()+"'";
		List<Object[]> saleOrders= this.findBySQL(sql);
		List<SaleOrderAndPassengerVo> saleOrderAndPassengerVos=null;
		if(saleOrders!=null&&saleOrders.size()>0){
			saleOrderAndPassengerVos=new ArrayList<SaleOrderAndPassengerVo>();
			for(Object[] objects:saleOrders){
				SaleOrderAndPassengerVo saleOrderAndPassengerVo=new SaleOrderAndPassengerVo();
				saleOrderAndPassengerVo.setId(objects[0].toString());
				saleOrderAndPassengerVo.setOrderNo(objects[1].toString());
				if(objects[2]!=null){
					saleOrderAndPassengerVo.setBillId(objects[2].toString());
				}
				if(objects[3]!=null){
					saleOrderAndPassengerVo.setAccountId(objects[3].toString());
				}
				if(objects[4]!=null){
					saleOrderAndPassengerVo.setPassengerId(objects[4].toString());
				}
				if(objects[5]!=null){
					saleOrderAndPassengerVo.setName(objects[5].toString());
				}
				if(objects[6]!=null){
					saleOrderAndPassengerVo.setCardNum(objects[6].toString());
				}
				if(objects[7]!=null){
					saleOrderAndPassengerVo.setPnrCode(objects[7].toString());
				}
				saleOrderAndPassengerVos.add(saleOrderAndPassengerVo);
			}
		}
		return saleOrderAndPassengerVos;
	}


	@Override
	public boolean updateByID(GjSaleOrderEntity gjSaleOrderEntity)
			throws Exception {
		String hql=ProduceHqlUtil.getUpdateByIdHql(gjSaleOrderEntity);
		int i=this.executeHql(hql);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}

	@Deprecated
	@Override
	public List<Object[]> queryOrderSummaryByPage1(Page page, User user, SaleOrderParamVo saleOrderParamEntity) throws Exception {
		StringBuffer hql=new StringBuffer();
//		hql.append("from GjSaleOrderEntity");
		hql.append("select distinct case when a.pnr_no_time is null then '9999-12-31' else a.pnr_no_time end,a.departure_date,a.departure_time,a.id,a.seats from t_gj_saleorder a");
		//hql.append(" and a.is_auto='" + BBOOConstants.GjSaleOrderEntity_isAuto_two+"'");
		if (user != null && user.getMert() != null && StringUtils.isNotEmpty(user.getMert().getMno())) {
			hql.append(" and a.mno='" + user.getMert().getMno()+"'");
		}
		if (saleOrderParamEntity != null) {
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightClass())) {
				hql.append(" and a.flight_class='" + saleOrderParamEntity.getFlightClass()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getStartDate())) {
				hql.append(" and a.create_time>='" + saleOrderParamEntity.getStartDate()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getEndDate())) {
				hql.append(" and a.create_time<='" + DateUtil.stringDatePlusOne(saleOrderParamEntity.getEndDate())+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getContactMob())) {
				hql.append(" and a.contact_mob='" + saleOrderParamEntity.getContactMob()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightType())) {
				if(saleOrderParamEntity.getFlightType().equals("1")){  //单程
					hql.append(" and a.flight_type='S'");
					
				}else if(saleOrderParamEntity.getFlightType().equals("2")){  //往返
					hql.append(" and a.flight_type='D'");
				}else if(saleOrderParamEntity.getFlightType().equals("3")){//多程
					
				}
//				hql.append(" and a.flight_type='" + saleOrderParamEntity.getFlightType()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderNo())) {
				hql.append(" and (a.order_no='" + saleOrderParamEntity.getOrderNo()+"' or a.bill_id='"+saleOrderParamEntity.getOrderNo()+"')");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getShopNameCh())) {
				hql.append(" and a.shop_name_ch='" + saleOrderParamEntity.getShopNameCh()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderStatus())) {
				hql.append(" and a.slf_status='" + saleOrderParamEntity.getOrderStatus()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getPnrCode())) {
				hql.append(" and a.pnr_code='" + saleOrderParamEntity.getPnrCode() + "'");
			}
			//这个是从乘客信息表中查的字段
			if(StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())||StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())){
				hql.append(" and a.id in (select c.id from t_gj_saleorder c left join t_gj_salepassenger d on c.id=d.fkid ");
				StringBuffer stringBuffer=new StringBuffer();
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())) {
					stringBuffer.append(" and d.name='" + saleOrderParamEntity.getPassengerName()+"'");
				}
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())) {
					stringBuffer.append(" and d.eticket_num='" + saleOrderParamEntity.getTicketNum()+"'");
				}
				hql.append(stringBuffer.toString().replaceFirst("and", "where"));
				hql.append(")");
			}
		}
		//hql.append(" order by a.pnr_no_time,b.departure_date,b.departure_time,a.id");
		String orderString=" order by case when a.pnr_no_time is null then '9999-12-31' else a.pnr_no_time end,a.seats,a.departure_date,a.departure_time,a.id";
		//hql.append(" order by case when a.pnr_no_time is null then '9999-12-31' else a.pnr_no_time end,b.departure_date,b.departure_time,a.id");
		hql.append(orderString);
		String conditionhql=hql.toString().replaceFirst("and", "where");
		List<Object[]> list=  this.findBySQL(conditionhql, page);
		if(list!=null&&list.size()>0){
			StringBuffer sql=new StringBuffer();
//		hql.append("from GjSaleOrderEntity");
			//sql.append("select top 99.9999 percent a.id,a.order_no,a.create_time,a.flight_type,b.departure_time,b.dep_aircode,b.arrival_time,b.arr_aircode,b.departure_date,b.flight_num,a.pnr_code,a.allprice,a.passenger_count,a.slf_status,a.lock_user,b.cabin,a.distributor,a.pnr_no_time,a.leave_remark,a.bill_id,a.shop_name,a.shop_name_ch,a.old_order_no,b.id as flightId,a.departure_date as order_departure_date,a.departure_time as order_departure_time from t_gj_saleorder a left join t_gj_saleflight b on a.id=b.fkid");
			sql.append("select top 99.9999 percent a.id,a.order_no,a.create_time,a.flight_type,b.departure_time,b.dep_aircode,b.arrival_time,b.arr_aircode,b.departure_date,b.flight_num,a.pnr_code,a.allprice,a.passenger_count,a.slf_status,a.lock_user,b.cabin,a.distributor,a.pnr_no_time,a.leave_remark,a.bill_id,a.shop_name,a.shop_name_ch,a.old_order_no,b.id as flightId,a.policy_type,a.is_auto,a.statement,b.is_shared,a.pnr_type,b.cabin_num,a.is_lowspace,b.lowspace,a.is_new_code,a.seats from t_gj_saleorder a left join t_gj_saleflight b on a.id=b.fkid");
			String temp="";
			for(int i=0;i<list.size();i++){
				temp=temp+list.get(i)[3];
				if(i!=list.size()-1){
					temp=temp+",";
				}
			}
			sql.append(" where a.id in ("+temp+")");
			sql.append(orderString);
			return this.findBySQL(sql.toString());
		}else{
			return null;
		}
	}

	@Override
	public List<Object[]> countProfitByAirline(OrderChartsVo vo)
			throws Exception {
		return null;
	}

	@Override
	public List<Object[]> queryRefundOrders(RefundVo vo, Page page)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("select ");
		sb.append(" br.id brid,br.pcount as passengetCount,br.locker,");
		sb.append(" so.id id,so.order_no,so.create_time createTime,so.flight_type,so.pnr_code,");
		sb.append(" so.allprice,so.distributor,so.distributor_ch,so.pnr_no_time,");
		sb.append(" so.leave_remark,so.bill_id,so.shop_name,so.shop_name_ch,");
		sb.append(" so.old_order_no,so.policy_type,so.is_auto,so.statement,so.pnr_type,");
		sb.append(" so.slf_status,so.departure_date ");
		sb.append(" from t_base_refund br");
		sb.append(" left join t_gj_saleorder so on so.id=br.fkid");
		sb.append(" where 1=1");
		if (vo.getState() != null) {
			sb.append(" and br.state=? ");
			params.add(vo.getState());
		}
		if (StringUtils.isNotEmpty(vo.getOrderStatus())) {
			sb.append(" and br.order_status = ?");
			params.add(vo.getOrderStatus());
		}
		if (StringUtils.isNotEmpty(vo.getStartRefundDate()) && StringUtils.isNotEmpty(vo.getEndRefundData())) {
			sb.append(" and br.create_time >= ? and br.create_time < ?");
			params.add(vo.getStartRefundDate());
			params.add(DateUtil.stringDatePlusOne(vo.getEndRefundData()));
		}
		if (StringUtils.isNotEmpty(vo.getOrderNo())) {
			sb.append(" and so.order_no=?");
			params.add(vo.getOrderNo());
		}
		if (StringUtils.isNotEmpty(vo.getStartDate()) && StringUtils.isNotEmpty(vo.getEndDate())) {
			sb.append(" and so.departure_date >= ? and  so.departure_date < ?");
			params.add(vo.getStartDate());
			params.add(DateUtil.stringDatePlusOne(vo.getEndDate()));
		}
		if (StringUtils.isNotEmpty(vo.getCarrier())) {
			sb.append(" and so.carrier = ?");
			params.add(vo.getCarrier());
		}
		if (StringUtils.isNotEmpty(vo.getPnrCode())) {
			sb.append(" and so.pnr_code = ?");
			params.add(vo.getPnrCode());
		}
		sb.append(" order by so.departure_date");
		//return this.findBySQL(sb.toString(), params.toArray());
		return this.findBySQL(sb.toString(), params.toArray(), page);
	}

	@Override
	public Integer countRefundOrders(SaleOrderParamVo vo,
			BaseRefundEntity entity) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("select");
		sb.append(" count(*) ");
		sb.append(" from t_base_refund br");
		sb.append(" left join t_gj_saleorder so on so.id=br.fkid");
		sb.append(" where 1=1 ");
		if (entity.getState() != null) {
			sb.append(" and br.state=? ");
			params.add(entity.getState());
		}
		if (StringUtils.isNotEmpty(entity.getOrderStatus())) {
			sb.append(" and br.order_status = ?");
			params.add(entity.getOrderStatus());
		}
		if (StringUtils.isNotEmpty(vo.getOrderNo())) {
			sb.append(" and so.order_no=?");
			params.add(vo.getOrderNo());
		}
		if (StringUtils.isNotEmpty(vo.getStartDate()) && StringUtils.isNotEmpty(vo.getEndDate())) {
			sb.append(" and so.departure_date >= ? and  so.departure_date < ?");
			params.add(vo.getStartDate());
			params.add(DateUtil.stringDatePlusOne(vo.getEndDate()));
		}
		if (StringUtils.isNotEmpty(vo.getCarrier())) {
			sb.append(" and so.carrier = ?");
			params.add(vo.getCarrier());
		}
		if (StringUtils.isNotEmpty(vo.getPnrCode())) {
			sb.append(" and so.pnr_code = ?");
			params.add(vo.getPnrCode());
		}
		return this.countBySQL(sb.toString(), params.toArray());
	}
	
	public GjSaleOrderEntity getSaleOrderById(String id) throws Exception{
		return this.findUnique("from GjSaleOrderEntity so left join fetch so.flights sf left join fetch so.passengers sp where so.id='"+id+"'");
	}

	@Override
	public List<GjSaleOrderEntity > queryOrderSummaryByPage2(Page page, User user, SaleOrderParamVo saleOrderParamEntity) throws Exception {
		StringBuffer hql=new StringBuffer();
		hql.append("select distinct a.pnr_no_time,a.departure_date,a.departure_time,a.id,a.seats from t_gj_saleorder a");
		if (user != null && user.getMert() != null && StringUtils.isNotEmpty(user.getMert().getMno())) {
			hql.append(" and a.mno='" + user.getMert().getMno()+"'");
		}
		if (saleOrderParamEntity != null) {
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightClass())) {
				hql.append(" and a.flight_class='" + saleOrderParamEntity.getFlightClass()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getStartDate())) {
				hql.append(" and a.create_time>='" + saleOrderParamEntity.getStartDate()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getEndDate())) {
				hql.append(" and a.create_time<='" + DateUtil.stringDatePlusOne(saleOrderParamEntity.getEndDate())+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getContactMob())) {
				hql.append(" and a.contact_mob='" + saleOrderParamEntity.getContactMob()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightType())) {
				if(saleOrderParamEntity.getFlightType().equals("1")){  //单程
					hql.append(" and a.flight_type='S'");
				}else if(saleOrderParamEntity.getFlightType().equals("2")){  //往返
					hql.append(" and a.flight_type='D'");
				}else if(saleOrderParamEntity.getFlightType().equals("3")){//多程
					
				}
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderNo())) {
				hql.append(" and (a.order_no='" + saleOrderParamEntity.getOrderNo()+"' or a.bill_id='"+saleOrderParamEntity.getOrderNo()+"')");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getShopNameCh())) {
				hql.append(" and a.shop_name_ch='" + saleOrderParamEntity.getShopNameCh()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderStatus())) {
				hql.append(" and a.slf_status='" + saleOrderParamEntity.getOrderStatus()+"'");
			}
			if (StringUtils.isNotEmpty(saleOrderParamEntity.getPnrCode())) {
				hql.append(" and a.pnr_code='" + saleOrderParamEntity.getPnrCode() + "'");
			}
			//这个是从乘客信息表中查的字段
			if(StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())||StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())){
				hql.append(" and a.id in (select c.id from t_gj_saleorder c left join t_gj_salepassenger d on c.id=d.fkid ");
				StringBuffer stringBuffer=new StringBuffer();
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getPassengerName())) {
					stringBuffer.append(" and d.name='" + saleOrderParamEntity.getPassengerName()+"'");
				}
				if (StringUtils.isNotEmpty(saleOrderParamEntity.getTicketNum())) {
					stringBuffer.append(" and d.eticket_num='" + saleOrderParamEntity.getTicketNum()+"'");
				}
				hql.append(stringBuffer.toString().replaceFirst("and", "where"));
				hql.append(")");
			}
		}
		String orderString="";
		if(BBOOConstants.GjSaleOrderEntity_flightClass_N.equals(saleOrderParamEntity.getFlightClass())){
			orderString=" order by a.seats,a.departure_date,a.departure_time,a.id"; //国内的排序不需要pnrnotime
		}else{
			orderString=" order by a.pnr_no_time,a.seats,a.departure_date,a.departure_time,a.id";
		}
		hql.append(orderString);
		String conditionhql=hql.toString().replaceFirst("and", "where");
		List<Object[]> list=  this.findBySQL(conditionhql, page);
		if(list!=null&&list.size()>0){
			StringBuffer sql=new StringBuffer();
			sql.append("select distinct a from GjSaleOrderEntity a left join fetch a.flights sf left join fetch a.passengers sp ");
			String temp="";
			for(int i=0;i<list.size();i++){
				temp=temp+list.get(i)[3];
				if(i!=list.size()-1){
					temp=temp+",";
				}
			}
			sql.append(" where a.id in ("+temp+")");
			if(BBOOConstants.GjSaleOrderEntity_flightClass_N.equals(saleOrderParamEntity.getFlightClass())){
				sql.append(" order by a.seats,a.departureDate,a.departureTime");  //国内的不需要pnrNotime排序
			}else{
				sql.append(" order by a.pnrNoTime,a.seats,a.departureDate,a.departureTime");
			}
			return this.find(sql.toString());
		}else{
			return null;
		}
	}

	@Override
	public void deleteById(String id) throws Exception {
		this.delete(id);
	}
	
}
