<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单详情查询</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/order/orderDeal.js" type="text/javascript"></script>
<script src="${ctx }/static/js/build/order/orderQuery.js" type="text/javascript"></script>
<script>
var ctx = "${ctx}";

//显示非当前页（分页，页脚所需要的代码）
pageNav.pHtml = function(pageNo, pn, showPageNo) {
  showPageNo = showPageNo || pageNo;
  var H = " <a href='javascript:queryOrderSummary({\"page\":"+pageNo+"})' class='pageNum'>" + showPageNo + "</a> ";
  return H;
 
};
//这里开始分页查询订单数据
queryOrderSummary=function(data){
		//从浏览器地址获取flightClass参数
		var flightClass=getParamValue("flightClass");
		data.flightClass=flightClass;
		var startDate = $("input[name='startDate']").val(), 
				endDate = $("input[name='endDate']").val(), 
				contactMob = $("input[name='contactMob']").val(), 
				flightType = $("input[name='flightType']:checked").val(), 
				orderNo = $("input[name='orderNo']").val(), 
				passengerName = $("input[name='passengerName']").val(), 
				ticketNum = $("input[name='ticketNum']").val(),
				status = $("select[name='status']").val(),
				shopNameCh = $("input[name='shopNameCh']").val(),
				allShopNameCh = $("input[name='allShopNameCh']").val();
		var chk_value =[]; 
		$("input[name='allShopNameCh']:checked").each(function(){ 
			chk_value.push($(this).val()); 
		}); 
		if(chk_value.length>0){
			data.allShopNameCh="全部店铺";
		}
		
		data.startDate=startDate;
		data.endDate=endDate;
		data.contactMob=contactMob;
		data.flightType=flightType;
		data.orderNo=orderNo;
		data.passengerName=passengerName;
		data.ticketNum=ticketNum;
		data.orderStatus=status;
		var temp_shopNameCh='<%=request.getSession().getAttribute("shopNameCh")%>';
		if(shopNameCh==''&&temp_shopNameCh!='null'){
			data.shopNameCh=temp_shopNameCh;
		}else{
			data.shopNameCh=shopNameCh;
		}
		if(data.allShopNameCh=="全部店铺"){
			data.shopNameCh="";
		}
		$.ajax({
			url:"${ctx }/order/gj-order-summary!queryOrderByPage.do?time="+new Date(),
			dataType:"json",
			type: "post",
			data:data,
			success:function(result){
				if(result.error){
					alert(result.error);
				}else{
					$("#table-bd").html("");
					if(result.total){  //返回总页数
						pageNav.go(data.page,Math.ceil(result.total/20));
					} else if (result.total == 0){
						pageNav.go(data.page, 1);
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
							}else if(item.distributor=="N"){  //途牛的图标
								img_tag="<img src='${ctx }/static/img/build/tuniu.jpg'/>";
							}else if(item.distributor=="O"){  //同程的图标
								img_tag="<img src='${ctx }/static/img/build/tongc.png'/>";
							}
							var td_orderNo="";
							var temp_orderNo="";
							if(item.oldOrderNo!="0"&&item.oldOrderNo!=""){
								temp_orderNo=item.orderNo+"&nbsp;"+"<span style='color:red'>改</span>";
							}else {
								temp_orderNo=item.orderNo;
							}
							if(item.distributor=="C"){
								td_orderNo="<td>"+img_tag+item.shopNameCh+"<br/>"+item.billId+"<br/>"+"<a href='${ctx }/order/gj-order-detail!showOrderDetail.do?id="+item.id+"&operate=orderQuery&flightClass="+flightClass+"'>"+temp_orderNo+"</a></td>"; //订单编号(携程的订单号加上主订单号)
							}else{
								td_orderNo="<td>"+img_tag+item.shopNameCh+"<br/>"+"<a href='${ctx }/order/gj-order-detail!showOrderDetail.do?id="+item.id+"&operate=orderQuery&flightClass="+flightClass+"'>"+temp_orderNo+"</a></td>"; //订单编号
							}
							//var td_orderNo="<td>"+img_tag+"<a href='${ctx }/order/gj-order-detail!showOrderDetail.do?id="+item.id+"'>"+item.orderNo+"</a></td>"; //订单编号
							var createTimes=item.createTime.split(" ");
							var td_createTime="<td>"+createTimes[0]+"<br/>"+createTimes[1]+"</td>"; //订单时间
							if(item.flightType=="S"){
								item.flightType="单程";
							}else if(item.flightType=="D"){
								item.flightType="往返";
							}else if(item.flightType=="T"){
								item.flightType="其它";
							}else if(item.flightType=="L"){
								item.flightType="联程";
							}else if(item.flightType=="X"){
								item.flightType="多程";
							}
							var td_flightType="<td>"+item.flightType+"</td>";  //航程类型：单程、往返
							var td_dep_arr="";
							if(item.flights){
								$.each(item.flights,function(j,flight){
										var each_departureTime=flight.departureTime;
										var each_depAircode=flight.depAircode;
										var each_arrivalTime=flight.arrivalTime;
										var each_arrAircode=flight.arrAircode;
										var each_dep_arr=each_departureTime+" "+each_depAircode+"<br/>"+each_arrivalTime+" "+each_arrAircode;
										if(j<item.flights.length-1){
											each_dep_arr=each_dep_arr+"<hr/>";
										}
										td_dep_arr=td_dep_arr+each_dep_arr;
								});
							}
							td_dep_arr="<td>"+td_dep_arr+"</td>";
// 							var td_departureTime="<td>"+item.departureTime+"</td>"; //起飞时间00:00 格式
// 							var td_depAircode="<td>"+item.depAircode+"</td>"; //起飞城市三字码
// 							var td_arrivalTime="<td>"+item.arrivalTime+"</td>"; //到达时间00:00格式
// 							var td_arrAircode="<td>"+item.arrAircode+"</td>"; //到达城市三字码
							var td_flightDate_flightNo="";  //用来存储航班日期，航班号
							if(item.flights){
								$.each(item.flights,function(j,flight){
									var flightDate_flightNo=flight.departureDate+"<hr/>"; //航班日期
									flightDate_flightNo=flightDate_flightNo+flight.flightNum+"("+flight.cabin+")"; //航班号
									if(j<item.flights.length-1){
											flightDate_flightNo=flightDate_flightNo+"<hr/>";
									}
									td_flightDate_flightNo=td_flightDate_flightNo+flightDate_flightNo;
								});
							}
							td_flightDate_flightNo="<td>"+td_flightDate_flightNo+"</td>";
							var td_pnrCode="<td>"+item.pnrCode+"</td>"; //pnr
							
							var td_allprice="<td>"+item.allprice+"/"; //订单总价
							var td_passengerCount=""+item.passengerCount+"</td>"; //乘客总人数
							var td_slfStatus="<td>"+getSlfStatusCH(item.slfStatus)+"</td>"; //订单状态//这个字段暂时不确定
							var td_remark="<td>"+item.remark+"</td>"; //服务说明
							var td_lockUser="<td>"+item.lockUser+"</td>"; //锁定人
							var var_tr=td_orderNo+td_createTime+td_flightType+td_dep_arr+td_flightDate_flightNo+td_pnrCode+td_allprice+td_passengerCount+td_slfStatus+td_remark+td_lockUser;
							if(item.slfStatus == 2){
								var var_a = "<a href='${ctx }/order/gj-order-detail!queryOrderOutByID.do?id="+item.id+"&operate=orderModify'>修改"+"</a>";
							} else {
								var var_a = "<a href='javascript:void(0);' style='color: gray;'>修改"+"</a>";
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
			success : function (result) {
				if (result.error) {
					alert(message+"失败");
				}else if (result.success) {
					var page=$("#page").text();
					var data={
						"page":page
					};
					queryOrderSummary(data);
				}
			},
			error : function (XMLHttpRequest, textStatus, errorThrown) {
				alert("请求出错了");
			}
		});
	}
}

function queryButton(){
	var page=$("#page").text();
	var data={
		"page":page
	};
	queryOrderSummary(data);
}



$(document).ready(function(){
	queryOrderSummary({"page":"1"});
	$("#J_Btn").click(function(){
				var page="1";  //查询还是要从第一页开始
				var data={
				"page":page
				};
				queryOrderSummary(data);
	});
	
	var error = "${error}";
	if(error) {
		alert(error);
	}
});
</script>


</head>
<body style="margin: 10px; min-width: 920px;">
	<div class="mod-hd" style="margin-top: 10px">
		<script type="text/javascript">
			if(getParamValue("flightClass")=="I"){
				document.write("<h2>国际订单详情</h2>");			
			}else{
				document.write("<h2>国内订单详情</h2>");
			}
		</script>
	</div>
	<form id="J_Form" action="">
		<table class="m-search g-clear g-table" style="width: 100%">
			<tr class="rows">
				<td class="col">
					<span class="label">起止日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input id="startDate" name="startDate" type="text" class="cinput">
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input id="endDate" name="endDate" type="text" class="cinput">
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">联系人手机</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="contactMob" type="text" value=""> </span>
					</div>
				</td>
				<td class="col" style="width: 500px;">
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
					<span class="label">乘机人姓名</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="passengerName" type="text" value=""> </span>
					</div>
				</td>
				<td class="col">
					<span class="label">票号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="ticketNum" type="text"> </span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">订单状态</span>
					<div class="label-info">
						<span class="g-select"> 
							<select name="status">
									<option value="">全部</option>
									<option value="0">订座成功等待支付</option>
									<option value="1">支付成功等待出票</option>
									<option value="2">出票完成</option>
									<option value="5">出票中</option>
									<option value="12">订单取消</option>
									<option value="20">等待座位确认</option>
									<option value="30">退票申请中</option>
									<option value="31">退票完成等待退款</option>
									<option value="39">退款完成</option>
									<option value="40">改签申请中</option>
									<option value="42">改签完成</option>
									<option value="50">未出票申请退款</option>
									<option value="51">订座成功等待价格确认</option>
									<option value="60">支付待确认</option>
									<option value="61">暂缓出票</option>
									<option value="62">订单超时</option>
									<option value="-1">其它</option>
							</select> 
						</span>
					</div>
				</td>
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
			<!-- <tr class="rows">
				<td class="col col2" colspan="3">
					<span class="label">服务单类型</span>
					<div class="label-info">
						<label> <input name="serviceOrderType" value="0" type="radio" checked="checked" class="g-input-check">全部 </label> 
						<label> <input name="serviceOrderType" value="1" type="radio" class="g-input-check">退款 </label> 
						<label> <input name="serviceOrderType" value="2" type="radio" class="g-input-check">改签 </label> 
						<label> <input name="serviceOrderType" value="3" type="radio" class="g-input-check">报销凭证 </label>
					</div>
				</td>
			</tr> -->
			<tr class="rows">
				<td class="col">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" id="J_Btn" class="g-btn" value="查询" />
						<input type="button" id="J_Exports" class="g-btn" value="导出">
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
					<th class="col08">订单日期</th>
					<th class="col05">类型</th>
					<th class="col02">起飞-到达</th>
					<th class="col04">航班日期<br>航班号(舱位)</th>
					<th class="col05">PNR</th>
					<th class="col02">成交价/人数</th>
					<th class="col02">订单状态</th>
					<th class="col02">服务说明</th>
					<th class="col04">锁定人</th>
					<th class="col05">操作</th>
				</tr>
			</thead> 
			<tbody class="table-bd" id="table-bd">
			</tbody>
		</table>
	</div>
	<div id="pageNav">
	</div>
	
	
</body>
</html>