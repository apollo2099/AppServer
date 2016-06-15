package com.taozhu.modules.web.mainframe.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.modules.web.mainframe.service.MainFrameService;


@Controller
@RequestMapping(value="/mainframe.do")
public class MainframeController extends BaseWebController{
	@Autowired
	private MainFrameService mainFrameService;
	
	@RequestMapping(params="showMainPage")
	public String showMainPage(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		params.put("parentId", 0);
		List<Map> menuList = this.mainFrameService.getMainFrameMenu(params);
		model.put("menuList", menuList);
		model.put("jsonMenuList",JSONArray.toJSONString(menuList));
		return "main/mainframe";
	} 
	
	
	@RequestMapping(params="getSecondMenu")
	public void getSecondMenu(HttpServletRequest request,HttpServletResponse response,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		List<Map> menuList = this.mainFrameService.getSecondMenu(params);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(menuList), AjaxUtil.RESPONCE_TYPE_JSON);
	} 
	
}
