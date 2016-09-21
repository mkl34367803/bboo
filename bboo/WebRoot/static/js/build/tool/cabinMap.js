var cacheData;
function getDatas(page) {
	var data = {};
	var searchCarrier = $("#searchCarrier").val();
	var searchCabin = $("#searchCabin").val();
	var searchShareCode = $("#searchShareCode").val();
	data.carrier = searchCarrier;
	data.cabin = searchCabin;
	data.shareCode = searchShareCode;
	data.page = page; // 分页查询
	data.rows = 20; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/cabin-map!queryByPage.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.list) {
					addTbodyData(result.list);
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

function checkNull(data) {
	return data == null ? "" : data;
}

function addTbodyData(data) {
	$(".table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + checkNull(item.carrier) + "</td>";
		var_tr += "<td>" + checkNull(item.cabin) + "</td>";
		var_tr += "<td>" + checkNull(item.shareCode) + "</td>";
		var_tr += "<td>" + checkNull(item.shareCabin) + "</td>";
		var_tr += "<td>" + checkNull(item.shareNums) + "</td>";
		var_tr += "<td>" + checkNull(item.depCode) + "</td>";
		var_tr += "<td>" + checkNull(item.arrCode) + "</td>";
		var_tr += "<td>" + checkNull(item.createTime) + "</td>";
		var_tr += "<td><a href='javascript:modifyData(\"" + item.id
				+ "\")'>修改</a></td>";
		/*var_tr += "&nbsp;&nbsp;<a href='javascript:deleteData(\""
				+ item.id + "\")'>删除</a>";*/
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
function addData() {
	openDialog();
	reset();
}

function modifyData(id) {
	$.each(cacheData, function(i, item) {
		if (item.id == id) {
			$("#id").val(item.id);
			$("#carrier").val(item.carrier);
			$("#cabin").val(item.cabin);
			$("#shareCode").val(item.shareCode);
			$("#shareNums").val(item.shareNums);
			$("#shareCabin").val(item.shareCabin);
			$("#depCode").val(item.depCode);
			$("#arrCode").val(item.arrCode);
		}
	});
	openDialog();
}

function deleteData(id) {
	if(confirm("确定删除？")){
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				id : id
			},
			url : ctx + "/tool/cabin-map!delete.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					var page = $("#page").html();
					getDatas(page);
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
	$("#carrier").val("");
	$("#cabin").val("");
	$("#shareCode").val("");
	$("#shareNums").val("");
	$("#shareCabin").val("");
	$("#depCode").val("");
	$("#arrCode").val("");
}

$(document).ready(function() {
	getDatas(1);
	
	$("#searchButton").click(function() {
		getDatas(1);
	});
	$("#addButton").click(function() {
		addData();
	});
	$("#lightCancel").click(function() {
		closeDialog();
	});
	$("#lightOk").click(function() {
		var carrier = $.trim($("#carrier").val());
		var cabin = $.trim($("#cabin").val());
		var shareCode = $.trim($("#shareCode").val());
		var shareNums = $.trim($("#shareNums").val());
		var shareCabin = $.trim($("#shareCabin").val());
		var depCode = $.trim($("#depCode").val());
		var arrCode = $.trim($("#arrCode").val());
		if(check(carrier) && check(cabin) && check(shareCode) && check(shareCabin)) {
			var cabinMap = {};
			cabinMap.id = $("#id").val();
			cabinMap.carrier = carrier;
			cabinMap.cabin = cabin;
			cabinMap.shareCode = shareCode;
			cabinMap.shareNums = shareNums;
			cabinMap.shareCabin = shareCabin;
			cabinMap.depCode = depCode;
			cabinMap.arrCode = arrCode;
			$.ajax({
				type : "post",
				dataType : "json",
				data : cabinMap,
				url : ctx + "/tool/cabin-map!saveOrUpdate.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					}
					if (result.success) {
						//alert(result.success);
						closeDialog();
						reset();
						var page = $("#page").html();
						getDatas(page);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		} else {
			alert("参数不能为空，长度不能大于2");
		}
	});
	
	var check = function(data) {
		if(data.length < 1 ||data.length > 2){
			return false;
		}
		return true;
	}
});