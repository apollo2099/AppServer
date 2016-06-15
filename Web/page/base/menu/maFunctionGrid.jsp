<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="maFunctionGrid" sqlMap="com.taozhu.persistence.MaFunctionMapper.commonQuery" queryMode="group" checkbox="true">
	<t:queryCol title="菜单名称" field="functionName"/>
	<t:queryCol title="父类ID" field="parentId"/>
	<t:dgCol field="id" title="序号" ></t:dgCol>
	<t:dgCol field="function_code" title="菜单code" width="180"/>
	<t:dgCol field="function_name" title="菜单名称" width="150"/>
	<t:dgCol field="function_page" title="菜单访问url" width="350" datebox="true"/>
	<t:dgCol field="parent_id" title="父类ID" width="150" />
	<t:dgToolBar title="新增" icon="icon-add" url="showMaFunction.do" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="showMaFunction.do" funname="update"/>
</t:datagrid>
