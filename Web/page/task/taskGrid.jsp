<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="userGrid" sqlMap="com.taozhu.persistence.TaskMapper.queryList" queryMode="group" checkbox="true" idField="userGrid">
	<t:queryCol title="任务名称" field="task_name"/>	
	<t:dgCol field="id" title="序号"></t:dgCol>
	<t:dgCol field="task_name" title="任务名称" width="150"/>
	<t:dgCol field="target_object" title="目标实例" width="150"/>
	<t:dgCol field="target_method" title="目标方法" width="100"/>
	<t:dgCol field="target_date" title="定时目标时间" width="150"/>
	<t:dgCol field="statusName" title="状态" width="100" dictionary=""/>
	<t:dgCol field="description" title="描述" width="100"/>
	<t:dgCol field="create_time" title="创建时间" width="150" formatter="yyyy-MM-dd hh:mm:ss"/>
	<t:dgToolBar title="新增" icon="icon-add" url="taskWebController.do?showTask" funname="add" height="450" />
	<t:dgToolBar title="修改" icon="icon-edit" url="taskWebController.do?showTask" funname="update"  height="450"/>
	<t:dgToolBar title="删除" icon="icon-remove" url="taskWebController.do?deleteTask" funname="deleteALLSelect" />
    <t:dgToolBar title="任务状态控制" icon="icon-add" url="taskWebController.do?showTaskStatus"  funname="update"  height="95" />
</t:datagrid>

<%-- 
<script type="text/javascript">
function ajaxSelect(){
	var rowsData = $('#userGrid').datagrid('getSelections');
	if(rowsData.length!=-1){
		$.messager.alert('温馨提示','请选择一条记录操作');
	}
	var taskId=rowsData[0].id;
	var url="taskWebController.do?startTask&id="+id;
	openwindow("test", url, "userGrid", 1000, 450);	
}

</script>
--%>