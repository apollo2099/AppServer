<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单编辑信息</title>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="userWebController.do?updateUserPwd">
		<input name="id" type="hidden" value="${mp.id}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						登录用户名: </label></td>
				<td><input name="account" class="inputxt" size="64"
					value="${mp.account}" readonly="readonly" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						旧密码: </label></td>
				<td><input name="userPassword" class="inputxt" type="password"
					size="64" /> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						新密码: </label></td>
				<td><input name="newPassword" class="inputxt" type="password"
					size="64" /> <span class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
