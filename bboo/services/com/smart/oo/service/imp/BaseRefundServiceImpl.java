package com.smart.oo.service.imp;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.BaseRefundEntity;
import com.smart.comm.entity.RefundEntity;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IBaseRefundService;
import com.smart.utils.StringUtils;

@Service("BBOOBaseRefundServiceImpl")
public class BaseRefundServiceImpl implements IBaseRefundService {
	
	@Autowired
	private FactoryDaoImpl factoryDao;

	@Override
	public List<BaseRefundEntity> queryList(BaseRefundEntity entity)
			throws Exception {
		return this.factoryDao.getBaseRefundDao().queryList(entity);
	}

	@Override
	public void saveEntity(BaseRefundEntity entity) throws Exception {
		this.factoryDao.getBaseRefundDao().saveEntity(entity);
	}

	@Override
	public void updateEntity(BaseRefundEntity entity) throws Exception {
		this.factoryDao.getBaseRefundDao().updateEntity(entity);
	}

	@Override
	public void deleteById(String id) throws Exception {
		this.factoryDao.getBaseRefundDao().deleteById(id);
	}

	@Override
	public void saveRefundAndBaseRefund(BaseRefundEntity baseRefundEntity,
			Set<RefundEntity> refundList, String baserefundId) throws Exception {
		this.factoryDao.getBaseRefundDao().saveEntity(baseRefundEntity);
		for (RefundEntity refundEntity : refundList) {
			refundEntity.setBase(baseRefundEntity);
			this.factoryDao.getRefundDao().saveRefound(refundEntity);
			System.out.println(refundEntity.toString());
		}
		if (StringUtils.isNotEmpty(baserefundId)) {
			this.factoryDao.getBaseRefundDao().updateOrderStatus(baserefundId, baseRefundEntity.getOrderStatus());
		}
		// 更改状态
		this.factoryDao.getSaleOrderDao().updateSlfStatus(baseRefundEntity.getFkid(), baseRefundEntity.getOrderStatus());
		this.factoryDao.getGjBuyOrderDao().updateSlfStatus(baseRefundEntity.getFkid(), baseRefundEntity.getOrderStatus());
	}

	@Override
	public BaseRefundEntity queryById(String id) throws Exception {
		return this.factoryDao.getBaseRefundDao().queryById(id);
	}

	@Override
	public void updateOrderStatus(String id, String orderStatus)
			throws Exception {
		this.factoryDao.getBaseRefundDao().updateOrderStatus(id, orderStatus);
	}

	@Override
	public void updateLocker(String id, String locker) throws Exception {
		this.factoryDao.getBaseRefundDao().updateLocker(id, locker);
	}
	
}
