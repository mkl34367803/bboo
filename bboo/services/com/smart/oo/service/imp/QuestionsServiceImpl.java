package com.smart.oo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.comm.entity.QuestionsEntity;
import com.smart.framework.base.Page;
import com.smart.oo.dao.imp.FactoryDaoImpl;
import com.smart.oo.service.IQuestionsService;

@Service("BBOOQuestionsServiceImpl")
public class QuestionsServiceImpl implements IQuestionsService {
	
	@Autowired
	private FactoryDaoImpl factoryDaoImpl;

	@Override
	public List<QuestionsEntity> queryByPage(QuestionsEntity entity, Page page)
			throws Exception {
		return this.factoryDaoImpl.getQuestionsDao().queryByPage(entity, page);
	}
	
	@Override
	public Integer countNum(QuestionsEntity entity) throws Exception {
		return this.factoryDaoImpl.getQuestionsDao().countNum(entity);
	}

	@Override
	public List<QuestionsEntity> queryList(QuestionsEntity entity)
			throws Exception {
		return this.factoryDaoImpl.getQuestionsDao().queryList(entity);
	}

	@Override
	public QuestionsEntity queryById(Integer id) throws Exception {
		return this.factoryDaoImpl.getQuestionsDao().queryById(id);
	}

	@Override
	public void saveEntity(QuestionsEntity entity) throws Exception {
		this.factoryDaoImpl.getQuestionsDao().saveEntity(entity);
	}

	@Override
	public void updateEntity(QuestionsEntity entity) throws Exception {
		this.factoryDaoImpl.getQuestionsDao().updateEntity(entity);
	}

	@Override
	public void deleteEntity(Integer id) throws Exception {
		this.factoryDaoImpl.getQuestionsDao().deleteEntity(id);
	}

}
