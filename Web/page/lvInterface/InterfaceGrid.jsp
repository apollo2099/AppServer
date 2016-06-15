<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="InterfaceGrid" sqlMap="com.taozhu.persistence.LvInterfaceMapper.queryInterfaceList" queryMode="group" checkbox="true">
	<t:queryCol title="接口名称" field="inf_name"/>
	<t:queryCol title="接口地址" field="inf_path"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="inf_name" title="接口名称" width="80"/>
	<t:dgCol field="inf_path" title="接口请求地址" width="300"/>
	<t:dgCol field="inf_param" title="接口请求参数" width="200"/>
	<t:dgCol field="inf_result" title="接口请求结果" width="400"/>
	<t:dgCol field="inf_remark" title="接口备注" width="60"/>
	<t:dgToolBar title="新增" icon="icon-add" url="addorupdate.do" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="addorupdate.do" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="deleteALLSelect.do" funname="deleteALLSelect" />
</t:datagrid>
