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

<script src="${ctx }/static/js/build/pageNav/pageNav.js" type="text/javascript"></script>
<script src="${ctx }/static/js/build/order/laydate.js" type="text/javascript"></script>
<script src="${ctx }/layer/layer/layer.js" type="text/javascript"></script>
<script src="${ctx }/static/js/build/highcharts/highcharts.js"></script>

<script type="text/javascript" src="${ctx }/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<title>出票订单管理系统</title>