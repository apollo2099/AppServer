<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>银行业务信息</title>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="businessBankWebController.do?saveBusinessBank">
		<input name="id" type="hidden" value="${mp.id}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						银行名称: </label></td>
				<td><select name="bank_code" class="easyui-combobox"
					style="width:350px;" datatype="*">
						<c:forEach items="${bankList}" var="b">
							<option value="${b.code }"
								<c:if test="${b.code==mp.bank_code}">selected="selected"</c:if>>${b.bank_name}</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						业务名称: </label></td>
				<td><select name="business_code" class="easyui-combobox"
					style="width:350px;" datatype="*">
						<c:forEach items="${businessList}" var="b">
							<option value="${b.code }"
								<c:if test="${b.code==mp.sub_business_code}">selected="selected"</c:if>>${b.sub_name}</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
