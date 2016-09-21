<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/finance/doReportForms.js" type="text/javascript"></script>
<style type="text/css">
.white_content {
	top: 32%;
	left: 36%;
	width: 300px;
	height: 203px;
}

.light-label {
	float: left;
	width: 50px;
	padding-right: 10px;
	text-align: right;
	line-height: 28px
}

.light-label-info {
	padding-left: 55px
}
</style>
<script type="text/javascript">
	//显示非当前页
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getDatas(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	var ctx = "${ctx}";
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div>
		<div class="mod-hd" style="margin-top: 10px">
			<h2>操作报表</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">业务类型</span>
						<div class="label-info">
							<span class="g-select"> 
								<oms:select showEmpty="true" id="reportForms" name="searchReportForms" style="width: 100%">
									<oms:options></oms:options>
								</oms:select>
							</span>
						</div>
					</td>
					<td class="col">
						<span class="label">是否启用</span>
						<div class="label-info">
							<label> <input name="searchIsUsed" value="" type="radio" checked="checked" class="searchIsUsed">全部</label> 
							<label style="margin-left:20px;"> <input name="searchIsUsed" value="1" type="radio" class="searchIsUsed">启用</label> 
							<label style="margin-left:20px;"> <input name="searchIsUsed" value="0" type="radio" class="searchIsUsed">禁用</label> 
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col" style="width: 400px;">
						<span class="label">&nbsp;</span>
						<div class="label-info">
							<input type="button" name='queryBtn' class='queryBtn g-btn' value='查询'/>
							
							<f:function value="ADDFINANCESALORDER">
								<input type="button" name='addBtn' class='addBtn g-btn' value='添加'/>
							</f:function>
							
							<input type="button" name='downloadBtn' class='downloadBtn g-btn' value='下载'/>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="datagrid" class="g-content">
		<table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th class="pcol15">ID</th>
					<!-- <th class="pcol13">商务号</th> -->
					<th class="pcol13">业务类型</th>
					<th class="pcol15">字段名</th>
					<th class="pcol15">描述</th>
					<th class="pcol20">创建时间</th>
					<th class="pcol10">是否启用</th>
					<th class="pcol12">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd"></tbody>
		</table>
	</div>
	
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>id</span><input type="text" name="id" id="id" />
		</div>
		
		<span class="light-label">字段名</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="fieldName" id="fieldName" /> </span>
		</div><br>
		
		<span class="light-label">业务类型</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px;"> 
				<oms:select showEmpty="true" id="reportForms" name="reportForms" style="width: 100%">
					<oms:options></oms:options>
				</oms:select>
			</span>
			<!-- <span class="g-ipt-text"> <input type="text" name="mnoType" id="mnoType" /> </span> -->
		</div><br>
		
		<span class="light-label" style="line-height:12px">是否启用</span>
		<div class="light-label-info">
			<label> <input name="isUsed" value="1" type="radio" checked="checked" class="isUsed">启用</label> 
			<label style="margin-left:20px;"> <input name="isUsed" value="0" type="radio" class="isUsed">禁用</label> 
		</div><br>
		
		<span class="light-label">描述</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="description" id="description" /> </span>
		</div><br>
		
		<span class="light-label">&nbsp;</span>
		<div class="light-label-info">
			<input type="button" id="lightOk" class="g-btn" value="确定" />
			<input type="button" id="lightCancel" class="g-btn" value="取消" />
		</div>
		
	</div>
	<div id="fade" class="black_overlay"></div>
	<div id="pageNav"></div>
</body>
</html>