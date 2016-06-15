<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="userGrid" sqlMap="com.taozhu.persistence.UserMapper.queryWebUserAttention" queryMode="group" checkbox="true">
	<t:queryCol title="关注者账户" field="account"/>
	<t:queryCol title="关注者昵称" field="nickName"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="account" title="关注者账户" width="250"/>
	<t:dgCol field="nick_name" title="关注者昵称" width="200"/>
	<t:dgCol field="head_image" title="关注者头像" width="100"/>
	<t:dgCol field="attention_code" title="被关注的业务经理" width="200"/>
	<t:dgToolBar title="新增" icon="icon-add" url="userWebController.do?showUser" funname="add" height="500" />
	<t:dgToolBar title="修改" icon="icon-edit" url="userWebController.do?showUser" funname="update"  height="500"/>
</t:datagrid>
