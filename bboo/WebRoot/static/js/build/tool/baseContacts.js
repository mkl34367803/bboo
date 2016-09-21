var cacheData;
function getDatas(page) {
	var data = {};
	var searchLinkman =  $.trim($("input[name='searchLinkman']").val());
	var searchLinktel =  $.trim($("input[name='searchLinktel']").val());
	var searchPhone =  $.trim($("input[name='searchPhone']").val());
	if (searchLinktel != "") {
		if(!checkTel(searchLinktel)) {
			return;
		}
	}
	if (searchPhone != "") {
		if(!checkPhone(searchPhone)) {
			return;
		}
	}
	
	data.linkman = searchLinkman;
	data.linktel = searchLinktel;
	data.phone = searchPhone;
	data.page = page; // 分页查询
	data.rows = 20; // 每页展示多少条记录
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/tool/base-contacts!queryByPage.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.list) {
					addTbodyData(result.list);
				}
				if (result.total== 0) {
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

function checkNumm(data) {
	return data == null ? "" : data;
}

function addTbodyData(data) {
	$(".table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + checkNumm(item.linkman) + "</td>";
		var_tr += "<td>" + checkNumm(item.linktel) + "</td>";
		var_tr += "<td>" + checkNumm(item.phone) + "</td>";
		var_tr += "<td>" + checkNumm(item.email) + "</td>";
		var_tr += "<td>" + checkNumm(item.address) + "</td>";
		var_tr += "<td>" + checkNumm(item.createTime) + "</td>";
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
			$("input[name='linkman']").val(item.linkman);
			$("input[name='linktel']").val(item.linktel);
			$("input[name='phone']").val(item.phone);
			$("input[name='email']").val(item.email);
			$("input[name='address']").val(item.address);
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
			url : ctx + "/tool/base-contacts!delete.do",
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
	$("input[name='linkman']").val("");
	$("input[name='linktel']").val("");
	$("input[name='phone']").val("");
	$("input[name='email']").val("");
	$("input[name='address']").val("");
}

// 验证手机号码的格式
function checkPhone(phone){ 
	if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
		alert("手机号码错误，请重填"); 
		return false; 
	}
	return true;
}

// 验证固定号码的格式
function checkTel(tel){ 
	if(!(/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(tel))){
		alert('联系电话错误，请重填'); 
		return false; 
	} 
	return true;
}

// 验证邮箱的格式
function checkEmail(email) {
	if(!(/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(email))){
		alert('邮箱的格式错误，请重填'); 
		return false; 
	} 
	return true;
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
		var linkman = $.trim($("input[name='linkman']").val());
		var linktel = $.trim($("input[name='linktel']").val());
		var phone = $.trim($("input[name='phone']").val());
		var email = $.trim($("input[name='email']").val());
		var address = $.trim($("input[name='address']").val());
		if(linkman != "") {
			if (linktel != "") {
				if (!checkTel(linktel)) {
					return ;
				}
			}
			if (phone != "") {
				if (!checkPhone(phone)) {
					return ;
				}
			} else {
				alert("手机号码不能为空");
				return ;
			}
			if (email != "") {
				if (!checkEmail(email)) {
					return ;
				}
			} else {
				alert("电子邮箱不能为空");
				return ;
			}
			var entity = {};
			entity.id = $("#id").val();
			entity.linkman = linkman;
			entity.linktel = linktel;
			entity.phone = phone;
			entity.email = email;
			entity.address = address;
			$.ajax({
				type : "post",
				dataType : "json",
				data : entity,
				url : ctx + "/tool/base-contacts!saveOrUpdate.do",
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
			alert("联系人不能为空");
		}
	});
});