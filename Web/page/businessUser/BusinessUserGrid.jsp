<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="bankGrid" sqlMap="com.taozhu.persistence.BusinessUserMapper.queryBusinessUserList" queryMode="group" checkbox="true">
	<t:queryCol title="子业务名称" field="sub_name"/>
	<t:queryCol title="业务经理" field="nick_name"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="sub_name" title="子业务名称" width="250"/>
	<t:dgCol field="nick_name" title="业务经理" width="200"/>
	<t:dgToolBar title="新增" icon="icon-add" url="businessUserController.do?showBusinessUser" funname="add"  />
	<t:dgToolBar title="修改" icon="icon-edit" url="businessUserController.do?showBusinessUser" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="businessUserController.do?deleteALLSelect" funname="deleteALLSelect" />
</t:datagrid>
