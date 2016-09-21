package com.smart.oo.dao.imp;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.smart.base.exception.SqlException;
import com.smart.comm.entity.ReportFormsEntity;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IReportFormsDao;
import com.smart.utils.StringUtils;
@Repository("ReportFormsDaoImpl")
public class ReportFormsDaoImpl extends BaseDAO<ReportFormsEntity,Serializable> implements IReportFormsDao{
	private static final long serialVersionUID = 1L;

	@Override
	public List<ReportFormsEntity> getFieldName(ReportFormsEntity r)
			throws SqlException {
		List<Object> params=new ArrayList<Object>();
		StringBuffer sbf=new StringBuffer(" from ReportFormsEntity   where 1=1 and isUsed=1  and  mno="+"'"+r.getMno()+"'");
		if(StringUtils.isNotEmpty(r.getMnoType())){
			sbf.append("   and   mnoType=?");
			params.add(r.getMnoType());
		}	
		return this.find(sbf.toString(),params.toArray());
	}

	@Override
	public void saveFieldName(ReportFormsEntity r) throws SqlException {
		this.save(r);
	}

	@Override
	public void updateFieldName(ReportFormsEntity r) throws SqlException {
		this.update(r);
	}

	@Override
	public List<ReportFormsEntity> findByPage(ReportFormsEntity r,Page p) throws SqlException {
		StringBuffer  hql=new StringBuffer("from  ReportFormsEntity where 1=1");
		List<Object>  params=new ArrayList<Object>();
		if(StringUtils.isNotEmpty(r.getMno())){
			hql.append("  and mno=?");
			params.add(r.getMno());
		}
		if(StringUtils.isNotEmpty(r.getMnoType())){
			hql.append("  and mnoType=?");
			params.add(r.getMnoType());
		}
		if (StringUtils.isNotEmpty(r.getIsUsed()+"")) {
			hql.append("  and isUsed=?");
			params.add(r.getIsUsed());
		}
		hql.append("  order by id desc");
		return this.find(hql.toString(), params.toArray(), p);
	}

	@Override
	public ReportFormsEntity getById(ReportFormsEntity r) throws SqlException {
		String hql="from ReportFormsEntity where 1=1 and id="+"'"+r.getId()+"'"+"  and mno="+"'"+r.getMno()+"'";
		return (ReportFormsEntity) this.findUnique(hql);
	}

	@Override
	public  List<ReportFormsEntity> getCount(ReportFormsEntity r) throws SqlException{
		StringBuffer  hql=new StringBuffer("from  ReportFormsEntity where 1=1");
		List<Object>  params=new ArrayList<Object>();
		if(StringUtils.isNotEmpty(r.getMno())){
			hql.append("  and mno=?");
			params.add(r.getMno());
		}
		if(StringUtils.isNotEmpty(r.getMnoType())){
			hql.append("  and mnoType=?");
			params.add(r.getMnoType());
		}
		if (StringUtils.isNotEmpty(r.getIsUsed()+"")) {
			hql.append("  and isUsed=?");
			params.add(r.getIsUsed());
		}
		return this.find(hql.toString(), params.toArray());
	}
	

	public static void main(String[] args) throws SqlException {
		ApplicationContext ac =new  ClassPathXmlApplicationContext("applicationContext.xml");
		ReportFormsDaoImpl dao=(ReportFormsDaoImpl) ac.getBean("ReportFormsDaoImpl");

	}

	@Override
	public void deleteReportForms(String id, String mno) throws Exception {
		this.executeHql("delete ReportFormsEntity where id='"+id+"' and mno='"+mno+"'");
	}

	@Override
	public List<ReportFormsEntity> queryReportForms(
			ReportFormsEntity reportForms) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from ReportFormsEntity where 1=1");
		if (StringUtils.isNotEmpty(reportForms.getIsUsed()+"")) {
			hql.append(" and isUsed='"+reportForms.getIsUsed()+"'");
		}
		if (StringUtils.isNotEmpty(reportForms.getMnoType())) {
			hql.append(" and mnoType='"+reportForms.getMnoType()+"'");
		}
		if (StringUtils.isNotEmpty(reportForms.getFieldName())) {
			hql.append(" and fieldName='"+reportForms.getFieldName()+"'");
		}
		hql.append(" and mno='"+reportForms.getMno()+"'");
		return this.find(hql.toString());
	}

}
