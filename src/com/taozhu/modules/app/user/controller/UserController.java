package com.taozhu.modules.app.user.controller;



import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.taozhu.common.base.controller.BaseController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.common.util.SysPropertyUtil;
import com.taozhu.modules.app.bank.service.BankService;
import com.taozhu.modules.app.common.AppResultUtil;
import com.taozhu.modules.app.message.contraller.MessageAuthCodeController;
import com.taozhu.modules.app.message.service.MessageAuthCodeService;
import com.taozhu.modules.app.question.service.QuestionService;
import com.taozhu.modules.app.user.service.UserService;
import com.taozhu.modules.app.user.utils.UserCodeUtils;
import com.taozhu.modules.app.user.utils.UserConstants;

/**
 * 用户信息/业务经理接口功能实现
 * @author liaoxj
 * @date 2014-11-16
 */
@Controller
@RequestMapping(value="/userController.do")
public class UserController extends BaseController {
	private static final Log logger	= LogFactory.getLog(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private BankService bankService;
	
	@Resource
	private QuestionService questionService;
	
	@Resource
	private MessageAuthCodeService messageAuthCodeService;
	@Resource
	private MessageAuthCodeController messageAuthCodeController;
	
/**
 * 用户帐号信息登录
 * @param response
 * @param request
 * @param param={"account":"帐号","password":"密码"}
 * @return {"success":"true/false","msg":"结果消息","result":"[]"}
 */
	@RequestMapping(params="login")
	public void login(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		String account = BaseDAOUtil.getStringValue(paramMap, "account");
		String password = BaseDAOUtil.getStringValue(paramMap, "password");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(account)){
			retVal=AppResultUtil.resultPack(false, "登录帐号不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		if(ObjectUtils.isEmpty(password)){
			retVal=AppResultUtil.resultPack(false, "登录密码不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
		
		
		//根据帐号密码查询用户信息
		Map<String,Object> reMap= userService.userLogin(paramMap);
		//组装返回数据
		String tokenStr="";
		if(!reMap.isEmpty()){
			//判断用户类型，如果是业务经理返回网点支行信息
			Integer userType=(Integer) reMap.get("user_type");
			if(!StringUtils.isEmpty(userType)&&userType==1){
				 Integer branchCode=(Integer) reMap.get("branch_code");
				 paramMap.clear();
				 paramMap.put("branch_code", branchCode);
				 Map<String,Object> branchMap=bankService.queryBankBranch(paramMap);
				 if(!StringUtils.isEmpty(branchMap)){
					 reMap.put("branch_name", branchMap.get("branch_name"));
				 }
			}
			try {
				String userId = BaseDAOUtil.getStringValue(reMap, "user_id");
				String head_image = BaseDAOUtil.getStringValue(reMap, "head_image");
				String nick_name = BaseDAOUtil.getStringValue(reMap, "nick_name");
				//获取 Token 方法
				SdkHttpResult result = ApiHttpClient.getToken(SysPropertyUtil.getInstance().getValue("appKey"), SysPropertyUtil.getInstance().getValue("appSecret"), userId, nick_name, head_image, FormatType.json);
				String token = result.getResult();
				Map<String,Object> tokenMap = JSONObject.parseObject(token,HashMap.class);
				tokenStr = BaseDAOUtil.getStringValue(tokenMap, "token");
			} catch (Exception e) {
				e.printStackTrace();
			}
			reMap.put("token", tokenStr);
			retVal=AppResultUtil.resultPack(true, "登录成功", reMap);
		}else{
			//帐号不存在，密码错误，
			retVal=AppResultUtil.resultPack(false, "登陆失败,账号或密码错误", "");
		}
		this.printJson(response, retVal);
	}
	

	/**
	 * 用户注册功能接口实现
	 * @param response
	 * @param request
	 * @param param={"account":"帐号","password":"密码","authCode":"验证码"}
     * @return {"msg":"注册成功","result":{"userId":1622475814},"success":true}
	 */
	@RequestMapping(params="register")
	public void register(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		String global_path=  SysPropertyUtil.getInstance().getValue("global.path");
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		String account = BaseDAOUtil.getStringValue(paramMap, "account");
		String password = BaseDAOUtil.getStringValue(paramMap, "password");
		String authCode = BaseDAOUtil.getStringValue(paramMap, "authCode");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(account)){
			retVal=AppResultUtil.resultPack(false, "登录帐号不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		if(ObjectUtils.isEmpty(password)){
			retVal=AppResultUtil.resultPack(false, "登录密码不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		if(ObjectUtils.isEmpty(authCode)){
			retVal=AppResultUtil.resultPack(false, "验证码不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
		
		
		
		Map<String, Object> returnMap = new HashedMap();
		// 验证验证码是否有效（需接入短信平台）
		if (!StringUtils.isEmpty(paramMap.get("account"))) {
			paramMap.put("mobile", paramMap.get("account"));
			
			Map<String, Object> resultMsgMap = messageAuthCodeService.queryDetails(paramMap);
			if (!StringUtils.isEmpty(resultMsgMap)) {
				// 存在验证码，用户注册

				
				// 验证注册用户是否已经存在
				Map<String, Object> temp = userService.userDetails(paramMap);
				if (temp.isEmpty()) {
					String headImageDefault=global_path+"/fileServer/head_image_default.png";
					String cardImageDefault=global_path+"/fileServer/card_image_default.png"; 
					// 用户信息注册
					long userId =UserCodeUtils.createRandom();
					paramMap.put("status", UserConstants.USER_STATUS_ON_LINE);
					paramMap.put("userId", userId);
					paramMap.put("createTime", new Date());
					paramMap.put("nick_name", "jrr_"+userId);
					paramMap.put("head_image", headImageDefault);
					paramMap.put("user_type", UserConstants.USER_TYPE_GENERAL);
					paramMap.put("sex", 1);
					paramMap.put("introduction", "我就是懒");
					paramMap.put("is_allow", UserConstants.USER_IS_ALLOW_TRUE);
					paramMap.put("user_card_path", cardImageDefault);
					paramMap.put("mobile",  paramMap.get("account"));
					
					int reqNum = userService.userRegister(paramMap);
					// 组装返回数据
					if (!StringUtils.isEmpty(reqNum)) {
						returnMap.put("userId",userId);
						retVal=AppResultUtil.resultPack(true, "注册成功", returnMap);
						 //修改手机验证码的状态为停用
						Map<String, Object> authMap=new HashMap<String, Object>();
						authMap.put("status", 0);
						authMap.put("mobile",  paramMap.get("account"));
					    messageAuthCodeService.updateMessageAuthStatus(authMap);
					} else {
						retVal=AppResultUtil.resultPack(false, "注册失败", "");
					}
				} else {
					retVal=AppResultUtil.resultPack(false, "该帐号已经注册", "");
				}
			} else {
				retVal=AppResultUtil.resultPack(false, "验证码失效", "");
			}
		} else {
			retVal=AppResultUtil.resultPack(false, "注册账号为空", "");
		}
		this.printJson(response, retVal);
	}
	
    /**
     * 查看用户详情接口实现
	 * @param response
	 * @param request
	 * 	@param  param={"userId":"帐号（必填）"}
     * @return {"success":"true/false","msg":"结果消息","result":"{"account":"账号","nick_name":"昵称","head_image":"头像",
     * "user_type":"用户类型","sex":"性别","introduction":"个人简介","create_time":"注册时间","mobile":"手机","tel":"电话",
     * "isAllow":"是否允许聊天","user_card_path":"用户名片"，"user_code","用户编码"}"
     */
	@RequestMapping(params="userDetails")
	public void userDetails(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		Integer userId = BaseDAOUtil.getIntValue(paramMap, "account");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(userId)){
			retVal=AppResultUtil.resultPack(false, "帐号未登录！", "");
			this.printJson(response, retVal);
			return;
		}
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/

		// 根据帐号密码查询用户信息
		Map<String ,Object> resultMap=  userService.userDetails(paramMap);

		// 组装返回数据
		retVal=AppResultUtil.resultPack(true, "返回成功", resultMap);
		this.printJson(response, retVal);
	}
	
	
    /**
     * 查看业务经理详情接口实现
	 * @param response
	 * @param request
	 * 	@param param={"userId":"帐号（非必填）","userAttentionId":"被关注者账号（必填）"}
     * @return {"success":"true/false","msg":"结果消息","result":"{"account":"账号","nick_name":"昵称","head_image":"头像",
     * "user_type":"用户类型","sex":"性别","introduction":"个人简介","create_time":"注册时间","mobile":"手机","tel":"电话",
     * "isAllow":"是否允许聊天","user_card_path":"用户名片"，"user_code","用户编码"}"
     */
	@RequestMapping(params="userMngDetails")
	public void userMngDetails(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		Integer userId=BaseDAOUtil.getIntValue(paramMap, "userId");
        Integer userAttentionId=BaseDAOUtil.getIntValue(paramMap, "userAttentionId");
		// 根据帐号密码查询用户信息
        paramMap.clear();
        paramMap.put("userId", userAttentionId);//查看业务经理详情
		Map<String ,Object> resultMap=  userService.userDetails(paramMap);
		if(ObjectUtils.isNotEmpty(userId)){
			//根据用户编号和业务经理编号判断是否已经被关注
			paramMap.clear();
			paramMap.put("userId", userId);//查看业务经理详情
			paramMap.put("userAttentionId", userAttentionId);//查看业务经理详情
			Map map= userService.queryUserAttionById(paramMap);
			if(ObjectUtils.isNotEmpty(map)){
				resultMap.put("is_attention", 1);
			}else{
				resultMap.put("is_attention", 0);
			}
		}
		
		// 组装返回数据
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", resultMap);
		this.printJson(response, retVal);
	}
	

	
	/**
	 * 获取用户关注列表接口实现
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":"[{"nick_name":"用户名称","head_image":"用户头像"}....{}]"}
	 */
	@RequestMapping(params="findUserAttention")
	public void findUserAttention(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		List<Map<String,Object>> resultList = userService.findUserAttention(paramMap);
				
		// 组装返回数据
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", resultList);
		this.printJson(response, retVal);
	}
	

	
	/**
	 * 找回用户密码接口（重置密码）
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号（手机号）","password":"密码","authCode":"验证码"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":"[{"nick_name":"用户名称","head_image":"用户头像"}....{}]"}
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params="findUserPwd")
	public void findUserPwd(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param) throws UnsupportedEncodingException{
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		//查询用户信息
		String mobile= (String) paramMap.get("userId");
		//验证短信验证码是否有效
		paramMap.put("mobile", mobile);
		paramMap.put("account", mobile);
		Map map= messageAuthCodeService.queryDetails(paramMap);
		if(!StringUtils.isEmpty(map)){
			//修改用户密码为默认密码
			userService.updateUserPwdByAccount(paramMap);
			
			 //修改手机验证码的状态为停用
			Map<String, Object> authMap=new HashMap<String, Object>();
			authMap.put("status", 0);
			authMap.put("mobile", mobile);
		    messageAuthCodeService.updateMessageAuthStatus(authMap);
		   			
			//组装返回数据
			Map<String,Object> returnMap=new HashedMap();
			JSONObject retVal = new JSONObject();
			retVal=AppResultUtil.resultPack(true, "返回成功", returnMap);
			this.printJson(response, retVal);
		}else{
			 JSONObject retVal = new JSONObject();
			 retVal=AppResultUtil.resultPack(false, "验证码失效", "");
			 this.printJson(response, retVal);
		}
	}
	
	/**
	 * 获取业务经理信息
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号","longitude":"经度","latitude":"纬度","subBusinessCode":"子业务编码"}
     * @return  {"resultFlag":"true/false","msg":"结果消息","result":["{"account":"账号","nick_name":"昵称","head_image":"头像","user_type":"用户类型",
     * "sex":"性别","introduction":"个人简介","create_time":"注册时间","mobile":"手机","tel":"电话","is_allow":"是否允许聊天","user_card_path":"用户名片",
     * "user_code","用户编码"}...{}]}
	 */
	@RequestMapping(params="queryUser")
	public void queryUser(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		if(!StringUtils.isEmpty(paramMap.get("longitude"))&&!StringUtils.isEmpty(paramMap.get("latitude"))){
			double jl_jd = 102834.74258026089786013677476285;//每经度单位米; 	
	        double jl_wd = 111712.69150641055729984301412873;//每纬度单位米; 
	        paramMap.put("jl_jd", jl_jd);
	        paramMap.put("jl_wd", jl_wd);
	        paramMap.put("range", 5000);
		}
		List<Map<String,Object>> resultList = userService.queryManagerList(paramMap);
		//增加返回业务是否回答过问题
		Map<String,Object> map = questionService.queryUserQuestion(paramMap);
		map.put("userList", resultList);
		// 组装返回数据
//		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", resultList);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", map);
		this.printJson(response, retVal);
	}
	
	




	/**
	 *用户关注接口实现
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号","userAttentionId":"被关注者账号"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 */
	@RequestMapping(params="saveUserAttention")
	public void saveUserAttention(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		Integer userId=BaseDAOUtil.getIntValue(paramMap, "userId");
        Integer userAttentionId=BaseDAOUtil.getIntValue(paramMap, "userAttentionId");
        
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(userId)){
			retVal=AppResultUtil.resultPack(false, "帐号未登录！", "");
			this.printJson(response, retVal);
			return;
		}
		if(ObjectUtils.isEmpty(userAttentionId)){
			retVal=AppResultUtil.resultPack(false, "关注业务经理不存在！", "");
			this.printJson(response, retVal);
			return;
		}
		
		
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
        
		paramMap.put("userId", userId);//查看业务经理详情
		paramMap.put("userAttentionId", userAttentionId);//查看业务经理详情
		Map map= userService.queryUserAttionById(paramMap);
		if(ObjectUtils.isEmpty(map)){
			//查询关注用户信息和被关注者用户信息
			Map<String, Object> userMap=new HashMap<String, Object>();
			userMap.put("userId", userId);
//			userMap.put("userType", UserConstants.USER_TYPE_GENERAL);
			Map<String ,Object> accountMap=  userService.userDetails(userMap);
			userMap.clear();
			userMap.put("userId", userAttentionId);
//			userMap.put("userType", UserConstants.USER_TYPE_MANAGER);
			Map<String ,Object> attentionAccountMap=  userService.userDetails(userMap);

			//保存关注用户信息
			if(!StringUtils.isEmpty(accountMap)&&!StringUtils.isEmpty(attentionAccountMap)){
				paramMap.put("userCode", String.valueOf(accountMap.get("user_id")));
				paramMap.put("attentionCode", String.valueOf(attentionAccountMap.get("user_id")));
				paramMap.put("code",CodeUtils.createRandom());
				paramMap.put("createTime", new Date());
				userService.saveUserAttention(paramMap);
				//修改被关注者的状态，修改为已经被关注
				userMap.clear();
				userMap.put("userId", paramMap.get("userAttentionId"));
				userService.updateUserAttentionFlag(userMap);
				
				retVal=AppResultUtil.resultPack(true, "关注成功", "");
			}else{
				retVal=AppResultUtil.resultPack(false, "关注失败", "");
			}
		}else{
			retVal=AppResultUtil.resultPack(false, "该业务经理已经关注", "");
		}
		this.printJson(response, retVal);
	}
	
	/**
	 *取消用户关注接口实现
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号","userAttentionId":"被关注者账号"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 */
	@RequestMapping(params="deleteUserAttention")
	public void deleteUserAttention(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		Integer userId = BaseDAOUtil.getIntValue(paramMap, "userId");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(userId)){
			retVal=AppResultUtil.resultPack(false, "帐号未登录!", "");
			this.printJson(response, retVal);
			return;
		}
		
		
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
		
		int num=userService.deleteUserAttention(paramMap);
		if(ObjectUtils.isNotEmpty(num)){
			retVal=AppResultUtil.resultPack(true, "取消关注成功", "");
		}else{
			retVal=AppResultUtil.resultPack(false, "取消关注失败", "");
		}
		this.printJson(response, retVal);
	}
	/**
	 *修改用户信息接口实现
	 * @param response
	 * @param request，，，
	 * @param param={"userId":"帐号","headImage":"头像","sex":"性别","nickname":"昵称","introduction":"个性签名","userCardPath":"个人名片"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 */
	@RequestMapping(params="updateUser")
	public void updateUser(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		String userId = BaseDAOUtil.getStringValue(paramMap, "userId");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(userId)){
			retVal=AppResultUtil.resultPack(false, "帐号未登录！", "");
			this.printJson(response, retVal);
			return;
		}
		
		
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
	    userService.updateUser(paramMap);
		try {
			String head_image = BaseDAOUtil.getStringValue(paramMap, "head_image");
			String nick_name = BaseDAOUtil.getStringValue(paramMap, "nick_name");
			//更新用户信息刷新融云信息接口
			ApiHttpClient.refreshToken(SysPropertyUtil.getInstance().getValue("appKey"), SysPropertyUtil.getInstance().getValue("appSecret"), userId, nick_name, head_image, FormatType.json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		retVal=AppResultUtil.resultPack(true, "返回成功", "");
		this.printJson(response, retVal);
	}
	
	/**
	 * 上传用户地理位置接口
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号","longitude":"经度","latitude":"纬度"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 */
	@RequestMapping(params="updateUserPosition")
	public void updateUserPosition(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		
		Integer userId = BaseDAOUtil.getIntValue(paramMap, "userId");
		Double longitude = BaseDAOUtil.getDoubleValue(paramMap, "longitude");
		Double latitude = BaseDAOUtil.getDoubleValue(paramMap, "latitude");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
//		if(ObjectUtils.isEmpty(userId)){
//			retVal=AppResultUtil.resultPack(false, "帐号未登录！", "");
//			this.printJson(response, retVal);
//			return;
//		}
		if(ObjectUtils.isEmpty(longitude)){
			retVal=AppResultUtil.resultPack(false, "上传经度不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		if(ObjectUtils.isEmpty(latitude)){
			retVal=AppResultUtil.resultPack(false, "上传纬度不能为空", "");
			this.printJson(response, retVal);
			return;
		}
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
		//如果用户userId为空则不更新
		if(userId!=0){
		//更新地址位置
			userService.updateUserPosition(paramMap);
		}
		//更新经纬度然后查询附件人
		if(!StringUtils.isEmpty(paramMap.get("longitude"))&&!StringUtils.isEmpty(paramMap.get("latitude"))){
			double jl_jd = 102834.74258026089786013677476285;//每经度单位米; 	
	        double jl_wd = 111712.69150641055729984301412873;//每纬度单位米; 
	        paramMap.put("jl_jd", jl_jd);
	        paramMap.put("jl_wd", jl_wd);
	        paramMap.put("range", 5000);
		}
		List<Map<String,Object>> resultList = userService.queryManagerList(paramMap);
		retVal=AppResultUtil.resultPack(true, "返回成功", resultList);
		this.printJson(response, retVal);
	}
	

	
	/**
	 * 修改用户密码接口实现
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号","oldPassword":"旧密码","newPassword":"新密码"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 */
	@RequestMapping(params="updateUserPwd")
	public void updateUserPwd(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		Integer userId = BaseDAOUtil.getIntValue(paramMap, "userId");
		String oldPassword=(String) paramMap.get("oldPassword");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(userId)){
			retVal=AppResultUtil.resultPack(false, "帐号未登录！", "");
			this.printJson(response, retVal);
			return;
		}
		
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/

		// 根据帐号密码查询用户信息
		Map<String ,Object> resultMap=  userService.userPwdDetails(paramMap);
		String password=(String) resultMap.get("password");
		
		if(oldPassword.equals(password)){
			//修改密码
			userService.updateUserPwd(paramMap);
			retVal=AppResultUtil.resultPack(true, "修改密码成功", "");
		}else{
			retVal=AppResultUtil.resultPack(false, "修改密码失败", "");
		}
		this.printJson(response, retVal);
	}
	
	
	/**
	 * 设置是否允许聊天接口
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号","isAllow":"1是/0否"}
    * @return {"resultFlag":"true/false","msg":"结果消息","result":""}
	 */
	@RequestMapping(params="updateUserAllow")
	public void updateUserAllow(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		Integer userId = BaseDAOUtil.getIntValue(paramMap, "userId");
		JSONObject retVal = new JSONObject();
		/***********************************************
		 * 添加服务器验证
		 ***********************************************/
		if(ObjectUtils.isEmpty(userId)){
			retVal=AppResultUtil.resultPack(false, "帐号未登录！", "");
			this.printJson(response, retVal);
			return;
		}
		
		/***********************************************
		 * 接口业务逻辑处理
		 ***********************************************/
		userService.updateUserAllow(paramMap);
	
		retVal=AppResultUtil.resultPack(true, "返回成功", "");
		this.printJson(response, retVal);
	}
	
	/**
	 * 获取头像和昵称方法
	 * @param response
	 * @param request
	 * @param param={"userId":"帐号"}
	 * @return {"resultFlag":"true/false","msg":"结果消息","result":"{head_image:"头像路径","nick_name":"昵称"}"}
	 */
	@RequestMapping(params="queryUserById")
	public void queryUserById(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		JSONObject retVal = new JSONObject();
		String userId = BaseDAOUtil.getStringValue(paramMap, "userId");
		if(org.apache.commons.lang.StringUtils.isNotEmpty(userId)){
			Map<String, Object> reMap = null;
			try {
				reMap = userService.queryUserById(paramMap);
				retVal=AppResultUtil.resultPack(true, "返回成功", reMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				retVal=AppResultUtil.resultPack(false, "查询结果异常", "");
			}
		}else{
			retVal=AppResultUtil.resultPack(false, "传过来的userId为空", "");
		}
		
		this.printJson(response, retVal);
	}

	/**
	 * 获取头像和昵称方法
	 * @param response
	 * @param request
	 * @param param={"userId":"{"帐号","账号"}"}
	 * @return {"resultFlag":"true/false","msg":"结果消息","result":"{head_image:"头像路径","nick_name":"昵称"}"}
	 */
	@RequestMapping(params="queryUserByIds")
	public void queryUserByIds(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		JSONObject retVal = new JSONObject();
		String userIds = BaseDAOUtil.getStringValue(paramMap, "userId");
		if(org.apache.commons.lang.StringUtils.isNotEmpty(userIds)){
			try {
				List<String> userList = JSONObject.parseObject(userIds, List.class);
				paramMap.put("userList", userList);
				List<Map<String,Object>> usersList = userService.queryUserByIds(paramMap);
				retVal=AppResultUtil.resultPack(true, "返回成功", usersList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				retVal=AppResultUtil.resultPack(false, "查询结果异常", "");
			}
		}else{
			retVal=AppResultUtil.resultPack(false, "传过来的userId为空", "");
		}
		
		this.printJson(response, retVal);
	}
}
