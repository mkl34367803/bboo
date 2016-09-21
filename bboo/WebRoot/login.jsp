<%@ page language="java" pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>国际订单系统</title>
<meta name="keywords" content="帮票网络科技有限公司" />
<style>
.picshow_change {
	left: auto;
}
</style>

</head>
<body>
	<form name="form1" action="<%=request.getContextPath()%>/login.do"
		method="post" onsubmit="return checkForm();">
		<table width="500" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td height="120">&nbsp;</td>
			</tr>
			<tr>
				<td height="327"><table width="500" align="right"
						cellpadding="0" cellspacing="1" bgcolor="#FFFFFF">
						<tr>
							<td height="180"
								background="<%=request.getContextPath()%>/images/suo.jpg">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="47%" height="2" align="center" style="color: red;"
											colspan="4"><font size="3"> <%
 	String ainfo = request.getParameter("AuthorizationReturnInfo");
 	if (ainfo != null)
 		out.print(ainfo);
 %>
										</font></td>
									</tr>
									<tr>
										<td width="47%" height="28" align="right">用户名：</td>
										<td width="53%" colspan="3">&nbsp; <input
											name="loginName" type="text" id="loginName"
											style="width: 150px; font-weight: bold;"
											onkeypress="clean(this)" onmousedown="clean(this)"
											onchange="clean(this);" /><span id="span_loginName"
											style="color: red;"></span></td>
									</tr>
									<tr>
										<td height="28" align="right">密 码：</td>
										<td colspan="3">&nbsp; <input name="password"
											type="password" id="password" style="width: 150px;"
											onkeypress="clean(this)" onmousedown="clean(this)"
											onchange="clean(this);" /><span id="use_vkboard"></span><span
											id="span_password" style="color: red;"></span></td>
									</tr>
									<tr>
										<td height="28" align="right">验证码：</td>
										<td width="53%" colspan="3">&nbsp; <input name="image"
											type="text" id="image"
											style="width: 150px; font-weight: bold;"><span
											id="span_image" style="color: red;"></span>
										</td>
									</tr>
									<tr>
										<td height="28" align="center"></td>
										<td colspan="3" height="1" align="center"><img
											src="./image.jpg"
											onclick="this.src='<%=request.getContextPath()%>/image.jpg?'+(new Date()) "
											id="verify_img" /><a href="javascript:verifyImgRefresh()"
											class="blue_font_12a">看不清，换一张？</a></td>
									</tr>
									<tr>
										<td colspan="4" height="2"></td>
									</tr>
									<tr>
										<td height="38" align="right">&nbsp;</td>
										<td height="30" colspan="3">&nbsp;&nbsp;<input
											type="image"
											src="<%=request.getContextPath()%>/images/l3.gif" /> <input
											type="image"
											src="<%=request.getContextPath()%>/images/l4.gif"
											onclick="this.form.reset(); return false;" /></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td align="left" colspan="3"><span style="color: red;"
											id="message">${message }</span></td>
									</tr>

								</table>
							</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="35" align="center">&nbsp;</td>
			</tr>
		</table>
		<script language="javascript">
			page_init()
		</script>
	</form>
</body>
</html>
