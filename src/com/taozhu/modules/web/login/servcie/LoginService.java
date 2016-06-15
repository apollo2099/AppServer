package com.taozhu.modules.web.login.servcie;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taozhu.common.util.MD5Util;
import com.taozhu.modules.web.base.user.service.MaUserService;

@Service("loginService")
public class LoginService {
	
	@Autowired
	private MaUserService  maUserService;
	
	
	
	public Map<String,Object> verifyUserPermissions(Map params){
		Map<String,Object> loginResult = new HashMap<String, Object>();
		loginResult.put("state", "fail");
		loginResult.put("msg", "");
		loginResult.put("userInfo", new HashMap());
		String userCode = (String) params.get("userCode");
		String userPassword = (String) params.get("userPassword");
		if(StringUtils.isBlank(userCode)){
			loginResult.put("msg", "账号不能为空，请输入！");
			return loginResult;
		}
		if(StringUtils.isBlank(userCode)){
			loginResult.put("msg", "密码不能为空，请输入！");
			return loginResult;
		}
		//超级账号：superman/taozhu
		if("superman".equals(userCode)){
			if(!"0d421be630a15328875e18f474b83d2f".equals(MD5Util.getMD5String(userPassword))){
				loginResult.put("msg", "密码错误，请重新输入密码！");
				return loginResult;
			}
			loginResult.put("state", "success");
		}else {
			params.remove("userPassword");
			Map user = maUserService.getMaUser(params);
			if(user!=null){
				String pw = (String) user.get("user_password");//查询数据库密码
				String md5Pwd=MD5Util.getMD5String(userPassword);
				if(!md5Pwd.equals(pw)){
					loginResult.put("msg", "密码错误，请重新输入密码！");
					return loginResult;
				}else{
					loginResult.put("userInfo", user);
					loginResult.put("state", "success");
				}
			}else{
				loginResult.put("msg", "账号错误，请重新确认账号！");
				return loginResult;
			}
		}
		return loginResult;
	}
	

}
