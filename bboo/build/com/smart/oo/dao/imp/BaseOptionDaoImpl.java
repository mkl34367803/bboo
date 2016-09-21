package com.smart.oo.dao.imp;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.entity.Option;
import com.smart.framework.base.BaseDAO;
import com.smart.framework.base.Page;
import com.smart.oo.dao.IBaseOptionDao;
import com.smart.timer.base.DateFormat;
import com.smart.utils.StringUtils;

@Repository("BaseOptionDaoImpl")
public class BaseOptionDaoImpl extends BaseDAO<Option, Integer> implements IBaseOptionDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5293580610854528373L;

	@Override
	public List<Option> queryOptionList(Option option) throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from Option where 1=1 ");
		if (StringUtils.isNotEmpty(option.getOption_id()+"")) {
			hql.append(" and option_id='"+option.getOption_id()+"'");
		}
		if (StringUtils.isNotEmpty(option.getOption_name())) {
			hql.append(" and option_name='"+option.getOption_name()+"'");
		}
		if (StringUtils.isNotEmpty(option.getOption_value())) {
			hql.append(" and option_value='"+option.getOption_value()+"'");
		}
		if (StringUtils.isNotEmpty(option.getOption_text())) {
			hql.append(" and option_text='"+option.getOption_text()+"'");
		}
		if (StringUtils.isNotEmpty(option.getSelect_id()+"")) {
			hql.append(" and select_id='"+option.getSelect_id()+"'");
		}
		return this.find(hql.toString());
	}

	@Override
	public void updateOption(Option option) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update Option set");
		sb.append(" select_id=" + option.getSelect_id() + ",");
		sb.append(" option_name='" + option.getOption_name() + "',");
		sb.append(" option_value='" + option.getOption_value() + "',");
		sb.append(" option_text='" + option.getOption_text() + "',");
		sb.append(" descrtption='" + option.getDescrtption() + "',");
		String last_edittime = DateFormat.getDateString(new Date(), "yyyy-MM-dd HH:mm:ss");
		sb.append(" last_edittime='" + last_edittime + "',");
		sb.append(" is_display='" + option.getIs_display() + "'");
		sb.append(" where option_id=" + option.getOption_id()+" ");
		this.executeHql(sb.toString());
	}

	@Override
	public void saveOption(Option option) throws Exception {
		this.save(option);
	}

	@Override
	public void deleteOptionById(Integer id) throws Exception {
		this.delete(id);
	}

	@Override
	public List<Option> queryOptionsByPage(Page page, Option option)
			throws Exception {
		StringBuffer hql = new StringBuffer();
		hql.append("from Option");
		boolean flag = false;
		if(null != option.getOption_id()){
			hql.append(" where option_id='"+option.getOption_id()+"'");
			flag = true;
		}
		if(null != option.getCreate_person()){
			if(flag) {
				hql.append(" and create_person='"+option.getCreate_person()+"'");
			} else {
				hql.append(" where create_person='"+option.getCreate_person()+"'");
				flag = true;
			}
		}
		if(null != option.getSelect_id()){
			if(flag) {
				hql.append(" and select_id='"+option.getSelect_id()+"'");
			} else {
				hql.append(" where select_id='"+option.getSelect_id()+"'");
				flag = true;
			}
		}
		if(null != option.getOption_text()){
			if(flag) {
				hql.append(" and option_text='"+option.getOption_text()+"'");
			} else {
				hql.append(" where option_text='"+option.getOption_text()+"'");
				flag = true;
			}
		}
		if(null != option.getOption_name()){
			if(flag) {
				hql.append(" and option_name='"+option.getOption_name()+"'");
			} else {
				hql.append(" where option_name='"+option.getOption_name()+"'");
				flag = true;
			}
		}
		if(null != option.getOption_value()){
			if(flag) {
				hql.append(" and option_value='"+option.getOption_value()+"'");
			} else {
				hql.append(" where option_value='"+option.getOption_value()+"'");
				flag = true;
			}
		}
		
		return this.find(hql.toString(), page);
	}

}
