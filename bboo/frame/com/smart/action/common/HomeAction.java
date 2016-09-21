package com.smart.action.common;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.smart.framework.base.BaseAction;
/**
 * 
 * @Description
 * @author ERICH
 * @ClassName: HomeAction
 * @since 2012-12-16 上午12:29:20
 * @Version 1.0
 */
@Results( { @Result(name = "home_page", location = "/jsp/home.jsp") })
public class HomeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute() {

		return "home_page";
	}
}
