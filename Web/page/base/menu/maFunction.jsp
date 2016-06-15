<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>菜单编辑信息</title>
  </head>
  <body>
   <t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"  action="saveMaFunction.do">
			<input name="id" type="hidden" value="${mp.id}">
			<input name="functionCode" type="hidden" value="${mp.function_code}">
				<div class="form">
					<div>
						<label class="Validform_label">
							菜单名称:
						</label>
						<input name="functionName" class="inputxt" size="64" value="${mp.function_name}" />
						<span class="Validform_checktip"></span>
					</div>
					<div>
						<label class="Validform_label">
							菜单访问地址:
						</label>
						<input name="functionPage" class="inputxt" size="64" value="${mp.function_page}" />
						<span class="Validform_checktip"></span>
					</div>
					<label class="Validform_label">
							菜单父级ID:
						</label>
						<input name="parentId" class="inputxt" size="64" value="${mp.parent_id}" />
						<span class="Validform_checktip"></span>
					</div>
					
				</div>
	</t:formvalid>
	<style type="text/css">
        .fitem input{
            width:360px;
        }
    </style>
  </body>
</html>
