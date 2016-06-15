<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/icon.css">
	<script type="text/javascript" src="plug-in/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
	
  </head>
  <body class="easyui-layout"> 
  	<div data-options="region:'center',iconCls:'icon-ok'" style="background-image:url('style/images/firstPage.jpg');width:100%;heigth:100%;background-size:100% 100%;"></div>
  </body>
</html>
