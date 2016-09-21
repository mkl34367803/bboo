<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	<div id="header">
		<p class="logo">
			<a href="/"><img alt="数据中心" src="" title="返回首页" /></a>
		</p>
		<div class="header_user_and_nav">
			<p class="header_user">

				<input id="vark_user_id" type="hidden" value="1073" /> 您好！<span>&bull;</span>欢迎您使用本系统
				<span>&bull;</span> 当前时间： <strong id="current_time">00:00:00</strong><span>&bull;</span>
				<a class="header_help" href="/help">帮助</a><span>&bull;</span> <a
					class="header_logout" href="/logout">退出</a> <input type="hidden"
					id="cur_timestamp" value="1447541384" /> <input id="open_time"
					type="hidden" value="8:00" /> <input id="close_time" type="hidden"
					value="16:30" />

			</p>

			<ul class="nav">
				<li><a class="nav_index" href="/">回到首页</a></li>
				<li>
					<div id="nav_system_wrap">
						<p>
							<a class="nav_system" href="javascript:void(0);">系统设置</a> <a
								class="nav_change_pwd" href="/changepwd">修改密码</a>
						</p>
					</div>
				</li>
				<li>
					<div id="nav_withdraw_wrap">
						<p>
							<a class="nav_withdraw" href="javascript:void(0);">政策管理</a> <a
								class="nav_withdraw_self"
								href="${ctx }/airlogin/airline-login!jumpLogin.do?aircode=JD">登陆JDB2B</a>
							<a class="nav_withdraw_history"
								href="${ctx }/airlogin/airline-login!jumpLogin.do?aircode=HU">登陆HUB2B</a>
							<a class="nav_withdraw_history"
								href="${ctx }/airlogin/airline-login!jumpLogin.do?aircode=3U">登陆3UB2B</a>
							<a class="nav_withdraw_history"
								href="${ctx }/airlogin/airline-login!findCookie.do">SESSION查询</a>
							<a class="nav_withdraw_history"
								href="${ctx }/airb2bflt/product-air-temp!toquerytempflight.do">航班查询</a>

						</p>
					</div>
				</li>
				<li>
					<div id="nav_system_wrap">
						<p>
							<a class="nav_system" href="javascript:void(0);">订单管理</a> <a
								class="nav_change_pwd" href="${ctx }/qunarxunjia/qunar-version!queryOrder.do">qunar询价订单</a>
						</p>
					</div>
				</li>
			</ul>

		</div>
	</div>
</body>
</html>
