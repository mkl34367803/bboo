package com.smart.oo.dao.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.comm.entity.QuestionsEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IQuestionsDao;
import com.smart.utils.StringUtils;

@Repository("BBOOQuestionsDaoImpl")
public class QuestionsDaoImpl extends BaseDAO<QuestionsEntity, Integer> implements IQuestionsDao {

	private static final long serialVersionUID = -2986031115532997759L;

	@Override
	public List<QuestionsEntity> queryByPage(QuestionsEntity entity, Page page)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append(" from QuestionsEntity where 1=1");
		if (null != entity.getQType()) {
			sb.append(" and QType = ?");
			params.add(entity.getQType());
		}
		if (null != entity.getClassify()) {
			sb.append(" and classify = ?");
			params.add(entity.getClassify());
		}
		if (null != entity.getIsuse()) {
			sb.append(" and isuse = ?");
			params.add(entity.getIsuse());
		}
		if (StringUtils.isNotEmpty(entity.getIntoPerson())) {
			sb.append(" and intoPerson = ?");
			params.add(entity.getIntoPerson());
		}
		sb.append(" order by QType desc");
		return this.find(sb.toString(), params.toArray(), page);
	}
	
	@Override
	public Integer countNum(QuestionsEntity entity) throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append(" from QuestionsEntity where 1=1");
		if (null != entity.getQType()) {
			sb.append(" and QType = ?");
			params.add(entity.getQType());
		}
		if (null != entity.getClassify()) {
			sb.append(" and classify = ?");
			params.add(entity.getClassify());
		}
		if (null != entity.getIsuse()) {
			sb.append(" and isuse = ?");
			params.add(entity.getIsuse());
		}
		if (StringUtils.isNotEmpty(entity.getIntoPerson())) {
			sb.append(" and intoPerson = ?");
			params.add(entity.getIntoPerson());
		}
		return this.countByHQL(sb.toString(), params.toArray());
	}

	@Override
	public List<QuestionsEntity> queryList(QuestionsEntity entity)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sb.append(" from QuestionsEntity where 1=1");
		if (null != entity.getQType()) {
			sb.append(" and QType = ?");
			params.add(entity.getQType());
		}
		if (null != entity.getClassify()) {
			sb.append(" and classify = ?");
			params.add(entity.getClassify());
		}
		if (null != entity.getIsuse()) {
			sb.append(" and isuse = ?");
			params.add(entity.getIsuse());
		}
		if (StringUtils.isNotEmpty(entity.getIntoPerson())) {
			sb.append(" and intoPerson = ?");
			params.add(entity.getIntoPerson());
		}
		return this.find(sb.toString(), params.toArray());
	}
	
	@Override
	public QuestionsEntity queryById(Integer id) throws Exception {
		String hql = "from QuestionsEntity where id = "+id;
		return this.findUnique(hql);
	}

	@Override
	public void saveEntity(QuestionsEntity entity) throws Exception {
		this.save(entity);
	}

	@Override
	public void updateEntity(QuestionsEntity entity) throws Exception {
		this.update(entity);
	}

	@Override
	public void deleteEntity(Integer id) throws Exception {
		this.delete(id);
	}

}
