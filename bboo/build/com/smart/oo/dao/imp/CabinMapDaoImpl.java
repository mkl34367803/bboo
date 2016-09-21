package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.CabinMapEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.ICabinMapDao;
import com.smart.utils.StringUtils;

@Repository("BBOOCabinMapDaoImpl")
public class CabinMapDaoImpl extends BaseDAO<CabinMapEntity, Integer> implements
		ICabinMapDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6934564816966476570L;

	public CabinMapDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer saveData(CabinMapEntity e) throws Exception {
		// TODO Auto-generated method stub
		return this.save(e);
	}

	@Override
	public List<CabinMapEntity> findList(CabinMapEntity e) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer("from CabinMapEntity where 1=1");
		return this.find(buf.toString());
	}

	@Override
	public List<CabinMapEntity> findByPage(CabinMapEntity c, Page page)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("from CabinMapEntity where 1=1 ");
		if (StringUtils.isNotEmpty(c.getCarrier())) {
			sb.append(" and carrier='"+c.getCarrier()+"'");
		}
		if (StringUtils.isNotEmpty(c.getCabin())) {
			sb.append(" and cabin='"+c.getCabin()+"'");
		}
		if (StringUtils.isNotEmpty(c.getShareCode())) {
			sb.append(" and shareCode='"+c.getShareCode()+"'");
		}
		sb.append(" order by carrier");
		return this.find(sb.toString(), page);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		this.delete(id);
	}

	@Override
	public void updateCabinMap(CabinMapEntity c) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update CabinMapEntity ");
		sb.append(" set cabin='" + c.getCabin() + "'");
		sb.append(" , carrier='" + c.getCarrier() + "'");
		sb.append(" , shareCode='" + c.getShareCode() + "'");
		sb.append(" , shareCabin='" + c.getShareCabin() + "'");
		sb.append(" , shareNums='" + c.getShareNums() + "'");
		sb.append(" , depCode='" + c.getDepCode() + "'");
		sb.append(" , arrCode='" + c.getArrCode() + "'");
		sb.append(" where id='" + c.getId()+"'");
		this.executeHql(sb.toString());
	}

}
