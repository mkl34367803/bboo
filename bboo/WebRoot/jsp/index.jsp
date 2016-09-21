<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<%@include file="/jsp/share/MenuJs.jsp" %>
    	<%@include file="/jsp/share/MenuCss.jsp" %>
  </head>
  
 <frameset border="0" frameSpacing="0" rows="85,*" frameBorder="no" >

	<frame id="top" src="<%=request.getContextPath() %>/common/top.do" noresize="noresize" scrolling="no"/>
	<FRAMESET cols=162,*>
		<FRAME name="left" src="${ctx }/common/left.do" noResize scrolling=no target="main">
		<FRAME name="main" src="">
	</FRAMESET>
</frameset>
<noframes></noframes>
</html>
