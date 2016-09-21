function setDatepickerChinese(){
        $.datepicker.regional['zh-CH'] = {
            closeText: '关闭',
            prevText: '&#x3c;上月',
            nextText: '下月&#x3e;',
            currentText: '今天',
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','>十一月','十二月'],
            monthNamesShort: ['一','二','三','四','五','六','七','八','九','十','十一','十二'],
            dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
            dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
            dayNamesMin: ['日','一','二','三','四','五','六'],
            weekHeader: '周',
            dateFormat: 'yy-mm-dd',
            firstDay: 1,
            isRTL: false,
            showMonthAfterYear: true,
            yearSuffix: '年'
        };
        $.datepicker.setDefaults($.datepicker.regional['zh-CH']);
}


$(document).ready(function(){
if($('#page_name').val() == 'addmobileorder'){
        console.log("fffff");
        setDatepickerChinese();
        $('#query_start_time').datepicker({
            maxDate : 90,
            onClose : function( selectedDate ){
                $('#query_end_time').datepicker( 'option', 'minDate', selectedDate );
                //setFilterCondition($('#query_start_time'), true);
            }
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        });
        var register_check_list = [
            {
                '$input' : $('#dpt'),
                'name' : '出发3字码',
                'check_type' : ['empty']
            },{
                '$input' : $('#arr'),
                'name' : '到达3字码',
                'check_type' : ['empty']
            },{
                '$input' : $('#flight_no'),
                'name' : '航班号不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#query_start_date'),
                'name' : '出发日期不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#psg_l_name'),
                'name' : '姓不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#psg_f_name'),
                'name' : '名不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#psg_id'),
                'name' : '证件号码不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#psg_tel'),
                'name' : '旅客电话格式错',
                'check_type' : ['phone']
            }
        ];

        function registerFormSubmit(){
            showLoading('正在提交注册信息');
            $('#register_form').submit();
        }
        setCheck(register_check_list, checkShowTips, $('#register_submit'), registerFormSubmit, 'OK');
}
});
