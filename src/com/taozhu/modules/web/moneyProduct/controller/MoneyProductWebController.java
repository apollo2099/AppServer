package com.taozhu.modules.web.moneyProduct.controller;

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
import com.taozhu.modules.web.moneyProduct.service.MoneyProductWebService;
@Controller
@RequestMapping(value="MoneyProductWebController.do")
public class MoneyProductWebController extends BaseWebController {
	@Resource
	private MoneyProductWebService moneyProductWebService;
	@Resource
	private BusinessWebService businessWebService;
	@RequestMapping(params="queryMoneyProduct")
	public String queryMoneyProduct(HttpServletResponse response,HttpServletRequest request){
		return "moneyProduct/MoneyProductGrid";
	}
	
	@RequestMapping(params="showMoneyProduct")
	public String showMoneyProduct(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = moneyProductWebService.queryMoneyProductById(params);
		}
		//查询业务列表
		List<Map<String,Object>> businessList= businessWebService.queryList();
		model.put("businessList", businessList);
		model.put("mp", mp);
		return "/moneyProduct/MoneyProduct";
	}
	
	@RequestMapping(params="deleteALLSelect")
	public void deleteALLSelect(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		moneyProductWebService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	@RequestMapping(params="saveMoneyProduct")
	public void saveMoneyProduct(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			moneyProductWebService.saveMoneyProduct(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			moneyProductWebService.updateMoneyProduct(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
