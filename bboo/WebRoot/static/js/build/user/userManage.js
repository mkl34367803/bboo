var cacheData;
var OPERATE;
function getUsers(page, operate) {
	var searchName = $.trim($("#searchName").val());
	var searchLoginName = $.trim($("#searchLoginName").val());
	var searchCompanyName = $.trim($("#searchCompanyName").val());
	var data = {};
	OPERATE = operate;
	data.searchName = searchName;
	data.searchLoginName = searchLoginName;
	data.searchCompanyName = searchCompanyName;
	data.page = page; // 分页查询
	data.rows = 10; // 每页展示多少条记录
	data.operate = operate;
	var oind = layer.open({
		type: 3,
		icon: 1
	});
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/configure/user!queryUsers.do",
		success : function(result) {
			layer.close(oind);
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
			layer.close(oind);
			alert("请求出错了");
		}
	});
}

function addTbodyData(data) {
	$(".table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + item.userId + "</td>";
		var_tr += "<td>" + item.name + "</td>";
		var_tr += "<td>" + item.loginName + "</td>";
		var_tr += "<td>" + item.ssbm + "</td>";
		var_tr += "<td>" + item.sszw + "</td>";
		var_tr += "<td>" + item.sj + "</td>";
		var_tr += "<td>" + item.ywm + "</td>";
		// 拼接json字符串
		if (OPERATE == "employee") {
			$("#title").text(item.companyName+" 公司员工管理");
			if (item.deleted == "N") {
				var_tr += "<td>启用</td>";
			} else if (item.deleted == "Y") {
				var_tr += "<td>禁用</td>";
			}
			var_tr += "<td><a href='javascript:updateUser(\"" + item.userId
			+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteUser(\""
			+ item.userId + "\")'>删除</a></td>";
		}
		if(OPERATE == "superUser"){
			$("#title").text("超级用户管理");
			var_tr += "<td>" + item.companyName + "</td>";
			if (item.deleted == 'N') {
				var_tr += "<td><a href='javascript:updateDeleted("
					+ item.userId + ", 0)'>禁用</a></td>";
			} else {
				var_tr += "<td><a href='javascript:updateDeleted("
					+ item.userId + ", 1)'>启用</a></td>";
			}			
		}
		var_tr = "<tr>" + var_tr + "</tr>";
		$(".table-bd").append(var_tr);
	});
	$(".table-bd tr:even").addClass("even");
	$(".table-bd tr:odd").addClass("odd");
}

// 启用 - 禁用
function updateDeleted(userId, i) {
	var deleted = null;
	if (i == 0) {
		deleted = "Y";
	} else if (i == 1) {
		deleted = "N";
	}
	$.ajax({
		type : "post",
		dataType : "json",
		
		data : {
			userId : userId,
			deleted: deleted
		},
		url : ctx + "/configure/user!updateDeleted.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				var page = $("#page").html();
				getUsers(page, OPERATE);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});	
}

function openDialog() {
	$("#light").css('display', 'block');
	$("#fade").css('display', 'block');
}
function closeDialog() {
	$("#light").css('display', 'none');
	$("#fade").css('display', 'none');
}
function addUser() {
	openDialog();
	reset();
}
function updateUser(userId) {
	$.each(cacheData, function(i, item) {
		if (item.userId == userId) {
			$("#light").css("height", "285px");
			$("#passDiv").hide();
			$("#mnoDiv").hide();
			$("#roleIdDiv").hide();
			$("#roleId").val(item.roleId);
			$("#userId").val(item.userId);
			$("#name").val(item.name);
			$("#loginName").val(item.loginName);
			$("#password").val(item.password);
			$("#ssbm").val(item.ssbm);
			$("#sszw").val(item.sszw);
			$("#sj").val(item.sj);
			$("#ywm").val(item.ywm);
			$("input:radio[value='"+item.deleted+"']").attr("checked", true);
		}
	});
	openDialog();
}

function deleteUser(userId) {
	if(confirm("确定删除？")){
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : {
				userId : userId
			},
			url : ctx + "/configure/user!deleteUser.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
					var page = $("#page").html();
					getUsers(page, OPERATE)
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});		
	}
}


function reset() {
	$("#light").css("height", "400px");
	$("#passDiv").show();
	//$("#roleIdDiv").show();
	$("#password").val("123456");
	$("#userId").val("");
	$("#name").val("");
	$("#loginName").val("");
	$("#ssbm").val("");
	$("#sszw").val("");
	$("#sj").val("");
	$("#ywm").val("");
	$("#roleId").val("");
	$("input:radio[value='N']").attr("checked", "checked");
}




var checkSJ = function(tel) {
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	if (reg.test(tel)) {
	     return true;
	}else{
	     alert("号码格式错误有误~");
	     return false;
	};
}

var checkRoleId = function(roleId) {
	if (roleId == "") {
		alert("请输入roleId");
		return false;
	} else {
		return true;
	}
}

var getRoleData = function() {
	$.getJSON(ctx + "/configure/base-role!getAllRole.do", function(result) {
		if (result.error) {
			alert(result.error);
		} else if (result.success) {
			var li_str = "<ul>"
			$.each(result.list, function(i, item) {
				//var opstr = "<option value='"+item.roleId+"'>"+item.name+"</option>";
				li_str += "<li><input name='roleCB' value='"+item.roleId+"' type='checkbox'>" +
						"<span>"+item.name+"</span></li>"
			});
			li_str += "</ul>";
			li_str += "<a href='javascript:confRole()'>确定</a>";
			li_str += "<a href='javascript:clearRole()' style='margin-left: 10px;'>清空</a>";
			$(".roleSelect").append(li_str);
		}
	});
}
function confRole() {
	$(".roleSelect").css({"display": "none"});
	var chk_val = getRoleValue();
	$("input[name='roleId'").val(chk_val);
}

function clearRole() {
	$("input[name='roleId'").val("");
	$("input[name='roleCB']").each(function() {
		$(this).attr("checked", false);
	});
};
function getRoleValue() {
	var chk_val = [];
	$("input[name='roleCB']:checked").each(function() {
		chk_val.push($(this).val());
	});
	return chk_val;
}
$(document).ready(function() {
	var aQuery = window.location.href.split("?");
	var operateStr = aQuery[1].split("=");
	var operate = operateStr[1];
	if(operate == "employee"){
		$("#comTd").hide();
		$("#th8").text("启用 / 禁用");
		$("#addButton").hide();
	} else if (operate == "superUser") {
		$("#comTd").show();
		$("#th8").text("公司名称")
	}
	
	getRoleData();
	
	$("#searchButton").click(function() {
		getUsers(1, operate);
	});
	
	$("#addButton").click(function() {
		$(".roleSelect").css({"display": "none"});
		clearRole();
		addUser();
	});
	$("#lightCancel").click(function() {
		closeDialog();
	});
	$("#lightOk").click(function() {
		var name = $.trim($("#name").val());
		var loginName = $.trim($("#loginName").val());
		var password = $.trim($("#password").val());
		var ssbm = $.trim($("#ssbm").val());
		var sszw = $.trim($("#sszw").val());
		var ywm = $.trim($("#ywm").val());
		var sj = $.trim($("#sj").val());
		var roleId = $.trim($("#roleId").val());
		var userId = $.trim($("#userId").val());;
		var fkmercid = $("select[name='fkmercid'").val();
		var url = "";
		if(userId == ""){
			url = ctx + "/configure/user!save.do"
		} else {
			url = ctx + "/configure/user!update.do"
		}
		if(name != "" && loginName != "" && password != "") {
			if (sj != "") {
				if(!checkSJ(sj)) {
					return;
				}
			}
			if (checkRoleId(roleId)) {
				var userEntity = {};
				userEntity.userId = userId;
				userEntity.name = name;
				userEntity.loginName = loginName;
				userEntity.password = password;
				userEntity.ssbm = ssbm;
				userEntity.sszw = sszw;
				userEntity.ywm = ywm;
				userEntity.sj = sj;
				userEntity.roleId = roleId;
				userEntity.deleted = $(".deleted:checked").val();
				$.ajax({
					type : "post",
					dataType : "json",
	
					data : userEntity,
					url : url,
					success : function(result) {
						if (result.error) {
							alert(result.error);
						}
						if (result.success) {
							//alert(result.success);
							closeDialog();
							reset();
							var page = $("#page").html();
							getUsers(1, operate);
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("请求出错了");
					}
				});
			}
		} else {
			alert("名称和密码不能为空");
		}
	});
	
	$("input[name='roleId'").click(function() {
		$(".roleSelect").css({"display": "block"});
	});
	
});