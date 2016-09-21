<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>订单详情</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<style type="text/css">
.refund-hd {
    background: #56a4e0;
    height: 20px;
    padding: 4px 6px 4px 10px;
    line-height: 20px;
    color: #fff;
    vertical-align: middle;
    cursor: pointer;
}
.refund-label {
    width: 65px;
    padding-right: 10px;
    text-align: right;
    line-height: 28px;
}
.refund-label-info {
    text-align: left;
}
</style>
<script type="text/javascript">
	// 锁单
	<%-- function getLock(state){
		var id = "${orderDetail.id }";
		if(state == true){
			var currentLockUser='<%=request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME")%>';
			$.get("${ctx }/order/gj-order-detail!updateLockUser.do", 
				{"id":id,"lockUser":currentLockUser},
				function(){
					$(".lockUser").text(currentLockUser)
				}
			);
		} else {
			$.get("${ctx }/order/gj-order-detail!updateLockUser.do?id="+id+"&lockUser="+"", function(){$(".lockUser").text("")});
		}
	} --%>

	$(function(){
		$("div.mod-hd").click(function(){
			$(this).next().toggle();
			if($(this).find("span.show").text() == "收起"){
				$(this).find("span.show").text("展开");
			} else {
				$(this).find("span.show").text("收起");
			} 
		});
		
		// 锁单图标
		var s = $("span.lockUser").text();
		if(s == " "){
			$("#lock img").attr("src", "${ctx }/images/build/unlock.png");
		} else {
			$("#lock img").attr("src", "${ctx }/images/build/lock.png");
		}
		
		// 锁单
		/* $("#lock").click(function(){
			if($(this).find("img").attr("src") == "${ctx }/images/build/lock.png"){
				$(this).find("img").attr("src", "${ctx }/images/build/unlock.png");
				getLock(false);
			} else {
				$(this).find("img").attr("src", "${ctx }/images/build/lock.png");
				getLock(true);
			}
		}); */
		
	});
</script>
</head>
<body>
<div class="m-main">
	<div class="m-content">
	
		<div class="detail-model">
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
				<jsp:include page="orderMessage.jsp"></jsp:include>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-orderPurchase">
				<span class="show">收起</span>
				<h2>采购信息</h2>
			</div>
			<div class="g-content">
				<c:if test="${BUYERROR ne null and BUYERROR ne '' }">
					<div style="background-color: #fff; padding: 5px; color: red"><h3>${ERROR }</h3></div>
				</c:if>
				<table class="g-table table-list">
					<thead class="table-hd">
						<tr>
							<th>采购地</th>
							<th>支付方式</th>
							<th>票面价</th>
							<th>返点</th>
							<th>奖励金额</th>
							<th>基建</th>
							<th>燃油</th>
							<th>采购订单号</th>
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
							<td>${buyOrderEntity.purchasePlaceCh}</td>
							<td>
								<c:if test="${buyOrderEntity.payWay == '10' }">快钱非担保</c:if>
								<c:if test="${buyOrderEntity.payWay == '2' }">支付宝非担保</c:if>
								<c:if test="${buyOrderEntity.payWay == '1' }">财付通非担保</c:if>
								<c:if test="${buyOrderEntity.payWay == '11' }">快钱担保</c:if>
								<c:if test="${buyOrderEntity.payWay == '12' }"> 支付宝担保</c:if>
								<c:if test="${buyOrderEntity.payWay == '13' }"> 财付通担保</c:if>
								<c:if test="${buyOrderEntity.payWay == '-1' }">其它</c:if>
							</td>
							<td>${buyOrderEntity.printPrice}</td>
							<td>${buyOrderEntity.backPoint}</td>
							<td>${buyOrderEntity.reward}</td>
							<td>${buyOrderEntity.taxFee}</td>
							<td>${buyOrderEntity.oilFee}</td>
							<td>${buyOrderEntity.purchaseNo}</td>
						</tr>
					</tbody>
					
					<thead class="table-hd">
						<tr>
							<th>采购pnr</th>
							<th>支付账号</th>
							<th>交易流水号</th>
							<th>后返</th>
							<th>支付机票款</th>
							<th>结算价</th>
							<th>结算总价</th>
							<th>利润</th>
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
							<td>${buyOrderEntity.pnrCode}</td>
							<td>${buyOrderEntity.payAccount}</td>
							<td>${buyOrderEntity.transactionNo}</td>
							<td>${buyOrderEntity.afterPoint}</td>
							<td>${buyOrderEntity.cost}</td>
							<td>${buyOrderEntity.payPirce}</td>
							<td>${buyOrderEntity.allprice}</td>
							<td>
								<fmt:formatNumber type="number" value="${buyOrderEntity.lr}" pattern="#.##" maxFractionDigits="2" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-b2bPrice">
				<span class="show">收起</span>
				<h2>b2b报价</h2>
			</div>
			<div class="g-content">
			</div>
		</div>
		
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
			<div class="mod-hd" id="js-mod-refundDetail">
				<span class="show">收起</span>
				<h2>退票信息</h2>
			</div>
			<div class="g-content">
				<c:forEach items="${baseRefundList }" var="baseRefund" varStatus="i">
				<c:if test="${baseRefund.state == 1 }">
					<table>
						<tr>
							<td style="width: 70px">乘客姓名：</td>
							<td>
								<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i">
									<c:forEach items="${orderDetail.passengers }" var="passenger" varStatus="i">
										<c:if test="${passenger.id == refund.psgid }">${passenger.name }&nbsp;&nbsp;</c:if>
									</c:forEach>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>航班号：</td>
							<td>
								<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i">
									<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i">
										<c:if test="${flight.id == refund.fltid }">${flight.flightNum }&nbsp;&nbsp;</c:if>
									</c:forEach>
								</c:forEach>
							</td>
						</tr>
					</table>
					
					<div class="refoundDiv" style="margin-bottom: 10px;">
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="pcol10"></th>
									<th class="pcol10">舱位票面价</th>
									<th class="pcol10">退票的票面</th>
									<th class="pcol10">燃油</th>
									<th class="pcol10">机建</th>
									<th class="pcol10">退票费率</th>
									<th class="pcol10">退票费</th>
									<th class="pcol10">退款金额</th>
									<th class="pcol10">实际退款金额(含税)</th>
									<th class="pcol10">利润</th>
								</tr>
							</thead>
							<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i" end="0">
								<tbody class="table-bd">
									<tr>
										<td>理论支出</td>
										<td>${refund.face }</td>
										<td>${refund.baseFace }</td>
										<td>${refund.yq }</td>
										<td>${refund.tax }</td>
										<td>${refund.rate }</td>
										<td>${refund.fee }</td>
										<td>${refund.refund }</td>
										<td>${refund.actRefund }</td>
										<td rowspan="2">
											<fmt:formatNumber type="number" value="${refund.actRefund2 - refund.actRefund }" pattern="#.##" maxFractionDigits="2" />
										</td>
									</tr>
								</tbody>
								<tbody class="table-bd">
									<tr>
										<td>实际支出</td>
										<td>${refund.face2 }</td>
										<td>${refund.baseFace2 }</td>
										<td>${refund.yq2 }</td>
										<td>${refund.tax2 }</td>
										<td>${refund.rate2 }</td>
										<td>${refund.fee2 }</td>
										<td>${refund.refund2 }</td>
										<td>${refund.actRefund2 }</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</c:if>
				</c:forEach>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-reservationDetail">
				<span class="show">收起</span>
				<h2>留票信息</h2>
			</div>
			<div class="g-content">
				<c:forEach items="${baseRefundList }" var="baseRefund" varStatus="i">
				<c:if test="${baseRefund.state == 2 }">
					<table>
						<tr>
							<td style="width: 70px">乘客姓名：</td>
							<td>
								<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i">
									<c:forEach items="${orderDetail.passengers }" var="passenger" varStatus="i">
										<c:if test="${passenger.id == refund.psgid }">${passenger.name }&nbsp;&nbsp;</c:if>
									</c:forEach>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td>航班号：</td>
							<td>
								<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i">
									<c:forEach items="${orderDetail.flights }" var="flight" varStatus="i">
										<c:if test="${flight.id == refund.fltid }">${flight.flightNum }&nbsp;&nbsp;</c:if>
									</c:forEach>
								</c:forEach>
							</td>
						</tr>
					</table>
					
					<div class="refoundDiv" style="margin-bottom: 10px;">
						<table class="g-table table-list" >
							<thead class="table-hd ">
								<tr>
									<th class="pcol10"></th>
									<th class="pcol10">舱位票面价</th>
									<th class="pcol10">退票的票面</th>
									<th class="pcol10">燃油</th>
									<th class="pcol10">机建</th>
									<th class="pcol10">退票费率</th>
									<th class="pcol10">退票费</th>
									<th class="pcol10">退款金额</th>
									<th class="pcol10">实际退款金额(含税)</th>
									<th class="pcol10">利润</th>
								</tr>
							</thead>
							<c:forEach items="${baseRefund.refunds }" var="refund" varStatus="i" end="0">
								<tbody class="table-bd">
									<tr>
										<td>支出</td>
										<td>${refund.face }</td>
										<td>${refund.baseFace }</td>
										<td>${refund.yq }</td>
										<td>${refund.tax }</td>
										<td>${refund.rate }</td>
										<td>${refund.fee }</td>
										<td>${refund.refund }</td>
										<td>${refund.actRefund }</td>
										<td rowspan="2">
											<fmt:formatNumber type="number" value="${refund.actRefund2 - refund.actRefund }" pattern="#.##" maxFractionDigits="2" />
										</td>
									</tr>
								</tbody>
								<tbody class="table-bd">
									<tr>
										<td>收入</td>
										<td>${refund.face2 }</td>
										<td>${refund.baseFace2 }</td>
										<td>${refund.yq2 }</td>
										<td>${refund.tax2 }</td>
										<td>${refund.rate2 }</td>
										<td>${refund.fee2 }</td>
										<td>${refund.refund2 }</td>
										<td>${refund.actRefund2 }</td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
				</c:if>
				</c:forEach>
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
			<div class="g-content record-bd">
				<ul>
					<c:forEach items="${sysLogEntities }" var="sysLog" varStatus="i">
						<li><i></i>${sysLog.operatime } ${sysLog.userName } <c:if test="${sysLog.logType == 'ORDER_LOG' }">[出票]</c:if> ${sysLog.contents }</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="m-sildenav" >
		<div class="detail-model" style="margin: 5px">
			<div class="mod-hd" style="margin-bottom: 3px;">
				<div id="lock" style="float: left;"><img alt="" src=""></div>
				<div style="margin-left: 24px">锁定人：<span class="lockUser">${orderDetail.lockUser }</span></div>
			</div>
			<div></div>
			<div class="mod-hd">
				<span class="show">收起</span>
				<h2>快速导航</h2>
			</div>
			<div class="g-content">
				<div style="margin-left: 20px;">
					<ul style="list-style-type: circle;">
						<li><a href="#js-mod-orderInfo">订单信息</a></li>
						<li><a href="#js-mod-orderPurchase">采购信息</a></li>
						<li><a href="#js-mod-b2bPrice">b2b报价</a></li>
						<li><a href="#js-mod-orderRules">销售规则</a></li>
						<li><a href="#js-mod-refundDetail">退票信息</a></li>
						<li><a href="#js-mod-reservationDetail">留票信息</a></li>
						<li><a href="#js-mod-refundBlock">退款</a></li>
						<li><a href="#js-mod-quickChange">改签</a></li>
						<li><a href="#js-mod-orderPay">支付信息</a></li>
						<li><a href="#js-mod-insurance">保险服务</a></li>
						<li><a href="#js-mod-market">营销</a></li>
						<li><a href="#js-mod-phoneRecord">电话记录</a></li>
						<li><a href="#js-mod-complain">投诉信息</a></li>
						<li><a href="#js-mod-logs">操作日志</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>