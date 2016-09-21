<%@page isELIgnored="false"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="p" uri="/WEB-INF/tlds/page.tld"%>
<%@ taglib prefix="f" uri="/WEB-INF/tlds/security.tld"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld"%>
<%@ taglib prefix="oms" uri="/WEB-INF/tlds/oms.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	response.addHeader("Cache-control", "No-cache");
	response.addDateHeader("Expires", 0);
	request.setAttribute("ctx", request.getContextPath());
%>