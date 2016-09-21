<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本账号管理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
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
	top: 15%;
	left: 15%;
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
var cacheData;

//显示非当前页
pageNav.pHtml = function (pageNo, pn, showPageNo) {
	showPageNo = showPageNo || pageNo;
	var H = " <a href='javascript:getBaseAccountList(" + pageNo +");' class='pageNum'>" + showPageNo + "</a> ";
	return H;

};

function getBaseAccountList(page) {
	var data = {};
	data.page = page; // 分页查询
	data.rows = 5; // 每页展示多少条记录
	var mno=$("#search_mno").val();
	var userName=$("#search_userName").val();
	data.mno=mno;
	data.userName=userName;
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/account/base-account!queryBaseAccount.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.rows) {
					cacheData = result.rows; // 缓存返回来的数据。
					addTbodyData(result.rows);
				}
				if(result.total==0){
					pageNav.go(page,Math.ceil(1) );
				}else if(result.total){
					pageNav.go(page,Math.ceil(result.total/data.rows) );
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

function addTbodyData(data) {
	$("#table-bd").html("");
	$.each(data, function(i, item) {	
		var td_id = "<td>" + item.id + "</td>";
		var td_userName = "<td>" + item.userName + "</td>";
		var td_acctype = "<td>" + item.acctype + "</td>";
		var temp_acckind="";
		if(item.acckind==1){
			temp_acckind="分销";
		}else if(item.acckind==2){
			temp_acckind="采购";
		}else{
			temp_acckind="其他";
		}
		var td_acckind = "<td>" + temp_acckind + "</td>";
		var td_accpay = "<td>" + item.accpay + "</td>";
		var td_paytype = "<td>" + item.paytype + "</td>";
		var td_office= "<td>" + item.office + "</td>";
		var temp_isu="";
		if(item.isu==1){
			temp_isu="启用";
		}else if(item.isu==2){
			temp_isu="禁用";
		}
		var td_isu = "<td>" + temp_isu+ "</td>";
		var td_ctime = "<td>" + item.ctime+ "</td>";
		// 拼接json字符串
		var td_operate = "<td><a href='javascript:modifyBaseAccount("+item.id+")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteBaseAccount("+item.id+")'>删除</a></td>";
		var var_tr = "<tr>" + td_id +td_userName+td_acctype+td_acckind+td_accpay+td_paytype+td_office+td_isu+td_ctime+td_operate+ "</tr>";
		$("#table-bd").append(var_tr);
	});
	$(".table-bd tr:even").addClass("even");
	$(".table-bd tr:odd").addClass("odd");
}

/**
 * 新增一行记录
 */
function addBaseAccount(){
	openDialog();
}
/**
 * 修改单行记录
 *
 * rowId 每行记录的id号
 */
function modifyBaseAccount(id){
	$.each(cacheData,function(i,item){
		if(item.id==id){
			setValue(item);
		}
	});
	openDialog();
}
/**
 *删除单行记录 
 *
 * rowId 每行记录的id号
 */
function deleteBaseAccount(id){
	$.ajax({
		type : "post",
		dataType : "json",
		data : {id:id},
		url : "",  //这个页面不需要删除功能，加上url就可以删除了
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				alert(result.success);
				var page=$("#page").html();
				getOfficeList(page);  //删除了东西也要重新刷新页面
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

function openDialog(){
	$("#light").css('display','block');  
	$("#fade").css('display','block');  
}
function closeDialog(){
	$("#light").css('display','none');  
	$("#fade").css('display','none');  
}

function lightOk(){
	var paramVo=getValue();
	$.ajax({
		type : "post",
		dataType : "json",
		data : paramVo,
		url : "${ctx }/account/base-account!saveAndUpdateBaseAccount.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				closeDialog();
				reset();
				var page=$("#page").html();
				getBaseAccountList(page);  //新增完了数据要刷新页面
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

function lightCancel(){
	closeDialog();
	reset();
}



/**
 * 获取新增的记录页面上填的信息
 */
function getValue(){
	var paramVo={};
	paramVo.mno=$("#mno").val();
	paramVo.userName=$("#userName").val();
	paramVo.bigcid=$("#bigcid").val();
	paramVo.secret=$("#secret").val();
	paramVo.appkey=$("#appkey").val();
	paramVo.codes=$("#codes").val();
	paramVo.sessions=$("#sessions").val();
	paramVo.url=$("#url").val();
	paramVo.ignoredShopNames=$("#ignoredShopNames").val();
	paramVo.acctype=$("#acctype").val();
	paramVo.describe=$("#describe").val();
	paramVo.acckind=$("#acckind").val();
	paramVo.accpay=$("#accpay").val();
	paramVo.paypwd=$("#paypwd").val();
	paramVo.paytype=$("#paytype").val();
	paramVo.ctime=$("#ctime").val();
	paramVo.apiHost=$("#apiHost").val();
	paramVo.apiServiceName=$("#apiServiceName").val();
	paramVo.apiClassName=$("#apiClassName").val();
	paramVo.apiMethodName=$("#apiMethodName").val();
	paramVo.office=$("#office").val();
	paramVo.sdoffice=$("#sdoffice").val();
	return paramVo;
}

/**
 * 修改的时候，需要先设置值
 */
function setValue(item){
	$("#mno").val(item.mno);
	$("#userName").val(item.userName);
	$("#bigcid").val(item.bigcid);
	$("#secret").val(item.secret);
	$("#appkey").val(item.appkey);
	$("#codes").val(item.codes);
	$("#sessions").val(item.sessions);
	$("#url").val(item.url);
	$("#ignoredShopNames").val(item.ignoredShopNames);
	$("#acctype").val(item.acctype);
	$("#describe").val(item.describe);
	$("#acckind").val(item.acckind);
	$("#accpay").val(item.accpay);
	$("#paypwd").val(item.paypwd);
	$("#paytype").val(item.paytype);
	$("#ctime").val(item.ctime);
	$("#apiHost").val(item.apiHost);
	$("#apiServiceName").val(item.apiServiceName);
	$("#apiClassName").val(item.apiClassName);
	$("#apiMethodName").val(item.apiMethodName);
	$("#office").val(item.office);
	$("#sdoffice").val(item.sdoffice);
}

/**
 * 修改和添加后，需要清空div中的值
 */
function resetValue() {
	$("#mno").val("");
	$("#userName").val("");
	$("#bigcid").val("");
	$("#secret").val("");
	$("#appkey").val("");
	$("#codes").val("");
	$("#sessions").val("");
	$("#url").val("");
	$("#ignoredShopNames").val("");
	$("#acctype").val("");
	$("#describe").val("");
	$("#acckind").val("");
	$("#accpay").val("");
	$("#paypwd").val("");
	$("#paytype").val("");
	$("#ctime").val("");
	$("#apiHost").val("");
	$("#apiServiceName").val("");
	$("#apiClassName").val("");
	$("#apiMethodName").val("");
	$("#office").val("");
	$("#sdoffice").val("");
}

/**
 * ready方法永远放到最后面
 */
$(document).ready(function() {
	getBaseAccountList(1);
	$("#searchButton").click(function(){
		getBaseAccountList(1);
	});
	$("#addButton").click(function(){
		addBaseAccount();
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
						<span class="label">商户号</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="mno" id="search_mno" /> </span>
						</div>
					</td>
					<td class="col">
						<span class="label">用户名</span>
						<div class="label-info">
							<span class="g-ipt-text"> <input type="text" name="userName" id="search_userName" /> </span>
						</div>
					</td>
					
				</tr>
				<tr class="rows">
					<td class="col"><span class="label">&nbsp;</span>
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
				<th field="id" width="100">账号id</th>
				<th field="userName" width="100">用户名</th>
				<th field="acctype" width="120">账户类型</th>
				<th field="acckind" width="100" formatter="formatterAcckind">账号种类</th>
				<th field="accpay" width="100">支付账号</th>
				<th field="paytype" width="100">支付类型</th>
				<th field="office" width="100" >office</th>
				<th field="isu" width="100" formatter="formatterIsu">是否禁用</th>
				<th field="ctime" width="140">创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="table-bd" class="table-bd"></tbody>
	</table>
</div>

<div id="pageNav" ></div>

<div id="light"  style="width: 580px;height: 380px" class="white_content">
		<table>
			<tr>
				<td>用户名：</td>
				<td>
					<input type="text" name="userName" id="userName" class="easyui-validatebox" required="true" />
				</td>
				<td>密码：</td>
				<td>
					<input type="password" name="secret" id="secret" class="easyui-validatebox" required="true" />
				</td>
			</tr>
			<tr>
				<td>安全KEY：</td>
				<td>
					<input type="password" name="appkey" id="appkey" class="easyui-validatebox" />
				</td>
				<td>安全码：</td>
				<td>
					<input type="password" name="codes" id="codes" class="easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<td>SESSION：</td>
				<td>
					<input type="password" name="sessions" id="sessions" class="easyui-validatebox" />
				</td>
				<td>url值：</td>
				<td>
					<input type="text" name="url" id="url" class="easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<td>排除店铺：</td>
				<td>
					<input type="text" name="ignoredShopNames" id="ignoredShopNames" class="easyui-validatebox" />
				</td>
				<td>账户类型：</td>
				<td>
					<input type="text" name="acctype" id="acctype" class="easyui-validatebox"   required="true" />
				</td>
			</tr>
			<tr>
				<td>描述：</td>
				<td>
					<input type="text" name="describe" id="describe" class="easyui-validatebox"   required="true" />
				</td>
				<td>账号种类：</td>
				<td>
					<select name="acckind" >
						<option value="1">分销</option>
						<option value="2">采购</option>
						<option value="3">其他</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>支付账号：</td>
				<td>
					<input type="text" name="accpay" id="accpay" class="easyui-validatebox" />
				</td>
				<td>支付密码：</td>
				<td>
					<input type="password" name="paypwd" id="paypwd" class="easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<td>支付类型：</td>
				<td>
					<input type="text" name="paytype" id="paytype" class="easyui-validatebox" />
				</td>
				<td>是否禁用：</td>
				<td>
					<select name="isu">
					<option value="1">启用</option>
					<option value="2">禁用</option>
					</select>
				</td>
			</tr>
			<tr>
			<td>大客户优惠码：</td>
				<td>
					<input type="text" name="bigcid" id="bigcid" class="easyui-validatebox" />
				</td>
				<td>接口HOST：</td>
				<td>
					<input type="text" name="apiHost" id="apiHost" class="easyui-validatebox" />
				</td>
			</tr>
			<tr>
				<td>接口项目名：</td>
				<td>
					<input type="text" name="apiServiceName" id="apiServiceName" class="easyui-validatebox" />
				</td>
				<td>接口类名：</td>
				<td>
					<input type="text" name="apiClassName" id="apiClassName" class="easyui-validatebox" />
				</td>
			</tr>
			<tr >
				<td>接口方法名：</td>
				<td >
					<input type="text" name="apiMethodName" id="apiMethodName" class="easyui-validatebox" />
				</td>
				<td>office：</td>
				<td >
					<input type="text" name="office" id="office" class="easyui-validatebox" />
				</td>
				
			</tr>
			<tr >
				<td>预定office：</td>
				<td >
					<input type="text" name="sdoffice" id="sdoffice" class="easyui-validatebox" />
				</td>
				<td>是否自动预定：</td>
				<td >
					<select name="issd">
					<option value="1">是</option>
					<option value="0">否</option>
					</select>
				</td>
				
			</tr>
		</table>
		<br>
		<div>
    		<button id="lightOk" class="g-btn" onclick="lightOk()">确定</button>&nbsp;&nbsp;<button  id="lightCancel" class="g-btn" onclick="lightCancel()">取消</button>
		</div>
</div>

<div id="fade" class="black_overlay">



</body>
</html>