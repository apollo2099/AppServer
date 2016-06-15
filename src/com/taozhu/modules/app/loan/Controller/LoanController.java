package com.taozhu.modules.app.loan.Controller;

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
import com.taozhu.modules.app.loan.service.LoanService;

@Controller
@RequestMapping(value="LoanController.do")
public class LoanController extends BaseController{
	@Resource
	private LoanService loanService;
	@RequestMapping(params="queryLoanList")
	public void queryLoanList(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		List<Map<String,Object>> list = loanService.queryLoanList(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", list);
		this.printJson(response, retVal);
	}
	@RequestMapping(params="queryLoanById")
	public void queryLoanById(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		Map<String,Object> mp = loanService.queryLoanById(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", mp);
		this.printJson(response, retVal);
	}
	
}
