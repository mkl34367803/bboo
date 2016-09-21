var cacheData;
function getSwitches(page) {
	var data = {};
	var searchID = $("#searchID").val();
	var searchCompanyName = $("#searchCompanyName").val();
	var searchOnoff = $("input[name='searchOnoff']:checked").val();
	var searchMkey = $("select[name='searchMkey']").val();
	data.searchID = searchID;
	data.searchCompanyName = searchCompanyName;
	data.searchOnoff = searchOnoff;
	data.searchMkey = searchMkey;
	data.page = page; // 分页查询
	data.rows = 10; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/switch!querySwitch.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.switches) {
					addTbodyData(result.switches);
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
		var_tr += "<td>" + item.companyName + "</td>";
		if (item.mkeyCh) {
			var_tr += "<td>"+item.mkeyCh+"</td>";
		} else {
			var_tr += "<td>"+item.mkey+"</td>";
		}
		var_tr += "<td>" + item.describe + "</td>";
		if (item.onoff == 1) {
			var_tr += "<td>开启</td>";
		} else if (item.onoff == 0) {
			var_tr += "<td>关闭</td>";
		}
		var_tr += "<td>" + item.ctm + "</td>";
		var_tr += "<td>" + item.validity + "</td>";
		// 拼接json字符串
		var_tr += "<td><a href='javascript:modifySwitch(\"" + item.id
				+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteSwitch(\""
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
function addSwitch() {
	openDialog();
	reset();
}

function modifySwitch(id) {
	$.each(cacheData, function(i, item) {
		if (item.id == id) {
			$("#light").css("height", "250px");
			$("#id").val(item.id);
			$("#mno").val(item.mno);
			$(".onoff input:radio[value='"+item.onoff+"']").attr("checked", true);
			$("#validity").val(item.validity);
			$("#switchKey option[value='"+item.mkey+"']").attr("selected", true);
			$("#describe").val(item.describe);
		}
	});
	openDialog();
}

function deleteSwitch(id) {
	if(confirm("确定删除？")){
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : {
				id : id
			},
			url : ctx + "/tool/switch!deleteSwitch.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
					var page = $("#page").html();
					getSwitches(page);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});		
	}
}

function reset() {
	$("#light").css("height", "250px");
	$("#id").val("");
	$("#mno").val("");
	$(".onoff input:radio[value='1']").attr("checked", "checked");
	$("#validity").val("");
	$("#switchKey").val("");
	$("#describe").val("");
}