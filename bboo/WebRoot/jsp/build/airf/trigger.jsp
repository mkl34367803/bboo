<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>触发保持航班基础数据任务</title>
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

<script type="text/javascript">
	$(document).ready(
			function() {
				$('#j_init_flight_nos').click(
						function() {
							showAlert("确认要初始化信息吗？", 'confirm', function() {
								showLoading("正在初始化中... ...");
								$.get('../airf/air-flight!init.do?v='
										+ Math.round(Math.random() * 100000),
										function(data) {
											showAlert(data);
										});
							});
						});
				$('#j_init_share_nos').click(
						function() {
							showAlert("确认要初始化共享信息吗？", 'confirm', function() {
								showLoading("正在初始化中... ...");
								$.get('../airf/share-flight!initShare.do?v='
										+ Math.round(Math.random() * 100000),
										function(data) {
											showAlert(data);
										});
							});
						});
				$('#j_init_share_price_nos').click(
						function() {
							showAlert("确认要初始化共享航班运价吗？", 'confirm', function() {
								showLoading("正在初始化中... ...");
								$.get('../airf/share-flight!initPrice.do?v='
										+ Math.round(Math.random() * 100000),
										function(data) {
											showAlert(data);
										});
							});
						});
				$('#j_load_flight_share_nos').click(
						function() {
							p = $(this).attr('name');
							$('#' + p).html('下载中');
							$.get('../airf/air-flight!loadShareFlt.do?v='
									+ Math.round(Math.random() * 100000),
									function(data) {
										showAlert(data);
										$('#' + p).html('下载完成');
									});
						});
			})
</script>
</head>

<body>

	<table class="g-table table-list">
		<tbody class="table-bd">
			<tr>
				<td style="text-align: left;"><input class="g-btn"
					type="button" name="initData" id="j_init_flight_nos" value="初始化航班信息" />
					初始化信息：${msg }</td>
			</tr>
			<tr>
				<td style="text-align: left;"><input class="g-btn"
					type="button" name="initShareData" id="j_init_share_nos"
					value="初始化共享航班数据" /></td>
			</tr>
			<tr>
				<td style="text-align: left;"><input class="g-btn"
					type="button" name="initSharePriceData" id="j_init_share_price_nos"
					value="初始化共享航班运价" /></td>
			</tr>
			<tr>
				<td style="text-align: left;"><input class="g-btn"
					type="button" name="loadsharefltdata" id="j_load_flight_share_nos"
					value="下载共享航班信息" /></td>
			</tr>
		</tbody>
	</table>

</body>
</html>
