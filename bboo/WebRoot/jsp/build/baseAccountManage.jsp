<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本账号管理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>

<script type="text/javascript">
function search(){
		$('#dg').datagrid('load',{
			mno:$('#search_mno').val(),
			userName:$('#search_userName').val()
		});
}
	function openAddDialog() {
		$("#dlg-buttons").show();
		$("#saveButton").show();
		$("#dlg input").attr("disabled",false);     //设置id=dlg下面的input框全部不可操作。
		$("#dlg select").attr("disabled",false);     //设置id=dlg下面的input框全部不可操作。
		$("#ctime").datebox({disabled:false});  //置灰easyui自带的日期框。
		$("#dlg").dialog("open").dialog("setTitle", "添加分销商账号信息");
		url = "${ctx }/account/base-account!saveAndUpdateBaseAccount.do";
	}

	function openModifyDialog() {
		$("#dlg-buttons").show();
		$("#saveButton").show();
		$("#dlg input").attr("disabled",false);     //设置id=dlg下面的input框全部不可操作。
		$("#dlg select").attr("disabled",false);     //设置id=dlg下面的input框全部不可操作。
		$("#ctime").datebox({disabled:false});  //置灰easyui自带的日期框。
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑分销商账号信息");
		$("#fm").form("load", row);
		url = "${ctx }/account/base-account!saveAndUpdateBaseAccount.do?id=" + row.id;
	}
	function openViewDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要查看的数据！");
			return;
		}
		var row = selectedRows[0];
// 		$("#dlg").dialog("options").closable=true;
// 		$("#dlg").dialog({closable:true});
		$("#dlg").dialog("open").dialog("setTitle", "查看分销商账号信息");
		$("#fm").form("load", row);
		$("#saveButton").hide();
// 		$("#dlg-buttons").hide();
// 		 $('#continueButton').attr("disabled", true);
		$("#dlg input").attr("disabled",true);     //设置id=dlg下面的input框全部不可操作。
		$("#dlg select").attr("disabled",true);     //设置id=dlg下面的input框全部不可操作。
		$("#ctime").datebox('disable');  //置灰easyui自带的日期框。
		url = "${ctx }/account/base-account!saveAndUpdateBaseAccount.do?id=" + row.id;
	}
	function deleteUser() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].id);
		}
		var ids = strIds.join(",");
		$.messager.confirm("系统提示", "您确认要删掉这<font color=red>"
				+ selectedRows.length + "</font>条数据吗？", function(r) {
			if (r) {
				$.post("${ctx }/resource/gj-resource!deleteResource.do", {
					delIds : ids
				}, function(result) {
					if (result.success) {
						$.messager.alert("系统提示", "您已成功删除<font color=red>"
								+ result.delNums + "</font>条数据！");
						$("#dg").datagrid("reload");
					} else {
						$.messager.alert('系统提示', +result.errorMsg);
					}
				}, "json");
			}
		});
	}

	function save() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				return $(this).form("validate");
			},
			success : function(result) {
				result=$.parseJSON(result);
				if (result.errorMsg) {
					$.messager.alert("系统提示", result.errorMsg);
					return;
				} else {
					$.messager.alert("系统提示", "保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}

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
	function closeDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function formatterIsu(value, row, index){
		if(row.isu==1){
			return "启用";
		}else if(row.isu==2){
			return "禁用";
		}
	}
	function formatterAcckind(value, row, index){
		if(row.acckind==1){
			return "分销";
		}else if(row.acckind==2){
			return "采购";
		}else{
			return "其他";
		}
	}
</script>
</head>
<body>
	<table id="dg" title="分销商账号管理" class="easyui-datagrid" pagination="true" rownumbers="true" url="<%=request.getContextPath()%>/account/base-account!queryBaseAccount.do" fit="true" toolbar="#tb" resizeHandle="right">
		<thead>
			<tr>
				<th field="cb" checkbox="true">&quot;&quot;</th>
				<th field="id" width="100"  hidden="true">账号id</th>
				<th field="mno" width="100" hidden="true">商户账号</th>
				<th field="userName" width="100">用户名</th>
				<th field="bigcid" width="100" hidden="true">大客户优惠码</th>
				<th field="secret" width=100" hidden="true">密码</th>
				<th field="appkey" width="100" hidden="true">安全KEY</th>
				<th field="codes" width="100" hidden="true">安全码</th>
				<th field="sessions" width="100" hidden="true">SESSION</th>
				<th field="url" width="100" hidden="true">url</th>
				<th field="ignoredShopNames" width="100" hidden="true">排除店铺</th>
				<th field="acctype" width="120">账户类型</th>
				<th field="describe" width="100" hidden="true">描述</th>
				<th field="acckind" width="100" formatter="formatterAcckind">账号种类</th>
				<th field="accpay" width="100">支付账号</th>
				<th field="paypwd" width="100" hidden="true">支付密码</th>
				<th field="paytype" width="100">支付类型</th>
				<th field="office" width="100" >office</th>
				<th field="isu" width="100" formatter="formatterIsu">是否禁用</th>
				<th field="ctime" width="140">创建时间</th>
				<th field="apiHost" width="100" hidden="true">接口HOST</th>
				<th field="apiServiceName" width="100" hidden="true">接口项目名</th>
				<th field="apiClassName" width="100" hidden="true">接口类名</th>
				<th field="apiMethodName" width="100" hidden="true">接口方法名称</th>
				<th field="sdoffice" width="100" hidden="true">预定office号</th>
				<th field="issd" width="100" hidden="true">是否自动预定</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:openViewDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">查看</a>
<!-- 			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
		</div>
		<div>
			&nbsp;商户账号：&nbsp;
			<input type="text" name="mno" id="search_mno" />
			&nbsp;用户名：&nbsp;
			<input type="text" name="userName" id="search_userName" />
			<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg"   class="easyui-dialog" style="width: 580px;height: 380px;padding: 10px 20px" closed="true" buttons="#dlg-buttons" closable="false">
		<form id="fm" method="post">
			<table>
<!-- 				<tr> -->
<!-- 					<td>商户账号：</td> -->
<!-- 					<td> -->
<!-- 						<input type="text" name="mno" id="mno" class="easyui-validatebox" required="true" /> -->
<!-- 					</td> -->
					
<!-- 				</tr> -->
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
<!-- 					<td>创建时间：</td> -->
<!-- 					<td> -->
<!-- 						<input type="text" name="ctime" id="ctime" class="easyui-datebox" /> -->
<!-- 					</td> -->
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
<!-- 				<tr> -->
<!-- 					<td>父菜单id：</td> -->
<!-- 					<td> -->
<!-- 						<input name="bigcid" id="bigcid" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'resource_id',textField:'name',url:'${ctx }/resource/gj-resource!queryResource.do'" /> -->
<!-- 					</td> -->
<!-- 					<td>父菜单id：</td> -->
<!-- 					<td> -->
<!-- 						<input name="bigcid" id="bigcid" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'resource_id',textField:'name',url:'${ctx }/resource/gj-resource!queryResource.do'" /> -->
<!-- 					</td> -->
<!-- 				</tr> -->
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok" id="saveButton">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>