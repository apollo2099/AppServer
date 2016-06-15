<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript" type="text/javascript">
// 以下方式直接跳转 到登录界面
window.location.href = 'login.jsp';
// 以下方式定时跳转 
// setTimeout("javascript:location.href='hello.html'", 5000);  
</script>
