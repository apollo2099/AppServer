<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>菜单信息</title>
  </head>
  <body>
   <t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"
			action="saveInterface.do">
			<input name="id" type="hidden" value="${mp.id}">
			<div class="fitem">
					<label class="Validform_label">
						接口名称:
					</label>
					<input name="inf_name" class="inputxt"  style=" width:380px;"
						value="${mp.inf_name}" />
					<span class="Validform_checktip"></span>
				</div>
				<div class="fitem">
					<label class="Validform_label">
						接口请求地址:
					</label>
					<input name="inf_path" 
						value="${mp.inf_path}" class="inputxt" style=" width:380px;" />
					<span class="Validform_checktip"></span>
				</div>
				<div class="fitem">
					<label class="Validform_label">
						接口请求参数:
					</label>
					<textarea name="inf_param"  rows="5" cols="60"
						 >${mp.inf_param}</textarea>
					<span class="Validform_checktip"></span>
				</div>
				<div class="fitem">
					<label class="Validform_label">
						接口请求结果:
					</label>
					<textarea name="inf_result"  rows="5" cols="60"
						 >${mp.inf_result}</textarea>
					<span class="Validform_checktip"></span>
				</div>
				<div class="fitem">
					<label class="Validform_label">
						接口备注:
					</label>
					<textarea name="inf_remark"  rows="5" cols="60" >${mp.inf_remark}</textarea>
					<span class="Validform_checktip"></span>
				</div>
	</t:formvalid>
	<style type="text/css">
        #formobj{
            margin:0;
            padding:10px 30px;
        }
        .fitem{
            margin-bottom:5px;
        }
    </style>
  </body>
</html>
