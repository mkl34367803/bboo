<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript" src="${ctx }/static/js/build/refund/refundReport.js"></script>
<script type="text/javascript">
var ctx = "${ctx }";
</script>
</head>
<body>
	<div class="mod-hd" style="margin-top: 10px">
		<h2>退留票报表下载</h2>
	</div>
	<!--条件查询   -->
	<form>
		<table class="m-search g-clear g-table" style="width: 100%">
			<tr class="rows">
				<td class="col">
					<span class="label">退票日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="ticketStart" id="ticketStart" type="text" class="cinput" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="ticketEnd" id="ticketEnd" type="text" class="cinput" >
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">起飞日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="departureStart" id="departureStart" type="text" class="cinput" onclick="laydate();" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="departureEnd" id="departureEnd" type="text" class="cinput" onclick="laydate();" >
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">分销商铺</span>
					<div class="label-info">
						<!-- <span class="g-ipt-text"> <input name="caiGouProjo" type="text" > </span> -->
						<span class="g-select"> 
							<select name="distributor">
								<option value="">全部</option>
								<option value="T">淘宝</option>
								<option value="Q">去哪儿</option>
								<option value="C">携程</option>
								<option value="H">航班管家</option>
								<option value="K">酷讯</option>
								<option value="N">途牛</option>
								<option value="O">同程</option>
							</select> 
						</span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col" colspan="3" style="width: 800px;">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" name="refundBtn" class="g-btn" value="退票下载" />
						<input type="button" name="reserveBtn" class="g-btn" value="留票下载" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>