<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp" %>
<% 
	request.setAttribute("orderDealTag", "true"); //是否是处理页面标志，不是处理页面有些按钮不显示
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>订单处理页面</title>
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
	$("div.mod-hd").click(function () {
		$(this).next().toggle();
		if ($(this).find("span.show").text() == "收起") {
			$(this).find("span.show").text("展开");
		} else {
			$(this).find("span.show").text("收起");
		}
	});
});
</script>
</head>

<body>
<form action="" method="post" name="savePurchForm" id="savePurchForm"></form>
	<div class="m-content" style="width: 98%">
		<div class="detail-model" style="margin-top: 10px">
			<div class="mod-hd" id="js-mod-orderInfo">
				<span class="show">收起</span>
				<script type="text/javascript">
				var flightClass="${orderDetail.flightClass}";
				if(flightClass=="I"){
					document.write("<h2>国际订单信息</h2>");			
				}else{
					document.write("<h2>国内订单信息</h2>");
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
								<td width="30%">订单来源： ${orderDetail.distributorCh}---${orderDetail.shopNameCh}</td>
								<td width="40%" colspan="2" class="detail">
									<h1>
									<c:if test="${orderDetail.status == '0' }">订座成功等待支付</c:if>
									<c:if test="${orderDetail.status == '1' }">支付成功等待出票</c:if>
									<c:if test="${orderDetail.status == '2' }">出票完成</c:if>
									<c:if test="${orderDetail.status == '5' }">出票中</c:if>
									<c:if test="${orderDetail.status == '12' }">订单取消</c:if>
									<c:if test="${orderDetail.status == '20' }">等待座位确认</c:if>
									<c:if test="${orderDetail.status == '30' }"> 退票申请中</c:if>
									<c:if test="${orderDetail.status == '31' }">退票完成等待退款</c:if>
									<c:if test="${orderDetail.status == '39' }">退款完成</c:if>
									<c:if test="${orderDetail.status == '40' }">改签申请中</c:if>
									<c:if test="${orderDetail.status == '42' }">改签完成</c:if>
									<c:if test="${orderDetail.status == '50' }">未出票申请退款</c:if>
									<c:if test="${orderDetail.status == '51' }">订座成功等待价格确认</c:if>
									<c:if test="${orderDetail.status == '60' }">支付待确认</c:if>
									<c:if test="${orderDetail.status == '61' }">暂缓出票</c:if>
									<c:if test="${orderDetail.status == '62' }">订单超时</c:if>
									<c:if test="${orderDetail.status == '-1' }">其它</c:if>
									</h1> 
								</td>
							</tr>
							<tr>
								<td>编码：${orderDetail.pnrCode}</td>
								<td class="textred">最晚出票时限：<!-- 2016-05-23 15:40:08 -->-</td>
							</tr>
							<tr>
								<td>分销商原编码：${orderDetail.salePnrCode}</td>
								<td>奖 励： ¥<!-- 0.00 --> - 元 </td>
								<td colspan="2" class="detail" align="center"><h1>
									SELF 订单状态：
									<c:if test="${orderDetail.slfStatus == '0' }">订座成功等待支付</c:if>
									<c:if test="${orderDetail.slfStatus == '1' }">支付成功等待出票</c:if>
									<c:if test="${orderDetail.slfStatus == '2' }">出票完成</c:if>
									<c:if test="${orderDetail.slfStatus == '5' }">出票中</c:if>
									<c:if test="${orderDetail.slfStatus == '12' }">订单取消</c:if>
									<c:if test="${orderDetail.slfStatus == '20' }">等待座位确认</c:if>
									<c:if test="${orderDetail.slfStatus == '30' }"> 退票申请中</c:if>
									<c:if test="${orderDetail.slfStatus == '31' }">退票完成等待退款</c:if>
									<c:if test="${orderDetail.slfStatus == '39' }">退款完成</c:if>
									<c:if test="${orderDetail.slfStatus == '40' }">改签申请中</c:if>
									<c:if test="${orderDetail.slfStatus == '42' }">改签完成</c:if>
									<c:if test="${orderDetail.slfStatus == '50' }">未出票申请退款</c:if>
									<c:if test="${orderDetail.slfStatus == '51' }">订座成功等待价格确认</c:if>
									<c:if test="${orderDetail.slfStatus == '60' }">支付待确认</c:if>
									<c:if test="${orderDetail.slfStatus == '61' }">暂缓出票</c:if>
									<c:if test="${orderDetail.slfStatus == '62' }">订单超时</c:if>
									<c:if test="${orderDetail.slfStatus == '-1' }">其它</c:if>
								</h1> </td>
							</tr>
							<tr>
								<td rowspan="2">政策说明： ${orderDetail.statement}</td>
							</tr>
						</table>
						
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="col02">政策类型</th>
									<th class="col02">产品类型</th>
									<th class="col02">大编码</th>
									<th class="col01">业务线</th>
									<th class="col02">订单来源</th>
									<th class="col02">价格来源</th>
									<th class="col01">快递费</th>
									<th class="col02">保险费/份数</th>
									<th class="col02">指派方式</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<td>
										<script type="text/javascript">
											var policyType=covertPolicyTypeToChinese(${orderDetail.policyType});
											document.write(policyType);
										</script>
									</td>
									<td>${orderDetail.productType}</td>
									<td>${orderDetail.pnrCodeBig }</td>
									<td><!-- 国际TTS包装出票任务 -->-</td>
									<td>
										<c:if test="${orderDetail.source == 'self' }">普通政策</c:if>
										<c:if test="${orderDetail.source == 'qfare' }">加价政策</c:if>
										<c:if test="${orderDetail.source == '加价政策' }">特殊政策</c:if>
										<c:if test="${orderDetail.source == 'othe' }">其它</c:if>
									</td>
									<td>-</td>
									<td style="background-color: yellow">${orderDetail.journeySheetPrice }</td>
									<td style="background-color: yellow">${orderDetail.bxFee}/${orderDetail.bxCount}</td>
									<td>-</td>
								</tr>
							</tbody>
						</table>
						
						<!-- 报价类型 -->
						<table class="g-table table-list">
							<thead class="table-hd ">
								<tr>
									<th class="col01">行程类型</th>
									<th class="col01">航班号</th>
									<th class="col01">舱位</th>
									<th class="col05">起降机场</th>
									<th class="col05">起飞时间</th>
									<th class="col05">降落时间</th>
									<th class="col03">行李额</th>
									<th class="col01">航班信息</th>
									<th class="col03">用户支付金额</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<td rowspan="${orderDetail.airlineCount }">
										<c:if test="${orderDetail.flightType == 'S' }">单程</c:if>
										<c:if test="${orderDetail.flightType == 'D' }">往返</c:if>
										<c:if test="${orderDetail.flightType == 'T' }">其它</c:if>
										<c:if test="${orderDetail.flightType == 'L' }">联程</c:if>
										<c:if test="${orderDetail.flightType == 'X' }">多程</c:if>
									</td>
									
								<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" end="0" >
									<td>${flight.flightNum } 
									<c:if test="${flight.isShared == 'Y' }">(${flight.shareNum})</c:if>
									</td>
									<td>${flight.cabin }</td>
									<td>
										<script type="text/javascript">
											var orderId="${orderDetail.id}";
											var flightId="${flight.id}";
											var flightInfo="<a href='javascript:void(0);' onclick='getAvhInfo(\""+orderId+"\",\""+flightId+"\")'>";
											flightInfo+="${flight.depAircode}-${flight.arrAircode}</a>";
											document.write(flightInfo);
										</script>
									 </td>
									<td>${flight.departureDate}&nbsp;${flight.departureTime }
									</td>
									<td>
										${flight.arrivalDate}&nbsp;${flight.arrivalTime }
									</td>
									<td>
										<div class="g-drift">
											<a href="javascript:;" class="">详情</a>
											<div class="g-tip g-tip-w tip-b-r">
												<div class="info">
													<p>${orderDetail.luggageRule }</p>
												</div>
											</div>
										</div>
									</td>
									<td>
										<div class="g-drift">
											<a href="javascript:;" class="">详情</a>
											<div class="g-tip g-tip-w tip-b-r">
												<div class="info">
													<p>航空公司：${flight.airCodeCh }</p>
													<div>
														<span>机型：${flight.planeModule }</span> 
														<span id="flightTime">飞行时间：
															<script>
																var departureDate="${flight.departureDate}";
																var arrivalDate="${flight.arrivalDate}";
																var departureTime="${flight.departureTime}";
																var arrivalTime="${flight.arrivalTime}";
																var time = getTime(departureDate, arrivalDate, departureTime, arrivalTime);
																document.write(time);
															</script>
														</span>
													</div>
												</div>
											</div>
										</div>
									</td>
									
									<td rowspan="${orderDetail.airlineCount }">
										<div class="g-drift">
											<a href="javascript:;" class=""> <i class="rmb">${orderDetail.currency }</i>${orderDetail.allprice } </a>
											<div class="g-tip g-tip-w tip-b-r order-info">
												<table class="g-table" style="width: 200px; margin: 10px 0;min-width: 170px;">
													<thead class="table-hd">
														<tr>
															<th>类型</th>
															<th>价格</th>
															<th>份数</th>
														</tr>
													</thead>
													<tbody class="table-bd">
														<tr>
															<td>成人Tax</td>
															<td><i class="rmb">${orderDetail.currency }</i> ${orderDetail.adultTax }</td>
															<td>${orderDetail.adultsCount }</td>
														</tr>
														<tr>
															<td>快递费</td>
															<td><i class="rmb">${orderDetail.currency }</i> 0.00</td>
															<td>1</td>
														</tr>
														<tr>
															<td>成人Price</td>
															<td><i class="rmb">${orderDetail.currency }</i> ${orderDetail.adultPrice }</td>
															<td>${orderDetail.adultsCount }</td>
														</tr>
					
														<tr style="color:#f60;">
															<td>订单总价</td>
															<td><i class="rmb">${orderDetail.currency }</i> ${orderDetail.allprice }</td>
															<td>1</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
								
							<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" begin="1">
								<tr>
									<td>${flight.flightNum } 
									<c:if test="${flight.isShared == 'Y' }">(${flight.shareNum})</c:if>
									</td>
									<td>${flight.cabin}</td>
									<td>
										<script type="text/javascript">
											var orderId="${orderDetail.id}";
											var flightId="${flight.id}";
											var flightInfo="<a href='javascript:void(0);' onclick='getAvhInfo(\""+orderId+"\",\""+flightId+"\")'>";
											flightInfo+="${flight.depAircode}-${flight.arrAircode}</a>";
											document.write(flightInfo);
										</script>
									</td>
									<td>${flight.departureDate}&nbsp; ${flight.departureTime }
									</td>
									<td>
										${flight.arrivalDate}&nbsp;${flight.arrivalTime }
									</td>
									<td>
									<%-- ${orderDetail.luggageRule } --%>
										<div class="g-drift">
											<a href="javascript:;" class="">详情</a>
											<div class="g-tip g-tip-w tip-b-r">
												<div class="info">
													<p>${orderDetail.luggageRule }</p>
												</div>
											</div>
										</div>
									</td>
									<td>
										<div class="g-drift">
											<a href="javascript:;" class="">详情</a>
											<div class="g-tip g-tip-w tip-b-r">
												<div class="info">
													<p>航空公司：${flight.airCodeCh }</p>
													<div>
														<span>机型：${flight.planeModule }</span> 
														<span class="flightTime">飞行时间：
															<script>
																var departureDate="${flight.departureDate}";
																var arrivalDate="${flight.arrivalDate}";
																var departureTime="${flight.departureTime}";
																var arrivalTime="${flight.arrivalTime}";
																var time = getTime(departureDate, arrivalDate, departureTime, arrivalTime);
																document.write(time);
															</script>
														</span>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						
						<!-- 国际航班价格 -->
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="col10"><input type="checkbox"  name="passengerIDs"  value=""  onclick="if(this.checked==true) { checkAll('passengerID'); } else { clearAll('passengerID'); }" /></th>
									<th class="col10">序号</th>
									<th class="col07">乘客类型/性别</th>
									<th class="col07">乘客姓名</th>
									<th class="col07">国籍</th>
									<th class="col12">证件类型</th>
									<th class="col04">证件号(生日)</th>
									<th class="col04">证件有效期</th>
									<th class="col02">证件签发地</th>
									<th class="col12">票面价</th>
									<th class="col12">基建</th>
									<th class="col12">燃油</th>
									<th class="col12">销售价</th>
									<th class="col12">佣金</th>
									<th class="col12">PNR</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<c:forEach items="${orderDetail.passengers}" var="passenger" varStatus="i" >
									<tr>
										<td><input type="checkbox" name="passengerID" value="${passenger.id}" /> </td>
										<td>${passenger.pindex }</td>
										<td>
											<c:if test="${passenger.ageType == '-2' }">其他</c:if>
											<c:if test="${passenger.ageType == '-1' }">留学生</c:if>
											<c:if test="${passenger.ageType == '0' }">成人</c:if>
											<c:if test="${passenger.ageType == '1' }">儿童</c:if>
											/
											<c:if test="${passenger.gender == 'M' }">男</c:if>
											<c:if test="${passenger.gender == 'F' }">女</c:if>
										</td>
										<td>${passenger.name }</td>
										<td>${passenger.nationality }</td>
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
										<td>${passenger.cardNum }<br/>(${passenger.birthday })</td>
										<td>${passenger.cardExpired }</td>
										<td>${passenger.cardIssuePlace }</td>
										<td style="background: yellow">${passenger.price }</td>
										<td style="background: yellow">${ passenger.taxFee }</td>
										<td style="background: yellow">${ passenger.oilFee }</td>
										<td style="background: yellow">
											${passenger.cost }
										</td>
										<td style="background: yellow">
											${passenger.commission }
										</td>
										<td><a href="javascript:void(0);" onclick="getRtInfo('${orderDetail.id}','${passenger.id}')">${passenger.pnr}</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<c:if test="${fn:length(orderDetail.passengers)>1}">
						<div align="center" style="padding-top: 10px"><button id="button_splitOrder" class="g-btn"  onclick="javascript:splitOrder('${orderDetail.id}',${fn:length(orderDetail.passengers)})">拆分订单</button></div>
					</c:if>
					<hr>
					<hr>
				</c:forEach>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-pnrInfo">
				<span class="show">展开</span>
				<h2>编码相关信息</h2>
			</div>
			<div  style="display:none">
				<div class="gg_title">编码文本信息</div>
				<div class="gg_content">
					<textarea rows="20" cols="200">${orderDetail.pnrText}
					</textarea>
					
				</div>
			</div>
			<div class="g-content">
				<table class="g-table table-list"  >
					<thead class="table-hd">
						<tr>
							<th class="col10">实际出票舱位</th>
							<th class="col10">实际出票航班号</th>
							<th class="col10">实际出票起飞日期</th>
							<th class="col10">实际票面价</th>
							<th class="col10">实际基建</th>
							<th class="col10">实际燃油</th>
						</tr>
					</thead>
					<tbody class="table-bd">
						<c:forEach items="${orderDetail.flights}" var="flight" varStatus="i">
								<tr>
									<td>${flight.actCabin} </td>
									<td>${flight.actFltno} </td>
									<td>${flight.actDept} </td>
									<td>${flight.actPrice} </td>
									<td>${flight.actTax} </td>
									<td>${flight.actYq} </td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
    	<jsp:include page="savePurchaseInfo.jsp"></jsp:include>
    	<jsp:include page="savePolicyInfo.jsp"></jsp:include>
    	
		
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-orderRules">
				<span class="show">收起</span>
				<h2>销售规则</h2>
			</div>
			<div class="g-content">
				<div class="gg_title">航司退改签说明</div>
				<div class="gg_content">${orderDetail.returnTicketRule }</div> 
				<div class="gg_title">特殊票务说明</div>
				<div class="gg_content">${orderDetail.specialCode }</div>
				<div class="gg_title">舱位说明</div>
				<div class="gg_content">${orderDetail.specialCode }</div>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-reimburseRules">
				<span class="show">收起</span>
				<h2>报销规则</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-refundBlock">
				<span class="show">收起</span>
				<h2>退款</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-quickChange">
				<span class="show">收起</span>
				<h2>改签</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-orderPay">
				<span class="show">收起</span>
				<h2>支付信息</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-insurance">
				<span class="show">收起</span>
				<h2>保险服务</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-market">
				<span class="show">收起</span>
				<h2>营销</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-phoneRecord">
				<span class="show">收起</span>
				<h2>电话记录</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-complain">
				<span class="show">收起</span>
				<h2>投诉信息</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-logs">
				<span class="show">收起</span>
				<h2>操作日志</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
	</div>
	<div id="avhQuery" class="white_content" style="width: 530px;height: 320px"> 
		<div>
			<textarea id="avhInfo"  cols="100" rows="15" style="background-color: black;color: green;"></textarea>		
		</div>
		<br>
		<div >
			<button  id="avhtermCancel" class="g-btn" onclick="closeAvhDialog()">关闭</button>
		</div>
	</div>
	<div id="rtQuery" class="white_content" style="width: 530px;height: 320px">
		<div>
			<textarea id="rtInfo" cols="100" rows="15" style="background-color: black;color: green;"></textarea>
		</div>
		<br>
		<div>
			<button id="rtCancel" class="g-btn" onclick="closeRtDialog()">关闭</button>
		</div>
	</div>
	<div id="fade" class="black_overlay"></div>
	<div id="loading" class="ajaxLoading"><img src=${ctx }/layer/layer/skin/default/loading-1.gif></div>
				
			<!-- 	<div class="m-sildenav" > -->
<!-- 		<div class="detail-model" style="margin: 5px"> -->
<!-- 			<div class="mod-hd" style="margin-bottom: 3px"> -->
<!-- 				<h2>锁定人：${orderDetail.lockUser }</h2> -->
<!-- 			</div><div></div> -->
<!-- 			<div class="mod-hd"> -->
<!-- 				<span class="show">收起</span> -->
<!-- 				<h2>快速导航</h2> -->
<!-- 			</div> -->
<!-- 			<div class="g-content"> -->
<!-- 				<div style="margin-left: 30px"> -->
<!-- 					<ul style="list-style-type: circle;"> -->
<!-- 						<li><a href="#js-mod-orderInfo">订单信息</a></li> -->
<!-- 						<li><a href="#js-mod-ticket">出票</a></li> -->
<!-- 						<li><a href="#js-mod-orderPurchase">采购信息</a></li> -->
<!-- 						<li><a href="#js-mod-b2bPrice">b2b报价</a></li> -->
<!-- 						<li><a href="#js-mod-orderRules">销售规则</a></li> -->
<!-- 						<li><a href="#js-mod-refundBlock">退款</a></li> -->
<!-- 						<li><a href="#js-mod-quickChange">改签</a></li> -->
<!-- 						<li><a href="#js-mod-orderPay">支付信息</a></li> -->
<!-- 						<li><a href="#js-mod-insurance">保险服务</a></li> -->
<!-- 						<li><a href="#js-mod-market">营销</a></li> -->
<!-- 						<li><a href="#js-mod-phoneRecord">电话记录</a></li> -->
<!-- 						<li><a href="#js-mod-complain">投诉信息</a></li> -->
<!-- 						<li><a href="#js-mod-logs">操作日志</a></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->
</body>
</html>