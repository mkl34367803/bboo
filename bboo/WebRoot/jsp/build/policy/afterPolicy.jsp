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
<script src="${ctx }/static/js/build/policy/afterPolicy.js" type="text/javascript"></script>
<style type="text/css">

.white_content {
	top: 25%;
	left: 36%;
	width: 300px;
	height: auto;
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
			<h2>AfterPolicy管理</h2>
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
						<span class="label">产品类型</span>
						<div class="label-info">
							<span class="g-select"> 
								<!-- <select name="searchProductType" id="searchProductType">
									<option value="">全部</option>
									<option value="JH_GW">JH_GW</option>
									<option value="JH_DL">JH_DL</option>
								</select> -->
								<oms:select showEmpty="true" id="productType" name="searchProductType" style="width: 100%">
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
					<th class="pcol13">渠道</th>
					<th class="pcol13">产品类型</th>
					<th class="pcol13">航司</th>
					<th class="pcol13">后返</th>
					<th class="pcol13">创建时间</th>
					<th class="pcol15">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd"></tbody>
		</table>
	</div>
	
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>id</span><input type="text" name="id" id="id" />
		</div>
		
		<span class="light-label">渠道</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px">
				<!-- <select name="channel" id="channel">
					<option value="">------</option>
					<option value="CP_517NA">517NA</option>
					<option value="CP_BAITOUR">BAITOUR</option>
					<option value="CP_8000YI">8000YI</option>
					<option value="CP_51BOOK">51BOOK</option>
					<option value="CP_19E">19E</option>
					<option value="CP_LH800">LH800</option>
					<option value="CP_AIR_B2B_EBAO">AIR_B2B_EBAO</option>
					<option value="CP_LOCALBSP">LOCALBSP</option>
					<option value="CP_QUA">QUA</option>
				</select> -->
				<oms:select showEmpty="true" id="channel" name="channel" style="width: 100%">
					<oms:options></oms:options>
				</oms:select>
			</span>
		</div><br>
		
		<span class="light-label">产品类型</span>
		<div class="light-label-info">
			<span class="g-select" style="width: 208px">
				<!-- <select name="productType" id="productType">
					<option value="">全部</option>
					<option value="JH_GW">JH_GW</option>
					<option value="JH_DL">JH_DL</option>
				</select> -->
				<oms:select showEmpty="true" id="productType" name="productType" style="width: 100%">
					<oms:options></oms:options>
				</oms:select>
			</span>
		</div><br>
		
		<span class="light-label">航司</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="carrier" id="carrier" /> </span> 
		</div><br>
		
		<span class="light-label">后返</span>
		<div class="light-label-info">
			<span class="g-ipt-text"> <input type="text" name="after" id="after" /> </span>
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