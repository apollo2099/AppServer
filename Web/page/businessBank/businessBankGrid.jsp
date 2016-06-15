<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="bankGrid" sqlMap="com.taozhu.persistence.BusinessBankMapper.queryBusinessBankList" queryMode="group" checkbox="true">
	<t:queryCol title="银行名称" field="bank_name"/>
	<t:queryCol title="子业务名称" field="sub_name"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="bank_name" title="银行名称" width="200"/>
	<t:dgCol field="sub_name" title="子业务名称" width="250"/>
	<t:dgToolBar title="新增" icon="icon-add" url="businessBankWebController.do?showBusinessBank" funname="add"   height="100"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="businessBankWebController.do?showBusinessBank" funname="update"  height="100"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="businessBankWebController.do?deleteALLSelect" funname="deleteALLSelect" />
</t:datagrid>
