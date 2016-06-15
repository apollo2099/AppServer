<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>定时任务列表信息</title>

  </head>
  <body>
   <t:formvalid formid="formobj" layout="div" dialog="true" refresh="true" 
			action="taskWebController.do?updateTaskStatus">
			<input name="id" type="hidden" value="${mp.id}">
				<div class="form">
					<label class="Validform_label">
						任务名称:
					</label>
					<input name="task_name" class="inputxt" value="${mp.task_name}"  readonly="readonly"/>
					<span class="Validform_checktip"></span>
				</div>
				<div class="form">
					<label class="Validform_label">
						任务状态:
					</label>
					<select name="status">
					<option value="1" <c:if test="${mp.status==1 }">selected="selected"</c:if>>启动</option>
					<option value="0" <c:if test="${mp.status==0 }">selected="selected"</c:if>>停止</option>
					</select>
					<span class="Validform_checktip"></span>
				</div>
	</t:formvalid>
	<style type="text/css">
        .fitem input{
            width:360px;
        }
    </style>
  </body>
</html>
