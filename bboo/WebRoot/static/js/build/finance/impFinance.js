var cacheData;
function getDataList(page) {
	var data = getDate();
	data.page = page; // 分页查询
	data.rows = 20; // 每页展示多少条记录
	var oind = layer.open({
		type: 3,
		icon: 1
	});
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/finance/finance-file!queryByPage.do",
		success : function(result) {
			layer.close(oind);
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
			layer.close(oind);
			alert("请求出错了");
		}
	});
}

function addTbodyData(data) {
	$("#listbd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + item.fileno + "</td>";
		var_tr += "<td>" + item.fileName + "</td>";
		var_tr += "<td><a href='"+ctx+item.filePath+"' target='_blank'>.../"+item.fileName+"</a> </td>";
		var_tr += "<td>" + item.fileSize + "</td>";
		var_tr += "<td>" + item.count + "</td>";
		var_tr += "<td>" + item.fileType + "</td>";
		var_tr += "<td>" + item.createTime + "</td>";
		var_tr += "<td>" + item.operator + "</td>";
		var_tr += "<td><a href='javascript:showProfit(\"" + item.fileno
		+ "\")'>利润</a>&nbsp;&nbsp;<a href='javascript:deleteData(\""
		+ item.id + "\",\""+item.fileno+"\")'>删除</a></td>";
		var_tr = "<tr>" + var_tr + "</tr>";
		$("#listbd").append(var_tr);
	});
	$("#listbd tr:even").addClass("even");
	$("#listbd tr:odd").addClass("odd");
}

function openDialog() {
	$("#light").css('display', 'block');
	$("#fade").css('display', 'block');
}

function closeDialog() {
	$("#light").css('display', 'none');
	$("#fade").css('display', 'none');
}

var getDate = function() {
	var data = {};
	var startDate = $("input[name='startDate']").val();
	var endDate = $("input[name='endDate']").val();
	var searchFileNo = $("input[name='searchFileNo']").val();
	var searchFileName = $("input[name='searchFileName']").val();
	var searchOperator = $("input[name='searchOperator']").val();
	data.startDate = $.trim(startDate);
	data.endDate = $.trim(endDate);
	data.searchFileNo = $.trim(searchFileNo);
	data.searchFileName = $.trim(searchFileName);
	data.searchOperator = $.trim(searchOperator);
	return data;
}

// 利润
function showProfit(fileno) {
	$.ajax({
		type : "post",
		dataType : "json",
		data : {fileno: fileno},
		url : ctx + "/finance/finance-ctrl!getProfit.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.list) {
					openDialog();
					var resultJson=eval(result.list);
					$("#profitbd").html("");
					for(var a=0;a<resultJson[0].length;a++){
						var var_tr = "<tr>"
							+"<td>"+resultJson[0][a].caiGaoProjo+"</td>"
							+"<td>"+resultJson[1][a]+"</td>"
							+"<td>"+resultJson[0][a].surePrice+"</td>"
							+"<td>"+resultJson[0][a].shiJiChengBen+"</td>"
							+"<td>"+resultJson[0][a].liRun+"</td>"
							+"</tr>";
						$("#profitbd").append(var_tr);
					}
					var vartr = "<tr>" +
							"<td>总计</td>"+
							"<td>"+resultJson[2][0]+"</td>"+
							"<td>"+resultJson[2][1]+"</td>"+
							"<td>"+resultJson[2][2]+"</td>"+
							"<td>"+resultJson[2][3]+"</td>"+
							"</tr>";
					vartr += "<tr><td colspan='5'><input type='button' class='g-btn' onclick='closeDialog();' value='关闭'/></td></tr>"
					$("#profitbd").append(vartr);
					$("#profitbd tr:even").addClass("even");
					$("#profitbd tr:odd").addClass("odd");
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
	
}

function deleteData(id, fileno) {
	if(confirm("确定删除？")){
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : {
				id : id,
				fileno : fileno
			},
			url : ctx + "/finance/finance-file!deleteFile.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					//alert(result.success);
					var page = $("#page").html();
					getDataList(page);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});		
	}
}

$(document).ready(function() {
	
	// 上传Excel
	$("input[name='upLoadFile']").click(function() {
		var reportForms = $("select[name='reportForms']").val();
		var file = $("#file")[0].files[0];
		if (reportForms == "") {
			alert("报价类型不能为空");
			return;
		}
		if (file == null) {
			alert("上传文件不能为空");
			return;
		}
		var ind = layer.msg('  导入中...', {
			icon: 16,
			time: 0,
			shade: [0.3, '#000']
		});
		var formData = new FormData();
		formData.append("file", file);
		formData.append("fileType", reportForms);
		$.ajax({
			url: ctx + "/finance/finance-file!uploadFiles.do",
			type: "post",
			data: formData,
			dataType: "json",
			processData: false,
			contentType: false,
			success: function(result) {
				layer.close(ind);
				if (result.error) {
					alert(result.error);
				} else if (result.success) {
					getDataList(1);
					alert(result.success);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("input[name='upLoadFile'").val("上传");
				alert("请求出错了");
			}
		});
	});
		
	// 查询
	$("input[name='querydata']").click(function() {
		getDataList(1);
	});
	
	// 查询导入数据
	$("input[name='queryImportData']").click(function() {
		window.location.href=ctx + "/jsp/build/finance/financeSaleList.jsp";
	});
	
});
