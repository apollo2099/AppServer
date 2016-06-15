<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<title>广告信息</title>
	</head>
	<script type="text/javascript">
jQuery(document).ready(function() {
	$("#formobj").bind("submit",function(){
		$("#banner_desc").val(KE.util.getData("banner_desc1"));
	});
	 KE.show({
        id : 'banner_desc1' //TEXTAREA输入框的ID
    });
	 
	$("#fileAddPic").bind("change", function() {
		window.top.$.messager.progress({
					text : '文件上传中....',
					interval : 300
				});
		$.ajaxFileUpload( {
			url : 'upBannerPhotoController.do?upload&dir=fileServer', //用于文件上传的服务器端请求地址
			secureuri : false, //是否需要安全协议，一般设置为false
			fileElementId : 'fileAddPic', //文件上传域的ID
			dataType : 'string', //返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				var dataObj = eval("("+data+")");
				$("#banner_path").val(dataObj.fileUrl);
				$("#selectImage").attr("src",dataObj.fileUrl);
				window.top.$.messager.progress('close');
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				window.top.$.messager.progress('close');
				alert("图片上传失败");
			}
		});
	});

    
});
</script>
	<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="enterBanner.do?lv_banner">
		<input name="id" type="hidden" value="${mp.id}">
		<input name="banner_desc" id="banner_desc" type="hidden"
			value='${mp.banner_desc}' />
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						广告名称: </label></td>
				<td><input name="banner_name" id="banner_name" class="inputxt"
					value="${mp.banner_name}" datatype="*" /> <span
					class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						类&ensp;&ensp;&ensp;&ensp;型: </label></td>
				<td><input type="radio" name="banner_type" id="banner_type"
					datatype="*" errormsg="请选择类型！" value="1"
					<c:if test="${mp.banner_type==1 }">checked="checked"</c:if>>
					web </input> <input type="radio" name="banner_type" id="banner_type"
					value="2"
					<c:if test="${mp.banner_type==2 }">checked="checked"</c:if>>
					广告详情 </input> <span class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						web_url: </label></td>
				<td><input type="text" name="web_url" id="web_url"
					value="${mp.web_url}"></input> <span class="Validform_checktip"></span>
				</td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						是否显示: </label></td>
				<td><input type="radio" name="is_show" id="is_show"
					datatype="*" errormsg="请选择是否显示！" value="1"
					<c:if test="${mp.is_show==1 }">checked="checked"</c:if>> 是
					</input> <input type="radio" name="is_show" id="is_show" value="2"
					<c:if test="${mp.is_show==2 }">checked="checked"</c:if>> 否
					</input> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						排&ensp;序&ensp;值: </label></td>
				<td><input name="order_value" id="order_value" class="inputxt"
					value="${mp.order_value}" datatype="n" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						广告备注: </label></td>
				<td><textarea name="banner_desc2" id="banner_desc1" rows="15"
						cols="100">${mp.banner_desc}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						广告图片: </label></td>
				<td>
					<form id='banner_form' name='banner_form' method="post"
						enctype="multipart/form-data">
						<p>
							<input type="file" name="fileAddPic" id="fileAddPic" />
						</p>
						 <img id="selectImage" alt="" width="40px" height="30px"src="${mp.banner_path}"  />
						 <input name="banner_path" id="banner_path" type="hidden" value='${mp.banner_path}' datatype="*"/>
						 <span class="Validform_checktip"></span>
					</form>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
