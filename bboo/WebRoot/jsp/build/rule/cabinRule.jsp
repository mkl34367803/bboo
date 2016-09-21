<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>舱位退改签管理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/rule/cabinRule.js" type="text/javascript"></script>
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

.bookChannelSelect {
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
.bookChannelSelect ul li {
	margin-bottom: 5px;
}
.qcbox {
	width: 95px;
	float: left;
	border: 1px solid #e4e4e4;
    background-color: #fff;
    color: #333;
    height: 26px;
    position: relative;
    display: block;
}
.split {
    float: left;
    padding: 0 2px;
    line-height: 28px;
}
.g-drift {
	margin-right: 8px;
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
			<h2>舱位退改签管理</h2>
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
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">&nbsp;</span>
						<div class="label-info">
							<input type="button" id="searchButton" class="g-btn" value="查询" style="margin-right: 6px;" />
							<input type="button" id="addButton" class="g-btn" value="添加" />
						</div>
					</td>
					<td class="col">
						<span class="label">&nbsp;</span>
						<div class="label-info">
						<div class="g-drift">
							<a href="javascript:;" class="">退票说明</a>
							<div class="g-tip g-tip-w tip-b-r">
								<div class="info">
									<font style="color: red;">0</font>  表示不允许退票，在退票规则中填入 0，表示不允许退票；<br> 
									<font style="color: red;">0-0-0</font>，表示不收手续费。<br>
									<font style="color: red;">10-0-20</font>，表示起飞前的手续费比例是 10%，起飞后的手续费比例是 20%。<br>
									<font style="color: red;">20-2-30</font>，表示起飞前 2 小时之前的手续费比例是20%，起飞前 2 小时之后的手续费 比例是 30%。<br>
									<font style="color: red;">40-168-80-0-100</font>，表示起飞前 168 小时之前的手续费比例是 40%，起飞前168 小时 之内的手续费比例是 80%，起飞后的手续费比例是 100%。
								</div>
							</div>
						</div>
						<div class="g-drift">
							<a href="javascript:;" class="">改期说明</a>
							<div class="g-tip g-tip-w tip-b-r">
								<div class="info">
									<font style="color: red;">0</font>   表示不允许改期，在改期规则中 填入 0，表示不允许改期。<br> 
									<font style="color: red;">0-0-0</font>，表示不收手续费。<br>
									<font style="color: red;">10-0-20</font>，表示起飞前的手续费比例是 10%，起飞后的手续费比例是 20%。<br>
									<font style="color: red;">20-2-30</font>，表示起飞前 2 小时之前的手续费比例是20%，起飞前 2 小时之后的手续费 比例是 30%。<br>
									<font style="color: red;">40-168-80-0-100</font>，表示起飞前 168 小时之前的手续费比例是 40%，起飞前168 小时 之内的手续费比例是 80%，起飞后的手续费比例是 100%。
								</div>
							</div>
						</div>
						<div class="g-drift">
							<a href="javascript:;" class="">作废说明</a>
							<div class="g-tip g-tip-w tip-b-r">
								<div class="info">
									<font style="color: red;">0</font>  当天出票可以作废<br> 
									<font style="color: red;">1</font>  当天出票当天起飞以外航班可以作废<br> 
									<font style="color: red;">2</font>  当天出票1天以外的航班可以作废 <br>
									<font style="color: red;">-1</font>   不允许作废
								</div>
							</div>
						</div>
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
					<th class="pcol06">航司</th>
					<th class="pcol06">舱位</th>
					<th class="pcol10">退票规则</th>
					<th class="pcol10">改期规则</th>
					<th class="pcol08">签转</th>
					<th class="pcol10">作废</th>
					<th class="pcol18">有效期</th>
					<th class="pcol08">最后操作人</th>
					<th class="pcol14">录入时间</th>
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
		
		<span class="light-label">退票规则</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="returnRule" id="returnRule" /> </span>
		</div><br>
		
		<span class="light-label">改期规则</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="changeRule" id="changeRule" /> </span>
		</div><br>
		
		<span class="light-label">签转</span>
		<div class="light-label-info">
			<label> <input name="endorsement" value="是" type="radio" style="vertical-align: middle;">允许</label> 
			<label style="margin-left:20px;"> <input name="endorsement" value="否" type="radio" checked="checked" style="vertical-align: middle;">不允许</label> 
		</div><br>
		
		<span class="light-label">作废</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="voidDay" id="voidDay" /> </span>
			<!-- <span class="g-select" style="width: 208px">
				<select name="voidDay" id="voidDay">
					<option value="">----</option>
					<option value="0">当天出票可以作废</option>
					<option value="1">当天出票当天起飞以外航班可以作废</option>
					<option value="2">当天出票1天以外的航班可以作废</option>
					<option value="-1">不允许作废</option>
				</select>
			</span> -->
		</div><br>
		
		<span class="light-label">有效期</span>
		<div class="light-label-info">
			<div class="qcbox">
				<input name="startValidity" id="startValidity" type="text" class="cinput" onclick="laydate();" >
			</div>
			<span class="split">到</span>
			<div class="qcbox">
				<input name="endValidity" id="endValidity" type="text" class="cinput" onclick="laydate();" >
			</div>
		</div><br>
		
		<div style="margin-top: 25px;">
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