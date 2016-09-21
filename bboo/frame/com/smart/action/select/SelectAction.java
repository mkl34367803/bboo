package com.smart.action.select;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Date;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.Select;
import com.smart.framework.base.ALog;
import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;
import com.smart.service.log.Logger;
import com.smart.service.select.SelectServiceImpl;

/**
 * 
 * @Description
 * @author ERICH
 * @ClassName: SelectAction
 * @since 2012-12-16 上午12:30:58
 * @Version 1.0
 */
@InterceptorRefs({@InterceptorRef("logStack")})
@Results({
		@Result(name = "success", location = "/jsp/select/select_list.jsp"),
		@Result(name = "select_add", location = "/jsp/select/select_add.jsp"),
		@Result(name = "option_list", location = "/jsp/select/option_list.jsp"),
		@Result(name = "query", location = "/select/select!querySelect.do", type = "redirect") })
public class SelectAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SelectServiceImpl selectService;

	@Autowired
	private Logger LOG;

	/**
	 * 查询下拉菜单数据
	 * 
	 * @return
	 * @throws SqlException
	 */
	public String querySelect() throws SqlException {
		request.setAttribute("slectList", selectService.getAllSelect(page));

		return "success";
	}

	public String addSelect() {
		return "select_add";
	}

	public String addOption() throws SqlException {
		int id = Integer.parseInt(getParameter("selectId"));
		Select entity = null;
		entity = selectService.getSelectById(id);

		request.setAttribute("selectObj", entity);
		request.setAttribute("optionList", entity.getOptions());
		return "option_list";
	}

	/**
	 * 保存SELECT表数据
	 * 
	 * @return
	 */
	@ALog
	public String saveSelect() throws SqlException {
		SelectForm sForm = new SelectForm();
		this.populate(request, sForm);
		Select entity = new Select();
		entity.setDescrtption(sForm.getDescription());
		entity.setIs_display(sForm.getDeleted());
		entity.setSelect_name(sForm.getSelectName());
		entity.setCreate_time(new Date());
		entity.setLast_edittime(new Date());
		entity.setCreate_person(SecurityContext.getUser().getName());
		int id;
		id = selectService.insertSelect(entity);
		LOG.save(request, SecurityContext.getUser().getName()
				+ "：创建一个下拉菜单NAME选项！", String.valueOf(id));

		return "query";
	}

	public String checkSelectUnique() throws SqlException, IOException {

		String resout = "";
		request.setCharacterEncoding("UTF-8");
		String value = new String(request.getParameter("value").getBytes(
				"ISO-8859-1"), "UTF-8");
		boolean isUnique = false;
		try {
			isUnique = selectService.checkUniqueSelectName(value);
		} catch (SqlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8"); // 先指定输出流的编码
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter(); // 再拿到输出对象

		if (isUnique) {
			resout = "pass";
		} else {
			resout = "refuse";
		}
		out.print(resout);

		return null;
	}
	@ALog
	public String deleteSelect() throws SqlException {
		int id = Integer.parseInt(request.getParameter("selectId"));
		selectService.deleteSelectById(id);

		return "query";
	}

	public String updateSelect() throws SqlException {
		SelectForm sForm = new SelectForm();
		this.populate(request, sForm);
		Select entity = null;
		try {
			entity = selectService.getSelectById(Integer.parseInt(sForm
					.getSelectId()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entity.setSelect_id(Integer.parseInt(sForm.getSelectId()));
		entity.setSelect_name(sForm.getSelectName());
		entity.setDescrtption(sForm.getDescription());
		entity.setLast_edittime(new Date());
		entity.setIs_display(sForm.getDeleted());

		selectService.updateSelectById(entity);

		return "query";
	}

}
