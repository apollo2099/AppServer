<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>业务明细信息</title>
    
<script type="text/javascript">
    jQuery(document).ready(function() {
  		$("#fileAddPic").bind("change",function(){
  			 $.ajaxFileUpload({
  				 url: 'upBannerPhotoController.do?upload&dir=fileServer', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: 'fileAddPic', //文件上传域的ID
                    dataType: 'string', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
  					   alert(data);
                       $("#sub_images").val(GLOBAL_PATH+"fileServer/"+data);
                    },
                    error: function (data, status, e)//服务器响应失败处理函数
                    {
                        alert("图片上传失败");
                    }
  			 });
  		});
  	});
  </script>
    
  </head>
  <body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="businessDetailWebController.do?saveBusinessDetail">
		<input name="id" type="hidden" value="${mp.id}">
		<input name="sub_images" id="sub_images" class="inputxt" type="hidden"
			value="${mp.sub_images}" />
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						业务名称: </label></td>
				<td><select name="business_code" class="easyui-combobox"
					style="width:350px;" datatype="*">
						<c:forEach items="${businessList }" var="b">
							<option value="${b.code }"
								<c:if test="${b.code==mp.business_code}">selected="selected"</c:if>>${b.business_name
								}</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						子业务名称: </label></td>
				<td><input name="sub_name" class="inputxt"
					value="${mp.sub_name}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						排序值: </label></td>
				<td><input name="order_value" class="inputxt"
					value="${mp.order_value}" datatype="n" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						子业务类型: </label></td>
				<td><input name="sub_type" id="sub_type" datatype="*"
					required="required" type="radio" value="1"
					<c:if test="${mp.sub_type==1 }">checked="checked"</c:if> />需要回答问题
					<input name="sub_type" id="sub_type" required="required"
					type="radio" value="2"
					<c:if test="${mp.sub_type==2 }">checked="checked"</c:if> />不需要回答问题
					<span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						是否主页显示: </label></td>
				<td><input name="is_visible" id="is_visible" datatype="*"
					required="required" type="radio" value="1"
					<c:if test="${mp.is_visible==1 }">checked="checked"</c:if> />是&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="is_visible" id="is_visible" required="required"
					type="radio" value="0"
					<c:if test="${mp.is_visible==0}">checked="checked"</c:if> />否 <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						业务简介: </label></td>
				<td><textarea name="sub_desc" rows="5" cols="87" datatype="*">${mp.sub_desc}</textarea></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						子业务图片: </label></td>
				<td>
					<form id='head_form' name='head_form' method="post"
						enctype="multipart/form-data">
						<input type="file" name="fileAddPic" id="fileAddPic"><img
							alt="" src="${mp.head_image }">
					</form> <img alt="" src="${mp.sub_images}" datatype="*"><span
					class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
