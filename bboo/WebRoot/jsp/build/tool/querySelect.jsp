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
<script src="${ctx }/static/js/build/tool/querySelect.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 32%;
	left: 36%;
	width: 300px;
	height: 156px;
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

</style>
<script type="text/javascript">
	//显示非当前页
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getSelects(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	
	var ctx = "${ctx}";
	
	$(document).ready(function() {
		getSelects(1);
		$("#searchButton").click(function() {
			getSelects(1);
		});
		$("#addButton").click(function() {
			addOption();
		});
		$("#lightCancel").click(function() {
			closeDialog();
		});
		$("#lightOk").click(function() {
			var selectName = $.trim($("#selectName").val());
			var descrtption = $.trim($("#descrtption").val());
			if(selectName != "" && descrtption != "") {
				var option = {};
				option.selectId = $("#selectId").val();
				option.selectName = selectName;
				option.descrtption = descrtption;
				//option.isDisplay = $("#isDisplay").val();
				option.isDisplay = $(".isDisplay:checked").val();
				$.ajax({
					type : "post",
					dataType : "json",
	
					data : option,
					url : "${ctx }/tool/base-select!saveOrUpdate.do",
					success : function(result) {
						if (result.error) {
							alert(result.error);
						}
						if (result.success) {
							//alert(result.success);
							closeDialog();
							reset();
							var page = $("#page").html();
							getSelects(page);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("请求出错了");
					}
				});
			} else {
				alert("参数不能为空");
			}
		});
	});
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div>
		<div class="mod-hd" style="margin-top: 10px">
			<h2>Select管理</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">ID</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchSelectId" id="searchSelectId" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">名称</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchSelectName" id="searchSelectName" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">创建人</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchCreatePerson" id="searchCreatePerson" /> </span>
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
					<th class="pcol08">ID</th>
					<th class="pcol10">名称</th>
					<th class="pcol10">描述</th>
					<th class="pcol08">是否显示</th>
					<th class="pcol10">创建人</th>
					<th class="pcol20">创建时间</th>
					<th class="pcol18">最后编辑时间</th>
					<th class="pcol16">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd">
			</tbody>
		</table>
	</div>
	
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>ID</span><input type="text" name="selectId" id="selectId" />
		</div>
		
		<span class="light-label">名称</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="selectName" id="selectName" /> </span>
		</div><br>
		
		<span class="light-label">描述</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="descrtption" id="descrtption" /> </span>
		</div><br>
		
		<span class="light-label" style="line-height:12px">是否显示</span>
		<div class="light-label-info">
			<label> <input name="isDisplay" value="Y" type="radio" checked="checked" class="isDisplay">显示</label> 
			<label style="margin-left:20px;"> <input name="isDisplay" value="N" type="radio" class="isDisplay">隐藏</label> 
						
			<!-- <span class="g-ipt-text"> <input type="text" name="isDisplay" id="isDisplay" /> </span> -->
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