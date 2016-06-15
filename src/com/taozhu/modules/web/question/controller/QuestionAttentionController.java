package com.taozhu.modules.web.question.controller;

import java.util.Date;
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
import com.taozhu.modules.web.question.service.QuestionAttentionService;
import com.taozhu.modules.web.question.service.QuestionWebService;
@Controller
public class QuestionAttentionController extends BaseWebController{
	@Resource
	private QuestionAttentionService questionAttentionService;
	
	@Resource
	private QuestionWebService questionWebService;
	@RequestMapping(value="/questionAttentionGrid.do")
	public String questionAttentionGrid(HttpServletResponse response,HttpServletRequest request){
		return "/question/questionAttentionGrid";
	}
	
	@RequestMapping(value="/showQuestionAttention.do")
	public String showQuestionAttention(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = questionAttentionService.getQuestionAttentionById(params);
		}
		List<Map> questionList= questionWebService.getQuestionList(null);
		model.put("questionList", questionList);
		model.put("mp", mp);
		return "/question/questionAttention";
	}
	
	@RequestMapping(value = "/saveQuestionAttention.do")
	public void saveQuestionAttention(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			questionAttentionService.saveQuestionAttention(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			questionAttentionService.saveQuestionAttention(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	@RequestMapping(value="/deleteQuestionAttention.do")
	public void deleteQuestionAttention(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		questionAttentionService.deleteQuestionAttention(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
