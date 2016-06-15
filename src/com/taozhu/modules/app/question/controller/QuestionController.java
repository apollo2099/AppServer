package com.taozhu.modules.app.question.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.controller.BaseController;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.modules.app.banner.service.BannerService;
import com.taozhu.modules.app.common.AppResultUtil;
import com.taozhu.modules.app.question.service.QuestionService;

@Controller
@RequestMapping(value="/questionController.do")
public class QuestionController extends BaseController{
	
	@Resource(name="questionService")
	private QuestionService questionService;
	
	@RequestMapping(params="queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,HashMap.class);
		List<Map<String,Object>> alist = questionService.queryList(paramMap);
		//根据问题编号查询答案列表
		if(ObjectUtils.isNotEmpty(alist)){
			for (Map<String, Object> map : alist) {
				  Integer questionCode=(Integer) map.get("code");
				  Map<String, Object> paramTmp=new HashMap<String, Object>();
				  paramTmp.put("questionCode", questionCode);
				  List<Map<String,Object>> attentionList= questionService.queryQuestionAttentionList(paramTmp);
				  map.put("attentionList", attentionList);
			}
		}
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	
	@RequestMapping(params="queryUserQuesList")
	public void queryUserQuesListList(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,
				HashMap.class);
		List<Map<String,Object>> alist = questionService.queryUserQuesListList(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	
	@RequestMapping(params="insertUserQues")
	public void insertUserQues(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,
				HashMap.class);
		int state = questionService.insertUserQues(paramMap);
		JSONObject retVal = new JSONObject();
		retVal.put("success", true);
		if(state<=0){
			retVal.put("success", false);
		}
		retVal.put("msg", "结果消息");
		retVal.put("result", new JSONArray());
		this.printJson(response, retVal);
		
	}
	@RequestMapping(params="queryUserQuestion")
	public void queryUserQuestion(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "param") String param){
		Map<String, Object> paramMap = JSONObject.parseObject(param,
				HashMap.class);
		Map<String,Object> map = questionService.queryUserQuestion(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", map);
		this.printJson(response, retVal);
	}
}
