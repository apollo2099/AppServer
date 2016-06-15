<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/icon.css">
    <script type="text/javascript" src="plug-in/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
  </body>
  <script type="text/javascript">
  	$(function(){
  		$.post("questionController.do?insertUserQues",
  {
    param:"{a:[{questionCode:1,isAnswer:0},{questionCode:2,isAnswer:1}],b:[{questionCode:3,isAnswer:0}]}"
  },
  function(data,status){
    alert("Data: " + data + "\nStatus: " + status);
  });
  	});
  </script>
</html>
