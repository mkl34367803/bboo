<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/office/officeManage.js" type="text/javascript"></script>
<style type="text/css">
.black_overlay {
	display: none;
	position: fixed;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: fixed;
	top: 35%;
	left: 40%;
	width: 225px;
	height: auto;
	padding: 16px;
	border: 16px solid #52a3e2;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
<script type="text/javascript">
var ctx = "${ctx}";

	//显示非当前页
	pageNav.pHtml = function (pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:getOfficeList(" + pageNo +");' class='pageNum'>" + showPageNo + "</a> ";
		return H;
	
	};
	$(document).ready(function() {
		getOfficeList(1);
		$("#searchButton").click(function(){
			getOfficeList(1);
		});
		$("#addButton").click(function(){
			addOffice();
		});
		$("#lightCancel").click(function(){
			closeDialog();
			reset();
		});
		$("#lightOk").click(function(){
			var baseOfficeEntity={};
			var etdzNum = $("#etdzNum").val();
			if (!checkIsNum(etdzNum)) {
				alert("打票机台数必须是数字");
				return;
			}
			baseOfficeEntity.id =$("#id").val();
			baseOfficeEntity.office =$("#office").val();
			baseOfficeEntity.offtypes = $("#offtypes").val();
			baseOfficeEntity.appkey = $("#appkey").val();
			baseOfficeEntity.etdzNum = etdzNum;
			baseOfficeEntity.isu =$("#isu").val();
			$.ajax({
				type : "post",
				dataType : "json",
				
				data : baseOfficeEntity,
				url : "${ctx }/office/base-office!saveOrUpdate.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					}
					if (result.success) {
						//alert(result.success);
						closeDialog();
						reset();
						var page=$("#page").html();
						getOfficeList(page);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		});
	});
</script>
</head>
<body>
	<div>
		<div class="mod-hd">
			<h2>office查询</h2>
		</div>
		<div class="g-content">
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
							<span class="label">office</span>
							<div class="label-info">
								<span class="g-ipt-text"> <input type="text" name="searchOffice" id="searchOffice" /> </span>
							</div>
						</td>
						<td class="col">
							<span class="label">用途</span>
							<div class="label-info">
								<span class="g-ipt-text"> <input type="text" name="searchOfftypes" id="searchOfftypes" /> </span>
							</div>
						</td>
					</tr>
					<tr class="rows">
						<td class="col">
							<span class="label">appkey</span>
							<div class="label-info">
								<span class="g-ipt-text"> <input type="text" name="searchAppkey" id="searchAppkey" /> </span>
							</div>
						</td>
						<td class="col">
							<span class="label">启用禁用</span>
							<div class="label-info">
								<span class="g-select"> <select name="searchIsu" id="searchIsu">
										<option value="0">-------</option>
										<option value="1">启用</option>
										<option value="2">禁用</option>
								</select> </span>
							</div>

						</td>
					</tr>
					<tr class="rows">
				<td class="col">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" id="searchButton" class="g-btn" value="查询" />
						<input type="button" id="addButton" class="g-btn" value="新增" />
					</div>
				</td>
			</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="datagrid" class="g-content">
		<table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th class="pcol15">ID</th>
					<th class="pcol15">office</th>
					<th class="pcol15">用途</th>
					<th class="pcol15">打票机台数</th>
<!-- 					<th class="col05">appkey</th> -->
					<th class="pcol10">启用禁用</th>
					<th class="pcol20">创建时间</th>
					<th class="pcol10">操作</th>
				</tr>
			</thead>
			<tbody id="table-bd" class="table-bd"></tbody>
		</table>
	</div>
	<div id="light" class="white_content"> 
		<div  style="display: none">
    	<span>id</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" id="id" />
		</div>
		<div>
    	<span>office</span>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="office" id="office" />
		</div>
		<br>
		<div>
    	<span>offtypes</span><input type="text" name="offtypes" id="offtypes"/>
		</div>
		<br>
		<div>
    	<span>appkey</span>&nbsp;<input type="password" name="appkey" id="appkey"/>
		</div>
		<br>
		<div>
    	<span>打票机台数</span><input type="text" name="etdzNum" id="etdzNum"/>
		</div>
		<br>
		<div>
    	<span>启用禁用</span>
    	<select name="isu" id="isu">
    			<option value="1">启用</option>
    			<option value="2">禁用</option>
    	</select>
		</div>
		<br>
		<div>
    	<button id="lightOk" class="g-btn" >确定</button>&nbsp;&nbsp;<button  id="lightCancel" class="g-btn" >取消</button>
		</div>
	</div>
	<div id="fade" class="black_overlay">
	</div>
	<div id="pageNav" ></div>
</body>
</html>