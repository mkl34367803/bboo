package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.entity.Resource;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.from.ResourceVo;
import com.smart.oo.service.IGjResourceService;
@Service(value="GjResouceServiceImpl")
public class GjResouceServiceImpl implements IGjResourceService {
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public Integer saveResourse(Resource entity) throws Exception {
		// TODO Auto-generated method stub
		return factoryDao.getGjResourseDao().saveResourse(entity);
	}

	@Override
	public List<Resource> queryResource(Page page,ResourceVo resourceParam)
			throws Exception {
		return factoryDao.getGjResourseDao().queryResource(page,resourceParam);
	}

	@Override
	public List<Resource> queryAllResource(Page page,ResourceVo resourceParam) throws Exception {
		return factoryDao.getGjResourseDao().queryAllResource(page,resourceParam);
	}

	@Override
	public Integer updateResource(Resource entity) throws Exception {
		return factoryDao.getGjResourseDao().updateResource(entity);
	}

	@Override
	public Integer deleteResourceByBatch(List<String> ids) throws Exception {
		return factoryDao.getGjResourseDao().deleteResourceByBatch(ids);
	}

	@Override
	public Resource getResourceByID(Integer integer) throws Exception {
		return factoryDao.getGjResourseDao().getResourceByID(integer);
	}

	@Override
	public List<Resource> getParentResource() throws Exception {
		return factoryDao.getGjResourseDao().getParentResource();
	}

}
