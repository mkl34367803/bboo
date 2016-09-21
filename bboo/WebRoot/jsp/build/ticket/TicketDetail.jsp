<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>查询客票信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<link type="text/css" rel="stylesheet"
	href="${ctx }/static/css/build/list_v5.css" />
<style type="text/css">
a.g-btn {
	color: white;
	min-width: 10px;
	width: 50px;
	height: 18px;
}

a.g-btn_tri {
	color: white;
	min-width: 10px;
	width: 50px;
	height: 18px;
}

a.g-btn_query {
	color: white;
	min-width: 10px;
	width: 50px;
	height: 18px;
}

a.g-btn-detail {
	color: white;
	min-width: 10px;
	width: 50px;
	height: 18px;
}

a.g-btn-down {
	color: white;
	min-width: 10px;
	width: 50px;
	height: 18px;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#j_query_ticket_nos').click(function() {

			$('#tno').val($.trim($('#tno').val()));
			var tno = $('#tno').val();
			var pname = $('#pname').val();
			if (tno == '') {
				showAlert("票号不能为空");
				return;
			}
			$('.g-btn').val('正在查询，请稍后...');
			$('.g-btn').attr({
				"disabled" : "disabled"
			});
			$("#info").html('');

			$.post("../ticket/ticket-hand!queryTicket.do?t=" + Math.random(), {
				tno : tno,
				pname : pname
			}, function(data) {
				var dataObj = eval("(" + data + ")");//转换为json对象 
				$("#info").html(dataObj.root.data);
				$('.g-btn').removeAttr("disabled");//将按钮可用
				$('.g-btn').val('查询');
			});

		});
	})
</script>
</head>

<body style="margin: 10px">
	<div class="mod-hd">
		<h2>客票状态查询</h2>
	</div>
	<table class="g-table order-table">
		<tr>
			<td>${ERR_001 }${SUC_001 }</td>
		</tr>
	</table>
	<form action="${ctx }/ticket/ticket-hand!queryTicket.do" name="form"
		method="post">
		<table class="g-table table-list">
			<tbody class="table-bd">
				<tr>
					<td style="width: 70px;">票号：</td>
					<td style="width: 105px;"><input class="cinput"
						style="width:200px;height:27px" id="tno" name="tno"
						value="${tno }" /></td>
					<td style="width: 70px;">旅客姓名：</td>
					<td style="width: 105px;"><input class="cinput"
						style="width:200px;height:27px" id="pname" name="pname"
						value="${pname }" /></td>
					<td style="text-align: left;"><input class="g-btn"
						type="button" name="querydata" id="j_query_ticket_nos" value="查询" /></td>
				</tr>
			</tbody>
		</table>
		<table>
			<thead>
				<tr>
					<td><textarea name="info" id="info" rows="20" cols="100"
							style="overflow-x:hidden;overflow-y:hidden;background: black;color:#00CD00;font-size: 20px"></textarea></td>
				</tr>
			</thead>
		</table>
	</form>
</body>
</html>
