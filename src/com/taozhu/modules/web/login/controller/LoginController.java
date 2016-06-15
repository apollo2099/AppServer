package com.taozhu.modules.web.login.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.modules.web.login.servcie.LoginService;

@Controller
@RequestMapping(value="login.do")
public class LoginController extends BaseWebController{
	
	@Resource
	private LoginService loginService;
	
	/**
	 * 返回登录页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(params="login")
	public String showLoginPage(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){
		return "main/login";
	}
	
	@RequestMapping(params="loginOut")
	public String loginOut(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		return "main/login";
	}
	
	
	
	/**
	 * 用户登录验证
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(params="verify")
	public void verifyUserPermissions(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){
		HttpSession session = request.getSession();
		Map params = this.getRequestParams(request);
		
		Map<String,Object> loginMark = this.loginService.verifyUserPermissions(params);
		try {
			if("fail".equals(loginMark.get("state"))){
				AjaxUtil.ajaxResponse(response, JSONObject.toJSONString(loginMark), AjaxUtil.RESPONCE_TYPE_TEXT);
			}else{
				if(loginMark.get("userInfo")!=null){
					session.setAttribute("loginInfo", JSONObject.toJSON(loginMark));
					AjaxUtil.ajaxResponse(response, JSONObject.toJSONString(loginMark), AjaxUtil.RESPONCE_TYPE_TEXT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
