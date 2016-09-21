package com.smart.oo.dao.imp;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.Select;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBaseSelectDao;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

@Repository("BaseSelectDaoImpl")
public class BaseSelectDaoImpl extends BaseDAO<Select, Integer> implements IBaseSelectDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5292919044267573127L;

	@Override
	public List<Select> querySelects() throws Exception {
		return this.find("from Select where 1=1");
	}

	@Override
	public Select querySelectById(Integer id) throws Exception {
		return this.findUnique("from Select where id=" + id);
	}

	@Override
	public void updateSelect(Select select) throws Exception {
		 StringBuffer hql = new StringBuffer();
		 hql.append("update Select set");
		 hql.append(" select_name='"+select.getSelect_name()+"',");
		 hql.append(" descrtption='"+select.getDescrtption()+"',");
		 String last_edittime = DateFormat.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
		 hql.append(" last_edittime='"+last_edittime+"',");
		 hql.append(" is_display='"+select.getIs_display()+"'");
		 hql.append(" where id='"+select.getSelect_id()+"'");
		this.executeHql(hql.toString());
	}

	@Override
	public void saveSelect(Select select) throws Exception {
		this.save(select);
	}
	
	@Override
	public void deleteSelectById(Integer selectId) throws Exception {
		this.delete(selectId);
	}

	@Override
	public List<Select> querySelectsByPage(Page page, Select select)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from Select");
		boolean flag = false;
		if(null != select.getSelect_id()){
			hql.append(" where select_id='"+select.getSelect_id()+"'");
			flag = true;
		}
		if(null != select.getSelect_name()){
			if(flag) {
				hql.append(" and select_name='"+select.getSelect_name()+"'");
			} else {
				hql.append(" where select_name='"+select.getSelect_name()+"'");
				flag = true;
			}
		}
		if(null != select.getCreate_person()){
			if(flag) {
				hql.append(" and create_person='"+select.getCreate_person()+"'");
			} else {
				hql.append(" where create_person='"+select.getCreate_person()+"'");
				flag = true;
			}
		}
		return this.find(hql.toString(), page);
	}

	@Override
	public List<Select> querySelects(Select select) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from Select where 1=1 ");
		if (StringUtils.isNotEmpty(select.getSelect_id()+"")) {
			hql.append(" and select_id='"+select.getSelect_id()+"'");
		}
		if (StringUtils.isNotEmpty(select.getSelect_name())) {
			hql.append(" and select_name='"+select.getSelect_name()+"'");
		}
		if (StringUtils.isNotEmpty(select.getDescrtption())) {
			hql.append(" and descrtption='"+select.getDescrtption()+"'");
		}
		if (StringUtils.isNotEmpty(select.getCreate_person())) {
			hql.append(" and create_person='"+select.getCreate_person()+"'");
		}
		if (StringUtils.isNotEmpty(select.getIs_display())) {
			hql.append(" and is_display='"+select.getIs_display()+"'");
		}
		return this.find(hql.toString());
	}

	
}
