package com.smart.action.common;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.framework.base.BaseAction;
import com.smart.framework.security.SecurityContext;

/**
 * 顶部ACTION
 * 
 * @Description
 * @author ERICH
 * @ClassName: TopAction
 * @since 2012-12-16 上午12:29:49
 * @Version 1.0
 */
@Results({ @Result(name = "success", location = "/jsp/top.jsp") })
public class TopAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String name;// 用户姓名

	public String execute() {
		name = SecurityContext.getUser().getName();
		return "success";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
