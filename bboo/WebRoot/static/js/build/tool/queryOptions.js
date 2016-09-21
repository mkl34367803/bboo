var cacheData;
function getOptions(page, searchSelectId) {
	var data = {};
	data.searchSelectId = searchSelectId;
	data.page = page; // 分页查询
	data.rows = 10; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/base-option!queryOptionsByPage.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.rows) {
					addTbodyData(result.rows, searchSelectId);
				}
				if (result.total == 0) {
					pageNav.go(page, Math.ceil(1));
				} else if (result.total) {
					pageNav.go(page, Math.ceil(result.total / data.rows));
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

function addTbodyData(data, selectId) {
	$(".table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + item.optionId + "</td>";
		/*var_tr += "<td>" + item.optionName + "</td>";*/
		var_tr += "<td>" + item.optionValue + "</td>";
		var_tr += "<td>" + item.optionText + "</td>";
		var_tr += "<td>" + item.descrtption + "</td>";
		//var_tr += "<td>" + item.isDisplay + "</td>";
		if(item.isDisplay == 'Y') {
			var_tr += "<td>显示 </td>";
		} else {
			var_tr += "<td>隐藏 </td>";
		}
		var_tr += "<td>" + item.createPerson + "</td>";
		var_tr += "<td>" + item.createTime + "</td>";
		var_tr += "<td>" + item.lastEdittime + "</td>";
		// 拼接json字符串
		var_tr += "<td><a href='javascript:modifyOption(\"" + item.optionId
				+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteOption(\""
				+ item.optionId + "\", \"" + selectId +"\")'>删除</a></td>";
		var_tr = "<tr>" + var_tr + "</tr>";
		$(".table-bd").append(var_tr);
	});
	$(".table-bd tr:even").addClass("even");
	$(".table-bd tr:odd").addClass("odd");
}

function openDialog() {
	$("#light").css('display', 'block');
	$("#fade").css('display', 'block');
}
function closeDialog() {
	$("#light").css('display', 'none');
	$("#fade").css('display', 'none');
}
function addOption() {
	openDialog();
	reset();
}
function modifyOption(id) {
	$.each(cacheData, function(i, item) {
		if (item.optionId == id) {
			$("#optionId").val(item.optionId);
			$("#description").val(item.descrtption);
			$("input:radio[value='"+item.isDisplay+"']").attr("checked", true);
			$("#optionName").val(item.optionName);
			$("#optionText").val(item.optionText);
			$("#optionValue").val(item.optionValue);
		}
	});
	openDialog();
}

function deleteOption(optionId, selectId) {
	if(confirm("确定删除？")) {
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : {
				optionId : optionId
			},
			url : ctx + "/tool/base-option!deleteOption.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
					var page = $("#page").html();
					getOptions(page, selectId);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	}
}

function reset() {
	$("#optionId").val("");
	$("#description").val("");
	$("#isDisplay").val("");
	$("#optionName").val("");
	$("#optionText").val("");
	$("#optionValue").val("");
	$("#selectId").val("");
	$("input:radio[value='Y']").attr("checked", "checked");
}