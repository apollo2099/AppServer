package com.taozhu.modules.web.base.menu.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.web.base.menu.service.MaFunctionService;

@Controller
public class MaFunctionController extends BaseWebController{
	@Resource
	private MaFunctionService maFunctionService;
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveMaFunction.do")
	public void saveMaFunction(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){			
			params.put("functionCode", CodeUtils.getCode("MENU"));
			maFunctionService.saveMaFunction(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			maFunctionService.updateMaFunction(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/maFunctionGrid.do")
	public String maFunctionGrid(HttpServletResponse response,HttpServletRequest request){
		return "/base/menu/maFunctionGrid";
	}
	@RequestMapping(value="/showMaFunction.do")
	public String showMaFunction(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = this.maFunctionService.getMaFunction(params);
		}
		model.put("mp", mp);
		return "/base/menu/maFunction";
	}
}
