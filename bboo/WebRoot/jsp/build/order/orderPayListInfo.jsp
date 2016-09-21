<%@ page language="java" import="java.util.*,com.smart.entity.User" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld" %>
<%
	request.getSession().setAttribute("startPage", "orderPayListInfo");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单查询</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/order/orderDeal.js" type="text/javascript"></script>
<style type="text/css">
.black_overlay {
	display: none;
	position: fixed;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}

.white_content {
	display: none;
	position: fixed;
	top: 40%;
	left: 40%;
	width: 225px;
	height: 110px;
	padding: 16px;
	border: 16px solid #52a3e2;
	background-color: white;
	z-index: 1002;
	overflow: auto;
}
</style>
<script>
var ctx = "${ctx}";

	//显示非当前页（分页的页脚代码）
	pageNav.pHtml = function(pageNo, pn, showPageNo) {
		showPageNo = showPageNo || pageNo;
		var H = " <a href='javascript:queryOrderSummary({\"page\":" + pageNo
				+ "})' class='pageNum'>" + showPageNo + "</a> ";
		return H;

	};
//这里开始分页查询订单数据
queryOrderSummary=function(data){
		//从浏览器地址获取flightClass参数
		var flightClass=getParamValue("flightClass");
		data.flightClass=flightClass;
<%--		data.startDate = $("input[name='startDate']").val(), --%>
<%--		data.endDate = $("input[name='endDate']").val(), --%>
		data.purchaseStartDate = $("input[name='purchaseStartDate']").val(), 
		data.purchaseEndDate = $("input[name='purchaseEndDate']").val(), 
		data.purchaseNo = $("input[name='purchaseNo']").val(), 
		data.flightType = $("input[name='flightType']:checked").val(), 
		data.orderNo = $("input[name='orderNo']").val(), 
		data.pnrCode = $("input[name='pnrCode']").val(), 
		data.shopNameCh = $("input[name='shopNameCh']").val();
		data.allShopNameCh = $("input[name='allShopNameCh']").val();
		var chk_value =[]; 
		$('input[name="allShopNameCh"]:checked').each(function(){ 
			chk_value.push($(this).val()); 
		}); 
		if(chk_value.length>0){
			data.allShopNameCh="全部店铺";
		}
		var temp_shopNameCh='<%=request.getSession().getAttribute("shopNameCh")%>';
		if(data.shopNameCh==''&&temp_shopNameCh!='null'){
			data.shopNameCh=temp_shopNameCh;
		}else{
			data.shopNameCh=data.shopNameCh;
		}
		if(data.allShopNameCh=="全部店铺"){
			data.shopNameCh="";
		}
		data.orderStatus='1';  //1表示支付成功，等待出票的订单
		$.ajax({
			url:"${ctx }/order/order-pay!queryOrderSummaryByPage.do?time="+new Date(),
			dataType:"json",
			data:data,
			type:"POST",
			success:function(result){
				if(result.error){
					alert(result.error);
				}else{
					$("#table-bd").html("");
					if(result.total){  //返回总页数
						pageNav.go(data.page,Math.ceil(result.total/20));
					}
					if(result.list){ //如果list这个属性存在，说明有数据返回
						tableDataList=result.list;
						$.each(result.list,function(i,item){
							var img_tag="";
							if(item.distributor=="Q"){  //去哪儿的图标
								img_tag="<img src='${ctx }/static/img/build/qunaer.png'/>";
							}else if(item.distributor=="C"){  //携程的图标
								img_tag="<img src='${ctx }/static/img/build/trip.png'/>";
							}else if(item.distributor=="T"){  //淘宝的图标
								img_tag="<img src='${ctx }/static/img/build/taob.png'/>";
							}
							var td_orderNo="";
							var temp_orderNo="";
							if(item.oldOrderNo!="0"&&item.oldOrderNo!=""){
								temp_orderNo=item.orderNo+"&nbsp;"+"<span style='color:red'>改</span>";
							}else {
								temp_orderNo=item.orderNo;
							}
							if(item.distributor=="C"){
								td_orderNo="<td>"+img_tag+item.shopNameCh+"<br/>"+item.billId+"<br/>"+temp_orderNo+"</td>"; //订单编号(携程的订单号加上主订单号)
							}else{
								td_orderNo="<td>"+img_tag+item.shopNameCh+"<br/>"+temp_orderNo+"</td>"; //订单编号
							}
							var td_purchasePlaceCh=item.purchasePlaceCh;
							var td_purchaseNo="<td>"+td_purchasePlaceCh+"<br/>"+item.purchaseNo+"</td>";
							var createTimes=item.createTime.split(" ");
							var td_createTime="<td>"+createTimes[0]+"<br/>"+createTimes[1]+"</td>"; //订单时间
							var td_addtime;
							if(item.addtime){
								var addtimes=item.addtime.split(" ");
								td_addtime="<td>"+addtimes[0]+"<br/>"+addtimes[1]+"</td>"; //订单时间
							}else{
								td_addtime="<td></td>"; //订单时间
							}
							if(item.flightType=="S"){
								item.flightType="单程";
							}else if(item.flightType=="D"){
								item.flightType="往返";
							}
							var isLowspace="";
							if(item.isLowspace==1){
								isLowspace="有低舱";
							}
							var seats=item.seats;
							var td_flightType="";
							if(seats>=0&&seats<=5){
								td_flightType="<td>"+item.flightType+"(<span style='color:red'>急</span>)"+"<br/>"+isLowspace+"</td>";  //航程类型：单程、往返
							}else{
								td_flightType="<td>"+item.flightType+"<br/>"+isLowspace+"</td>";  //航程类型：单程、往返
							}
							var isNewCode=item.isNewCode;
							var td_pnrCode="";
							if(isNewCode==1){
								td_pnrCode="<td>"+item.pnrCode+"<sup><img src='${ctx}/images/build/new.gif'></sup>"+"</td>"; //pnr
							}else{
								td_pnrCode="<td>"+item.pnrCode+"</td>"; //pnr
							}
							
							var td_passengerCount="<td>"+item.passengerCount+"</td>"
							
							var td_slfStatus="<td>"+getSlfStatusCH(item.slfStatus)+"</td>"
							var td_printPrice="<td>"+item.printPrice+"</td>"
							var td_cost="<td>"+item.cost+"</td>"
							var td_payPirce="<td>"+item.payPirce+"</td>"
							var td_allprice="<td>"+item.allprice+"</td>"
							var td_lockUser="<td>"+item.lockUser+"</td>"; //锁定人
							var var_tr=td_orderNo+td_purchaseNo+td_createTime+td_addtime+td_flightType+td_pnrCode+td_passengerCount+td_slfStatus+td_printPrice+td_cost+td_payPirce+td_allprice+td_lockUser;
							var var_a="<a href='${ctx }/order/gj-order-detail!showOrderDetail.do?id="+item.id+"&operate=orderPayList&flightClass="+flightClass+"'>查看</a>";
							if(item.slfStatus!=99){
								var currentLockUser='<%=request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME")%>';
									var_a=var_a+"<br/>"+"<a href='javascript:cancelOrder(\""+item.id+"\")'>取消</a>";
								if(item.lockUser==null||item.lockUser==""){//如果锁定人等于自己，这里一个处理，处理和强制处理调用同一个页面
									var_a=var_a+"<br/>"+"<a href='${ctx }/order/order-pay!orderPayDeal.do?id="+item.id+"'>支付</a>";
								}else if(item.lockUser==currentLockUser){
									var_a=var_a+"<br/>"+"<a href='${ctx }/order/order-pay!orderPayDeal.do?id="+item.id+"'>支付</a>";
									var_a=var_a+"<br/>"+"<a href='javascript:unlockOrder(\"确定要解除锁定\",\""+item.id+"\")'>解除锁定</a>";
								}else {
									//这里一个强制解锁的标签
									var_a=var_a+"<br/>"+"<a href='javascript:unlockOrder(\"确定要强制解锁\",\""+item.id+"\")'>强制解锁</a>";
								}
							}
							var_tr="<tr>"+var_tr+"<td class='operate' >"+var_a+"</td></tr>";
							$("#table-bd").append(var_tr);
						});
						$(".table-bd tr:even").addClass("even");
						$(".table-bd tr:odd").addClass("odd");
					}
				}			
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
};

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
					var page = $("#page").text();
					var data = {
						"page" : page
					};
					queryOrderSummary(data);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	}
}
	//解除锁定和强制解锁
	function cancelOrder(orderID) {
		if (confirm("取消订单号，该订单将无法再出票，请考虑清楚，是否要取消订单？")) {
			$.ajax({
				type : "post",
				dataType : "json",
				data : {
					id : orderID
				},
				url : "${ctx }/order/gj-order-detail!cancelOrderByID.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					} else if (result.success) {
						var page = $("#page").text();
						var data = {
							"page" : page
						};
						queryOrderSummary(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		}
	}


	//解除锁定和强制解锁
	function unlockOrder(message, orderID) {
		if (confirm(message)) {
			$.ajax({
				type : "post",
				dataType : "json",
				data : {
					id : orderID,
					lockUser : ''
				},
				url : "${ctx }/order/gj-order-detail!updateLockUser.do",
				success : function(result) {
					if (result.error) {
						alert(result.error + "失败");
					} else if (result.success) {
						var page = $("#page").text();
						var data = {
							"page" : page
						};
						queryOrderSummary(data);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
		}
	}
	/**
	*传一个订单的id，pnrNoTime,和留票备注过来。
	*/
	function dealPnrNoTime(id,pnrNoTime,leaveRemark){
	$("#light").css('display','block');  
	$("#fade").css('display','block');  
	$("#pnrNoTime").val(pnrNoTime);
	$("#leaveRemark").val(leaveRemark);
	$("#lightID").val(id);//这个是订单的ID
// 		$("light").show();
// 		$("fade").show();
	}
	function light_ok(){
		$.ajax({
				type : "post",
				dataType : "json",
				data : {
					id : $("#lightID").val(),
					pnrNoTime : $("#pnrNoTime").val(),
					leaveRemark : $("#leaveRemark").val()
				},
				url : "${ctx }/order/gj-order-detail!updatePnrNoTime.do",
				success : function(result) {
					if (result.error) {
						alert(result.error + "失败");
					} else if (result.success) {
						var page = $("#page").text();
						var data = {
							"page" : page
						};
						queryOrderSummary(data);
						$("#light").css('display','none');  
						$("#fade").css('display','none');  
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
	}
	function light_cancel(){
		$("#light").css('display','none');  
		$("#fade").css('display','none');  
	}
	function queryButton() {
		var page = $("#page").text();
		var data = {
			"page" : page
		};
		queryOrderSummary(data);
	}

	/**
	*查看指定的时间字符串是否在未来24小时内
	*/
	function compareDate(dateString) {
	//yyyy-MM-dd HH:mm:ss
	if (dateString != undefined) {
		var nowDate = new Date();
		dateString=dateString.substring(0,19);
		var paramDate = new Date(dateString.replace(/-/,"/"));
		if((nowDate.valueOf() + 1 * 3 * 60 * 60 * 1000)<=paramDate.valueOf()&&paramDate.valueOf()<(nowDate.valueOf() + 1 * 24 * 60 * 60 * 1000)){
			return 'yellow';
		}else if(paramDate.valueOf()<(nowDate.valueOf() + 1 * 3 * 60 * 60 * 1000)){
			return 'red';
		}else {
			return 'default';
		}
	}
}
	
	
$(document).ready(function(){
	function formatMD(d){
		if(d < 10){
			return "0"+d;
		}else{
			return d;
		}
	}
	// 格式化日期, d为默认日期, next为相差天数
	function formatDate(d, next) {
		if (d) {
			if (next) {
				d = new Date(d.getTime() + next * 24 * 60 * 60 * 1000);
			}
			return d.getFullYear() + "-" + formatMD(d.getMonth() + 1) + "-"+ formatMD(d.getDate());
		}
	}

	// 初始化日期
	window.SERVICE_TIME = new Date();
	var defaultTime = window.SERVICE_TIME;
	curDate = formatDate(defaultTime, 0);
	nextDate = formatDate(defaultTime, -30);

<%--	$("input[name='startDate']").val(nextDate);--%>
<%--	$("input[name='endDate']").val(curDate);--%>
	$("input[name='purchaseStartDate']").val(nextDate);
	$("input[name='purchaseEndDate']").val(curDate);
	
	// 出发时间，到达时间控件
	var startDate = {
		elem: '#purchaseStartDate',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			endDate.min = datas;
			endDate.start = datas;
		}
	};
	var endDate = {
		elem: '#purchaseEndDate',
		format: 'YYYY-MM-DD',
		min: laydate.now(-30),
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			startDate.max = datas;
		}
	};
	laydate(startDate);
	laydate(endDate);
});

$(document).ready(function() {
	queryOrderSummary({
		"page" : "1"
	});
	$("#etermCancel").click(function(){
		closeEtermDialog();
	});
	$("#J_Btn").click(function() {
		var page = "1"; //查询还是要从第一页开始
		var data = {
			"page" : page
		};
		queryOrderSummary(data);
	});
	$("#lightOk").click(function(){
		light_ok();
	});
	$("#lightCancel").click(function(){
		light_cancel();
	});
});
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div class="mod-hd" >
		<script type="text/javascript">
			if(getParamValue("flightClass")=="I"){
				document.write("<h2>国际待支付订单</h2>");			
			}else{
				document.write("<h2>国内待支付订单</h2>");
			}
		</script>
	</div>
	<form id="J_Form" action="">
		<table class="m-search g-clear g-table" style="width: 100%">
			<tr class="rows">
<%--				<td class="col">--%>
<%--					<span class="label">销售日期</span>--%>
<%--					<div class="label-info">--%>
<%--						<div class="qcbox start-date">--%>
<%--							<input id="startDate" name="startDate" type="text" class="cinput">--%>
<%--						</div>--%>
<%--						<span class="split">到</span>--%>
<%--						<div class="qcbox stop-date">--%>
<%--							<input id="endDate" name="endDate" type="text" class="cinput">--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
				<td class="col">
					<span class="label">采购日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input id="purchaseStartDate" name="purchaseStartDate" type="text" class="cinput">
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input id="purchaseEndDate" name="purchaseEndDate" type="text" class="cinput">
						</div>
					</div>
				</td>
			
				<td class="col">
					<span class="label">航程类型</span>
					<div class="label-info">
						<label> <input name="flightType" value="0" type="radio" checked="checked" class="g-input-check">全部</label> 
						<label> <input name="flightType" value="1" type="radio" class="g-input-check">单程</label> 
						<label> <input name="flightType" value="2" type="radio" class="g-input-check">往返</label>
						<label> <input name="flightType" value="3" type="radio" class="g-input-check">多程</label>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">订单号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="orderNo" type="text"> </span>
					</div>
				</td>
				<td class="col">
					<span class="label">采购订单号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="purchaseNo" type="text" value=""> </span>
					</div>
				</td>
				<td class="col" colspan="2">
					<span class="label">pnr编码</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="pnrCode" type="text"> </span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="col" >
					<span class="label">店铺名称</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="shopNameCh" type="text"> </span>
					</div>
				</td>
				<td class="col" >
					<span class="label">全部店铺</span>
					<div class="label-info" >
						<label><input name="allShopNameCh" type="checkbox"  value="全部店铺" class="g-checkbox"> </label> 
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" id="J_Btn" class="g-btn" value="查询" />
					</div>
				</td>
			</tr>
		</table>
	</form>
	
	<div class="g-content">
		<table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th width="135px">订单号</th>
					<th width="135px">采购地<br/>采购订单号</th>
					<th class="col08">销售日期</th>
					<th class="col08">采购日期</th>
					<th class="col05">航程类型</th>
					<th class="col05">PNR</th>
					<th class="col05">乘客人数</th>
					<th class="col04">支付状态</th>
					<th class="col02">票面价</th>
					<th class="col02">支付机票款</th>
					<th class="col02">结算价</th>
					<th class="col02">结算价总价</th>
					<th class="col05">锁定人</th>
					<th class="col05">操作</th>
				</tr>
			</thead> 
			<tbody class="table-bd" id="table-bd">
			</tbody>
		</table>
	</div>
	<div id="etermQuery" class="white_content" style="width: 530px;height: 320px"> 
		<div>
			<textarea id="etermInfo"  cols="100" rows="15" style="background-color: black;color: green;"></textarea>		
		</div>
		<br>
		<div >
<!-- 			<button id="lightOk" class="g-btn" >复制</button>&nbsp;&nbsp; -->
			<button  id="etermCancel" class="g-btn" >关闭</button>
		</div>
	</div>
	<div id="light" class="white_content"> 
		<div>
		    <input id="lightID" name="lightID" type="hidden" >
			<span class="label">pnrNoTime</span><input id="pnrNoTime" name="pnrNoTime" type="text" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
		</div>
		<br>
		<div >
			<span>留&nbsp;票&nbsp;备&nbsp;注&nbsp;</span><input id="leaveRemark" name="leaveRemark" type="text">
		</div>
		<br>
		<div >
			<button id="lightOk" class="g-btn" >确定</button>&nbsp;&nbsp;<button  id="lightCancel" class="g-btn" >取消</button>
		</div>
	</div>
	<div id="fade" class="black_overlay">
	</div>
	<div id="pageNav" >
	</div>
</body>
</html>