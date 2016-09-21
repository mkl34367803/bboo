<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	request.getSession().setAttribute("startPage", "orderImport");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'orderStorage.jsp' starting page</title>
<%@include file="/jsp/share/JS.jsp"%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
td {
	padding-right: 50px;
}
thead {
    background: #56a4e0;
    height: 20px;
    padding: 4px 6px 4px 10px;
    line-height: 20px;
    color: #fff;
    vertical-align: middle;
    cursor: pointer;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
	type:"post",
	dataType: "json",
	url:"${ctx }/order/order-import!getBaseAccounts.do",
	success:function(result){
		$.each(result,function(i,n){
			var img_tag="";
			
			if(n.acctype.indexOf("QUNAR")!=-1){  //去哪儿的图标
				img_tag="<img src='${ctx }/static/img/build/qunaer.png'/>";
			}else if(n.acctype.indexOf("CTRIP")!=-1){  //携程的图标
				img_tag="<img src='${ctx }/static/img/build/trip.png'/>";
			}else if(n.acctype.indexOf("TAOBAO")!=-1){  //淘宝的图标
				img_tag="<img src='${ctx }/static/img/build/taob.png'/>";
			}else if(n.acctype.indexOf("TONGC")!=-1){  //同程的图标
				img_tag="<img src='${ctx }/static/img/build/tongc.png'/>";
			}else if(n.acctype.indexOf("TUNIU")!=-1){  //途牛的图标
				img_tag="<img src='${ctx }/static/img/build/tuniu.jpg'/>";
			}
			if(n.acctype.indexOf("_N_")!=-1){
				img_tag+="(国内)";
			}else if(n.acctype.indexOf("_O_")!=-1){
				img_tag+="(国际)";
			}
			var var_tr="<tr id='tr"+i+"'><td >"+img_tag+n.describe+"</td><td>分销订单号：<input id='input"+i+"'></td><td><button id='button"+i+"' onclick='javascript:button_onclick(this)'>分销订单号导入</button></td><td id='td"+i+"' style='display:none'>"+n.id+"</td></tr>";
			$("#accountTbody").append(var_tr);
		});
	},
	error:function(XMLHttpRequest, textStatus, errorThrown){
		alert("请求出错了");
	}
	});
});

/**
 * 第一次按下导入订单按钮
 */
function button_onclick(param){
 	var buttonID=param.id;
	$("#"+buttonID).attr("disabled","disabled");
 	var index=buttonID.split('button');
 	var orderID="input"+index[1];
 	var accountID="td"+index[1];
	$.ajax({
		type : "post",
		dataType : "json",
		data : {
			orderNo : $('#'+orderID).val(),
			id : $('#'+accountID).text()
		},

		url : "${ctx }/order/order-import!isOrderExist.do",
		success : function(result) {
			if(result.error){
				alert(result.error);
				$("#"+buttonID).attr("disabled",false);
			}else if (result.exist) {
				if(confirm("订单已经存在，是否还要继续导入？")){
					button_onclick_back(param);
				}
			} else if (result.notExist) {
				if(result.success){
					var currentLockUser='<%=request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME")%>';
					window.location.href = "${ctx }/order/gj-order-detail!queryOrderByID.do?id=" + result.success+"&lockUser="+currentLockUser;
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了！");
			$("#"+buttonID).attr("disabled",false);
		}
	});
}

/**
 * 如果订单已经存在，还是点击确认按钮继续导入的方法
 */
function button_onclick_back(param) {
	var buttonID = param.id;
	var index = buttonID.split('button');
	var orderID = "input" + index[1];
	var accountID = "td" + index[1];
	$.ajax({
		type : "post",
		dataType : "json",
		data : {
			orderNo : $('#' + orderID).val(),
			id : $('#' + accountID).text()
		},

		url : "${ctx }/order/order-import!importOrderByOrderID.do",
		success : function (result) {
			if (result.error) {
				alert(result.error);
				$("#"+buttonID).attr("disabled",false);
			} else if (result.success) {
				var currentLockUser='<%=request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME")%>';
				window.location.href = "${ctx }/order/gj-order-detail!queryOrderByID.do?id=" + result.success+"&lockUser="+currentLockUser;
			}
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了！");
			$("#"+buttonID).attr("disabled",false);
		}
	});
}
</script>
</head>

<body>
	<table >
	<thead>
	<tr >
	<td >分销商描述信息</td><td>分销订单号</td><td>导入分销订单</td><td style="display: none;"></td>
	</tr>
	</thead>
	<tbody id="accountTbody">
	</tbody>
	</table>
</body>
</html>
