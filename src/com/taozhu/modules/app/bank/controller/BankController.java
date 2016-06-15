package com.taozhu.modules.app.bank.controller;

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
import com.taozhu.modules.app.bank.service.BankService;
import com.taozhu.modules.app.common.AppResultUtil;
@Controller
@RequestMapping(value="/bankController.do")
public class BankController extends BaseController {
	@Resource(name="bankService")
	private BankService bankService;
	
	/**
	 * 银行列表查询
	 * @param response
	 * @param request
	 * @param param={"subBusinessCode":"子业务编号（非必填）"}
	 * @return {"resultFlag":"true/false","msg":"结果消息","result":"[]"}
	 */
	@RequestMapping(params="queryList")
	public void queryList(HttpServletResponse response,HttpServletRequest request){
		List<Map<String,Object>> alist =bankService.queryList();
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	
	/**
	 * 银行网点查询
	 * @param response
	 * @param request
	 * @param param={"bankCode":"银行编号（必填）"}
	 * @return {"resultFlag":"true/false","msg":"结果消息","result":"[]"}
	 */
	@RequestMapping(params="queryBranchList")
	public void queryBranchList(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String bannerCode){
		Map<String, Object> paramMap = JSONObject.parseObject(bannerCode,HashMap.class);
		List<Map<String,Object>> alist =bankService.queryBranchList(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", alist);
		this.printJson(response, retVal);
	}
	/**
	 * 根据网点code查询简介和广告
	 * @param response
	 * @param param={"branchCode":"网点编号（必填）"}
	 * @return {"resultFlag":"true/false","msg":"结果消息","result":"[]"}
	 */
	@RequestMapping(params="queryBranchDetails")
	public void queryBranchDetails(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		Map<String,Object>  mp = bankService.queryBranchDetails(paramMap);
		List<Map<String,Object>> list = bankService.queryBranchBanner(mp);
		//将网点下面的广告放入map中
		mp.put("banner", list);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", mp);
		this.printJson(response, retVal);
	}
}
