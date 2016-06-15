<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="bankGrid" sqlMap="com.taozhu.persistence.BankMapper.queryList" queryMode="group" checkbox="true">
	<t:queryCol title="银行名称" field="bank_name"/>
	<t:queryCol title="内部编码" field="code"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="bank_name" title="银行名称" width="250"/>
	<t:dgCol field="code" title="内部编码" width="200"/>
	<t:dgToolBar title="新增" icon="icon-add" url="showBank.do" funname="add" height="100" />
	<t:dgToolBar title="修改" icon="icon-edit" url="showBank.do" funname="update"  height="100"/>
	<t:dgToolBar title="批量导入" icon="icon-add" url="bankImport.do" funname="add" height="450"  />
	<t:dgToolBar title="删除" icon="icon-remove" url="deleteBank.do" funname="deleteALLSelect" />
</t:datagrid>
