var cacheData;
function getOfficeList(page) {
	var data = {};
	var searchID = $("#searchID").val();
	var searchOffice = $("#searchOffice").val();
	var searchOfftypes = $("#searchOfftypes").val();
	var searchAppkey = $("#searchAppkey").val();
	var searchIsu = $("#searchIsu").val();
	data.searchID=searchID;
	data.searchOffice=searchOffice;
	data.searchOfftypes=searchOfftypes;
	data.searchAppkey=searchAppkey;
	data.searchIsu=searchIsu;
	data.page = page; // 分页查询
	data.rows = 5; // 每页展示多少条记录
	$.ajax({
				type : "post",
				dataType : "json",
				data : data,
				url : ctx + "/office/base-office!queryBaseOffice.do",
				success : function(result) {
					if (result.error) {
						alert(result.error);
					}
					if (result.success) {
						if (result.rows) {
							addTbodyData(result.rows);
						}
						if(result.total==0){
							pageNav.go(page,Math.ceil(1) );
						}else if(result.total){
							pageNav.go(page,Math.ceil(result.total/data.rows) );
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("请求出错了");
				}
			});
}

function addTbodyData(data) {
	$("#table-bd").html("");
	cacheData = data; // 页面上缓存数据。
	$.each(data, function(i, item) {
				var var_isu="";
				if(item.isu==1){
					var_isu="启用";
				}else if(item.isu==2){
					var_isu="禁用";
				}
				var var_tr = "<td>" + item.id + "</td>";
				var_tr += "<td>" + item.office + "</td>";
				var_tr += "<td>" + item.offtypes + "</td>";
				var_tr += "<td>" + item.etdzNum + "</td>";
				//var_tr += "<td>" + item.appkey + "</td>";
				
				var_tr += "<td>" +var_isu + "</td>";
				var_tr += "<td>" + item.ctime + "</td>";
				// 拼接json字符串
				var_tr += "<td><a href='javascript:modifyOffice("+item.id+")'>修改</a>&nbsp;&nbsp;<a href='javascript:deleteOffice("+item.id+")'>删除</a></td>";
				var_tr = "<tr>" + var_tr + "</tr>";
				$("#table-bd").append(var_tr);
			});
	$(".table-bd tr:even").addClass("even");
	$(".table-bd tr:odd").addClass("odd");
}

function openDialog(){
	$("#light").css('display','block');  
	$("#fade").css('display','block');  
}
function closeDialog(){
	$("#light").css('display','none');  
	$("#fade").css('display','none');  
}
function addOffice(){
	openDialog();
}
function modifyOffice(id) {
	// alert(data.id);
	// window.location.href=ctx + "/jsp/build/office/modifyOffice.jsp";
	$.each(cacheData,function(i,item){
		if(item.id==id){
			$("#id").val(item.id);
			$("#office").val(item.office);
			$("#offtypes").val(item.offtypes);
			$("#appkey").val(item.appkey);
			$("#etdzNum").val(item.etdzNum);
			$("#isu").val(item.isu);
		}
	});
	openDialog();
}

function deleteOffice(id) {
	$.ajax({
		type : "post",
		dataType : "json",
		data : {id:id},
		url : ctx + "/office/base-office!deleteOfficeById.do",
		success : function(result) {
			if (result.error) {
				alert(result.error);
			}
			if (result.success) {
				alert(result.success);
				var page=$("#page").html();
				getOfficeList(page);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求出错了");
		}
	});
}

function reset(){
$("#id").val("");
$("#office").val("");
$("#offtypes").val("");
$("#appkey").val("");
$("#etdzNum").val("");
$("#isu").val("");
}

function checkIsNum(num) {
	if (!isNaN(num)) {
		return true;
	} else {
		return false;
	}
}