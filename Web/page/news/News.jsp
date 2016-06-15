<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>新闻信息</title>
    
<script type="text/javascript">
    jQuery(document).ready(function() {
  		$("#fileAddPic").bind("change",function(){
  			window.top.$.messager.progress({
					text : '文件上传中....',
					interval : 300
				});
  			 $.ajaxFileUpload({
  				 url: 'upBannerPhotoController.do?upload&dir=fileServer', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'fileAddPic', //文件上传域的ID
                    dataType: 'string', //返回值类型 一般设置为json
                    success: function (data, status){  //服务器成功响应处理函数
                       $("#selectApk").attr("href",GLOBAL_PATH+"fileServer/"+data);
                       $("#selectApk").text(GLOBAL_PATH+"fileServer/"+data);
                       $("#images").val(GLOBAL_PATH+"fileServer/"+data);
                       window.top.$.messager.progress('close');
                    },
                    error: function (data, status, e){//服务器响应失败处理函数
                    	window.top.$.messager.progress('close');
                        alert("图片上传失败");
                    }
  			 });
  		});
  	});
  </script>
    
  </head>
  <body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="newsWebController.do?saveNews">
		<input name="id" type="hidden" value="${mp.id}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						标题: </label></td>
				<td><input name="title" class="inputxt"
					value="${mp.title}" datatype="*" style="width:80%;"/> <span class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						简介: </label></td>
				<td><input name="introduction" class="inputxt"
					value="${mp.introduction}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						网址: </label></td>
				<td><input name="web_url" class="inputxt"
					value="${mp.web_url}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						内容: </label></td>
				<td><textarea name="content" rows="10" cols="67" datatype="*">${mp.content}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						咨询图片: </label></td>
				<td>
				<form id='head_form' name='head_form' method="post"
						enctype="multipart/form-data">
						<input type="file" name="fileAddPic" id="fileAddPic" style="width:80%;">
					</form> <a id="selectApk" href="#">${mp.images}</a> <input
					name="images" id="images" type="hidden" value="${mp.images}"
					datatype="*" /> <span class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
