package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IAfterPolicyDao;
import com.smart.utils.StringUtils;

@Repository("BBOOAfterPolicyDaoImpl")
public class AfterPolicyDaoImpl extends BaseDAO<AfterPolicyEntity, Integer>
		implements IAfterPolicyDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7110637297252614747L;

	@Override
	public List<AfterPolicyEntity> findAlls() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer buf = new StringBuffer("from AfterPolicyEntity where 1=1");
		return this.find(buf.toString());
	}

	@Override
	public List<AfterPolicyEntity> findListForPage(AfterPolicyEntity e, Page p)
			throws Exception {
		List<Object> list = new ArrayList<Object>();
		StringBuffer buf = new StringBuffer(
				"from AfterPolicyEntity where 1=1 and mno=?");
		list.add(e.getMno());
		if (StringUtils.isNotEmpty(e.getCarrier())) {
			buf.append(" and carrier=?");
			list.add(e.getCarrier());
		}
		if (StringUtils.isNotEmpty(e.getProductType())) {
			buf.append(" and productType=?");
			list.add(e.getProductType());
		}
		buf.append(" order by channel,id desc");
		return this.find(buf.toString(), list.toArray());
	}

	@Override
	public Integer saveData(AfterPolicyEntity e) throws Exception {
		// TODO Auto-generated method stub
		return this.save(e);
	}

	@Override
	public void deleteByID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		this.delete(id);
	}

	@Override
	public Integer updateData(AfterPolicyEntity e) throws Exception {
		StringBuffer buf = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		buf.append("update AfterPolicyEntity ");
		buf.append("set carrier=?");
		if (e.getCarrier() == null) {
			list.add("");
		} else {
			list.add(e.getCarrier());
		}
		buf.append(",channel=?");
		list.add(e.getChannel());
		buf.append(",productType=?");
		list.add(e.getProductType());
		buf.append(",after=?");
		list.add(e.getAfter());
		buf.append(",channelCh=?");
		list.add(e.getChannelCh());
		buf.append(",productTypeCh=?");
		list.add(e.getProductTypeCh());
		buf.append(" where id=? and mno=?");
		list.add(e.getId());
		list.add(e.getMno());
		return this.executeHql(buf.toString(), list.toArray());
	}

}
