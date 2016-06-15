package com.taozhu.modules.web.application.controller;

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
import com.taozhu.modules.web.application.service.ApplicationWebService;

@Controller
@RequestMapping(value="/applicationWebController.do")
public class ApplicationWebController extends BaseWebController {

	@Resource
	private ApplicationWebService applicationWebService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "saveApplication")
	public void saveApplication(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			applicationWebService.saveWebApp(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			applicationWebService.updateWebApp(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(params = "applicationGrid")
	public String applicationGrid(HttpServletResponse response,HttpServletRequest request){
		return "/application/applicationGrid";
	}
	
	
	@RequestMapping(params = "showApplication")
	public String showApplication(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = applicationWebService.getAppById(params);
		}
		model.put("mp", mp);
		return "/application/application";
	}

	@RequestMapping(params = "deleteApplication")
	public void deleteApplication(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		applicationWebService.deleteApp(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
