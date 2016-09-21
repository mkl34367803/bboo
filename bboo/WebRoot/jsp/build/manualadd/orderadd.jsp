<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>手工录入订单</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript" src="${ctx }/static/js/build/order/nationality.js"></script>
<script type="text/javascript" src="${ctx }/static/js/build/order/main.js"></script>
<link rel="stylesheet" href="${ctx }/static/css/build/main.css" type="text/css"></link></head>



<body>
	<div  style="margin-top: 10px; height: auto;width: 1070px;float: left;">
		<div >
		<div>
		
		<span class="spantagcss" style="font-size: 20px">添加订单</span> 
	</div>
	<div style="float: left;width:100%;height: 1%;" class="mod-hd">
		
		</div>
	
	<form  action="${ctx }/order/gj-order-manual!insertorder.do" method="post" id="myform"  name="f1" >
	
			<span>共享航班 <font color="red" >*</font></span>
			<div class="label-info">
						<label> <input name="IsShared" value="Y" type="radio" checked="checked" class="g-input-check">是</label> 
						<label> <input name="IsShared" value="N" type="radio" class="g-input-check">否</label> 
						<label> <input name="IsShared" value="T" type="radio" class="g-input-check">其他</label>
			</div>
			
		<div>
			<span style="font-size: 15px;color: #0040FF">航班信息</span>
			</div>
		<div id="div_flight">
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
					<span>航段</span>
						
							<a href="javascript:delflight()" style="float: right;">删除该航段</a></th>
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
					
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>航程序号：
						<input name="sequence" id="sequence"  value="1"  type="text" style="width: 140px;height: 25px" onblur="checkSequence(this)" />
						<span ID='sequenceSpan' ></span>
					</td>
					
					
					<td >
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red" >*</font>航班号：
						<input name="flightNum" id="flightNum" value="" type="text" style="width: 140px;height: 25px" onblur="checkNum(this)" />
						<span id="flightNumSpan"></span>
					</td>
					
					<td style="width: 260px">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red" >*</font>承运人：
						<input name="carrier" id="carrier" value="" type="text" style="width: 140px;height: 25px" onblur="checkAir(this)" />
						<span id="carrierSpan"></span>
					</td>
				
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>舱位：
						<input name="cabin" value=""id="cabin" value="" type="text" style="width: 140px;height: 25px" onblur="checkCabin(this)"  />
						<span id="cabinSpan"></span>
					</td>
				</tr>
				<tr>
					
					
					
					<td><font color="red">*</font>出发机场三字码：
						<input name="depAircode" id="depAircode" value="" type="text" style="width: 140px;height: 25px" onblur="checkDepair(this)"  />
						<span id="depAircodeSpan"></span>
					</td>
					
					
					
					
					<td><font color="red">*</font>到达机场三字码：
						<input name="arrAircode" id="arrAircode" value="" type="text" style="width: 140px;height: 25px" onblur="checkarrAircode(this)"  />
						<span id="arrAircodeSpan"></span>
					</td>
					<td style="width: 260px">&nbsp;
					<font color="red">*</font>出发日期：
						<input  onclick="laydate()" name="departureDate" type="text" class="cinput" style="width: 140px;height: 25px" />
						
					</td>
					
					
					<td style="width: 260px">
					<font color="red">*</font>到达日期：
						<input onclick="laydate()" name="arrivalDate" type="text" class="cinput" style="width: 140px;height: 25px"  />
						
					</td>
					
					
					
				</tr>
				<tr>
					
					
					
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>出发时间：
						<input type="text"  name="departureTime" id="departureTime" value="" style="width: 140px;height: 25px" onblur="checkDepTime(this)" />
						<span id="departureTimeSpan"></span>
					</td>
					
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>到达时间 ：
						<input type="text"  name="arrivalTime" id="arrivalTime" value=""style="width: 140px;height: 25px" onblur="checkarrivalTime(this)"  />
						<span id="arrivalTimeSpan"></span>
					</td>
					<td>&nbsp;
					<font color="red">*</font>航段类型：
					<select name="segmentType" id="segmentType" onblur="checksegmentType(this)"style="width: 140px;height: 25px" >
						
						<option value="1" selected="selected">去程</option>
						<option value="2">回程</option>
					</select>
					<span id="segmentTypeSpan"></span>
					</td>
				</tr>
					</tbody>
				</table>
		</div>
				<div id="bbbb"></div>
				<a href="javascript:flight()" class="easyui-linkbutton"
				style="float: right;" >增加航段 </a>
		
		<div>
			<span style="font-size: 15px;color: #0040FF">乘机人信息</span>
		</div>
		<div id="div_pass">
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
					<span>乘客</span>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							序号：
							 <input type="text" name="pindex" id="pindex"style="width: 140px;height: 25px" value="1" onblur="checkpindex(this)" />
							<span id="pindexSpan"></span>
							</label> 
							<a href="javascript:delpass()" style="float: right;">删除该乘客</a></th>
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
						
				<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">*</font>乘客姓名：
						<input name="name" id="name" value="" type="text" style="width: 140px;height: 25px" onblur="checkName(this)"  />
					<span id="nameSpan"></span>
					</td>
					
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>证件号码：
						<input name="cardNum" id="cardNum" type="text" style="width: 140px;height: 25px" onblur="checkcardNum(this);"  />
						<span ></span>
					</td>
					<td style="width: 260px">
					<font color="red">*</font>出生日期：
						<input name="birthday"  type="text" id="birthday" style="width: 140px;height: 25px"  />
					</td>
				<td>&nbsp;
						<font color="red">*</font>
							乘客类别：
							 <select name="ageType"id="ageType" onchange="ageTypeChange();" onblur="checkageType(this)" style="width: 140px;height: 25px">
								
								<option value="0" selected="selected">成人</option>
								<option value="1">儿童</option>
								<option value="2">婴儿</option>
								<option value="-1">留学生</option>
							</select> 
							
						</td>
				
					
				</tr>
				<tr>
				
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;<font color="red">*</font>乘客描述：
						<select name="ageDes"id="ageDes" style="width: 140px;height: 25px" >
						
							<option value="青年" selected="selected">青年</option>
							<option value="老年">老年</option>
							<option value="中年">中年</option>
							<option value="儿童">儿童</option>
						</select>
					</td>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<font color="red">*</font>性别：
						<select name="gender"id="gender"  style="width: 140px;height: 25px">
							
							<option value="M" selected="selected">男</option>
							<option value="F">女</option>
						</select>
					</td>
					
					
					<td style="width: 260px">
					<font color="red">*</font>证件类型：
						<select name="cardType" id="cardType" style="width: 140px;height: 25px">
							
							<option value="NI" selected="selected" >身份证</option>
							<option value="PP">护照</option>
							<option value="GA">港澳通行证</option>
							<option value="TW">台湾通行证</option>
							<option value="LN">临时身份证</option>
							<option value="HK">户口簿</option>
							<option value="JG">警官证</option>
							<option value="JR">军人证</option>
							<option value="SB">士兵证</option>
							<option value="XS">学生证</option>
							<option value="JS">驾驶证</option>
							<option value="HX">回乡证</option>
							<option value="TB">台胞证</option>
							<option value="HY">国际海员证</option>
							<option value="CS">出生证明</option>
							<option value="TH">其它</option>
							
						</select>
					</td>
					
					
					
					
					<td>
						<div class="control-nationality-suggest" style="left: 24px;">
							<font color="red">*</font>国籍：
							<input  name="nationality"id="nationality"  type="text" class="nationality-suggest-input"
							style="width: 140px;height: 25px"onblur="checknationality(this)" />
							<span id="nationalitySpan"></span>
							<div class="nationality-suggest-list-container">
								<!-- <div class="nationality-suggest-hint">输入中英文/代码搜索选择</div> -->
								<ul class="nationality-suggest-list" ></ul>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>发证国家：
						<input type="text"  name="cardIssuePlace" id="cardIssuePlace" style="width: 140px;height: 25px" onblur="checkcardIssuePlace(this)"  />
						<span id="cardIssuePlaceSpan"></span>
					</td>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>证件有效期：
						
						<input  onclick="laydate()" name="cardExpired" type="text" class="cinput" style="width: 140px;height: 25px" />
						<span id="cardExpiredSpan"></span>
					</td>
					<td style="width: 260px"><font color="red">*</font>
					&nbsp;保险：
					<select name=" bxCount" id="bxCount" onblur="cehckbxCount(this)" >
						<option value="1" selected="selected">1</option>
						<option value="0">0</option>
						
					</select>
						份
						<i>￥ 30/份</i>
						<span id="bxCountSpan"></span>
					</td>
					
				<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<font color="red">*</font>票号：
						<input type="text"  name="eticketNum" id="eticketNum" style="width: 140px;height: 25px" onblur="checketicketNum(this)" />
						<span></span>
					</td>
					
				</tr>
				<tr>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;<font color="red">*</font>票面价：
						<input name="price" id="price"value="" type="text" style="width: 140px;height: 25px"
						class="easyui-numberbox" data-options="min:0,precision:2" onblur="checkPrice(this)" />
					<span ></span>
					</td>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>销售价：
						<input type="text"  name="cost" id="cost"  style="width: 140px;height: 25px"
						class="easyui-numberbox" data-options="min:0,precision:2" onblur="checkcost(this)"  />
						<span></span>
					</td>
					
				</tr>

					</tbody>
				</table>
		</div>
		<div id="aaa"></div>
			<a href="javascript:pass()" class="easyui-linkbutton"
				style="float: right;" >增加乘客 </a>
				
		
		<div>
			<span style="font-size: 15px;color: #0040FF">订单信息</span>
		</div>
		<div>
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
					
						<label>
							<font color="red">*</font>B2C网站入库号：
							<input name="extOrderID" id="extOrderID" value="" type="text"  onblur="checkextOrderID(this)" />
								<span id="extOrderIDSpan"></span>
							</label> 
							
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font> 订单号：
						<input name="orderNo" id="orderNo" value="" type="text" style="width: 140px;height: 25px" onblur="checkorderNo(this)"/>
						<span id="orderNoSpan"></span>
					</td>
					
					
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>PNR：
						<input name="pnrCode" id="pnrCode" value="" type="text" style="width: 140px;height: 25px" onblur="checkpnrCode(this)"/>
						<span id="pnrCodeSpan"></span>
					</td>
					
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>PNR 大编：
						<input name="pnrCodeBig" id="pnrCodeBig" value="" type="text" style="width: 140px;height: 25px" onblur="checkpnrCodeBig(this)"  />
						<span id="pnrCodeBigSpan"></span>
					</td>
				
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单状态：
						<select name="status"id="status" style="width: 140px;height: 25px" onblur="checkstatus()" >
							
							<option value="0" selected="selected">订座成功等待支付</option>
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
							<option value="-1">其他</option>
							
						</select>
						<span id="statusSpan"></span>
					</td>
				</tr>
				<tr>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					航程类型：
						<select name="flightType"id="flightType" style="width: 140px;height: 25px" >
							
							<option value="s" selected="selected">单程</option>
							<option value="d">往返</option>
							<option value="l">联程</option>
							<option value="x">多程</option>
							<option value="t">其他</option>
						</select>
					</td>
					
					
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;出票紧急度：
						<select name="urgency" id="urgency"style="width: 140px;height: 25px" onblur="checkurgency(this)" >
							
							<option value="1" selected="selected">临近转出时间 （距离转出小于30分钟）</option>
							<option value="2">催出票 （urgencyTimes>0）</option>
							<option value="3">AV舱位不足5个(由于前段数据不足，故暂时废弃)</option>
							<option value="4">临近PNR ADTK （距离ADTK大于3小时）</option>
							<option value="5">出票超时长规范 （进入超过1小时）</option>
							<option value="-1">其他</option>
						</select>
						<span id="urgencySpan"></span>
					</td>
					
					
					<td style="width: 260px">&nbsp;
					<font color="red">*</font>航班剩余座位：
						<input name="seats"id="seats" type="text" style="width: 140px;height: 25px" onblur="checkseats(this)"/>
						<span id="seatsSpan"></span>
					</td>
					
					
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					儿童数目：
						<input name="childrenCount" id="childrenCount" value="0" type="text" style="width: 140px;height: 25px" onblur="checkchildrenCount(this)"/>
						<span id="childrenCountSpan"></span>
					</td>
				</tr>
				<tr>
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					成人数目：
						<input name="adultsCount" id="adultsCount" value="1" type="text" style="width: 140px;height: 25px" onblur="checkadultsCount(this)"/>
						<span id="adultsCountSpan"></span>
					</td>
					<!-- 订单分离等操作后的关联的原始订单ID (分离订单，新 ID生成规则，基于乘客分离 ID+P 基于航段分离 ID+F 基于换开分离 ID+O) -->
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单分离：
					<input name="oldId" id="oldId" value="" type="text" style="width: 140px;height: 25px" onblur="checkoldId()" />
					<span id="oldIdSpan"></span>
					</td>
					<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>订单来源：
						<select name="source" id="source"style="width: 140px;height: 25px" >
					
						<option value="self" selected="selected">普通政策</option>
						<option value="qfare">加价政策</option>
						<option value="spec">特殊政策</option>
						<option value="othe">其它</option>
						</select>
					</td>
					
					<td style="width: 260px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					锁定操作人：
						<input name="lockUser" id="lockUser"value="" type="text" style="width: 140px;height: 25px" onblur="checklockUser(this)" />
						<span id="lockUserSpan"></span>
					</td>
				</tr>
				
					</tbody>
				</table>
	</div>
		<div>
			<span style="font-size: 15px;color: #0040FF">价格</span>
	</div>
	<div>
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
								
							
							
								<td style="width: 300px">&nbsp;&nbsp;<font color="red">*</font>
									成人价格：
									<input name="adultPrice" id="adultPrice" onkeyup="caculate()" onblur="checkadultPrice(this)"
									 class="easyui-numberbox" data-options="min:0,precision:2"  value="">
									 <span></span>
								</td>
								<td><font color="red">*</font>
									税费：
									<input name="adultTax" id="adultTax" onkeyup="caculate()" class="easyui-numberbox" data-options="min:0,precision:2"   value=" ">
								</td>
							</tr>
							<tr>
								<td><font color="red">*</font>
									儿童价格 ：
									<input name="childPrice" id="childPrice" onkeyup="caculate()" class="easyui-numberbox" data-options="min:0,precision:2" value="">
								</td>
							
								<td><font color="red">*</font>
								税费：
									<input name="childTax" id="childTax" onkeyup="caculate()" class="easyui-numberbox" data-options="min:0,precision:2" value="">
								</td>
								
							
							</tr>
						
							<tr>
							
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									总价：
									<input name="allprice" id="allprice" disabled="disabled" value="">
								</td>
								
								
							</tr>
					</tbody>
				</table>
	</div>
	<div>
			<span style="font-size: 15px;color: #0040FF">政策信息</span>
	</div>
	<div>
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
					<td ><font color="red">*</font> PNR 预定 OFFICE号：
						<input name="pnrOffNo"id="pnrOffNo" value="" type="text" style="width: 140px;height: 25px" onblur="checkpnrOffNo(this)"/>
						<span id="pnrOffNoSpan"></span>
					</td>
					
					
					<td><font color="red">*</font>提取OFFICE号：
						<input name="rtOffno"id="rtOffno" value="" type="text" style="width: 140px;height: 25px" onblur="checkrtOffno(this)"/>
						<span id="rtOffnoSpan"></span>
					</td>
					
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PNR文本：
						<input name="pnrText" id="pnrText" value="" type="text" style="width: 140px;height: 25px" />
						<span id="pnrTextSpan"></span>
					</td>
				
					<td><font color="red">*</font>政策类型：
						<select name="policyType" id="policyType" onblur="checkpolicyType(this)" style="width: 140px;height: 25px">
							
							<option value="1" selected="selected">NFD</option>
							<option value="2">清仓产品</option>
							<option value="3">商旅产品</option>
							<option value="5">中转产品</option>
							<option value="6">包机</option>
							<option value="7">切位</option>
							<option value="8">航司 VIP 卡</option>
							<option value="10000">普通 供应商 OpenAPI对外接口规范 86</option>
							<option value="10001">规则运价</option>
							<option value="-1">其它</option>
						</select>
						<span id="policyTypeSpan"></span>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>政策来源：
						<input name="productSource" id="productSource" value="" type="text" style="width: 140px;height: 25px" onblur="checkproductSource(this)" />
						<span id="productSourceSpan"></span>
					</td>
					
					
					
					
					
					<td >&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="red">*</font>销售政策ID：
						<input name="policyId" id="policyId" value="" type="text" style="width: 140px;height: 25px"onblur="checkpolicyId(this)"/>
						<span id="policyIdSpan"></span>
					</td>
					
					
					<td style="width: 260px">销售政策Code：
						<input name="policyCode" id="policyCode" value="" type="text" style="width: 140px;height: 25px"  />
						<span id="policyCodeSpan"></span>
					</td>
				</tr>
					</tbody>
				</table>
	</div>
	<div>
				<span style="font-size: 15px;color: #0040FF">联系人信息</span>
	</div>
	<div>
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr>
					<td style="width: 260px"><font color="red">*</font>联系人姓名：
							<input name="contactName" id="contactName" value="" type="text" style="width: 140px;height: 25px"onblur="checkcontactName(this)"/>
							<span id="contactNameSpan"></span>
						</td>
						<td style="width: 260px"><font color="red">*</font>联系人手机：
							<input name="contactMob" id="contactMob" value="" type="text" style="width: 140px;height: 25px" onblur="checkcontactMob(this)"  />
							<span id="contactMobSpan"></span>
						</td>
						<td style="width: 260px">联系人电话：
							<input name="contactTel"id="contactTel" value="" type="text" style="width: 140px;height: 25px" />
						</td>
						<td style="width: 260px">联系人邮箱：
							<input name="contactEmail" id="contactEmail" value="" type="text" style="width: 140px;height: 25px" />
						</td>
				</tr>
					</tbody>
				</table>
	</div>
	<div>
				<span style="font-size: 15px;color: #0040FF">投递信息</span>
	</div>
	<div>
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
						</tr>
					</thead>
					<tbody class="table-bd">
						<tr style="float: left;">
						<td> &nbsp;&nbsp;&nbsp;收件人：
							<input name="receiver" id="receiver" value="" type="text" style="width: 140px;height: 25px"onblur="checkreceiver(this)"/>
							<span id="receiverSpan"></span>
						</td>
						
					</tr>	
					<tr >
						<td style="float: left;">邮政编码：
							<input name="postcode" id="postcode" value="" type="text" style="width: 140px;height: 25px"onblur="checkpostcode(this)"/>
							<span id="postcodeSpan"></span>
						</td>
					</tr>
					<tr style="float: left;">
						<td>投递地址：
							<input name="address" id="address" value="" type="text" style="width: 400px;height: 25px"onblur="checkaddress(this)"/>
							<span id="addressSpan"></span>
						</td>
					</tr>		
					</tbody>
				</table>
	</div>
	<div>
				<span style="font-size: 15px;color: #0040FF">退改签规则</span>
	</div>

	<div>
			<table class="g-table table-list">
				<thead class="table-hd ">
					<tr>
					<th colspan="4" style="text-align: left;">
						</tr>
					</thead>
					<tbody class="table-bd" style="float: left;">
						<tr>
						<td>退票规则：
							<input name="returnTicketRule" id="returnTicketRule" value="" type="text" style="width: 700px;height: 25px"  />
						</td>
						
					</tr>	
					<tr>
						<td>改期规则：
							<input name="changeDateRule" id="changeDateRule" value="" type="text" style="width: 700px;height: 25px" />
						</td>
					</tr>
					<tr>
						<td>退款原因：
							<input name="refundCause" id="refundCause" value="" type="text" style="width: 700px;height: 25px"  />
						</td>
					</tr>	
					<tr>
						<td>其他说明 ：
						<textarea name="statement" id="statement" value="${order.statement }" style="width: 700px;height: 100px"></textarea>
							
						</td>
					</tr>		
					</tbody>
				</table>
	</div>
	
				 
				</form>
	
		 
		 <button style="float: right;" class="g-btn js_submit " type="button" onclick="tiJiao()">提交</button>
		
		
	</div>
	
	
	</div>
	
		
</body>
<script type="text/javascript">

     function checkSequence(x){
     		if($(x).val()==''||$(x).val()==null){
     			$(x).siblings('span').css("color", "red").text("航班序号不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[0-9]{0,20}$/;
		           if(ai.test($(x).val())){
		             $(x).siblings('span').text("");
			         return true;
		           }{
		               $(x).siblings('span').css("color", "red").text("航班序号不合法只能是数字");
			         return false;
		           } 
	       }
	  }
	   function checkpindex(pi){
     		if($(pi).val()==''||$(pi).val()==null){
     			$(pi).siblings('span').css("color", "red").text("序号不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[0-9]{0,20}$/;
		           if(ai.test($(pi).val())){
		             $(pi).siblings('span').text("");
			         return true;
		           }{
		               $(pi).siblings('span').css("color", "red").text("序号不合法只能是数字");
			         return false;
		           } 
	       }
	  }
	 function checkNum(num){
	       
	       if($(num).val()==''||$(num).val()==null){
	           $(num).siblings('span').css("color","red").text("航班号不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[A-Z]{2}[0-9]{3}$/;
		           if(ai.test($(num).val())){
		             $(num).siblings('span').text("");
			         return true;
		           }{
		           $(num).siblings('span').css("color", "red").text("航班号不合法，如（AA123）");
		             
			         return false;
		           } 
	          
	       }
	  }
	function checkAir(air){
	     
	       if($(air).val()==''||$(air).val()==null){
	       		$(air).siblings('span').css("color","red").text("承运人不能够为空！");
	           return false;
	       }else {
	                 var car = /^[A-Za-z]{0,3}$/;
		           if(car.test($(air).val())){
		             $(air).siblings('span').text("");
			         return true;
		           }{
		           $(air).siblings('span').css("color", "red").text("承运人号不合法，不能大于三个字符！");
			         return false;
		           } 
	          
	       }
	  }
	function checkCabin(ckca){
	       if($(ckca).val()==''||$(ckca).val()==null){
	       $(ckca).siblings('span').css("color","red").text("舱位不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[A-Z]{1}$/;
		           if(ai.test($(ckca).val())){
		            $(ckca).siblings('span').text("");
			         return true;
		           }{
		             $(ckca).siblings('span').css("color", "red").text("只能输入一个大写字母");
			         return false;
		           } 
	       }
	  }

	function checkDepair(da){
	       if($(da).val()==''||$(da).val()==null){
	       		$(da).siblings('span').css("color","red").text("出发机场三字码不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[A-Z]{3}$/;
		           if(ai.test($(da).val())){
		              $(da).siblings('span').text("");
			         return true;
		           }{
		            $(da).siblings('span').css("color","red").text("出发机场三字码不合法,如（AAA）");
			         return false;
		           } 
	          
	       }
	  }
	
	function checkPrice(cp){
		if($(cp).val()==''||$(cp).val()==null){
	       		$(cp).siblings('span').css("color","red").text("票面价不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[0-9]+\.?[0-9]{0,2}$/;
		           if(ai.test($(cp).val())){
		              $(cp).siblings('span').text("");
			         return true;
		           }{
		            $(cp).siblings('span').css("color","red").text("只能填写数字！");
			         return false;
		           } 
	       }
	  }
	  
	  function checkcost(kc){
		if($(kc).val()==''||$(kc).val()==null){
	       		$(kc).siblings('span').css("color","red").text("销售价不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[0-9]+\.?[0-9]{0,2}$/;
		           if(ai.test($(kc).val())){
		              $(kc).siblings('span').text("");
			         return true;
		           }{
		            $(kc).siblings('span').css("color","red").text("只能填写数字！");
			         return false;
		           } 
	       }
	  }
	   function checkadultPrice(tp){
		if($(tp).val()==''||$(tp).val()==null){
	       		$(tp).siblings('span').css("color","red").text("成人价不能够为空！");
	           return false;
	       }else {
	                 var ai = /^[0-9]+\.?[0-9]{0,2}$/;
		           if(ai.test($(tp).val())){
		              $(tp).siblings('span').text("");
			         return true;
		           }{
		            $(tp).siblings('span').css("color","red").text("只能填写数字！");
			         return false;
		           } 
	       }
	  }
	function checkDepTime(dt){
		if($(dt).val()==''||$(dt).val()==null){
	       		$(dt).siblings('span').css("color","red").text("出发时间不能为空！");
	           return false;
	       }else {
	                  var ai = /^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/;
		           if(ai.test($(dt).val())){
		              $(dt).siblings('span').text("");
			         return true;
		           }{
		            $(dt).siblings('span').css("color","red").text("时间不对！如：（00：00）");
			         return false;
		           } 
	       }
	  }
	function checkarrivalTime(arrt){
		if($(arrt).val()==''||$(arrt).val()==null){
	       		$(arrt).siblings('span').css("color","red").text("到达时间不能为空！");
	           return false;
	       }else {
	                var ar = /^(0\d{1}|1\d{1}|2[0-3]):([0-5]\d{1})$/;
		           if(ar.test($(arrt).val())){
		              $(arrt).siblings('span').text("");
			         return true;
		           }{
		            $(arrt).siblings('span').css("color","red").text("时间不对！如：（00：00）");
			         return false;
		           } 
	       }
	  }	  
	function checkarrAircode(ac){
		if($(ac).val()==''||$(ac).val()==null){
	       		$(ac).siblings('span').css("color","red").text("到达机场三字码不能为空！");
	           return false;
	       }else {
	                 var ar = /^[A-Z]{3}$/;
		           if(ar.test($(ac).val())){
		              $(ac).siblings('span').text("");
			         return true;
		           }{
		            $(ac).siblings('span').css("color","red").text("仅支持大写字母，不得超过3个字符");
			         return false;
		           } 
	       }
	  }	  

	function checkName(na){
		if($(na).val()==''||$(na).val()==null){
	       		$(na).siblings('span').css("color","red").text("乘客姓名不能为空！");
	           return false;
	       }else {
	               var ar = /^[a-zA-Z\u4e00-\u9fa5]+$/;
		           if(ar.test($(na).val())){
		              $(na).siblings('span').text("");
			         return true;
		           }{
		            $(na).siblings('span').css("color","red").text("不支持数字和符号");
			         return false;
		           } 
	       }
	  }	 
	function checkNumID(anum){
	if($(anum).val()==''||$(anum).val()==null){
	       		$(anum).siblings('span').css("color","red").text("证件号不能为空！");
	           return false;
	       }else {
	                  var ar = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
		           if(ar.test($(anum).val())){
		              $(anum).siblings('span').text("");
			         return true;
		           }{
		            $(anum).siblings('span').css("color","red").text("请输入正确的（15位或18位字母请大写）证件号");
			         return false;
		           } 
	       }
	  }	 
	 
	 //获取生日
	 function getBirthDay(iIdNo){
	 	var tmpStr = "";
	 	if (iIdNo.length == 15) {
			tmpStr = iIdNo.substring(6, 12);
			tmpStr = "19" + tmpStr;
			tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
			return tmpStr;
		}
		else {
			tmpStr = iIdNo.substring(6, 14);
			tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6);
			return tmpStr;
		}
	 }

	
	function getAge(birthdayStr){
		var births = birthdayStr.split("-");
			var Y = new Date().getFullYear(); 
			return Y - births[0];
	} 
	
	 //获取乘客描述...
	 function getAgeDes(birthday){
	 
	 	var age = getAge(birthday);  
	 	
	 	if(age<=18){
	 		return "儿童";
	 	} else if(age<40){
	 		return "青年";
	 	} else if(age<60){
	 		return "中年";
	 	}else {
	 		return "老年";
	 	}
	 }
	 
     function checkcardNum(anum) {
     	if(checkNumID($(anum))){//身份证号码正确
     		var birthday = getBirthDay($(anum).val());//获取生日
     		var ageDes = getAgeDes(birthday);//获取乘客描述...
     		$(anum).parent().next().find("input[name='birthday']").val(birthday);
     		$(anum).parent().parent().next().find("select[name='ageDes']").val(ageDes);
     		return true;
     	} else {
     		return false;
     	}
     } 
	function checkID(li){
		if($(li).val()==''||$(li).val()==null){
	       		$(li).siblings('span').css("color","red").text("国籍不能为空！");
	           return false;
	       }else {
	                 var ar = /^[a-zA-Z\u4e00-\u9fa5]+$/;
		           if(ar.test($(li).val())){
		              $(li).siblings('span').text("");
			         return true;
		           }{
		            $(li).siblings('span').css("color","red").text("没有该国籍！");
			         return false;
		           } 
	       }
	  }	 
	  function getCardIss(iss){
	 	var isStr = "";
			isStr = iss.substring();
			return isStr;
	 }
     function checknationality() {
     	var $lity = $("#nationality");
     	if(checkID($lity)){
     		var cardIssuePlace = getCardIss($lity.val());
     		$("#cardIssuePlace").val(cardIssuePlace);
     		return true;
     	} else {
     		return false;
     	}
     }
	function checkcardIssuePlace(pl){
	if($(pl).val()==''||$(pl).val()==null){
	       		$(pl).siblings('span').css("color","red").text("发证国家不能为空！");
	           return false;
	       }else {
	                  var ar =/^[a-zA-Z\u4e00-\u9fa5]+$/;
		           if(ar.test($(pl).val())){
		              $(pl).siblings('span').text("");
			         return true;
		           }{
		            $(pl).siblings('span').css("color","red").text("只能输入字母");
			         return false;
		           } 
	       }
	  }	 
	function checkextOrderID(){
	       var extOrderIDSpan=document.getElementById("extOrderIDSpan");
	       var extOrderID=$("input[name='extOrderID']").val();
	       if(extOrderID==''||extOrderID==null){
	            extOrderIDSpan.innerHTML='<span style="color:red;">B2C网站入库号不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-z0-9]+$/;
		           if(ar.test(extOrderID)){
		             extOrderIDSpan.innerHTML='';
			         return true;
		           }{
		              extOrderIDSpan.innerHTML='<span style="color:red;">请输入正确的B2C网站入库号号</span>';
			         return false;
		           } 
	          
	       }
	  }
	function checkorderNo(){
	       var orderNoSpan=document.getElementById("orderNoSpan");
	       var orderNo=$("input[name='orderNo']").val();
	       if(orderNo==''||orderNo==null){
	            orderNoSpan.innerHTML='<span style="color:red;">订单号不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-z0-9]+$/;
		           if(ar.test(orderNo)){
		             orderNoSpan.innerHTML='';
			         return true;
		           }{
		              orderNoSpan.innerHTML='<span style="color:red;">请输入正确的订单号</span>';
			         return false;
		           } 
	          
	       }
	  }
	  function checkpnrCode(){
	       var pnrCodeSpan=document.getElementById("pnrCodeSpan");
	       var pnrCode=$("input[name='pnrCode']").val();
	       if(pnrCode==''||pnrCode==null){
	            pnrCodeSpan.innerHTML='<span style="color:red;">PNR不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[A-Z0-9]+$/;
		           if(ar.test(pnrCode)){
		             pnrCodeSpan.innerHTML='';
			         return true;
		           }{
		              pnrCodeSpan.innerHTML='<span style="color:red;">PNR输入有误</span>';
			         return false;
		           } 
	          
	       }
	  }	
	 function checkpnrCodeBig(){
	       var pnrCodeBigSpan=document.getElementById("pnrCodeBigSpan");
	       var pnrCodeBig=$("input[name='pnrCodeBig']").val();
	       if(pnrCodeBig==''||pnrCodeBig==null){
	            pnrCodeBigSpan.innerHTML='<span style="color:red;">PNR大编不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[A-Z0-9]+$/;
		           if(ar.test(pnrCodeBig)){
		             pnrCodeBigSpan.innerHTML='';
			         return true;
		           }{
		              pnrCodeBigSpan.innerHTML='<span style="color:red;">请输入正确的PNR大编</span>';
			         return false;
		           } 
	          
	       }
	  }
	function checkseats(){
	       var seatsSpan=document.getElementById("seatsSpan");
	       var seats=$("input[name='seats']").val();
	       if(seats==''||seats==null){
	            seatsSpan.innerHTML='<span style="color:red;">剩余座位数不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[0-9]{0,3}$/;
		           if(ar.test(seats)){
		             seatsSpan.innerHTML='';
			         return true;
		           }{
		              seatsSpan.innerHTML='<span style="color:red;">输入有误</span>';
			         return false;
		           } 
	          
	       }
	  }
	  	function checkchildrenCount(){
	       var childrenCountSpan=document.getElementById("childrenCountSpan");
	       var childrenCount=$("input[name='childrenCount']").val();
	       if(childrenCount==''||childrenCount==null){
	            childrenCountSpan.innerHTML='<span style="color:red;">儿童数量不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[0-9]{0,2}$/;
		           if(ar.test(childrenCount)){
		             childrenCountSpan.innerHTML='';
			         return true;
		           }{
		              childrenCountSpan.innerHTML='<span style="color:red;">请输入正确的整数</span>';
			         return false;
		          } 
	          
	       }
	  }	 
	 function checkadultsCount(){
	       var adultsCountSpan=document.getElementById("adultsCountSpan");
	       var adultsCount=$("input[name='adultsCount']").val();
	       if(adultsCount==''||adultsCount==null){
	            adultsCountSpan.innerHTML='<span style="color:red;">成人数量不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[0-9]{0,2}$/;
		           if(ar.test(adultsCount)){
		             adultsCountSpan.innerHTML='';
			         return true;
		           }{
		              adultsCountSpan.innerHTML='<span style="color:red;">请输入正确整数</span>';
			         return false;
		           } 
	          
	       }
	       
	  }	 
	function checkpnrOffNo(){
	       var pnrOffNoSpan=document.getElementById("pnrOffNoSpan");
	       var pnrOffNo=$("input[name='pnrOffNo']").val();
	       if(pnrOffNo==''||pnrOffNo==null){
	            pnrOffNoSpan.innerHTML='<span style="color:red;">PNR 预定的OFFICE号不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-Z0-9]+$/;
		           if(ar.test(pnrOffNo)){
		             pnrOffNoSpan.innerHTML='';
			         return true;
		           }{
		              pnrOffNoSpan.innerHTML='<span style="color:red;">输入有误</span>';
			         return false;
		           } 
	          
	       }
	  }	
	function checkrtOffno(){
	       var rtOffnoSpan=document.getElementById("rtOffnoSpan");
	       var rtOffno=$("input[name='rtOffno']").val();
	       if(rtOffno==''||rtOffno==null){
	            rtOffnoSpan.innerHTML='<span style="color:red;">提取PNR 的OFFICE号不能为空！</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-Z0-9]+$/;
		           if(ar.test(rtOffno)){
		             rtOffnoSpan.innerHTML='';
			         return true;
		           }{
		              rtOffnoSpan.innerHTML='<span style="color:red;">输入有误</span>';
			         return false;
		           } 
	          
	       }
	  }
	  function checkproductSource(){
	       var productSourceSpan=document.getElementById("productSourceSpan");
	       var productSource=$("input[name='productSource']").val();
	       if(productSource==''||productSource==null){
	            productSourceSpan.innerHTML='<span style="color:red;">政策来源不能为空</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-Z0-9]+$/;
		           if(ar.test(productSource)){
		             productSourceSpan.innerHTML='';
			         return true;
		           }{
		              productSourceSpan.innerHTML='<span style="color:red;">输入有误</span>';
			         return false;
		           } 
	          
	       }
	  }  	 	 		
	 	function checkpolicyId(){
	       var policyIdSpan=document.getElementById("policyIdSpan");
	       var policyId=$("input[name='policyId']").val();
	       if(policyId==''||policyId==null){
	            policyIdSpan.innerHTML='<span style="color:red;">销售政策ID不能为空</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-Z0-9]+$/;
		           if(ar.test(policyId)){
		             policyIdSpan.innerHTML='';
			         return true;
		           }{
		              policyIdSpan.innerHTML='<span style="color:red;">输入有误</span>';
			         return false;
		           } 
	          
	       }
	  } 
	function checkcontactName(){
	       var contactNameSpan=document.getElementById("contactNameSpan");
	       var contactName=$("input[name='contactName']").val();
	       if(contactName==''||contactName==null){
	            contactNameSpan.innerHTML='<span style="color:red;">联系人姓名不能为空</span>';
	           return false;
	       }else {
	                 var ar = /^[a-zA-Z\u4e00-\u9fa5]+$/;
		           if(ar.test(contactName)){
		             contactNameSpan.innerHTML='';
			         return true;
		           }{
		              contactNameSpan.innerHTML='<span style="color:red;">不支持数字和符号</span>';
			         return false;
		           } 
	          
	       }
	  }
	function checkcontactMob(){
	       var contactMobSpan=document.getElementById("contactMobSpan");
	       var contactMob=$("input[name='contactMob']").val();
	       if(contactMob==''||contactMob==null){
	            contactMobSpan.innerHTML='<span style="color:red;">联系人手机不能为空</span>';
	           return false;
	       }else {
	                 var ar = /^1[0-9]{10}$/;
		           if(ar.test(contactMob)){
		             contactMobSpan.innerHTML='';
			         return true;
		           }{
		              contactMobSpan.innerHTML='<span style="color:red;">请输入正确的手机号码</span>';
			         return false;
		           } 
	          
	       }
	  }
	function checkreceiver(){
	       var receiverSpan=document.getElementById("receiverSpan");
	       var receiver=$("input[name='receiver']").val();
     		 var ar = /^[a-zA-Z\u4e00-\u9fa5]+$/;
           if(ar.test(receiver)){
             receiverSpan.innerHTML='';
	         return true;
           }{
              receiverSpan.innerHTML='<span style="color:red;">不支持数字和符号</span>';
	         return false;
           } 
	        
	  }
	  
	  function checkoldId(){
	       var oldIdSpan=document.getElementById("oldIdSpan");
	       var oldId=$("input[name='oldId']").val();
     		 var ar = /^[a-zA-Z\u4e00-\u9fa5]+$/;
           if(ar.test(oldId)){
             receiverSpan.innerHTML='';
	         return true;
           }{
              oldIdSpan.innerHTML='<span style="color:red;">不支持数字和符号</span>';
	         return false;
           } 
	        
	  }
	function checkpostcode(){
	       var postcodeSpan=document.getElementById("postcodeSpan");
	       var postcode=$("input[name='postcode']").val();
	      
                var ar = /^[0-9]{6}/;
           if(ar.test(postcode)){
             postcodeSpan.innerHTML='';
	         return true;
           }{
              postcodeSpan.innerHTML='<span style="color:red;">请输入正确的邮政编码</span>';
	         return false;
           } 
	        
	  }
	function checkaddress(){
	       var addressSpan=document.getElementById("addressSpan");
	       var address=$("input[name='address']").val();
	     
                var ar = /^[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*$/;
           if(ar.test(address)){
             addressSpan.innerHTML='';
	         return true;
           }{
              addressSpan.innerHTML='<span style="color:red;">请输入正确地址</span>';
	         return false;
           } 
	  }
	  function checkurgency(){
	       var urgency=document.getElementById("urgency");
	       if(urgency==''||urgency==null){
	            urgencySpan.innerHTML='<span style="color:red;">请选择紧急度</span>';
	           return false;
	       }{
	       	document.getElementById("urgencySpan").innerHTML='';
	       	return true;
	       }
	      
	  }
	  function checkpolicyType(){
	       var policyType=document.getElementById("policyType");
	       
	       if(policyType==''||policyType==null){
	            policyTypeSpan.innerHTML='<span style="color:red;">请选择政策类型</span>';
	           return false;
	       }{
	       	document.getElementById("policyTypeSpan").innerHTML='';
	       	return true;
	       }
	        
	  }
	function checkageType(){
	       var ageType=document.getElementById("ageType");
	     
	       if(ageType==''||ageType==null){
	            ageTypeSpan.innerHTML='<span style="color:red;">请选择乘客类型</span>';
	           return false;
	       }{
	       	document.getElementById("ageTypeSpan").innerHTML='';
	       	return true;
	       }
	        
	  }
	function checksegmentType(){
	       var segmentType=document.getElementById("segmentType");
	    
	       if(segmentType==''||segmentType==null){
	            segmentTypeSpan.innerHTML='<span style="color:red;">请选择航段类型</span>';
	           return false;
	       }{
	       	document.getElementById("segmentTypeSpan").innerHTML='';
	       	return true;
	       }
	     
	  }
	  
	  function cehckbxCount(){
	       var bxCount=document.getElementById("bxCount");
	       if(bxCount==''||bxCount==null){
	           bxCountSpan.innerHTML='<span style="color:red;">保险！！</span>';
	           return false;
	       }{
	       	document.getElementById("bxCountSpan").innerHTML='';
	       	return true;
	       }
	     
	  }
	  
	   function checkstatus(){
	       var status=document.getElementById("status");
	    
	       if(status==''||status==null){
	           statusSpan.innerHTML='<span style="color:red;">订单状态</span>';
	           return false;
	       }{
	       	document.getElementById("statusSpan").innerHTML='';
	       	return true;
	       }
	     
	  }
	  function checkcardExpired(){
	 
	  	 var cardExpiredSpan=document.getElementById("cardExpiredSpan");
	       var cardExpired=$("input[name='cardExpired']").val();
	       if(cardExpired==''||cardExpired==null){
	            cardExpiredSpan.innerHTML='<span style="color:red;">证件有效期不能为空！</span>';
	           return false;
	  }else{
             cardExpiredSpan.innerHTML='';
	         return true;
	  };
	  
  }
   function checketicketNum(ket){
     		if($(ket).val()==''||$(ket).val()==null){
     			$(ket).siblings('span').css("color", "red").text("票号不能够为空！");
	           return false;
	       }else {
	               var ai = /^[0-9]{0,20}$/;
		          if(ai.test($(ket).val())){
		             $(ket).siblings('span').text("");
			         return true;
		           }{
		               $(ket).siblings('span').css("color", "red").text("票号不合法");
			         return false;
		           } 
	   }
	  }
  
	  /* 价格 */
	   function caculate(){
	var adultPrice=Number($("#adultPrice").val());
	var adultTax=Number($("#adultTax").val());
	var childPrice=Number($("#childPrice").val());
	var childTax=Number($("#childTax").val());
	var aprice=(adultPrice+adultTax)+(childPrice+childTax);
	$("#aprice").val(aprice);
	$("#allprice").val(aprice);
	
}

  /* 增加 航段*/
	function flight(){
		var thenew= document.createElement('div');
		thenew.innerHTML = document.getElementById('div_flight').innerHTML;
		
		document.getElementById('bbbb').appendChild(thenew);
		generateflightNum();
	}	
	
	function generateflightNum(){
		var flightNum = 1;
		$("input[name='sequence']").each(function(){
		$(this).val(flightNum);
		flightNum++;
		});
		
	}
		/* 删除 航段*/
	function delflight(){
		document.getElementById('bbbb').removeChild(document.getElementById('bbbb').childNodes[0]);
			generateflightNum();
		}
		
	
	/*增加乘机人  */	
	function pass(){
		var thenew= document.createElement('div');
		thenew.innerHTML = document.getElementById('div_pass').innerHTML;
		
		document.getElementById('aaa').appendChild(thenew);
		
		generatePassNo();
		ageTypeChange();
		checkcardNum(this);
		checknationality(this);
		refresh();
		}
	function generatePassNo(){
		var passNo = 1;
		$("input[name='pindex']").each(function(){
			$(this).val(passNo);
			passNo++;
		});
		
	}
		/*删除乘机人  */
	function delpass(){
		document.getElementById('aaa').removeChild(document.getElementById('aaa').childNodes[0]);
			generatePassNo();
			ageTypeChange();
		}
		
		function ageTypeChange() {
		var adultsCount = 0;
		var childCount = 0;
		$("select[name='ageType']").each(function() {
			var select = $(this).val();
			if(select == "0"||select=="-1"){
				adultsCount ++;
			} else {
				childCount ++;
			}
		});
		$("#adultsCount").val(adultsCount);
		$("#childrenCount").val(childCount);
		
	}
	  
	   function tiJiao(){
	   
	   		var flag = true;
	   		var checkfn = function(data){$(data).each(function(){
	   			if(!checkNum(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkfn($("input[name='flightNum']"));
	   		
	   		
	   		var checkse = function(data){$(data).each(function(){
	   			if(!checkSequence(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkse($("input[name='sequence']"));
	   		
	   		var checka = function(data){$(data).each(function(){
	   			if(!checkAir(this)){
	   				flag = false;
	   			}
	   		});};
	   		checka($("input[name='carrier']"));
	   		var checkca = function(data){$(data).each(function(){
	   			if(!checkCabin(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkca($("input[name='cabin']"));
	   		var checkd = function(data){$(data).each(function(){
	   			if(!checkDepair(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkd($("input[name='depAircode']"));
	   		
	   		var checkp = function(data){$(data).each(function(){
	   			if(!checkPrice(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkp($("input[name='price']"));
	   		
	   		var checkdt = function(data){$(data).each(function(){
	   			if(!checkDepTime(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkdt($("input[name='departureTime']"));
	   		var checkart = function(data){$(data).each(function(){
	   			if(!checkarrivalTime(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkart($("input[name='arrivalTime']"));
	   		var checkai = function(data){$(data).each(function(){
	   			if(!checkarrAircode(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkai($("input[name='arrAircode']"));
	   	
	   		var checkme = function(data){$(data).each(function(){
	   			if(!checkName(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkme($("input[name='name']"));
	   		var checkcn = function(data){$(data).each(function(){
	   			if(!checkcardNum(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkcn($("input[name='cardNum']"));
	   		var checkna = function(data){$(data).each(function(){
	   			if(!checknationality(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkna($("input[name='nationality']"));
	   		var checkcip = function(data){$(data).each(function(){
	   			if(!checkcardIssuePlace(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkcip($("input[name='cardIssuePlace']"));
	   		var checkeid = function(data){$(data).each(function(){
	   			if(!checkextOrderID(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkeid($("input[name='extOrderID']"));
	   		var checkono = function(data){$(data).each(function(){
	   			if(!checkorderNo(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkono($("input[name='orderNo']"));
	   		var checkpc = function(data){$(data).each(function(){
	   			if(!checkpnrCode(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkpc($("input[name='pnrCode']"));
	   		var checkpcb = function(data){$(data).each(function(){
	   			if(!checkpnrCodeBig(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkpcb($("input[name='pnrCodeBig']"));
	   		var checkst = function(data){$(data).each(function(){
	   			if(!checkseats(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkst($("input[name='seats']"));
	   		var checkcc = function(data){$(data).each(function(){
	   			if(!checkchildrenCount(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkcc($("input[name='childrenCount']"));
	   		var checkadc = function(data){$(data).each(function(){
	   			if(!checkadultsCount(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkadc($("input[name='adultsCount']"));
	   		var checkof = function(data){$(data).each(function(){
	   			if(!checkpnrOffNo(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkof($("input[name='pnrOffNo']"));
	   		var checkrof = function(data){$(data).each(function(){
	   			if(!checkrtOffno(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkrof($("input[name='rtOffno']"));
	   		var checkps = function(data){$(data).each(function(){
	   			if(!checkproductSource(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkps($("input[name='productSource']"));
	   		var checkpid = function(data){$(data).each(function(){
	   			if(!checkpolicyId(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkpid($("input[name='policyId']"));
	   		var checkccn = function(data){$(data).each(function(){
	   			if(!checkcontactName(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkccn($("input[name='contactName']"));
	   		var checkctm = function(data){$(data).each(function(){
	   			if(!checkcontactMob(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkctm($("input[name='contactMob']"));
	   		var checkre = function(data){$(data).each(function(){ 
	   		});};
	   		checkre($("input[name='receiver']"));
	   		var checkpst = function(data){$(data).each(function(){
	   		
	   		});};
	   		checkpst($("input[name='postcode']"));
	   		var checkdd = function(data){$(data).each(function(){
	   		});};
	   		checkdd($("input[name='address']"));
	   		var checkur = function(data){$(data).each(function(){
	   			if(!checkurgency(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkur($("input[name='urgency']"));
	   		var checkpt = function(data){$(data).each(function(){
	   			if(!checkpolicyType(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkpt($("input[name='policyType']"));
	   		var checkat = function(data){$(data).each(function(){
	   			if(!checkageType(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkat($("input[name='ageType']"));
	   		var checkset = function(data){$(data).each(function(){
	   			if(!checksegmentType(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkset($("input[name='segmentType']"));
	   		
	   		var checkbx = function(data){$(data).each(function(){
	   			if(!cehckbxCount(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checkbx($("input[name='bxCount']"));
	   		
	   		var checksta = function(data){$(data).each(function(){
	   			if(!checkstatus(this)){
	   				flag = false;
	   			}
				 
	   		});};
	   		checksta($("input[name='status']"));
	   		var checkos = function (data){$(data).each(function(){
	   			if(!checkcost(this)){
	   				flag = false;
	   			}
	   		});};
	   		checkos($("input[name='cost']"));
	   		var checkred = function (data){$(data).each(function(){
				if(!checkcardExpired(this)){
					flag= false;
				}	
	   		});};
	   		checkred($("input[name='cardExpired']"));
	   		var checket = function(data){$(data).each(function(){
	   			if(!checketicketNum(this)){
	   			flag = false;
	   			};
	   		});};
	      	checket($("input[name='eticketNum']"));
	     if(flag){
	            $('#myform').submit();
	           
	     } else{
	     
	     alert("请检查页面上所有选项是否正确填写!");
	        return ;
	     }
	     
	
	  }
	
</script>
</html>