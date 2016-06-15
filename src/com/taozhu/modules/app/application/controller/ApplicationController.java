package com.taozhu.modules.app.application.controller;

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
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.modules.app.application.service.ApplicationService;
import com.taozhu.modules.app.common.AppResultUtil;

@Controller
@RequestMapping(value="/applicationController.do")
public class ApplicationController extends BaseController {

	@Resource
	private ApplicationService applicationService;
	
	/**
	 * 根据版本号查询app应用信息
	 * @param response
	 * @param request
	 * @param param={"appVersion":"应用版本（必填）"}
	 * @return {"success":"true/false","msg":"结果消息","result":{"app_name":"应用名称","app_url":"应用地址","app_version":"应用版本","remark":"更新备注"}}
	 */
	@RequestMapping(params="queryDetails")
	public void queryDetails(HttpServletResponse response,HttpServletRequest request, @RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		//根据版本查询app信息
		Map<String,Object> appMap=applicationService.queryDetails(paramMap);
		int appVersion =BaseDAOUtil.getIntValue(paramMap, "appVersion");
		int app_version = BaseDAOUtil.getIntValue(appMap, "app_version");
		//组装返回数据
		JSONObject retVal=new JSONObject();
		if(appVersion<app_version){
			 retVal=AppResultUtil.resultPack(true, "返回成功", appMap);
		}else{
			 retVal=AppResultUtil.resultPack(false, "当前已是最新版本", "");
		}
		
		this.printJson(response, retVal);
	}
	
}
