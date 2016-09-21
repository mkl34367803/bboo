<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>修改订单</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript" src="${ctx }/static/js/build/order/orderDeal.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	
	// 获取出票数据
	function getOrderData(){
		var data = "{\"passengers\":[";
		var n = ${orderDetail.passengers.size()};
		var i = 0;
		$.each($("input[name='eticketNum']"), function(){
			if($(this).attr("id") != "" && $.trim($(this).val()) != "") {
				if(checkEticketNum($(this).val())){
					data += "{\"id\":\""+$(this).attr("id")+"\",\"eticketNum\":\""+$.trim($(this).val())+"\"}";
					if(i != (n-1)){
						data += ",";
					} 
					i++;
				} else {
					alert("票号数据错误");
					return false;
				}
			}
		});
		if(data != "{\"passengers\":["){
			data += "],";
			var orderId = "${orderDetail.id }";
			var pnrCode = $.trim($("input[name='pnrCode']").val());
			if(orderId != "" && checkPnr(pnrCode)){
				pnrCode.toUpperCase();
				data += "\"orderId\":\""+orderId+"\",\"pnrCode\":\""+pnrCode+"\"";
				data += "}";
				return data;
			} else {
				alert("pnr数据错误");
				return false;
			}
		} else {
			return false;
		}
	}
	
	// 检查票号
	function checkEticketNum(data) {
		var re_eticketNum = new RegExp("^[\\d]{3}-[\\d]{10}$");
		var re_eticketNum2 = new RegExp("^[\\d]{13}$");
		var result = re_eticketNum.test(data);
		var result2 = re_eticketNum2.test(data);
		if(result || result2){
			return true;
		} else {
			return false;
		}
	}
	
	// 检查pnr
	function checkPnr(data) {
		var re_pnr = new RegExp("^[a-zA-Z0-9]{6}$");
		var result = re_pnr.test(data);
		if(!result) {
			return false;
		} else {
			return true;
		}
	}
		
</script>

<script>
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
		
		// 修改票号
		$(".orderout_submit").click(function(){
			var orderData = getOrderData();
			if(orderData != false) {
				var data = "salePassengers="+orderData;
				$.ajaxSettings.traditional=true;
				$.ajax({
					url: "${ctx }/order/gj-order-deal!updateTicketNumAndPnr.do",
					data: data,
					type: "POST",
					success: function(){
						//window.location.reload();
						alert("票号修改成功。");
					}
				});
			}
			
		});
	});
</script>
</head>
<body>
<div class="m-main">
	<div class="m-content">
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-orderInfo">
				<span class="show">收起</span>
				<h2>订单信息</h2>
			</div>
			<div class="g-content">
				<jsp:include page="orderMessage.jsp"></jsp:include>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-ticket">
				<span class="show">收起</span>
				<h2>出票</h2>
			</div>
			<div class="g-content">
				<div class="getticket" style="color: red;text-align: center;"></div>
				<!-- 票号回填 -->
				<form action="">
					<table class="g-table table-list" style="width: 100%; text-align: center;">
						<thead class="table-hd ">
							<tr>
								<th class="pcol25">乘机人</th>
								<th class="pcol25">证件号</th>
								<th class="pcol25">票号</th>
								<th class="pcol25">实际出票pnr</th>
							</tr>
						</thead>
						<tbody class="table-bd eticket">
							<tr>
								<c:forEach items="${orderDetail.passengers }" var="passenger" varStatus="i" end="0">
									<td>
										${passenger.name }( 
										<c:if test="${passenger.ageType == '-2' }">其他</c:if>
										<c:if test="${passenger.ageType == '-1' }">留学生</c:if>
										<c:if test="${passenger.ageType == '0' }">成人</c:if>
										<c:if test="${passenger.ageType == '1' }">儿童</c:if> )
									</td>
									<td>${passenger.cardNum }</td>
									<td>
										<span class="g-ipt-text"><input id="${passenger.id }" name="eticketNum" type="text" value="${passenger.eticketNum }"></span>
									</td>
									<td rowspan="${orderDetail.passengers.size() }">
										<span class="g-ipt-text"><input name="pnrCode" type="text" value="${orderDetail.pnrCode}"/></span>
									</td>
								</c:forEach>
							</tr>
							<c:forEach items="${orderDetail.passengers }" var="passenger" varStatus="i" begin="1">
								<tr>
									<td>
										${passenger.name }( 
										<c:if test="${passenger.ageType == '-2' }">其他</c:if>
										<c:if test="${passenger.ageType == '-1' }">留学生</c:if>
										<c:if test="${passenger.ageType == '0' }">成人</c:if>
										<c:if test="${passenger.ageType == '1' }">儿童</c:if> )
									</td>
									<td>${passenger.cardNum }</td>
									<td>
										<span class="g-ipt-text"><input id="${passenger.id }" name="eticketNum" type="text" value="${passenger.eticketNum }"></span>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="4">
									<input type="button" class="g-btn orderout_submit" value="修改" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		
		<jsp:include page="savePurchaseInfo.jsp"></jsp:include>
		
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
				<div style="margin-left: 20px">
					<ul style="list-style-type: circle;">
						<li><a href="#js-mod-orderInfo">订单信息</a></li>
						<li><a href="#js-mod-ticket">出票</a></li>
						<li><a href="#js-mod-orderPurchase">采购信息</a></li>
						<li><a href="#js-mod-b2bPrice">b2b报价</a></li>
						<li><a href="#js-mod-orderRules">销售规则</a></li>
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