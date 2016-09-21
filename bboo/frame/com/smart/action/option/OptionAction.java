package com.smart.action.option;

import java.util.Date;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.Option;
import com.smart.entity.Select;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.service.option.OptionServiceImpl;
import com.smart.service.select.SelectServiceImpl;

/**
 * 
 * @Description
 * @author ERICH
 * @ClassName: OptionAction
 * @since 2012-12-16 上午12:30:00
 * @Version 1.0
 */
@Results({ @Result(name = "option_list", location = "/jsp/select/option_list.jsp") })
public class OptionAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OptionServiceImpl optionService;

	@Autowired
	private SelectServiceImpl selectService;

	public String saveOption() throws SqlException {
		OptionForm oForm = new OptionForm();
		this.populate(request, oForm);
		Option entity = new Option();
		entity.setCreate_person(SecurityContext.getUser().getName());
		entity.setCreate_time(new Date());
		entity.setDescrtption(oForm.getDecription());
		entity.setIs_display(oForm.getDeleted());
		entity.setLast_edittime(new Date());
		entity.setOption_name(oForm.getOptionName());
		entity.setOption_text(oForm.getOptionText());
		entity.setOption_value(oForm.getOptionValue());
		Select sel = new Select();
		sel.setSelect_id(Integer.parseInt(oForm.getSelectId()));
		entity.setSelect(sel);
		optionService.insertOpton(entity);

		setOptions(sel.getSelect_id());
		return "option_list";
	}

	public String deleteOption() throws SqlException {

		int id = this.getParameterInt("selectId");
		int optionId = this.getParameterInt("optionId");

		optionService.deleteOption(optionId);

		setOptions(id);

		return "option_list";
	}

	private void setOptions(int id) throws SqlException {
		Select sbean = null;

		sbean = selectService.getSelectById(id);

		request.setAttribute("selectObj", sbean);
		request.setAttribute("optionList", sbean.getOptions());
	}

}
