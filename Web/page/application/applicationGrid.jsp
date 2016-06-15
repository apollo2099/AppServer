<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="bankGrid" sqlMap="com.taozhu.persistence.AppMapper.commonQuery" queryMode="group" checkbox="true">
	<t:queryCol title="应用名称" field="app_name"/>
	<t:queryCol title="应用版本" field="app_version"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="app_name" title="应用名称" width="250"/>
	<t:dgCol field="app_version" title="应用版本" width="200"/>
	<t:dgCol field="app_url" title="应用下载地址" width="400"/>
	<t:dgCol field="remark" title="更新备注" width="200"/>
	<t:dgToolBar title="新增" icon="icon-add" url="applicationWebController.do?showApplication" funname="add" height="250" />
	<t:dgToolBar title="修改" icon="icon-edit" url="applicationWebController.do?showApplication" funname="update"  height="250"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="applicationWebController.do?deleteApplication" funname="deleteALLSelect" />
</t:datagrid>
