package com.taozhu.modules.web.bank.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.web.bank.service.BankWebService;

@Controller
public class BankWebController extends BaseWebController{
	
	@Autowired
	private BankWebService bankWebService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveBank.do")
	public void saveBank(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			params.put("code", bankWebService.bankCode());
			bankWebService.saveBank(params);
		params.clear();
		params.put("msg", "插入成功！");
		}else{
			bankWebService.updateBank(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/BankGrid.do")
	public String bankList(HttpServletResponse response,HttpServletRequest request){
		return "/bank/BankGrid";
	}
	@RequestMapping(value="/showBank.do")
	public String showBank(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = bankWebService.getBankById(params);
		}
		model.put("mp", mp);
		return "/bank/Bank";
	}
	@RequestMapping(value="/deleteBank.do")
	public void deleteBank(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		bankWebService.deleteBank(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	@RequestMapping(value="/bankImport.do")
	public String bankImport(HttpServletRequest request,HttpServletResponse response){
		return "/fileImport/BankImport";	
	}
}
