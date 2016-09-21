package com.smart.action.port;

import java.util.Date;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.base.exception.SqlException;
import com.smart.entity.InterfaceAccount;
import com.smart.framework.base.BaseAction;
import com.smart.service.port.InterfaceAccountServiceImpl;


// @InterceptorRefs({@InterceptorRef("trimStack")})
/**
 * @Description 接口账户管理
 * @author ERICH
 * @ClassName: InterfaceAccountAction
 * @since 2012-11-6 下午11:45:08
 */
@Results({
		@Result(name = "add_interface_account", location = "/jsp/interface/interface_add.jsp"),
		@Result(name = "interface_account_list", location = "/jsp/interface/interface_list.jsp"),
		@Result(name = "query", location = "/port/interface-account!queryInterfaceAccount.do", type = "redirect") })
public class InterfaceAccountAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	InterfaceAccountServiceImpl interfaceAccountService;

	public String addInterfaceAccount() {

		return "add_interface_account";
	}

	@Action(value = "saveApiAccount", results = { @Result(name = "query", location = "/port/interface-account!queryInterfaceAccount.do", type = "redirect") }, interceptorRefs = {
			@InterceptorRef("trimStack"), @InterceptorRef("defaultStack") })
	public String saveInterfaceAccount() throws SqlException {
		InterfaceAccountForm iacForm = new InterfaceAccountForm();
		this.populate(request, iacForm);
		InterfaceAccount iac = new InterfaceAccount();
		iac.setApicookie(iacForm.getApicookie());
		iac.setApidirections(iacForm.getApidirections());
		iac.setApiname(iacForm.getApiname());
		iac.setApipw(iacForm.getApipw());
		iac.setApirequestway(iacForm.getApirequestway());
		iac.setApisession(iacForm.getApisession());
		iac.setApisn(iacForm.getApisn() != null ? Integer.parseInt(iacForm
				.getApisn()) : 1);
		iac.setApitype(iacForm.getApitype());
		iac.setApiurl(iacForm.getApiurl());
		iac.setApiuser(iacForm.getApiuser());
		iac.setCreatetime(new Date());
		iac.setUniquelyid(Integer.parseInt(iacForm.getUniquelyID()));
		iac.setAction(iacForm.getRequest_action());
		iac.setSource(iacForm.getInterface_source());
		interfaceAccountService.saveInterfaceAccount(iac);

		return "query";
	}

	public String queryInterfaceAccount() throws SqlException {

		request.setAttribute("slectList",
				interfaceAccountService.getAllInterfaceAccount(page));

		return "interface_account_list";
	}

	@Action(value = "deleteApiAccount", results = { @Result(name = "query", location = "/port/interface-account!queryInterfaceAccount.do", type = "redirect") })
	public String deleteInterfaceAccount() throws SqlException {

		int id = Integer.parseInt(request.getParameter("api_id"));

		interfaceAccountService.deleteInterfaceAccount(id);

		return "query";
	}

}
