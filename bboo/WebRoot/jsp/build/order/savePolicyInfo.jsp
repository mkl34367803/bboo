<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/jsp/share/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>获取b2b运价信息</title>
<%--<%@include file="/jsp/share/JS.jsp"%>--%>
<%--<%@include file="/jsp/share/CSS.jsp"%>--%>
<script type="text/javascript"
	src="${ctx }/static/js/build/order/orderDeal.js"></script>
	
<style type="text/css">
.table-list .table-hd th {
	border-bottom: 2px #fff solid
}

<%--创单的时候的，单击创单按钮弹出的遮罩的样式--%>
.createOrder_blackLayer {
	display: none;
	position: fixed;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1003;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}
</style>

<script type="text/javascript">
var ctx = "${ctx}";
var isLocalBsp=false;
$(document).ready(function () {
	$("#ajaxReloading").css("display","block");
	$.ajax({
		url : "${ctx }/order/product-data!queryProductDataByOrderId.do?time="
		 + new Date(),
		dataType : "json",
		data :{"orderId":"${orderDetail.id}"},
		type : "POST",
		success : function (result) {
			if(result.error){
				alert(result.error);
			}else if(result.success){
				if(result.gettime){
					$("#gettime").html(result.gettime);
				}
				if(result.list){
					var cost;  //销售价
					var commission; //佣金
					<c:forEach items="${orderDetail.passengers}" var="passenger" varStatus="i" >
						cost=${passenger.cost};
						commission=${passenger.commission};
					</c:forEach>
					showProductData(result.list,cost,commission);
				}
			}
			$("#ajaxReloading").css("display","none");
		},
	});
	$("#reloadPrice").click(function(){
		$("#ajaxReloading").css("display","block");
		$("#loading").css("display","none");
		$.ajax({
			url : "${ctx }/order/gj-order-deal!refreshProductDataByThirdInterface.do",
			dataType : "json",
			data :{"id":"${orderDetail.id}","pnrCode":"${orderDetail.pnrCode}"},
			type : "POST",
			success : function (result) {
				if(result.error){
					alert(result.error);
				}else if(result.success){
					if(result.gettime){
						$("#gettime").html(result.gettime);
					}
					if(result.list){
						//首先置空table_body
						$("#table-bd").html("");
						var cost;  //销售价
						var commission; //佣金
						<c:forEach items="${orderDetail.passengers}" var="passenger" varStatus="i" >
							cost=${passenger.cost};
							commission=${passenger.commission};
						</c:forEach>
						showProductData(result.list,cost,commission);
					}
				}
				$("#ajaxReloading").css("display","none");
			},
		});
	});
});

/**
 * 展示政策信息
 * @param list 从后台获取到的我方作为采购的政策信息
 * @param cost 我方作为供应方时销售价
 * @param commission 我方作为供应商时，需要向携程去哪给的佣金
 */
function showProductData(list,cost,commission){
	$.each(list,function(i,item){
		var var_tr;
		var td_radio="";
		if(item.product.productSource=="LOCALBSP"){
			td_radio="<td rowspan='2'><input type='radio' name='policy' value='"+item.id+"' style='cursor:pointer' onclick='createOrderLocalBSP()'/></td>";
		}else{
			td_radio="<td rowspan='2'><input type='radio' name='policy' value='"+item.id+"' style='cursor:pointer' onclick='createOrder()'/></td>";
		}
		var td_productSource="<td>"+item.product.productSource+"</td>";//政策来源
		var td_priceType="<td>"+item.priceType+"</td>";//运价类型
		var td_offNo="<td>"+item.product.offNo+"</td>";//政策出票offNo
//		var td_ticketPar="<td>"+item.ticketPar+"</td>";//所有人合计票面价
		var td_disPrice="<td>"+item.disPrice+"</td>";//单人支付价 销售价，不含税
//		var td_payPrice="<td>"+item.payPrice+"</td>";//所有人合计支付金额(含税)
//		var td_tax="<td>"+item.tax+"</td>";//所有人合计税费
		var td_farePrice="<td>"+item.farePrice+"</td>";//票面价/单个人
		var td_rebates="<td>"+item.rebates+"</td>";//返点
		var td_promotion="<td>"+item.promotion+"</td>";//奖励返点
		var td_after="<td>"+item.after+"</td>";//后返
		var td_fixedFee="<td>"+item.fixedFee+"</td>";//奖金
		var td_yj="<td>"+item.yj+"</td>";//佣金
		//这里加一个单张票的利润。买卖差价+后返-两方的佣金
		var td_lr=cost-item.disPrice+item.farePrice*(item.after)/100.0-commission-item.yj;
		td_lr="<td>"+td_lr.toFixed(2)+"</td>";
//		var_tr="<tr>"+td_radio+td_productSource+td_offNo+td_ticketPar+td_disPrice+td_payPrice+td_tax+td_farePrice+td_rebates+td_promotion+td_after+td_fixedFee+td_yj+td_lr+"</tr>";
		var_tr="<tr>"+td_radio+td_productSource+td_priceType+td_lr+td_disPrice+td_farePrice+td_rebates+td_fixedFee+td_after+td_promotion+td_yj+td_offNo+"</tr>";
		var flightInfo="";
		$.each(item.product.tripList,function(i,item){
			flightInfo=flightInfo+"航班号："+item.airNo+"，舱位："+item.bunkNo+"；";
		});
		var td_remark="<tr><td  colspan='11' style='text-align:left;padding:0cm 0cm 0cm 0.6cm;'>"+"("+flightInfo+")"+item.product.remark+"</td></tr>";//备注
		$("#table-bd").append(var_tr+td_remark);
	});
}

function createOrder(){
	isLocalBsp=false;
	var id=$("input[name='policy']:checked").val();
	if(id==undefined){
		alert("请选择一条政策信息");
		return;
	}
	$("#createOrderDiv").css("display", "block");
	$("#fade").css("display", "block");
	$.ajax({
		url : "${ctx }/order/product-data!getProductPriceById.do",
		dataType : "json",
		data:{id:id},
		type : "POST",
		success : function (result) {
			if(result.error){
				alert(result.error);
			}else if(result.success){
				var productPriceEntity=result.productPriceEntity;
				//遍历，展示到页面上去
				var tripList=productPriceEntity.product.tripList;
				$("#createOrderFlightBody").html(""); //将里面的元素置空
				$("#createOrderPassengerBody").html(""); //将里面的元素置空
				$("#createOrderPriceBody").html(""); //将里面的元素置空
				$.each(tripList,function(i,item){
					var id="<td style='display: none;'>"+item.id+"</td>";//id号
					var airNo="<td>"+item.airNo+"</td>";//航班号
					var bunkNo="<td>"+item.bunkNo+"</td>";//舱位
					var orgCode="<td>"+item.orgCode+"</td>";//起飞城市
					var dstCode="<td>"+item.dstCode+"</td>";//到达城市
					var airOrgTime="<td>"+item.airOrgTime+"</td>";//出发时间
					var airDstTime="<td>"+item.airDstTime+"</td>";//到达时间
					var tr_createOrderFlightBody="<tr>"+id+airNo+bunkNo+orgCode+dstCode+airOrgTime+airDstTime+"</tr>";
					$("#createOrderFlightBody").append(tr_createOrderFlightBody);
				});
				var psgList=productPriceEntity.product.psgList;
				$.each(psgList,function(i,item){
					var id="<td style='display: none;'>"+item.id+"</td>";//id号
					var psgType="<td>"+item.psgType+"</td>";//乘客类型
					var psgName="<td>"+item.psgName+"</td>";//乘客姓名
					var certNo="<td>"+item.certNo+"</td>";//证件号
					var birthday="<td>"+item.birthday+"</td>";//生日
					var tr_createOrderPassengerBody="<tr>"+id+psgType+psgName+certNo+birthday+"</tr>";
					$("#createOrderPassengerBody").append(tr_createOrderPassengerBody);
				});
				var id="<td style='display: none;'>"+productPriceEntity.id+"</td>";//id号;
				var td_productSource="<td>"+productPriceEntity.product.productSource+"</td>";//政策来源
				var td_priceType="<td>"+productPriceEntity.priceType+"</td>";//运价类型
				var td_disPrice="<td>"+productPriceEntity.disPrice+"</td>";//单人支付价 销售价，不含税
				var td_farePrice="<td>"+productPriceEntity.farePrice+"</td>";//票面价/单个人
				var td_rebates="<td>"+productPriceEntity.rebates+"</td>";//返点
				var td_fixedFee="<td>"+productPriceEntity.fixedFee+"</td>";//奖金,返现
				var td_after="<td>"+productPriceEntity.after+"</td>";//后返
				var td_promotion="<td>"+productPriceEntity.promotion+"</td>";//奖励返点
				var td_yj="<td>"+productPriceEntity.yj+"</td>";//佣金
				var td_offNo="<td>"+productPriceEntity.product.offNo+"</td>";//政策出票offNo
				var tr_createOrderPriceBody=id+td_productSource+td_priceType+td_disPrice+td_farePrice+td_rebates+td_fixedFee+td_after+td_promotion+td_yj+td_offNo;
				$("#createOrderPriceBody").append(tr_createOrderPriceBody);
			}
		},
	});
}

/**
 * 单独给localbsp搞一个弹出窗口的方法
 */
function createOrderLocalBSP(){
	isLocalBsp=true;
	var id=$("input[name='policy']:checked").val();
	if(id==undefined){
		alert("请选择一条政策信息");
		return;
	}
	$("#createOrderDiv").css("display", "block");
	$("#fade").css("display", "block");
	$.ajax({
		url : "${ctx }/order/product-data!getProductPriceById.do",
		dataType : "json",
		data:{id:id},
		type : "POST",
		success : function (result) {
			if(result.error){
				alert(result.error);
			}else if(result.success){
				var productPriceEntity=result.productPriceEntity;
				//遍历，展示到页面上去
				var tripList=productPriceEntity.product.tripList;
				$("#createOrderFlightBody").html(""); //将里面的元素置空
				$("#createOrderPassengerBody").html(""); //将里面的元素置空
				$("#createOrderPriceBody").html(""); //将里面的元素置空
				$("#lacalBspOffice").html("");
				$.each(tripList,function(i,item){
					var id="<td style='display: none;'>"+item.id+"</td>";//id号
					var airNo="<td>"+item.airNo+"</td>";//航班号
					var bunkNo="<td>"+item.bunkNo+"</td>";//舱位
					var orgCode="<td>"+item.orgCode+"</td>";//起飞城市
					var dstCode="<td>"+item.dstCode+"</td>";//到达城市
					var airOrgTime="<td>"+item.airOrgTime+"</td>";//出发时间
					var airDstTime="<td>"+item.airDstTime+"</td>";//到达时间
					var tr_createOrderFlightBody="<tr>"+id+airNo+bunkNo+orgCode+dstCode+airOrgTime+airDstTime+"</tr>";
					$("#createOrderFlightBody").append(tr_createOrderFlightBody);
				});
				var psgList=productPriceEntity.product.psgList;
				$.each(psgList,function(i,item){
					var id="<td style='display: none;'>"+item.id+"</td>";//id号
					var psgType="<td>"+item.psgType+"</td>";//乘客类型
					var psgName="<td>"+item.psgName+"</td>";//乘客姓名
					var certNo="<td>"+item.certNo+"</td>";//证件号
					var birthday="<td>"+item.birthday+"</td>";//生日
					var tr_createOrderPassengerBody="<tr>"+id+psgType+psgName+certNo+birthday+"</tr>";
					$("#createOrderPassengerBody").append(tr_createOrderPassengerBody);
				});
				var id="<td style='display: none;'>"+productPriceEntity.id+"</td>";//id号;
				var td_productSource="<td>"+productPriceEntity.product.productSource+"</td>";//政策来源
				var td_priceType="<td>"+productPriceEntity.priceType+"</td>";//运价类型
				var td_disPrice="<td>"+productPriceEntity.disPrice+"</td>";//单人支付价 销售价，不含税
				var td_farePrice="<td>"+productPriceEntity.farePrice+"</td>";//票面价/单个人
				var td_rebates="<td>"+productPriceEntity.rebates+"</td>";//返点
				var td_fixedFee="<td>"+productPriceEntity.fixedFee+"</td>";//奖金,返现
				var td_after="<td>"+productPriceEntity.after+"</td>";//后返
				var td_promotion="<td>"+productPriceEntity.promotion+"</td>";//奖励返点
				var td_yj="<td>"+productPriceEntity.yj+"</td>";//佣金
				var td_offNo="<td>"+productPriceEntity.product.offNo+"</td>";//政策出票offNo
				var tr_createOrderPriceBody=id+td_productSource+td_priceType+td_disPrice+td_farePrice+td_rebates+td_fixedFee+td_after+td_promotion+td_yj+td_offNo;
				$("#createOrderPriceBody").append(tr_createOrderPriceBody);
				$("#autoPay").hide();
				$("#notPay").hide();
				$("input[name='payType'][value='directPay']").attr("checked","true");
				$("#localBspOffice").append("<h2>office信息</h2>");
				if(result.baseOfficeEntities){
					var baseOfficeEntities=result.baseOfficeEntities;
					var isSelected=false;
					$.each(baseOfficeEntities,function(i,item){
						var temp="";
						if(td_offNo.indexOf(item.office)>=0&&!isSelected){
							temp=" checked='checked'";
							isSelected=true;
						}
						var input_row="<input type='radio'  name='localBspOffice' value='"+item.id+"'"+temp+">"+item.office+"|"+item.etdzNum;
						$("#localBspOffice").append(input_row);
					});
				}
			}
		},
	});
}

function create(){
	$("#createOrder_blackLayer").css("display", "block");
	$("#loading").css("display", "block");
	$("#create").attr("disabled",true);
	param={};
	var payType=$("input[name='payType']:checked").val();
	param.payType=payType;
	var productPriceId=$("input[name='policy']:checked").val();
	var baseOfficeId=$("input[name='localBspOffice']:checked").val();
	if(isLocalBsp==true){
		if(baseOfficeId==undefined){
			alert("请选择一个office信息");
			$("#create").attr("disabled",false);
			$("#createOrder_blackLayer").css("display", "none");
			$("#loading").css("display", "none");
			return;
		}
	}
	param.productPriceId=productPriceId;
	param.baseOfficeId=baseOfficeId;
	param.orderId="${orderDetail.id}";
	$.ajax({
		url : "${ctx }/order/gj-order-deal!createOrder.do?time="+ new Date(),
		dataType : "json",
		data :param,
		type : "POST",
		success : function (result) {
			if(result.error){
				alert(result.error);
				$("#create").attr("disabled",false);
				$("#createOrder_blackLayer").css("display", "none");
				$("#loading").css("display", "none");
			}else if(result.success){
				if(result.directPay){
					window.location.href=ctx + "/order/order-pay!orderPayDeal.do?id="+param.orderId;
				}else{
					var flightClass="${orderDetail.flightClass}";
					if(flightClass=="I"){
						window.location.href=ctx + "/jsp/build/order/orderListInfo.jsp?flightClass=I";
					}else if(flightClass=="N"){
						window.location.href=ctx + "/jsp/build/order/orderListInfo.jsp?flightClass=N";
					}
				}
			}
		},
	});
}

function createCancel(){
	$("#create").attr("disabled",false);
	$("#createOrderDiv").css("display", "none");
	$("#fade").css("display", "none");
	$("#autoPay").show();
	$("#notPay").show();
	$("#localBspOffice").html("");
}
</script>
</head>
<body>
	<div class="detail-model">
		<div class="mod-hd" id="js-mod-orderPurchase">
			<span class="show">收起</span>
			<h2>b2b报价</h2>
		</div>
		<div class="btn-box"
				style=" margin:0 auto; text-align:left;padding: 10px">
				<button id="reloadPrice" class="g-btn js_submit"
					onclick="">
					<span>重新加载</span>
				</button>
				缓存时间：<label id="gettime"></label>
		</div>
		<div id="ajaxReloading" style="display: none;"><img src="${ctx }/images/build/loading.gif"></div>
		<div class="g-content">
			<table class="g-table table-list">
				<thead class="table-hd"  id="table-hd">
					<tr>
						<th class="col08" rowspan="2">单选按钮</th>
						<th class="col08">政策来源</th>
						<th class="col08">运价类型</th>
						<th class="col05">利润</th>
<%--						<th class="col08">票面价（合）</th>--%>
						<th class="col08">采购价（单）</th>
<%--						<th class="col08">合计支付金额(含税)</th>--%>
<%--						<th class="col08">合计税费</th>--%>
						<th class="col08">票面价（单）</th>
						<th class="col08">返点</th>
						<th class="col08">返现</th>
						<th class="col08">后返</th>
						<th class="col08">奖励返点</th>
						<th class="col05">佣金</th>
						<th class="col08">出票OFFICE号</th>
					</tr>
					<tr>
						<th class="col05" colspan="11">备注</th>
					</tr>
				</thead>
				<tbody class="table-bd" id="table-bd">
				</tbody>
				
			</table>
		</div>
	</div>
	
	<div id="createOrderDiv" class="white_content" style="top:15%;left:15%;width: 900px;height: 400px">
			<div>
				<div>
					<h2>航班信息</h2>
				</div>
				<table class="g-table table-list">
					<thead  class="table-hd">
						<tr>
							<th style="display: none;">id</th>
							<th>航班号</th>
							<th>舱位</th>
							<th>起飞城市</th>
							<th>到达城市</th>
							<th>起飞时间</th>
							<th>到达时间</th>
						</tr>
					</thead>
					<tbody class="table-bd"  id="createOrderFlightBody">
					</tbody>
				</table>
			</div>
			<div>
				<h2>乘客信息</h2>
				<table class="g-table table-list">
					<thead class="table-hd">
						<tr>
							<th style="display: none;">id</th>
							<th>乘客类型</th>
							<th>乘客姓名</th>
							<th>证件号</th> 
							<th>生日</th> 
						</tr>
					</thead>
					<tbody class="table-bd" id="createOrderPassengerBody">
					</tbody>
				</table>
			</div>
			<div>
				<h2>政策信息</h2>
				<table class="g-table table-list">
					<thead class="table-hd">
						<tr>
							<th style="display: none;">id</th>
							<th class="col08">政策来源</th>
							<th class="col08">运价类型</th>
							<th class="col08">采购价（单）</th>
							<th class="col08">票面价（单）</th>
							<th class="col08">返点</th>
							<th class="col08">返现</th>
							<th class="col08">后返</th>
							<th class="col08">奖励返点</th>
							<th class="col05">佣金</th>
							<th class="col08">出票OFFICE号</th>
						</tr>
					</thead>
					<tbody class="table-bd" id="createOrderPriceBody">
					</tbody>
				</table>
			</div>
			<div id="localBspOffice">
				
			</div>
			<div>
				<h2>支付信息</h2>
				<span id="autoPay">
					<input type="radio" name="payType" value="autoPay" checked="checked">自动支付
				</span>
				<input type="radio"  name="payType" value="directPay">直接支付
				<span id="notPay">
					<input type="radio"  name="payType" value="notPay">不支付
				</span>
			</div>
			<div class="btn-box" style=" margin:0 auto; text-align:center;padding: 10px">
				<button id="create" class="g-btn js_submit" onclick="create()">
					<span>创单</span>
				</button>
				<button id="createCancel" class="g-btn js_submit" onclick="createCancel()">
					<span>取消</span>
				</button>
			</div>
		</div>
	<div id="createOrder_blackLayer" class="createOrder_blackLayer"></div>
</body>
</html>