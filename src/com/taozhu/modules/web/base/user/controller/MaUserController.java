package com.taozhu.modules.web.base.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.common.util.MD5Util;
import com.taozhu.modules.web.base.user.service.MaUserService;


@Controller
@RequestMapping(value="/maUserController.do")
public class MaUserController extends BaseWebController{
	
	@Autowired
	private MaUserService maUserService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "saveMaUser")
	public void saveMaUser(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			String userPassword= BaseDAOUtil.getStringValue(params, "userPassword");
			params.put("userPassword", MD5Util.getMD5String(userPassword));
			params.put("create_time", new Date());
			maUserService.saveMaUser(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			maUserService.updateMaUser(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(params="maUserGrid")
	public String maUserGrid(HttpServletResponse response,HttpServletRequest request){
		return "/base/user/maUserGrid";
	}
	@RequestMapping(params="showMaUser")
	public String showMaUser(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = this.maUserService.getMaUser(params);
		}
		model.put("mp", mp);
		return "/base/user/maUser";
	}
	
	@RequestMapping(params="showMaUserPwd")
	public String showMaUserPwd(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = this.maUserService.getMaUser(params);
		}
		model.put("mp", mp);
		
		return "/base/user/maUserPwd";
	}
	
	
	@RequestMapping(params = "updateUserPwd")
	public void updateUserPwd(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		String userPassword=BaseDAOUtil.getStringValue(params, "userPassword");
		String newPassword=BaseDAOUtil.getStringValue(params, "newPassword");
		if(params.get("id")!=null){
			Map result = new HashMap();
			result = this.maUserService.getMaUserById(params);
			String rsPassword=(String) result.get("user_password");
			if(StringUtils.isNotEmpty(userPassword)){
				if(rsPassword.equals(MD5Util.getMD5String(userPassword))){
					params.put("newPassword", MD5Util.getMD5String(newPassword));
					maUserService.updateMaUserPwd(params);	
					params.clear();
					params.put("msg", "更新成功！");
					params.put("success", true);
					AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
					
				}
			}
		}
		

	}
	
	
}
