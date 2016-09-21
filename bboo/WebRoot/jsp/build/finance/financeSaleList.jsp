<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>财务报表</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/finance/financeSaleList.js" type="text/javascript"></script>
<style type="text/css">
.white_content {
	top: 30%;
	left: 24%;
	width: 520px;
	height: auto;
	max-height: 270px;
	padding: 10px;
}
.show_div {
    border-radius: 10px;
    width: 800px;
    height: 500px;
    overflow-x: hidden;
    overflow-y: scroll;
    border: 1px solid #ccc;
    background: #fff;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 9999;
}

.xiaoShouSelect {
	position: absolute;
	background-color: #fff;
	width: 198px;
	height: auto;
	z-index: 70;
	display: none;
	padding: 5px 5px 5px 5px;
	border: solid 1px #e4e4e4;
	max-height: 400px;
}
.xiaoShouSelect ul li {
	margin-bottom: 5px;
}

}
</style>
<script type="text/javascript">
	//显示非当前页
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getFinanceSaleList(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	var ctx = "${ctx}";
</script>
</head>
<body>
	<div class="mod-hd" style="margin-top: 10px">
		<h2>上传的数据</h2>
	</div>
	<!--条件查询   -->
	<form>
		<table class="m-search g-clear g-table" style="width: 100%">
			<tr class="rows">
				<td class="col">
					<span class="label">出票日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="chuPiaoDate" type="text" class="cinput" onclick="laydate();" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="toChuPiaoDate" type="text" class="cinput" onclick="laydate();" >
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">起飞日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="fightDate" type="text" class="cinput" onclick="laydate();">
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="toFightDate" type="text" class="cinput" onclick="laydate();">
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">采购平台</span>
					<div class="label-info">
						<!-- <span class="g-ipt-text"> <input name="caiGouProjo" type="text" > </span> -->
						<span class="g-select"> 
							<select id="caiGouProjo">
								<option value="">全选</option>
							</select> 
						</span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">销售平台</span>
					<div class="label-info">
						<!-- <span class="g-ipt-text"> <input name="xiaoShouProjo" type="text" > </span> -->
						<span class="g-ipt-text"> 
							<input name="xiaoShouProjoCount" type="text" value="全选" > 
						</span>
						<!-- <span class="g-select"> 
							<select id="xiaoShouProjoSel">
							</select> 
						</span> -->
						<div class="xiaoShouSelect" id="xiaoShouSelect">
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">出票员</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="chuPiaoRen" type="text" > </span>
					</div>
				</td>
				<td class="col">
					<span class="label">票号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="fareNO" type="text"> </span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">订单号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="orderNO" type="text" > </span>
					</div>
				</td>
				<td class="col" colspan="2">
					<span class="label">文件号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="fileNO" type="text" > </span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col" colspan="3" style="width: 800px;">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" name="queryBtn" class="queryBtn g-btn" style="color: white;font-size: 14px;" value="查询" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="profitBtn" class="profitBtn g-btn" value="利润" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="downloadBtn" class="downloadBtn g-btn" value="下载" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name='operateBtn' class="operateBtn g-btn" value="操作">
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	<div class="g-content">
		<table class="g-table table-list">
			<thead class="table-hd">
				<tr>
					<th class="pcol13">订单号</th>
					<th class="pcol12">票号</th>
					<th class="pcol07">乘机人</th>
					<th class="pcol12">出票日期</th>
					<th class="pcol05">后返</th>
					<th class="pcol07">机票实收款</th>
					<th class="pcol08">实际成本</th>
					<th class="pcol08">实际支付成本</th>
					<th class="pcol06">利润</th>
					<th class="pcol08">销售平台</th>
					<th class="pcol07">采购平台</th>
					<th class="pcol07">出票员</th>
				</tr>
			</thead>
			<tbody class="table-bd" id="listbd">
			</tbody>
		</table>
	</div>
	
	<div id="light" class="profitDiv white_content">
		<table class="g-table table-list" style="min-width: 500px;">
			<thead class="table-hd">
				<tr>
					<th class="pcol20">采购平台</th>
					<th class="pcol20">数量</th>
					<th class="pcol20">机票实收款</th>
					<th class="pcol20">机票实际成本</th>
					<th class="pcol20">利润</th>
				</tr>
			</thead>
			<tbody class="table-bd" id="profitbd">
			</tbody>
		</table>
	</div>
	
	<div id="fade" class="black_overlay"></div>
	<div id="pageNav"></div>
</body>
</html>