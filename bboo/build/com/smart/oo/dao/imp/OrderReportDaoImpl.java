package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.GjSaleOrderEntity;
import com.smart.comm.utils.DateUtil;
import com.smart.comm.utils.StringUtilsc;
import com.smart.framework.base.BaseDAO;
import com.smart.oo.dao.IOrderReportDao;
import com.smart.oo.from.OrderReportVo;
@Repository("BBOOOrderReportDaoImpl")
public class OrderReportDaoImpl extends BaseDAO<GjSaleOrderEntity, String> implements IOrderReportDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Object[]> getSaleOrderList(OrderReportVo vo, OrderReportVo voDate) throws Exception {

		StringBuffer sb = new StringBuffer();
		
		sb.append("select ");
		// BuyOrder表查询字段
		sb.append(" bo.id,bo.ext_orderid,bo.purchase_no,bo.old_order_no,bo.flight_type,bo.airline_count," +
				"bo.passenger_count,bo.bx_fee as bo_box_fee,bo.bx_count as bo_bx_count, bo.pnr_code as buy_pnr_code," +
				"bo.pnr_code_big as bo_pnr_code_big,bo.pay_way,bo.transaction_no,bo.create_time,bo.ticket_date," +
				"bo.flight_class,bo.purchase_place, bo.purchase_place_ch,bo.supplier,bo.cabin_des," +
				"bo.back_point,bo.after_point,bo.sticking_point,bo.reward,bo.sale_price,bo.lr,bo.allprice as bo_allprice," +
				"bo.slf_remark,bo.merchant_no as bo_merchant_no,bo.status,bo.slf_status ");
		sb.append(",");
		// BuyPassenter表查询字段
		sb.append("bp.id as bp_id,bp.name,bp.gender,bp.age_type,bp.age_des,bp.eticket_num,bp.print_price as bp_print_price," +
				"bp.oil_fee as bp_oil_free,bp.tax_fee as bp_tax_fee,bp.pay_pirce as bp_pay_pirce,bp.cost as bp_cost ");
		sb.append(",");
		// SaleOrder表查询字段
		sb.append("sale.ext_oid,sale.order_no,sale.policy_type,sale.adult_price,sale.adult_tax,sale.child_tax,sale.child_price,sale.so_bx_fee," +
				"sale.so_pnr_code,sale.so_pnr_code_big,sale.bill_id,sale.shop_name,sale.shop_name_ch,sale.distributor," +
				"sale.distributor_ch,sale.rt_offno,sale.open_status,sale.open_des,sale.buy_price,sale.policy_id,sale.policy_code," +
				"sale.product_source,sale.operator,sale.old_pnr_code,sale.so_old_allprice,sale.allprice," +
				"sale.account_info,sale.mno,sale.journey_sheet_price ,sale.lock_user,sale.statement ");
		sb.append(",");
		// SalePassenger表查询字段
		sb.append("sale.bx_count,sale.ins_no,sale.cost,sale.sp_oil_fee,sale.sp_tax_fee,sale.price,sale.commission,sale.sp_print_price," +
				"sale.sp_price ");
		sb.append(",");
		// SaleFlight表查询字段
		sb.append("sale.sequence,sale.carrier,sale.air_code_ch,sale.flight_num,sale.cabin,sale.dep_aircode,sale.dep_aircode_ch," +
				"sale.arr_aircode,sale.arr_aircode_ch,sale.departure_date,sale.departure_time,sale.arrival_date,sale.arrival_time,sale.segment_type,sale.add_time,sale.self_print_price ");
		
		sb.append(" from t_gj_buyorder bo " +
				"inner join t_gj_buyflight bf on bo.id=bf.fkid " +
				"inner join t_gj_buypassenger bp on bo.id=bp.fkid ");
		sb.append("inner join ( ");
		sb.append("select ");
		// SaleOrder表查询字段
		sb.append("so.id,")
			.append("so.ext_oid,so.order_no,so.policy_type,so.adult_price,so.adult_tax,so.child_tax,so.child_price,so.bx_fee as so_bx_fee," +
					"so.pnr_code as so_pnr_code,so.pnr_code_big as so_pnr_code_big,so.bill_id,so.shop_name,so.shop_name_ch," +
					"so.distributor,so.distributor_ch,so.rt_offno,so.open_status,so.open_des,so.buy_price,so.policy_id,so.policy_code," +
					"so.product_source,so.operator,so.status as so_status,so.slf_status as so_slf_status,so.old_pnr_code,so.old_allprice as so_old_allprice,so.allprice," +
					"so.account_info,so.mno,so.journey_sheet_price,so.lock_user,so.statement ");
		sb.append(",");
		// SalePassenger表查询字段
		sb.append("sp.bx_count,sp.ins_no,sp.cost,sp.oil_fee as sp_oil_fee,sp.tax_fee as sp_tax_fee,sp.price,sp.commission," +
				"sp.print_price as sp_print_price,sp.id as sp_id,sp.price as sp_price ");
		sb.append(",");
		// SaleFlight表查询字段
		sb.append("sf.sequence,sf.carrier,sf.air_code_ch,sf.flight_num,sf.cabin,sf.dep_aircode,sf.dep_aircode_ch,sf.arr_aircode," +
				"sf.arr_aircode_ch,sf.departure_date,sf.departure_time,sf.arrival_date,sf.arrival_time,sf.segment_type,sf.add_time," +
				"sf.self_print_price,sf.id as sf_id ");
		sb.append("from t_gj_saleorder so ");
		sb.append(" inner join t_gj_saleflight sf on so.id=sf.fkid  ");
		sb.append(" inner join t_gj_salepassenger sp on so.id=sp.fkid ");
		
		if(vo != null){
			sb.append(" where 1=1");
			if(StringUtilsc.isNotEmpty(vo.getDistributor())){
				sb.append(" and so.distributor = '"+vo.getDistributor()+"'");
			}
			if (voDate != null) {
				if(StringUtilsc.isNotEmpty(vo.getCreateTime()) && StringUtilsc.isNotEmpty(voDate.getCreateTime())){
					sb.append(" and so.create_time >= '"+vo.getCreateTime()+"' and so.create_time < '"+DateUtil.stringDatePlusOne(voDate.getCreateTime())+"'");
				}
				if(StringUtilsc.isNotEmpty(vo.getTicketDate()) && StringUtilsc.isNotEmpty(voDate.getTicketDate())){
					sb.append(" and so.slf_addtime >= '"+vo.getTicketDate()+"' and so.slf_addtime < '"+DateUtil.stringDatePlusOne(voDate.getTicketDate())+"'");
				}
				if(StringUtilsc.isNotEmpty(vo.getDepartureDate()) && StringUtilsc.isNotEmpty(voDate.getDepartureDate())){
					sb.append(" and sf.departure_date >= '"+vo.getDepartureDate()+"' and sf.departure_date < '"+DateUtil.stringDatePlusOne(voDate.getDepartureDate())+"'");
				}
			}
		}
		
		sb.append(") as sale on sale.id=bo.id and sale.sp_id=bp.id and sale.sf_id=bf.id ");
		
		return this.findBySQL(sb.toString());
	}



}
