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
<script src="${ctx }/static/js/build/tool/cabinMap.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 28%;
	left: 36%;
	width: 330px;
	height: auto;
}

.light-label {
	float: left;
	width: 85px;
	padding-right: 10px;
	text-align: right;
	line-height: 28px
}

.light-label-info {
	padding-left: 90px
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
			<h2>CabinMap管理</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">航司</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchCarrier" id="searchCarrier" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">舱位</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchCabin" id="searchCabin" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">实际承运</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchShareCode" id="searchShareCode" /> </span>
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
					<th class="pcol10">航司</th>
					<th class="pcol10">舱位</th>
					<th class="pcol10">实际承运</th>
					<th class="pcol10">实际舱位</th>
					<th class="pcol15">实际承运航班号</th>
					<th class="pcol10">起始</th>
					<th class="pcol10">到达</th>
					<th class="pcol15">创建时间</th>
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
		
		<span class="light-label">航司</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="carrier" id="carrier" /> </span>
		</div><br>
		
		<span class="light-label">舱位</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="cabin" id="cabin" /> </span>
		</div><br>
		
		<span class="light-label">实际承运</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="shareCode" id="shareCode" /> </span>
		</div><br>
		
		<span class="light-label">实际舱位</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="shareCabin" id="shareCabin" /> </span>
		</div><br>
		
		<span class="light-label">实际承运航班号</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="shareNums" id="shareNums" /> </span>
		</div><br>
		
		<span class="light-label">出发地</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="depCode" id="depCode" /> </span>
		</div><br>
		
		<span class="light-label">到达地</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="arrCode" id="arrCode" /> </span>
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