package com.smart.oo.dao.imp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.FinanceSaleEntity;
import com.smart.comm.utils.DateUtil;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IFinanceSaleDao;
import com.smart.utils.StringUtils;
@Repository("FinanceSaleDaoImpl")
@SuppressWarnings("all")
public class FinanceSaleDaoImpl extends BaseDAO implements IFinanceSaleDao{
	private static final long serialVersionUID = 1L;
	@Override
	public void savePaserExcelDatas(FinanceSaleEntity fs) throws SqlException {	
		this.save(fs); 
	}
	
	/**
	 * 根据统计利润
	 */
	@Override
	public  List<Object[]> statisticsProfit(FinanceSaleEntity fs, FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception{
		//	// num,cai_gao_projo,money,hou_fan,yong_jin,sure_price, kuai_di,kuai_di_cheng_ben 
		String sql=" select count(*) as 张数," +
				   " cai_gao_projo ," +
				   " sum(money) as 采购价," +
				   " sum(hou_fan) as 后返," +
				   " sum(yong_jin) as 佣金," +
				   " sum(sure_price) as surePrice,"+
				   " sum(kuai_di) as 快递,"+ 
				   " sum(kuai_di_cheng_ben) as 快递成本,"+   
				   " sum(shi_ji_cheng_ben) as 实际成本," +//实际成本=采购-后返+佣金
				   " sum(li_run) as 利润";//利润=机票实收+快递费-快递成本-实际成本
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		sb.append(" from t_finance_sale where 1=1");
		if (StringUtils.isNotEmpty(fs.getFileno())) {
			sb.append(" and fileno='"+fs.getFileno()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getXiaoShouPojo())) {
			sb.append(" and xiao_shou_pojo='"+fs.getXiaoShouPojo()+"'");
		}
		if (null != xiaoShouProjos && xiaoShouProjos.length > 0) {
			  for (int i = 0; i < xiaoShouProjos.length; i++) {
				if (i == 0) {
					sb.append("  and  xiao_shou_pojo='"+xiaoShouProjos[0]+"'");
				} else {
					sb.append("  or  xiao_shou_pojo='"+xiaoShouProjos[i]+"'");
				}
			}
		  }
		if (StringUtils.isNotEmpty(fs.getOrderNO())) {
			sb.append(" and orderno='"+fs.getOrderNO()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getFareNO())) {
			sb.append(" and fareno='"+fs.getFareNO()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getCaiGaoProjo())) {
			sb.append(" and cai_gao_projo='"+fs.getCaiGaoProjo()+"'");
		} else {
			sb.append(" and cai_gao_projo != 'null'");
		}
		if (StringUtils.isNotEmpty(fs.getChuaPiaoRen())) {
			sb.append(" and chua_piao_ren='"+fs.getChuaPiaoRen()+"'");
		}
		
		if (null != fs2) {
			if (StringUtils.isNotEmpty(fs.getChuPiaoDate()) && StringUtils.isNotEmpty(fs2.getChuPiaoDate())) {
				sb.append(" and chu_piao_date >= '"+fs.getChuPiaoDate()+"' and chu_piao_date<'"+DateUtil.stringDatePlusOne(fs2.getChuPiaoDate())+"'");
			}
			if (StringUtils.isNotEmpty(fs.getFightDate()) && StringUtils.isNotEmpty(fs2.getFightDate())) {
				sb.append(" and fight_date between '"+fs.getFightDate()+"' and '"+fs2.getFightDate()+"'");
			}
		}
		
		if (StringUtils.isNotEmpty(fs.getMno())) {
			sb.append(" and mno='"+fs.getMno()+"'");
		}
		sb.append(" group by cai_gao_projo order by cai_gao_projo");
		return this.findBySQL(sb.toString());
	}
 /**
  * 条件查询分页展示
  */
public  List<FinanceSaleEntity> findFinanceSaleByCondition(FinanceSaleEntity fs,Page p,FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception{
	  List<Object> params=new ArrayList<Object>();
	  StringBuffer sbf=new StringBuffer("  from FinanceSaleEntity where 1=1");
	  if(StringUtils.isNotEmpty(fs.getXiaoShouPojo())){
		  sbf.append("  and  xiaoShouPojo=?");  //销售平台
		  params.add(fs.getXiaoShouPojo());
	  }
	  if (null != xiaoShouProjos && xiaoShouProjos.length > 0) {
		  for (int i = 0; i < xiaoShouProjos.length; i++) {
			if (i == 0) {
				sbf.append("  and  xiaoShouPojo=?");  //销售平台
				params.add(xiaoShouProjos[0]);
			} else {
				sbf.append("  or  xiaoShouPojo=?");  //销售平台
				params.add(xiaoShouProjos[i]);
			}
		}
	  }
	  if(StringUtils.isNotEmpty(fs.getFareNO())){  //票号
		  sbf.append("  and  fareNO=?");
		  params.add(fs.getFareNO());
	  }
	  //出票日期 
	  if(StringUtils.isNotEmpty(fs.getChuPiaoDate()) && StringUtils.isNotEmpty(fs2.getChuPiaoDate())){
		  sbf.append("  and  chuPiaoDate >= ?   and chuPiaoDate < ?");
		  params.add(fs.getChuPiaoDate());
		  params.add(DateUtil.stringDatePlusOne(fs2.getChuPiaoDate()));
	  }  
	   //起飞时间fightDate
	  if(StringUtils.isNotEmpty(fs.getFightDate()) && StringUtils.isNotEmpty(fs2.getFightDate())){
		  sbf.append("  and  fightDate   between   ?    and   ?" );
		  params.add(fs.getFightDate());
		  params.add(fs2.getFightDate());
	  }
	  if(StringUtils.isNotEmpty(fs.getChuaPiaoRen())){//出票人
		  sbf.append("  and chuaPiaoRen=?");
		  params.add(fs.getChuaPiaoRen());
	  }
	  if(StringUtils.isNotEmpty(fs.getCaiGaoProjo())){  //采购平台
		  sbf.append("  and caiGaoProjo=?");
		  params.add(fs.getCaiGaoProjo());
	  }
	  if(StringUtils.isNotEmpty(fs.getOrderNO())){  //订单号
		  sbf.append("  and orderNO=?");
		  params.add(fs.getOrderNO());
	  }
	  if(StringUtils.isNotEmpty(fs.getFileno())){  //文件号
		  sbf.append("  and fileno=?");
		  params.add(fs.getFileno());
	  }
	  if(StringUtils.isNotEmpty(fs.getMno())){ //商务号
		  sbf.append("  and mno=?");
		  params.add(fs.getMno());
	  } 
	  sbf.append("  order by id desc");
	  return this.find(sbf.toString(), params.toArray(), p);
	  
  }
  /**
   * 根据选择的字段下载数据
   */
public List<FinanceSaleEntity> downloadChooseData(FinanceSaleEntity f)  throws Exception{
	  StringBuffer  sbf=new StringBuffer("from FinanceSaleEntity where 1=1");
	  return this.find(sbf.toString());
  }

	@Override
	public List<FinanceSaleEntity> download(FinanceSaleEntity fs,
			FinanceSaleEntity fs2) throws Exception {
		List<Object> params=new ArrayList<Object>();
		  StringBuffer sbf=new StringBuffer("  from FinanceSaleEntity where 1=1");
		  if(StringUtils.isNotEmpty(fs.getXiaoShouPojo())){
			  sbf.append("  and  xiaoShouPojo=?");  //销售平台
			  params.add(fs.getXiaoShouPojo());
		  }
		  if(StringUtils.isNotEmpty(fs.getFareNO())){  //票号
			  sbf.append("  and  fareNO=?");
			  params.add(fs.getFareNO());
		  }
		  //出票日期 
		  if (StringUtils.isNotEmpty(fs2.getChuPiaoDate()) && StringUtils.isNotEmpty(fs2.getChuPiaoDate())) {
			  sbf.append("  and  chuPiaoDate >=  ?   and  chuPiaoDate < ?");
			  params.add(fs.getChuPiaoDate());
			  params.add(DateUtil.stringDatePlusOne(fs2.getChuPiaoDate()));
		  } else {
			  sbf.append("  and  chuPiaoDate=?");
			  params.add(fs.getChuPiaoDate());
		  }
		   //起飞时间fightDate
		  if (StringUtils.isNotEmpty(fs2.getFightDate()) && StringUtils.isNotEmpty(fs2.getFightDate())) {
			  sbf.append("  and  fightDate   between  ?   and   ?");
			  params.add(fs.getFightDate());
			  params.add(fs2.getFightDate());
		  }
		  if(StringUtils.isNotEmpty(fs.getChuaPiaoRen())){//出票人
			  sbf.append("  and chuaPiaoRen=?");
			  params.add(fs.getChuaPiaoRen());
		  }
		  if(StringUtils.isNotEmpty(fs.getCaiGaoProjo())){  //采购平台
			  sbf.append("  and caiGaoProjo=?");
			  params.add(fs.getCaiGaoProjo());
		  }
		  if(StringUtils.isNotEmpty(fs.getOrderNO())){  //订单号
			  sbf.append("  and orderNO=?");
			  params.add(fs.getOrderNO());
		  }
		  if(StringUtils.isNotEmpty(fs.getFileno())){  //文件号
			  sbf.append("  and fileno=?");
			  params.add(fs.getFileno());
		  }
		  if(StringUtils.isNotEmpty(fs.getFileno())){  //文件号
			  sbf.append("  and fileno=?");
			  params.add(fs.getFileno());
		  }
		  if(StringUtils.isNotEmpty(fs.getMno())){ //商务号
			  sbf.append("  and mno=?");
			  params.add(fs.getMno());
		  }
		  
		  sbf.append("  order by fightDate");
		return this.find(sbf.toString(), params.toArray());
	}

	@Override
	public List<FinanceSaleEntity> getCount(FinanceSaleEntity fs,FinanceSaleEntity fs2, String[] xiaoShouProjos) throws Exception {
		List<Object> params=new ArrayList<Object>();
		  StringBuffer sbf=new StringBuffer("  from FinanceSaleEntity where 1=1");
		  if(StringUtils.isNotEmpty(fs.getXiaoShouPojo())){
			  sbf.append("  and  xiaoShouPojo=?");  //销售平台
			  params.add(fs.getXiaoShouPojo());
		  }
		  if (null != xiaoShouProjos && xiaoShouProjos.length > 0) {
			  for (int i = 0; i < xiaoShouProjos.length; i++) {
				if (i == 0) {
					sbf.append("  and  xiaoShouPojo=?");  //销售平台
					params.add(xiaoShouProjos[0]);
				} else {
					sbf.append("  or  xiaoShouPojo=?");  //销售平台
					params.add(xiaoShouProjos[i]);
				}
			}
		  }
		  if(StringUtils.isNotEmpty(fs.getFareNO())){  //票号
			  sbf.append("  and  fareNO=?");
			  params.add(fs.getFareNO());
		  }
		  //出票日期 
		  if(StringUtils.isNotEmpty(fs.getChuPiaoDate()) && StringUtils.isNotEmpty(fs2.getChuPiaoDate())){
			  sbf.append("  and  chuPiaoDate >=  ?   and  chuPiaoDate < ?");
			  params.add(fs.getChuPiaoDate());
			  params.add(DateUtil.stringDatePlusOne(fs2.getChuPiaoDate()));
		  }  
		   //起飞时间fightDate
		  if(StringUtils.isNotEmpty(fs.getFightDate()) && StringUtils.isNotEmpty(fs2.getFightDate())){
			  sbf.append("  and  fightDate >= ? and  fightDate < ?" );
			  params.add(fs.getFightDate());
			  params.add(DateUtil.stringDatePlusOne(fs2.getFightDate()));
		  }
		  if(StringUtils.isNotEmpty(fs.getChuaPiaoRen())){//出票人
			  sbf.append("  and chuaPiaoRen=?");
			  params.add(fs.getChuaPiaoRen());
		  }
		  if(StringUtils.isNotEmpty(fs.getCaiGaoProjo())){  //采购平台
			  sbf.append("  and caiGaoProjo=?");
			  params.add(fs.getCaiGaoProjo());
		  }
		  if(StringUtils.isNotEmpty(fs.getOrderNO())){  //订单号
			  sbf.append("  and orderNO=?");
			  params.add(fs.getOrderNO());
		  }
		  if(StringUtils.isNotEmpty(fs.getFileno())){  //文件号
			  sbf.append("  and fileno=?");
			  params.add(fs.getFileno());
		  }
		  if(StringUtils.isNotEmpty(fs.getMno())){ //商务号
			  sbf.append("  and mno=?");
			  params.add(fs.getMno());
		  }
		  sbf.append("  order by id desc");
		return this.find(sbf.toString(), params.toArray());
	}

	@Override
	public void deleteByFileNo(String fileno, String mno) throws Exception {
	    String hql = "delete FinanceSaleEntity where fileno='"+fileno+"' and mno='"+mno+"'";
	    this.executeHql(hql);
	}

	@Override
	public List<FinanceSaleEntity> queryByOrderNo(String orderNo, String mno) throws Exception {
		String hql = "from FinanceSaleEntity where fileno='"+orderNo+"' and mno='"+"'"+mno+"'";;
		return this.find(hql);
	}

	@Override
	public void updateFinanceSale(FinanceSaleEntity financeSaleEntity)
			throws Exception {
		this.update(financeSaleEntity);
	}

	@Override
	public List<String> queryCaiGouPojos(FinanceSaleEntity fs) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct caiGaoProjo from FinanceSaleEntity where 1=1");
		if (StringUtils.isNotEmpty(fs.getXiaoShouPojo())) {
			sb.append(" and xiaoShouPojo='"+fs.getXiaoShouPojo()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getMno())) {
			sb.append(" and mno='"+fs.getMno()+"'");
		}
		sb.append(" order by caiGaoProjo");
		return this.find(sb.toString());
	}

	@Override
	public List<String> queryXiaoShouPojos(FinanceSaleEntity fs) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select distinct xiaoShouPojo from FinanceSaleEntity where 1=1");
		if (StringUtils.isNotEmpty(fs.getCaiGaoProjo())) {
			sb.append(" and caiGaoProjo='"+fs.getCaiGaoProjo()+"'");
		}
		sb.append(" and mno='"+fs.getMno()+"'");
		sb.append(" order by xiaoShouPojo");
		return this.find(sb.toString());
	}

	@Override
	public List<Object> getProfitBySalePojo(FinanceSaleEntity fs,
			FinanceSaleEntity fs2, String[] caigouProjos) throws Exception {
		String sql=" select   count(*)  as num ," +
				   " xiao_shou_pojo as xiaoShou ," +
				   " SUM(money)  as caiGouPrice ," +
				   " Sum(hou_fan) as houFan ," +
				   " SUM(yong_jin)  as yongJin," +
				   " Sum(sure_price)  as surePrice,"+
				   " SUM(kuai_di)  as kuaiDi,"+ 
				   " SUM(kuai_di_cheng_ben)  as kuaiDiChengBen,"+   
				   " SUM(money - hou_fan + yong_jin) as 实际成本," +//实际成本=采购-后返+佣金
				   " SUM(sure_price + kuai_di - kuai_di_cheng_ben - (money - hou_fan + yong_jin) ) as 利润";//利润=机票实收+快递费-快递成本-实际成本
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		sb.append(" from t_finance_sale where 1=1");
		if (StringUtils.isNotEmpty(fs.getFileno())) {
			sb.append(" and fileno='"+fs.getFileno()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getXiaoShouPojo())) {
			sb.append(" and xiao_shou_pojo='"+fs.getXiaoShouPojo()+"'");
		}
		if (null != caigouProjos && caigouProjos.length > 0) {
			  for (int i = 0; i < caigouProjos.length; i++) {
				if (i == 0) {
					sb.append("  and  cai_gao_projo='"+caigouProjos[0]+"'");
				} else {
					sb.append("  or  cai_gao_projo='"+caigouProjos[i]+"'");
				}
			}
		  }
		if (StringUtils.isNotEmpty(fs.getOrderNO())) {
			sb.append(" and orderno='"+fs.getOrderNO()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getFareNO())) {
			sb.append(" and fareno='"+fs.getFareNO()+"'");
		}
		if (StringUtils.isNotEmpty(fs.getXiaoShouPojo())) {
			sb.append(" and xiao_shou_pojo='"+fs.getXiaoShouPojo()+"'");
		} else {
			sb.append(" and xiao_shou_pojo != 'null'");
		}
		if (StringUtils.isNotEmpty(fs.getChuaPiaoRen())) {
			sb.append(" and chua_piao_ren='"+fs.getChuaPiaoRen()+"'");
		}
		
		if (null != fs2) {
			if (StringUtils.isNotEmpty(fs.getChuPiaoDate()) && StringUtils.isNotEmpty(fs2.getChuPiaoDate())) {
				sb.append(" and chu_piao_date >= '"+fs.getChuPiaoDate()+"' and chu_piao_date < '"+DateUtil.stringDatePlusOne(fs2.getChuPiaoDate())+"'");
			}
			if (StringUtils.isNotEmpty(fs.getFightDate()) && StringUtils.isNotEmpty(fs2.getFightDate())) {
				sb.append(" and fight_date between '"+fs.getFightDate()+"' and '"+fs2.getFightDate()+"'");
			}
		}
		
		if (StringUtils.isNotEmpty(fs.getMno())) {
			sb.append(" and mno='"+fs.getMno()+"'");
		}
		sb.append(" group by xiao_shou_pojo");
		return this.findBySQL(sb.toString());
	}

	@Override
	public void deleteFinanceSale(String id, String mno) throws Exception {
		this.executeHql("delete FinanceSaleEntity where id='"+id+" and mno='"+mno+"'");
	}

	@Override
	public FinanceSaleEntity queryById(String id, String mno) throws Exception {
		return (FinanceSaleEntity) this.findUnique("from FinanceSale where id='"+id+" and mno='"+mno+"'");
	}

}
