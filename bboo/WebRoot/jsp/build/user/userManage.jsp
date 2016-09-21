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
<script src="${ctx }/static/js/build/user/userManage.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 15%;
	left: 36%;
	width: 300px;
	height: 360px;
}

.light-label {
	float: left;
	width: 55px;
	padding-right: 10px;
	text-align: right;
	line-height: 28px
}

.light-label-info {
	padding-left: 65px
}

.lable-row {
	margin-bottom: 10px;
}
.roleSelect {
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
.roleSelect ul li {
	margin-bottom: 5px;
}
</style>
<script type="text/javascript">
	//显示非当前页
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getUsers(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;
	
	};
	
	var ctx = "${ctx}";
	
	$(document).ready(function() {
		$.getJSON("${ctx}/configure/merchant!getMerchants.do",function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.merchants) {
				var selectStr = "";
				$.each(result.merchants, function(i, item) {
					selectStr += "<option value='"+item.mercid+"'>"+item.companyName+"</option>"
				})
				
				$("#merchant").append(selectStr);
			}
		});
	});
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div>
		<div class="mod-hd" style="margin-top: 10px">
			<h2 id="title"></h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">姓名</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchName" id="searchName" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">登录名</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchLoginName" id="searchLoginName" /> </span>
						</div>
					</td>
					<td class="col" id="comTd">
						<span class="label">公司名称</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchCompanyName" id="searchCompanyName" /> </span>
						</div>
					</td>
				</tr>
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
					<th class="pcol10">ID</th>
					<th class="pcol10">姓名</th>
					<th class="pcol10">登录名</th>
					<th class="pcol10">ssbm</th>
					<th class="pcol10">sszw</th>
					<th class="pcol15">手机号码</th>
					<th class="pcol10">ywm</th>
					<th class="pcol10" id="th8"></th>
					<th class="pcol15">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd"></tbody>
		</table>
	</div>
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>userId</span><input type="text" name="userId" id="userId" />
		</div>
		
		<div class="lable-row">
			<span class="light-label">姓名</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="name" id="name" /> </span>
			</div>
		</div>
		<div class="lable-row">
			<span class="light-label">登录名</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="loginName" id="loginName" /> </span>
			</div>
		</div>
		<div id="passDiv" class="lable-row">
			<span class="light-label">密码</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="password" id="password" /> </span>
			</div>
		</div>
		<div id="roleIdDiv" class="lable-row">
			<span class="light-label">roleId</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="roleId" id="roleId" /> </span>
				<div class="roleSelect">
					
				</div>
				<!-- <span class="g-select" style="width: 208px"> 
					<select name="status" id="roleId">
					</select>
				</span> -->
			</div>
		</div>
		<div id="mnoDiv" class="lable-row">
			<span class="light-label">公司名称</span>
			<div class="light-label-info">
				<span class="g-select" style="width: 208px">
					<select name="fkmercid" id="merchant">
					</select>
				</span>
			</div>
		</div>
		<div class="lable-row">
			<span class="light-label">ssbm</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="ssbm" id="ssbm" /> </span>
			</div>
		</div>
		<div class="lable-row">
			<span class="light-label">sszw</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="sszw" id="sszw" /> </span>
			</div>
		</div>
		<div class="lable-row">
			<span class="light-label">ywm</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="ywm" id="ywm" /> </span>
			</div>
		</div>
		<div class="lable-row">
			<span class="light-label">手机号码</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="sj" id="sj" /> </span>
			</div>
		</div>
		<div class="lable-row">
			<span class="light-label" style="line-height:12px">是否禁用</span>
			<div class="light-label-info">
				<label> <input name="deleted" value="Y" type="radio" class="deleted">是</label> 
				<label style="margin-left:20px;"> <input name="deleted" value="N" type="radio" class="deleted" checked="checked">否</label> 
			</div>
		</div>
		<div>
			<span class="light-label">&nbsp;</span>
			<div class="light-label-info">
				<input type="button" id="lightOk" class="g-btn" value="确定" />
				<input type="button" id="lightCancel" class="g-btn" value="取消" />
			</div>
		</div>
		
	</div>
	<div id="fade" class="black_overlay"></div>
	
	<div id="pageNav"></div>
</body>
</html>