<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="messageAuthCodeGrid" sqlMap="com.taozhu.persistence.MessageAuthCodeMapper.queryList" queryMode="group" checkbox="true">
	<t:queryCol title="手机号码" field="mobile"/>
	<t:queryCol title="验证码" field="auth_code"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="mobile" title="手机号码" width="180"/>
	<t:dgCol field="auth_code" title="验证码" width="150"/>
	<t:dgCol field="create_time" title="创建时间" formatter="yyyy-MM-dd hh:mm:ss" width="200" />
	<t:dgCol field="status" title="状态" width="150" />

</t:datagrid>