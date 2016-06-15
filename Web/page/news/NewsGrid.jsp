<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="bankGrid" sqlMap="com.taozhu.persistence.NewsMapper.queryNewsList" queryMode="group" checkbox="true">
	<t:queryCol title="标题" field="title"/>
	<t:queryCol title="简介" field="introduction"/>
	<t:queryCol title="内容" field="content"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="title" title="标题"  width="250"/>
	<t:dgCol field="introduction" title="简介"  width="250"/>
	<t:dgCol field="web_url" title="网址"  width="250"/>
	<t:dgCol field="content" title="内容" width="250"/>
	<t:dgCol field="images" title="咨询图片" width="200"/>
	<t:dgToolBar title="新增" icon="icon-add" url="newsWebController.do?showNews" funname="add" height="200" />
	<t:dgToolBar title="修改" icon="icon-edit" url="newsWebController.do?showNews" funname="update"  height="200"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="newsWebController.do?deleteALLSelect" funname="deleteALLSelect" />
</t:datagrid>
