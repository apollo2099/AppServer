<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <title>接口列表</title>
    
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/color.css">
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">
    
    
    
    
    
	<link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="plug-in/easyui/themes/icon.css">
    <script type="text/javascript" src="plug-in/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="plug-in/easyui/jquery.easyui.min.js"></script>
</head>
<body>

    
    <table id="dg" title="接口列表详情" class="easyui-datagrid" style="width:100%;height:97%"
            url="lvInterface.do?showInteracePage"
            toolbar="#toolbar" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true">
        <thead>
            <tr>
                <th field="id" width="80">序号</th>
                <th field="inf_name" width="100">接口名称</th>
                <th field="inf_path" width="280">接口请求地址</th>
                <th field="inf_param" width="180">接口请求参数</th>
                <th field="inf_result" width="250">接口请求结果</th>
                <th field="inf_remark" width="60">接口备注</th>
            </tr>
        </thead>
           <c:forEach items="${menuList}" var="menu" varStatus="status">
	  		<tr>
	        			<td>${menu.id}</td>
	        			<td>${menu.inf_name}</td>
	        			<td>${menu.inf_path}</td>
	        			<td>${menu.inf_param}</td>
	        			<td>${menu.inf_result}</td>
	        			<td>${menu.inf_remark}</td>
	        			
	        </tr>
        </c:forEach>
    </table>
    <div id="toolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a>
    </div>
    
    <div id="dlg" class="easyui-dialog" style="width:700px;height:650px;padding:10px 20px" closed="true" buttons="#dlg-buttons">
        <div class="ftitle">接口信息</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>接口名称:</label>
                <input name="inf_name" class="easyui-textbox" size="64" required="true">
            </div>
            <div class="fitem">
                <label>接口请求地址:</label>
                <input name="inf_path" class="easyui-textbox" size="64" required="true">
            </div>
            <div>
                <label>接口请求参数:</label>
                <textarea name="inf_param" rows="5" cols="60" required="true"></textarea>
            </div>
            <div>
                <label>接口请求结果:</label>
                <textarea name="inf_result" rows="5" cols="60" required="true"></textarea>
            </div>
            <div class="fitem">
                <label>接口备注:</label>
                <textarea name="inf_remark" rows="5" cols="60" required="true"></textarea>
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">Cancel</a>
    </div>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','Add Interface');
            $('#fm').form('clear');
            url = 'lvInterface.do?insert';
        }
        function editUser(){
        	alert("Edit");
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','Edit Interface');
                $('#fm').form('load',row);
                url = 'lvInterface.do?update&id='+row.id;
            }
        }
        function saveUser(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                    	alert("成功");
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
                    if (r){
                        $.post('destroy_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
        .fitem input{
            width:360px;
        }
    </style>
</body>
</html>