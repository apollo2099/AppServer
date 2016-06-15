<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="QuestionAttentionGrid" sqlMap="com.taozhu.persistence.QuestionMapper.queryQuestionAttentionList" queryMode="group" checkbox="true">
	<t:queryCol title="问题名称" field="title"/>
	<t:dgCol field="id" title="序号"/>
	<t:dgCol field="title" title="问题名称" width="200"/>
	<t:dgCol field="attention" title="答案描述" width="300"/>
	<t:dgToolBar title="新增" icon="icon-add" url="showQuestionAttention.do" funname="add" height="220" />
	<t:dgToolBar title="修改" icon="icon-edit" url="showQuestionAttention.do" funname="update" height="220"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="deleteQuestionAttention.do" funname="deleteALLSelect" />
</t:datagrid>
