package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.AfterPolicyEntity;
import com.smart.framework.base.Page;
import com.smart.oo.cache.CacheService;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IAfterPolicyService;

@Service("BBOOAfterPolicyServiceImpl")
public class AfterPolicyServiceImpl extends CacheService<AfterPolicyEntity>
		implements IAfterPolicyService {

	@Autowired
	private FactoryDaoImpl daof;

	private static final String CACHENAME = "DB_BBOO_AFTER_POLICY_DATA_NAME_0003";

	@Override
	public List<AfterPolicyEntity> findDataList(String cacheKey, boolean iscache)
			throws Exception {
		// TODO Auto-generated method stub
		if (iscache) {
			if (!checkKey(CACHENAME, cacheKey)) {
				List<AfterPolicyEntity> datas = daof.getAfterPolicyDao()
						.findAlls();
				if (datas != null && datas.size() > 0) {
					this.setInCaches(CACHENAME, cacheKey, datas);
					return datas;
				} else {
				}
				return null;
			} else {
				List<AfterPolicyEntity> data = this.getInCaches(CACHENAME,
						cacheKey);
				if (data != null) {

				} else {
				}
				return data;
			}
		} else {
			return daof.getAfterPolicyDao().findAlls();
		}
	}

	/**
	 * Map<String, List<AfterPolicyEntity>> String 是商户号
	 */
	@Override
	public Map<String, List<AfterPolicyEntity>> findDataMap(String cacheKey,
			boolean iscache) throws Exception {
		// TODO Auto-generated method stub
		Map<String, List<AfterPolicyEntity>> maps = null;
		if (iscache) {
			if (!checkKey(CACHENAME, cacheKey)) {
				List<AfterPolicyEntity> datas = daof.getAfterPolicyDao()
						.findAlls();
				if (datas != null && datas.size() > 0) {
					maps = doListToMap(datas);
					this.setInCaches(CACHENAME, cacheKey, maps);
					return maps;
				} else {

				}
				return null;
			} else {
				maps = this.getMapInCaches(CACHENAME, cacheKey);
				if (maps != null) {

				} else {
				}
				return maps;
			}
		} else {
			List<AfterPolicyEntity> datas = daof.getAfterPolicyDao().findAlls();
			if (datas != null) {
				return doListToMap(datas);
			} else
				return null;
		}
	}

	private Map<String, List<AfterPolicyEntity>> doListToMap(
			List<AfterPolicyEntity> list) {
		Map<String, List<AfterPolicyEntity>> maps = new HashMap<String, List<AfterPolicyEntity>>();
		List<AfterPolicyEntity> ds = null;
		if (list != null) {
			for (AfterPolicyEntity d : list) {
				if (maps.containsKey(d.getMno())) {
					maps.get(d.getMno()).add(d);
				} else {
					ds = new ArrayList<AfterPolicyEntity>();
					maps.put(d.getMno(), ds);
					maps.get(d.getMno()).add(d);
				}
			}
		}
		return maps;
	}

	@Override
	public List<AfterPolicyEntity> findListForPage(AfterPolicyEntity e, Page p)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getAfterPolicyDao().findListForPage(e, p);
	}

	@Override
	public Integer saveData(AfterPolicyEntity e) throws Exception {
		// TODO Auto-generated method stub
		delCaches(CACHENAME);
		return daof.getAfterPolicyDao().saveData(e);
	}

	@Override
	public void deleteByID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		delCaches(CACHENAME);
		daof.getAfterPolicyDao().deleteByID(id);
	}

	@Override
	public Integer updateData(AfterPolicyEntity e) throws Exception {
		// TODO Auto-generated method stub
		delCaches(CACHENAME);
		return daof.getAfterPolicyDao().updateData(e);
	}

}
