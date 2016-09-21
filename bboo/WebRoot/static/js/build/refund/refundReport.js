function getDate() {
	var data = {};
	var refundStart = $("input[name='ticketStart']").val();
	var refundEnd = $("input[name='ticketEnd']").val();
	var departureStart = $("input[name='departureStart']").val();
	var departureEnd = $("input[name='departureEnd']").val();
	var distributor = $("select[name='distributor']").val();
	data.refundStart = $.trim(refundStart);
	data.refundEnd = $.trim(refundEnd);
	data.departureStart = $.trim(departureStart);
	data.departureEnd = $.trim(departureEnd);
	data.distributor = $.trim(distributor);
	
	var checkTicket = data.refundStart != "" && data.refundEnd != "" ? true : false;
	var checkCreate = data.departureStart != "" && data.departureEnd != "" ? true : false;
	if(!(checkCreate || checkTicket)) {
		alert("退票日期或起飞日期必填一个");
		return null;
	}
	return data;
}

function downloadExcel(data) {
	var ind = layer.open({
		type: 3,
		icon: 1
	});
	$.post(
			ctx+"/refund/refund!downloadRefund.do",data,function(data) {
			var result = eval("("+data+")");
			layer.close(ind);
			if (result.error) {
				alert(result.error);
			}
			if (result.zipFilePath) {
			var content = "<a href='"+ctx+"/"+ result.zipFilePath
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
							layer.close(index);
						}
				},'text');
			}
		}
	);
}

$(document).ready(function() {
	
	var departureStart = {
		elem: '#departureStart',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			createEnd.min = datas;
			createEnd.start = datas;
		}
	};
	var departureEnd = {
		elem: '#departureEnd',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			createStart.max = datas;
		}
	};
	laydate(departureStart);
	laydate(departureEnd);
	
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



	//下载
	$("input[name='refundBtn']").click(function() {
		var data = getDate();
		data.operate = "refund";
		if(data == null) {
			return ;
		}
		downloadExcel(data);
	});
	
	$("input[name='reserveBtn']").click(function() {
		var data = getDate();
		data.operate = "reserve";
		if(data == null) {
			return ;
		}
		downloadExcel(data);
	});
});