package com.taozhu.modules.web.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.common.util.MD5Util;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.modules.app.user.utils.UserCodeUtils;
import com.taozhu.modules.app.user.utils.UserConstants;
import com.taozhu.modules.web.bank.service.BankWebService;
import com.taozhu.modules.web.user.service.UserWebService;

@Controller
@RequestMapping(value="/userWebController.do")
public class UserWebController  extends BaseWebController {
	
	@Resource
	private UserWebService userWebService;
	@Resource
	private BankWebService bankWebService;

	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "saveUser")
	public void saveUser(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			String password=(String) params.get("password");
			params.put("password", MD5Util.getMD5String(password));
			params.put("create_time", new Date());
			params.put("user_id", UserCodeUtils.createRandom());
			params.put("status", 1);
			params.put("recommend", "");
			params.put("longitude", 0.0);
			params.put("latitude", 0.0);
			params.put("user_type", UserConstants.USER_TYPE_MANAGER);
			userWebService.saveUser(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			userWebService.updateUser(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	
	/**
	 * 用户列表查询
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="userGrid")
	public String userGrid(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		params.get("userType");
		return "/user/userGrid";
	}
	
	/**
	 * 业务经理列表查询
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="userManagerGrid")
	public String userManagerGrid(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		params.get("userType");
		return "/user/userManagerGrid";
	}
	
	/**
	 * 用户关注列表查询
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="userAttentionGrid")
	public String userAttentionGrid(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		params.get("userType");
		return "/user/userAttentionGrid";
	}
	
	/**
	 * 调整用户或者业务经理添加/编辑页面
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params="showUser")
	public String showUser(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			//查询用户详情
			mp = userWebService.getUserById(params);
			
			if(ObjectUtils.isNotEmpty( mp.get("branch_code"))){
				Integer branch_code=(Integer) mp.get("branch_code");
				
				//根据银行网点编码查询银行详情
				Map<String,Object> bankMap= bankWebService.queryBankByCode(mp);
				if(bankMap==null){
					bankMap = new HashMap<String,Object>();
				}
				
				//根据银行编码查询网点列表
				Map<String,Object> bankMapParam=new HashMap<String, Object>();
				bankMapParam.put("bank_code", BaseDAOUtil.getStringValue(bankMap,"code"));
				List<Map> branchList= bankWebService.queryBranchList(bankMapParam);

				//返回结果数据
				model.put("bankMap", bankMap);
				model.put("branchList", branchList);
			}
			
		
			
		}
		model.put("mp", mp);
		
		List<Map<String, Object>>  bankList= bankWebService.getBankList(null);
		model.put("bankList", bankList);
		return "/user/user";
	}
	
	
	
	@RequestMapping(params="showUserPwd")
	public String showUserPwd(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = userWebService.getUserById(params);
		}
		model.put("mp", mp);
		
		return "/user/userPwd";
	}
	
	
	@RequestMapping(params = "updateUserPwd")
	public void updateUserPwd(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		String userPassword=BaseDAOUtil.getStringValue(params, "userPassword");
		String newPassword=BaseDAOUtil.getStringValue(params, "newPassword");
		if(params.get("id")!=null){
			Map result = new HashMap();
			result = userWebService.getUserById(params);
			String rsPassword=(String) result.get("password");
			if(StringUtils.isNotEmpty(userPassword)){
				if(rsPassword.equals(MD5Util.getMD5String(userPassword))){
					params.put("newPassword", MD5Util.getMD5String(newPassword));
					params.put("account", result.get("account"));
					userWebService.updateUserPwd(params);	
					params.clear();
					params.put("msg", "更新成功！");
					params.put("success", true);
					AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
					
				}
			}
		}
	}
	
	
	@RequestMapping(params="deleteUser")
	public void deleteUser(HttpServletRequest req, HttpServletResponse res){
		Map params = this.getRequestParams(req);
		userWebService.deleteUser(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(res, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(params="userImport")
	public String userImport(){
		return "/fileImport/UserImport";	
	}
	
}
