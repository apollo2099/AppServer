package com.taozhu.modules.app.banner.controller;

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
import com.taozhu.modules.app.banner.service.BannerService;
import com.taozhu.modules.app.common.AppResultUtil;
/**
 * 广告信息接口
 * @author zhe
 *
 */
@Controller
@RequestMapping(value="/bannerController.do")
public class BannerController extends BaseController {
	@Resource(name="bannerService")
	private BannerService bannerService;
	/**
	 * 广告列表查询
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="queryList")
	public void queryList(HttpServletResponse response,HttpServletRequest request){
		List<Map<String,Object>> alist = bannerService.queryList();
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	/**
	 * 网点广告列表信息
	 * @param response
	 * @param request
	 * @param bannerCode
	 */
	@RequestMapping(params="queryDetails")
	public void queryDetails(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String bannerCode){
		Map<String, Object> paramMap = JSONObject.parseObject(bannerCode,
				HashMap.class);
		Map<String,Object> alist = bannerService.queryDetails(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	/**
	 * 网点广告详情 根据广告的code查询
	 * @param response
	 * @param request
	 * @param params
	 */
	@RequestMapping(params="queryBankBannerByCode")
	public void queryBankBannerByCode(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String params){
		Map<String, Object> paramMap = JSONObject.parseObject(params,
				HashMap.class);
		Map<String,Object> alist = bannerService.queryBankBannerByCode(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	/**
	 * 首页广告详情
	 * @param response
	 * @param request
	 * @param params
	 */
	@RequestMapping(params="queryBannerByCode")
	public void queryBannerByCode(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String params){
		Map<String,Object> paramMap = JSONObject.parseObject(params,HashMap.class);
		Map<String,Object> alist = bannerService.queryBannerByCode(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	
}
