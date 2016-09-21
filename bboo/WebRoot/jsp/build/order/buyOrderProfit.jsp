<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="/jsp/share/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'buyOrderProfit.jsp' starting page</title>
	<%@include file="/jsp/share/JS.jsp"%>
	<%@include file="/jsp/share/CSS.jsp"%>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	$(function(){
		init();
	});
	function init(){
		$.ajax({
			type:"post",
			dataType: "json",
			url:"${ctx }/order/gj-order-detail!getSaleOrderProfit.do",
			success:function(result){
				if(result.success){
					highCharts(result.buyOrderProfits);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("请求出错了");
			}
		});
	}
	function highCharts(data){
		var categories=[];
		var series=[];
		$.each(data,function(i,item){
			categories[i]=item.purchasePlace;
			series[i]=item.lr;
		});
		$("#container").highcharts({
	        title: {
	            text: 'Monthly Average Temperature',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Source: WorldClimate.com',
	            x: -20
	        },
	        xAxis: {
	            categories: categories
	        },
	        yAxis: {
	            title: {
	                text: '利润'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: 'RMB'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '莫林航空服务',
	            data: series
	        }]
    	});
	}
	</script>
  </head>
  
  <body>
     <div id="container" style="min-width:700px;height:400px"></div>
  </body>
</html>
