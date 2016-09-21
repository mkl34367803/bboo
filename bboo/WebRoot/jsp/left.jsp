<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/jsp/share/MenuJs.jsp"%>
<%@include file="/jsp/share/MenuCss.jsp"%>
<SCRIPT language=javascript>
<!--
	function table_display(tab_id, img_id) {//显示隐藏程序 
		var tab_id;//表格ID 
		var img_id;//图片ID 
		var default_img = "${ctx}/images/left_dot.gif";//默认图片 
		var on_img = "${ctx}/images/left_open.gif";//打开时图片 
		var off_img = "${ctx}/images/left_dot.gif";//隐藏时图片 
		if (tab_id.style.display == "none") {//如果为隐藏状态 
			tab_id.style.display = "";//切换为显示状态 
			img_id.src = on_img;
		}//换图 
		else {//否则 
			tab_id.style.display = "none";//切换为隐藏状态 
			img_id.src = off_img;
		}//换图 
	}
	function init() {
		window.top.main.location.href = document.getElementById('menu1')
				.getAttribute("href", 2)
	}
	window.onload = init;
//-->
</SCRIPT>
</head>
<body>
	<table height="100%" cellspacing="0" cellpadding="0" width="100%"
		border="0">
		<tbody>
			<tr>
				<td align="center" valign="top"><table cellspacing="0"
						cellpadding="0" width="150" border="0">
						<tbody>
							<tr>
								<td height="10"></td>
							</tr>
						</tbody>
					</table>
					<table style="CURSOR: hand" onclick="table_display(tab1,i1)"
						height="30" cellspacing="0" cellpadding="3" width="150"
						background="${ctx }/images/nav_bg_22.png" border="0">
						<tbody>
							<tr>
								<td align="right" width="23"><img id="i1"
									src="${ctx}/images/left_open.gif" /></td>
								<td width="115" align="left" class="white" style="vertical-align: middle;"
									style="color: #36648B"><b>${rootMenuName }</b></td>
							</tr>
						</tbody>
					</table>
					<table id="tab1" cellspacing="0" cellpadding="3" width="150"
						border="0">
						<tbody>
							<tr>
								<td height="8"  bgcolor="#E0EEEE"></td>
							</tr>
							<c:forEach items="${menuList}" var="menu" varStatus="i">
								<tr>
									<td align="left" bgcolor="#E0EEEE" align="center" height="25"><a
										style="color: #000080" href="${ctx }${menu.value}"
										target="main" id="menu${i.count}"> <img height="15"
											src="${ctx}/images/arrow_1.gif" width="15" />${menu.name
											}</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table cellspacing="0" cellpadding="0" width="150" border="0">
						<tbody>
							<tr>
								<td height="8"  bgcolor="#E0EEEE"></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>