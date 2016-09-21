package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.AirPriceEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IAirPriceDao;
import com.smart.utils.StringUtils;

@Repository("BBOOAirPriceDaoImpl")
public class AirPriceDaoImpl extends BaseDAO<AirPriceEntity, String> implements
		IAirPriceDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3761302080808989988L;

	@Override
	public String saveData(AirPriceEntity en) throws Exception {
		// TODO Auto-generated method stub
		return this.save(en);
	}

	@Override
	public void del(AirPriceEntity entity) throws Exception {
		// TODO Auto-generated method stub
		this.executeSql(
				"delete from t_air_price where dep_code=? and arr_code=? and keys<? and keyid=?",
				new Object[] { entity.getDepCode(), entity.getArrCode(),
						entity.getKeys(), entity.getKeyID() });
	}

	@Override
	public List<AirPriceEntity> findDatas(AirPriceEntity en) throws Exception {
		// TODO Auto-generated method stub
		return this.find(getFindDataSQL(en));
	}

	private String getFindDataSQL(AirPriceEntity en) {
		StringBuffer buf = new StringBuffer("from AirPriceEntity where 1=1");
		if (en != null) {
			if (StringUtils.isNotEmpty(en.getCarrier())) {
				buf.append(" and carrier='" + en.getCarrier() + "'");
			}
			if (StringUtils.isNotEmpty(en.getCabin())) {
				buf.append(" and cabin='" + en.getCabin() + "'");
			}
			if (StringUtils.isNotEmpty(en.getDepCode())) {
				buf.append(" and depCode='" + en.getDepCode() + "'");
			}
			if (StringUtils.isNotEmpty(en.getArrCode())) {
				buf.append(" and arrCode='" + en.getArrCode() + "'");
			}
		}
		buf.append(" and IsDelete=0");
		buf.append(" order by carrier,depCode,arrCode,price desc,id desc");
		return buf.toString();
	}

	@Override
	public List<AirPriceEntity> findDatas(AirPriceEntity en, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		return this.find(getFindDataSQL(en), p);
	}

	@Override
	public AirPriceEntity barrierById(String id) throws Exception {
		// TODO Auto-generated method stub
		List<AirPriceEntity> list = this.find("from AirPriceEntity where id=?",
				new Object[] { id });
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

}
