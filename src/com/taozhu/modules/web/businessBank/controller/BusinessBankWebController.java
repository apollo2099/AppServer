package com.taozhu.modules.web.businessBank.controller;

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
import com.taozhu.modules.web.bank.service.BankWebService;
import com.taozhu.modules.web.businessBank.service.BusinessBankWebService;
import com.taozhu.modules.web.businessDetail.service.BusinessDetailWebService;
import com.taozhu.modules.web.businessUser.service.BusinessUserService;
import com.taozhu.modules.web.user.service.UserWebService;

@Controller
@RequestMapping(value = "/businessBankWebController.do")
public class BusinessBankWebController extends BaseWebController{
	@Resource
	private BusinessBankWebService businessBankWebService;
	@Resource
	private BusinessDetailWebService businessDetailWebService;
	@Resource
	private BankWebService bankWebService;

	 /**
     * 业务详情与用户列表查询
     * @param response
     * @param request
     * @return
     */
	@RequestMapping(params="businessBankList")
	public String businessBankList(HttpServletResponse response,HttpServletRequest request){
		return "/businessBank/businessBankGrid";
	}
	/**
	 * 跳转到新增/修改业务详情页面
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params="showBusinessBank")
	public String showBusinessBank(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		
		if(params.get("id")!=null){
			mp = businessBankWebService.getBusinessBankById(params);
		}
		//查询业务列表
		List<Map<String,Object>> businessList= businessDetailWebService.queryList();
		model.put("businessList", businessList);
		//查询银行列表
		List<Map<String,Object>> bankList=bankWebService.getBankList(null);
		model.put("bankList", bankList);
		model.put("mp", mp);
		return "/businessBank/businessBank";
	}
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "saveBusinessBank")
	public void saveBusinessBank(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			businessBankWebService.saveBusinessBank(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			businessBankWebService.updateBusinessBank(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	/**
	 * 批量删除业务详情信息
	 * 备注：ids是所有选中的业务详情id
	 * @param response
	 * @param request
	 */
	@RequestMapping(params="deleteALLSelect")
	public void deleteALLSelect(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		businessBankWebService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
