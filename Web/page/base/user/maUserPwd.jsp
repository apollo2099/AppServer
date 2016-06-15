<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>菜单编辑信息</title>
  </head>
  <body>	
   <t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"  action="maUserController.do?updateUserPwd">
			<input name="id" type="hidden" value="${mp.id}">
				<div class="form">
				
					<div>
						<label class="Validform_label">
							登录用户名:
						</label>
						<input name="userCode" class="inputxt" size="64" value="${mp.user_code}" readonly="readonly"/>
						<span class="Validform_checktip"></span>
					</div>
					<div>
						<label class="Validform_label">
							旧密码:
						</label>
						<input name="userPassword" class="inputxt" type="password" size="64" />
						<span class="Validform_checktip"></span>
					</div>
			
					<div>
						<label class="Validform_label">
							新密码:
						</label>
						<input name="newPassword" class="inputxt"  type="password"  size="64"/>
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
