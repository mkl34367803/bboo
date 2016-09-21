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
<script src="${ctx }/static/js/build/tool/bookRule.js" type="text/javascript"></script>
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
			<h2>BookRule管理</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">店铺名称</span>
						<div class="label-info">
							<span class="g-select">
								<oms:select name="searchShopName" id="shopName" >
									<oms:options></oms:options>
								</oms:select>
							</span>
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
					<th class="pcol10">店铺名称</th>
					<th class="pcol10">政策类型</th>
					<th class="pcol10">订票渠道</th>
					<th class="pcol10">可否降舱</th>
					<th class="pcol10">是否可用</th>
					<th class="pcol10">利润</th>
					<th class="pcol10">包含规则</th>
					<th class="pcol10">排除规则</th>
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
		
		<span class="light-label">店铺名称</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px;"> 
				<oms:select name="shopName" id="shopName">
					<oms:options></oms:options>
				</oms:select>
			</span>
		</div><br>
		
		<span class="light-label">政策类型</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px;">
				<oms:select name="policyType" id="policyType">
					<oms:options></oms:options>
				</oms:select>
			</span>
		</div><br>
		
		<span class="light-label">订票渠道</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="bookChannelCount" /> </span>
			<div class="bookChannelSelect">
				<ul>
					<li><input type="checkbox" name="bookChannel" value="CP_517NA" />CP_517NA</li>
					<li><input type="checkbox" name="bookChannel" value="CP_BAITOUR" />CP_BAITOUR</li>
					<li><input type="checkbox" name="bookChannel" value="CP_8000YI" />CP_8000YI</li>
					<li><input type="checkbox" name="bookChannel" value="CP_51BOOK" />CP_51BOOK</li>
					<li><input type="checkbox" name="bookChannel" value="CP_19E" />CP_19E</li>
					<li><input type="checkbox" name="bookChannel" value="CP_LH800" />CP_LH800</li>
					<li><input type="checkbox" name="bookChannel" value="CP_AIR_B2B_EBAO" />CP_AIR_B2B_EBAO</li>
					<li><input type="checkbox" name="bookChannel" value="CP_LOCALBSP" />CP_LOCALBSP</li>
					<li><input type="checkbox" name="bookChannel" value="CP_QUA" />CP_QUA</li>
				</ul>
				<a href='javascript:conformCheckBox()'>确定</a>
				<a href='javascript:clearCheckBox()' style='margin-left: 10px;'>清空</a>
			</div>
		</div><br>
		
		<span class="light-label">可否降舱</span>
		<div class="light-label-info">
			<label> <input name="isDrop" value="1" type="radio" checked="checked" style="vertical-align: middle;">可以</label> 
			<label style="margin-left:20px;"> <input name="isDrop" value="2" type="radio" style="vertical-align: middle;">不可以</label> 
		</div><br>
		
		<span class="light-label">是否可用</span>
		<div class="light-label-info">
			<label class="label"> <input name="isuse" value="1" type="radio" checked="checked" style="vertical-align: middle;">可用</label> 
			<label class="label" style="margin-left:20px;"> <input name="isuse" value="2" type="radio" style="vertical-align: middle;">禁用</label> 
		</div><br>
		
		<span class="light-label">利润</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="lr" id="lr" /> </span>
		</div><br>
		
		<span class="light-label">包含规则</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="includeRule" id="includeRule" /> </span>
		</div><br>
		
		<span class="light-label">排除规则</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="ignoredRules" id="ignoredRules" /> </span>
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