package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.BookRuleEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBookRuleDao;
import com.smart.utils.StringUtils;

@Repository("BBOOBookRuleDaoImpl")
public class BookRuleDaoImpl extends BaseDAO<BookRuleEntity, Integer> implements IBookRuleDao {

	private static final long serialVersionUID = 2671578661710920075L;

	@Override
	public List<BookRuleEntity> queryByPage(BookRuleEntity entity, Page page)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" from BookRuleEntity where 1=1");
		if (StringUtils.isNotEmpty(entity.getShopName())) {
			sb.append(" and shopName='"+entity.getShopName()+"'");
		}
		sb.append(" and mno='"+entity.getMno()+"'");
		return this.find(sb.toString(), page);
	}

	@Override
	public void saveBookRule(BookRuleEntity entity) throws Exception {
		this.save(entity);
	}

	@Override
	public void updateBookRule(BookRuleEntity entity) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update BookRuleEntity ");
		sb.append(" set shopName='"+entity.getShopName()+"'");
		sb.append(" ,policyType='"+entity.getPolicyType()+"'");
		sb.append(" ,bookChannel='"+entity.getBookChannel()+"'");
		sb.append(" ,isDrop='"+entity.getIsDrop()+"'");
		sb.append(" ,isuse='"+entity.getIsuse()+"'");
		if (entity.getLr() != null) {
			sb.append(" ,lr='"+entity.getLr()+"'");
		} else {
			sb.append(" ,lr=''");
		}
		sb.append(" ,includeRule='"+entity.getIncludeRule()+"'");
		sb.append(" ,ignoredRules='"+entity.getIgnoredRules()+"'");
		sb.append("where id="+entity.getId());
		sb.append(" and mno='"+entity.getMno()+"'");
		this.executeHql(sb.toString());
	}

	@Override
	public void deleteBookRule(Integer id) throws Exception {
		this.delete(id);
	}

	@Override
	public List<BookRuleEntity> getAll() throws Exception {
		StringBuffer buf = new StringBuffer("from BookRuleEntity where 1=1");
		return this.find(buf.toString());
	}

}
