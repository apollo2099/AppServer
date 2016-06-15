package com.taozhu.modules.app.message.contraller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.controller.BaseController;
import com.taozhu.common.message.HttpSendUtil;
import com.taozhu.common.message.Sendsms;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.app.common.AppResultUtil;
import com.taozhu.modules.app.message.service.MessageAuthCodeService;
import com.taozhu.modules.app.user.service.UserService;

@Controller
@RequestMapping(value="/messageAuthCodeController.do")
public class MessageAuthCodeController  extends BaseController {
	 @Resource
	 private MessageAuthCodeService messageAuthCodeService;
	 @Resource
	 private UserService userService;
	 @Value("${sms.urlPath}")   
	 private String smsUrlPath;
	 @Value("${sms.account}")
	 private String smsAccount;
	 @Value("${sms.passowd}")
	 private String smsPassword;

		
	/**
	 * 发送验证码
	 * @param response
	 * @param request
	 * @param param={"mobile":"手机号码"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params="sendValidateCode")
	public void sendValidateCode(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param",required=true) String param) throws UnsupportedEncodingException{
		 Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		 String mobile=(String) paramMap.get("mobile");
		 long authCode= Math.round(Math.random()*899999+100000);
		 //发送验证码之前，先判断用户是否注册过，注册过后，提示获取失败，消息说用户已经注册
//		 List<Map<String,Object>> userList = userService.queryUserList(paramMap);
		     JSONObject retVal = new JSONObject();
//		 if(userList==null){
			 /**
			  * 业务说明：
			  * 查找当前用户是否有其他验证码，如果存在状态该位停用，重新发送验证码
			  */
			 List<Map<String,Object>> messageList=messageAuthCodeService.queryList(paramMap);
			 if(!StringUtils.isEmpty(messageList)){
				 //修改手机验证码的状态为停用
				 paramMap.put("status", 0);
				 messageAuthCodeService.updateMessageAuthStatus(paramMap);
			 }
			 
			 //发送短信消息
			 String content="您的验证码是："+authCode+"。请不要把验证码泄露给其他人。";
			 boolean flag = Sendsms.sendSms(content, mobile);
			 if(flag){
				// 保存用户短信验证码信息
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("mobile", mobile);
				params.put("authCode", authCode);
				params.put("createTime", new Date());
				params.put("status", 1);
				params.put("code", CodeUtils.getCode("AUTH"));
				messageAuthCodeService.saveMessageAuthCode(params);
				// 组装返回数据
				retVal=AppResultUtil.resultPack(flag, "返回成功", params);
			 }
//		 }else{
//			 	retVal=AppResultUtil.resultPack(false, "该帐号已经注册", "");
//		 }
		 this.printJson(response, retVal);
	}

	/**
	 * 
	 * @Method: sendMessage 
	 * @Description:  [调用公共的短信发送方法]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-11-29 下午5:31:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-11-29 下午5:31:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param mobile 手机号码
	 * @param content 短信内容
	 * @throws UnsupportedEncodingException 
	 * @return void
	 */
	@RequestMapping(params="sendMessage")
	public void sendMessage(@RequestParam(value = "mobile") String mobile,@RequestParam(value = "content") String content) throws UnsupportedEncodingException{
		 String postData="account="+smsAccount+"&password="+smsPassword+"&mobile="+java.net.URLEncoder.encode(mobile,"utf-8")+"&content="+java.net.URLEncoder.encode(content,"utf-8");
//		 HttpSendUtil.send(smsUrlPath, postData);
		 Sendsms.sendSms(content, mobile);
	}
	
}
