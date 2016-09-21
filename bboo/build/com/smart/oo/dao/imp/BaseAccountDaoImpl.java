package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.smart.comm.entity.BaseAccountEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBaseAccountDao;

@Repository("BaseAccountDaoImpl")
public class BaseAccountDaoImpl extends BaseDAO<BaseAccountEntity, Integer>
		implements IBaseAccountDao {
	private static List<BaseAccountEntity> baseAccountEntities = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<BaseAccountEntity> queryBaseAccount(Page page,
			BaseAccountEntity baseAccountEntity) throws Exception {
		if (baseAccountEntity == null) {
			return this.find(page);
		} else {
			StringBuffer hql = new StringBuffer();
			hql.append("from BaseAccountEntity");
			if (StringUtils.isNotBlank(baseAccountEntity.getMno())) {
				hql.append(" and mno = '" + baseAccountEntity.getMno() + "'");
			}
			if (StringUtils.isNotBlank(baseAccountEntity.getUserName())) {
				hql.append(" and userName like '%"
						+ baseAccountEntity.getUserName() + "%'");
			}
			String str = hql.toString().replaceFirst("and", "where");
			return this.find(str, page);
		}
	}

	@Override
	public Integer queryCountAccount(BaseAccountEntity baseAccountEntity)
			throws Exception {
		if (baseAccountEntity == null) {
			return this.countBySQL("select count(*) from zs_base_account");
		} else {
			StringBuffer hql = new StringBuffer();
			hql.append("select count(*) from zs_base_account");
			if (StringUtils.isNotBlank(baseAccountEntity.getMno())) {
				hql.append(" and mno = '" + baseAccountEntity.getMno() + "'");
			}
			if (StringUtils.isNotBlank(baseAccountEntity.getUserName())) {
				hql.append(" and user_name like '%"
						+ baseAccountEntity.getUserName() + "%'");
			}
			String str = hql.toString().replaceFirst("and", "where");
			return this.countBySQL(str);
		}
	}

	@Override
	public Integer saveAndUpdateBaseAccount(BaseAccountEntity baseAccountEntity)
			throws Exception {
		if (baseAccountEntity.getId() != null) {
			this.update(baseAccountEntity);
		} else {
			this.save(baseAccountEntity);
		}
		baseAccountEntities = this.find("from BaseAccountEntity");// 给缓存重新复制
		return 1;
	}
	@Override
	public List<BaseAccountEntity> queryBaseAccounts() throws Exception {

		if (baseAccountEntities == null || baseAccountEntities.size() == 0) {
			baseAccountEntities = this.find("from BaseAccountEntity");// 将所有的账户信息查询出来。
		}
		return baseAccountEntities;
	}

	@Override
	public List<BaseAccountEntity> queryBaseAccountByMno(String mno)
			throws Exception {
		// StringBuffer hql=new StringBuffer();
		// hql.append("from BaseAccountEntity");
		// hql.append(" where acckind='1'"); //1表示分销商
		// hql.append(" and isu='1'"); //1表示分销商
		// hql.append(" and acctype like '%-O-%'"); //模糊查询-O-表示订单
		// if(StringUtils.isNotEmpty(mno)){
		// hql.append(" and mno='"+mno+"'");
		// }
		// String str=hql.toString();
		// return this.find(str);
		List<BaseAccountEntity> result = null;
		baseAccountEntities = queryBaseAccounts();// 将所有的账户信息查询出来。
		if (baseAccountEntities != null && baseAccountEntities.size() > 0) {
			result = new ArrayList<BaseAccountEntity>();
			for (BaseAccountEntity baseAccountEntity : baseAccountEntities) {
				if (baseAccountEntity.getAcckind() != null
						&& baseAccountEntity.getIsu() != null
						&& 1 == baseAccountEntity.getAcckind()
						&& 1 == baseAccountEntity.getIsu()&&baseAccountEntity.getMno().equalsIgnoreCase(mno)) {
					result.add(baseAccountEntity);
				}
			}
		}
		return result;
	}

	@Override
	public BaseAccountEntity queryBaseAccountByID(Integer id) throws Exception {
		return this.findUnique("from BaseAccountEntity where id=" + id);
	}

}
