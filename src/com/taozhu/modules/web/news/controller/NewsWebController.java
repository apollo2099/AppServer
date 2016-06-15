package com.taozhu.modules.web.news.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.modules.web.news.service.NewsWebService;
@Controller
@RequestMapping(value = "/newsWebController.do")
public class NewsWebController extends BaseWebController{
	@Resource
	private NewsWebService newsWebService;
	@RequestMapping(params="newsGrid")
	public String newsList(HttpServletResponse response,HttpServletRequest request){
		return "/news/NewsGrid";
	}
	@RequestMapping(params="showNews")
	public String showNews(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = newsWebService.queryNewsById(params);
		}
		model.put("mp", mp);
		return "/news/News";
	}
	/**
	 * 批量删除业务详情信息
	 * 备注：ids是所有选中的业务详情id
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="deleteALLSelect")
	public void deleteALLSelect(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		newsWebService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	@RequestMapping(params="saveNews")
	public void saveNews(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			newsWebService.saveNews(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			newsWebService.updateNews(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
