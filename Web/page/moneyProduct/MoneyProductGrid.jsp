<jsp:directive.page language="java" import="java.util.*" pageEncoding="UTF-8"/>

<jsp:directive.include file="/context/mytags.jsp"/>
<t:datagrid name="MenoyProductGrid" sqlMap="com.taozhu.persistence.MoneyProductMapper.queryMoneyProductList" queryMode="group" checkbox="true">
	<t:queryCol title="产品名称" field="product_name"/>
	<t:queryCol title="产品编码" field="product_code"/>
	<t:dgCol field="id" title="序号" hidden="true"/>
	<t:dgCol field="business_name" title="业务编码" width="80"/>
	<t:dgCol field="product_name" title="产品名称" width="300"/>
	<t:dgCol field="product_code" title="产品编码" width="200"/>
	<t:dgCol field="earnings_type" title="收益类型" width="400"/>
	<t:dgCol field="subscribe" title="申购赎回时间" width="60"/>
	<t:dgCol field="origin_money" title="首次起点金额" width="60"/>
	<t:dgCol field="subscribe_way" title="赎回到账方式" width="60"/>
	<t:dgCol field="deal_trench" title="交易渠道" width="60"/>
	<t:dgCol field="yield_rate" title="预期年化收益率" width="60"/>
	<t:dgToolBar title="新增" icon="icon-add" url="MoneyProductWebController.do?showMoneyProduct" funname="add"/>
	<t:dgToolBar title="修改" icon="icon-edit" url="MoneyProductWebController.do?showMoneyProduct" funname="update"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="MoneyProductWebController.do?deleteALLSelect" funname="deleteALLSelect"/>
</t:datagrid>
