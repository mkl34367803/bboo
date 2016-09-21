var cacheData;
function getDatas(page) {
	var data = {};
	var searchMnoType = $("select[name='searchReportForms']").val();
	var searchIsUsed = $(".searchIsUsed:checked").val();
	data.mnoType = searchMnoType;
	data.isUsed = searchIsUsed;
	data.page = page; // 分页查询
	data.rows = 15; // 每页展示多少条记录
	var oind = layer.open({
		type: 3,
		icon: 1
	});
	$.ajax({
		type : "post",
		dataType : "json",
		data : data,
		url : ctx + "/finance/report-forms!queryByPage.do",
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
		var var_tr = "<td>" + item.id + "</td>";
		/*var_tr += "<td>" + item.mno + "</td>";*/
		var_tr += "<td>" + item.mnoType + "</td>";
		var_tr += "<td>" + item.fieldName + "</td>";
		var_tr += "<td>" + item.description + "</td>";
		var_tr += "<td>" + item.createDate + "</td>";
		if (item.isUsed == "1") {
			var_tr += "<td>启用</td>";
		} else {
			var_tr += "<td>禁用</td>";
		}
		// 拼接json字符串
		/*var_tr += "<td><a href='javascript:modifyDate(\"" + item.id
		+ "\")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteDate(\""
		+ item.id + "\")'>删除</a></td>";*/
		var_tr += "<td><a href='javascript:modifyDate(\"" + item.id
		+ "\")'>修改</a></td>";
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
function addDate() {
	openDialog();
	reset();
}
function modifyDate(id) {
	$.each(cacheData, function(i, item) {
		if (item.id == id) {
			$("#id").val(item.id);
			$("#fieldName").val(item.fieldName);
			$("select[name='reportForms']").val(item.mnoType);
			$("input:radio[value='"+item.isUsed+"']").attr("checked", true);
			$("#description").val(item.description);
		}
	});
	openDialog();
}

function deleteDate(id) {
	if(confirm("确定删除？")){
		var data = {};
		var searchMnoType = $("select[name='searchMnoType']").val();
		data.mnoType = searchMnoType;
		data.id = id;
		$.ajax({
			type : "post",
			dataType : "json",
			
			data : data,
			url : ctx + "/finance/report-forms!delete.do",
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
	$("#fieldName").val("");
	$("select[name='reportForms']").val("");
	$("input:radio[value='1']").attr("checked", "checked");
	$("#description").val("");
}



$(document).ready(function() {
	// 查询
	$(".queryBtn").click(function() {
		getDatas(1);
	});
	// 添加
	$(".addBtn").click(function() {
		addDate();
	});
	// 下载
	$(".downloadBtn").click(function() {
        var mnoType=$("select[name='searchReportForms']").val();
        if("" == mnoType) {
        	alert("请选择业务类型");
        	return;
        }
        var ind = layer.open({
			type: 3,
			icon: 1
		});
        $.post(ctx + "/finance/report-forms!downloadExcel.do",{"mnoType":mnoType},function(result){
        	var data = eval("("+result+")");
        	layer.close(ind);
        	if (data.error) {
				alert(data.error);
			}
        	if (data.zipFilePath) {
        		var content = "<a href='"+ctx+"/"+ data.zipFilePath
				+ "' name='report_forms'  style='font-size:18px; font-weight:bold; color:red;'>"
				+ "点击这下载./" + data.zipFilePath + "</a>";
        		layer.open({                  
        			title: false,
        			closeBtn:0,
        			btn:['关闭'],
        			content:"<div style='width:90%;height:auto; margin:0 auto;'>"
						+ content
						+ "</div>",
        			yes: function(index, layero) {
        				$("input[name='downloadBtn']").val('下载');
        				layer.close(index);
        			}
        		});
			}
          });
	});
	
	$("#lightCancel").click(function() {
		closeDialog();
	});
	$("#lightOk").click(function() {
		var fieldName = $.trim($("#fieldName").val());
		var mnoType = $.trim($("select[name='reportForms']").val());
		var isUsed = $(".isUsed:checked").val();
		var description = $.trim($("#description").val());
		if(fieldName != "" && mnoType != "" && isUsed != "" && description != "" ) {
			var entity = {};
			entity.id = $("#id").val();
			entity.fieldName = fieldName;
			entity.mnoType = mnoType;
			entity.isUsed = isUsed;
			entity.description = description;
			$.ajax({
				type : "post",
				dataType : "json",

				data : entity,
				url : ctx + "/finance/report-forms!saveOrUpdate.do",
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
			alert("参数不能为空");
		}
	});
});