<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>导入降舱航班信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<link type="text/css" rel="stylesheet"
	href="${ctx }/static/css/build/list_v5.css" />
<link type="text/css" rel="stylesheet"
	href="${ctx }/static/css/build/order_detail.css" />
<script src="${ctx }/static/js/build/tickets/data.js"
	type="text/javascript"></script>
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
	$(document).ready(
			function() {
				$('#j_upload_data_file_info').click(function() {
					var s = $(":input[name='file']").val();
					if (s == null || $.trim(s) == '') {
						showAlert("上传文件不能为空");
						return;
					}
					if (s.indexOf(".xls") < 0) {
						showAlert("上传文件必须是以后缀名xls结尾的文件");
						return;
					}
					showAlert("确认要上传文件吗？", 'confirm', function() {
						showLoading("正在上传文件信息");
						document.uploadForm.submit();
					});
				});

				$('#j_query_datas').click(function() {
					document.form.submit();
				});

				$('.g-btn_tri').click(
						function() {
							p = $(this).attr('name');
							var btxt = $('#' + p + 'Q').html();
							if (btxt == '执行中') {
								showAlert("文件正在执行，请稍后查询");
								return;
							}
							showAlert("确认要触发查询操作吗？", 'confirm', function() {
								$.get('../drop/drop-file!trigger.do?id=' + p
										+ '&v='
										+ Math.round(Math.random() * 100000),
										function(data) {
											showAlert(data);
											$('#' + p + 'Q').html('执行中');
										});
							});
						});

				$('.g-btn-detail').click(
						function() {
							p = $(this).attr('name');
							//console.log(p);
							$.get('../drop/drop-file!detail.do?id=' + p + '&v='
									+ Math.round(Math.random() * 100000),
									function(data) {
										showAlert(data);
									});
						});

				$('.g-btn-down').click(
						function() {
							p = $(this).attr('name');
							$('#' + p).html('下载中');
							$.get('../ticket/ticket-ctrl!down.do?fn=' + p
									+ '&v='
									+ Math.round(Math.random() * 100000),
									function(data) {
										showAlert(data);
										$('#' + p).html('下载完成');
									});
						});

			})
</script>
</head>

<body style="margin: 10px">
	<div class="mod-hd">
		<h2>导入订单航班数据</h2>
	</div>
	<table class="g-table order-table">
		<tr>
			<td>${ERR_001 }${SUC_001 }</td>
		</tr>
		<tr>
			<td><a href="${ctx}/jsp/build/models/DropFlight.xls"
				style="display:inline-block;" class="pur_btn_css">模版下载</a></td>
		</tr>

		<tr>
			<td>
				<form action="${ctx }/drop/drop-file!impdata.do"
					enctype="multipart/form-data" name="uploadForm" method="post">
					<input class="g-btn" type="file" name="file"
						style="border: 0;color:white;cursor:hand;width: 175px;" /> <input
						class="g-btn" type="button" name="upLoadFile"
						id="j_upload_data_file_info" value="上传" />
				</form>
			</td>
		</tr>
	</table>


	<form action="${ctx }/drop/drop-file!querylist.do" name="form"
		method="post">
		<table class="g-table table-list">
			<tbody class="table-bd">
				<tr>
					<td style="width: 70px;">导入日期：</td>
					<td style="width: 105px;"><input class="cinput"
						style="width:100px;height:27px" id="impdate" name="impdate"
						value="${impdate }" /></td>
					<td style="text-align: left;"><input class="g-btn"
						type="button" name="querydata" id="j_query_datas" value="查询" /></td>
				</tr>
			</tbody>
		</table>


		<table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th class="pcol14">文件号</th>
					<th class="pcol15">文件名称</th>
					<th class="pcol15">文件地址</th>
					<th class="pcol10">文件大小</th>
					<th class="pcol08">数据条数</th>
					<th class="pcol10">操作人</th>
					<th class="pcol10">状态</th>
					<th class="pcol18">操作</th>
				</tr>
			</thead>

			<tbody class="table-bd">
				<c:forEach items="${list }" var="t">
					<tr>
						<td>${t.fileno }</td>
						<td>${t.fileName }</td>
						<td><a href="${ctx}${t.filePath }">.../${t.fileName }</a></td>
						<td>${t.fileSize }K</td>
						<td>${t.count }</td>
						<td>${t.operator }</td>
						<td><c:if test="${t.scanStatus eq 0 }">等待查询</c:if> <c:if
								test="${t.scanStatus eq 1 }">查询中</c:if> <c:if
								test="${t.scanStatus eq 2 }">查询完成</c:if></td>
						<td><a class="g-btn-detail" href="javascript:void(0);"
							name="${t.id }" id="${t.id }">详情</a> <c:if
								test="${t.scanStatus eq 1}">
								<a class="g-btn_tri" href="javascript:void(0);" name="${t.id }"
									id="${t.id }Q">执行中</a>
							</c:if> <c:if test="${t.scanStatus eq 0}">
								<a class="g-btn_tri" href="javascript:void(0);" name="${t.id }"
									id="${t.id }Q">未触发</a>
							</c:if> <c:if test="${t.scanStatus eq 2}">
								<a class="g-btn_tri" href="javascript:void(0);" name="${t.id }"
									id="${t.id }Q">已完成</a>
							</c:if><br> ${t.createTime }</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="8"><p class="page">
							<p:page form="form" action="${ctx }/drop/drop-file!querylist.do" />
						</p></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" value="imporderflightfordrops" name="page_name"
			id="page_name" />
	</form>
</body>
</html>
