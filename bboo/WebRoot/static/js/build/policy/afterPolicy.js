var cacheData;
function getDatas(page) {
	var data = {};
	var searchCarrier = $("#searchCarrier").val();
	var searchProductType = $("select[name='searchProductType']").val();
	data.carrier = searchCarrier;
	data.productType = searchProductType;
	data.startPage = page; // 分页查询
	data.rows = 15; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/policy/after-policy!queryByPage.do",
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

function addTbodyData(data) {
	$(".table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + item.channelCh + "</td>";
		if (item.productType) {
			var_tr += "<td>" + item.productTypeCh + "</td>";
		} else {
			var_tr += "<td>全部</td>";
		}
		if (item.carrier) {
			var_tr += "<td>" + item.carrier + "</td>";
		} else {
			var_tr += "<td>全部</td>";
		}
		var_tr += "<td>" + item.after + "</td>";
		var_tr += "<td>" + item.createTime + "</td>";
		// 拼接json字符串
		var_tr += "<td><a href='javascript:modifyData(\"" + item.id
				+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteData(\""
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
function addData() {
	openDialog();
	reset();
}

function modifyData(id) {
	$.each(cacheData, function(i, item) {
		if (item.id == id) {
			$("#id").val(item.id);
			$("select[name='channel']").val(item.channel);
			$("select[name='productType']").val(item.productType);
			$("#carrier").val(item.carrier);
			$("#after").val(item.after);
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
			url : ctx + "/policy/after-policy!delete.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
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
	$("select[name='channel']").val("");
	$("select[name='productType']").val("");
	$("#carrier").val("");
	$("#after").val("");
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
	$("#lightOk").click(function() {
		var channel = $.trim($("select[name='channel']").val());
		var channelCh = $.trim($("select[name='channel']").find("option:selected").text());
		var productType = $.trim($("select[name='productType']").val());
		var productTypeCh = $.trim($("select[name='productType']").find("option:selected").text());
		var carrier = $.trim($("#carrier").val());
		var after = $.trim($("#after").val());
		if(channel != "") {
			if (carrier != "" && carrier.length > 2) {
				alert("舱位的长度不能大于2");
				return ;
			}
			var entity = {};
			entity.id = $("#id").val();
			entity.channel = channel;
			entity.channelCh = channelCh;
			entity.productType = productType;
			entity.productTypeCh = productTypeCh;
			entity.carrier = carrier;
			entity.after = after;
			$.ajax({
				type : "post",
				dataType : "json",
				data : entity,
				url : ctx + "/policy/after-policy!saveOrUpdate.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					}
					if (result.success) {
						//alert(result.success);
						closeDialog();
						reset();
						getDatas(1);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		} else {
			alert("渠道不能为空");
		}
	});
});