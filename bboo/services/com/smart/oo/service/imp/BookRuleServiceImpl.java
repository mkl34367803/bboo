package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.comm.entity.BookRuleEntity;
import com.smart.framework.base.Page;
import com.smart.oo.cache.CacheService;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBookRuleService;

@Service("BBOOBookRuleServiceImpl")
public class BookRuleServiceImpl extends CacheService<BookRuleEntity> implements IBookRuleService {
	/**
	 * 缓存名字
	 */
	private static final String CACHENAME = "DB_BBOO_BOOKRULE_DATA_NAME_0005";
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<BookRuleEntity> queryByPage(BookRuleEntity entity, Page page)
			throws Exception {
		return this.factoryDaoImpl.getBookRuleDao().queryByPage(entity, page);
	}

	@Override
	public void saveBookRule(BookRuleEntity entity) throws Exception {
		this.factoryDaoImpl.getBookRuleDao().saveBookRule(entity);
	}

	@Override
	public void updateBookRule(BookRuleEntity entity) throws Exception {
		this.factoryDaoImpl.getBookRuleDao().updateBookRule(entity);
	}

	@Override
	public void deleteBookRule(Integer id) throws Exception {
		this.factoryDaoImpl.getBookRuleDao().deleteBookRule(id);
	}

	@Override
	public List<BookRuleEntity> getAll(String cacheKey, boolean iscache) throws Exception {

		// TODO Auto-generated method stub
		if (iscache) {
			if (!checkKey(CACHENAME, cacheKey)) {
				List<BookRuleEntity> datas = factoryDaoImpl.getBookRuleDao().getAll();
				if (datas != null && datas.size() > 0) {
					this.setInCaches(CACHENAME, cacheKey, datas);
					return datas;
				} else {
				}
				return null;
			} else {
				List<BookRuleEntity> data = this.getInCaches(CACHENAME,
						cacheKey);
				if (data != null) {

				} else {
				}
				return data;
			}
		} else {
			return factoryDaoImpl.getBookRuleDao().getAll();
		}
	
	}

}
