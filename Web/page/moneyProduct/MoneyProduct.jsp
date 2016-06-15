<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>贷款信息</title>
  </head>
  <body>
	<t:formvalid formid="formobj" layout="div" dialog="true" refresh="true" action="MoneyProductWebController.do?saveMoneyProduct">
		<input name="id" type="hidden" value="${mp.id}">
		<table border="0" width="99%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						产品名称: </label></td>
				<td><input name="product_name" class="inputxt"
					value="${mp.product_name}" datatype="*" style="width:80%;"/> <span class="Validform_checktip"></span></td>
			</tr>

			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						产品编码: </label></td>
				<td><input name="product_code" class="inputxt"
					value="${mp.product_code}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						收益类型: </label></td>
				<td><input name="earnings_type" class="inputxt"
					value="${mp.earnings_type}" datatype="*" style="width:80%;"/> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						申购赎回时间: </label></td>
				<td><textarea name="subscribe" id="subscribe" rows="15"
						cols="100">${mp.subscribe}</textarea> <span
					class="Validform_checktip"></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						首次起点金额: </label></td>
				<td><textarea name="origin_money" id="origin_money" rows="15"
						cols="100">${mp.origin_money}</textarea><span
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
						赎回到账方式: </label></td>
				<td><textarea name="subscribe_way" id="subscribe_way" rows="15"
						cols="100">${mp.subscribe_way}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						交易渠道: </label></td>
				<td><textarea name="deal_trench" id="deal_trench" rows="15"
						cols="100">${mp.deal_trench}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
			<tr>
				<td width="30%" nowrap><label class="Validform_label">
						预期年化收益率: </label></td>
				<td><textarea name="yield_rate" id="yield_rate" rows="15"
						cols="100">${mp.yield_rate}</textarea> <span
					class="Validform_checktip"></span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>
