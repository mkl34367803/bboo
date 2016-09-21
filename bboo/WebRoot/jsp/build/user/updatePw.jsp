<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../share/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>修改登录密码</title>
<%@include file="/jsp/share/JS.jsp"%>
<%@include file="/jsp/share/CSS.jsp"%>
<style type="text/css">
	.m-content {
		min-width: 930px; 
		margin-right: 0px;
	}
	.m-search .col {
		width: 650px;
	}
	.error {
	    width: 65px;
	    padding-right: 10px;
	    line-height: 28px;
	}
</style>
<script type="text/javascript">
	function checkPw(data) {
		// 8至30位的数字、字母和~!@#$%^&*字符组合的密码
		/* var passReg = new RegExp("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\.\\~]{8,30}$");
		var result = passReg.test(data);
		return result; */
		if(data == ""){
			return false;
		} else {
			return true;
		}
	}
	
	function checkIsEqual(dataA, dataB) {
		if(dataA == dataB){
			return true;
		} else {
			return false;
		}
	}
	
	$(document).ready(function() {
		
		$("#J_submit").click(function() {
			$(".error").text("");
			$(".warning").text("");
			
			var password = $("input[name='password']").val().trim();
			var newpass = $("input[name='newpass']").val().trim();
			var verifypass = $("input[name='verifypass']").val().trim();
			var flag = true;
			
			if(!checkPw(password)){
				$("#perror").text("*密码格式错误");
				flag = false;
			} 
			if(!checkPw(newpass)){
				//$("#nerror").text("*请输入8至30位的数字、字母和字符~!@#$%^&*组成的密码");
				$("#nerror").text("*密码不能为空");
				flag = false;
			} 
			if(!checkPw(verifypass)){
				//$("#verror").text("*请输入8至30位的数字、字母和字符~!@#$%^&*组成的密码");
				$("#verror").text("*密码不能为空");
				flag = false;
			} else if(checkIsEqual(password, newpass)){
				$("#nerror").text("*新密码不能与原密码一致");
				flag = false;
			} else if(!checkIsEqual(newpass, verifypass)){
				$("#verror").text("*两次密码不一致");
				flag = false;
			}
			
			if(flag) {
				oldPassword = $("input[name='password']").val(),
				newPassword = $("input[name='newpass']").val();
				$.post("${ctx }/configure/user!updatePassword.do",{
					oldPassword: oldPassword,
					newPassword: newPassword
				}, function(data) {
					if(data == "success"){
						//alert("密码修改成功");
						$(".warning").text("密码修改成功");
					} else if(data == "oldPassError"){
						//alert("原密码错误");
						$(".warning").text("原密码错误");
					} else if(data == "userNull"){
						//alert("获取用户信息失败");
						$(".warning").text("获取用户信息失败");
					} else if(data == "passNull"){
						///alert("密码不能为空");
						$(".warning").text("密码不能为空");
					} else {
						//alert("未知错误");
						$(".warning").text("未知错误");
					}
				});
			}
		});
		
		$("#J_reset").click(function() {
			$("input[type='password']").val("");
			$(".error").text("");
			$(".warning").text("");
		});
	});
</script>
</head>
<body>
<div class="m-main">
<div class="m-content">
	<div class="mod-hd" style="margin-top: 10px">
		<h2>修改密码</h2>
	</div>
	<div class="g-content">
		<h2 class="warning" style="color: red; margin-left: 65px;"></h2><br>
		<form>
			<table class="m-search g-table">
				<tr class="rows">
					<td class="col">
						<span class="label">用户名</span>
						<span class="label" style="text-align: left;"><%=request.getSession().getAttribute("SESSION_KEY_CURRENT_USERNAME")%></span>
					</td>
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">密码</span>
						<span class="g-ipt-text"><input name="password" type="password" /></span>
						<span class="error" id="perror" style="color: red;"></span>
					</td>
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">新密码</span>
						<span class="g-ipt-text"><input name="newpass" type="password" /></span>
						<span class="error" id="nerror" style="color: red;"></span>
					</td>
				</tr>
				<tr class="rows">
					<td class="col">
						<span class="label">确认密码</span>
						<span class="g-ipt-text"><input name="verifypass" type="password" /></span>
						<span class="error" id="verror" style="color: red;"></span>
					</td>
				</tr>
				<tr class="rows">
					<td class="col" style="margin-left: 75px;">
						<input class="g-btn" type="button" id="J_submit" value="确定" style="margin-right: 7px;" />
						<input class="g-btn" type="button" id="J_reset" value="重置" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
</div>
</body>
</html>
