//判断浏览器start
var ie6 = $.browser.msie && $.browser.version.substr(0,1) == '6';
var ie7 = $.browser.msie && $.browser.version.substr(0,1) == '7';
var ie8 = $.browser.msie && $.browser.version.substr(0,1) == '8';
var ie9 = $.browser.msie && $.browser.version.substr(0,1) == '9';
var ff = $.browser.mozilla;
var chrome = navigator.userAgent.toLowerCase().match(/chrome\/([\d.]+)/);
var safari = navigator.userAgent.toLowerCase().match(/safari\/([\d.]+)/);
var opera = navigator.userAgent.toLowerCase().match(/opera\/([\d.]+)/);
//判断浏览器end

$(document).ready(function(){
    
    //把footer定位到页面最下方start
    footerToBottom();
    $(window).resize(function(){
        footerToBottom();
    });
    function footerToBottom(){
        var header_padding_total = parseInt($('#header').css('paddingTop')) + parseInt($('#header').css('paddingBottom'));
        var footer_padding_total = parseInt($('#footer').css('paddingTop')) + parseInt($('#footer').css('paddingBottom'));
        var min_height = getWindowHeight() - $('#header').height() - header_padding_total - $('#footer').height() - footer_padding_total - (ie7 ? 1 : 0);
        var content_padding_total = parseInt($('#content').css('paddingTop')) + parseInt($('#content').css('paddingBottom'));
        if($('#content').height() + content_padding_total < min_height){
            $('#wrap').height(min_height);
        }else{
            $('#wrap').height('auto');
        }
    }
    //把footer定位到页面最下方end
    
    //header start
    
    //保证金金额start
    $('#user_amount').text(setAmountFormat($('#user_amount').text()));
    $('#user_amount_steel').text(setAmountFormat($('#user_amount_steel').text()));

    $('#del_all_pt_zc').click(function(){
	showAlert("确认要删除所有VARK上传的平台数据吗？", 'confirm', function(){
               window.location.href = '/delzc';
                        });
    });
    $('#del_all_gw_zc').click(function(){
	showAlert("确认要删除所有VARK上传东航官网的数据吗？", 'confirm', function(){
               window.location.href = '/delgwzc';
                        });
    });
    $('#del_all_ca_zc').click(function(){
	showAlert("确认要删除所有VARK上传国航官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=CA';
                        });
    });
    $('#del_all_tv_zc').click(function(){
	showAlert("确认要删除所有VARK上传TV官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=TV';
                        });
    });
    $('#del_all_3u_zc').click(function(){
	showAlert("确认要删除所有VARK上传3U官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=3U';
                        });
    });
    $('#del_all_ho_zc').click(function(){
	showAlert("确认要删除所有VARK上传HO官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=HO';
                        });
    });
    $('#del_all_ns_zc').click(function(){
	showAlert("确认要删除所有VARK上传NS官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=NS';
                        });
    });
    $('#del_all_8l_zc').click(function(){
	showAlert("确认要删除所有VARK上传8L官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=8L';
                        });
    });
    $('#del_all_gs_zc').click(function(){
	showAlert("确认要删除所有VARK上传GS官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=GS';
                        });
    });
    $('#del_all_pn_zc').click(function(){
	showAlert("确认要删除所有VARK上传PN官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=PN';
                        });
    });
    $('#del_all_ky_zc').click(function(){
	showAlert("确认要删除所有VARK上传KY官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=KY';
                        });
    });
    $('#del_all_sc_zc').click(function(){
	showAlert("确认要删除所有VARK上传SC官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=SC';
                        });
    });
    $('#del_all_g5_zc').click(function(){
	showAlert("确认要删除所有VARK上传G5官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=G5';
                        });
    });
    $('#del_all_zh_zc').click(function(){
	showAlert("确认要删除所有VARK上传ZH官网的数据吗？", 'confirm', function(){
               window.location.href = '/delcazc?code=ZH';
                        });
    });
    $('.del_single_zc').click(function(){
        p = $(this).attr('name');
	showAlert("确认要删除当前策略吗？"+p, 'confirm', function(){
               window.location.href = '/delpolicy/'+p;
                        });
    });
    $('.del_ctripfilter_zc').click(function(){
        p = $(this).attr('name');
	showAlert("确认要删除当前策略吗？"+p, 'confirm', function(){
               window.location.href = '/ctripdelfilter/'+p;
                        });
    });
    $('.del_ctripgw_zc').click(function(){
        p = $(this).attr('name');
	showAlert("确认要删除当前策略吗？"+p, 'confirm', function(){
               window.location.href = '/ctripdelgwpolicy/'+p;
                        });
    });
    $('.del_gw_zc').click(function(){
        p = $(this).attr('name');
	showAlert("确认要删除当前策略吗？"+p, 'confirm', function(){
               window.location.href = '/delgwpolicy/'+p;
                        });
    });
    $('#del_all_ctrip').click(function(){
	showAlert("确认要删除所有携程政策吗？全部删除后只能等下次上传", 'confirm', function(){
               window.location.href = '/ctripdelgwpolicy';
                        });
    });
    $('.up_all_zc').click(function(){
	code = $(this).attr('name');
     
        showAlert("确认要手工上传"+code+"官网的数据吗？注意：不要连续使用手工上传,最好10分钟1次", 'confirm', function(){
               window.location.href = '/uploadgwpolicy/'+code;
                        });
    });


    $('.upload_gw_modify').click(function(){
        p = $(this).attr('name');
        dl = p.split("_");
        dpt=dl[0];
        arr=dl[1];
        cabin=dl[2];
        code=dl[3];
	showAlert("确认要上传策略吗？"+p, 'confirm', function(){
               window.location.href = '/upload_gwpolicy?dpt='+dpt+'&arr='+arr+'&cabin='+cabin+'&code='+code;
        });
    });
    $('.upload_bijia').click(function(){
        p = $(this).attr('name');
	showAlert("确认要上传比价数据吗？"+p, 'confirm', function(){
               window.location.href = '/upload_bijia_policy?file='+p+'&type=cre';
        });
    });
    $('.del_bijia').click(function(){
        p = $(this).attr('name');
	showAlert("确认要删除比价数据吗？"+p, 'confirm', function(){
               window.location.href = '/upload_bijia_policy?file='+p+'&type=del';
        });
    });
    
    //保证金金额end
    
    //当前时间start
    var market_open;
    timeRun();
    function timeRun(){
        if($('#open_time').length > 0){
            setTime();
            var t = setInterval(setTime, 1000);
        }
    }
/*
    function epoch_2_time() {
	var time = new Date();
    	var time_second = $('#cur_timestamp').val()*1000;
    	time.setTime(time_second);
        time_second += 1000;
        time.setTime(time_second);
        var hour = formatTime(time.getHours());
        var minute = formatTime(time.getMinutes());
        var second = formatTime(time.getSeconds());
        var year = time.getFullYear();
        var month = formatTime(time.getMonth() + 1);
        var date = formatTime(time.getDate());
        $('#current_time').text(hour + ':' + minute + ':' + second + ' ' + year + '/' + month + '/' + date);
    	var t = setInterval(setTime,1000);
    }
    function formatTime(num){
        return (num > 9 ? num : '0' + num);
    }
*/

    function setTime(){
        var current_time = new Date();
        //epoch_2_time();
        //console.log(current_time);
        var open_time_arr = $('#open_time').val().split(':');
        var close_time_arr = $('#close_time').val().split(':');
        var open_time = new Date().setHours(open_time_arr[0],open_time_arr[1],0,0);
        var close_time = new Date().setHours(close_time_arr[0],close_time_arr[1],0,0);
        $('#current_time').text(current_time.toLocaleTimeString());
        if(current_time > open_time && current_time < close_time){
            market_open = true;
            $('#current_time').addClass('current_time_g').removeClass('current_time_r').attr('title', 'f服务营业中');
        }else{
            market_open = false;
            $('#current_time').addClass('current_time_r').removeClass('current_time_g').attr('title', '服务已经关闭');
        }
        
        //其他与时间有关的功能start
        setRechargeEnable();
        //其他与时间有关的功能end
        
    }
    //当前时间end
    
    //导航栏展开start
    $('#nav_admin_wrap, #nav_recharge_wrap, #nav_withdraw_wrap, #nav_system_wrap').mouseenter(function(){
        var num = $(this).find('a').length-1;
        $(this).stop(true).animate({height:42*num, paddingBottom:7},200).find('p').stop(true).animate({marginTop:-42},200);
    }).mouseleave(function(){
        $(this).stop(true).animate({height:42, paddingBottom:0},200).find('p').stop(true).animate({marginTop:0},200);
    });
    //导航栏展开end
    
    //header end
    
    //按钮点击样式start
    $('a.btn').live('mouseenter',function(){
        $(this).find('span').stop(true).animate({marginTop:-60},300);
    }).live('mouseleave',function(){
        $(this).find('span').stop(true).animate({marginTop:0},300);
    }).live('mousedown',function(){
        $(this).addClass('btn_mousedown');
    }).live('mouseup',function(){
        $(this).removeClass('btn_mousedown');
    });
    //按钮点击样式end
    
    //登录start
    if($('#page_name').val() == 'login'){
        var login_check_list = [
            {
                '$input' : $('#name_login'),
                'name' : '用户名',
                'check_type' : ['empty']
            },{
                '$input' : $('#pwd_login'),
                'name' : '密码',
                'check_type' : ['pwd']
            }
        ];
        function loginFormSubmit(){
            showLoading('正在登录');
            $('#login_form').submit();
        }
        setCheck(login_check_list, checkShowTips, $('#login_submit'), loginFormSubmit, 'OK');
    }
    //登录end
   
    //航班变动 start
    if($('#page_name').val() == 'query_chg'){

        $('#query_tab' + $('#current_query_tab').val()).click();
        $('input:radio[name="query_tab"]').click(function(){
            var new_query_tab = $(this).val();
            if(new_query_tab != $('#current_query_tab').val()){
                if(new_query_tab == 4){
                    window.location.href = '/admin/static'
                }else{
                    window.location.href = '/admin/query/' + new_query_tab;
                }
            }
        });
        setDatepickerChinese();
        $('#query_start_time').datepicker({
            maxDate : 0,
            onClose : function( selectedDate ){
                $('#query_end_time').datepicker( 'option', 'minDate', selectedDate );
                setFilterCondition($('#query_start_time'), true);
            }
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('from_date'));

        $('#query_flight_no').change(function(){
            setFilterCondition($(this), true);
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('flight_no'));


        setFilterConditionAll();
        $('#query_start_time_label,#query_flight_no_label').click(function(){
            $(this).addClass('none');
            $('#' + $(this).attr('id').slice(0,-6)).val('');
            chechNoCondition();
        });
        $('#fuzzy_filter_btn').click(function(){
            $('#query_start_time').val($.trim($('#query_start_time').val()));
            $('#query_flight_no').val($.trim($('#query_flight_no').val()));

            var start_time = $('#query_start_time').val();
            var flight_no = $('#query_flight_no').val();

            var start_time_url = '';
            var flight_no_url = '';

            var default_time_range = 30;

            var current_query_tab = $('#current_query_tab').val();

            if(start_time == '' || flight_no == ''){
                showAlert('查找条件不能为空！');
                return;
            }

            var new_url;
            start_time_url = start_time;
            flight_no_url = flight_no;

            new_url = '/flight_chg?from_date=' + start_time_url;
            new_url += '&flight_no=' + flight_no_url;

            if(ie6){
                $(this).attr('href','#');
            }
            window.location.href = new_url;
        });
	}


    //航班变动 end
    
    //用户注册start
    if($('#page_name').val() == 'register' || $('#page_name').val() == 'modify_user_info'){
	setDatepickerChinese();
        $('#query_end_time').datepicker({
            maxDate : 30,
            onClose : function( selectedDate ){
                $('#query_start_time').datepicker( 'option', 'maxDate', selectedDate );
                setFilterCondition($('#query_end_time'), true);
            }
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val();

        
        //选择地区start
        var area1_html = '';
        for(var i in area_data){
            area1_html += '<option value="' + i + '">' + i + '</option>'
        }
        $('#area1').html(area1_html).change(function(){
            getArea2();
        });
        
        function getArea2(){
            var area2_data = area_data[$('#area1').val()];
            var area2_html = '';
            for(var i in area2_data){
                area2_html += '<option value="' + area2_data[i] + '">' + area2_data[i] + '</option>';
            }
            $('#area2').html(area2_html);
        }
        getArea2();
        //选择地区end
        
        //表单验证start
        var register_check_list = [
            {
                '$input' : $('#name_register'),
                'name' : '真实姓名',
                'check_type' : ['empty']
            },{
                '$input' : $('#phone'),
                'name' : '手机号码',
                'check_type' : ['phone']
            },{
                '$input' : $('#area1'),
                'name' : '地区',
                'check_type' : ['empty']
            },{
                '$input' : $('#area2'),
                'name' : '地区',
                'check_type' : ['empty']
            },{
                '$input' : $('#gcj_id'),
                'name' : '钢材家ID',
                'check_type' : ['empty']
            },{
                '$input' : $('#start_time'),
                'name' : '起始交易时间',
                'check_type' : ['empty']
            },{
                '$input' : $('#end_time'),
                'name' : '结束交易时间',
                'check_type' : ['empty']
            },{
                '$input' : $('#weixinid'),
                'name' : '微信UUID',
                'check_type' : ['empty']
            },{
                '$input' : $('#corporate_account_register'),
                'name' : '对公账号',
                'check_type' : ['all_num']
            },{
                '$input' : $('#rate'),
                'name' : '费率',
                'check_type' : ['num|4']
            },{
                '$input' : $('#fee'),
                'name' : '手续费',
                'check_type' : ['num|2','range|0|10000|元']
            },{
                '$input' : $('#withdraw_fee'),
                'name' : '提现手续费',
                'check_type' : ['num|2','range|0||元']
            },{
                '$input' : $('#min_recharge'),
                'name' : '最小充值金额',
                'check_type' : ['num|2','range|0.01||元']
            },{
                '$input' : $('#account_register'),
                'name' : '到账的“财付通”账号',
                'check_type' : ['empty']
            },{
                '$input' : $('#agent'),
                'name' : '代理人账号',
                'check_type' : ['empty']
            },{
                '$input' : $('#private_account_register'),
                'name' : '私人卡号',
                'check_type' : ['all_num']
            },{
                '$input' : $('#private_name_register'),
                'name' : '真实姓名',
                'check_type' : ['empty']
            },{
                '$input' : $('#private_info'),
                'name' : '卡信息',
                'check_type' : ['empty']
            }
        ];
        if($('#page_name').val() == 'register'){
            register_check_list.push({
                '$input' : $('#pwd_register'),
                'name' : '密码',
                'check_type' : ['pwd']
            });
            register_check_list.push({
                '$input' : $('#pwd_repeat'),
                'name' : '密码',
                'check_type' : ['repeat|#pwd_register']
            });
        }
        function registerFormSubmit(){
            showLoading('正在提交注册信息');
            $('#register_form').submit();
        }
        setCheck(register_check_list, checkShowTips, $('#register_submit'), registerFormSubmit, 'OK');
        
        function showIsAgent(){
            var is_agent = $('input:radio[name="is_agent"]:checked').val();
            if(is_agent == '1'){
                $('#agent_rate_wrap').removeClass('none');
                $('#agent_split_wrap').removeClass('none');
                $('#agent_remark_wrap').removeClass('none');
            }else if(is_agent == '0'){
                $('#agent_rate_wrap').addClass('none');
                $('#agent_split_wrap').addClass('none');
                $('#agent_remark_wrap').addClass('none');
            }
        }
        showIsAgent();
        $('input:radio[name="is_agent"]').click(function(){
            showIsAgent();
        });
        //表单验证end
        
    }
    //用户注册end
    
    //充值start
    if($('#page_name').val() == 'recharge'){
        function showRechargeBankInfo(){
            var charge_type = $('input:radio[name="charge_type"]:checked').val();
            if(charge_type == 'charge_service'){
              $('#recharge_max_amount').html('充值用于月服务费').removeClass('none');
            }else if(charge_type == 'charge_data'){
              $('#recharge_max_amount').html('充值用于航变航延信息查询').removeClass('none');
            }
        };
        var tmp_market_open = !market_open;
        setRechargeEnable();
        showRechargeBankInfo();
        
        //获取银行卡额度start
        $('#bank').change(function(){
            showRechargeBankInfo();
        });
        $('input:radio[name="charge_type"]').click(function(){
            showRechargeBankInfo();
        });
        $('#reget_recharge_max_amount').live('click', function(){
            showRechargeBankInfo();
        });
        //获取银行卡额度end
        
        var min_recharge_str = 'Math.max(setAmountFormat($("#min_recharge").val()),0.01)';
        var max_recharge_str = 'setAmountFormat($("#bank").data("max_amount"))';
        var recharge_check_list = [
            {
                '$input' : $('#bank'),
                'name' : '银行',
                'check_type' : ['empty']
            },{
                '$input' : $('#money'),
                'name' : '充值金额',
                'check_type' : ['num|2','range|' + min_recharge_str + '|' + max_recharge_str + '|元']
            },{
                '$input' : $('#pwd_recharge'),
                'name' : '支付密码',
                'check_type' : ['pwd']
            },{
                '$input' : $('#remark'),
                'name' : '备注',
                'check_type' : ['empty']
            }
        ];
        function rechargeFormSubmit(){
            showLoading('正在提交充值信息');
            var paras = {
                m : $('#money').val(),
                remark : $('#remark').val(),
                charge_type : $('input:radio[name="charge_type"]:checked').val() 
            };
            $.post('/pay', paras, function(data){
                hideLoading();
                if(data == 'money is empty'){
                    checkShowTips($('#pwd_recharge'), '金额为空', false);
                }else{
                    data = eval('(' + data + ')');
                    showAlert('本次充值总金额：' + data['tot_fee'] + '元<br/>点击“确定”进入支付页面', 'alert', function(){
                        alertWhetherCompletePay(data['url'].replace(/\&amp;/g,'&'));
                    }, null, function(){
                        $('#popup_normal_btn').attr({
                            href : data['url'].replace(/\&amp;/g,'&'),
                            target : '_blank'
                        });
                    });
                }
            });
        }

        function alertWhetherCompletePay(pay_url){
            showAlert('您完成支付了吗？', 'confirm', function(){
                window.location.href = '/mytrans';
            }, function(){
                alertWhetherCompletePay(pay_url);
            }, function(){
                $('#popup_confirm_btn').text('已完成支付');
                $('#popup_cancel_btn').attr({
                    href : pay_url,
                    target : '_blank'
                }).text('再次生成支付页面');
            });
        }
        setCheck(recharge_check_list, checkShowTips, $('#recharge_submit'), rechargeFormSubmit, 'OK');
    }
    
    //切换能否充值start
    function setRechargeEnable(){
        if(tmp_market_open != market_open){
            if(market_open){
                $('#recharge_enabled_wrap').removeClass('none');
                $('#recharge_disabled_wrap').addClass('none');
            }else{
                $('#recharge_enabled_wrap').addClass('none');
                $('#recharge_disabled_wrap').removeClass('none');
            }
            $(window).resize();
            tmp_market_open = market_open;
        }
    }
    //切换能否充值end
    
    //充值end
    
    //退回start
    if($('#page_name').val() == 'gwpolicy'){
    
        //获取银行信息start
        /*
        $('#bank').change(function(){
            showWithdrawBankInfo();
        });
        function showWithdrawBankInfo(){
            $.get('/bankcheck', {bank_id : $('#bank').val()}, function(data){
                switch(data['status']){
                    case 'ok':
                        $('#bank').data({'channel' : data['channel']});
                        break;
                    case 'error':
                        $('#bank').data({'channel' : 'err'});
                        break;
                }
                var bank_name = $('#bank').find('option:selected').text();
                $('#withdraw_time').text(bank_name + '到账时间：' + '3月24日24点前').removeClass('none');
            });
        }
        */
        //获取银行信息end
        
        //批量退回选择start
        $('#withdraw_money_list').find('input.J_checkbox').removeAttr('checked').removeAttr('disabled').removeAttr('title');
        $('#withdraw_money_list').find('input.J_checkbox').click(function(){
            var $tr = $(this).closest('tr');
            if($(this).attr('checked') == 'checked'){
                $tr.stop(true).animate({backgroundColor:'##f9f2cc'}, 200)
                //$tr.find('a.J_withdraw_single_btn').addClass('table_btn_disabled');
                //$tr.find('a.J_withdraw_multiple_btn').removeClass('table_btn_disabled');
                $('.J_withdraw_multiple_btn').removeClass('table_btn_disabled');
            }else{
                $tr.stop(true).animate({backgroundColor:'transparent'}, 200);
                //$tr.find('a.J_withdraw_single_btn').removeClass('table_btn_disabled');
                //$tr.find('a.J_withdraw_multiple_btn').addClass('table_btn_disabled');
                $('.J_withdraw_multiple_btn').addClass('table_btn_disabled');
            }
            
        });
        //批量退回选择end
        
        //单笔退回start
        $('#withdraw_money_list').find('a.J_withdraw_single_btn').click(function(){
            var withdraw_money = $(this).closest('tr').find('strong.money_icon').text();
            var tid_list = [$(this).closest('tr').find('td.J_transaction_id').text()];
            withdrawStep1ToStep2(withdraw_money, tid_list);
        });
        //单笔退回end
        
        //批量退回start
        $('#withdraw_money_list').find('.J_withdraw_multiple_btn').click(function(){
            if($(this).hasClass('table_btn_disabled')){
                return;
            }
            var withdraw_money = 0;
            var tid_list = [];
            $('#withdraw_money_list').find('input.J_checkbox').each(function(){
                if($(this).attr('checked') == 'checked'){
                    //withdraw_money += parseFloat($(this).closest('tr').find('strong.money_icon').text());
                    //tid_list.push($(this).closest('tr').find('td.J_transaction_id').text());
                    tid_list.push($(this).closest('tr').find('a.del_gw_zc').attr('name'));
                }
            });
            if(tid_list.length > 1){
                withdrawStep1ToStep2(tid_list);
            }else if(tid_list.length == 1){
                showAlert('批量修改的交易只有1笔<br/>请单独点修改选项', 'alert', function(){
                    withdraw_money = withdraw_money.toFixed(2);
                    withdrawStep1ToStep2(withdraw_money, tid_list);
                });
            }else{
                showAlert('请选择批量退回的交易！');
            }
        });
        //批量退回end
        
        function withdrawStep1ToStep2(tid_list){
           $('#withdraw_title').text('批量修改（共' + tid_list.length + '笔）');
           $('#tid_list').val('["' + tid_list.join('","') + '"]');
           $('#withdraw_step1').addClass('none');
	   $('#withdraw_step2_type1').removeClass('none');
           $(window).resize();
        }
        
        $('#prev_submit_corporate,#prev_submit').click(function(){
            $('#withdraw_title').text('去哪儿政策调整');
            $('#withdraw_step1').removeClass('none');
            $('#withdraw_step2_type1').addClass('none');
            $(window).resize();
        });
        $('input:radio[name="withdraw_type"]').click(function(){
            var type = $('input:radio[name="withdraw_type"]:checked').attr('id');
            if(type == 'corporate'){
                $('#withdraw_step2_type1').removeClass('none');
                $('#withdraw_step2_type2').addClass('none');
            }else if(type == 'transfer'){
                $('#withdraw_step2_type1').addClass('none');
                $('#withdraw_step2_type2').removeClass('none');
            }
            $(window).resize();
        });
        
        //通过url中的参数money判断是显示step1还是step2 start
        /*
        var withdraw_money = getUrlPara('withdraw_money');
        if(withdraw_money != ''){
            $('#withdraw_step1').addClass('none');
            $('#withdraw_step2_type').removeClass('none');
            $('#corporate').click().click();
            $('#money_corporate').val(withdraw_money);
            $('#money').val(withdraw_money);
            var transaction_id = getUrlPara('transaction_id');
            $('#transaction_id').val(transaction_id);
            $(window).resize();
        }
        */
        //通过url中的参数money判断是显示step1还是step2 end
        
        var withdraw_corporate_check_list = [
            {
                '$input' : $('#piaomian'),
                'name' : '指定票面价上浮',
                'check_type' : ['all_num']
            },{
                '$input' : $('#beforeday'),
                'name' : '最晚提前出票时限',
                'check_type' : ['all_num']
            },{
                '$input' : $('#point'),
                'name' : '留点',
                'check_type' : ['empty']
            },{
                '$input' : $('#bonus'),
                'name' : '留钱',
                'check_type' : ['empty']
            }
        ];
        function withdrawCorporateFormSubmit(){
            var url = '/batch_modify_policy';
            var paras = {
                piaomian : $('#piaomian').val(),
                beforeday : $('#beforeday').val(),
                point : $('#point').val(),
                bonus : $('#bonus').val(),
                refund : $('#refund').val(),
                change : $('#change').val(),
                trans_id : $('#tid_list').val()
            };
            $.post(url, paras, function(data){
                switch(parseInt(data['status'])){
                    case 0:
                        showAlert(data['result']);
                        break;
                    case 1:
                        showAlert(data['result'], 'alert', function(){
                            window.location.href = '/gwpolicy';
                        });
                        break;
                }
            },'json');
        }
        setCheck(withdraw_corporate_check_list, checkShowTips, $('#withdraw_submit_corporate'), withdrawCorporateFormSubmit, 'OK');
        
        //对私账号，默认卡号相关start
        $('#card').blur(function(){
            var default_card = $('#default_card').val();
            $(this).val($.trim($(this).val()));
            if($(this).val() == default_card){
                $('#card_repeat').val(default_card).closest('p').addClass('none');
                $('#use_default_card').addClass('none');
            }else{
                $('#card_repeat').val('').closest('p').removeClass('none');
                $('#use_default_card').removeClass('none');
            }
        });
        $('#use_default_card').find('a').click(function(){
            var default_card = $('#default_card').val();
            $('#card').val(default_card).focus();
            $('#card_repeat').val(default_card).closest('p').addClass('none');
            $('#use_default_card').addClass('none');
        });
        //对私账号，默认卡号相关end
        
        var withdraw_check_list = [
            {
                '$input' : $('#bank'),
                'name' : '到账银行',
                'check_type' : ['empty']
            },{
                '$input' : $('#subbranch'),
                'name' : '地区及支行名称',
                'check_type' : ['empty']
            },{
                '$input' : $('#card'),
                'name' : '到账卡号',
                'check_type' : ['all_num']
            },{
                '$input' : $('#card_repeat'),
                'name' : '到账卡号',
                'check_type' : ['repeat|#card']
            },{
                '$input' : $('#name'),
                'name' : '收款人姓名',
                'check_type' : ['empty']
            },{
                '$input' : $('#money'),
                'name' : '退回总金额',
                'check_type' : ['num|2','range|0.01|setAmountFormat($("#user_amount").text())|元']
            },{
                '$input' : $('#pwd_withdraw'),
                'name' : '支付密码',
                'check_type' : ['pwd']
            }
        ];
        function withdrawFormSubmit(){
            var url = ($('#type').val() == 'single' ? '/withdraw_detail' : '/batch_withdraw_detail');
            var paras = {
                bank_name : $('#bank').val(),
                bank_detail : $('#subbranch').val(),
                bank_acct : $('#card').val(),
                acct_name : $('#name').val(),
                pwd : $('#pwd_withdraw').val(),
                trans_id : $('#tid_list').val(),
                type : 1
            };
            $.post(url, paras, function(data){
                switch(parseInt(data['status'])){
                    case 0:
                        if(data['result'] == '支付密码不正确'){
                            checkShowTips($('#pwd_withdraw'), '支付密码错误', false);
                        }else{
                            showAlert(data['result']);
                        }
                        break;
                    case 1:
                        showAlert(data['result'], 'alert', function(){
                            window.location.href = '/withdraw_detail';
                        });
                        break;
                }
            },'json');
        }
        setCheck(withdraw_check_list, checkShowTips, $('#withdraw_submit'), withdrawFormSubmit, 'OK');
    }
    //退回end
    
    //记录start
    if($('#page_name').val() == 'recharge_transaction' || $('#page_name').val() == 'withdraw_transaction'){
        $('#transaction').find('td').mouseenter(function(){
            $(this).stop(true).animate({backgroundColor:'#b3db14'},250)
            .siblings('td').stop(true).animate({backgroundColor:'#d6f267'},250);
        }).mouseleave(function(){
            $(this).stop(true).animate({backgroundColor:'transparent'},250)
            .siblings('td').stop(true).animate({backgroundColor:'transparent'},250);
        });
        if(chrome || opera){
            $('#transaction').find('a.transaction_detail,a.transaction_withdraw').find('span').mouseenter(function(){
                $(this).stop(true).animate({marginTop:-40},300);
            }).mouseleave(function(){
                $(this).stop(true).animate({marginTop:0},300);
            });
        }else{
            $('#transaction').find('a.transaction_detail').addClass('transaction_detail_for_no_animate');
            $('#transaction').find('a.transaction_withdraw').addClass('transaction_withdraw_for_no_animate');
        }
    }
    //记录end
    
    //修改密码start
    var change_pwd_check_list = [
        {
            '$input' : $('#old_pwd'),
            'name' : '旧密码',
            'check_type' : ['pwd']
        },{
            '$input' : $('#new_pwd'),
            'name' : '新密码',
            'check_type' : ['pwd']
        },{
            '$input' : $('#new_pwd_repeat'),
            'name' : '新密码',
            'check_type' : ['repeat|#new_pwd']
        }
    ];
    function changePwdFormSubmit(){
        var paras = {
            oldpwd : $('#old_pwd').val(),
            newpwd : $('#new_pwd').val()
        };
        $.post('/changepwd',paras,function(data){
            if(data == '历史密码不正确'){
                showAlert('旧密码不正确');
                checkShowTips($('#old_pwd'), '旧密码不正确', false);
            }else{
                showAlert('密码已修改');
                $('#old_pwd').val('');
                $('#new_pwd').val('');
                $('#new_pwd_repeat').val('');
            }
        });
    }
    setCheck(change_pwd_check_list, checkShowTips, $('#change_pwd_submit'), changePwdFormSubmit, 'OK');
    //修改密码end
    
    if($('#page_name').val() == 'addpolicy'){
        setDatepickerChinese();
        $('#query_start_time').datepicker({
            maxDate : 90,
            onClose : function( selectedDate ){
                $('#query_end_time').datepicker( 'option', 'minDate', selectedDate );
                setFilterCondition($('#query_start_time'), true);
            }
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        });
        $('#query_end_time').datepicker({
            maxDate : 90,
            onClose : function( selectedDate ){
                $('#query_start_time').datepicker( 'option', 'maxDate', selectedDate );
                setFilterCondition($('#query_end_time'), true);
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
                '$input' : $('#cabin'),
                'name' : '舱位不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#code'),
                'name' : '航司不能为空',
                'check_type' : ['empty']
            },{
                '$input' : $('#query_start_date'),
                'name' : '开始日期',
                'check_type' : ['empty']
            },{
                '$input' : $('#query_end_date'),
                'name' : '到达日期',
                'check_type' : ['empty']
            },{
                '$input' : $('#piaomian'),
                'name' : '指定票面价格上浮',
                'check_type' : ['all_num']
            },{
                '$input' : $('#bonus'),
                'name' : '留钱不能为空',
                'check_type' : ['empty']
            }
        ];

        function registerFormSubmit(){
            showLoading('正在提交注册信息');
            $('#register_form').submit();
        }
        setCheck(register_check_list, checkShowTips, $('#register_submit'), registerFormSubmit, 'OK');

    }
    //后台查询start
    if($('#page_name').val() == 'query'){
        
        $('#query_tab' + $('#current_query_tab').val()).click();
        $('input:radio[name="query_tab"]').click(function(){
            var new_query_tab = $(this).val();
            if(new_query_tab != $('#current_query_tab').val()){
                if(new_query_tab == 4){
                    window.location.href = '/admin/static'
                }else{
                    window.location.href = '/admin/query/' + new_query_tab;
                }
            }
        });
        setDatepickerChinese();
        $('#query_start_time').datepicker({
            maxDate : 0,
            onClose : function( selectedDate ){
                $('#query_end_time').datepicker( 'option', 'minDate', selectedDate );
                setFilterCondition($('#query_start_time'), true);
            }
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('from_date'));
        $('#query_end_time').datepicker({
            maxDate : 30,
            onClose : function( selectedDate ){
                $('#query_start_time').datepicker( 'option', 'maxDate', selectedDate );
                setFilterCondition($('#query_end_time'), true);
            }
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('to_date'));
        $('#query_t_city').change(function(){
            setFilterCondition($(this), true);
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('t_city'));
        $('#query_f_city').change(function(){
            setFilterCondition($(this), true);
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('f_city'));
        $('#query_t_city').change(function(){
            setFilterCondition($(this), true);
        }).keypress(function(e){
            if(e.which == 13){
                $('#fuzzy_filter_btn').click();
            }
        }).val(getUrlPara('t_city'));
        $('#query_tid').change(function(){
            setFilterCondition($(this), true);
        }).keypress(function(e){
            if(e.which == 13){
                $('#exact_filter_btn').click();
            }
        }).val(getUrlSlash(-1));
        //起始时间、结束时间、用户名、交易号相关end
        
        //查找条件标签start
        setFilterConditionAll();
        $('#query_start_time_label, #query_end_time_label, #query_f_city_label, #query_t_city_label').click(function(){
            $(this).addClass('none');
            $('#' + $(this).attr('id').slice(0,-6)).val('');
            chechNoCondition();
        });
        //查找条件标签end
        
        $('#fuzzy_filter_btn').click(function(){
            $('#query_start_time').val($.trim($('#query_start_time').val()));
            $('#query_end_time').val($.trim($('#query_end_time').val()));
            $('#query_f_city').val($.trim($('#query_f_city').val()));
            $('#query_t_city').val($.trim($('#query_t_city').val()));
            
            var start_time = $('#query_start_time').val();
            var end_time = $('#query_end_time').val();
            var f_city = $('#query_f_city').val();
            var t_city = $('#query_t_city').val();
            
            var start_time_url = '';
            var end_time_url = '';
            var f_city_url = '';
            var t_city_url = '';
            
            var default_time_range = 30;
            
            var current_query_tab = $('#current_query_tab').val();
            
            if(start_time == '' || end_time == '' || f_city == '' || t_city ==''){
                showAlert('查找条件不能为空！');
                return;
            }else if(start_time == '' && end_time == ''){
                f_city_url = f_city;
                t_city_url = t_city;
            }else{
                if(start_time == ''){
                    var end_time_arr = end_time.split('-');
                    var date_end_time = new Date(end_time_arr[0], parseInt(end_time_arr[1])-1, end_time_arr[2]);
                    if(date_end_time == 'Invalid Date'){
                        showAlert('结束时间格式不正确！');
                        return;
                    }
                    date_end_time.setDate(date_end_time.getDate() - default_time_range);
                    start_time_url = date_end_time.getFullYear() + '-' + (formatMonth(date_end_time.getMonth()+1)) + '-' + formatDate(date_end_time.getDate());
                }else{
                    start_time_url = start_time;
                }
                if(end_time == ''){
                    var start_time_arr = start_time.split('-');
                    var date_start_time = new Date(start_time_arr[0], parseInt(start_time_arr[1])-1, start_time_arr[2]);
                    if(date_start_time == 'Invalid Date'){
                        showAlert('起始时间格式不正确！');
                        return;
                    }
                    date_start_time.setDate(date_start_time.getDate() + default_time_range);
                    end_time_url = date_start_time.getFullYear() + '-' + (formatMonth(date_start_time.getMonth()+1)) + '-' + formatDate(date_start_time.getDate());
                }else{
                    end_time_url = end_time;
                }
                f_city_url = f_city;
                t_city_url = t_city;
            }
            
            var new_url;
            if(current_query_tab == 1){
                new_url = '/admin/query/' + $('#current_query_tab').val();
                new_url += '?f_city=' + f_city_url;
                new_url += '&t_city=' + t_city_url;
            }else if(current_query_tab == 4){
                new_url = '/admin/static?from_date=' + start_time_url;
                new_url += '&to_date=' + end_time_url;
                new_url += '&f_city=' + f_city_url;
                new_url += '&t_city=' + t_city_url;
            }else if(current_query_tab == 6){
                new_url = '/agent?from_date=' + start_time_url;
                new_url += '&to_date=' + end_time_url;
                new_url += '&f_city=' + f_city_url;
                new_url += '&t_city=' + t_city_url;
            }
            if(ie6){
                $(this).attr('href','#');
            }
            window.location.href = new_url;
        });
        $('#exact_filter_btn').click(function(){
            $('#query_tid').val($.trim($('#query_tid').val()));
            var tid = $('#query_tid').val();
            if(tid == ''){
                showAlert('请输入交易号！');
                return;
            }
            var new_url = '/admin/qtid/' + tid;
            if(ie6){
                $(this).attr('href','#');
            }
            window.location.href = new_url;
        });
    }
    
    //设置datepicker为中文start
    function setDatepickerChinese(){
        $.datepicker.regional['zh-CH'] = {
            closeText: '关闭',
            prevText: '&#x3c;上月',
            nextText: '下月&#x3e;',
            currentText: '今天',
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
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
    //设置datepicker为中文end
    
    //设置查找条件文字start
    function setFilterConditionAll(){
        setFilterCondition($('#query_start_time'));
        setFilterCondition($('#query_end_time'));
        setFilterCondition($('#query_f_city'));
        setFilterCondition($('#query_t_city'));
        setFilterCondition($('#query_aid'));
        chechNoCondition();
    }
    function setFilterCondition($input, check_no_condition){
        var is_show = $input.closest('p').hasClass('none');
        var name = {
            query_start_time : '起始时间',
            query_end_time : '结束时间',
            query_f_city : '出发城市',
            query_t_city : '到达城市',
            query_aid : '代理人ID'
        };
        var id = $input.attr('id');
        var $label = $('#' + id + '_label');
        if($input.val() != '' && !is_show){
            $label.text(name[id] + '：' + $input.val()).removeClass('none');
        }else{
            $label.addClass('none');
        }
        if(check_no_condition){
            chechNoCondition();
        }
    }
    function chechNoCondition(){
        var current_query_tab = $('#current_query_tab').val();
        if(current_query_tab == 1 || current_query_tab == 2 || current_query_tab == 3 || current_query_tab == 0){
            if($('#query_start_time_label').hasClass('none') && $('#query_end_time_label').hasClass('none') && $('#query_f_city_label').hasClass('none') && $('#query_t_city_label').hasClass('none')){
                $('#no_query_filter_condition').removeClass('none');
            }else{
                $('#no_query_filter_condition').addClass('none');
            }
        }else if(current_query_tab == 4){
            if($('#query_start_time_label').hasClass('none') && $('#query_end_time_label').hasClass('none') && $('#query_f_city_label').hasClass('none') && $('#query_aid_label').hasClass('none')){
                $('#no_query_filter_condition').removeClass('none');
            }else{
                $('#no_query_filter_condition').addClass('none');
            }
        }else if(current_query_tab == 5){
            if($('#query_tid_label').hasClass('none')){
                $('#no_query_filter_condition').removeClass('none');
            }else{
                $('#no_query_filter_condition').addClass('none');
            }
        }
    }
    //设置查找条件文字end
    
    //格式化日期start
    function formatMonth(month){
        return (month > 9 ? month : '0' + month);
    }
    function formatDate(date){
        return (date > 9 ? date : '0' + date);
    }
    //格式化日期end
    
    //后台查询end
    
    //后台转账start
    if($('#page_name').val() == 'withdraw_process'){
        $('#withdraw_process_list').find('a:not(.table_btn_g)').click(function(){
            var $btn = $(this);
            var alert_html = [
                '请输入管理员密码',
                '<br/>',
                '<p class="input input_withdraw_process"><input id="withdraw_process_pwd" type="password"/></p>'
            ];
            showAlert(alert_html.join(''), 'confirm', function(){
                getTemplate($btn);
            }, null, function(){
                $('#withdraw_process_pwd').focus();
                $('#withdraw_process_pwd').keypress(function(e){
                    if(e.which == 13){
                        $('#popup_confirm_btn').click();
                    }
                });
            });
        });
    }
    function getTemplate($btn){
        if($('#withdraw_process_pwd').val() == ''){
            showAlert('管理员密码不能为空');
            return;
        }
        var url;
        var paras;
        if($btn.hasClass('J_batch')){
            url = '/admin/batch_withdraw_process';
            paras = {
                batch_id : $btn.siblings('input').val(),
                pwd : $('#withdraw_process_pwd').val()
            };
        }else{
            url = '/admin/withdraw_process';
            paras = {
                tid : $btn.closest('tr').find('td.J_tid').text(),
                pwd : $('#withdraw_process_pwd').val()
            };
        }
        $.post(url, paras, function(data){
            switch(data['status']){
                case 0:
                    showAlert(data['result']);
                    break;
                case 1:
                    showAlert(data['result'] + '<br/>点击“确定”后将自动下载打款信息', 'alert', function(){
                        $btn.closest('tr').find('td.J_status').html('<span class="status_icon3">已经打款</span>');
                        $btn.attr({'href':data['url']}).addClass('table_btn_g').text('再次下载打款信息').unbind('click');
                        window.location.href = data['url'];
                    });
                    break;
            }
        },'json');
    }
    //后台转账end
    
    //帮助start
    if($('#page_name').val() == 'help'){
        $('#help_img').find('img').load(function(){
            $(window).resize();
        });
    }
    //帮助end
    
});

//特殊常用函数start

//表单验证显示函数start
function checkShowTips($input, msg, type){
    $input.siblings('span').text(msg);
    if(type){
        $input.closest('p').addClass('input_g').removeClass('input_r');
    }else{
        $input.closest('p').addClass('input_r').removeClass('input_g');
    }
}
//表单验证显示函数en

//格式化数字start
function setAmountFormat(val){
    /*
    if(val == ''){
        return '';
    }else if(isNaN(val)){
        return 0.00;
    }
    var amount = new Number(val);
    return amount.toFixed(2);
    */
    return val;
}
//格式化数字end

//获取银行列表start
function getBankList(fn){
    showLoading('正在加载银行信息');
    $.get('/getbanklist', function(data){
        var bank_html = '';
        for(var i in data){
            bank_html += '<option value="' + i + '">' + data[i] + '</option>';
        }
        $('#bank').html(bank_html);
        fn();
        hideLoading();
    },'json');
}
//获取银行列表end

//特殊常用函数end

//常用函数start
function getPageHeight(){
    if(chrome){
        return document.body.scrollHeight;
    }else{
        return document.documentElement.scrollHeight;
    }
}
function getWindowHeight(){
    return document.documentElement.clientHeight;
}
function getScrollTop(){
    if(chrome){
        return document.body.scrollTop;
    }else{
        return document.documentElement.scrollTop;
    }
}

//黑色半透明蒙版start
function showBlackMask(showBlackMaskCallback){
    if($('#black_mask').length <= 0){
        $('body').append('<div id="black_mask"></div>');
    }
    $('#black_mask').stop(true).animate({opacity:0.75},500);
    showBlackMaskCallback();
}
function hideBlackMask(hideBlackMaskCallback){
    $('#black_mask').stop(true).animate({opacity:0},500,function(){
        $(this).remove();
    });
    hideBlackMaskCallback();
}
//黑色半透明蒙版end

//loading界面start
function showLoading(loading_msg){
    showBlackMask(function(){
        $('body').append('<div id="loading_animate"></div><div id="loading_msg">' + loading_msg + '</div>');
        $('#loading_animate,#loading_msg').stop(true).animate({opacity:1},300);
    });
}
function hideLoading(){
    hideBlackMask(function(){
        $('#loading_animate,#loading_msg').stop(true).animate({opacity:0},300,function(){
            $(this).remove();
        });
    });
}
//loading界面end

//弹出框start
function showAlert(msg, type, fn1, fn2, fn3){
    //type: alert\confirm
    //fn1: when click confirm
    //fn2: when click cancel
    //fn3: when alert display full
    
    if(type == undefined){
        type = 'alert';
    }
    showBlackMask(function(){
        $('#popup').remove();
        $('body').append(generatePopupHtml(type, msg));
        var popup_margin_top = -($('#popup').height() + parseInt($('#popup').css('paddingTop')) + parseInt($('#popup').css('paddingBottom')))/2;
        var popup_margin_left = -($('#popup').width() + parseInt($('#popup').css('paddingLeft')) + parseInt($('#popup').css('paddingRight')))/2
        $('#popup').css({margin:popup_margin_top - 120 + 'px 0 0 ' + popup_margin_left + 'px'})
        .stop(true).animate({marginTop:popup_margin_top + 10, opacity:1}, 250)
        .animate({marginTop:popup_margin_top}, 100);
        
        if(type == 'alert'){
            $('#popup_normal_btn').on('click', function(){
                hideAlert(fn1);
                $(this).unbind('click');
            });
        }else if(type == 'confirm' || type == "chupiao"){
            $('#popup_confirm_btn').on('click', function(){
                hideAlert(fn1);
                $(this).unbind('click');
            });
            $('#popup_cancel_btn').on('click', function(){
                hideAlert(fn2);
                $(this).unbind('click');
            });
        }
        if(fn3 != undefined){
            fn3();
        }
    });
}
function hideAlert(fn){
    hideBlackMask(function(){
        var popup_margin_top = -($('#popup').height() + parseInt($('#popup').css('paddingTop')) + parseInt($('#popup').css('paddingBottom')))/2;
        $('#popup').stop(true).animate({marginTop:popup_margin_top - 10}, 100)
        .animate({marginTop:popup_margin_top + 120, opacity:0}, 250, function(){
            if(fn != undefined){
                fn();
            }
            $(this).remove();
        });
    });
}
function generatePopupHtml(type, msg){
    var btn_html;
    if(type == 'alert'){
        btn_html = '<a href="javascript:void(0);" id="popup_normal_btn">确 定</a>';
    }else if(type == 'confirm'){
        btn_html = '<a href="javascript:void(0);" id="popup_confirm_btn">确定</a> <a href="javascript:void(0);" id="popup_cancel_btn">取消</a>';
    }else if(type == 'chupiao'){
        btn_html = '<a href="javascript:void(0);" id="popup_confirm_btn">点击下单</a> <a href="javascript:void(0);" id="popup_cancel_btn">取消</a>';
    }
    var popup_html = [
        '<div id="popup">',
            '<div class="popup_msg">' + msg + '</div>',
            '<div class="popup_btn">' + btn_html + '</div>',
        '</div>'
    ];
    return popup_html.join('');
}
//弹出框end

//表单验证start
function setCheck(check_list, show_tips_fn, $submit_btn, submit_fn, suc_msg){ //show_tips_fn must has 3 paras: $input, msg, type
    var check_input_list = new Array();
    for(var i in check_list){
        var e_check_list = check_list[i];
        check_input_list.push(e_check_list['$input']);
        e_check_list['$input'].data('check_data', {
            'name' : e_check_list['name'],
            'check_type' : e_check_list['check_type'],
            'suc_msg' : suc_msg,
            'show_tips_fn' : show_tips_fn,
            '$submit_btn' : $submit_btn
        }).live('blur',function(){
            checking($(this));
        }).live('keypress',function(e){
            if(e.which == 13){
                $(this).data('check_data')['$submit_btn'].click();
            }
        });
    }
    $submit_btn.data('check_data', {
        'check_input_list' : check_input_list,
        'submit_fn' : submit_fn
    }).live('click',function(){
        var check_data = $(this).data('check_data');
        var check_input_list = check_data['check_input_list'];
        var check_result = true;
        for(var j in check_input_list){
            if(!checking(check_input_list[j])){
                check_result = false;
            }
        }
        if(check_result){
            check_data['submit_fn']();
        }
    });
}
function checking($input){
    var check_data = $input.data('check_data');
    if(check_data == undefined){
        return true;
    }
    var check_result = true;
    for(j in check_data['check_type']){
        var e_check_type = check_data['check_type'][j].split('|');
        //empty, pwd, repeat|$target, phone, integer, num|3, len|1|10, range|0|10|元, re|/正则表达式/
        
        switch(e_check_type[0]){
            case 'empty':
                $input.val($.trim($input.val()));
                var val = $input.val();
                if(val == '' || val == undefined){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能为空', false);
                    check_result = false;
                }
                break;
            case 'pwd':
                var pwd = $input.val();
                if(pwd == ''){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能为空', false);
                    check_result = false;
                }
                break;
            case 'repeat':
                var val = $(e_check_type[1]).val();
                var val_repeat = $input.val();
                if(val_repeat == ''){
                    check_data['show_tips_fn']($input, '请再次输入' + check_data['name'], false);
                    check_result = false;
                }else if(val != val_repeat){
                    check_data['show_tips_fn']($input, '两次输入的' + check_data['name'] + '不一致', false);
                    check_result = false;
                }
                break;
            case 'phone':
                $input.val($.trim($input.val()));
                var phone = $input.val();
                var phone_re = /^1[3|4|5|8][0-9]\d{8}$/;
                if(phone == ''){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能为空', false);
                    check_result = false;
                }else if(!phone_re.exec(phone)){
                    check_data['show_tips_fn']($input, check_data['name'] + '格式不正确', false);
                    check_result = false;
                }
                break;
            case 'all_num':
                $input.val($.trim($input.val()));
                var all_num = $input.val();
                var re = /^\d+$/;
                if(all_num == ''){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能为空', false);
                    check_result = false;
                }else if(!re.test(all_num)){
                    check_data['show_tips_fn']($input, check_data['name'] + '必须是全部是数字', false);
                    check_result = false;
                }
                break;
            case 'integer':
                $input.val($.trim($input.val()));
                var integer = $input.val();
                if(integer == ''){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能为空', false);
                    check_result = false;
                }else if(integer != parseInt(integer)){
                    check_data['show_tips_fn']($input, check_data['name'] + '必须是整数', false);
                    check_result = false;
                }else{
                    $input.val(parseInt(integer));
                }
                break;
            case 'num':
                $input.val($.trim($input.val()));
                var num = $input.val();
                if(num == ''){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能为空', false);
                    check_result = false;
                }else if(isNaN(num)){
                    check_data['show_tips_fn']($input, check_data['name'] + '必须是数值', false);
                    check_result = false;
                }else{
                    $input.val(parseFloat(num));
                    num = $input.val();
                    e_check_type[1] = eval(e_check_type[1]);
                    if(e_check_type[1] != undefined && e_check_type[1] > 0){
                        var num_split = num.toString().split('.');
                        if(num_split[1] != undefined && num_split[1].length > e_check_type[1]){
                            check_data['show_tips_fn']($input, check_data['name'] + '的精度不能超过小数点后' + e_check_type[1] + '位', false);
                            check_result = false;
                        }
                    }
                }
                break;
            case 'len':
                $input.val($.trim($input.val()));
                var val = $input.val();
                e_check_type[1] = eval(e_check_type[1]);
                e_check_type[2] = eval(e_check_type[2]);
                if(e_check_type[1] != undefined && val.length < e_check_type[1]){
                    check_data['show_tips_fn']($input, check_data['name'] + '的长度不能少于' + e_check_type[1] + '位', false);
                    check_result = false;
                }else if(e_check_type[2] != undefined && val.length > e_check_type[2]){
                    check_data['show_tips_fn']($input, check_data['name'] + '的长度不能超过' + e_check_type[2] + '位', false);
                    check_result = false;
                }
                break;
            case 'range':
                $input.val($.trim($input.val()));
                var val = Number($input.val());
                e_check_type[1] = eval(e_check_type[1]);
                e_check_type[2] = eval(e_check_type[2]);
                var unit = (e_check_type[3] != undefined ? e_check_type[3] : '');
                if(e_check_type[1] != undefined && val < Number(e_check_type[1])){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能小于' + e_check_type[1] + unit, false);
                    check_result = false;
                }else if(e_check_type[2] != undefined && val > Number(e_check_type[2])){
                    check_data['show_tips_fn']($input, check_data['name'] + '不能大于' + e_check_type[2] + unit, false);
                    check_result = false;
                }
                break;
            case 're':
                $input.val($.trim($input.val()));
                var re = new RegExp(e_check_type[1]);
                if(!re.test($input.val())){
                    check_data['show_tips_fn']($input, check_data['name'] + '的格式不正确', false);
                    check_result = false;
                }
                break;
        }
        if(!check_result){
            break;
        }  
    }
    if(check_result){
        check_data['show_tips_fn']($input, check_data['suc_msg'], true);
    }
    return check_result;
}
//表单验证end

//获取url信息start
function getUrlPara(key){
    var re = eval('/' + key + '=[^&]*/');
    var re_match = window.location.search.match(re);
    if(re_match == null){
        return '';
    }else{
        var key_value = re_match[0].split('=');
        return key_value[1];
    }
}
function getUrlSlash(index){
    var url = window.location.pathname.split('/');
    return url[ url.length+index ];
}
//获取url信息end


//常用函数end

//常用数据start
var area_data = {'北京':['北京'],'上海':['上海'],'天津':['天津'],'重庆':['重庆','涪陵市','万州市','黔江市'],'河北':['石家庄','张家口','承德','秦皇岛','唐山','廊坊','保定','沧州','衡水','邢台','邯郸'],'山西':['太原','大同','朔州','阳泉','长治','晋城','忻州','离石','晋中','临汾','运城','吕梁市'],'内蒙古':['呼和浩特','包头','乌海','赤峰','海拉尔','乌兰浩特','通辽','锡林浩特','集宁','东胜','临河','阿拉善左旗','巴彦淖尔市','鄂尔多斯市','呼伦贝尔市','乌兰察布市','锡林郭勒盟','兴安盟'],'辽宁':['沈阳','朝阳','阜新','铁岭','抚顺','本溪','辽阳','鞍山','丹东','大连','营口','盘锦','锦州','葫芦岛'],'吉林':['长春','白城','松原','吉林','四平','辽源','通化','白山','延吉','延边州'],'黑龙江':['哈尔滨','齐齐哈尔','黑河','大庆','伊春','鹤岗','佳木斯','双鸭山','七台河','鸡西','牡丹江','绥化','加格达奇','大兴安岭地区'],'江苏':['南京','苏州','徐州','连云港','宿迁','淮安','盐城','扬州','泰州','南通','镇江','常州','无锡'],'浙江':['杭州','湖州','嘉兴','舟山','宁波','绍兴','金华','台州','温州','丽水','衢州'],'安徽':['合肥','宿州','淮北','阜阳','蚌埠','淮南','滁州','马鞍山','芜湖','铜陵','安庆','黄山','六安','巢湖','贵池','宣城','毫州'],'福建':['福州','南平','三明','莆田','泉州','厦门','漳州','龙岩','宁德','福安','邵武','石狮','永安','武夷山','福清'],'江西':['南昌','九江','景德镇','鹰潭','新余','萍乡','赣州','上饶','临川','宜春','吉安','抚州'],'山东':['济南','聊城','德州','东营','淄博','潍坊','烟台','威海','青岛','日照','临沂','枣庄','济宁','泰安','莱芜','滨州','菏泽'],'河南':['郑州','三门峡','洛阳','焦作','新乡','鹤壁','安阳','濮阳','开封','商丘','许昌','漯河','平顶山','南阳','信阳','济源','周口','驻马店'],'湖北':['武汉','十堰','襄樊','荆门','孝感','黄冈','鄂州','黄石','咸宁','荆州','宜昌','恩施','仙桃','潜江','随州市','广水','天门'],'湖南':['长沙','张家界','常德','益阳','岳阳','株洲','湘潭','衡阳','郴州','永州','邵阳','怀化','娄底','吉首'],'广东':['广州','深圳','清远','韶关','河源','梅州','潮州','汕头','揭阳','汕尾','惠州','东莞','珠海','中山','江门','佛山','茂名','湛江','阳江','云浮','肇庆'],'广西':['南宁','桂林','柳州','贺州','玉林','钦州','北海','防城港','百色','河池','贵港','梧州','崇左市','来宾市'],'海南':['海口','三亚','儋州','琼海','文昌','万宁','五指山','东方'],'四川':['成都','广元','绵阳','德阳','南充','广安','遂宁','内江','乐山','自贡','泸州','宜宾','攀枝花','巴中','达州','资阳','雅安','西昌','阿坝州','眉山市','凉山州','甘孜州'],'贵州':['贵阳','六盘水','遵义','毕节','铜仁','安顺','凯里','都匀','兴义','黔东南州','黔南州','黔西南州'],'云南':['昆明','曲靖','玉溪','丽江','昭通','思茅','临沧','保山','潞西','泸水','中甸','大理','楚雄','个旧','文山','景洪','红河','德宏州','迪庆州','西双版纳州','怒江州'],'西藏':['拉萨','那曲','昌都','林芝','乃东','日喀则','噶尔','阿里地区','山南地区','樟木口岸'],'陕西':['西安','延安','铜川','渭南','咸阳','宝鸡','汉中','榆林','商洛','安康'],'甘肃':['兰州','嘉峪关','白银','天水','酒泉','张掖','金昌','庆阳','平凉','定西','陇南','临夏','甘南藏族','武威市'],'宁夏':['银川','石嘴山','吴忠','固原','中卫市'],'青海':['西宁','平安','海晏','共和','同仁','玛沁','玉树','德令哈','果洛藏族自治州','海北藏族自治州','海东地区','海南藏族自治州','海西蒙古族藏族自治州','黄南藏族自治州'],'新疆':['乌鲁木齐','克拉玛依','石河子','喀什','阿克苏','和田','吐鲁番','哈密','阿图什','博乐','昌吉','库尔勒','伊犁','奎屯','塔城','阿勒泰','巴音郭楞蒙古自治州','博尔塔拉蒙古自治州','克孜勒苏柯尔克孜自治州','伊犁哈萨克自治州']};
//常用数据end
