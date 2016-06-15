<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="InterfaceGrid" sqlMap="com.taozhu.persistence.BannerMapper.querybranchList" queryMode="group" checkbox="true" >
	<t:queryCol title="广告名称" field="banner_name"/>
	<t:queryCol title="广告图片路径" field="banner_path"/>
	<t:queryCol title="广告类型" field="banner_type"/>
	<t:queryCol title="网点Code" field="branch_code"/>
	
	<t:dgCol title="广告名称" field="banner_name"  width="100"/>
	<t:dgCol title="广告图片路径" field="banner_path" width="120"/>
	<t:dgCol title="广告备注" field="banner_desc" width="200"/>
	<t:dgCol title="广告类型" field="banner_type_name" width="80"/>
	<t:dgCol title="是否显示" field="is_show_name" width="50"/>
	<t:dgCol title="网点code" field="branch_name" width="50"/>
	<t:dgCol title="web_url" field="web_url"  width="150"/>
	<t:dgCol title="创建时间" field="create_time" width="80"/>
	<t:dgCol title="修改时间" field="modify_time" width="80"/>
	<t:dgCol title="修改人" field="modify_name"   width="80"/>
	<t:dgToolBar title="新增" icon="icon-add" url="enterBanner.do?enterBranchBanner" width="600" height="400" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="enterBanner.do?enterBranchBanner" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="enterBanner.do?deleteBranchBanner" funname="deleteALLSelect" />
</t:datagrid>
