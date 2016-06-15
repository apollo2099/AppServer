package com.taozhu.modules.web.bankBranch.controller;

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
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.web.bank.service.BankWebService;
import com.taozhu.modules.web.bankBranch.service.BankBranchService;

@Controller
public class BankBranchController extends BaseWebController {
	@Resource
	private BankBranchService bankBranchService;
	@Resource
	private BankWebService bankWebService;
	
	@RequestMapping(value="/bankBranchGrid.do")
	public String bankBranchGrid(HttpServletResponse response,HttpServletRequest request){
		return "/bankBranch/BankBranchDetailsGrid";
	}
	@RequestMapping(value="/showBankBranch.do")
	public String showBankBranch(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		//查询网点银行列表
		List<Map<String,Object>> bankList=bankWebService.getBankList(null);
		model.put("bankList", bankList);
		if(params.get("id")!=null){
			mp = bankBranchService.getBankBranchById(params);
		}
		model.put("mp", mp);
		return "/bankBranch/BankBranchDetails";
	}
	@RequestMapping(value="/saveBankBranch.do")
	public void saveBankBranch(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			params.put("branch_code", bankBranchService.bankBranchCode());
			params.put("code", bankBranchService.bankBranchDetailsCode());
			//保存银行网点
			bankBranchService.insertBankBranch(params);
			//保存银行网点详情
			bankBranchService.insertBankBranchDetails(params);;
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			//更新网点信息
			bankBranchService.updateBankBranch(params);
			//更新网点详情信息
			bankBranchService.updateBankBranchDetails(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/queryBankBranchByBank.do")
	public void queryBankBranchByBank(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		List<Map<String,Object>> brancnList=bankBranchService.queryBankBranchList(params);
		System.out.println(JSONArray.toJSONString(brancnList));
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(brancnList), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	@RequestMapping(value="/deleteALLSelect.do")
	public void deleteALLSelect(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		bankBranchService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
}
