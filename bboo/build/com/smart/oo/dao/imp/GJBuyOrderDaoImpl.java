package com.smart.oo.dao.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.smart.comm.entity.GjBuyOrderEntity;
import com.smart.comm.utils.DateUtil;
import com.smart.comm.utils.ProduceHqlUtil;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IGjBuyOrderDao;
import com.smart.oo.from.BuyOrderParamVo;
import com.smart.oo.from.OrderChartsVo;
import com.smart.oo.from.SaleOrderParamVo;
@Repository("GjBuyOrderDaoImpl")
public class GJBuyOrderDaoImpl extends BaseDAO<GjBuyOrderEntity, String> implements IGjBuyOrderDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean splitOrder() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GjBuyOrderEntity queyOrderByID(String id) throws Exception {
		return this.get(id);
	}

	@Override
	public String saveBuyOrder(GjBuyOrderEntity gjBuyOrderEntity) throws Exception {
		return this.save(gjBuyOrderEntity);
	}

	@Override
	public void updateBuyOrder(GjBuyOrderEntity gjBuyOrderEntity) throws Exception {
		this.update(gjBuyOrderEntity);
	}
	
	@Override
	public boolean updatePnrCode(String id, String pnrCode) throws Exception {
		String stringBuffer = "update GjBuyOrderEntity set pnrCode='"+pnrCode+"' where id='"+id+"'";
		int result = this.executeHql(stringBuffer);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<GjBuyOrderEntity> queryByCreateTime(String startTime, String endTime) throws Exception {
		this.findBySQL("select id from t_gj_buyorder ");
		return null;
	}
	
	@Override
	public List<Object[]> getProfitByPurchasePlace() throws Exception {
		return this.findBySQL("select sum(lr),purchase_place_ch from t_gj_buyorder group by purchase_place_ch");
	}

	@Override
	public List<Object[]> statisticTicketByPurchasePlace(OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.purchase_place_ch,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.purchase_place_ch");
		sb.append(" order by bo.purchase_place_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByCarrier(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bf.carrier,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bf.carrier");
		sb.append(" order by bf.carrier");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByOperator(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.lock_user,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.lock_user");
		sb.append(" order by bo.lock_user");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByAgeDes(OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bp.age_des,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bp.age_des");
		sb.append(" order by bp.age_des");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByDistributor(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.distributor_ch,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.distributor_ch");
		sb.append(" order by so.distributor_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByShopName(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.shop_name_ch,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.shop_name_ch");
		sb.append(" order by so.shop_name_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByCreateTime(OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select convert(varchar(10),bo.create_time,120) sj,COUNT(*) count  from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid ");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid ");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by convert(varchar(10),bo.create_time,120)");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByPurchasePlace(
			OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.purchase_place_ch,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.purchase_place_ch");
		sb.append(" order by bo.purchase_place_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByCarrier(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.carrier,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id ");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.carrier");
		sb.append(" order by so.carrier");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByOperator(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.lock_user,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.lock_user");
		sb.append(" order by bo.lock_user");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByDistributor(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.distributor_ch,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.distributor_ch");
		sb.append(" order by so.distributor_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByShopName(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.shop_name_ch,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.shop_name_ch");
		sb.append(" order by so.shop_name_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByCreateTime(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select convert(varchar(10),bo.create_time,120) sj,SUM(bo.lr) lr  from t_gj_buyorder bo");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by convert(varchar(10),bo.create_time,120)");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticSaleroomByCarrier(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.carrier,SUM(bo.print_price) saleroom from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.carrier");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicket(OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfit(OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select SUM(bo.lr) lr  from t_gj_buyorder bo");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticSaleroom(OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select SUM(bo.print_price) saleroom from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByCarrierAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bf.carrier,CONVERT(varchar(10),bo.create_time,120) ctm,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bf.carrier,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by bf.carrier");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByPurchasePlaceAndCrt(
			OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.purchase_place_ch,CONVERT(varchar(10),bo.create_time,120) ctm,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.purchase_place_ch,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by bo.purchase_place_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByOperatorAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.lock_user,CONVERT(varchar(10),bo.create_time,120) ctm,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.lock_user,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by bo.lock_user");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByAgeDesAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bp.age_des,CONVERT(varchar(10),bo.create_time,120) ctm,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bp.age_des,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by bp.age_des");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByDistributorAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.distributor_ch,CONVERT(varchar(10),bo.create_time,120) ctm,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.distributor_ch,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by so.distributor_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticTicketByShopNameAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.shop_name_ch,CONVERT(varchar(10),bo.create_time,120) ctm,COUNT(*) count from t_gj_buyorder bo");
		sb.append(" left join t_gj_buyflight bf on bo.id=bf.fkid");
		sb.append(" left join t_gj_buypassenger bp on bo.id=bf.fkid");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.shop_name_ch,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by so.shop_name_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByCarrierAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.carrier,CONVERT(varchar(10),bo.create_time,120) ctm,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id ");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.carrier,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by so.carrier");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByPurchasePlaceAndCrt(
			OrderChartsVo vo) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.purchase_place_ch,CONVERT(varchar(10),bo.create_time,120) ctm,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.purchase_place_ch,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by bo.purchase_place_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByOperatorAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select bo.lock_user,CONVERT(varchar(10),bo.create_time,120) ctm,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by bo.lock_user,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by bo.lock_user");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByDistributorAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.distributor_ch,CONVERT(varchar(10),bo.create_time,120) ctm,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.distributor_ch,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by so.distributor_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public List<Object[]> statisticProfitByShopNameAndCrt(OrderChartsVo vo)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select so.shop_name_ch,CONVERT(varchar(10),bo.create_time,120) ctm,SUM(bo.lr) lr from t_gj_buyorder bo");
		sb.append(" left join t_gj_saleorder so on so.id=bo.id");
		sb.append(" where bo.flight_class='"+vo.getFlightClass()+"'");
		sb.append(" and bo.create_time >= '"+vo.getStartCreateDate()+"' and bo.create_time < '"+DateUtil.stringDatePlusOne(vo.getEndCreateDate())+"'");
		sb.append(" group by so.shop_name_ch,CONVERT(varchar(10),bo.create_time,120)");
		sb.append(" order by so.shop_name_ch");
		return this.findBySQL(sb.toString());
	}

	@Override
	public boolean updateSlfStatus(String id, String slfStatus)
			throws Exception {
		String stringBuffer = "update GjSaleOrderEntity set slfStatus='"+slfStatus+"' where id='"+id+"'";
		int result = this.executeHql(stringBuffer);
		if(result>0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void updateById(GjBuyOrderEntity gjBuyOrderEntity) throws Exception {
		String stringBuffer=ProduceHqlUtil.getUpdateByIdHql(gjBuyOrderEntity);
		this.executeHql(stringBuffer);
	}

	@Override
	public List<Object[]> queryOrderSummaryByPage(Page page,BuyOrderParamVo saleOrderParamEntity) throws Exception {
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer.append("select a.id,a.purchase_no,a.create_time,a.purchase_place,a.flight_type,a.pnr_code,a.passenger_count,a.print_price,a.cost,a.pay_pirce,a.allprice,b.distributor,b.order_no,b.shop_name,b.shop_name_ch,b.old_order_no,a.purchase_place_ch,b.lock_user,a.slf_status,a.flight_class,a.addtime from t_gj_buyorder a,t_gj_saleorder b");
		stringBuffer.append(" where a.id=b.id");
		stringBuffer.append(" and b.mno='" + saleOrderParamEntity.getMno()+"'");
		stringBuffer.append(" and (a.slf_status='0' or a.slf_status='60' or a.slf_status='99')");
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getOrderNo())) {
			stringBuffer.append(" and (b.order_no='" + saleOrderParamEntity.getOrderNo()+"' or b.bill_id='"+saleOrderParamEntity.getOrderNo()+"')");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getPurchaseNo())) {
			stringBuffer.append(" and a.purchase_no='" + saleOrderParamEntity.getPurchaseNo()+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getStartDate())) {
			stringBuffer.append(" and a.create_time>='" + saleOrderParamEntity.getStartDate()+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getEndDate())) {
			stringBuffer.append(" and a.create_time<='" + DateUtil.stringDatePlusOne(saleOrderParamEntity.getEndDate())+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getPurchaseStartDate())) {
			stringBuffer.append(" and a.addtime>='" + saleOrderParamEntity.getPurchaseStartDate()+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getPurchaseEndDate())) {
			stringBuffer.append(" and a.addtime<='" + DateUtil.stringDatePlusOne(saleOrderParamEntity.getPurchaseEndDate())+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightClass())) {
			stringBuffer.append(" and a.flight_class='" + saleOrderParamEntity.getFlightClass()+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getFlightType())) {
			if(saleOrderParamEntity.getFlightType().equals("1")){  //单程
				stringBuffer.append(" and a.flight_type='S'");
				
			}else if(saleOrderParamEntity.getFlightType().equals("2")){  //往返
				stringBuffer.append(" and a.flight_type='D'");
			}else if(saleOrderParamEntity.getFlightType().equals("3")){//多程
				
			}
		}
		
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getSlfStatus())) {
			stringBuffer.append(" and a.slf_status='" + saleOrderParamEntity.getSlfStatus()+"'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getPnrCode())) {
			stringBuffer.append(" and a.pnr_code='" + saleOrderParamEntity.getPnrCode() + "'");
		}
		if (StringUtils.isNotEmpty(saleOrderParamEntity.getShopNameCh())) {
			stringBuffer.append(" and b.shop_name_ch='" + saleOrderParamEntity.getShopNameCh() + "'");
		}
		stringBuffer.append(" order by a.create_time");
		String sql=stringBuffer.toString();
		return this.findBySQL(sql, page);
	}

}
