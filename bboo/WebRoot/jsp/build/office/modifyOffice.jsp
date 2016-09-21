<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modifyOffice.jsp' starting page</title>
	<%@include file="/jsp/share/JS.jsp"%>
	<%@include file="/jsp/share/CSS.jsp"%>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="${ctx }/static/js/build/office/officeManage.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	var ctx = "${ctx}";
		var id=<%=request.getParameter("id")%>;
		$(document).ready(function() {
			modifyOffice(id);
		});
	</script>
  </head>
  
  <body>
    <form action="">
    	<span>office</span><input type="text" name="office" id="office" />
    	<span>offtypes</span><input type="text" name="offtypes" id="offtypes"/>
    	<span>appkey</span><input type="password" name="appkey" id="appkey"/>
    	<span>打票机台数</span><input type="text" name="etdzNum" id="etdzNum"/>
    	<span>启用禁用</span>
    	<select name="isu" id="isu">
    			<option value="1">启用</option>
    			<option value="2">禁用</option>
    	</select>
    	<input type="submit" value="确定">
    </form>
  </body>
</html>
