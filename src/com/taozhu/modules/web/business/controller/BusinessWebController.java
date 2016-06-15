package com.taozhu.modules.web.business.controller;

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
import com.taozhu.modules.web.business.service.BusinessWebService;

@Controller
public class BusinessWebController extends BaseWebController{
	
	@Autowired
	private BusinessWebService businessWebService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveBusiness.do")
	public void saveBusiness(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			params.put("code", businessWebService.businessCode());
			businessWebService.saveBusiness(params);
		params.clear();
		params.put("msg", "插入成功！");
		}else{
			businessWebService.updateBusiness(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/businessGrid.do")
	public String businessGrid(HttpServletResponse response,HttpServletRequest request){
		return "/business/BusinessGrid";
	}
	@RequestMapping(value="/showBusiness.do")
	public String showBusiness(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = businessWebService.getBusinessById(params);
		}
		model.put("mp", mp);
		return "/business/Business";
	}
	
	@RequestMapping(value="/businessImport.do")
	public String businessImport(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		return "/fileImport/BusinessImport";
	}
	/**
	 * 批量删除业务信息
	 * 备注：ids是所有选中的业务详情id
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/deleteBusinessALLSelect.do")
	public void deleteBusinessALLSelect(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		businessWebService.deleteBusinessALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
