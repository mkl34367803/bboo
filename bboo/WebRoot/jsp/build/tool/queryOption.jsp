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
<script src="${ctx }/static/js/build/tool/queryOptions.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 30%;
	left: 35%;
	width: 300px;
	height: 202px;
}

.light-label {
	float: left;
	width: 50px;
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
		var H = " <a href='javascript:getOptions(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	
	var ctx = "${ctx}";
	
	$(document).ready(function() {
		var selectId = "${param.selectId}";
		var descrtption = decodeURI("${param.descrtption}");
		
		$("#title").html(descrtption+" Option管理");
		
		getOptions(1, selectId);
		
		$("#addButton").click(function() {
			addOption();
		});
		$("#lightCancel").click(function() {
			closeDialog();
		});
		$("#lightOk").click(function() {
			var description = $.trim($("#description").val());
			/* var optionName = $.trim($("#optionName").val()); */
			var optionText = $.trim($("#optionText").val());
			var optionValue = $.trim($("#optionValue").val());
			if (description != "" && optionText != "" && optionValue != "" && selectId != ""){
				var option = {};
				option.optionId = $("#optionId").val();
				option.description = description;
				/* option.optionName = optionName; */
				option.optionText = optionText;
				option.optionValue = optionValue;
				option.selectId = selectId;
				//option.isDisplay = $("#isDisplay").val();
				option.isDisplay = $(".isDisplay:checked").val();
				$.ajax({
					type : "post",
					dataType : "json",
	
					data : option,
					url : "${ctx }/tool/base-option!saveOrUpdate.do",
					success : function(result) {
						if (result.error) {
							alert(result.error);
						}
						if (result.success) {
							//alert(result.success);
							closeDialog();
							reset();
							var page = $("#page").html();
							getOptions(page, selectId);
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
	<div style="margin-bottom: 5px;">
		<div class="mod-hd" style="margin-top: 10px">
			<h2 id="title">Option管理</h2>
		</div>
		<div class="g-content">
			<div>
				<input type="button" id="addButton" class="g-btn" value="添加" />
			</div>
		</div>
	</div>
	
	<div id="datagrid" class="g-content">
		<table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th class="pcol06">ID</th>
					<!-- <th class="pcol08">名称</th> -->
					<th class="pcol12">value</th>
					<th class="pcol11">text</th>
					<th class="pcol11">描述</th>
					<th class="pcol08">是否显示</th>
					<th class="pcol10">创建人</th>
					<th class="pcol16">创建时间</th>
					<th class="pcol16">最后编辑时间</th>
					<th class="pcol10">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd">
			</tbody>
		</table>
	</div>
	
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>id</span><input type="text" name="optionId" id="optionId" />
		</div>
		
		<!-- <span class="light-label">名称</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="optionName" id="optionName" /> </span>
		</div><br> -->
		
		<span class="light-label">value</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="optionValue" id="optionValue" /> </span>
		</div><br>
		
		<span class="light-label">text</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="optionText" id="optionText" /> </span>
		</div><br>
		
		<span class="light-label">描述</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="description" id="description" /> </span>
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