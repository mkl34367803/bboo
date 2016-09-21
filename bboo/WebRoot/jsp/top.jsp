<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/jsp/share/MenuJs.jsp"%>
<%@include file="/jsp/share/MenuCss.jsp"%>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/frm.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#centshop").click(function() {
			$.ajax({
				type : "POST",
				url : "${ctx}/centshop/cent-shop!toCentShop.do",
				async : false,
				success : function(data) {
					if (data != "1") {
						window.open("");
					} else {
						window.location = "${ctx}/login.jsp";
					}
				}
			});
		});
	});
</script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
	<table background="" border=0 height=110 cellSpacing=0 cellPadding=0
		width="100%" bgColor="#FCFCFC">
		<tr>
			<td></td>
		</tr>
		<tr>
			<td align="center" rowspan="4" width="160"></td>
		</tr>
		<tr>
			<td><font color="#8E388E">当前用户：${name }</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
				href="${ctx }/login!logout.do" class="huang" target="_top"><font
					color="#8E388E" >安全退出</font></a></td>
			<td align="center" class="huang"><font color="#8E388E">在线人数${onLineUsers
				}人&nbsp;&nbsp;&nbsp;&nbsp;版本号：beta
					v1.1.0720</font></td>

		</tr>
		<tr>
			<td valign="bottom">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					height="30">
					<tr>
						<c:forEach items="${SESSION_KEY_CURRENT_USER_MENU}" var="resource">
							<td width="80" background="${ctx }/images/nav_bg_22.png"
								style="vertical-align: middle;"><a
								href="${ctx }/common/left.do?resourceId=${resource.resourceId }"
								target="left" class="zi"><b style="color:  #36648B">${resource.name }</b></a></td>
						</c:forEach>
						<td>&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<TR>
			<TD bgColor=#D0A6DD height=3></TD>
		</TR>
	</TABLE>
</body>
</html>