package com.taozhu.test.modules.message.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.app.message.service.MessageAuthCodeService;
import com.taozhu.test.common.util.BaseJunitCase;

public class MessageAuthCodeServiceTest extends BaseJunitCase{
	
	@Resource
	private MessageAuthCodeService messageAuthCodeService;

	@Test
	public void testQueryDetails() {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("mobile", "13927409420");
		params.put("authCode", "469171");
		Map<String,Object> resultMap= messageAuthCodeService.queryDetails(params);
		Object result= JSONObject.toJSON(resultMap);
		System.out.println("测试结果"+result);
	}

	@Test
	public void testSaveMessageAuthCode() {
		Map<String, Object> params=new HashMap<String, Object>();
		long authCode= Math.round(Math.random()*899999+100000);
		params.put("mobile", "13927409420");
		params.put("authCode", authCode);
		params.put("createTime", new Date());
		params.put("status", 1);
		params.put("code", CodeUtils.getCode("AUTH"));
		int num= messageAuthCodeService.saveMessageAuthCode(params);
		System.out.println(num);
	}

	@Test
	public void testUpdateMessageAuthStatus() {
		Map<String, Object> params=new HashMap<String, Object>();
		long authCode= Math.round(Math.random()*899999+100000);
		params.put("mobile", "13927409420");
		params.put("status", 0);
		int num= messageAuthCodeService.updateMessageAuthStatus(params);
	}

}
