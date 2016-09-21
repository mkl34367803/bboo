package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.framework.base.Page;
import com.smart.oo.cache.CacheService;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseOfficeService;

@Service("BBOOBaseOfficeServiceImpl")
public class BaseOfficeServiceImpl extends CacheService<BaseOfficeEntity>
		implements IBaseOfficeService {

	@Autowired
	private FactoryDaoImpl daof;

	@Override
	public List<BaseOfficeEntity> findDataList()
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getIbaseOfficeDao().findDataList();
	}

	@Override
	public List<BaseOfficeEntity> findDataList(String cacheName,
			String cacheKey, boolean iscache) throws Exception {
		// TODO Auto-generated method stub
		if (iscache) {
			if (!checkKey(cacheName, cacheKey)) {
				List<BaseOfficeEntity> datas = daof.getIbaseOfficeDao()
						.findDataList();
				if (datas != null && datas.size() > 0) {
					this.setInCaches(cacheName, cacheKey, datas);
					return datas;
				} else {
				}
				return null;
			} else {
				List<BaseOfficeEntity> data = this.getInCaches(cacheName,
						cacheKey);
				if (data != null) {

				} else {
				}
				return data;
			}
		} else {
			return daof.getIbaseOfficeDao().findDataList();
		}
	}

	@Override
	public List<BaseOfficeEntity> queryBaseOffice(Page page, BaseOfficeEntity baseOfficeEntity) throws Exception {
		return daof.getIbaseOfficeDao().queryBaseOffice(page, baseOfficeEntity);
	}

	@Override
	public BaseOfficeEntity queryOfficeByID(Integer id) throws Exception {
		return daof.getIbaseOfficeDao().queryOfficeByID(id);
	}

	@Override
	public Integer saveOffice(BaseOfficeEntity baseOfficeEntity) throws Exception {
		return daof.getIbaseOfficeDao().saveOffice(baseOfficeEntity);
	}

	@Override
	public void updateOffice(BaseOfficeEntity baseOfficeEntity) throws Exception {
		daof.getIbaseOfficeDao().updateOffice(baseOfficeEntity);
	}

	@Override
	public void deleteOfficeById(Integer id) throws Exception {
		daof.getIbaseOfficeDao().deleteOfficeById(id);
	}

	@Override
	public List<BaseOfficeEntity> queryByParamVo(BaseOfficeEntity paramVo)  throws Exception {
		return daof.getIbaseOfficeDao().queryByParamVo(paramVo);
	}

}
