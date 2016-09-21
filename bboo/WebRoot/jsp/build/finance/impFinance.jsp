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
<script src="${ctx }/static/js/build/finance/impFinance.js" type="text/javascript"></script>
<script src="${ctx }/static/js/build/order/orderQuery.js" type="text/javascript"></script>
<style type="text/css">
.m-search tr td {
	padding-top: 5px;
	padding-left: 5px;
}
.white_content {
	top: 30%;
	left: 24%;
	width: 520px;
	height: 260px;
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
</style>
<script type="text/javascript">
	//显示非当前页
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getDataList(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	var ctx = "${ctx}";
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div class="mod-hd" style="margin-top: 10px">
		<h2>报表导入</h2>
	</div>
	<!--条件查询   -->
	<form>
		<table class="m-search g-clear g-table" style="width: 100%">
			<%-- <tr class="rows">
				<td class="col" colspan="4" style="width: 600px; padding-left: 10px;">
					<a href="${ctx}/jsp/build/models/ImpTicketNo.xls" style="display:inline-block;" class="pur_btn_css">财务报表模版下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/jsp/build/models/ImpTicketNo.xls" style="display:inline-block;" class="pur_btn_css">销售报表模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/jsp/build/models/ImpTicketNo.xls" style="display:inline-block;" class="pur_btn_css">出票报表模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/jsp/build/models/ImpTicketNo.xls" style="display:inline-block;" class="pur_btn_css">采购报表模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/jsp/build/models/liRunSale.xls" style="display:inline-block;" class="pur_btn_css">利润报表模板下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr> --%>
			<tr class="rows">
				<td class="col">
					<span class="label">导入日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="startDate" type="text" class="cinput" id="startDate" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="endDate" type="text" class="cinput" id="endDate" >
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">文件号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="searchFileNo" type="text" > </span>
					</div>
				</td>
				<td class="col">
					<span class="label">文件名</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="searchFileName" type="text" > </span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">创建人</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="searchOperator" type="text" > </span>
					</div>
				</td>
				<td class="col">
					<span class="label">报表类型</span>
					<div class="label-info">
						<span class="g-select"> 
							<oms:select showEmpty="true" id="reportForms" name="reportForms" style="width: 100%">
								<oms:options></oms:options>
							</oms:select>
						</span>
					</div>
				</td>
				<td class="col">
					<span class="label">上传Excel</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="file" type="file" id="file"> </span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col" colspan="4" style="width: 800px;">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" name="querydata" class="g-btn" style="color: white;font-size: 14px;" value="查询" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="queryImportData" class="g-btn" value="查询导入数据" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="upLoadFile" class="g-btn" value="上传" />
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	<div class="g-content">
		<table class="g-table table-list">
			<thead class="table-hd">
				<tr>
					<th class="pcol12">文件号</th>
					<th class="pcol10">文件名称</th>
					<th class="pcol10">文件地址</th>
					<th class="pcol10">文件大小</th>
					<th class="pcol08">数据条数</th>
					<th class="pcol17">文件类型</th>
					<th class="pcol15">导入日期</th>
					<th class="pcol08">创建人</th>
					<th class="pcol10">操作</th>
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