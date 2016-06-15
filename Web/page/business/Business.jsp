<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>业务表单信息</title>
  </head>
  <body>
   <t:formvalid formid="formobj" layout="div" dialog="true" refresh="true" 
			action="saveBusiness.do">
			<input name="id" type="hidden" value="${mp.id}">
				<div class="form">
					<label class="Validform_label">
						业务名称:
					</label>
					<input name="business_name" class="inputxt"
						value="${mp.business_name}" datatype="*"/>
					<span class="Validform_checktip"></span>
				</div>
				<div class="form">
					<label class="Validform_label">
						排序值:
					</label>
					<input name="order_value" class="inputxt"
						value="${mp.order_value}" datatype="n"/>
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
