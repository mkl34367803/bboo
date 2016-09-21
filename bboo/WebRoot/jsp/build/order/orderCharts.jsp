<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/order/orderQuery.js" type="text/javascript"></script>
<script src="${ctx }/static/js/build/order/orderCharts.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div>
		<div class="mod-hd" style="margin-top: 10px">
			<h2>利润统计</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col" style="width: 190px;">
						<span class="label">航班类型</span>
						<div class="label-info" style="width: 120px;">
							<label> <input name="flightClass" value="N" type="radio" class="g-input-check" checked="checked">国内</label> 
							<label> <input name="flightClass" value="I" type="radio" class="g-input-check">国际</label>
						</div>
					</td>
					<td class="col">
						<span class="label">出票日期</span>
						<div class="label-info">
							<div class="qcbox start-date">
								<input id="startDate" name="startDate" type="text" class="cinput">
							</div>
							<span class="split">到</span>
							<div class="qcbox stop-date">
								<input id="endDate" name="endDate" type="text" class="cinput">
							</div>
						</div>
					</td>
					<td class="col">
						<span class="label">类型</span>
						<div class="label-info">
							<label> <input name="type" value="ticketNum" type="radio" class="g-input-check" checked="checked">票量</label> 
							<label class="profitClass"> <input name="type" value="profit" type="radio" class="g-input-check">利润</label>
							<label class="saleroomClass"> <input name="type" value="saleroom" type="radio" class="g-input-check">销售额</label>
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col" colspan="3" style="width: 100%">
						<span class="label">显示类型</span>
						<div class="label-info">
							<!-- <label class=""> <input name="showType" value="percent" type="checkbox" class="g-input-check">百分比</label> -->
							<label class=""> <input name="showType" value="tongQi" type="checkbox" class="g-input-check">同期</label>
							<label class="dayCheck"> <input name="showType" value="day" type="checkbox" class="g-input-check">日期</label>
							<label class=""> <input name="showType" value="addPercent" type="checkbox" class="g-input-check">增长率</label>
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col" colspan="3" style="width: 100%">
						<span class="label">统计类型</span>
						<div class="label-info">
							<label> <input name="chartType" value="carrier" type="radio" class="g-input-check" checked="checked">航司</label> 
							<label> <input name="chartType" value="purchasePlace" type="radio" class="g-input-check">采购渠道</label>
							<label> <input name="chartType" value="operator" type="radio" class="g-input-check">出票员</label>
							<label> <input name="chartType" value="ageDes" type="radio" class="g-input-check">乘机人类型</label>
							<label> <input name="chartType" value="distributor" type="radio" class="g-input-check">分销渠道</label>
							<label> <input name="chartType" value="shopName" type="radio" class="g-input-check">店铺名称</label>
							<label> <input name="chartType" value="createTime" type="radio" class="g-input-check">订单生成时间</label>
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">&nbsp;</span>
						<div class="label-info">
							<input type="button" id="searchButton" class="g-btn" value="统计" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="container" style="min-width: 400px; height: 400px;">
		
	</div>
	<br>
	<div id="tongqiContainer" style="min-width: 400px; height: 400px;">
		
	</div>
	
</body>
</html>