package com.taozhu.modules.app.news.controller;

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
import com.taozhu.modules.app.common.AppResultUtil;
import com.taozhu.modules.app.news.service.NewsService;
@Controller
@RequestMapping(value="/newsController.do")
public class NewsController extends BaseController {
	@Resource(name="newsService")
	private NewsService newsService;
	/**
	 * 首页查询资讯
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="queryNewsList")
	public void queryNewsList(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		List<Map<String,Object>> list = newsService.queryNewsList(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", list);
		this.printJson(response, retVal);
	}
	/**
	 * 根据资讯id查询资讯明细
	 * @param response
	 * @param request
	 * @param paramStr
	 */
	@RequestMapping(params="queryNewsById")
	public void queryNewsById(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		Map<String,Object> mp = newsService.queryNewsById(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", mp);
		this.printJson(response, retVal);
	}
	
}
