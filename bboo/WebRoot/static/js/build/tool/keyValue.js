var cacheData;
function getKeyValues(page) {
	var data = {};
	var searchID = $("#searchID").val();
	var searchK = $("#searchK").val();
	var searchV = $("#searchV").val();
	var searchDescribe = $("#searchDescribe").val();
	data.searchID = searchID;
	data.searchK = searchK;
	data.searchV = searchV;
	data.searchDescribe = searchDescribe;
	data.page = page; // 分页查询
	data.rows = 10; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/key-value!queryKeyValues.do",
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
		var var_tr = "<td>" + item.id + "</td>";
		var_tr += "<td>" + item.k + "</td>";
		var_tr += "<td>" + item.v + "</td>";
		var_tr += "<td>" + item.describe + "</td>";
		var_tr += "<td>" + item.ctm + "</td>";
		// 拼接json字符串
		var_tr += "<td><a href='javascript:modifyKeyValue(\"" + item.id
				+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteKeyValue(\""
				+ item.id + "\")'>删除</a></td>";
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
function addKeyValue() {
	openDialog();
	reset();
}
function modifyKeyValue(id) {
	$.each(cacheData, function(i, item) {
		if (item.id == id) {
			$("#id").val(item.id);
			$("#k").val(item.k);
			$("#v").val(item.v);
			$("#describe").val(item.describe);
		}
	});
	openDialog();
}

function deleteKeyValue(id) {
	if(confirm("确定删除？")){
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : {
				id : id
			},
			url : ctx + "/tool/key-value!deleteKeyValue.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
					var page = $("#page").html();
					getKeyValues(page);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});		
	}
}

function reset() {
	$("#id").val("");
	$("#k").val("");
	$("#v").val("");
	$("#describe").val("");
}

$(document).ready(function() {
	getKeyValues(1);
	$("#searchButton").click(function() {
		getKeyValues(1);
	});
	$("#addButton").click(function() {
		addKeyValue();
	});
	$("#lightCancel").click(function() {
		closeDialog();
	});
	$("#lightOk").click(function() {
		var k = $.trim($("#k").val());
		var v = $.trim($("#v").val());
		var describe = $.trim($("#describe").val());
		if(k != "" && v != "" && describe != "" ) {
			var keyValEntity = {};
			keyValEntity.id = $("#id").val();
			keyValEntity.k = k;
			keyValEntity.v = v;
			keyValEntity.describe = describe;
			$.ajax({
				type : "post",
				dataType : "json",

				data : keyValEntity,
				url : ctx + "/tool/key-value!saveOrUpdate.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					}
					if (result.success) {
						//alert(result.success);
						closeDialog();
						reset();
						var page = $("#page").html();
						getKeyValues(page);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		} else {
			alert("参数不能为空");
		}
	});
});