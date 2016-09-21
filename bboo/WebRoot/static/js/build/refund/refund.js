/**
 * 相差天数
 * @param departureDate
 * @param arrivalDate
 * @returns
 */
function getDays(departureDate, arrivalDate) {
	var dateArray, dDate, aDate, days;
	dateArray = departureDate.split("-");
	dDate = new Date(dateArray[1]+"-"+dateArray[2]+"-"+dateArray[0]);
	
	dateArray = arrivalDate.split("-");
	aDate = new Date(dateArray[1]+"-"+dateArray[2]+"-"+dateArray[0]);
	days = parseInt((aDate - dDate) / 1000 / 60 / 60 / 24);
	return Math.abs(days);
}
/**
 * 获取飞行时间
 * @param departureDate
 * @param arrivalDate
 * @param departureTime
 * @param arrivalTime
 * @returns {String}
 */
function getTime(departureDate, arrivalDate, departureTime, arrivalTime) {
	var dTimes,
	aTimes,
	dTime,
	aTime;
	if (departureDate == arrivalDate) {
		dTimes = departureTime.split(":");
		aTimes = arrivalTime.split(":");
		dTime = parseInt(dTimes[0]) * 60 + parseInt(dTimes[1]);
		aTime = parseInt(aTimes[0]) * 60 + parseInt(aTimes[1]);
	} else {
		var dateDif = getDays(departureDate, arrivalDate);
		dTimes = departureTime.split(":");
		aTimes = arrivalTime.split(":");
		dTime = parseInt(dTimes[0]) * 60 + parseInt(dTimes[1]);
		aTime = parseInt(aTimes[0]) * 60 + parseInt(aTimes[1]) + 24 * 60 * dateDif;
	}
	var h = parseInt((aTime - dTime) / 60);
	var m =  parseInt((aTime - dTime) % 60);
	return h + "小时" + m + "分钟";
}

/**
 * 获取和当前时间的间隔时间（小时）
 * @param departureDate
 * @param departureTime
 * @returns {Number}
 */
function getHour(departureDate, departureTime) {
	var departureD = new Date(departureDate + " " + departureTime);
	var nowDate = new Date();
	var d = departureD.getTime();
	var n = nowDate.getTime();
	var dp = d - n;
	var dph = dp / 1000 / 60 / 60;
	return dph;
}
	
function checkAll(name) {
	var el = document.getElementsByTagName('input');
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = true;
		}
	}
}
function clearAll(name) {
	var el = document.getElementsByTagName('input');
	var len = el.length;
	for (var i = 0; i < len; i++) {
		if ((el[i].type == "checkbox") && (el[i].name == name)) {
			el[i].checked = false;
		}
	}
}

// 换成已退票信息
var chacheRefund;

/**
 * 检查乘机人id
 * @param id
 */
function checkPsg(id) {
	$("input[name='passengerID'").each(function() {
		if ($(this).val() == id) {
			$(this).attr("checked", false);
			$(this).attr("disabled", "disabled");
		}
	});
};

/**
 * 检查航班id
 * @param id
 */
function checkFlt(id) {
	$("input[name='flightID'").each(function() {
		if ($(this).val() == id) {
			$(this).attr("checked", false);
			$(this).attr("disabled", "disabled");
		}
	});
};

/**
 * 检查是否退票
 */
function checkRefund() {
	var data = {};
	data.fkid = fkid;
	if ("refund" == operator) {
		
	} else if ("reserve" == operator) {
		data.state = "2";
		data.orderStatus = "31";
	}
	$.ajax({
		type : "post",
		dataType : "json",
		data: data,
		url: ctx + "/refund/refund!queryByBaseRefund.do",
		success: function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.list) {
					chacheRefund = result.list;
					$.each(result.list, function(i, item) {
						var psgid = item.psgid;
						var fltid = item.fltid;
						checkPsg(psgid);
					});
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

/**
 * 计算利润
 */
function getProfit() {
	var fee = $("input[name='fee']").val();
	var fee2 = $("input[name='fee2']").val();
	var profit = parseFloat(fee2) - parseFloat(fee);
	$("input[name='profit']").val(profit.toFixed(2));
};

/**
 * 计算退票数据
 * @param i
 */
function caculate(i) {
	var face = $("input[name='face"+i+"']").val();
	var baseFace = $("input[name='baseFace"+i+"']").val();
	var yq = $("input[name='yq"+i+"']").val();
	var tax = $("input[name='tax"+i+"']").val();
	var rate = $("input[name='rate"+i+"']").val();
	var fee = baseFace * rate / 100;
	$("input[name='fee"+i+"']").val(fee.toFixed(2));
	var ct;
	if (i == 1) {
		ct = cost;
	} else {
		ct = cost2;
	}
	var refund = ct - fee;
	if (refund < 0) {
		refund = 0;
	}
	$("input[name='refund"+i+"']").val(refund.toFixed(2));
	var actRefund = parseFloat(refund) + parseFloat(yq) + parseFloat(tax);
	$("input[name='actRefund"+i+"']").val(actRefund.toFixed(2));
	getProfit();
}

/**
 * 获取退票数据
 * @returns
 */
function getData() {
	var data = {};
	data.fkid = fkid;
	var flightID = [];
	$("input[name='flightID']:checked").each(function(i, item) {
		flightID.push($(this).val());
	});
	var passengerID = [];
	$("input[name='passengerID']:checked").each(function(i, item) {
		passengerID.push($(this).val());
	});
	
	if (flightID.length == 0 || passengerID.length == 0) {
		alert("请选择航程和乘客");
		return null;
	}
	
	var state = $("input[name='state']:checked").val();
	data.fltids = flightID.toString();
	data.psgids = passengerID.toString();
	if ("refund" == operator) {
		if ("1" == state) {
			data.orderStatus = "31";
		} else {
			data.orderStatus = "70";
		}
		data.state = 1;
	} else if ("reserve" == operator) {
		data.state = "2";
		data.orderStatus = "31";
	}
	
	var face = $.trim($("input[name='face']").val());
	var baseFace = $.trim($("input[name='baseFace']").val());
	var yq = $.trim($("input[name='yq']").val());
	var tax = $.trim($("input[name='tax']").val());
	var rate = $.trim($("input[name='rate']").val());
	var fee = $.trim($("input[name='fee']").val());
	var refund = $.trim($("input[name='refund']").val());
	var actRefund = $.trim($("input[name='actRefund']").val());
	var rtype = $("input[name='rtype']:checked").val();
	var isvoid = $("input[name='isvoid']:checked").val();
	
	var face2 = $.trim($("input[name='face2']").val());
	var baseFace2 = $.trim($("input[name='baseFace2']").val());
	var yq2 = $.trim($("input[name='yq2']").val());
	var tax2 = $.trim($("input[name='tax2']").val());
	var rate2 = $.trim($("input[name='rate2']").val());
	var fee2 = $.trim($("input[name='fee2']").val());
	var refund2 = $.trim($("input[name='refund2']").val());
	var actRefund2 = $.trim($("input[name='actRefund2']").val());
	var rtype2 = $("input[name='rtype2']:checked").val();
	var isvoid2 = $("input[name='isvoid2']:checked").val();
	
	if (face2 == "" || baseFace2 == "" || yq2 == "" || tax2 == "" || rate2 == "" || fee2 == "" || refund2 == "" || actRefund2 == "" || rtype2 == "" || isvoid2 == "") {
		alert("数据不能为空");
	}
	
	data.face = face;
	data.baseFace = baseFace;
	data.yq = yq;
	data.tax = tax;
	data.rate = rate;
	data.fee = fee;
	data.refund = refund;
	data.actRefund = actRefund;
	data.rtype = rtype;
	data.isvoid = isvoid;
	
	data.face2 = face2;
	data.baseFace2 = baseFace2;
	data.yq2 = yq2;
	data.tax2 = tax2;
	data.rate2 = rate2;
	data.fee2 = fee2;
	data.refund2 = refund2;
	data.actRefund2 = actRefund2;
	data.rtype2 = rtype2;
	data.isvoid2 = isvoid2;
	
	if (brid) {
		data.brid = brid;
	}
	return data;
};

/**
 * 通过出发到达时间获取飞行时间
 * @param dTime
 * @param aTime
 * @returns {String}
 */
function getFlightTime(dTime, aTime) {
	var dTimes = dTime.split(":");
	var aTimes = aTime.split(":");
	var ds = 0;
	var as = 0;
	if (parseInt(aTimes[0]) >= parseInt(dTimes[0])) {
		ds = parseInt(dTimes[0]) * 60 + parseInt(dTimes[1]);
		as = parseInt(aTimes[0]) * 60 + parseInt(aTimes[1]);
	} else {
		ds = parseInt(dTimes[0]) * 60 + parseInt(dTimes[1]);
		as = parseInt(aTimes[0]) * 60 + parseInt(aTimes[1]) + 24 * 60;
	}
	if (ds > as) {
		as += 60 * 24;
	}
	var h = parseInt((as - ds) / 60) + "小时" ;
	var m =  parseInt((as - ds) % 60) + "分钟";
	return h + m;
};

/**
 * 输出航班时刻信息
 */
function writeFlightDynamics(fligntNumArr, item) {
	var fd_td = "";
	var fd_div = "";
	$.each(fligntNumArr, function(fi, fitem) {
		if(fitem == item.flightNo) {
			fd_td += "<tr>";
			fd_td += "<td><img style='width: 30px;height: 25px;' src='"+ctx+"/images/air/"+(item.flightNo).substring(0,2)+".png'>&nbsp;"+item.flightNo+"</td>";
			fd_td += "<td>"+item.DAirportCode+"</td>";
			fd_td += "<td>"+item.AAirportCode+"</td>";
			fd_td += "<td>"+item.DDate+"</td>";
			fd_td += "<td>计划&nbsp;&nbsp;"+item.planDDateTime+"<br>实际&nbsp;&nbsp;"+item.actDDateTime+"</td>";
			fd_td += "<td>计划&nbsp;&nbsp;"+item.planADateTime+"<br>实际&nbsp;&nbsp;"+item.actADateTime+"</td>";
			fd_td += "<td>"+getFlightTime(item.actDDateTime, item.actADateTime)+"</td>";
			fd_td += "<td style='font-size: 15px;'><strong>"+item.statusCh+"</strong></td>";
			fd_td += "</tr>";
			
		}
	});
	$(".flightDynamics-td").append(fd_td);
}

/**
 * 获取航班时刻信息
 * @param data
 */
function getFlightDynamics(data) {
	$.ajax({
		type: "post",
		url: ctx + "/refund/base-refund!getFlightDynamics.do",
		data: data,
		dataType: "json",
		success: function(result) {
			if (result.error) {
				alert(result.error);
			} else if (result.list) {
				$(".flightDynamics").show();
				var datas = result.list.datas;
				var fd_td = "";
				$.each(datas, function(i, item) {
					writeFlightDynamics(sharNumArr, item);
					writeFlightDynamics(fligntNumArr, item);
				});
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
};

/**
 * 查询航班时刻信息
 */
function queryFlightDynamics() {
	for (var int = 0; int < depAircodeArr.length; int++) {
		if (depAircodeArr[int] != "") {
			var data = {};
			data.depCode = depAircodeArr[int];
			data.arrCode = arrAircodeArr[int];
			data.depDate = departureDateArr[int];
			getFlightDynamics(data);
		}
	}
};

/**
 * 获取当前日期
 * @returns {String}
 */
function getSysDate() {
	var date = new Date();
	var newDate = "";
	if (date.getMonth() < 9) {
		newDate = date.getFullYear()+"-0"+(date.getMonth()+1)+"-"+date.getDate();
	} else {
		newDate = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
	}
	return newDate;
}

/**
 * 计算退票费率
 * @param hour
 * @param returnRule
 * @returns
 */
function caculateRefundRate(hour, returnRule) {
	var returnRuleArr = returnRule.split("-");
	var h = parseInt(hour);
	if (returnRule == 0) {
		return 0;
	} else if (returnRule == "0-0-0") {
		return 100;
	} else if (returnRuleArr.length == 3) {
		if (h > parseInt(returnRuleArr[1])) {
			return parseInt(returnRuleArr[0]);
		} else {
			return parseInt(returnRuleArr[2]);
		}
	} else if (returnRuleArr.length == 5) {
		if (h > parseInt(returnRuleArr[1])) {
			return parseInt(returnRuleArr[0]);
		} else if(h > parseInt(returnRuleArr[3])) {
			return parseInt(returnRuleArr[2]);
		} else {
			return parseInt(returnRuleArr[4]);
		}
	}
}

/**
 * 获取航司退票费率
 * @param returnRule 退票规则
 */
function getRefundRate(returnRule) {
	var rate2 = 0;
	for ( var i = 0; i < departureDateArr.length; i++) {
		var hour = getHour(departureDateArr[i], departureTimeArr[i]);
		rate2 = caculateRefundRate(hour, returnRule);
	}
	return rate2;
}

/**
 * 获取舱位退改签信息
 * @param carrier
 * @param cabin
 */
function getCabinRule(carrier, cabin) {
	var data = {};
	data.carrier = carrier;
	data.cabin = cabin;
	data.startValidity = getSysDate();
	$.ajax({
		type: "post",
		data: data,
		dataType: "json",
		url: ctx + "/rule/cabin-rule!queryList.do",
		success: function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				if (result.list) {
					var td_var = "";
					var refund_rule = "";
					var rate2 = 0;
					$.each(result.list, function(i, item) {
						td_var += "航司："+item.carrier+"<br>舱位："+item.cabin+"<br>"
						td_var += "退票规则："+getRule(item.returnRule, "returnRule")+"<br>";
						td_var += "改期规则："+getRule(item.changeRule, "changeRule")+"<br>";
						if (item.endorsement == "是") {
							td_var += "签转：允许签转<br>";
						} else if (item.endorsement == "否") {
							td_var += "签转：不允许签转<br>";
						}
						td_var += "废票："+ getVoidDay(item.voidDay)+"<br>";
						td_var += "<br>";
						
						refund_rule += "航司退票规则："+getRule(item.returnRule, "returnRule")+"<br><br>";
						refund_rule += "航司改期规则："+getRule(item.changeRule, "changeRule")+"<br>";
						
						rate2 += getRefundRate(item.returnRule);
					});
					$(".cabinRuldDiv").append(td_var);
					
					$(".refundRuleDiv").append(refund_rule);
					
					// 插入航司退票费率并计算
					$("input[name='rate2']").val(rate2);
					caculate(2);
				}
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

/**
 * 获取作废信息
 * @param voidDay
 * @returns {String}
 */
function getVoidDay(voidDay) {
	if (voidDay == "0") {
		return "当天出票可以作废";
	} else if (voidDay == "1") {
		return "当天出票当天起飞以外航班可以作废";
	} else if (voidDay == "2") {
		return "当天出票1天以外的航班可以作废";
	} else if (voidDay == "-1") {
		return "不允许作废";
	}
}

/**
 * 获取退票或改期规则
 * @param rule
 * @param ruleType
 * @returns {String}
 */
function getRule(rule, ruleType) {
	var rt = "";
	if (ruleType == "returnRule") {
		rt = "退票";
	} else if (ruleType == "changeRule") {
		rt = "改期";
	}
	if (rule == "0") {
		return "不允许"+rt;
	} else if (rule == "0-0-0") {
		return "不收手续费";
	} else {
		var ruleArr = rule.split("-");
		if (ruleArr.length == 3) {
			if (ruleArr[1] == 0) {
				return "起飞前手续费的比例为"+ruleArr[0]+"%，起飞后手续费的比例为"+ruleArr[2]+"%；";
			} else {
				return "起飞"+ruleArr[1]+"小时之前手续费的比例为"+ruleArr[0]+"%，起飞前"+ruleArr[1]+"小时之后手续费的比例为"+ruleArr[2]+"% ；";
			}
		} else if (ruleArr.length == 5) {
			if (ruleArr[3] == 0) {
				return "起飞"+ruleArr[1]+"小时之前手续费的比例为"+ruleArr[0]+"%，起飞前"+ruleArr[1]+"小时之后到起飞前手续费的比例为"+ruleArr[2]+"% ，起飞后手续费的比例为"+ruleArr[4]+"%；";
			} else {
				return "起飞"+ruleArr[1]+"小时之前手续费的比例为"+ruleArr[0]+"%，起飞前"+ruleArr[1]+"小时之后到起飞"+ruleArr[3]+"小时之前手续费的比例为"+ruleArr[2]+"% ，起飞前"+ruleArr[3]+"小时之后手续费的比例为"+ruleArr[4]+"%；";
			}
		}
	}
}

/**
 * 通过退票费计算
 * @param i
 */
function caculateByFee(i) {
	var face = $("input[name='face"+i+"']").val();
	var baseFace = $("input[name='baseFace"+i+"']").val();
	var yq = $("input[name='yq"+i+"']").val();
	var tax = $("input[name='tax"+i+"']").val();
	var fee = $("input[name='fee"+i+"']").val();
	var rate = fee / baseFace * 100;
	$("input[name='rate"+i+"']").val(rate.toFixed(2));
	var refund = cost - fee;
	if (refund < 0) {
		refund = 0;
	}
	$("input[name='refund"+i+"']").val(refund.toFixed(2));
	var actRefund = parseFloat(refund) + parseFloat(yq) + parseFloat(tax);
	$("input[name='actRefund"+i+"']").val(actRefund.toFixed(2));
	getProfit();
}

function showDialog(msg) {
	layer.alert(msg);
}

// 缓存RT信息
var cacheRtMsg;
/**
 * 获取RT信息
 */
function getRtMsg() {
	$.each(passengerIdArr, function(i, psgId) {
		var data = {};
		data.id = buyorderId;
		data.passengerId = psgId;
		$.ajax({
			type: "post",
			data: data,
			dataType: "json",
			url: ctx + "/refund/base-refund!getEtermInfo.do",
			success: function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					if (result.eterm) {
						cacheRtMsg = result.eterm;
						showDialog(result.eterm);
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	});
}

$(function () {
	
	checkRefund();
	
	$("input[name='flightID']").click(function() {
		if ($(this).attr("checked") == "checked") {
			var fltid = $(this).val();
			checkAll("passengerID");
			$.each(chacheRefund, function(i, item) {
				if (fltid == item.fltid) {
					var psgid = item.psgid;
					checkPsg(psgid);
				}
			});
		}
	});

	$("div.mod-hd").click(function () {
		$(this).next().toggle();
		if ($(this).find("span.show").text() == "收起") {
			$(this).find("span.show").text("展开");
		} else {
			$(this).find("span.show").text("收起");
		}
	});
	
	$(".supRefund").hide();
	
	//下一步
	$("input[name='nextBtn']").click(function() {
		var face = $.trim($("input[name='face']").val());
		var baseFace = $.trim($("input[name='baseFace']").val());
		var yq = $.trim($("input[name='yq']").val());
		var tax = $.trim($("input[name='tax']").val());
		var rate = $.trim($("input[name='rate']").val());
		var fee = $.trim($("input[name='fee']").val());
		var refund = $.trim($("input[name='refund']").val());
		var actRefund = $.trim($("input[name='actRefund']").val());
		var rtype = $("input[name='rtype']:checked").val();
		var isvoid = $("input[name='isvoid']:checked").val();
		if (face != "" && baseFace != "" && yq != "" && tax != "" && rate != "" && fee != "" && refund != "" && actRefund != "" && rtype != "" && isvoid != "") {
			$(".psgRefund").hide();
			$(".supRefund").show();
			$(".psgRuleDiv").hide();
			$(".refundRuleDiv").show();
		} else {
			alert("数据不能为空");
		}
	});
	
	// 上一步
	$("input[name='backBtn']").click(function() {
		$(".psgRefund").show();
		$(".supRefund").hide();
		$(".psgRuleDiv").show();
		$(".refundRuleDiv").hide();
	});
	
	$("input[name='face']").keyup(function() {
		caculate("");
	});
	
	$("input[name='baseFace']").keyup(function() {
		caculate("");
	});

	$("input[name='yq']").keyup(function() {
		caculate("");
	});
	
	$("input[name='tax']").keyup(function() {
		caculate("");
	});
	
	$("input[name='rate']").keyup(function() {
		caculate("");
	});
	
	$("input[name='fee']").keyup(function() {
		caculateByFee("");
	});
	
	
	
	$("input[name='face2']").keyup(function() {
		caculate(2);
	});
	
	$("input[name='baseFace2']").keyup(function() {
		caculate(2);
	});

	$("input[name='yq2']").keyup(function() {
		caculate(2);
	});
	
	$("input[name='tax2']").keyup(function() {
		caculate(2);
	});
	
	$("input[name='rate2']").keyup(function() {
		caculate(2);
	});
	
	$("input[name='fee2']").keyup(function() {
		caculateByFee("2");
	});
	
	// 退票  留票
	$("input[name='refundBtn'").click(function() {
		var data = getData();
		data.passengerCount = passengerCount;
		data.airlineCount = airlineCount;
		if (data == null) {
			return;
		}
		$.ajax({
			type: "post",
			data: data,
			dataType: "json",
			url: ctx + "/refund/refund!save.do",
			success: function(result) {
				if (result.error) {
					alert(result.error);
				}
				if (result.success) {
					window.location.href = ctx + "/jsp/build/refound/refundQuery.jsp?operator=" + operator;
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	});
	
	// 查询航班时刻信息
	queryFlightDynamics();
	
	// 查询舱位退改签信息
	for ( var i = 0; i < carrierArr.length; i++) {
		getCabinRule(carrierArr[i], cabinArr[i]);
	}
	
	$(".rtMsg").click(function() {
		if (cacheRtMsg) {
			showDialog(cacheRtMsg);
		} else {
			getRtMsg();
		}
	});
	
});