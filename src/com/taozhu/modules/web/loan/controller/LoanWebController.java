package com.taozhu.modules.web.loan.controller;

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
import com.taozhu.modules.web.business.service.BusinessWebService;
import com.taozhu.modules.web.loan.service.LoanWebService;
@Controller
@RequestMapping(value="LoanWebController.do")
public class LoanWebController extends BaseWebController {
	@Resource
	private LoanWebService loanWebService;
	@Resource
	private BusinessWebService businessWebService;
	
	@RequestMapping(params="queryLoan")
	public String queryLoan(HttpServletResponse response,HttpServletRequest request){
		return "loan/LoanGrid";
	}
	
	@RequestMapping(params="showLoan")
	public String showLoan(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = loanWebService.queryLoanById(params);
		}
		//查询业务列表
		List<Map<String,Object>> businessList= businessWebService.queryList();
		model.put("businessList", businessList);
		model.put("mp", mp);
		return "/loan/Loan";
	}
	
	@RequestMapping(params="deleteALLSelect")
	public void deleteALLSelect(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		loanWebService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	@RequestMapping(params="saveLoan")
	public void saveLoan(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			loanWebService.saveLoan(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			loanWebService.updateLoan(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
