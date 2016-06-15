package com.taozhu.modules.web.question.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.modules.web.business.service.BusinessWebService;
import com.taozhu.modules.web.businessDetail.service.BusinessDetailWebService;
import com.taozhu.modules.web.lvInterface.service.LvInterfaceService;
import com.taozhu.modules.web.question.service.QuestionWebService;

@Controller
public class QuestionWebController extends BaseWebController{
	
	@Autowired
	private QuestionWebService questionWebService;
	@Autowired
	private BusinessWebService businessWebService;
	
	@Resource
	private BusinessDetailWebService businessDetailWebService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveQuestion.do")
	public void saveInterface(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			//获取问题编码
			int questionId=questionWebService.questionCode();
			params.put("create_time", new Date());
			params.put("code", questionId);
			questionWebService.saveQuestion(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			questionWebService.updateQuestion(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/questionGrid.do")
	public String interfaceList(HttpServletResponse response,HttpServletRequest request){
		return "/question/questionGrid";
	}
	@RequestMapping(value="/showQuestion.do")
	public String showQuestion(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = questionWebService.getQuestionById(params);
		}
		//查询业务列表
		List<Map<String,Object>> businessList= businessDetailWebService.queryList();
		model.put("businessList", businessList);
		model.put("mp", mp);
		return "/question/question";
	}
	@RequestMapping(value="/deleteQuestion.do")
	public void deleteQuestion(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		questionWebService.deleteQuestion(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
