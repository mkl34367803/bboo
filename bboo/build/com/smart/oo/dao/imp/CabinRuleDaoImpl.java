package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.CabinRuleEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.ICabinRuleDao;
import com.smart.utils.StringUtils;

@Repository("BBOOCabinRuleDaoImpl")
public class CabinRuleDaoImpl extends BaseDAO<CabinRuleEntity, Integer> implements ICabinRuleDao {

	private static final long serialVersionUID = -150912311637452420L;

	@Override
	public List<CabinRuleEntity> queryByPage(CabinRuleEntity entity, Page page)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("from CabinRuleEntity entity where 1=1");
		if (StringUtils.isNotEmpty(entity.getCarrier())) {
			sb.append(" and carrier = ?");
			params.add(entity.getCarrier());
		}
		if (StringUtils.isNotEmpty(entity.getCabin())) {
			sb.append(" and cabin = ?");
			params.add(entity.getCabin());
		}
		if (null != entity.getId()) {
			sb.append(" and id = ?");
			params.add(entity.getId());
		}
		return this.find(sb.toString(), params.toArray(), page);
	}

	@Override
	public List<CabinRuleEntity> queryList(CabinRuleEntity entity)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("from CabinRuleEntity entity where 1=1");
		if (StringUtils.isNotEmpty(entity.getCarrier())) {
			sb.append(" and carrier = ?");
			params.add(entity.getCarrier());
		}
		if (StringUtils.isNotEmpty(entity.getCabin())) {
			sb.append(" and cabin = ?");
			params.add(entity.getCabin());
		}
		if (null != entity.getId()) {
			sb.append(" and id = ?");
			params.add(entity.getId());
		}
		if (StringUtils.isNotEmpty(entity.getStartValidity()) && StringUtils.isNotEmpty(entity.getEndValidity())) {
			sb.append(" and startValidity >= ? and endValidity < ?");
			params.add(entity.getStartValidity());
			params.add(entity.getEndValidity());
		} else if (StringUtils.isNotEmpty(entity.getStartValidity())) {
			sb.append(" and startValidity <= ? and endValidity >= ?");
			params.add(entity.getStartValidity());
			params.add(entity.getStartValidity());
		}
		return this.find(sb.toString(), params.toArray());
	}

	@Override
	public void saveEntity(CabinRuleEntity entity) throws Exception {
		this.save(entity);
	}

	@Override
	public void updateEntity(CabinRuleEntity entity) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append("update CabinRuleEntity");
		sb.append(" set carrier=?");
		params.add(entity.getCarrier());
		sb.append(" ,cabin=?");
		params.add(entity.getCabin());
		sb.append(" ,returnRule=?");
		params.add(entity.getReturnRule());
		sb.append(" ,changeRule=?");
		params.add(entity.getChangeRule());
		sb.append(" ,endorsement=?");
		params.add(entity.getEndorsement());
		sb.append(" ,voidDay=?");
		params.add(entity.getVoidDay());
		sb.append(" ,startValidity=?");
		params.add(entity.getStartValidity());
		sb.append(" ,endValidity=?");
		params.add(entity.getEndValidity());
		sb.append(" ,lastOperator=?");
		params.add(entity.getLastOperator());
		sb.append(" where id=?");
		params.add(entity.getId());
		this.executeHql(sb.toString(), params.toArray());
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		this.delete(id);
	}

}
