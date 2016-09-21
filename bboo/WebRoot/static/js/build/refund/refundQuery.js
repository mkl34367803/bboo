//这里开始分页查询订单数据
function queryOrderSummary(data){
		//从浏览器地址获取flightClass参数
		data.flightClass=flightClass;
		var contactMob =  $.trim($("input[name='contactMob']").val()), 
			orderNo =  $.trim($("input[name='orderNo']").val()), 
			passengerName =  $.trim($("input[name='passengerName']").val()), 
			ticketNum =  $.trim($("input[name='ticketNum']").val()),
			shopNameCh =  $.trim($("input[name='shopNameCh']").val()),
			pnrCode =  $.trim($("input[name='pnrCode']").val());
				
		data.pnrCode=pnrCode;
		data.orderNo=orderNo;
		if (orderNo == "" && passengerName == "" && ticketNum == "" && pnrCode == "") {
			alert("订单号、票号、PNR和乘机人姓名，至少要填一个");
			return ;
		}
		data.contactMob=contactMob;
		data.passengerName=passengerName;
		data.ticketNum=ticketNum;
		
		data.shopNameCh=shopNameCh;
		
		var url = ctx +"/order/gj-order-summary!queryOrderByPage.do";
		$.ajax({
			url: url,
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
						writeBody(result.list);
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

function writeBody(data) {
	$.each(data,function(i,item){
		var img_tag="";
		if(item.distributor=="Q"){  //去哪儿的图标
			img_tag="<img src='"+ctx +"/static/img/build/qunaer.png'/>";
		}else if(item.distributor=="C"){  //携程的图标
			img_tag="<img src='"+ctx +"/static/img/build/trip.png'/>";
		}else if(item.distributor=="T"){  //淘宝的图标
			img_tag="<img src='"+ctx +"/static/img/build/taob.png'/>";
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
		var createTimes=item.createTime.split(" ");
		var td_createTime="<td>"+createTimes[0]+"<br/>"+createTimes[1]+"</td>"; //订单时间
		var td_flightType="<td>"+getFlightTypeCh(item.flightType)+"</td>";  //航程类型：单程、往返
		var td_dep_arr="";
		var td_flightDate_flightNo="";  //用来存储航班日期，航班号
		if(item.flights){
			$.each(item.flights,function(j,flight){
				var each_departureTime=flight.departureTime;
				var each_depAircode=flight.depAircode;
				var each_arrivalTime=flight.arrivalTime;
				var each_arrAircode=flight.arrAircode;
				var each_dep_arr=each_departureTime+" "+each_depAircode+"<br/>"+each_arrivalTime+" "+each_arrAircode;
				if(j<item.flights.length-1){
					each_dep_arr=each_dep_arr+"<hr style='height:1px;border:none;border-top:1px solid #555555;'/>";
				}
				td_dep_arr=td_dep_arr+each_dep_arr;
				
				var flightDate_flightNo=flight.departureDate+"<hr style='height:1px;border:none;border-top:1px dashed #0066CC;'/>"; //航班日期
				flightDate_flightNo=flightDate_flightNo+flight.flightNum+"("+flight.cabin+")"; //航班号+仓位
				if(j<item.flights.length-1){
					flightDate_flightNo=flightDate_flightNo+"<hr style='height:1px;border:none;border-top:1px solid #555555;'/>";
				}
				td_flightDate_flightNo=td_flightDate_flightNo+flightDate_flightNo;
			});
		}
		td_dep_arr="<td>"+td_dep_arr+"</td>";
		td_flightDate_flightNo="<td>"+td_flightDate_flightNo+"</td>";
		var td_pnrCode="<td>"+item.pnrCode+"</td>"; //pnr
		
		var td_allprice="<td>"+item.allprice+"/"; //订单总价
		var td_passengerCount=""+item.passengerCount+"</td>"; //乘客总人数
		var td_slfStatus="<td>"+getSlfStatusCH(item.slfStatus)+"</td>"; //订单状态//这个字段暂时不确定
		
		var td_remark = "<td>"+item.remark+"</td>"; //服务说明
		var var_tr=td_orderNo+td_createTime+td_flightType+td_dep_arr+td_flightDate_flightNo+td_pnrCode+td_allprice+td_passengerCount+td_slfStatus+td_remark;
		var var_a="";
								
		var_a += "<a href='"+ctx +"/order/gj-order-detail!showOrderDetail.do?id="+item.id+"&operate=refundQuery&flightClass="+flightClass+"'>查看</a>";
		var_a=var_a+"<br/>"+"<a href='"+ctx +"/refund/refund!queryOrderByID.do?id="+item.id+"'>退票</a>";
		
		var_tr="<tr>"+var_tr+"<td class='operate' >"+var_a+"</td></tr>";
		$("#table-bd").append(var_tr);
	});
}

function getFlightTypeCh(flightType) {
	if(flightType=="S"){
		return "单程";
	}else if(flightType=="D"){
		return "往返";
	}else if(flightType=="T"){
		return "其它";
	}else if(flightType=="L"){
		return "联程";
	}else if(flightType=="X"){
		return "多程";
	}
	return flightType;
}

//解除锁定和强制解锁
function updateLocker(message, brid) {
	if (confirm(message)) {
		$.ajax({
			type : "post",
			dataType : "json",
			data : {
				id : brid,
				lockUser : ''
			},
			url : ctx +"/refund/base-refund!updateLocker.do",
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

function queryButton(){
	var page=$("#page").text();
	var data={
		"page":page
	};
	queryOrderSummary(data);
}

// 获取flightClass中文
function getFC(flightClass) {
		if(getParamValue("flightClass")=="I"){
			return "国际";			
		} else if(getParamValue("flightClass")=="N"){
			return "国内";
		}
		return "";
	}

$(document).ready(function(){
	
	$("#J_Btn").click(function(){
		var page="1";  //查询还是要从第一页开始
		var data={
			"page":page
		};
		queryOrderSummary(data);
	});
	
	$(".mod-msg").html("<h2>"+getFC(flightClass)+"退票查询</h2>");
	
});