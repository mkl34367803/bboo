<%@ page pageEncoding="UTF-8"%>
<%
	response.addHeader("Cache-control", "No-cache"); 
	response.addDateHeader("Expires", 0); 
	request.setAttribute("ctx",request.getContextPath());
%>
<!-- 在此页面中加入CSS,JS等引用 -->
<link type="text/css" rel="stylesheet" href="${ctx }/static/css1.css?v=20130403"></link>
<link type="text/css" rel="stylesheet" href="${ctx }/static/datepicker_css.min.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx }/static/colorbox02.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx }/static/colorbox02.css"></link>
<link type="text/css" rel="stylesheet" href="${ctx }/static/menu.css"></link>
<title>出票订单管理系统</title>