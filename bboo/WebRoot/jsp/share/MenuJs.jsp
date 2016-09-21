<%@ page pageEncoding="UTF-8"%>
<%
	response.addHeader("Cache-control", "No-cache");
	response.addDateHeader("Expires", 0);
	request.setAttribute("ctx", request.getContextPath());
%>
<script src="${ctx }/static/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${ctx }/static/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="${ctx }/static/js/jquery-datepicker.min.js" type="text/javascript"></script>
<script src="${ctx }/static/js/newjs5.js?v=20141126" type="text/javascript"></script>

<script src="${ctx }/static/js/colorbox/jquery.colorbox-min.js" type="text/javascript"></script>
<script src="${ctx }/static/js/colorbox/jquery.colorbox.js" type="text/javascript"></script>
<title>出票订单管理系统</title>