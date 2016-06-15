<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" CONTENT="IE=EmulateIE7"/>
    <title>金融人管理平台</title>
    <link rel="SHORTCUT ICON" href="style/images/favicon.ico" type="image/x-icon" />
		<link href="style/images/favicon.ico" rel="icon" type="image/x-icon" />
		<link href="style/images/favicon.ico" rel="Bookmark"/>
	<link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/icon.css">
	<script type="text/javascript" src="plug-in/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
  </head>  
  <script type="text/javascript">
  	 var contextPath = "${contextPath}";
  </script>
   <script type="text/javascript" src="page/main/mainframe.js"></script>
  <body class="easyui-layout"> 
       <div data-options="region:'north'" style="height:75px" >
       		<div class="easyui-layout" data-options="fit:true">
       		<div data-options="region:'west',border:true,split:false" style="background-image:url('style/images/header1.png');border-color:#17B4EE;width:278px;"></div>
       		<div data-options="region:'east',split:false" style="background:url('style/images/header2.png') repeat;border-color:#17B4EE;width:100px;">
       		<a href="login.do?loginOut">|退&nbsp;出|</a>
       		<a href="login.do?loginOut">个人信息|</a>
       		</div>
    		<div data-options="region:'center',border:false" style="background:url('style/images/header2.png') repeat;"></div>
    	
    		
       		</div>
       </div>
        <div data-options="region:'west',split:true" title="菜单导航" style="width:180px;">
        	<div id="menu-accordion" class="easyui-accordion" data-options="border:false,fit:true">
        		<c:forEach items="${menuList}" var="menu" varStatus="status">
        			<div title="${menu.function_name}" id="Menu${menu.id}" data-options="iconCls:'icon-add'" style="padding:10px;">
        				<ul id="sonMenu${menu.id}"></ul>
					</div>
        		</c:forEach>
			</div>
        </div>
        <div data-options="region:'center',iconCls:'icon-ok'">
	        <div id="mainTabs"  class="easyui-tabs" data-options="border:false,fit:true">
		        <div title="主页">
		        	<iframe height=99.5% width=100% src="page/main/firstPage.jsp" frameborder="0"></iframe>
		        </div>
		    </div>
        </div>
        
         <div data-options="region:'south',border:false" style="height:30px;background:url('style/images/header2.png') repeat;text-align:center;">
           Copyright&copy; 2014-2015 广州计然网络科技有限公司（HUA YANG INTERNATIONAL TECHNOLOGY LIMITED）.All Rights Reserved
        </div>
  </body>
</html>
