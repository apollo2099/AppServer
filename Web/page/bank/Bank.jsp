<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>银行表单信息</title>
  </head>
  <body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="saveBank.do">
		<input name="id" type="hidden" value="${mp.id}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						银行名称: </label></td>
				<td><input name="bank_name" class="inputxt"
					value="${mp.bank_name}" datatype="s5-16"
					errormsg="名称至少5个字符,最多16个字符！" /> <span class="Validform_checktip"></span>
				</td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
