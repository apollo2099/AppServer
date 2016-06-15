<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="userGrid" sqlMap="com.taozhu.persistence.UserMapper.queryUserManagerGrid" queryMode="group" checkbox="true">
	<t:queryCol title="帐号" field="account"/>
	<t:queryCol title="昵称" field="nickName"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="account" title="登录账户" width="250"/>
	<t:dgCol field="nick_name" title="昵称" width="200"/>
	<t:dgCol field="user_type_name" title="用户类型" width="100"/>
	<t:dgCol field="head_image" title="头像" width="100"/>
	<t:dgCol field="sex_name" title="性别" width="100" dictionary=""/>
	<t:dgCol field="is_allow_flag" title="是否允许聊天" width="100"/>
	<t:dgCol field="create_time" title="注册时间" width="150" formatter="yyyy-MM-dd hh:mm:ss"/>
	<t:dgToolBar title="新增" icon="icon-add" url="userWebController.do?showUser" funname="add" height="500" />
	<t:dgToolBar title="修改" icon="icon-edit" url="userWebController.do?showUser" funname="update"  height="500"/>
	<t:dgToolBar title="重置密码" icon="icon-remove" url="userWebController.do?showUserPwd" funname="update"  height="300"/>
	<t:dgToolBar title="批量导入" icon="icon-add" url="userWebController.do?userImport" funname="detail" height="450"  />
	<t:dgToolBar title="删除" icon="icon-remove" url="userWebController.do?deleteUser" funname="deleteALLSelect" />
</t:datagrid>
