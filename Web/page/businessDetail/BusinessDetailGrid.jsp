<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="bankGrid" sqlMap="com.taozhu.persistence.BusinessDetailMapper.queryWebList" queryMode="group" checkbox="true">
	<t:queryCol title="子业务名称" field="sub_name"/>
	<t:queryCol title="子业务编码" field="code"/>
	<t:queryCol title="业务编码" field="business_code"/>
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="sub_name" title="子业务名称" width="250"/>
	<t:dgCol field="code" title="子业务编码" width="200"/>
	<t:dgCol field="sub_type" title="类型" width="50"/>
	<t:dgCol field="sub_images" title="图片" width="50" image="false" />
	<t:dgCol field="order_value" title="排序值" width="50"/>
	<t:dgCol field="create_time" title="创建时间" width="150" formatter="yyyy-MM-dd hh:mm:ss" />
	<t:dgCol field="business_code" title="业务编码" width="250"/>
	<t:dgToolBar title="新增" icon="icon-add" url="businessDetailWebController.do?showBusinessDetail" funname="add"  />
	<t:dgToolBar title="修改" icon="icon-edit" url="businessDetailWebController.do?showBusinessDetail" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="businessDetailWebController.do?deleteALLSelect" funname="deleteALLSelect" />
</t:datagrid>
