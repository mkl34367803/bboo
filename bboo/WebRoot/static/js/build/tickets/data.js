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
		navigationAsDateFormat : false,
		yearSuffix : '年'
	};
	$.datepicker.setDefaults($.datepicker.regional['zh-CH']);
}

$(document).ready(function() {
	if ($('#page_name').val() == 'imp_tickets') {
		console.log("fffff");
		setDatepickerChinese();
		$('#impdate').datepicker({
			maxDate : 90,
			onClose : function(selectedDate) {
				// $('#impdate').datepicker('option', 'minDate', selectedDate);
			}
		}).keypress(function(e) {
			if (e.which == 13) {
				$('#fuzzy_filter_btn').click();
			}
		});
	}
});
