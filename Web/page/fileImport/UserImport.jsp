<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	
	String pattt = request.getRealPath("/");
	String filePath = pattt+"page\\file\\user.xls";
	String downLoadPath = basePath+"download.do?file="+filePath+"&fileName=";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>taozhu资源管理系统-导入页面</title>
		<link href="<%=basePath%>/plug-in/css/default.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css"	href="<%=basePath%>/plug-in/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"	href="<%=basePath%>/plug-in/easyui/themes/icon.css" />
    	<link href="<%=basePath%>/plug-in/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=basePath%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/jquery.easyui.min.1.3.2.js"></script>
		<script type="text/javascript" src="<%=basePath%>/plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=basePath%>/plug-in/uploadify/swfobject.js"></script>
		<script type="text/javascript" src="<%=basePath%>/plug-in/uploadify/jquery.uploadify-3.1.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#file_upload').uploadify({
				uploader : '<%=basePath%>/import/index.do',
				swf:'<%=basePath%>/plug-in/uploadify/uploadify.swf',
				fileObjName : 'file',
				cancelImage : '<%=basePath%>/plug-in/uploadify/uploadify-cancel.png',
				queueID : 'fileQueue',
				queueSizeLimit : 1,
				fileTypeDesc : '',
				fileTypeExts : '*.xls',
				formData : {'templateId':'UserImport'},
				method : 'post',
				multi : true,
				debug : false,
				auto : false,
				buttonText : '浏览',
                removeTimeout   : 10,
                successTimeout : 60,
				onFallback:function(){//没有兼容的FLASH时触发
			         alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");
			     },
				onUploadSuccess : function(file, data, response) {//在每一个文件上传成功后触发
						 var htmlStr = "<label> 导入结果:</label>";
						 if(data){
							 if(data.success=="true"){
								 var templateCuid = data.templateCuid; 
								htmlStr += "<p>导入批次："+data.batchNo+",总数："+data.total+",已验证通过数据数:"+data.success+",数据错误数:"+data.error+"</p>";
							 	if(data.error!="0"){
							 		var errorExportUrl ='/exporterrors.do?templateId='+templateCuid+'&batchNo='+data.batchNo+'&boName=boName';
									htmlStr += "<table border=1 autoScroll='true'>";
								 	htmlStr += "<thead><tr><th>页数</th><th>行数</th><th>错误信息</th>></tr></thead>";
								 	htmlStr += "<tbody>";
									htmlStr +="<tr align='center'><td><BR>错误数据:<a href='#' onclick='window.location.href=\""+errorExportUrl+"\"' ><B>"+error+"</B></a> 条。";"</td></tr>";
								 	htmlStr += "</tbody>";
								 	htmlStr += "</table>";
							 	}else{
							 		htmlStr += "<p>导入批次："+data.batchNo+",总数："+data.total+",成功导入数据数:"+data.sucess+"</p>";
							 	}
							 }else{
							 	htmlStr += data.msg;
							 }
						 }
						 
						 $("#result").html(htmlStr);
			        },
				onUploadError : function(file, errorCode, errorMsg, errorString) {//上传失败时执行
			        },
			    onQueueComplete: function(queueData) {//在队列中的文件上传完成后触发
			        },
			    onSelectError:function(file, errorCode, errorMsg){
               		switch(errorCode) {
                   		case -100:
                       		alert("上传的文件数量已经超出系统限制的"+$('#upload').uploadify('settings','queueSizeLimit')+"个文件！");
                       		break;
                   		case -110:
                       		alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#upload').uploadify('settings','fileSizeLimit')+"大小！");
                      		 break;
                   		case -120:
                       		alert("文件 ["+file.name+"] 大小异常！");
                       		break;
                   		case -130:
                       		alert("文件 ["+file.name+"] 类型不正确！");
                      		break;
               		}
           		}
			});
		});
		
		</script>
		<style type="text/css">
		.STYLE3 {
			font-size: 36px;
			color: #36648B;
		}
		</style>
	</head>
	<body>
		<p class="STYLE3">
					用户信息导入
					<a href="<%=downLoadPath %>"><font color="red">模版下载</font></a>
		</p>
		
		<div id="result" style="font-size:12px;"/>
		<div id="fileQueue"/>
		<input type="file" name="file_upload" id="file_upload"/>
		<input type="button" value="开始上传" onclick="javascript:jQuery('#file_upload').uploadify('upload')"></input>
		<input type="button" value="取消所有上传" onclick="javascript:jQuery('#file_upload').uploadify('cancel')"></input>
	</body>
</html>