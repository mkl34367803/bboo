function setDatepickerChinese() {
	$.datepicker.regional['zh-CH'] = {
		closeText : '关闭',
		prevText : '&#x3c;上月',
		nextText : '下月&#x3e;',
		// currentText : '今天',
		// clearText : '清除',// 清除日期的按钮名称
		monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
				'十月', '>十一月', '十二月' ],
		monthNamesShort : [ '一', '二', '三', '四', '五', '六', '七', '八', '九', '十',
				'十一', '十二' ],
		dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
		dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		weekHeader : '周',
		dateFormat : 'yy-mm-dd',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : true,
		changeMonth : true,
		changeYear : false,
		showButtonPanel : true,
		yearSuffix : '年'
	};
	$.datepicker.setDefaults($.datepicker.regional['zh-CH']);
}

$(document).ready(
		function() {
			if ($('#page_name').val() == 'add_qunar_jinjia_add_rule') {
				console.log("fffff");
				setDatepickerChinese();
				$('#startDate').datepicker(
						{
							maxDate : 365,
							onClose : function(selectedDate) {
								$('#startDate').datepicker('option', 'minDate',
										selectedDate);
							}
						}).keypress(function(e) {
					if (e.which == 13) {
						$('#fuzzy_filter_btn').click();
					}
				});
				$('#endDate').datepicker(
						{
							maxDate : 365,
							onClose : function(selectedDate) {
								$('#endDate').datepicker('option', 'minDate',
										selectedDate);
							}
						}).keypress(function(e) {
					if (e.which == 13) {
						$('#fuzzy_filter_btn').click();
					}
				});
				var register_check_list = [ {
					'$input' : $('#agentDomain'),
					'name' : '去哪儿域名，如xkw.trade.qunar.com',
					'check_type' : [ 'empty' ]
				}, {
					'$input' : $('#airLine'),
					'name' : '航司二字代码，如111,CA,CZ,ZH',
					'check_type' : [ 'empty' ]
				}, {
					'$input' : $('#cabinCode'),
					'name' : '适用舱位，如111,Y,C,D',
					'check_type' : [ 'empty' ]
				}, {
					'$input' : $('#savePoint'),
					'name' : '留点，如-1.2',
					'check_type' : [ 'num|2', 'range|-3' ]
				}, {
					'$input' : $('#savePrice'),
					'name' : '留钱，如-5',
					'check_type' : [ 'num|2', 'range|-50' ]
				}, {
					'$input' : $('#extraPoint'),
					'name' : '留点，如-1.1',
					'check_type' : [ 'num|2', 'range|-3' ]
				}, {
					'$input' : $('#extraPrice'),
					'name' : '留钱，如-5',
					'check_type' : [ 'num|2', 'range|-50' ]
				}, {
					'$input' : $('#quotaPoint'),
					'name' : '固定返点，如0',
					'check_type' : [ 'num|2', 'range|-0.01' ]
				} ];
				function formSubmit() {
					showLoading('正在努力保存数据');
					showAlert("确认要添加当前询价规则吗？", 'confirm', function() {
						subbtn();
					});
					hideLoading('');
				}
				setCheck(register_check_list, checkShowTips,
						$('#add_qunar_jinjia_add_rule_sub'), formSubmit, 'OK');
			}
		});

function subbtn() {

	var isShare = $("#isShare").val();
	var agentDomain = $("#agentDomain").val();
	var airLine = $("#airLine").val();
	var cabinCode = $("#cabinCode").val();
	var savePoint = $("#savePoint").val();
	var savePrice = $("#savePrice").val();

	var extraPoint = $("#extraPoint").val();
	var extraPrice = $("#extraPrice").val();
	var quotaPoint = $("#quotaPoint").val();
	var depCode = $("#depCode").val();
	var arrCode = $("#arrCode").val();
	var notline = $("#notline").val();
	var notcity = $("#notcity").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var startPrice = $("#startPrice").val();

	var endPrice = $("#endPrice").val();
	var remark = $("#remark").val();

	$("#register_submit").val("正在努力保存...");
	$.post("./rules/quote-rule!savePage.do?t=" + Math.random(), {
		agentDomain : agentDomain,
		airLine : airLine,
		cabinCode : cabinCode,
		savePoint : savePoint,
		savePrice : savePrice,
		extraPoint : extraPoint,
		extraPrice : extraPrice,
		quotaPoint : quotaPoint,
		depCode : depCode,
		arrCode : arrCode,
		notline : notline,
		notcity : notcity,
		startDate : startDate,
		endDate : endDate,
		startPrice : startPrice,
		endPrice : endPrice,
		remark : remark,
		isShare : isShare
	}, function(data) {
		showAlert(data);
	});
}
