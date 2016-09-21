<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本菜单管理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#dg').datagrid({
			url : "${ctx }/resource/gj-resource!queryResource.do",
			columns : [ [ {
				field : 'resourceId',
				title : '菜单id',
				width : 50,
				sortable : true
			}, {
				field : 'description',
				title : '描述',
				width : 50,
				sortable : true
			}, {
				field : 'name',
				title : '名称',
				width : 50,
				sortable : true
			}, {
				field : 'sort',
				title : '分类',
				width : 50,
				sortable : true
			}, {
				field : 'type',
				title : '类型',
				width : 50,
				sortable : true
			}, {
				field : 'value',
				title : 'url值',
				width : 50,
				sortable : true
			}, {
				field : 'pid',
				title : '父菜单',
				width : 80,
				formatter : function(value, row, index) {
					if (row.parent) {
						return row.parent.resourceId;
					} else {
						return value;
					}
				}
			} ] ]
		});
	});

	function search() {
		$('#dg').datagrid('load', {
			resourceId : $('#search_resourceId').val(),
			name : $('#search_name').val(),
			sort : $('#search_sort').val(),
			type : $('#search_type').val(),
			value : $('#search_value').val(),
			pid : $('#search_pid').val()
		});
	}
	function openAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加菜单信息");
		url = "${ctx }/resource/gj-resource!saveOrUpdateResource.do";
	}

	function openModifyDialog() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑菜单信息");
		$("#fm").form("load", row);
		if(row.parent){
			$("#parentID").combobox('setValue', row.parent.resourceId);
		}
		url = "${ctx }/resource/gj-resource!saveOrUpdateResource.do?resourceId=" + row.resourceId;
	}
	function deleteUser() {
		var selectedRows = $("#dg").datagrid('getSelections');
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var strIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			strIds.push(selectedRows[i].resourceId);
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
				$.parseJSON(result);
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
		$("#description").val("");
		$("#name").val("");
		$("#sort").val("");
		$("#type").val("");
		$("#value").val("");
		$("#pid").val("");
	}
	function closeDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
</script>
</head>
<body>
	<table id="dg" title="菜单信息" class="easyui-datagrid" fitColumns="true" pagination="true" rownumbers="true"  fit="true" toolbar="#tb">
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th field="cb" checkbox="true"></th> -->
<!-- 				<th field="resourceId" width="50">菜单id</th> -->
<!-- 				<th field="description" width="50">描述</th> -->
<!-- 				<th field="name" width="50">名称</th> -->
<!-- 				<th field="sort" width="50">分类</th> -->
<!-- 				<th field="type" width=50">类型</th> -->
<!-- 				<th field="value" width="50">url值</th> -->
<!-- 				<th field="pid" width="50">父菜单id</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
	</table>

	<div id="tb">
		<div>
			<a href="javascript:openAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>
			&nbsp;菜单id：&nbsp;
			<input type="text" name="resourceId" id="search_resourceId" />
			&nbsp;名称：&nbsp;
			<input type="text" name="name" id="search_name" />
			&nbsp;分类：&nbsp;
			<input type="text" name="sort" id="search_sort" />
			&nbsp;类型：&nbsp;
			<input type="text" name="type" id="search_type" />
			&nbsp;url值：&nbsp;
			<input type="text" name="value" id="search_value" />
			&nbsp;父菜单id：&nbsp;
			<input type="text" name="pid" id="search_pid" />
			<a href="javascript:search()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px" closed="true" buttons="#dlg-buttons" closable="false">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>描述：</td>
					<td>
						<input type="text" name="description" id="description" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<td>名称：</td>
					<td>
						<input type="text" name="name" id="name" class="easyui-validatebox" required="true" />
					</td>
				</tr>
				<tr>
					<td>分类：</td>
					<td>
						<input type="text" name="sort" id="sort" class="easyui-validatebox" />
					</td>
				</tr>
				<tr>
					<td>类型：</td>
					<td>
						<input type="text" name="type" id="type" class="easyui-validatebox" />
					</td>
				</tr>
				<tr>
					<td>url值：</td>
					<td>
						<input type="text" name="value" id="value" class="easyui-validatebox" />
					</td>
				</tr>
				<tr>
					<td>父菜单：</td>
					<td>
						<input name="parentID" id="parentID" class="easyui-combobox" data-options="panelHeight:'auto',editable:false,valueField:'resourceId',textField:'name',url:'${ctx }/resource/gj-resource!getParentResource.do'" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:save()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>