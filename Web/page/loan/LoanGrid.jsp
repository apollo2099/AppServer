<jsp:directive.page language="java" import="java.util.*" pageEncoding="UTF-8"/>

<jsp:directive.include file="/context/mytags.jsp"/>
<t:datagrid name="loanGrid" sqlMap="com.taozhu.persistence.LoanMapper.queryLoanList" queryMode="group" checkbox="true">
	<t:queryCol title="贷款名称" field="loan_name"/>
	<t:queryCol title="月利率" field="month_rate"/>
	<t:queryCol title="月费率" field="on_the_rate"/>
	<t:dgCol field="id" title="序号" hidden="true"/>
	<t:dgCol field="business_name" title="业务编码" width="80"/>
	<t:dgCol field="loan_name" title="贷款名称" width="100"/>
	<t:dgCol field="month_rate" title="月利率" width="100"/>
	<t:dgCol field="on_the_rate" title="月费率" width="100"/>
	<t:dgCol field="money" title="金额" width="60"/>
	<t:dgCol field="deadline" title="期限" width="60"/>
	<t:dgCol field="conditions" title="申请条件" width="200"/>
	<t:dgCol field="material" title="所需材料" width="200"/>
	<t:dgCol field="loan_icon" title="图标" width="200"/>
	<t:dgToolBar title="新增" icon="icon-add" url="LoanWebController.do?showLoan" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="LoanWebController.do?showLoan" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="LoanWebController.do?deleteALLSelect" funname="deleteALLSelect"/>
</t:datagrid>
