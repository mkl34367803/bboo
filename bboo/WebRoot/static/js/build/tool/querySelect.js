var cacheData;
function getSelects(page) {
	var data = {};
	var searchSelectId = $("#searchSelectId").val();
	var searchSelectName = $("#searchSelectName").val();
	var searchCreatePerson = $("#searchCreatePerson").val();
	data.searchSelectId = searchSelectId;
	data.searchSelectName = searchSelectName;
	data.searchCreatePerson = searchCreatePerson;
	data.page = page; // 分页查询
	data.rows = 10; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/base-select!querySelectsByPage.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.rows) {
					addTbodyData(result.rows);
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

function addTbodyData(data) {
	$(".table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + item.selectId + "</td>";
		var_tr += "<td>" + item.selectName + "</td>";
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
		var_tr += "<td><a href='javascript:modifySelect(" + item.selectId
				+ ")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteSelect(\""
				+ item.selectId + "\")'>删除</a>&nbsp;&nbsp;<a href='javascript:optionManage(\""
				+ item.selectId + "\",\"" + item.descrtption + "\")'>option</a></td>";
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
function modifySelect(id) {
	$.each(cacheData, function(i, item) {
		if (item.selectId == id) {
			$("#selectId").val(item.selectId);
			$("#selectName").val(item.selectName);
			$("#descrtption").val(item.descrtption);
			//$("#isDisplay").val(item.isDisplay);
			$("input:radio[value='"+item.isDisplay+"']").attr("checked", true);
		}
	});
	openDialog();
}

function deleteSelect(selectId) {
	if (confirm("确认删除？")) {
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : {
				selectId : selectId
			},
			url : ctx + "/tool/base-select!deleteSelect.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
					var page = $("#page").html();
					getSelects(page);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	}
}

function optionManage(selectId, descrtption) {
	location.href="queryOption.jsp?selectId="+selectId+"&descrtption="+encodeURI(encodeURI(descrtption));
}

function reset() {
	//$("#selectId").val("");
	$("#selectName").val("");
	$("#descrtption").val("");
	$("#isDisplay").val("");
	$("input:radio[value='Y']").attr("checked", "checked");
}