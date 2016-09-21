ID = $('#vark_user_id').val();

$(function() {
	timeID = setInterval("timeinfo('" + ID + "')", 15000);
});

function timeinfo(id) {
	var paras = {
		id : id
	};
	$.get('/qb2c_timeinfo', paras, function(data) {
		for ( var i in data) {
			console.log(i);
			if (data[i]['status'] == "error") {
				$('#yadata_' + data[i]['id']).html(
						"<strong class='status_error'>" + data[i]['info']
								+ "</strong>");
			} else {
				$('#yadata_' + data[i]['id']).html(
						"<strong class='status_ok'>" + data[i]['info']
								+ "</strong>");
			}
		}
	}, 'json');
}

$(document)
		.ready(
				function() {

					if ($.browser.msie && $.browser.version == "6.0") {
						var html = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" id="sound" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,14,0" width="1" height="1"><param name="movie" value="../static/sound.swf"/><param name="quality" value="high"/><param name="bgcolor" value="#ffffff"/></object>';
						$("#sound-container").html(html);
					} else {
						var html = '<embed src="../static/sound.swf" quality="high" bgcolor="#ffffff" width="1" height="1" type="application/x-shockwave-flash" name="sound" id="sound" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>';
						$("#sound-container").html(html);
					}

					$('.J_add_ya_data').click(function() {
						p = $(this).attr('name');
						showAlert("确认要上传当前策略吗？" + p, 'confirm', function() {
							FnoDataSubmit(p);
						});
					});
					$('.J_del_chupiao').click(function() {
						p = $(this).attr('name');
						showAlert("确认要删除当前航班信息吗？" + p, 'confirm', function() {
							window.location.href = '/del_b2c_order?id=' + p;
						});
					});
					$('.J_del_quna_jinjia_rule').click(function() {
						p = $(this).attr('name');
						showAlert("确认要删除当前询价规则吗？" + p, 'confirm', function() {
							DelQunarJinjiaRuleSubmit(p);
						});
					});

					/*
					 * $('.J_show_log_detail').click(function(){ id =
					 * $(this).attr('name'); showLoading('正在查询订单信息'); var paras = {
					 * id : id }; $.get('/b2c_fetch_detail', paras,
					 * function(data){ hideLoading(); var ticket_info = data;
					 * showAlert(data['msg'], 'confirm', function(){ }, null,
					 * function(){ $('#ticket_no').focus(); }); },'json');
					 * 
					 * });
					 */

					$('.J_show_log_detail').click(function() {
						p = $(this).attr('name');
						console.log(p);
						// $.get('/jsonyalog?id='+p, function(data){
						$.get('/b2c_fetch_detail?id=' + p, function(data) {
							showAlert(data);
						});

					});

					/*
					 * $('.J_show_log_detail').click(function(){ p =
					 * $(this).attr('name'); console.log(p); fetchOrder(p);
					 * $.get('/qb2c_jsonyalog?id='+p, function(data){
					 * showAlert(data); });
					 * 
					 * }); function fetchOrder(id){ showLoading('正在提交航班信息'); var
					 * paras = { id : id }; $.get('/b2c_fetch_detail', paras,
					 * function(data){ hideLoading(); if(data == 'ok'){
					 * $('#yadata_'+uri).html("获取出票结果,请等待");
					 * //window.document.getElementById('sound').TCallLabel('/',
					 * "imReceive"); } }); }
					 */

					function FnoDataSubmit(uri) {
						showLoading('正在提交航班信息');
						var paras = {
							id : uri
						};
						$.get('/qb2c_toqueue', paras, function(data) {
							hideLoading();
							if (data == 'ok') {
								$('#yadata_' + uri).html("开始搜索,请等待");
								window.document.getElementById('sound')
										.TCallLabel('/', "imReceive");
							} else {
								data = eval('(' + data + ')');
							}
						});
					}

					function DelQunarJinjiaRuleSubmit(uri) {
						showLoading('正在提交删除任务');
						var paras = {
							id : uri
						};
						$.get('./rules/quote-rule!delRule.do', paras, function(
								data) {
							hideLoading();
							showAlert(data);
						});
					}

					/** **********************search****************************** */
					$('#orderNo').change(function() {
						setFilterCondition($(this), true);
					}).keypress(function(e) {
						if (e.which == 13) {
							$('#bus_filter_btn').click();
						}
					}).val(getUrlPara('orderNo'));
					$('#contactName').change(function() {
						setFilterCondition($(this), true);
					}).keypress(function(e) {
						if (e.which == 13) {
							$('#bus_filter_btn').click();
						}
					}).val(getUrlPara('contactName'));

					function setFilterCondition($input, check_no_condition) {
						var is_show = $input.closest('p').hasClass('none');
						var name = {
							orderNo : '订单号',
							contactName : '客人姓名'
						};
						var id = $input.attr('id');
						var $label = $('#' + id + '_label');
						if ($input.val() != '' && !is_show) {
							$label.text(name[id] + '：' + $input.val())
									.removeClass('none');
						} else {
							$label.addClass('none');
						}
						if (check_no_condition) {
							chechNoCondition();
						}
					}

					function chechNoCondition() {
						if ($('#orderNo_label').hasClass('none')
								&& $('#contactName_label').hasClass('none')) {
							$('#no_query_filter_condition').removeClass('none');
						} else {
							$('#no_query_filter_condition').addClass('none');
						}
					}

					function getUrlPara(key) {
						var re = eval('/' + key + '=[^&]*/');
						var re_match = window.location.search.match(re);
						if (re_match == null) {
							return '';
						} else {
							var key_value = re_match[0].split('=');
							return key_value[1];
						}
					}
					$('#bus_filter_btn')
							.click(
									function() {
										$('#cookieno').val(
												$.trim($('#cookieno').val()));
										var cookieno = $('#cookieno').val();
										if (cookieno == '') {
											showAlert('查找条件不能为空！');
											return;
										}
										var new_url;
										var orderNo_url = cookieno;
										new_url = '../airlogin/airline-login!findCookie.do?page=1&cookieno='
												+ orderNo_url;
										window.location.href = new_url;
									});

					/** **********************search****************************** */

					$('#bus_jd_flt_query_btn')
							.click(
									function() {
										$('#depcode').val(
												$.trim($('#depcode').val()));
										$('#arrcode').val(
												$.trim($('#arrcode').val()));
										$('#cabincode').val(
												$.trim($('#cabincode').val()));
										$('#depdate').val(
												$.trim($('#depdate').val()));
										$('#createtime').val(
												$.trim($('#createtime').val()));
										$('#aircode').val(
												$.trim($('#aircode').val()));
										var depcode = $('#depcode').val();
										var arrcode = $('#arrcode').val();
										var cabincode = $('#cabincode').val();
										var depdate = $('#depdate').val();
										var createtime = $('#createtime').val();
										var aircode = $('#aircode').val();
										if (depcode == '' || arrcode == ''
												|| aircode == '') {
											showAlert('查询条件不能为空<br/>【起飞城市，到达城市，承运人】！');
											return;
										}
										var new_url;
										new_url = '../airb2bflt/product-air-temp!querytempflight.do?depcode='
												+ depcode
												+ '&arrcode='
												+ arrcode
												+ '&cabincode='
												+ cabincode
												+ '&depdate='
												+ depdate
												+ '&createtime='
												+ createtime
												+ '&aircode='
												+ aircode;
										window.location.href = new_url;
									});

					/** --------------查询询价规则设置页面-----------------* */
					$('#bus_qunar_jinjia_rule_list_btn')
							.click(
									function() {
										$('#fromcity').val(
												$.trim($('#fromcity').val()));
										$('#tocity').val(
												$.trim($('#tocity').val()));
										$('#aircode').val(
												$.trim($('#aircode').val()));
										$('#cabincode').val(
												$.trim($('#cabincode').val()));
										$('#depdate').val(
												$.trim($('#depdate').val()));
										$('#tfaceprice').val(
												$.trim($('#tfaceprice').val()));
										var fromcity = $('#fromcity').val();
										var tocity = $('#tocity').val();
										var aircode = $('#aircode').val();
										var cabincode = $('#cabincode').val();
										var depdate = $('#depdate').val();
										var tfaceprice = $('#tfaceprice').val();
										if (fromcity == '' || tocity == ''
												|| aircode == ''
												|| cabincode == ''
												|| depdate == '') {
											showAlert('查询条件不能为空<br/>【起飞城市，到达城市，承运人，舱位，起飞日期】！');
											return;
										}
										var new_url;
										new_url = '../rules/quote-rule!toRulePage.do?fromcity='
												+ fromcity
												+ '&tocity='
												+ tocity
												+ '&aircode='
												+ aircode
												+ '&cabincode='
												+ cabincode
												+ '&depdate='
												+ depdate
												+ '&tfaceprice='
												+ tfaceprice;
										window.location.href = new_url;
									});
					/** --------------查询NFD 列表页面-----------------* */
					$('#nfd_list_id001_btn')
							.click(
									function() {
										$('#aircode').val(
												$.trim($('#aircode').val()));
										$('#fromcity').val(
												$.trim($('#fromcity').val()));
										$('#tocity').val(
												$.trim($('#tocity').val()));
										$('#cabincode').val(
												$.trim($('#cabincode').val()));
										$('#depdate').val(
												$.trim($('#depdate').val()));
										$('#tfaceprice').val(
												$.trim($('#tfaceprice').val()));
										var fromcity = $('#fromcity').val();
										var tocity = $('#tocity').val();
										var aircode = $('#aircode').val();
										var cabincode = $('#cabincode').val();
										var depdate = $('#depdate').val();
										var tfaceprice = $('#tfaceprice').val();
										if (aircode == '' || cabincode == '') {
											showAlert('查询条件不能为空<br/>【承运人，舱位】！');
											return;
										}
										var new_url;
										new_url = '../nfd/nfd-handle!nfdList.do?fromcity='
												+ fromcity
												+ '&tocity='
												+ tocity
												+ '&aircode='
												+ aircode
												+ '&cabincode='
												+ cabincode
												+ '&depdate='
												+ depdate
												+ '&tfaceprice='
												+ tfaceprice;
										window.location.href = new_url;
									});

					/** --------------查询下载 列表页面-----------------* */
					$('#file_list_forload02')
							.click(
									function() {
										$('#fileType').val(
												$.trim($('#fileType').val()));
										$('#createTime').val(
												$.trim($('#createTime').val()));
										var fileType = $('#fileType').val();
										var createTime = $('#createTime').val();
										var new_url;
										new_url = '../file/file-info!queryFilesForLoad.do?fileType='
												+ fileType
												+ '&createTime='
												+ createTime;
										window.location.href = new_url;
									});

					$('#bus_qunar_jinjia_rule_add_btn').click(function() {
						var new_url;
						new_url = '../rules/quote-rule!toSavePage.do';
						window.location.href = new_url;
					});

					$('#bus_qunar_order_xunjia_btn')
							.click(
									function() {
										$('#createtime').val(
												$.trim($('#createtime').val()));
										var createtime = $('#createtime').val();
										var new_url;
										new_url = '../qunarxunjia/qunar-version!queryOrderxxok.do?createtime='
												+ createtime;
										window.location.href = new_url;
									});

				});

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

$(document).ready(function() {
	if ($('#page_name').val() == 'chaxun_qunar_xunjia_order') {
		console.log("fffff");
		setDatepickerChinese();
		$('#createtime').datepicker({
			maxDate : 90,
			onClose : function(selectedDate) {
				$('#createtime').datepicker('option', 'minDate', selectedDate);
			}
		}).keypress(function(e) {
			if (e.which == 13) {
				$('#fuzzy_filter_btn').click();
			}
		});
	}

	if ($('#page_name').val() == 'quote_rule_list') {
		console.log("fffff");
		setDatepickerChinese();
		$('#depdate').datepicker({
			maxDate : 90,
			onClose : function(selectedDate) {
				$('#depdate').datepicker('option', 'minDate', selectedDate);
			}
		}).keypress(function(e) {
			if (e.which == 13) {
				$('#fuzzy_filter_btn').click();
			}
		});
	}

});
