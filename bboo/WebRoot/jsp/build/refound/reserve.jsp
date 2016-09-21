<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>留票处理页面</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript" src="${ctx }/static/js/build/refund/refund.js"></script>
<style type="text/css">
body {
	min-width: 800px;
	padding-right: 20px;
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
.refoundDiv input[type="text"] {
	width: 100px;
}
.redFont {
	color: red;
}
.refund-hd {
    background: #56a4e0;
    height: 20px;
    padding: 4px 6px 4px 10px;
    line-height: 20px;
    color: #fff;
    vertical-align: middle;
    cursor: pointer;
}
.refundRuleDiv {
	display: none;
}
</style>
<script type="text/javascript">
	var ctx = "${ctx }";
	var fkid = "${orderDetail.id}";
	var buyorderId = "${buyOrderEntity.id}";
	var passengerCount = "${orderDetail.passengerCount }";
	var airlineCount = "${orderDetail.airlineCount }";
	var error = "${error }";
	if (error) {
		alert(error);
	}
	var fligntNumArr = [];
	var sharNumArr = [];
	var depAircodeArr = [];
	var arrAircodeArr = [];
	var departureDateArr = [];
	var departureTimeArr = [];
	var passengerIdArr = [];
	
	var operator = "reserve";
	
	var carrierArr = [];
	var cabinArr = [];
</script>
</head>
<body>
<form action="" method="post" name="savePurchForm" id="savePurchForm"></form>
	<div class="m-content" style="width: 100%">
		<div class="detail-model" style="margin-top: 10px">
			<div class="mod-hd" id="js-mod-orderInfo">
				<span class="show">收起</span>
				<script type="text/javascript">
				var flightClass="${orderDetail.flightClass}";
				if(flightClass=="I"){
					document.write("<h2>国际留票信息</h2>");			
				}else{
					document.write("<h2>国内留票信息</h2>");
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
								<td width="40%" colspan="2" class="detail" style="height: 100%;width: 100%">
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
									<c:if test="${orderDetail.status == '99' }">订座成功等待支付（系统）</c:if>
									<c:if test="${orderDetail.status == '70' }">退票留票中</c:if>
									<c:if test="${orderDetail.status == '-1' }">其它</c:if>
									</h1> 
								</td>
							</tr>
							<tr>
								<td>PNR：${orderDetail.pnrCode}</td>
								<td class="textred">最晚出票时限：<!-- 2016-05-23 15:40:08 -->-</td>
							</tr>
							<tr>
								<td>解冻状态： <%-- ${orderDetail.fzstatus } --%></td>
								<td>奖 励： ¥<!-- 0.00 --> - 元 </td>
								<td colspan="2" class="detail" style="height: 100%;width: 100%"><h1>
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
									<c:if test="${orderDetail.slfStatus == '99' }">订座成功等待支付（系统）</c:if>
									<c:if test="${orderDetail.slfStatus == '70' }">退票留票中</c:if>
									<c:if test="${orderDetail.slfStatus == '-1' }">其它</c:if>
								</h1> </td>
							</tr>
							<tr>
								<td rowspan="2">锁定人： ${orderDetail.lockUser}</td>
							</tr>
						</table>
						
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="col01">业务线</th>
									<th class="col02">订单来源</th>
									<th class="col02">价格来源</th>
									<th class="col01">生单PNR</th>
									<th class="col02">政策代码</th>
									<th class="col02">大编码</th>
									<th class="col02">政策类型</th>
									<th class="col01">报价类型</th>
									<th class="col02">产品类型</th>
									<th class="col02">指派方式</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<td><!-- 国际TTS包装出票任务 -->-</td>
									<td>
										<c:if test="${orderDetail.source == 'self' }">普通政策</c:if>
										<c:if test="${orderDetail.source == 'qfare' }">加价政策</c:if>
										<c:if test="${orderDetail.source == '加价政策' }">特殊政策</c:if>
										<c:if test="${orderDetail.source == 'othe' }">其它</c:if>
									</td>
									<td>-</td>
									<td>${orderDetail.pnrCode }</td>
									<td>${orderDetail.policyCode }</td>
									<td>${orderDetail.pnrCodeBig }</td>
									<td>
										<c:if test="${orderDetail.policyType == '1' }">NFD</c:if>
										<c:if test="${orderDetail.policyType == '2' }"> 清仓产品</c:if>
										<c:if test="${orderDetail.policyType == '3' }">商旅产品</c:if>
										<c:if test="${orderDetail.policyType == '5' }">中转产品</c:if>
										<c:if test="${orderDetail.policyType == '6' }">包机</c:if>
										<c:if test="${orderDetail.policyType == '7' }">切位</c:if>
										<c:if test="${orderDetail.policyType == '8' }">航司VIP卡</c:if>
										<c:if test="${orderDetail.policyType == '10000' }">普通供应商</c:if>
										<c:if test="${orderDetail.policyType == '86' }">OpenAPI对外接口规范</c:if>
										<c:if test="${orderDetail.policyType == '10001' }">规则运价</c:if>
										<c:if test="${orderDetail.policyType == '-1' }">其它</c:if>
									</td>
									<td>-</td>
									<td>-</td>
									<td>-</td>
								</tr>
							</tbody>
						</table>
						
						<!-- 报价类型 -->
						<table class="g-table table-list">
							<thead class="table-hd ">
								<tr>
									<th class="pcol05"></th>
									<th class="pcol05">行程类型</th>
									<th class="pcol08">航班号</th>
									<th class="pcol05">舱位</th>
									<th class="pcol08">实际航班号</th>
									<th class="pcol05">实际舱位</th>
									<th class="pcol10">起降机场</th>
									<th class="pcol14">起飞时间</th>
									<th class="pcol14">降落时间</th>
									<th class="pcol08">行李额</th>
									<th class="pcol08">航班信息</th>
									<th class="pcol10">用户支付金额</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" end="0" >
								<tr>
									<td><input type="checkbox" name="flightID" value="${flight.id}" checked="checked" disabled="disabled" /> </td>
									<td rowspan="${orderDetail.airlineCount }">
										<c:if test="${orderDetail.flightType == 'S' }">单程</c:if>
										<c:if test="${orderDetail.flightType == 'D' }">往返</c:if>
										<c:if test="${orderDetail.flightType == 'T' }">其它</c:if>
										<c:if test="${orderDetail.flightType == 'L' }">联程</c:if>
										<c:if test="${orderDetail.flightType == 'X' }">多程</c:if>
									</td>
									<td>${flight.flightNum } 
									<c:if test="${flight.isShared == 'Y' }">(${flight.shareNum})</c:if>
<%--									<c:if test="${flight.isShared == 'N' }">不共享</c:if>--%>
<%--									<c:if test="${flight.isShared == 'T' }">其他</c:if>--%>
									</td>
									<td>${flight.cabin }</td>
									<td>${flight.actFltno } 
									<td>${flight.actCabin }</td>
									<td>${flight.depAircodeCh }-${flight.arrAircodeCh }(${flight.depAircode }-${flight.arrAircode }) </td>
									<td>${flight.departureDate}&nbsp;${flight.departureTime }</td>
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
							</c:forEach>
							
								
							<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" begin="1">
								<tr>
									<td><input type="checkbox" name="flightID" value="${flight.id}" checked="checked" /> </td>
									<td>${flight.flightNum } </td>
									<td>${flight.cabin }</td>
									<td>${flight.actFltno } 
									<td>${flight.actCabin }</td>
									<td>${flight.depAircodeCh }-${flight.arrAircodeCh }(${flight.depAircode }-${flight.arrAircode }) </td>
									<td>${flight.departureDate}&nbsp;${flight.departureTime }</td>
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
														<span class="flightTime">飞行时间：
															<script>
																departureDate="${flight.departureDate}";
																arrivalDate="${flight.arrivalDate}";
																departureTime="${flight.departureTime}";
																arrivalTime="${flight.arrivalTime}";
																time = getTime(departureDate, arrivalDate, departureTime, arrivalTime);
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
									<th class="pcol04"></th>
									<th class="pcol04">序号</th>
									<th class="pcol08">乘客姓名</th>
									<th class="pcol10">乘客类型/性别</th>
									<th class="pcol08">国籍</th>
									<th class="pcol08">证件类型</th>
									<th class="pcol14">证件号(生日)</th>
									<th class="pcol08">票面价</th>
									<th class="pcol06">基建</th>
									<th class="pcol06">燃油</th>
									<th class="pcol08">销售价</th>
									<th class="pcol06">佣金</th>
									<th class="pcol10">PNR</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<c:forEach items="${orderDetail.passengers}" var="passenger" varStatus="i" >
									<tr>
										<td><input type="checkbox" name="passengerID" value="${passenger.id}" checked="checked" disabled="disabled" /> </td>
										<td>${passenger.pindex }</td>
										<td>${passenger.name }</td>
										<td>
											<c:if test="${passenger.ageType == '-2' }">其他</c:if>
											<c:if test="${passenger.ageType == '-1' }">留学生</c:if>
											<c:if test="${passenger.ageType == '0' }">成人</c:if>
											<c:if test="${passenger.ageType == '1' }">儿童</c:if>
											/
											<c:if test="${passenger.gender == 'M' }">男</c:if>
											<c:if test="${passenger.gender == 'F' }">女</c:if>
										</td>
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
										<td>
											<div class="g-drift">
												<a href="javascript:;" class="">${passenger.cardNum }</a>
												<div class="g-tip g-tip-w tip-b-r" style="width: 100px;">
													<div class="info">
														<p>${passenger.birthday }</p>
													</div>
												</div>
											</div>
										</td>
										<td class="redFont">${passenger.price }</td>
										<td class="redFont">${passenger.taxFee }</td>
										<td  class="redFont">${passenger.oilFee }</td>
										<td class="redFont">
											${passenger.cost }
										</td>
										<td class="redFont">
											${passenger.commission }
										</td>
										<td>${passenger.pnr}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
					</div>
				<div></div>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd">
				<span class="show">收起</span>
				<h2>采购信息</h2>
			</div>
			<div class="g-content">
				<!-- 采购信息 -->
				<table class="g-table table-list" style="width: 800px;">
					<thead class="table-hd ">
						<tr>
							<th class="pcol12">采购地</th>
							<th class="pcol18">订单号</th>
							<th class="pcol13">PNR</th>
							<th class="pcol13">大编</th>
							<th class="pcol15">支付方式</th>
							<th class="pcol12">采购人</th>
							<th class="pcol17">订单创建时间</th>
						</tr> 
					</thead>
					<tbody class="table-bd">
						<tr>
							<td>${buyOrderEntity.purchasePlaceCh }</td>
							<td>${buyOrderEntity.purchaseNo }</td>
							<td><a href="javascript:void(0)" class="rtMsg">${buyOrderEntity.pnrCode }</a></td>
							<td>${buyOrderEntity.pnrCodeBig }</td>
							<td>
								<c:if test="${buyOrderEntity.payWay == 1}">财付通非担保</c:if>
								<c:if test="${buyOrderEntity.payWay == 2}">支付宝非担保</c:if>
								<c:if test="${buyOrderEntity.payWay == 10}">快钱非担保</c:if>
								<c:if test="${buyOrderEntity.payWay == 11}">快钱担保</c:if>
								<c:if test="${buyOrderEntity.payWay == 12}">支付宝担保</c:if>
								<c:if test="${buyOrderEntity.payWay == 13}">财付通担保</c:if>
								<c:if test="${buyOrderEntity.payWay == -1}">其它</c:if>
							</td>
							<td>${buyOrderEntity.lockUser }</td>
							<td>${buyOrderEntity.createTime }</td>
						</tr>
					</tbody>
					<!-- 采购乘客信息 -->
					<thead class="table-hd ">
						<tr>
							<th>姓名</th>
							<th>票面价</th>
							<th>基建</th>
							<th>燃油</th>
							<th>结算价</th>
							<th colspan="2">票号</th>
						</tr>
					</thead>
					<tbody class="table-bd">
						<c:forEach items="${buyOrderEntity.passengers}" var="passenger" varStatus="i" >
						<tr>
							<td>${passenger.name }</td>
							<td class="redFont">${passenger.printPrice }</td>
							<td class="redFont">${passenger.taxFee }</td>
							<td class="redFont">${passenger.oilFee }</td>
							<td class="redFont">${passenger.cost }</td>
							<td colspan="2">${passenger.eticketNum }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd">
				<span class="show">收起</span>
				<h2>退票信息</h2>
			</div>
			<div class="g-content">
				<div class="psgRuleDiv">
					乘客退票规则：${buyOrderEntity.returnTicketRule }<br><br>
					乘客改期规则：${buyOrderEntity.changeDateRule }
				</div>
				<div class="refundRuleDiv">
				</div>
				
				
				<div class="refoundDiv psgRefund" style="margin-bottom: 10px;">
					<div class="refund-hd" style="background-color: #87CEFA">
						<h2>乘客留票信息</h2>
					</div>
					<div class="g-content" style="margin-bottom: 10px">
						<table class="g-table table-list" >
							<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i" end="0">
							<thead class="table-hd ">
								<tr>
									<th class="pcol17">舱位票面价</th>
									<th class="pcol17">退票的票面</th>
									<th class="pcol16">燃油</th>
									<th class="pcol16">机建</th>
									<th class="pcol17">退票费率</th>
									<th class="pcol17">退票费</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<c:forEach items="${orderDetail.passengers}" var="passenger" varStatus="i" end="0" >
									<c:forEach items="${orderDetail.flights}" var="flight" varStatus="i" end="0" >
										<td><input name="face" value="${refund.face2 }" type="text" /></td>
										<td><input name="baseFace" value="${refund.baseFace2 }" type="text" /></td>
										<td><input name="yq" value="${refund.yq2 }" type="text" /></td>
										<td><input name="tax" value="${refund.tax2 }" type="text" /></td>
										<td><input name="rate" value="${refund.rate2 }" type="text" /></td>
										<td><input name="fee" value="${refund.fee2 }" type="text" /></td>
									<script type="text/javascript">
										var cost = ${passenger.cost};
										var brid = "${baseRefund.id }";
									</script>
									</c:forEach>
									</c:forEach>
								</tr>
							</tbody>
							<thead class="table-hd ">
								<tr>
									<th>退款金额</th>
									<th>实际退款金额(含税)</th>
									<th>是否自愿</th>
									<th>退票</th>
									<!-- <th>提交</th> -->
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<td><input name="refund" value="${refund.refund2 }" type="text" disabled="disabled" /></td>
									<td><input name="actRefund" value="${refund.actRefund2 }" type="text" disabled="disabled" /></td>
									<td>
										<label> <input name="rtype" value="0" type="radio">自愿</label> 
										<label style="margin-left: 10px;"> <input name="rtype" value="1" type="radio">非自愿</label> 
									</td>
									<td>
										<label> <input name="isvoid" value="0" type="radio">作废</label> 
										<label style="margin-left: 10px;"> <input name="isvoid" value="1" type="radio">退票</label> 
									</td>
									<!-- <td>
										<label> <input name="issubmit" value="1" type="radio" checked="checked">提交</label> 
										<label style="margin-left: 10px;"> <input name="issubmit" value="2" type="radio">未提交</label> 
									</td> -->
								</tr>
								<tr>
									<td colspan="6">
										<input name="nextBtn" type="button" class="g-btn" value="下一步" />
									</td>
								</tr>
							</tbody>
							<script type="text/javascript">
								$("input:radio[name='rtype'][value='"+${refund.rtype2 }+"']").attr("checked", true);
								$("input:radio[name='isvoid'][value='"+${refund.isvoid2 }+"']").attr("checked", true);
							</script>
							</c:forEach>
						</table>
					</div>
				</div>
				
				<div class="refoundDiv supRefund">
					<div class="refund-hd" style="background-color: #87CEFA">
						<h2>供应商退票信息</h2>
					</div>
					<div class="g-content" style="margin-bottom: 10px">
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="pcol17">舱位票面价</th>
									<th class="pcol17">退票的票面</th>
									<th class="pcol16">燃油</th>
									<th class="pcol16">机建</th>
									<th class="pcol17">退票费率</th>
									<th class="pcol17">退票费</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<c:forEach items="${buyOrderEntity.passengers}" var="passenger" varStatus="i" end="0" >
									<c:forEach items="${buyOrderEntity.flights}" var="flight" varStatus="i" end="0" >
										<td><input name="face2" value="${flight.printPrice }" type="text" /></td>
										<td><input name="baseFace2" value="${flight.actPrice }" type="text" /></td>
										<td><input name="yq2" value="${passenger.oilFee }" type="text" /></td>
										<td><input name="tax2" value="${passenger.taxFee }" type="text" /></td>
										<td><input name="rate2" value="" type="text" /></td>
										<td><input name="fee2" value="" type="text" /></td>
										<script type="text/javascript">
											var cost2 = ${passenger.cost};
											var baseFace2 = "${flight.actPrice }";
											if (baseFace2 == "") {
												$("input[name='baseFace2']").val("${flight.printPrice }");
											}
										</script>
									</c:forEach>
									</c:forEach>
								</tr>
							</tbody>
							<thead class="table-hd ">
								<tr>
									<th>退款金额</th>
									<th>实际退款金额(含税)</th>
									<th>是否自愿</th>
									<th>退票</th>
									<!-- <th>提交</th> -->
									<th>是否留票</th>
									<th>利润</th>
								</tr>
							</thead>
							<tbody class="table-bd">
								<tr>
									<td><input name="refund2" value="" type="text" disabled="disabled" /></td>
									<td><input name="actRefund2" value="" type="text" disabled="disabled" /></td>
									<td>
										<label> <input name="rtype2" value="0" type="radio" checked="checked">自愿</label> 
										<label style="margin-left: 10px;"> <input name="rtype2" value="1" type="radio">非自愿</label> 
									</td>
									<td>
										<label> <input name="isvoid2" value="0" type="radio">作废</label> 
										<label style="margin-left: 10px;"> <input name="isvoid2" value="1" type="radio" checked="checked">退票</label> 
									</td>
									<!-- <td>
										<label> <input name="issubmit2" value="1" type="radio" checked="checked">提交</label> 
										<label style="margin-left: 10px;"> <input name="issubmit2" value="2" type="radio">未提交</label> 
									</td> -->
									<td>
										<label> <input name="state" value="1" type="radio">退票</label> 
										<label style="margin-left: 10px;"> <input name="state" value="2" type="radio" checked="checked">留票</label> 
									</td>
									<td><input name="profit" value="" type="text" disabled="disabled" /></td>
								</tr>
								<tr>
									<td colspan="6">
										<input name="backBtn" type="button" class="g-btn" value="上一步" />
										<input name="refundBtn" type="button" class="g-btn" value="确定" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-logs">
				<span class="show">收起</span>
				<h2>舱位退改签信息</h2>
			</div>
			<div class="g-content">
				<div class="cabinRuldDiv">
				
				</div>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-logs">
				<span class="show">收起</span>
				<h2>航班时刻信息</h2>
			</div>
			<div class="g-content">
				<table class="flightDynamics g-table table-list" style="width: 650px;min-width: 650px;">
					<thead class="table-hd">
						<tr>
							<th class="pcol16">航班号</th>
							<th class="pcol08">出发地</th>
							<th class="pcol08">目的地</th>
							<th class="pcol14">起飞日期</th>
							<th class="pcol14">起飞时间</th>
							<th class="pcol14">到达时间</th>
							<th class="pcol14">航程时间</th>
							<th class="pcol12">状态</th>
						</tr>
					</thead>
					<tbody class="flightDynamics-td table-bd">
					</tbody>
				</table>
				
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-logs">
				<span class="show">收起</span>
				<h2>操作日志</h2>
			</div>
			<div class="g-content">
				<%-- <ul>
					<c:forEach items="${sysLogEntities }" var="sysLog" varStatus="i">
						<li><i></i>${sysLog.operatime } ${sysLog.userName } <c:if test="${sysLog.logType == 'ORDER_LOG' }">[出票]</c:if> ${sysLog.contents }</li>
					</c:forEach>
				</ul> --%>
			</div>
		</div>
	</div>
	
	
	<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" >
	<script>
		fligntNumArr.push("${flight.flightNum}");
		sharNumArr.push("${flight.shareNum}");
		depAircodeArr.push("${flight.depAircode}");
		arrAircodeArr.push("${flight.arrAircode}");
		departureDateArr.push("${flight.departureDate}");
	</script>
	</c:forEach>
	
	<c:forEach items="${buyOrderEntity.flights}" var="flight" varStatus="i">
	<script>
		carrierArr.push("${flight.carrier}");
		cabinArr.push("${flight.cabin}");
	</script>
	</c:forEach>
	
	<c:forEach items="${buyOrderEntity.passengers}" var="passenger" varStatus="i" >
	<script>
		passengerIdArr.push("${passenger.id}");
	</script>
	</c:forEach>

</body>
</html>