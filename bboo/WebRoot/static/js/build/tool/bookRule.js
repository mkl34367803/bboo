var cacheData;
var cacheOptions;
function getDatas(page) {
	var data = {};
	var searchShopName =  $.trim($("select[name='searchShopName']").val());
	data.shopName = searchShopName;
	data.page = page; // 分页查询
	data.rows = 20; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/book-rule!queryByPage.do",
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
		var var_tr = "<td>" + item.shopName + "</td>";
		/*var_tr += "<td>" + item.policyType + "</td>";*/
		var_tr += "<td>" + getOptionDes(item.policyType) + "</td>";
		var_tr += "<td>" + item.bookChannel + "</td>";
		if (item.isDrop == '1') {
			var_tr += "<td>可以</td>";
		} else if (item.isDrop == '2') {
			var_tr += "<td>不可以</td>";
		} else {
			var_tr += "<td>" + item.isDrop + "</td>";
		}
		if (item.isuse == '1') {
			var_tr += "<td>可用</td>";
		} else if (item.isuse == '2') {
			var_tr += "<td>禁用</td>";
		} else {
			var_tr += "<td>" + item.isuse + "</td>";
		}
		var_tr += "<td>" + item.lr + "</td>";
		var_tr += "<td>" + item.includeRule + "</td>";
		var_tr += "<td>" + item.ignoredRules + "</td>";
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
			clearCheckBox();
			$("#id").val(item.id);
			$("select[name='shopName']").val(item.shopName);
			$("select[name='policyType']").val(item.policyType);
			/*$("#bookChannel").val(item.bookChannel);*/
			var bcArray = (item.bookChannel).split(",");
			for ( var i = 0; i < bcArray.length; i++) {
				$("input[name='bookChannel'][value='"+bcArray[i]+"']").attr("checked", "checked");
				if (i == 0) {
					$("input[name='bookChannelCount']").val("");
				} else {
					$("input[name='bookChannelCount']").val("选中"+(i+1)+"个");
				}
			}
			$("input:radio[name='isDrop'][value='"+item.isDrop+"']").attr("checked", true);
			$("input:radio[name='isuse'][value='"+item.isuse+"']").attr("checked", true);
			$("#lr").val(item.lr);
			$("#includeRule").val(item.includeRule);
			$("#ignoredRules").val(item.ignoredRules);
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
			url : ctx + "/tool/book-rule!delete.do",
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
	$("select[name='shopName']").val("");
	$("select[name='policyType']").val("");
	clearCheckBox();
	$("input:radio[name='isDrop'][value='1']").attr("checked", true);
	$("input:radio[name='isuse'][value='1']").attr("checked", true);
	$("#lr").val("");
	$("#includeRule").val("");
	$("#ignoredRules").val("");
}

function conformCheckBox() {
	$(".bookChannelSelect").css({"display": "none"});
	var i = 0;
	$("input[name='bookChannel']:checked").each(function() {
		i++;
	});
	if (i == 0) {
		$("input[name='bookChannelCount']").val("");
	} else {
		$("input[name='bookChannelCount']").val("选中"+i+"个");
	}
}

function clearCheckBox() {
	$("input[name='bookChannelCount'").val("");
	$("input[name='bookChannel']").each(function() {
		$(this).attr("checked", false);
	});
}

function getOptions() {
	$.getJSON(ctx + "/tool/base-select!queryBySelectName.do",{"selectName": "policyType"}, function(result) {
		$.each(result.list, function(i, item) {
			var selectId = item.selectId;
			$.getJSON(ctx + "/tool/base-option!queryBySelectId.do",{"selectId": selectId}, function(result) {
				cacheOptions = result.list;
			});
		});
	});
}

function getOptionDes(optionValue) {
	var des;
	$.each(cacheOptions, function(index, item) {
		if (item.optionValue == optionValue) {
			des = item.descrtption;
			return;
		}
	});
	return des;
}

$(document).ready(function() {
	getOptions();
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
	$("input[name='bookChannelCount'").click(function() {
		$(".bookChannelSelect").css({"display": "block"});
	});
	$("#lightOk").click(function() {
		var shopName = $.trim($("select[name='shopName']").val());
		var policyType = $.trim($("select[name='policyType']").val());
		var bookChannel = [];
		$("input[name='bookChannel']:checked").each(function() {
			bookChannel.push($(this).val());
		});
		var isDrop = $.trim($("input[name='isDrop']:checked").val());
		var isuse = $.trim($("input[name='isuse']:checked").val());
		var lr = $.trim($("#lr").val());
		var includeRule = $.trim($("#includeRule").val());
		var ignoredRules = $.trim($("#ignoredRules").val());
		if(shopName != "" && policyType != "") {
			var bookRule = {};
			bookRule.id = $("#id").val();
			bookRule.shopName = shopName;
			bookRule.policyType = policyType;
			bookRule.bookChannel = bookChannel.toString();
			bookRule.isDrop = isDrop;
			bookRule.isuse = isuse;
			bookRule.lr = lr;
			bookRule.includeRule = includeRule;
			bookRule.ignoredRules = ignoredRules;
			$.ajax({
				type : "post",
				dataType : "json",
				data : bookRule,
				url : ctx + "/tool/book-rule!saveOrUpdate.do",
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