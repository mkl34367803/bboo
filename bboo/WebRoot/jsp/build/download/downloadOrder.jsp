<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="/jsp/share/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderdownload.jsp' starting page</title>
	<%@include file="/jsp/share/JS.jsp"%>
	<%@include file="/jsp/share/CSS.jsp"%>
    
  </head>
  
  <body>
  <div style="margin-top: 10px; height: auto;width: 1070px;float: left;">
  	<div class="mod-hd" style="margin-top: 10px">
		<h2>销售利润报表下载</h2>
	</div>
	<div>&nbsp;</div>
   <form action="${ctx }/download/down-load-order!queryOrderDownLoad.do" name="from" method="post">
   	<div>
		<table>
		
			<tr>
		<td class="col">出票日期：
			<input  name="ticketDate" vlaue="" type="text" style="width: 150px;height: 25px;">
		</td>
		
		
		</tr>
		</table>
		</div>
		<div>
		<br>
		<table>
		<tr>
		
		<td >导单日期：
			<input  name="addTime" vlaue="" type="text" style="width: 150px;height: 25px;">
		</td>
		
		
		</tr>
		</table>
		</div>
		<br>
		<div>
		<table>
		
		<tr>
		
	<td>创单日期：
			<input name="createTime" vlaue="" type="text" style="width: 150px;height: 25px;">
		</td>
		</tr>
		</table>
		</div>
		<br>
		<div>
		<table>
		<tr class="rows">
		
				<td class="col">&nbsp;&nbsp;&nbsp;商铺名：
							<select name="distributor" style="width: 150px;height: 25px;">
									<option value="">全部</option>
									<option value="T">淘宝</option>
									<option value="Q">去哪儿</option>
									<option value="C">携程</option>
									<option value="H">航班管家</option>
									<option value="K">酷讯</option>
									<option value="N">途牛</option>
									<option value="O">同程</option>
								
							</select> 
				</td>
				
			</tr>
			</table>
			</div>
			<div style="float: right;">
			<table>
			<tr class="rows">
				<td class="col">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<input type="button" name="queryorder" class="g-btn" value="查询" />
						<input type="button" name="J_Exports" class="g-btn" value="下载">
					</div>
				</td>
			</tr>
		</table>
	</div>
   </form>
   <div>
   <table class="g-table table-list">
			<thead class="table-hd ">
				<tr>
					<th width="105px">订单号</th>
					<th class="col04">航班号</th>
					<th class="col08">订单日期</th>
					<th class="col05">分销号</th>
					<th class="col04">始发地</th>
					<th class="col04">目的地</th>
					<th class="col05">PNR</th>
					<th class="col01">人数</th>
					<th class="col02">成交价</th>
					<th class="col02">订单状态</th>
					<th class="col04">锁定人</th>
				</tr>
				<c:forEach items="${list }" var="der">
				<tr>
					<td>${der.orderNo} </td>
					<td>${der.flightNum }</td>
					<td>${der.addTime }</td>
					<td>${der.depAircode }</td>
					<td>${eder.arrAircode }</td>
					<td>${der.pnr }</td>
					<td>${der.passengerCount }</td>
					<td>${der.allprice }</td>
					<td>${der.status }</td>
					<td>${der.lockUser }</td>
					<td></td>
				</tr>
				</c:forEach>
			</thead> 
		</table>
		
   </div>
   </div>
  </body>
  <script type="text/javascript">
  $(function(){
  		$(":input[name='queryorder']").click(function(){
  			document.from.submit();
  		});
  		$(":input[name='J_Exports']").click(function(){
  			var path = $("#path").val();
  			var ticketDate = $(":input[name='ticketDate']").val();
  			var addTime = $(":input[name='addTime']").val();
  			var createTime = $(":input[name='createTime']").val();
  			var distributor = $(":input[name='distributor']").val();
  			//var orderNo = $(":input[name='orderNo']").val();
  			
  			var url=path="${ctx }/download/down-load-order!DownLoadFile.do";
  			location.href=url+"?ticketDate"+ticketDate,"addTime"+addTime,
  			"createTime"+createTime,"distributor"+distributor;
  		});
  		
  			$(":input[name='closeAll']").click(function(){
		layer.closeAll();
	});
	$(":input[name='Parent_closeAll']").click(function(){
		parent.layer.closeAll();
	});
	$(".tite").hover(function(event){
		var $p=$(this).find("div p");
		if($p.length>0){
			layer.tips($(this).find("div p").text(),$(this),
			{guide:0,
				maxWidth:505,
				style:[
					"background:#78BA32;color:#fff;word-wrap:break-word;margin-bottom: -10px;",
					"#78BA32"]} 
			);
		}
	},function(env){
		layer.closeTips();
	});
  });
  </script>
</html>

