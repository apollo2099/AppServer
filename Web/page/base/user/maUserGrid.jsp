<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="messageAuthCodeGrid" sqlMap="com.taozhu.persistence.MaUserMapper.commonQuery" queryMode="group" checkbox="true">
	<t:queryCol title="登录用户名" field="userCode"/>
	<t:queryCol title="真实姓名" field="userName"/>
	<t:queryCol title="E-mail" field="email"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="user_code" title="登录用户名" width="180"/>
	<t:dgCol field="user_name" title="姓名" width="150"/>
	<t:dgCol field="user_sex" title="性别" width="150" datebox="true"/>
	<t:dgCol field="email" title="邮件" width="150" />
	<t:dgCol field="telephone" title="联系电话" width="150" />
	<t:dgCol field="address" title="地址" width="150" />
	<t:dgToolBar title="新增" icon="icon-add" url="maUserController.do?showMaUser" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="maUserController.do?showMaUser" funname="update"/>
	<t:dgToolBar title="修改密码" icon="icon-edit" url="maUserController.do?showMaUserPwd" funname="update"/>
</t:datagrid>