var cacheData;
var showType;
function getDatas(startPage) {
	var data = {};
	var searchClassify = $("select[name='searchClassify']").val();
	var searchQType = $("select[name='searchQType']").val();
	var searchIsuse = $.trim($("input[name='searchIsuse']:checked").val());
	var searchIntoPerson = $.trim($("input[name='searchIntoPerson']").val());
	
	data.classify = searchClassify;
	data.QType = searchQType;
	data.isuse = searchIsuse;
	data.intoPerson = searchIntoPerson;
	data.startPage = startPage; // 分页查询
	data.pageSize = 20; // 每页展示多少条记录
	
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/question/questions-nfilter!queryByPage.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				cacheData = result;
				if (result.list) {
					if (showType == "search") {
						addTbodyData(result.list);
					} else if (showType == "show") {
						addShowData(result.list);
					}
				}
				if (result.total== 0) {
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


function checkNumm(data) {
	return data == null ? "" : data;
}
//题目类别
function checkClassify(data) {
	if (1 == data) {
		return "技术";
	} else if (2 == data) {
		return "航空业务";
	} else if (3 == data) {
		return "财务";
	} else if (4 == data) {
		return "行政";
	}
	return data;
}
//问题类型1 问答题 2选择题 3多选题
function checkQType(data) {
	if (1 == data) {
		return "问答题";
	} else if (2 == data) {
		return "选择题";
	} else if (3 == data) {
		return "多选题";
	}
	return data;
}
//0可用 1删除
function checkIsuse(data) {
	if (0 == data) {
		return "可用";
	} else if (1 == data) {
		return "不可以";
	}
	return data;
}

// 查询数据
function addTbodyData(data) {
	$(".table-bd").html("");
	$.each(data, function(i, item) {
		var var_tr = "<td>" + checkClassify(item.classify) + "</td>";
		var_tr += "<td>" + checkQType(item.qType) + "</td>";
		var question = item.question;
		if (question.length > 30) {
			var_tr += "<td>" + question.substring(0, 30) + "......</td>";
		} else {
			var_tr += "<td>" + question + "</td>";
		}
		var_tr += "<td>" + checkNumm(item.intoPerson) + "</td>";
		var_tr += "<td>" + checkIsuse(item.isuse) + "</td>";
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

//显示数据
function addShowData(data) {
	$(".showDiv").html("");
	var qType = "";
	$.each(data, function(i, item) {
		var div_msg = "";
		if (item.qType != qType) {
			qType = item.qType;
			div_msg += "<h2>" + checkQType(qType) + "</h2>";
		}
		div_msg += addByQtype(i, item);
		$(".showDiv").append(div_msg);
	});
}

function addByQtype(i, item) {
	var div_msg = (i+1) + "、问题：" + item.question + "<br>";
	if (item.qType == 1) {
		div_msg += "<span>答案："+ item.answerTxt + "</span><br>";
	} else {
		div_msg += "<span>A、" + item.a + "</span><span>B、" + item.b + "</span><span>C、" + item.c + "</span><span>D、" + item.d + "</span><br>";
		div_msg += "<span>答案："+ item.answerSelect + "</span><br>";
	}
	return div_msg;
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
	$(".questionDiv").show();
	$(".answerTxtDiv").show();
	$(".selectDiv").hide();
	$(".answerSelectSDiv").hide();
	$(".answerSelectMDiv").hide();
	reset();
}

function modifyData(id) {
	$.each(cacheData.list, function(i, item) {
		if (item.id == id) {
			$("#id").val(item.id);
			$("input[name='intoPerson']").val(item.intoPerson);
			$("input[name='createTime']").val(item.createTime);
			$("select[name='classify']").val(item.classify);
			$("select[name='QType']").val(item.qType);
			$("input[name='question']").val(item.question);
			$("input[name='isuse'][value='"+item.isuse+"']").val();
			var QType = item.qType;
			showDivByQtype(QType);
			if (QType == 1) {
				$("input[name='answerTxt']").val(item.answerTxt);
			} else { 
				$("input[name='A']").val(item.a);
				$("input[name='B']").val(item.b);
				$("input[name='C']").val(item.c);
				$("input[name='D']").val(item.d);
				if (QType == 2) {
					$("input[name='answerSelectS'][value='"+item.answerSelect+"']").attr("checked", true);
				} else if (QType == 3) {
					var as = item.answerSelect;
					var asArr = as.split(",");
					$.each(asArr, function(i, asitem) {
						$("input[name='answerSelectM'][value='"+asitem+"']").attr("checked", true);
					});
				}
			}
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
			url : ctx + "/question/questions-nfilter!delete.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					var startPage = $("#page").html();
					getDatas(startPage);
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
	$("input[name='intoPerson']").val("");
	$("input[name='createTime']").val("");
	$("select[name='classify']").val(1);
	$("select[name='QType']").val(1);
	$("input[name='question']").val("");
	$("input[name='isuse'][value='0']").attr("checked", true);
	$("input[name='answerTxt']").val("");
	$("input[name='A']").val("");
	$("input[name='B']").val("");
	$("input[name='C']").val("");
	$("input[name='D']").val("");
	$("input[name='answerSelectS'][value='A']").attr("checked", true);
	$(".answerSelectMDiv :checkbox").attr("checked", false);
}

function showDivByQtype(qType) {
	if (qType == 1) {
		$(".questionDiv").show();
		$(".answerTxtDiv").show();
		$(".selectDiv").hide();
		$(".answerSelectSDiv").hide();
		$(".answerSelectMDiv").hide();
	} else if (qType == 2) {
		$(".questionDiv").show();
		$(".answerTxtDiv").hide();
		$(".selectDiv").show();
		$(".answerSelectSDiv").show();
		$(".answerSelectMDiv").hide();
	} else if (qType == 3) {
		$(".questionDiv").show();
		$(".answerTxtDiv").hide();
		$(".selectDiv").show();
		$(".answerSelectSDiv").hide();
		$(".answerSelectMDiv").show();
	}
}

function getEntityData() {
	var id = $.trim($("#id").val());
	var intoPerson = $.trim($("input[name='intoPerson']").val());
	var createTime = $.trim($("input[name='createTime']").val());
	
	var classify = $.trim($("select[name='classify']").val());
	var QType = $.trim($("select[name='QType']").val());
	var question = $.trim($("input[name='question']").val());
	var isuse = $.trim($("input[name='isuse']:checked").val());
	
	if (question == "") {
		alert("请输入问题");
		return null;
	}
	if (isuse == "") {
		alert("请输入状态");
	}
	var data = {};
	data.id = id;
	data.intoPerson = intoPerson;
	data.createTime = createTime;
	
	data.classify = classify;
	data.QType = QType;
	data.question = question;
	data.isuse = isuse;
	if (QType == 1) {
		var answerTxt = $.trim($("input[name='answerTxt']").val());
		if (answerTxt == "") {
			alert("请输入答案");
			return null;
		}
		data.answerTxt = answerTxt;
	} else { 
		var A = $.trim($("input[name='A']").val());
		var B = $.trim($("input[name='B']").val());
		var C = $.trim($("input[name='C']").val());
		var D = $.trim($("input[name='D']").val());
		
		if (A == "" || B == "") {
			alert("请输入选项内容");
			return null;
		}
		
		var answerSelect = "";
		if (QType == 2) {
			answerSelect = $.trim($("input[name='answerSelectS']:checked").val());
		} else if (QType == 3) {
			answerSelect = $.trim($("input[name='answerSelectM']").val());
			$("input[name='answerSelectM']:checked").each(function(i) {
				if (i == 0) {
					answerSelect = $(this).val();
				} else {
					answerSelect += "," + $(this).val();
				}
			});
		}
		if (answerSelect == "") {
			alert("请选择答案");
		}
		data.A = A;
		data.B = B;
		data.C = C;
		data.D = D;
		data.answerSelect = answerSelect;
	}
	return data;
}

$(document).ready(function() {
	getDatas(1);
	
	// 查询
	$("#searchButton").click(function() {
		showType = "search";
		$(".SQDiv").show();
		$(".showDiv").hide();
		getDatas(1);
	});
	
	// 显示
	$("#showButton").click(function() {
		showType = "show";
		$(".SQDiv").hide();
		$(".showDiv").show();
		getDatas(1);
	});
	
	// 添加
	$("#addButton").click(function() {
		addData();
	});
	$("#lightCancel").click(function() {
		closeDialog();
		reset();
	});
	$("#lightOk").click(function() {
		var data = getEntityData();
		
		if (data) {
			$.ajax({
				type : "post",
				dataType : "json",
				data : data,
				url : ctx + "/question/questions-nfilter!saveOrUpdate.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					}
					if (result.success) {
						//alert(result.success);
						closeDialog();
						reset();
						var startPage = $("#page").html();
						getDatas(startPage);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		}
	});
	
	$("select[name='QType']").change(function() {
		var qv = $(this).children("option:selected").val();
		showDivByQtype(qv);
	});
});