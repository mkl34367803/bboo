package com.smart.oo.dao.imp;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.BaseOfficeEntity;
import com.smart.comm.utils.ProduceHqlUtil;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBaseOfficeDao;

@Repository("BBOOBaseOfficeDaoImpl")
public class BaseOfficeDaoImpl extends BaseDAO<BaseOfficeEntity, Integer>
		implements IBaseOfficeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2192523039862828405L;

	public BaseOfficeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<BaseOfficeEntity> findDataList()
			throws Exception {
		return this.find("from BaseOfficeEntity where 1=1");
	}

	@Override
	public List<BaseOfficeEntity> queryBaseOffice(Page page, BaseOfficeEntity baseOfficeEntity) throws Exception {
		StringBuffer hql=new StringBuffer();
		hql.append("from BaseOfficeEntity where mno='"+baseOfficeEntity.getMno()+"'");
		if(baseOfficeEntity.getId()!=null){
			hql.append(" and id="+baseOfficeEntity.getId());
		}
		if(baseOfficeEntity.getOffice()!=null){
			hql.append(" and office='"+baseOfficeEntity.getOffice()+"'");
		}
		if(baseOfficeEntity.getOfftypes()!=null){
			hql.append(" and offtypes='"+baseOfficeEntity.getOfftypes()+"'");
		}
		if(baseOfficeEntity.getAppkey()!=null){
			hql.append(" and appkey='"+baseOfficeEntity.getAppkey()+"'");
		}
		if(baseOfficeEntity.getIsu()!=null){
			hql.append(" and isu="+baseOfficeEntity.getIsu());
		}
		return this.find(hql.toString(), page);
	}

	@Override
	public BaseOfficeEntity queryOfficeByID(Integer id) throws Exception {
		return this.findUnique("from BaseOfficeEntity where id="+id);
	}

	@Override
	public Integer saveOffice(BaseOfficeEntity baseOfficeEntity) throws Exception {
		return this.save(baseOfficeEntity);
	}

	@Override
	public void updateOffice(BaseOfficeEntity baseOfficeEntity) throws Exception {
		this.executeHql("update BaseOfficeEntity set office='"+baseOfficeEntity.getOffice()+"',offtypes='"+baseOfficeEntity.getOfftypes()+"',appkey='"+baseOfficeEntity.getAppkey()+"',isu="+baseOfficeEntity.getIsu()+",etdzNum="+baseOfficeEntity.getEtdzNum()+" where id="+baseOfficeEntity.getId());
//		this.update(baseOfficeEntity);		
	}

	@Override
	public void deleteOfficeById(Integer id) throws Exception {
		this.delete(id);
	}

	@Override
	public List<BaseOfficeEntity> queryByParamVo(BaseOfficeEntity paramVo) throws Exception {
		String hql=ProduceHqlUtil.getQueryByParamHql(paramVo);
		List<BaseOfficeEntity> result=this.find(hql);
		return result;
	}

}
