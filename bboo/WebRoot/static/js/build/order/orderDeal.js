function getSlfStatusCH(temp_slfStatu) {
	if (temp_slfStatu == 0) {
		return "订座成功等待支付";
	} else if (temp_slfStatu == 1) {
		return "支付成功等待出票";
	} else if (temp_slfStatu == 2) {
		return "出票完成";
	} else if (temp_slfStatu == 5) {
		return "出票中";
	} else if (temp_slfStatu == 12) {
		return "订单取消";
	} else if (temp_slfStatu == 20) {
		return "等待座位确认";
	} else if (temp_slfStatu == 30) {
		return "退票申请中";
	} else if (temp_slfStatu == 31) {
		return "退票完成等待退款";
	} else if (temp_slfStatu == 39) {
		return "退款完成";
	} else if (temp_slfStatu == 40) {
		return "改签申请中";
	} else if (temp_slfStatu == 42) {
		return "改签完成";
	} else if (temp_slfStatu == 50) {
		return "未出票申请退款";
	} else if (temp_slfStatu == 51) {
		return "订座成功等待价格确认";
	} else if (temp_slfStatu == 60) {
		return "支付待确认";
	} else if (temp_slfStatu == 61) {
		return "暂缓出票";
	} else if (temp_slfStatu == 62) {
		return "订单超时";
	} else if (temp_slfStatu == 99) {
		return "待支付（系统）";
	} else if (temp_slfStatu == 70) {
		return "退票留票中";
	} else if (temp_slfStatu == -1) {
		return "其它";
	} else {
		return temp_slfStatu;
	}
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
function splitOrder(id,passengerLength) {
	$.ajaxSettings.traditional=true;
	var chk_value = [];
	$('input[name="passengerID"]:checked').each(function () {
		chk_value.push($(this).val());
	});
	if(passengerLength==chk_value.length){
		alert("选择全部拆单是没有意义的，请只选择部分乘机人");
		return;
	}
	//var id='${orderDetail.id}';
	$.ajax({
		type : "post",
		dataType : "json",
		data:{id:id,passengerIDs:chk_value},
		url : ctx + "/order/gj-order-deal!splitOrder.do",
		success : function (result) {
			if(result.error){
				alert("请选择需要拆分的订单");
			}
			if(result.success){
			//var currentLockUser='<%=request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME")%>';
				//重新刷新一次页面
			document.savePurchForm.action=ctx + "/order/gj-order-detail!queryOrderByID.do?id="+result.success+"&lockUser=";
			document.savePurchForm.submit();
			//window.location.href = ctx + "/order/gj-order-detail!queryOrderByID.do?id="+result.success+"&lockUser="+currentLockUser;
			}
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了！");
		}
	});
}

function getRtInfo(id,passengerId){
	$.ajax({
		type : "post",
		dataType : "json",
		data:{id:id,passengerId:passengerId},
		url : ctx + "/order/order-eterm!getEtermInfo.do",
		success : function (result) {
			if(result.error){
				alert(result.error);
			}
			if(result.success){
				$("#rtInfo").val(result.eterm);
				openRtDialog();
			}
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了！");
		}
	});
}

function openRtDialog(){
	$("#rtQuery").css('display','block');  
	//$("#fade").css('display','block');  
}
function closeRtDialog(){
	$("#rtQuery").css('display','none');  
	$("#fade").css('display','none');  
}
function getAvhInfo(id,flightId){
	$.ajax({
		type : "post",
		dataType : "json",
		data:{id:id,flightId:flightId},
		url : ctx + "/order/order-eterm!getAvhInfo.do",
		success : function (result) {
			if(result.error){
				alert(result.error);
			}
			if(result.success){
				$("#avhInfo").val(result.eterm);
				openAvhDialog();
			}
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了！");
		}
	});
}

function openAvhDialog(){
	$("#avhQuery").css('display','block');  
}
function closeAvhDialog(){
	$("#avhQuery").css('display','none');  
}
// 根据参数名称获取参数值  (获取浏览器地址的参数)
 function getParamValue(name) {
	var paramsArray = getUrlParams();
	if (paramsArray != null) {
		for (var i = 0; i < paramsArray.length; i++) {
			for (var j in paramsArray[i]) {
				if (j == name) {
					return paramsArray[i][j];
				}
			}
		}
	}
	return null;
}
 // 获取地址栏的参数数组  
function getUrlParams() {
	var search = window.location.search;
	// 写入数据字典  
	var tmparray = search.substr(1, search.length).split("&");
	var paramsArray = new Array;
	if (tmparray != null) {
		for (var i = 0; i < tmparray.length; i++) {
			var reg = /[=|^==]/; // 用=进行拆分，但不包括==  
			var set1 = tmparray[i].replace(reg, '&');
			var tmpStr2 = set1.split('&');
			var array = new Array;
			array[tmpStr2[0]] = tmpStr2[1];
			paramsArray.push(array);
		}
	}
	// 将参数数组进行返回  
	return paramsArray;
}

/**
 * 
 * 政策类型 1 NFD 2 清仓产品 3 商旅产品 5 中转产品 6 包机 7 切位 8 航司VIP卡 10000 普通 10001 规则运价 10
 * 金牌 11 远期 13竞价 14特价 15特惠 16 让利 17 特殊 30 优选 31 立减 -1其它
 * 
 * 10001 规则运价 10002 公布运价 10003 私有运价 10004 供应商直连 10005 申请 10006 包机/切位 10007
 * 直销 10008 特惠 10009 固定 K 位 10010 清仓产品 10011 公布转私有 10021 包机/切位 10022 申
 */
function covertPolicyTypeToChinese(policyType){
	/**
	 * 政策类型 1 NFD 2 清仓产品 3 商旅产品 5 中转产品 6 包机 7 切位 8 航司VIP卡 10000 普通 10001 规则运价 10
	 * 金牌 11 远期 13竞价 14特价 15特惠 16 让利 17 特殊 -1其它
	 *  */
	if(policyType==1){
		return "NFD";
	}else if(policyType==2){
		return "清仓产品";
	}else if(policyType==3){
		return "商旅产品";
	}else if(policyType==4){
		return ""; 
	}else if(policyType==5){
		return "中转产品"; 
	}else if(policyType==6){
		return "包机"; 
	}else if(policyType==7){
		return "切位"; 
	}else if(policyType==8){
		return "航司VIP卡"; 
	}else if(policyType==10000){
		return "普通"; 
	}else if(policyType==10){
		return "金牌"; 
	}else if(policyType==11){
		return "远期"; 
	}else if(policyType==12){
		return ""; 
	}else if(policyType==13){
		return "竞价"; 
	}else if(policyType==14){
		return "特价"; 
	}else if(policyType==15){
		return "特惠"; 
	}else if(policyType==16){
		return "让利"; 
	}else if(policyType==17){
		return "特殊"; 
	}else if(policyType==30){
		return "优选"; 
	}else if(policyType==31){
		return "立减"; 
	}else if(policyType==-1){
		return "其他";
	}else if(policyType==10001){
		return "规则运价";
	}else if(policyType==10002){
		return "公布运价"; 
	}else if(policyType==10003){
		return "私有运价"; 
	}else if(policyType==10004){
		return "供应商直连"; 
	}else if(policyType==10005){
		return "申请"; 
	}else if(policyType==10006){
		return "包机/切位"; 
	}else if(policyType==10007){
		return "直销"; 
	}else if(policyType==10008){
		return "特惠"; 
	}else if(policyType==10009){
		return "固定 K 位"; 
	}else if(policyType==10010){
		return "清仓产品"; 
	}else if(policyType==10011){
		return "公布转私有 "; 
	}else if(policyType==10021){
		return "包机/切位"; 
	}else if(policyType==10022){
		return "申"; 
	}else{
		return "";
	}
}

/**
 * 将saleOrderEntity中pnrType从数字转化为对应的汉字
 * 规则如下： 0成人 1儿童 2婴儿 3成人+儿童 4成人+婴儿 5成人+儿童+婴儿 -1其他
 * @param pnrType 数字
 */
function convertSaleOrderEntityPnrType(pnrType){
	if(pnrType==0){
		return "成人";
	}else if(pnrType==1){
		return "儿童 ";
	}else if(pnrType==2){
		return "婴儿";
	}else if(pnrType==3){
		return "<span style='color:red'>含儿童</span>";
	}else if(pnrType==4){
		return "含婴儿";
	}else if(pnrType==5){
		return "<span style='color:red'>含儿童婴儿</span>";
	}else if(pnrType==-1){
		return "其他";
	}
}


/**
 * 将页面跳转到最开始操作的那个页面去
 * @param flightClass  国际还是国内
 * @returns
 */
function redirectStartPage(flightClass,startPage,ctx){
	if(startPage=="orderImport"){
		window.location.href=ctx+"/jsp/build/order/orderImport.jsp";
	}else if(startPage=="orderListInfo"){
		if(flightClass=="I"){
			window.location.href=ctx+"/jsp/build/order/orderListInfo.jsp?flightClass=I";
		}else if(flightClass=="N"){
			window.location.href=ctx+"/jsp/build/order/orderListInfo.jsp?flightClass=N";
		}
	}else if(startPage=="orderPayListInfo"){
		if(flightClass=="I"){
			window.location.href=ctx+"/jsp/build/order/orderPayListInfo.jsp?flightClass=I";
		}else if(flightClass=="N"){
			window.location.href=ctx+"/jsp/build/order/orderPayListInfo.jsp?flightClass=N";
		}
	}
}