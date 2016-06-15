<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="BankBranchDetailsGrid" sqlMap="com.taozhu.persistence.BankBranchMapper.queryBankBranchList" queryMode="group" checkbox="true">
	<t:queryCol title="网点标题" field="branch_title"/>
	<t:queryCol title="网点详情" field="branch_desc"/>
	<t:dgCol field="id" title="id" frozenColumn="true" hidden="true"/>
	<t:dgCol title="网点标题" field="branch_title" width="250"/>
	<t:dgCol title="网点code" field="branch_code" width="150"/>
	<t:dgCol title="网点地址" field="branch_address" width="250"/>
	<t:dgCol title="创建时间" field="create_time" formatter="yyyy-MM-dd hh:mm:ss" width="150"/>
	<t:dgCol title="网点详情编码" field="code" width="150"/>
	<t:dgToolBar title="新增" icon="icon-add" url="showBankBranch.do" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="showBankBranch.do" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="deleteALLSelect.do" funname="deleteALLSelect" />
</t:datagrid>
