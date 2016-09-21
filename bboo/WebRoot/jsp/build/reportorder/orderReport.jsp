<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<%@ taglib prefix="t" uri="/WEB-INF/tlds/common.tld" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售利润报表</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<script type="text/javascript">

$(document).ready(function() {
	
	var createStart = {
		elem: '#createStart',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			createEnd.min = datas;
			createEnd.start = datas;
		}
	};
	var createEnd = {
		elem: '#createEnd',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			createStart.max = datas;
		}
	};
	laydate(createStart);
	laydate(createEnd);
	
	var ticketStart = {
		elem: '#ticketStart',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			ticketEnd.min = datas;
			ticketEnd.start = datas;
		}
	};
	var ticketEnd = {
		elem: '#ticketEnd',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			ticketStart.max = datas;
		}
	};
	laydate(ticketStart);
	laydate(ticketEnd);


	var getDate = function() {
		var data = {};
		var createStart = $("input[name='createStart']").val();
		var createEnd = $("input[name='createEnd']").val();
		var ticketStart = $("input[name='ticketStart']").val();
		var ticketEnd = $("input[name='ticketEnd']").val();
		var departureStart = $("input[name='departureStart']").val();
		var departureEnd = $("input[name='departureEnd']").val();
		var distributor = $("select[name='distributor']").val();
		data.createStart = $.trim(createStart);
		data.createEnd = $.trim(createEnd);
		data.ticketStart = $.trim(ticketStart);
		data.ticketEnd = $.trim(ticketEnd);
		data.departureStart = $.trim(departureStart);
		data.departureEnd = $.trim(departureEnd);
		data.distributor = $.trim(distributor);
		
		var checkCreate = data.createStart != "" && data.createEnd != "" ? true : false;
		var checkTicket = data.ticketStart != "" && data.ticketEnd != "" ? true : false;
		if(!(checkCreate || checkTicket)) {
			alert("请输入创单日期或出票日期");
			return null;
		}
		return data;
	}

	//下载
	$("input[name='downloadBtn']").click(function() {
		var data = getDate();
		if(data == null) {
			return ;
		}
		$(this).val('下载中');
		$.post(
			"${ctx }/report/order-report!downloadExcek.do",data,function(data) {
				var result = eval("("+data+")");
				if (result.error) {
					$("input[name='downloadBtn']").val('下载');
					alert(result.error);
				}
				if (result.zipFilePath) {
				var content = "<a href='${ctx }/"+ result.zipFilePath
					+ "' name='report_forms'  style='font-size:18px; font-weight:bold; color:red;'>"
					+ "点击这下载./" + result.zipFilePath + "</a>";
					layer.open({
						title : false,
						closeBtn : 0,
						btn : [ '关闭' ],
						content : "<div style='width:90%;height:auto; margin:0 auto;'>"
							+ content
							+ "</div>",
							yes : function(index,layero) {
								$("input[name='downloadBtn']").val('下载');
								layer.close(index);
							}
					},'text');
				}
			}
		);
	});
});

</script>
</head>
<body>
	<div class="mod-hd" style="margin-top: 10px">
		<h2>销售利润报表下载</h2>
	</div>
	<!--条件查询   -->
	<form>
		<table class="m-search g-clear g-table" style="width: 100%">
			<tr class="rows">
				<td class="col">
					<span class="label">销售日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="createStart" id="createStart" type="text" class="cinput" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="createEnd" id="createEnd" type="text" class="cinput" >
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">创单日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="ticketStart" id="ticketStart" type="text" class="cinput" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="ticketEnd" id="ticketEnd" type="text" class="cinput" >
						</div>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col">
					<span class="label">起飞日期</span>
					<div class="label-info">
						<div class="qcbox start-date">
							<input name="departureStart" type="text" class="cinput" onclick="laydate();" >
						</div>
						<span class="split">到</span>
						<div class="qcbox stop-date">
							<input name="departureEnd" type="text" class="cinput" onclick="laydate();" >
						</div>
					</div>
				</td>
				<td class="col">
					<span class="label">分销商铺</span>
					<div class="label-info">
						<!-- <span class="g-ipt-text"> <input name="caiGouProjo" type="text" > </span> -->
						<span class="g-select"> 
							<select name="distributor">
								<option value="">全部</option>
								<option value="T">淘宝</option>
								<option value="Q">去哪儿</option>
								<option value="C">携程</option>
								<option value="H">航班管家</option>
								<option value="K">酷讯</option>
								<option value="N">途牛</option>
								<option value="O">同程</option>
							</select> 
						</span>
					</div>
				</td>
			</tr>
			<tr class="rows">
				<td class="col" colspan="3" style="width: 800px;">
					<span class="label">&nbsp;</span>
					<div class="label-info">
						<!-- <input type="button" name="downloadBtn" class="g-btn" value="下载" /> -->
						<f:function value="GNDOWNSALEREPORT">
							<input type="button" name="downloadBtn" class="g-btn" value="国内下载" />
						</f:function>
						<f:function value="GJDOWNSALEREPORT">
							<input type="button" name="gjDownloadBtn" class="g-btn" value="国际下载" />
						</f:function>
					</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>