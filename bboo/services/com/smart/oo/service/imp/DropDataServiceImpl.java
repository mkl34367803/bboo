package com.smart.oo.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.DropDataEntity;
import com.smart.comm.entity.DropFileEntity;
import com.smart.oo.dao.IDropDataDao;
import com.smart.oo.dao.IDropFileDao;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IDropDataService;

@Service("GDropDataServiceImpl")
public class DropDataServiceImpl implements IDropDataService {

	@Autowired
	private FactoryDaoImpl daof;

	@Resource(name = "BBOODropDataDaoImpl")
	private IDropDataDao dropDataDao;
	@Resource(name = "BBOODropFileDaoImpl")
	private IDropFileDao dropFileDao;

	@Override
	public String saveData(DropDataEntity data) throws Exception {
		// TODO Auto-generated method stub
		return dropDataDao.saveData(data);
	}

	@Override
	public List<DropDataEntity> findDistinctFlight(DropDataEntity data)
			throws Exception {
		// TODO Auto-generated method stub
		return dropDataDao.findDistinctFlight(data);
	}

	@Override
	public List<DropDataEntity> findFlight(DropDataEntity data)
			throws Exception {
		// TODO Auto-generated method stub
		return dropDataDao.findFlight(data);
	}

	@Override
	public Map<String, List<DropDataEntity>> getQueryLines(DropDataEntity data)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String, List<DropDataEntity>> map = new HashMap<String, List<DropDataEntity>>();
		List<DropDataEntity> list = dropDataDao.findFlight(data);
		if (list != null) {
			Iterator<DropDataEntity> iterator = list.iterator();
			while (iterator.hasNext()) {
				DropDataEntity d = iterator.next();
				String key = d.getDepCode() + d.getArrCode() + d.getCarrier()
						+ d.getDDate();
				key = key.toUpperCase();
				if (map.containsKey(key)) {
					map.get(key).add(d);
				} else {
					List<DropDataEntity> clst = new ArrayList<DropDataEntity>();
					clst.add(d);
					map.put(key, clst);
				}
				iterator.remove();
			}
		}
		return map;
	}

	@Override
	public void saveData(List<DropDataEntity> data, DropFileEntity entity)
			throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 保存文件数据
		 */
		if (data != null && data.size() > 0) {
			for (DropDataEntity t : data) {
				dropDataDao.saveData(t);
			}
		}
		/**
		 * 保存操作记录
		 */
		if (entity != null)
			dropFileDao.saveData(entity);

	}
}
