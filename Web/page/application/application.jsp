<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>App应用信息</title>
 <script type="text/javascript">
    jQuery(document).ready(function() {
  		$("#fileAddPic").bind("change",function(){
  			window.top.$.messager.progress({
					text : '文件上传中....',
					interval : 300
				});
  			 $.ajaxFileUpload({
  				 url: 'upBannerPhotoController.do?uploadApk&dir=fileApp', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'fileAddPic', //文件上传域的ID
                    dataType: 'string', //返回值类型 一般设置为json
                    success: function (data, status) { //服务器成功响应处理函数
                    	var dataObj = eval("("+data+")");
                       $("#app_url").val(dataObj.fileUrl);
                       $("#selectApk").attr("href",dataObj.fileUrl);
                       $("#selectApk").text(dataObj.fileUrl);
                       $("#app_version").val(dataObj.versionCode);
                       window.top.$.messager.progress('close');
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                    	window.top.$.messager.progress('close');
                        alert("apk上传失败");
                    }
  			 });
  		});
  	});
    </script>
  </head>
  <body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="applicationWebController.do?saveApplication">
		<input name="id" type="hidden" value="${mp.id}">
		<input type="hidden" name="app_version" id="app_version"
			value="${mp.app_version}" />
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						应用名称: </label></td>
				<td><input name="app_name" 
					value="${mp.app_name}" datatype="*" style="width:80%;" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						更新备注: </label></td>
				<td><textarea name="remark" id="introduction" rows="5"
						cols="60">${mp.remark}</textarea> <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						上传apk: </label></td>
				<td>
					<form id='head_form' name='head_form' method="post"
						enctype="multipart/form-data">
						<input type="file" name="fileAddPic" id="fileAddPic" style="width:80%;">
					</form> <a id="selectApk" href="#">${mp.app_url}</a> <input
					name="app_url" id="app_url" type="hidden" value="${mp.app_url}"
					datatype="*" /> <span class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
