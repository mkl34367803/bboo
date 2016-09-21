<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>出票中订单处理</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript">

var ctx = "${ctx}";

	// 锁单
	function getLock(state){
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
	}
	
	// 获取出票数据
	function getOrderData(){
		var data = "{\"passengers\":[";
		var n = ${orderDetail.passengers.size()};
		var i = 0;
		var eticketData = "";
		var flag = true;
		$.each($("input[name='eticketNum']"), function(){
			if($(this).attr("id") != "") {
				var ticket = $.trim($(this).val());
				if(ticket != ""){
					if(checkEticketNum(ticket)){
						eticketData += "{\"id\":\""+$(this).attr("id")+"\",\"eticketNum\":\""+ticket+"\"}";
						if(i != (n-1)){
							eticketData += ",";
						} 
						i++;
					} else {
						alert("票号格式错误");
						flag = false;
						return false;
					}
				}
			}
		});
		if(flag){
			data += eticketData + "],";
		} else {
			return false;
		}
		
		var orderId = "${orderDetail.id }";
		var pnrCode = $.trim($("input[name='pnrCode']").val());
		if(orderId != "" && checkPnr(pnrCode)){
			pnrCode.toUpperCase();
			data += "\"orderId\":\""+orderId+"\",\"pnrCode\":\""+pnrCode+"\"";
			data += ",\"isUpdateInteface\":\"true\"";
			data += "}";
			return data;
		} else {
			alert("pnr格式错误");
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
		
		// 锁单
		$("#lock").click(function(){
			if($(this).find("img").attr("src") == "${ctx }/images/build/lock.png"){
				$(this).find("img").attr("src", "${ctx }/images/build/unlock.png");
				getLock(false);
			} else {
				$(this).find("img").attr("src", "${ctx }/images/build/lock.png");
				getLock(true);
			}
		});
		
		// 回填票号--提交
		$(".orderout_submit").click(function(){
			var orderData = getOrderData();
			if(orderData != false) {
				var data = "salePassengers="+orderData;
				$.ajaxSettings.traditional=true;
				$.ajax({
					url: "${ctx }/order/gj-order-deal!updateTicketNumAndPnr.do",
					data: data,
					dataType: "json",
					type: "POST",
					success: function(result){
						if (result.error) {
							alert(result.error);
							window.location.reload();
						} else if(result.success){
							window.location.reload();
							//location.href="${ctx }/jsp/build/order/orderOutListInfo.jsp";
						} else {
							window.location.reload();
							alert(result);
						} 
					}
				});
			}
			
		});
		
		// 回填票号--取消
		$(".orderout_cancel").click(function(){
			window.location.reload();
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
			<div class="mod-hd" id="js-mod-ticket">
				<span class="show">收起</span>
				<h2>出票</h2>
			</div>
			<div class="g-content">
				<div class="getticket" style="color: red;text-align: center;"></div>
				<!-- 票号回填 -->
				<form action="">
					<table class="g-table table-list">
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
									<input type="button" class="g-btn orderout_submit" style="margin-right: 10px;" value="保存" />
									<input type="button" class="g-btn orderout_cancel" value="取消" />
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		
		<div class="detail-model">
			<div class="mod-hd" id="js-mod-orderPurchase">
				<span class="show">收起</span>
				<h2>采购信息</h2>
			</div>
			<div class="g-content">
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
							<td>${buyOrderEntity.lr}</td>
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