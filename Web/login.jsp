<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("contextPath", path);
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title>系统登录</title>
		<style type="text/css">
-ms-filter
:
"progid
:DXImageTransform
.Microsoft
.AlphaImageLoader
(src='style/images/loginBg
.png
'
,
sizingMethod
='scale')";

		
filter
:progid
:DXImageTransform
.Microsoft
.AlphaImageLoader
(src='style/images/loginBg
.png
'
,
sizingMethod
='scale');
</style>
		<link rel="stylesheet" type="text/css"
			href="plug-in/easyui/themes/metro/blue_easyui.css">
		<link rel="stylesheet" type="text/css"
			href="plug-in/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="style/css/login.css">
		<script type="text/javascript" src="plug-in/easyui/jquery.min.js"></script>
		<script type="text/javascript"
			src="plug-in/easyui/jquery.easyui.min.js"></script>
	</head>
	<script type="text/javascript">
	var contextPath = "${contextPath}";
	var loginInfo = '${loginInfo}';
</script>
	<body class="easyui-layout">
		<div data-options="region:'center',fit:true" class="login_bg">
			<div id="loginFrame" class="login_frame">
				<form name="loginForm" id="loginForm" action="/login.do?verify" method="post">
					<div id="loginPanel" class="easyui-panel login_panel">
						<div id="usertip" style="margin-bottom: 10px">
							<input id="userCode" name="userCode"
								style="width: 100%; padding: 12px">
						</div>
						<div id="pwtip" style="margin-bottom: 20px">
							<input id="userPassword" name="userPassword" type="password"
								style="width: 100%; padding: 12px">
						</div>
						<div id="verfytip">
							<a id="loginBtn" style="padding: 5px 0px; width: 100%;"> <span
								style="font-size: 14px;">Login</span> </a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="tip" tabindex="-1" class="tooltip tooltip-top"
			style="height:25px;border-color: #95b8e7; border-style: solid; 
			border-width: 1px; border-radius: 5px; left: 20px; top: 20px; 
			display: none; z-index: 9003; background-color: #FFFFFF;">
			<div class="tooltip-content" style="padding-top:3px;">
				<span id="tipTxt" style="color: #BE0028;font-size:15px;"></span>
			</div>
			<div class="tooltip-arrow-outer"
				style="border-top-color: rgb(149, 184, 231);"></div>
			<div class="tooltip-arrow"
				style="border-top-color: rgb(149, 184, 231);"></div>
		</div>
	</body>
	<script type="text/javascript" src="page/main/login.js"></script>
	<script type="text/javascript">
	$('#userCode').focus(function() {
		var a = 1;
		alert(1);
	});
</script>
</html>