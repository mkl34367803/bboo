<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>订单信息</title>
<script type="text/javascript" src="${ctx }/static/js/build/order/orderDeal.js"></script>
<script type="text/javascript">
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
		var h = parseInt((aTime - dTime) / 60) + "小时" ;
		var m =  parseInt((aTime - dTime) % 60) + "分钟";
		return h + m;
	}
	
	$(document).ready(function() {
		$('.rmb').text(function(){
			var currency = "${orderDetail.currency }";
			if(currency == "RMB"){
				return "¥";
			} else {
				return currency;
			}
		});
	});
</script>

</head>
<body>
	<c:if test="${ERROR ne null and ERROR ne '' }">
		<div style="background-color: #fff; padding: 5px; color: red"><h3>${ERROR }</h3></div>
	</c:if>
	<table class="g-table order-table">
		<tr>
			<c:choose>
			<c:when test="${orderDetail.distributor == 'C'}">
			<td class="pcol35">主订单号：${orderDetail.billId} <br/>子订单号： ${orderDetail.orderNo} </td>
			</c:when>
			<c:otherwise>
			<td class="pcol35">订单号： ${orderDetail.orderNo} </td>
			</c:otherwise>
			</c:choose>
			<td class="pcol40">订单来源： ${orderDetail.distributorCh }---${orderDetail.shopNameCh }</td>
			<td class="detail pcol30" rowspan="2" style="width:400px;height: 100%">
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
			<td>编码： ${orderDetail.pnrCode }</td>
			<td class="textred">最晚出票时限：<!-- 2016-05-23 15:40:08 -->-</td>
		</tr>
		<tr>
			<td>解冻状态： <%-- ${orderDetail.fzstatus } --%></td>
			<td>奖 励： <i class="rmb"></i>&nbsp;&nbsp;${buyOrderEntity.reward}&nbsp;&nbsp;元 </td>
			<td class="detail" rowspan="2" style="width:400px;height: 100%">
				<h1>
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
				</h1> 
			</td>
		</tr>
		<tr>
			<td rowspan="2">政策说明： ${orderDetail.statement}</td>
		</tr>
	</table><br><br>
	
	<table class="g-table table-list">
		<thead class="table-hd ">
			<tr>
				<th class="pcol10">政策类型</th>
				<th class="pcol10">产品类型</th>
				<th class="pcol10">大编码</th>
				<th class="pcol10">业务线</th>
				<th class="pcol10">订单来源</th>
				<th class="pcol10">价格来源</th>
				<th class="pcol10">快递费</th>
				<th class="pcol10">保险费/份数</th>
				<th class="pcol10">指派方式</th>
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
				<th class="pcol09">行程类型</th>
				<th class="pcol06">航段</th>
				<th class="pcol09">航班号</th>
				<th class="pcol09">舱位</th>
				<th class="pcol06">起降机场</th>
				<th class="pcol09">起飞时间</th>
				<th class="pcol09">降落时间</th>
				<th class="pcol09">行李额</th>
				<th class="pcol09">航班信息</th>
				<th class="pcol09">用户支付金额</th>
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
				
			<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i" end="0">
				<td>${flight.sequence } </td>
				<td>${flight.flightNum } </td>
				<td>${flight.cabin }</td>
				<td>${flight.depAircodeCh }-${flight.arrAircodeCh }(${flight.depAircode }-${flight.arrAircode }) </td>
				<td>${flight.departureDate}<br/> ${flight.departureTime }
<%--					<c:choose>--%>
<%--						<c:when test="${flight.departureDate eq flight.arrivalDate }"></c:when>--%>
<%--						<c:otherwise>&nbsp;跨天</c:otherwise>--%>
<%--					</c:choose>--%>
				</td>
				<td>
					${flight.arrivalDate}<br/>${flight.arrivalTime }
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
											var arrivalDate="${flight.arrivalDate }";
											var departureTime="${flight.departureTime }";
											var arrivalTime="${flight.arrivalTime }";
											var time = getTime(departureDate, arrivalDate, departureTime, arrivalTime);
											document.write(time);
										</script>
									</span>
								</div>
							</div>
						</div>
					</div>
				</td>
			</c:forEach>
				
				<td rowspan="${orderDetail.airlineCount }">
					<div class="g-drift">
						<a href="javascript:;" class=""> <i class="rmb"></i> ${orderDetail.allprice } </a>
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
										<td><i class="rmb"></i> ${orderDetail.adultTax }</td>
										<td>${orderDetail.adultsCount }</td>
									</tr>
									<tr>
										<td>快递费</td>
										<td><i class="rmb"></i> 0.00</td>
										<td>1</td>
									</tr>
									<tr>
										<td>成人Price</td>
										<td><i class="rmb"></i> ${orderDetail.adultPrice }</td>
										<td>${orderDetail.adultsCount }</td>
									</tr>

									<tr style="color:#f60;">
										<td>订单总价</td>
										<td><i class="rmb"></i> ${orderDetail.allprice }</td>
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
				<td>${flight.sequence } </td>
				<td>${flight.flightNum } </td>
				<td>${flight.cabin }</td>
				<td>${flight.depAircodeCh }-${flight.arrAircodeCh }(${flight.depAircode }-${flight.arrAircode }) </td>
				<td>${flight.departureDate}<br/> ${flight.departureTime }
<%--					<c:choose>--%>
<%--						<c:when test="${flight.departureDate eq flight.arrivalDate }"></c:when>--%>
<%--						<c:otherwise>&nbsp;跨天</c:otherwise>--%>
<%--					</c:choose>--%>
				</td>
				<td>
					${flight.arrivalDate}<br/>${flight.arrivalTime }
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
											var departureDate="${flight.departureDate}";
											var arrivalDate="${flight.arrivalDate }";
											var departureTime="${flight.departureTime }";
											var arrivalTime="${flight.arrivalTime }";
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
	<table class="g-table table-list" style="width: 100%">
		<thead class="table-hd ">
			<tr>
				<th class="pcol05">序号</th>
				<th class="pcol06">乘客类型</th>
				<th class="pcol08">乘客姓名</th>
				<th class="pcol05">性别</th>
				<th class="pcol04">国籍</th>
				<th class="pcol07">证件类型</th>
				<th class="pcol14">证件号(生日)</th>
				<th class="pcol09">证件有效期</th>
				<th class="pcol08">证件签发地</th>
				<th class="pcol12">票号</th>
				<th class="pcol07">票面价/<br>销售价</th>
				<th class="pcol05">税费</th>
				<th class="pcol10">出票渠道／PNR</th>
			</tr>
		</thead>
		<tbody class="table-bd">
			<c:forEach items="${orderDetail.passengers }" var="passenger" varStatus="i">
				<tr>
					<td>${passenger.pindex }</td>
					<td>
						<c:if test="${passenger.ageType == '-2' }">其他</c:if>
						<c:if test="${passenger.ageType == '-1' }">留学生</c:if>
						<c:if test="${passenger.ageType == '0' }">成人</c:if>
						<c:if test="${passenger.ageType == '1' }">儿童</c:if>
					</td>
					<td>${passenger.name }</td>
					<td>
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
					<td>${passenger.cardExpired }</td>
					<td>${passenger.cardIssuePlace }</td>
					<td>
						${passenger.eticketNum }<br> 
						<span style="color: green">${passenger.ticketStatus }</span>
					</td>
					<td>${passenger.price }/<br>${passenger.cost }</td>
					<td>${passenger.oilFee + passenger.taxFee }</td>
					<td>-<br>${passenger.pnr}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
