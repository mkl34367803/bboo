package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.KeyValEntity;
import com.smart.comm.utils.OOComms;
import com.smart.framework.base.Page;
import com.smart.oo.cache.CacheService;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IKeyValService;

@Service("GKeyValServiceImpl")
public class KeyValServiceImpl extends CacheService<KeyValEntity> implements
		IKeyValService {

	@Autowired
	private FactoryDaoImpl daof;

	private static final String CACHENAME = OOComms.CACHE_KEY_VAL_DATAS_NAME;

	@Override
	public List<KeyValEntity> findDataList() throws Exception {
		// TODO Auto-generated method stub
		return daof.getIkeyValDao().findDataList();
	}

	@Override
	public List<KeyValEntity> findDataList(String cacheKey, boolean iscache)
			throws Exception {
		// TODO Auto-generated method stub
		if (iscache) {
			if (!checkKey(CACHENAME, cacheKey)) {
				List<KeyValEntity> datas = daof.getIkeyValDao().findDataList();
				if (datas != null && datas.size() > 0) {
					this.setInCaches(CACHENAME, cacheKey, datas);
					return datas;
				} else {
				}
				return null;
			} else {
				List<KeyValEntity> data = this.getInCaches(CACHENAME, cacheKey);
				if (data != null) {

				} else {
				}
				return data;
			}
		} else {
			return daof.getIkeyValDao().findDataList();
		}
	}

	@Override
	public void saveData(KeyValEntity v) throws Exception {
		this.daof.getIkeyValDao().saveData(v);
	}

	@Override
	public void updateData(KeyValEntity v) throws Exception {
		daof.getIkeyValDao().updateData(v);
	}

	@Override
	public void deleteData(String id) throws Exception {
		daof.getIkeyValDao().deleteData(id);
	}

	@Override
	public KeyValEntity queryById(Integer id) throws Exception {
		return daof.getIkeyValDao().queryById(id);
	}

	@Override
	public List<KeyValEntity> queryKeyValues(Page page,
			KeyValEntity keyValEntity) throws Exception {
		// TODO Auto-generated method stub
		return daof.getIkeyValDao().queryKeyValues(page, keyValEntity);
	}

	@Override
	public KeyValEntity queryByKey(String key) throws Exception {
		return daof.getIkeyValDao().queryByKey(key);
	}

}
