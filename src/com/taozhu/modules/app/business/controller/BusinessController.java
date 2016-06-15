package com.taozhu.modules.app.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.controller.BaseController;
import com.taozhu.modules.app.business.service.BusinessService;
import com.taozhu.modules.app.common.AppResultUtil;
@Controller
@RequestMapping(value="/businessController.do")
public class BusinessController extends BaseController {
	@Resource(name="businessService")
	private BusinessService businessService;
	/**
	 * 首页菜单功能查询
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="queryBusinessList")
	public void queryBusinessList(HttpServletResponse response,HttpServletRequest request){
		List<Map<String,Object>> list = businessService.queryBusinessList();
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", list);
		this.printJson(response, retVal);
	}
	
	/**
	 * 查询业务列表以及业务列表的详情
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="queryList")
	public void queryList(HttpServletResponse response,HttpServletRequest request){
		List<Map<String,Object>> mp = businessService.queryList();
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", mp);
		this.printJson(response, retVal);
	}
	/**
	 * 业务列表详情
	 * @param response
	 * @param request
	 * @param paramStr
	 */
	@RequestMapping(params="queryBusinessDetails")
	public void queryBusinessDetails(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		List<Map<String,Object>> mp = businessService.queryBusinessDetails(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", mp);
		this.printJson(response, retVal);
	}
	/**
	 * 业务搜索功能
	 * @param response
	 * @param request
	 * @param paramStr
	 */
	@RequestMapping(params="queryBusinessByName")
	public void queryBusinessByName(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		List<Map<String,Object>> list = businessService.queryBusinessByName(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", list);
		this.printJson(response, retVal);
	}
}
