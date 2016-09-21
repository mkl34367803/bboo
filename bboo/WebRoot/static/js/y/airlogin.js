$(document).ready(
		function() {
			if ($('#page_name').val() == 'longinairb2b') {
				console.log("fffff");
				var register_check_list = [ {
					'$input' : $('#loginName'),
					'name' : '登陆账号',
					'check_type' : [ 'empty' ]
				}, {
					'$input' : $('#loginPwd'),
					'name' : '登陆密码',
					'check_type' : [ 'empty' ]
				}, {
					'$input' : $('#vercode'),
					'name' : '验证码',
					'check_type' : [ 'empty' ]
				} ];
				function registerFormSubmit() {
					showLoading('正在努力登陆');
					// 回调
					subbtn();
					hideLoading('');
				}
				setCheck(register_check_list, checkShowTips,
						$('#register_submit'), registerFormSubmit, 'OK');
			}
		});

function subbtn() {

	var loginName = $("#loginName").val();
	var loginPwd = $("#loginPwd").val();
	var vercode = $("#vercode").val();
	var uuid = $("#uuid").val();
	var aircode = $("#aircode").val();

	$("#register_submit").val("正在登陆...");
	$.post("./airlogin/airline-login!login.do?t=" + Math.random(), {
		loginName : loginName,
		loginPwd : loginPwd,
		vercode : vercode,
		uuid : uuid,
		aircode : aircode
	}, function(data) {
		if (data.indexOf("SUCCESS|") != -1) {
			showAlert(data);
			// 刷新验证码
			guid(aircode);
		} else {
			showAlert("异常；" + data);
			$("#register_submit").removeAttr("disabled");// 将按钮可用
			$("#register_submit").val("登陆");
			guid(aircode);
		}
	});
}

function guid(aircode) {
	var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g,
			function(c) {

				var r = Math.random() * 16 | 0, v = c == 'x' ? r
						: (r & 0x3 | 0x8);

				return v.toString(16);

			});
	var srcx = './airlogin/airline-login!getImage.do?aircode=' + aircode
			+ '&uuid=' + uuid;
	$('#imgg').attr("src", srcx);
	$('#uuid').val(uuid);
}
