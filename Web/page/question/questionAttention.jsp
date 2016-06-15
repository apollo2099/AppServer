<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>问题答案表单信息</title>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="saveQuestionAttention.do">
		<input name="id" type="hidden" value="${mp.id}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						问题名称: </label></td>
				<td><select name="question_code" class="easyui-combobox"
					style="width:350px;" datatype="*">
						<c:forEach items="${questionList }" var="b">
							<option value="${b.code }"
								<c:if test="${b.code==mp.question_code}">selected="selected"</c:if>>${b.sub_name}---${b.title}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						问题答案: </label></td>
				<td><textarea name="attention" rows="5" cols="87">${mp.attention}</textarea>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
