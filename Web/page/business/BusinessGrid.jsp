<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="businessGrid" sqlMap="com.taozhu.persistence.BusinessMapper.queryList" queryMode="group" checkbox="true">
	<t:queryCol title="业务名称" field="business_name"/>
	<t:queryCol title="业务编码" field="code"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="business_name" title="业务名称" width="250"/>
	<t:dgCol field="order_value" title="排序值" width="100"/>
	<t:dgCol field="code" title="业务编码" width="200"/>
	<t:dgCol field="create_time" title="创建时间" formatter="yyyy-MM-dd hh:mm:ss" width="250"/>
	<t:dgCol field="modify_time" title="修改时间" formatter="yyyy-MM-dd hh:mm:ss" width="250"/>
	<t:dgToolBar title="新增" icon="icon-add" url="showBusiness.do" funname="add" height="250" />
	<t:dgToolBar title="修改" icon="icon-edit" url="showBusiness.do" funname="update"  height="250"/>
	<t:dgToolBar title="批量导入" icon="icon-add" url="businessImport.do" funname="add" height="450"  />
	<t:dgToolBar title="删除" icon="icon-remove" url="deleteBusinessALLSelect.do" funname="deleteALLSelect" />
</t:datagrid>
