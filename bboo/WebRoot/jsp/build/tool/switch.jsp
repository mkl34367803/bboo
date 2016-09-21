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
<script src="${ctx }/static/js/build/tool/switch.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 25%;
	left: 36%;
	width: 280px;
	height: 166px;
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
		var H = " <a href='javascript:getSwitches(" + pageNo
				+ ");' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
	
	var ctx = "${ctx}";
	
	$(document).ready(function() {
		getSwitches(1);
		
		$.getJSON("${ctx }/configure/merchant!getMerchants.do",function(result) {
			if (result.merchants) {
				var selectStr = "";
				$.each(result.merchants, function(i, item) {
					selectStr += "<option value='"+item.mno+"'>"+item.companyName+"</option>"
				})
				
				$("#mno").append(selectStr);
			}
		});
		
		$("#searchButton").click(function() {
			getSwitches(1);
		});
		$("#addButton").click(function() {
			addSwitch();
		});
		$("#lightCancel").click(function() {
			closeDialog();
		});
		$("#lightOk").click(function() {
			var mno = $.trim($("#mno").val());
			var onoff = $("input[name='onoff']:checked").val();
			var validity = $.trim($("#validity").val());
			var mkey = $.trim($("select[name='switchKey']").val());
			var describe = $.trim($("#describe").val());
			if(mno != "" && onoff != "" && validity != "" && mkey != "" && describe != "" ) {
				var switchEntity = {};
				switchEntity.id = $("#id").val();
				switchEntity.mno = mno;
				switchEntity.onoff = onoff;
				switchEntity.validity = validity;
				switchEntity.mkey = mkey;
				switchEntity.describe = describe;
				$.ajax({
					type : "post",
					dataType : "json",
	
					data : switchEntity,
					url : "${ctx }/tool/switch!saveOrUpdate.do",
					success : function(result) {
						if (result.error) {
							alert(result.error);
						}
						if (result.success) {
							//alert(result.success);
							closeDialog();
							reset();
							getSwitches(1);
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
			<h2>Switch管理</h2>
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
						<span class="label">商户名称</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchCompanyName" id="searchCompanyName" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">开关</span>
						<div class="label-info">
							<label> <input name="searchOnoff" value="" type="radio" class="g-input-check" checked="checked">全部</label>
							<label> <input name="searchOnoff" value="1" type="radio" class="g-input-check">开启</label> 
							<label> <input name="searchOnoff" value="0" type="radio" class="g-input-check">关闭</label>
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">mkey</span>
						<div class="label-info">
							<span class="g-select"> 
								<oms:select showEmpty="true" id="switchKey" name="searchMkey" style="width: 100%">
									<oms:options></oms:options>
								</oms:select>
							</span>
							<!-- <span class="g-ipt-text"> <input type="text" name="searchMkey" id="searchMkey" /> </span> -->
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
					<th class="pcol12">商户名称</th>
					<th class="pcol18">mkey</th>
					<th class="pcol10">描述</th>
					<th class="pcol10">开关</th>
					<th class="pcol20">创建时间</th>
					<th class="pcol10">有效期</th>
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
		
		<span class="light-label">商户名称</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px">
				<select name="mno" id="mno">
				</select>
			</span>
		</div><br>
		
		<span class="light-label">mkey</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px;"> 
				<oms:select showEmpty="true" id="switchKey" name="switchKey" style="width: 100%">
					<oms:options></oms:options>
				</oms:select>
				<!-- <select name="mkey" id="mkey">
						<option value="">----</option>
						<option value="QUNAR_XUNJIA_MASTER_ON">去哪儿询价总开关</option>
						<option value="CTRIP_XUNJIA_MASTER_ON">携程询价总开关</option>
						<option value="TAOBAO_XUNJIA_MASTER_ON">淘宝询价总开关</option>
				</select>  -->
			</span>
			<!-- <span class="g-ipt-text"> <input type="text" name="mkey" id="mkey" /> </span> -->
		</div><br>
		
		<span class="light-label">描述</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="describe" id="describe" /> </span>
		</div><br>
		
		<span class="light-label" style="line-height:12px">开关</span>
		<div class="light-label-info">
			<label> <input name="onoff" value="1" type="radio" checked="checked" class="onoff">开启</label> 
			<label style="margin-left:20px;"> <input name="onoff" value="0" type="radio" class="onoff">关闭</label> 
		</div><br>
		
		<span class="light-label">有效期</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="validity" id="validity" onclick="laydate();" /> </span>
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