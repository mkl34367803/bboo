<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp" %>
<% 
	request.setAttribute("orderDealTag", "true"); //是否是处理页面标志，不是处理页面有些按钮不显示
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>订单支付页面</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript" src="${ctx }/static/js/build/order/orderDeal.js"></script>
<style type="text/css">
.ajaxLoading {
	display: none;
	position: fixed;
	top: 30%;
	left: 30%;
	padding: 16px;
	z-index: 1002;
	overflow: auto;
}
.g-table{
	width: 100%;
}
.mod-hd {
    background: #56a4e0;
    height: 20px;
    padding: 4px 6px 4px 10px;
    line-height: 20px;
    color: #fff;
    vertical-align: middle;
    cursor: pointer;
}
</style>


<script type="text/javascript">
var ctx = "${ctx}";

// 相差天数
	function getDays(departureDate, arrivalDate) {
		var dateArray, dDate, aDate, days;
		dateArray = departureDate.split("-");
		dDate = new Date(dateArray[1]+"-"+dateArray[2]+"-"+dateArray[0]);
		
		dateArray = arrivalDate.split("-");
		aDate = new Date(dateArray[1]+"-"+dateArray[2]+"-"+dateArray[0]);
		days = parseInt((aDate - dDate) / 1000 / 60 / 60 / 24);
		return Math.abs(days);
	}
	// 获取飞行时间
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
	
$(function () {	
	$("#lightCancel").click(function(){
		closeDialog();
	});
	
});

//订单支付接口的调用
function payOrder(orderID) {
	if (confirm("是否确定要支付订单，将打款打对方账户，请谨慎操作")) {
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				id : orderID
			},
			url : "${ctx }/order/order-pay!payOrder.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				} else if (result.success) {
					var flightClass="${orderDetail.flightClass}";
					var startPage="<%=request.getSession().getAttribute("startPage")%>";
					redirectStartPage(flightClass,startPage,ctx);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	}
}

function alreadyPay(orderId){
	if (confirm("如果确认订单已线下支付，订单将变成出票完成，请谨慎操作！")) {
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				orderId : orderId
			},
			url : "${ctx }/order/order-pay!alreadyPay.do",
			success : function(result) {
				if (result.error) {
					alert(result.error);
				} else if (result.success) {
					var flightClass="${orderDetail.flightClass}";
					var startPage="<%=request.getSession().getAttribute("startPage")%>";
					redirectStartPage(flightClass,startPage,ctx);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	}
}

</script>
</head>
<body>
<form action="" method="post" name="savePurchForm" id="savePurchForm"></form>
	<div class="m-content" style="width: 100%">
		<div class="detail-model" style="margin-top: 10px">
			<div class="mod-hd" id="js-mod-orderInfo">
				<script type="text/javascript">
				var flightClass="${orderDetail.flightClass}";
				if(flightClass=="I"){
					document.write("<h2>国际订单支付信息</h2>");			
				}else{
					document.write("<h2>国内订单支付信息</h2>");
				}
				</script>
			</div>
			<div class="g-content">
				<c:if test="${ERROR ne null and ERROR ne '' }">
					<div style="background-color: #fff; padding: 5px; color: red"><h3>${ERROR }</h3></div>
				</c:if>
					<div>
						<table class="g-table order-table" >
							<tr>
							    <c:choose>
									<c:when test="${orderDetail.distributor == 'C'}">
										<td width="30%">主订单号：${orderDetail.billId} <br/>子订单号： ${orderDetail.orderNo} </td>
									</c:when>
									<c:otherwise>
										<td width="30%">订单号： ${orderDetail.orderNo} </td>
									</c:otherwise>
								</c:choose>
								<td width="30%">订单来源：  ${orderDetail.distributorCh}---${orderDetail.shopNameCh}</td>
								<td width="40%" colspan="2" class="detail">
									<h1>
										<script type="text/javascript">
											var status="${buyOrderEntity.status}";
											document.write(getSlfStatusCH(status));
										</script>
									</h1> 
								</td>
							</tr>
							<tr>
								<td>PNR：${orderDetail.pnrCode}</td>
								<td>大编码：${orderDetail.pnrCodeBig}</td>
							</tr>
							<tr>
								<td>采购订单号： ${buyOrderEntity.purchaseNo}</td>
								<td>采购地：  ${buyOrderEntity.purchasePlaceCh} </td>
								<td colspan="2" class="detail" align="center"><h1>
									SELF 订单状态：
									<script type="text/javascript">
									var slfStatus="${buyOrderEntity.slfStatus}";
									document.write(getSlfStatusCH(slfStatus));
									</script>
								</h1> </td>
							</tr>
							<tr>
								<td rowspan="2">锁定人： ${orderDetail.lockUser}</td>
							</tr>
						</table>
						
							
							
						
						<!-- 航班信息 -->
						<table class="g-table table-list">
							<thead class="table-hd ">
								<tr>
									<th class="col01">航班号(实际)</th>
									<th class="col01">舱位(实际)</th>
									<th class="col05">起降城市</th>
									<th class="col05">到达城市</th>
									<th class="col05">起飞时间</th>
									<th class="col05">到达时间</th>
								</tr>
							</thead>
							<tbody class="table-bd">
							<tr>
								<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" begin="0">
									<tr>
										<td>${flight.flightNum } 
											<c:if test="${flight.actFltno != '' }">(${flight.actFltno})</c:if>
										</td>
										<td>${flight.cabin } 
											<c:if test="${flight.actCabin != '' }">(${flight.actCabin})</c:if>
										</td>
										<td>${flight.depAircode}</td>
										<td>${flight.arrAircode}</td>
										<td>${flight.departureDate}&nbsp;${flight.departureTime }
										</td>
										<td>${flight.arrivalDate}&nbsp;${flight.arrivalTime }</td>
									</tr>
								</c:forEach>
							</tr>

							
							</tbody>
						</table>
						
						<!-- 乘客信息 -->
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="col07">乘客姓名</th>
									<th class="col07">乘客类型</th>
									<th class="col12">证件类型</th>
									<th class="col04">证件号</th>
									<th class="col12">票面价</th>
									<th class="col12">基建</th>
									<th class="col12">燃油</th>
									<th class="col12">采购价</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<c:forEach items="${buyOrderEntity.passengers}" var="passenger" varStatus="i" >
									<tr>
										<td>${passenger.name }</td>
										<td>
											<c:if test="${passenger.ageType == '-2' }">其他</c:if>
											<c:if test="${passenger.ageType == '-1' }">留学生</c:if>
											<c:if test="${passenger.ageType == '0' }">成人</c:if>
											<c:if test="${passenger.ageType == '1' }">儿童</c:if>
										</td>
										<td>
											<c:if test="${passenger.cardType == 'PP' }">护照</c:if>
											<c:if test="${passenger.cardType == 'HX' }">回乡证</c:if>
											<c:if test="${passenger.cardType == 'TB' }">台胞证</c:if>
											<c:if test="${passenger.cardType == 'GA' }">港澳通行证</c:if>
											<c:if test="${passenger.cardType == 'HY' }">国际海员证</c:if>
											<c:if test="${passenger.cardType == 'NI' }">身份证</c:if>
											<c:if test="${passenger.cardType == 'XS' }">学生证</c:if>
											<c:if test="${passenger.cardType == 'JR' }">军人证</c:if>
											<c:if test="${passenger.cardType == 'JS' }">驾驶证</c:if>
											<c:if test="${passenger.cardType == 'TW' }">台湾通行证</c:if>
											<c:if test="${passenger.cardType == 'SB' }">士兵证</c:if>
											<c:if test="${passenger.cardType == 'LN' }">临时身份证</c:if>
											<c:if test="${passenger.cardType == 'HK' }">户口簿</c:if>
											<c:if test="${passenger.cardType == 'JG' }">警官证</c:if>
											<c:if test="${passenger.cardType == 'TH' }">其它</c:if>
											<c:if test="${passenger.cardType == 'CS' }">出生证明</c:if>
										</td>
										<td>${passenger.cardNum }</td>
										<td style="background: yellow">${passenger.printPrice }</td>
										<td style="background: yellow">${ passenger.taxFee }</td>
										<td style="background: yellow">${ passenger.oilFee }</td>
										<td style="background: yellow">
											${passenger.payPirce }
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
			</div>
		</div>
		

    	
		
		<div  style=" margin:0 auto; text-align:center;padding: 10px">
			<span style="background-image:url(${ctx}/static/img/build/money.gif);padding-left:45px;background-repeat:no-repeat;font-size: 20px;">支付总金额：${buyOrderEntity.allprice}</span>
			<button id="alreadyPay" class="g-btn js_submit" onclick="alreadyPay('${orderDetail.id}')" style="margin-bottom:8px">
				<span>已经线下支付</span>
			</button>
			<button id="pay" class="g-btn js_submit" onclick="payOrder('${orderDetail.id}')" style="margin-bottom:8px">
				<span>支付</span>
			</button>
		</div>
		

		<div id="light" class="white_content" style="width: 530px;height: 320px">
			<div>
				<textarea id="etermInfo" cols="100" rows="15" style="background-color: black;color: green;"></textarea>
			</div>
			<br>
			<div>
				<!-- 			<button id="lightOk" class="g-btn" >复制</button>&nbsp;&nbsp; -->
				<button id="lightCancel" class="g-btn">关闭</button>
			</div>
		</div>
		<div id="fade" class="black_overlay"></div>
		<div id="loading" class="ajaxLoading"><img src="${ctx }/layer/layer/skin/default/loading-1.gif"></div>
				
	</div>
</body>
</html>