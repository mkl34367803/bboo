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
<script src="${ctx }/static/js/build/tool/keyValue.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 32%;
	left: 36%;
	width: 280px;
	height: 166px;
}

.light-label {
	float: left;
	width: 35px;
	padding-right: 10px;
	text-align: right;
	line-height: 28px
}

.light-label-info {
	padding-left: 45px
}
</style>
<script type="text/javascript">
	//显示非当前页
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getKeyValues(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	
	var ctx = "${ctx}";
	
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div>
		<div class="mod-hd" style="margin-top: 10px">
			<h2>KeyValue管理</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">ID</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchID" id="searchID" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">key</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchK" id="searchK" /> </span>
						</div>
					</td>
				</tr>
				<!-- <tr class="rows">
					<td class="col">
						<span class="label">value</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchV" id="searchV" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">描述</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchDescribe" id="searchDescribe" /> </span>
						</div>
					</td>
				</tr> -->
				<tr class="rows">
					<td class="col">
						<span class="label">&nbsp;</span>
						<div class="label-info">
							<input type="button" id="searchButton" class="g-btn" value="查询" />
							<input type="button" id="addButton" class="g-btn" value="添加" />
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
					<th class="pcol08">ID</th>
					<th class="pcol12">key</th>
					<th class="pcol38">value</th>
					<th class="pcol15">描述</th>
					<th class="pcol15">创建时间</th>
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
		
		<span class="light-label">key</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="k" id="k" /> </span>
		</div><br>
		<span class="light-label">value</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="v" id="v" /> </span>
		</div><br>
		<span class="light-label">描述</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="describe" id="describe" /> </span>
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