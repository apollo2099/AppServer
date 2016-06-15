<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>贷款信息</title>
  </head>
<script type="text/javascript">
jQuery(document).ready(function() {
	$("#formobj").bind("submit",function(){
		$("#conditions").val(KE.util.getData("condition1"));
		$("#material").val(KE.util.getData("material1"));
	});
	 KE.show({
        id : 'condition1' //TEXTAREA输入框的ID
    });
	 KE.show({
        id : 'material1' //TEXTAREA输入框的ID
    });
	 
	$("#fileAddPic").bind("change", function() {
		window.top.$.messager.progress({
					text : '文件上传中....',
					interval : 300
				});
		$.ajaxFileUpload({
			url : 'upBannerPhotoController.do?upload&dir=fileServer', //用于文件上传的服务器端请求地址
			secureuri : false, //是否需要安全协议，一般设置为false
			fileElementId : 'fileAddPic', //文件上传域的ID
			dataType : 'string', //返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				var dataObj = eval("("+data+")");
				$("#selectImage").attr("href",dataObj.fileUrl);
                $("#selectImage").text(dataObj.fileUrl);
                $("#loan_icon").val(dataObj.fileUrl);
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
		action="LoanWebController.do?saveLoan">
		<input name="id" type="hidden" value="${mp.id}">
		<input name="conditions" id="conditions" type="hidden" value='${mp.conditions}' />
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						贷款名称: </label></td>
				<td><input name="loan_name" class="inputxt"
					value="${mp.loan_name}" datatype="*" style="width:80%;"/> <span class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						月利率: </label></td>
				<td><input name="month_rate" class="inputxt"
					value="${mp.month_rate}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						月费率: </label></td>
				<td><input name="on_the_rate" class="inputxt"
					value="${mp.on_the_rate}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						金额: </label></td>
				<td><input name="money" class="inputxt"
					value="${mp.money}" datatype="n" style="width:80%;"/> <span
					class="Validform_checktip"></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						期限: </label></td>
				<td><input name="deadline" class="inputxt"
					value="${mp.deadline}" datatype="n" style="width:80%;"/> <span
					class="Validform_checktip"></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						业务名称: </label></td>
				<td><select name="business_code" class="easyui-combobox"
					style="width:350px;" datatype="*">
						<c:forEach items="${businessList }" var="b">
							<option value="${b.code }"
								<c:if test="${b.code==mp.business_code}">selected="selected"</c:if>>${b.business_name}</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						申请条件: </label></td>
				<td><textarea name="condition2" id="condition1" rows="15"
						cols="100">${mp.condition}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						申请条件: </label></td>
				<td><textarea name="material2" id="material1" rows="15"
						cols="100">${mp.material}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						图标: </label></td>
				<td>
					<form id='loan_form' name='loan_form' method="post" enctype="multipart/form-data">
						<p>
							<input type="file" name="fileAddPic" id="fileAddPic" />
						</p>
						<a id="selectImage" href="#">${mp.loan_icon}</a>
						<input name="loan_icon" id="loan_icon" type="hidden" value="${mp.loan_icon}" datatype="*" /> <span class="Validform_checktip"></span>
					</form>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
