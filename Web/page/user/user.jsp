<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>用户/业务经理表单信息</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#fileAddPic").bind("change",function() {
			$.ajaxFileUpload({
						url : 'upBannerPhotoController.do?upload&dir=fileServer', //用于文件上传的服务器端请求地址
						secureuri : false, //是否需要安全协议，一般设置为false
						fileElementId : 'fileAddPic', //文件上传域的ID
						dataType : 'string', //返回值类型 一般设置为json
						success : function(data, status) //服务器成功响应处理函数
						{
							$("#head_image").val(GLOBAL_PATH+ "fileServer/"+ data);
							$("#selectImage").attr("src",GLOBAL_PATH+ "fileServer/"+ data);
						},
						error : function(data,status, e)//服务器响应失败处理函数
						{
							alert("图片上传失败");
						}
					});
		});
		$("#userType").combobox({
			onChange :function(n1,o){
				if(n1==1){
					 $("#bankCodeTr").css('display' ,'');  
				     $("#branchCodeTr").css('display' ,''); 
				     $("#officeTr").css('display' ,'');   
				}else{
					$("#bankCodeTr").css('display' ,'none');  
				     $("#branchCodeTr").css('display' ,'none'); 
				     $("#officeTr").css('display' ,'none'); 
				}
			}
		});
		$("#bank").combobox({
			onChange : function(n1,o){
				$("#branch").combobox({
			  		valueField: 'branch_code',
					textField: 'branch_name',
			  		url:"queryBankBranchByBank.do?bank_code="+ n1
				});
			}
		});
	});
</script>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="userWebController.do?saveUser">
		<input name="id" type="hidden" value="${mp.id}">
		<input name="user_card_path" required="required" type="hidden"
			value="${mp.user_card_path}" />
		<input name="head_image" id="head_image" required="required"
			type="hidden" value="${mp.head_image}" />
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<c:if test="${empty mp.id }">
				<tr>
					<td width="30%" nowrap><label class="Validform_label">
							登录帐号: </label></td>
					<td><input name="account" value="${mp.account}"
						style="width:80%;" datatype="*" /> <span
						class="Validform_checktip"></span></td>
				</tr>
				<tr>
					<td width="30%" nowrap><label class="Validform_label">
							密码: </label></td>
					<td><input name="password" type="password"
						value="${mp.password}" style="width:80%;" datatype="*6-15"
						errormsg="密码范围在6~15位之间！" /> <span class="Validform_checktip"></span></td>
				</tr>
			</c:if>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						昵称: </label></td>
				<td><input name="nick_name" required="required"
					value="${mp.nick_name}" datatype="*" style="width:80%;" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						性别: </label></td>
				<td><input name="sex" required="required" type="radio"
					datatype="*" value="1"
					<c:if test="${mp.sex==1 }">checked="checked"</c:if> />男 <input
					name="sex" required="required" type="radio" value="2"
					<c:if test="${mp.sex==2 }">checked="checked"</c:if> />女 <input
					name="sex" required="required" type="radio" value="0"
					<c:if test="${mp.sex==0 }">checked="checked"</c:if> />不详 <span
					class="Validform_checktip"></span></td>
			</tr>


			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						手机： </label></td>
				<td><input name="mobile" required="required" datatype="m"
					style="width:80%;" value="${mp.mobile}" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						电话: </label></td>
				<td><input name="tel" required="required" style="width:80%;"
					value="${mp.tel}" /> <span class="Validform_checktip"></span></td>
			</tr>
			<c:if test="${not empty mp.id }">
				<tr>
					<td width="30%" nowrap><label class="Validform_label">
							用户类型: </label></td>
					<td>
					<select name="user_type" class="easyui-combobox" style="width:350px;" id="userType" datatype="*" >
								<option value="1" <c:if test="${mp.user_type ==1 }">selected="selected"</c:if>>业务经理</option>
								<option value="2" <c:if test="${mp.user_type ==2 }">selected="selected"</c:if>>普通用户</option>
					</select> 
					<span class="Validform_checktip"></span></td>
				</tr>
				<tr>
					<td width="30%" nowrap><label class="Validform_label">
							经度: </label></td>
					<td><input name="longitude" style="width:80%;"
						required="required" value="${mp.longitude}" readonly="readonly" />
						<span class="Validform_checktip"></span></td>
				</tr>
				<tr>
					<td width="30%" nowrap><label class="Validform_label">
							纬度: </label></td>
					<td><input name="latitude" style="width:80%;"
						required="required" value="${mp.latitude}" readonly="readonly" />
						<span class="Validform_checktip"></span></td>
				</tr>
				<tr>
					<td width="30%" nowrap><label class="Validform_label">
							注册时间: </label></td>
					<td><input name="create_time" style="width:80%;"
						required="required" value="${mp.create_time}" readonly="readonly" />
						<span class="Validform_checktip"></span></td>
				</tr>
			</c:if>
				<tr id="bankCodeTr" <c:if test="${mp.user_type ==2}">style="display:none "</c:if>>
					<td width="30%" nowrap><label class="Validform_label">
							所属银行: </label></td>
					<td><select id="bank" name="bank_code" class="easyui-combobox" style="width:350px;" datatype="*">
							<option>=请选择=</option>
							<c:forEach items="${bankList }" var="b">
								<option value="${b.code }"
									<c:if test="${b.code==bankMap.code }">selected="selected"</c:if>>${b.bank_name}</option>
							</c:forEach>
					</select> <span class="Validform_checktip"></span></td>
				</tr>
				<tr id="branchCodeTr" <c:if test="${mp.user_type ==2}">style="display:none "</c:if>>
					<td width="30%" nowrap><label class="Validform_label">
							所属网点: </label></td>
					<td><select name="branch_code" class="easyui-combobox" style="width:350px;" id="branch" datatype="*">
							<option>=请选择=</option>
							<c:forEach items="${branchList }" var="branch">
								<option value="${branch.code }"
									<c:if test="${mp.branch_code==branch.code }">selected="selected"</c:if>>${branch.branch_name}</option>
							</c:forEach>
					</select> <span class="Validform_checktip"></span></td>
				</tr>
				<tr id="officeTr" <c:if test="${mp.user_type ==2}">style="display:none "</c:if>>
					<td width="30%" nowrap><label class="Validform_label">
							个人职位： </label></td>
					<td><input name="office" required="required" datatype="*"
						style="width:80%;" value="${mp.office}" /> <span
						class="Validform_checktip"></span></td>
				</tr>	
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						是否允许聊天: </label></td>
				<td><input name="is_allow" required="required" type="radio"
					datatype="*" value="1"
					<c:if test="${mp.is_allow==1 }">checked="checked"</c:if> />是 <input
					name="is_allow" required="required" type="radio" value="0"
					<c:if test="${mp.is_allow==0 }">checked="checked"</c:if> />否 <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						个人简介: </label></td>
				<td><textarea name="introduction" id="introduction"
						style="width:80%;" rows="5" cols="60">${mp.introduction}</textarea>
					<span class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						头像: </label></td>
				<td>
					<form id='head_form' name='head_form' method="post"
						enctype="multipart/form-data">
						<input type="file" name="fileAddPic" id="fileAddPic">
					</form> <img id="selectImage'" alt="" src="${mp.head_image }">
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
