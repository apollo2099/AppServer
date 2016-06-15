package com.taozhu.test.modules.user.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.test.common.util.BaseJunitCase;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.common.util.MD5Util;
import com.taozhu.modules.app.user.service.*;
public class UserServiceTest extends BaseJunitCase{

	@Resource
	private UserService userService;
	@Test
	public void testUserLogin() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account", "admin");
		params.put("password", "admin");
		Map<String,Object> resultMap= userService.userLogin(params);
		Object result= JSONObject.toJSON(resultMap);
		System.out.println("测试结果"+result);
	}

	@Test
	public void testUserRegister() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account", "15874031198");
		params.put("password", "liaoxj");
		params.put("userCode", CodeUtils.getCode("USER"));
		params.put("createTime", new Date());
		userService.userRegister(params);
	}

	@Test
	public void testUserDetails() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account", "13927409420");
		Map<String,Object> resultMap= userService.userLogin(params);
		Object result= JSONObject.toJSON(resultMap);
		System.out.println("测试结果"+result);
	}

	@Test
	public void testUpdateUserPwd() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account", "admin");
		params.put("newPassword", MD5Util.getMD5String("admin"));
		userService.updateUserPwd(params);
	}

	@Test
	public void testSaveUserAttention() {//还有问题
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("userCode","1417008590750USERU1TTTTT");
		params.put("attentionCode","1417008590752USERU1TTTTT");
		params.put("code",CodeUtils.getCode());
		params.put("createTime", new Date());
	   userService.saveUserAttention(params);
	}
	
	@Test
	public void testFindUserAttention() {//还有问题
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account","admin");
		List<Map<String,Object>> resultList=userService.findUserAttention(params);
		Object result= JSONObject.toJSON(resultList);
		System.out.println("测试结果"+result);
	}



	@Test
	public void testUpdateUserAllow() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account", "admin");
		params.put("isAllow", Short.parseShort("1"));
		userService.updateUserAllow(params);
	}

	@Test
	public void testUpdateUserPosition() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("account", "admin");
		params.put("longitude", 111);
		params.put("latitude", 325);
		userService.updateUserPosition(params);
	}

	@Test
	public void testQueryUser() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("branchCode", "test");
		List<Map<String,Object>> resultList= userService.queryUserList(params);
		Object result= JSONObject.toJSON(resultList);
		System.out.println("测试结果"+result);
	}

	
	@Test
	public void testQueryUser2() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("longitude", Double.valueOf("22.450"));
		params.put("latitude", Double.valueOf("113.140"));
		double jl_jd = 102834.74258026089786013677476285;//每经度单位米; 	
        double jl_wd = 111712.69150641055729984301412873;//每纬度单位米; 
        params.put("jl_jd", jl_jd);
        params.put("jl_wd", jl_wd);
        params.put("range", 5000);
		List<Map<String,Object>> resultList= userService.queryUserList(params);
		Object result= JSONObject.toJSON(resultList);
		System.out.println("测试结果"+result);
	}
}
