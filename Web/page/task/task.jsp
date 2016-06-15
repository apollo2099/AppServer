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
			action="taskWebController.do?saveTask">
			<input name="id" type="hidden" value="${mp.id}">
			<input name="status" type="hidden" value="${mp.status}" />
				<div class="form">
					<label class="Validform_label">
						任务名称:
					</label>
					<input name="task_name" class="inputxt"  datatype="*"
						value="${mp.task_name}" />
					<span class="Validform_checktip"></span>
				</div>
				<div class="form">
					<label class="Validform_label">
						目标实例:
					</label>
					<input name="target_object" class="inputxt" datatype="*" value="${mp.target_object}" />
					<span class="Validform_checktip"></span>
				</div>
				<div class="form">
					<label class="Validform_label">
						目标方法:
					</label>
					<input name="target_method" class="inputxt"  datatype="*" required="required" 
						value="${mp.target_method}" />
					<span class="Validform_checktip"></span>
				</div>
				<div class="form">
					<label class="Validform_label">
						定时目标时间：
					</label>
					<input name="target_date" class="inputxt" datatype="*" required="required" value="${mp.target_date}" />
					<span class="Validform_checktip"></span>
				</div>
				<c:if test="${not empty mp.id }">
				<div class="form">
					<label class="Validform_label">
						任务状态:
					</label>
					 <c:if test="${mp.status==1 }">启动</c:if>
					 <c:if test="${mp.status==0 }">停止</c:if>
					<span class="Validform_checktip"></span>
				</div>
				</c:if>
			  <div class="form">
					<label class="Validform_label">
						描述:
					</label>
						<textarea name="description"  id="description" rows="5" cols="60">${mp.description}</textarea>
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
