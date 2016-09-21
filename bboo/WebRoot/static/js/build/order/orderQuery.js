$(document).ready(function(){
	function formatMD(d){
		if(d < 10){
			return "0"+d;
		}else{
			return d;
		}
	}
	// 格式化日期, d为默认日期, next为相差天数
	function formatDate(d, next) {
		if (d) {
			if (next) {
				d = new Date(d.getTime() + next * 24 * 60 * 60 * 1000);
			}
			return d.getFullYear() + "-" + formatMD(d.getMonth() + 1) + "-"+ formatMD(d.getDate());
		}
	}

	// 初始化日期
	window.SERVICE_TIME = new Date();
	var defaultTime = window.SERVICE_TIME;
	curDate = formatDate(defaultTime, 0);
	nextDate = formatDate(defaultTime, -7);

	$("input[name='startDate']").val(nextDate);
	$("input[name='endDate']").val(curDate);
	
	// 出发时间，到达时间控件
	var startDate = {
		elem: '#startDate',
		format: 'YYYY-MM-DD',
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			endDate.min = datas;
			endDate.start = datas;
		}
	};
	var endDate = {
		elem: '#endDate',
		format: 'YYYY-MM-DD',
		min: laydate.now(-7),
		istoday: true,
		istime: false,
		isclear: false,
		choose: function(datas){
			startDate.max = datas;
		}
	};
	laydate(startDate);
	laydate(endDate);
});