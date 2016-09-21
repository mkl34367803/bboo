package com.smart.oo.service.imp;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smart.comm.entity.ShareFlightEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IShareFlightService;

@Repository("BBOOShareFlightServiceImpl")
public class ShareFlightServiceImpl implements IShareFlightService {

	@Autowired
	private FactoryDaoImpl daof;

	@Override
	public Integer saveData(ShareFlightEntity e) throws Exception {
		// TODO Auto-generated method stub
		return daof.getShareFlightDao().saveData(e);
	}

	@Override
	public List<ShareFlightEntity> findShareDatas(ShareFlightEntity e)
			throws Exception {
		// TODO Auto-generated method stub
		return daof.getShareFlightDao().findShareDatas(e);
	}

	@Override
	public void saveData(List<ShareFlightEntity> elist) throws Exception {
		// TODO Auto-generated method stub
		if (elist != null) {
			Iterator<ShareFlightEntity> iterator = elist.iterator();
			while (iterator.hasNext()) {
				daof.getShareFlightDao().saveData(iterator.next());
				iterator.remove();
			}
		}
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		daof.getShareFlightDao().deleteAll();
	}

	@Override
	public void updateShareCabin() throws Exception {
		// TODO Auto-generated method stub
		daof.getShareFlightDao().updateShareCabin();
	}

	@Override
	public void updatePrice() throws Exception {
		// TODO Auto-generated method stub
		daof.getShareFlightDao().updatePrice();
	}

}
