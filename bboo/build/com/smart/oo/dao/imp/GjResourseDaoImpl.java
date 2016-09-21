package com.smart.oo.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.Resource;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IGjResourseDao;
import com.smart.oo.from.ResourceVo;

@Repository("GjResourseDaoImpl")
public class GjResourseDaoImpl extends BaseDAO<Resource, Integer> implements
		IGjResourseDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5999558128654703621L;

	public GjResourseDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public Integer saveResourse(Resource entity) throws Exception {
		return this.save(entity);
	}

	@Override
	public List<Resource> queryResource(Page page, ResourceVo resourceParam)
			throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Resource");
		// resourceId这里注意一下，两个名字不同
		if (resourceParam != null) {
			if (resourceParam.getResource_id() != null) {
				stringBuffer.append(" and resourceId='"
						+ resourceParam.getResource_id() + "'");
			}
			if (resourceParam.getName() != null) {
				stringBuffer.append(" and name='" + resourceParam.getName()
						+ "'");
			}
			if (resourceParam.getType() != null) {
				stringBuffer.append(" and type='" + resourceParam.getType()
						+ "'");
			}
			if (resourceParam.getType() != null) {
				stringBuffer.append(" and value='" + resourceParam.getValue()
						+ "'");
			}
			if (resourceParam.getDescription() != null) {
				stringBuffer.append(" and description='"
						+ resourceParam.getDescription() + "'");
			}
			if (resourceParam.getSort() != null) {
				stringBuffer.append(" and sort='" + resourceParam.getSort()
						+ "'");
			}
			if (resourceParam.getPid() != null) {
				stringBuffer
						.append(" and pid='" + resourceParam.getPid() + "'");
			}
		}
		String hql = stringBuffer.toString().replaceFirst("and", "where");
		return this.find(hql, page);
	}

	@Override
	public List<Resource> queryAllResource(Page page, ResourceVo resourceParam)
			throws Exception {
		return this.find(page);
	}

	@Override
	public Integer updateResource(Resource entity) throws Exception {
		this.update(entity);
		return 1;
	}

	@Override
	public Integer deleteResourceByBatch(List<String> ids) throws Exception {
		Integer result=0;
		for (String str : ids) {
			this.delete(Integer.parseInt(str));
			result++;
		}
		return result;
	}

	@Override
	public List<Resource> getResource() throws Exception {
		System.out.println(this.getClass());
		System.out.println(this.getEntityName());
		System.out.println(this.getHibernateTemplate());
		return this.find();
	}

	@Override
	public Resource getResourceByID(Integer integer) throws Exception {
		// TODO Auto-generated method stub
		return this.get(integer);
	}

	@Override
	public List<Resource> getParentResource() throws Exception {
		String hql="from Resource where pid=NULL";
		return this.find(hql);
	}

}
