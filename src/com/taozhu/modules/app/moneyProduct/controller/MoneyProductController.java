package com.taozhu.modules.app.moneyProduct.controller;

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
import com.taozhu.modules.app.moneyProduct.service.MoneyProductService;
@Controller
@RequestMapping(value="MoneyProductController.do")
public class MoneyProductController extends BaseController {
	@Resource
	private MoneyProductService moneyProductService;
	
	@RequestMapping(params="queryMoneyProductList")
	public void queryMoneyProductList(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		List<Map<String,Object>> list = moneyProductService.queryMoneyProductList(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", list);
		this.printJson(response, retVal);
	}
	@RequestMapping(params="queryMoneyProductById")
	public void queryMoneyProductById(HttpServletResponse response,HttpServletRequest request,@RequestParam(value = "param") String paramStr){
		Map<String,Object> paramMap =JSONObject.parseObject(paramStr,HashMap.class);
		Map<String,Object> mp = moneyProductService.queryMoneyProductById(paramMap);
		JSONObject retVal=AppResultUtil.resultPack(true, "返回成功", mp);
		this.printJson(response, retVal);
	}
}
