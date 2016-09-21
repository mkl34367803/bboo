var cacheData;
function getDatas(startPage) {
	var data = {};
	var searchCarrier =  $.trim($("input[name='searchCarrier']").val());
	var searchCabin =  $.trim($("input[name='searchCabin']").val());
	data.carrier = searchCarrier;
	data.cabin = searchCabin;
	data.startPage = startPage; // 分页查询
	data.pageSize = 20; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/rule/cabin-rule!queryByPage.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.list) {
					addTbodyData(result.list);
				}
				if (result.total == 0) {
					pageNav.go(startPage, Math.ceil(1));
				} else if (result.total) {
					pageNav.go(startPage, Math.ceil(result.total / data.pageSize));
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
		var var_tr = "<td>" + item.carrier + "</td>";
		var_tr += "<td>" + item.cabin + "</td>";
		var_tr += "<td>" + item.returnRule + "</td>";
		var_tr += "<td>" + item.changeRule + "</td>";
		if (item.endorsement == '是') {
			var_tr += "<td>允许签转</td>";
		} else if (item.endorsement == '否') {
			var_tr += "<td>不允许签转</td>";
		} else {
			var_tr += "<td>"+item.endorsement+"</td>";
		}
		var_tr += "<td>" + item.voidDay + "</td>";
		var_tr += "<td>" + item.startValidity + "至" + item.endValidity + "</td>";
		var_tr += "<td>" + item.lastOperator + "</td>";
		var_tr += "<td>" + item.createTime + "</td>";
		var_tr += "<td><a href='javascript:modifyData(\"" + item.id
				+ "\")'>修改</a>";
		var_tr += "&nbsp;&nbsp;<a href='javascript:deleteData(\""
				+ item.id + "\")'>删除</a>";
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
			$("input[name='carrier']").val(item.carrier);
			$("input[name='cabin']").val(item.cabin);
			$("input[name='returnRule']").val(item.returnRule);
			$("input[name='changeRule']").val(item.changeRule);
			$("input:radio[name='endorsement'][value='"+item.endorsement+"']").attr("checked", true);
			$("input[name='voidDay']").val(item.voidDay);
			$("input[name='startValidity']").val(item.startValidity);
			$("input[name='endValidity']").val(item.endValidity);
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
			url : ctx + "/rule/cabin-rule!delete.do",
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
	$("#returnRule").val("");
	$("#changeRule").val("");
	$("input:radio[name='endorsement'][value='否']").attr("checked", true);
	$("#voidDay").val("");
	$("#startValidity").val("");
	$("#endValidity").val("");
}

$(document).ready(function() {
	
	$("#searchButton").click(function() {
		getDatas(1);
	});
	$("#addButton").click(function() {
		addData();
	});
	$("#lightCancel").click(function() {
		closeDialog();
	});
	$("input[name='bookChannelCount'").click(function() {
		$(".bookChannelSelect").css({"display": "block"});
	});
	$("#lightOk").click(function() {
		var carrier = $.trim($("#carrier").val());
		var cabin = $.trim($("#cabin").val());
		var returnRule = $.trim($("#returnRule").val());
		var changeRule = $.trim($("#changeRule").val());
		var endorsement = $("input[name='endorsement']:checked").val()
		var voidDay = $.trim($("#voidDay").val());
		var startValidity = $.trim($("#startValidity").val());
		var endValidity = $.trim($("#endValidity").val());
		if(carrier != "" && cabin != "") {
			var data = {};
			data.id = $("#id").val();
			data.carrier = carrier;
			data.cabin = cabin;
			data.returnRule = returnRule;
			data.changeRule = changeRule;
			data.endorsement = endorsement;
			data.voidDay = voidDay;
			data.startValidity = startValidity;
			data.endValidity = endValidity;
			$.ajax({
				type : "post",
				dataType : "json",
				data : data,
				url : ctx + "/rule/cabin-rule!saveOrUpdate.do",
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
			alert("店铺名称和政策类型不能为空");
		}
	});
});