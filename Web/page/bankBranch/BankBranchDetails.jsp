<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>网点简介</title>
<style type="text/css">
</style>
</head>
<body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
		action="saveBankBranch.do">
		<input name="id" type="hidden" value="${mp.id}">
		<input name="branch_code" type="hidden" value="${mp.branch_code}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label  class="Validform_label"> 银行名称: </label></td>
				<td><select name="bank_code" class="easyui-combobox" style="width:350px;" datatype="*"
					nullmsg="请选择银行名称！" errormsg="请选择银行名称！">
						<c:forEach items='${bankList}' var="bank">
							<option value='${bank.code}'>${bank.bank_name}</option>
						</c:forEach>
				</select> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label  class="Validform_label"> 银行网点名称: </label></td>
				<td><input name="branch_name" class="inputxt"
					style="width:80%;" value="${mp.branch_name}" datatype="*" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label  class="Validform_label"> 银行网点标题: </label></td>
				<td><input name="branch_title" class="inputxt"
					style=" width:80%;" value="${mp.branch_title}" datatype="*" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label  class="Validform_label"> 银行网点详情: </label></td>
				<td><input name="branch_desc" value="${mp.branch_desc}"
					class="inputxt" style=" width:80%;" datatype="*" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label  class="Validform_label"> 网点地址: </label></td>
				<td><input name="branch_address" class="inputxt"
					style=" width:80%;" value="${mp.branch_address}" /> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label  class="Validform_label"> 排序值: </label></td>
				<td><input name="order_value" class="inputxt"
					style=" width:80%;" value="${mp.order_value}" datatype="n" /> <span
					class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
