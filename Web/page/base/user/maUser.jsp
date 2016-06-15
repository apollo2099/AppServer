<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <title>菜单编辑信息</title>
  </head>
  <body>	
   <t:formvalid formid="formobj" layout="div" dialog="true" refresh="true"  action="maUserController.do?saveMaUser">
			<input name="id" type="hidden" value="${mp.id}">
				<div class="form">
				 <c:if test="${empty mp.id}">
					<div>
						<label class="Validform_label">
							登录用户名:
						</label>
						<input name="userCode" class="inputxt" size="64" value="${mp.user_code}" />
						<span class="Validform_checktip"></span>
					</div>
					<div>
						<label class="Validform_label">
							密码:
						</label>
						<input name="userPassword" class="inputxt" type="password" size="64" value="${mp.user_password}" />
						<span class="Validform_checktip"></span>
					</div>
					</c:if>
					<div>
						<label class="Validform_label">
							真实姓名:
						</label>
						<input name="userName" class="inputxt" size="64" value="${mp.user_name}" />
						<span class="Validform_checktip"></span>
					</div>
						<div>
					<label class="Validform_label">
							性别:
						</label>
						<input type="radio" name="userSex" id="userSex" value="1" <c:if test="${mp.user_sex==1 }">checked="checked"</c:if>>男</input>
					    <input type="radio" name="userSex" id="userSex" value="2"  <c:if test="${mp.user_sex==2 }">checked="checked"</c:if>>女</input>
					    <input type="radio" name="userSex" id="userSex" value="0"  <c:if test="${mp.user_sex==0 }">checked="checked"</c:if>>不详</input>
						<span class="Validform_checktip"></span>
					</div>
						<div>
						<label class="Validform_label">
							邮件:
						</label>
						<input name="email" class="inputxt" size="64" value="${mp.email}" />
						<span class="Validform_checktip"></span>
					</div>
						<div>
						<label class="Validform_label">
							联系电话:
						</label>
						<input name="telephone" class="inputxt" size="64" value="${mp.telephone}" />
						<span class="Validform_checktip"></span>
					</div>
					<div>
						<label class="Validform_label">
							地址:
						</label>
						<textarea name="address"  id="address" rows="5" cols="60">${mp.address}</textarea>
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
