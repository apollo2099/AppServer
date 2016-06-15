<%@ taglib prefix="t" uri="/easyui-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%> 
<% 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="X-UA-Compatible" CONTENT="chrome=1"/>
<meta http-equiv="X-UA-Compatible" CONTENT="IE=EmulateIE7"/>
<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
<link href="<%=basePath%>/plug-in/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"	href="<%=basePath%>/plug-in/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"	href="<%=basePath%>/plug-in/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css"	href="<%=basePath%>/plug-in/css/common.css" />
<link rel="stylesheet" type="text/css"	href="<%=basePath%>/plug-in/css/accordion.css" />
<script type="text/javascript" src="<%=basePath%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/datagrid-scrollview.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/plugins/jquery-combobox.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/tools/dataformat.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/tools/curdtools.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/tools/jquery.pageinit.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plug-in/kindeditor-3.3.1/kindeditor.js"></script>
<script type="text/javascript" src="<%=basePath%>/page/common/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=basePath%>/page/common/js/global.js"></script>
