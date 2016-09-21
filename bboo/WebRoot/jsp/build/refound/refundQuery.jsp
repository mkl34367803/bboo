<%@ page language="java" import="java.util.*,com.smart.entity.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld" %>
<%@include file="../../share/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退票查询</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script src="${ctx }/static/js/build/order/orderDeal.js" type="text/javascript"></script>
<script src="${ctx }/static/js/build/refund/refundQuery.js" type="text/javascript"></script>

<script>
//显示非当前页（分页，页脚所需要的代码）
pageNav.pHtml = function(pageNo, pn, showPageNo) {
  showPageNo = showPageNo || pageNo;
  var H = " <a href='javascript:queryOrderSummary({\"page\":"+pageNo+"})' class='pageNum'>" + showPageNo + "</a> ";
  return H;
 
};

var ctx = "${ctx}";
var flightClass=getParamValue("flightClass");
var error = "${error }";
if (error) {
	alert(error);
}
</script>
</head>
<body style="margin: 10px; min-width: 920px;">
	<div class="mod-hd mod-msg" >
	</div>
	<form id="J_Form" action="">
		<table class="m-search g-clear g-table" style="width: 100%">
			<tr class="rows">
				<td class="col">
					<span class="label">订单号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="orderNo" type="text"> </span>
					</div>
				</td>
				<td class="col">
					<span class="label">票号</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="ticketNum" type="text"> </span>
					</div>
				</td>
				<td class="col">
					<span class="label">PNR</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="pnrCode" type="text" value=""> </span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">乘机人姓名</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="passengerName" type="text" value=""> </span>
					</div>
				</td>
				<td class="col">
					<span class="label">联系人手机</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="contactMob" type="text" value=""> </span>
					</div>
				</td>
				<td class="col">
					<span class="label">店铺名称</span>
					<div class="label-info">
						<span class="g-ipt-text"> <input name="shopNameCh" type="text"> </span>
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
					<th class="col08">销售日期</th>
					<th class="col05">类型</th>
					<th class="col02">起飞-到达</th>
					<th class="col04">航班日期<br>航班号(舱位)</th>
					<th class="col05">PNR</th>
					<th class="col02">成交价/人数</th>
					<th class="col02">订单状态</th>
					<th class="col02">服务说明</th>
					<th class="col05">操作</th>
				</tr>
			</thead> 
			<tbody class="table-bd" id="table-bd">
			</tbody>
		</table>
	</div>
	<div id="pageNav" >
	</div>
</body>
</html>