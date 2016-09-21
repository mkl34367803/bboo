<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>题库管理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/question/questions.js" type="text/javascript"></script>
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

.SQDiv {
	display: none;
}

.showDiv {
	padding: 10px 10px 10px 10px;
    margin-bottom: 10px;
	display: none;
	background: #fff;
	min-width: 900px;
	height: 100%;
	margin-right: -2px;
}

.showDiv span {
	margin-left: 20px;
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
			<h2>题库管理</h2>
		</div>
		<form action="">
			<table class="m-search g-clear g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">题目类别</span>
						<div class="label-info">
							<span class="g-select"> 
								<select name="searchClassify">
										<option value="1">技术</option>
										<option value="2">航空业务</option>
										<option value="3">财务</option>
										<option value="4">行政</option>
								</select> 
							</span>
						</div>
					</td>
					<td class="col">
						<span class="label">问题类型</span>
						<div class="label-info">
							<span class="g-select"> 
								<select name="searchQType">
										<option value="">全部</option>
										<option value="1">问答题</option>
										<option value="2">选择题</option>
										<option value="3">多选题</option>
								</select> 
							</span>
						</div>
					</td>
					<td class="col">
						<span class="label">状态</span>
						<div class="label-info">
							<label> <input name="searchIsuse" value="0" type="radio" checked="checked">可用</label> 
							<label style="margin-left:20px;"> <input name="searchIsuse" value="1" type="radio">不可用</label> 
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">出题人</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="searchIntoPerson" /> </span>
						</div>
					</td>
				</tr>
				<tr class="rows">
					<td class="col" colspan="3" style="width: 100%">
						<span class="label">&nbsp;</span>
						<div class="label-info">
							<input type="button" id="searchButton" class="g-btn" value="查询" />
							<input type="button" id="addButton" class="g-btn" value="添加" />
							<input type="button" id="showButton" class="g-btn" value="显示" />
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="datagrid" class="g-content SQDiv">
		<table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th class="pcol10">题目类别 </th>
					<th class="pcol10">问题类型</th>
					<th class="pcol40">问题</th>
					<th class="pcol10">出题人</th>
					<th class="pcol10">是否可用</th>
					<th class="pcol10">创建时间</th>
					<th class="pcol10">操作</th>
				</tr>
			</thead>
			<tbody class="table-bd"></tbody>
		</table>
	</div>
	
	<div class="showDiv">
	</div>
	
	<div id="light" class="white_content">
		
		<div style="display: none">
			<span>id</span><input type="text" name="id" id="id" />
			<span>id</span><input type="text" name="intoPerson" />
			<span>id</span><input type="text" name="createTime" />
		</div>
		
		<span class="light-label">题目类别</span>
		<div class="light-label-info">
			<span class="g-select"> 
				<select name="classify" style="width: 207px;">
					<option value="1">技术</option>
					<option value="2">航空业务</option>
					<option value="3">财务</option>
					<option value="4">行政</option>
				</select> 
			</span>
		</div><br>
		
		<span class="light-label">问题类型</span>
		<div class="light-label-info">
			<span class="g-select"> 
				<select name="QType" style="width: 207px;">
					<option value="1">问答题</option>
					<option value="2">选择题</option>
					<option value="3">多选题</option>
				</select> 
			</span>
		</div><br>
		
		<div class="questionDiv">
			<span class="light-label">问题</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="question" /> </span>
			</div><br>
		</div>
		
		<div class="selectDiv">
			<span class="light-label">A</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="A" /> </span>
			</div><br>
		
			<span class="light-label">B</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="B" /> </span>
			</div><br>
		
			<span class="light-label">C</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="C" /> </span>
			</div><br>
		
			<span class="light-label">D</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="D" /> </span>
			</div><br>
		</div>
		
		<div class="answerSelectSDiv">
			<span class="light-label">答案</span>
			<div class="light-label-info">
				<label> <input name="answerSelectS" value="A" type="radio" checked="checked">A</label> 
				<label style="margin-left:20px;"> <input name="answerSelectS" value="B" type="radio">B</label> 
				<label style="margin-left:20px;"> <input name="answerSelectS" value="C" type="radio">C</label> 
				<label style="margin-left:20px;"> <input name="answerSelectS" value="D" type="radio">D</label> 
			</div><br>
		</div>
		
		<div class="answerSelectMDiv">
			<span class="light-label">答案</span>
			<div class="light-label-info">
				<label> <input name="answerSelectM" value="A" type="checkbox">A</label> 
				<label style="margin-left:20px;"> <input name="answerSelectM" value="B" type="checkbox">B</label> 
				<label style="margin-left:20px;"> <input name="answerSelectM" value="C" type="checkbox">C</label> 
				<label style="margin-left:20px;"> <input name="answerSelectM" value="D" type="checkbox">D</label> 
			</div><br>
		</div>
		
		<div class="answerTxtDiv">
			<span class="light-label">答案</span>
			<div class="light-label-info">
				<span class="g-ipt-text"> <input type="text" name="answerTxt" /> </span>
			</div><br>
		</div>
		
		<span class="light-label">状态</span>
		<div class="light-label-info">
			<label> <input name="isuse" value="0" type="radio" checked="checked">可用</label> 
			<label style="margin-left:20px;"> <input name="isuse" value="1" type="radio">不可用</label> 
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