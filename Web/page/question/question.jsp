<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>问题表单信息</title>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="saveQuestion.do">
		<input name="id" type="hidden" value="${mp.id}">
		<input name="code" type="hidden" value="${mp.code}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						子业务名称: </label></td>
				<td><select name="business_code" class="easyui-combobox"
					style="width:350px;" datatype="*">
						<c:forEach items="${businessList}" var="b">
							<option value="${b.code}"
								<c:if test="${b.code==mp.business_code}">selected="selected"</c:if>>${b.sub_name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						问题名称: </label></td>
				<td><input name="title" class="inputxt" value="${mp.title}"
					datatype="*" style="width:80%;"/> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						问题描述: </label></td>
				<td><textarea name="description" rows="5" cols="87">${mp.description}</textarea>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
