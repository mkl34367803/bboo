package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.CabinMapEntity;
import com.smart.framework.base.Page;
import com.smart.oo.cache.CacheService;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.ICabinMapService;

@Service("BBOOCabinMapServiceImpl")
public class CabinMapServiceImpl extends CacheService<CabinMapEntity> implements
		ICabinMapService {

	@Autowired
	private FactoryDaoImpl daof;

	private static final String CACHENAME = "DB_BBOO_CABIN_MAPS_DATA_NAME_0004";

	@Override
	public List<CabinMapEntity> getCaches(String cacheKey, boolean iscache)
			throws Exception {
		// TODO Auto-generated method stub
		if (iscache) {
			if (!checkKey(CACHENAME, cacheKey)) {
				List<CabinMapEntity> datas = daof.getCabinMapDao().findList(
						null);
				if (datas != null && datas.size() > 0) {
					this.setInCaches(CACHENAME, cacheKey, datas);
					return datas;
				} else {
				}
				return null;
			} else {
				List<CabinMapEntity> data = this.getInCaches(CACHENAME,
						cacheKey);
				if (data != null) {

				} else {
				}
				return data;
			}
		} else {
			return daof.getCabinMapDao().findList(null);
		}
	}

	@Override
	public Integer saveData(CabinMapEntity e) throws Exception {
		// TODO Auto-generated method stub
		delCaches(CACHENAME);
		return daof.getCabinMapDao().saveData(e);
	}

	@Override
	public List<CabinMapEntity> findList(CabinMapEntity e) throws Exception {
		// TODO Auto-generated method stub
		return daof.getCabinMapDao().findList(e);
	}

	@Override
	public List<CabinMapEntity> findByPage(CabinMapEntity c, Page page)
			throws Exception {
		return daof.getCabinMapDao().findByPage(c, page);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		daof.getCabinMapDao().deleteById(id);
	}

	@Override
	public void updateCabinMap(CabinMapEntity c) throws Exception {
		daof.getCabinMapDao().updateCabinMap(c);
	}

}
