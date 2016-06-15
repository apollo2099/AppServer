<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="QuestionGrid" sqlMap="com.taozhu.persistence.QuestionMapper.queryQuestionList" queryMode="group" checkbox="true">
	<t:queryCol title="问题名称" field="title"/>
	<t:dgCol field="id" title="序号"/>
	<t:dgCol field="title" title="问题名称" width="200"/>
	<t:dgCol field="description" title="问题描述" width="300"/>
	<t:dgCol field="sub_name" title="业务名称" width="200"/>
	<t:dgCol field="create_time" title="生成时间" width="150" formatter="yyyy-MM-dd hh:mm:ss"/>
	<t:dgToolBar title="新增" icon="icon-add" url="showQuestion.do" funname="add" height="220" />
	<t:dgToolBar title="修改" icon="icon-edit" url="showQuestion.do" funname="update" height="220"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="deleteQuestion.do" funname="deleteALLSelect" />
</t:datagrid>
