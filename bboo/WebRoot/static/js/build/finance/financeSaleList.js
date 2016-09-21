var cacheData;
function getFinanceSaleList(page) {
	var data = getDate();
	if (data == null) {
		alert("出票日期和起飞日期必填一个");
		return;
	}
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
		url : ctx + "/finance/finance-ctrl!queryByPage.do",
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
			alert("请求出错了");
			layer.close(oind);
		}
	});
}

function addTbodyData(data) {
	$("#listbd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
		var var_tr = "<td>" + item.orderNO + "</td>";
		var_tr += "<td>" + item.fareNO + "</td>";
		var_tr += "<td>" + item.chengJiRenName + "</td>";
		var_tr += "<td>" + item.chuPiaoDate + "</td>";
		var_tr += "<td>" + item.houFan + "</td>";
		var_tr += "<td>" + item.surePrice + "</td>";
		var_tr += "<td>" + item.shiJiChengBen + "</td>";
		var_tr += "<td>" + item.money + "</td>";
		var_tr += "<td>" + item.liRun + "</td>";
		var_tr += "<td>" + item.xiaoShouPojo + "</td>";
		var_tr += "<td>" + item.caiGaoProjo + "</td>";
		var_tr += "<td>" + item.chuaPiaoRen + "</td>";
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
	var chuPiaoDate = $("input[name='chuPiaoDate']").val();
	var toChuPiaoDate = $("input[name='toChuPiaoDate']").val();
	var fightDate = $("input[name='fightDate']").val();
	var toFightDate = $("input[name='toFightDate']").val();
	var xiaoShouProjo = [];
	$("input[name='xiaoShouProjo']:checked").each(function() {
		xiaoShouProjo.push($(this).val());
	});
	var caiGouProjo = $("#caiGouProjo").val();
	var chuPiaoRen = $("input[name='chuPiaoRen']").val();
	var fareNO = $("input[name='fareNO']").val();
	var orderNO = $("input[name='orderNO']").val();
	var fileNO = $("input[name='fileNO']").val();
	data.chuPiaoDate = $.trim(chuPiaoDate);
	data.toChuPiaoDate = $.trim(toChuPiaoDate);
	data.fightDate = $.trim(fightDate);
	data.toFightDate = $.trim(toFightDate);
	data.xiaoShouProjo = $.trim(xiaoShouProjo);
	data.fareNO = $.trim(fareNO);
	data.caiGouProjo = $.trim(caiGouProjo);
	data.chuPiaoRen = $.trim(chuPiaoRen);
	data.orderNO = $.trim(orderNO);
	data.fileNO = $.trim(fileNO);
	
	if ((chuPiaoDate != "" && toChuPiaoDate != "") || (fightDate != "" && toFightDate != "")) {
		return data;
	}
	
	return null;
}

//获取采购平台信息
var getCaiGaoProjos = function() {
	var data = {};
	//var xiaoShouProjo = $("#xiaoShouProjo").val();
	//data.xiaoShouProjo = $.trim(xiaoShouProjo);
	$.ajax({
		type: "post",
		dataType: "json",
		data: data,
		url: ctx + "/finance/finance-ctrl!getCaiGaoProjos.do",
		success: function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.list) {
				var select_vr = "";
				for ( var i = 0; i < result.list.length; i++) {
					select_vr += "<option value='"+result.list[i]+"'>"+result.list[i]+"</option>";
				}
				$("#caiGouProjo").append(select_vr);
			}
		}
	});
}

var cacheCaiGouPojo;

//获取销售平台信息
var getXiaoShouPojos = function() {
	var data = {};
	var caiGaoProjo = $("#caiGouProjo").val();
	data.caiGaoProjo = $.trim(caiGaoProjo);
	if(cacheCaiGouPojo != caiGaoProjo) {
		cacheCaiGouPojo = caiGaoProjo;
		$(".xiaoShouSelect").html("");
		$.ajax({
			type: "post",
			dataType: "json",
			data: data,
			url: ctx + "/finance/finance-ctrl!getXiaoShouPojos.do",
			success: function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.list) {
					var ul_vr = "<ul>";
					for ( var i = 0; i < result.list.length; i++) {
						ul_vr += "<li><input name='xiaoShouProjo' value='"+result.list[i]+"' type='checkbox'>" +
						"<span>"+result.list[i]+"</span></li>"
					}
					ul_vr += "</ul>";
					ul_vr += "<a href='javascript:confXSP()'>确定</a>";
					ul_vr += "<a href='javascript:clearXSP()' style='margin-left: 10px;'>清空</a>";
					$(".xiaoShouSelect").append(ul_vr);
					$(".xiaoShouSelect").css({"display": "block"});
				}
			}
		});
	} else {
		$(".xiaoShouSelect").css({"display": "block"});
	}
}
function confXSP() {
	$(".xiaoShouSelect").css({"display": "none"});
	var i = 0;
	$("input[name='xiaoShouProjo']:checked").each(function() {
		i++;
	});
	if (i == 0) {
		$("input[name='xiaoShouProjoCount']").val("全选");
	} else {
		$("input[name='xiaoShouProjoCount']").val("选取"+i+"个");
	}
}

function clearXSP() {
	$("input[name='xiaoShouProjoCount'").val("");
	$("input[name='xiaoShouProjo']").each(function() {
		$(this).attr("checked", false);
	});
};


$(document).ready(function() {
	$(".xiaoShouProjoDiv").hide();
	
	getCaiGaoProjos();
	
	$("input[name='xiaoShouProjoCount']").click(function() {
		getXiaoShouPojos();
		//alert("aa");
	});
	
		
	// 查询
	$(".queryBtn").click(function() {
		getFinanceSaleList(1);
	});
	// 利润
	$(".profitBtn").click(function() {
		var data = getDate();
		if (data == null) {
			alert("出票日期和起飞日期必填一个");
			return;
		}
		$.ajax({
			type : "post",
			dataType : "json",
			data : data,
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
	});
	
	// 操作
	$(".operateBtn").click(function() {
		location.href = ctx + "/jsp/build/finance/doReportForms.jsp";
	});
	//下载
	$("input[name='downloadBtn']").click(function() {
		var data = getDate();
		if (data == null) {
			alert("出票日期和起飞日期必填一个");
			return;
		}
		var ind = layer.open({
			type: 3,
			icon: 1
		});
		$.post(
			ctx + "/finance/finance-ctrl!downloadExcel.do",data,function(data) {
				var result = eval("("+data+")");
				layer.close(ind);
				if (result.error) {
					alert(result.error);
				}
				if (result.zipFilePath) {
					var content = "<a href='"+ctx+"/"+ result.zipFilePath
					+ "' name='report_forms'  style='font-size:18px; font-weight:bold; color:red;'>"
					+ "点击这下载./" + result.zipFilePath + "</a>";
					layer.open({
						title : false,
						closeBtn : 0,
						btn : [ '关闭' ],
						content : "<div style='width:90%;height:auto; margin:0 auto;'>"
							+ content
							+ "</div>",
							yes : function(index,layero) {
								$("input[name='downloadBtn']").val('下载');
								layer.close(index);
							}
					},'text');
				}
			}
		);
	});
	
	$("body").click(function(event) {
		if ($(event.target).parents(".xiaoShouSelect").lenth == 0) {
			confXSP();
		}
	});
	
});
