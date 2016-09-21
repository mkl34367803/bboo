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

<link rel="stylesheet" href="${ctx }/static/css/build/list_v5.css">
<link rel="stylesheet" href="${ctx }/static/css/build/order_detail.css">
<link rel="stylesheet" href="${ctx }/static/css/build/pageNav/pageNav.css">

<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui-1.3.3/themes/icon.css">

<title>国际订单管理系统</title>