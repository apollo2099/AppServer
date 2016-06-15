<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!-- 
测试地址列表
http://127.0.0.1:8080/AppServer/page/user/userTestList.jsp
 -->

<%-- --%>
登录测试：
<form  action="userController.do?login" method="post">
<input name="param" value='{"account":"13927409420","password":"fd270d727b7828e42f49da0e9af23777"}'/>
<input type="submit" value="登录测试">
</form>

<hr/>
注册测试：
<form  action="userController.do?register" method="post">
<input name="param" value='{"account":"13927409420","password":"21232f297a57a5a743894a0e4a801fc3","authCode":"863733"}'/>
<input type="submit" value="注册测试">
</form>


<hr/>
找回用户密码：
<form  action="userController.do?findUserPwd" method="post">
<input name="param" value='{"account":"13927409420","password":"14e1b600b1fd579f47433b88e8d85291","authCode":"913210"}'/>
<input type="submit" value="找回密码">
</form>


<hr/>
查看用户详情接口实现：
<form  action="userController.do?userDetails" method="post">
<input name="param" value='{"userId":20130503,"userAttentionId":85429240}'/>
<input type="submit" value="用户详情">
</form>


<hr/>
查看业务经理详情接口实现：
<form  action="userController.do?userMngDetails" method="post">
<input name="param" value='{"userId":20130503,"userAttentionId":85429240}'/>
<input type="submit" value="用户详情">
</form>


<hr/>
修改用户资料：
<form  action="userController.do?updateUser" method="post">
<input name="param" value='{"userId":1622475814,"headImage":"/test.jpg","sex":1,"nickname":"超级小霸王","introduction":"我就是我"}'/>
<input type="submit" value="修改用户资料">
</form>

<hr/>
修改用户密码接口实现：
<form  action="userController.do?updateUserPwd" method="post">
<input name="param" value='{"userId":73879004,"oldPassword":"14e1b600b1fd579f47433b88e8d85291","newPassword":"fd270d727b7828e42f49da0e9af23777"}'/>
<input type="submit" value="修改密码">
</form>


<hr/>
用户关注接口实现：
<form  action="userController.do?saveUserAttention" method="post">
<input name="param" value='{"userId":1622475814,"userAttentionId":1622475815}'/>
<input type="submit" value="用户关注">
</form>


<hr/>
取消用户关注接口实现：
<form  action="userController.do?deleteUserAttention" method="post">
<input name="param" value='{"userId":20130503,"userAttentionId":85429240}'/>
<input type="submit" value="取消关注">
</form>
<hr/>
获取用户关注列表接口实现：
<form  action="userController.do?findUserAttention" method="post">
<input name="param" value='{"userId":1622475814}'/>
<input type="submit" value="获取用户">
</form>




<hr/>
设置是否允许聊天接口
<form  action="userController.do?updateUserAllow" method="post">
<input name="param" value='{"userId":1622475814,"isAllow":0}'/>
<input type="submit" value="设置是否允许聊天">
</form>

<hr/>
上传用户地理位置接口
<form  action="userController.do?updateUserPosition" method="post">
<input name="param" value='{"userId":1622475814,"longitude":22.450,"latitude":113.140}'/>
<input type="submit" value="用户地理">
</form>

<hr/>
根据业务编号查询业务经理接口
<form  action="userController.do?queryUser" method="post">
<input name="param" value='{"subBusinessCode":10000,"userId":67247744}'/>
<input type="submit" value="子业务编号查询">
</form>
<hr/>

根据网点查询业务经理接口
<form  action="userController.do?queryUser" method="post">
<input name="param" value='{"branchCode":1000100}'/>
<input type="submit" value="网点业务经理">
</form>
<hr/>

附近业务经理
<form  action="userController.do?queryUser" method="post">
<input name="param" value='{"longitude":22.450,"latitude":113.140}'/>
<input type="submit" value="附近业务经理">
</form>


<hr/>
发送验证码接口
<form  action="messageAuthCodeController.do?sendValidateCode" method="post">
<input name="param" value='{"mobile":"13927409420"}'/>
<input type="submit" value="发送验证码">
</form>


<hr/>
发送短信接口
<form  action="messageAuthCodeController.do?sendMessage" method="post">
<input name="mobile" value="13927409420"/>
<input name="content" value="text"/>
<input type="submit" value="发送短信">
</form>

<hr/>
图片上传接口
<form  action="photoController.do?upload" method="post">
<input name="param" value='{"data":"13928409420"}'/>
<input type="submit" value="图片上传">
</form>


=================================================================<br/>

银行接口查询
<form  action="bankController.do?queryList" method="post">
<input name="param" value=""/>
<input type="submit" value="银行列表">
</form>

<hr/>
根据子业务编码查询银行信息
<form  action="bankController.do?queryList" method="post">
<input name="param" value='{"subBusinessCode":"3333"}'/>
<input type="submit" value="业务编码查询">
</form>

<hr/>
银行网点接口查询
<form  action="bankController.do?queryBranchList" method="post">
<input name="param" value='{"bankCode":"1417788883386BANKU1TTTTT"}'/>
<input type="submit" value="网点列表">
</form>


<hr/>
银行网点详情接口查询
<form  action="bankController.do?queryBranchList" method="post">
<input name="param" value='{"branchCode":"1417794003081BRANCN1TTTTT"}'/>
<input type="submit" value="网点列表">
</form>


<hr/>
广告列表接口查询
<form  action="bannerController.do?queryList" method="post">
<input name="param" value=''/>
<input type="submit" value="广告列表">
</form>


<hr/>
网点广告列表接口查询
<form  action="bannerController.do?queryDetails" method="post">
<input name="param" value='{"bannerCode":"1417792641730BRANCN1TTTTT"}'/>
<input type="submit" value="网点广告">
</form>


<hr/>
业务列表接口查询
<form  action="businessController.do?queryList" method="post">
<input name="param" value=''/>
<input type="submit" value="业务列表">
</form>


<hr/>
业务详情列表接口查询
<form  action="businessController.do?queryBusinessDetails" method="post">
<input name="param" value='{"businessCode":"1417797480555BUSUU2TTTTT"}'/>
<input type="submit" value="广告详情">
</form>


<hr/>
App应用信息查询 
<form  action="applicationController.do?queryDetails" method="post">
<input name="param" value='{"appVersion":"1.0"}'/>
<input type="submit" value="广告详情">
</form>

<hr/>
问题列表接口查询
<form  action="questionController.do?queryList" method="post">
<input name="param" value='{"subBusinessCode":10008}'/>
<input type="submit" value="问题列表">
</form>
