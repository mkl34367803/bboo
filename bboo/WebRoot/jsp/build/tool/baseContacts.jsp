<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联系人管理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/tool/baseContacts.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 20%;
	left: 36%;
	width: 300px;
	height: auto;
}

.light-label {
	float: left;
	width: 55px;
	padding-right: 10px;
	text-align: right;
	line-height: 28px
}

.light-label-info {
	padding-left: 60px
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
			<h2>联系人管理</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">联系人</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchLinkman" id="searchLinkman" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">联系电话</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchLinktel" id="searchLinktel" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">手机号码</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchPhone" id="searchPhone" /> </span>
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col" colspan="3">
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
					<th class="pcol10">联系人</th>
					<th class="pcol15">联系电话</th>
					<th class="pcol15">手机号码</th>
					<th class="pcol20">电子邮箱</th>
					<th class="pcol20">地址</th>
					<th class="pcol10">创建时间</th>
					<th class="pcol10">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd"></tbody>
		</table>
	</div>
	
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>id</span><input type="text" name="id" id="id" />
		</div>
		
		<span class="light-label">联系人</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="linkman" /> </span>
		</div><br>
		
		<span class="light-label">联系电话</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="linktel" /> </span>
		</div><br>
		
		<span class="light-label">手机号码</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="phone" /> </span>
		</div><br>
		
		<span class="light-label">电子邮箱</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="email" /> </span>
		</div><br>
		
		<span class="light-label">地址</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="address" /> </span>
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