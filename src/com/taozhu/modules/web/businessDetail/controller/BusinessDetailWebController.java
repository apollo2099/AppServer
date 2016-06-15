package com.taozhu.modules.web.businessDetail.controller;

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
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.web.business.service.BusinessWebService;
import com.taozhu.modules.web.businessDetail.service.BusinessDetailWebService;

@Controller
@RequestMapping(value = "/businessDetailWebController.do")
public class BusinessDetailWebController extends BaseWebController{
	
	@Resource
	private BusinessDetailWebService businessDetailWebService;
	@Resource
	private BusinessWebService businessWebService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "saveBusinessDetail")
	public void saveBusinessDetail(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			params.put("code", businessDetailWebService.businessDetailCode());
			businessDetailWebService.saveBusinessDetail(params);
			params.clear();
			params.put("msg", "插入成功！");
		}else{
			businessDetailWebService.updateBusinessDetail(params);
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
    /**
     * 业务详情列表查询
     * @param response
     * @param request
     * @return
     */
	@RequestMapping(params="businessDetailGrid")
	public String businessDetailList(HttpServletResponse response,HttpServletRequest request){
		return "/businessDetail/BusinessDetailGrid";
	}
	
	/**
	 * 跳转到新增/修改业务详情页面
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params="showBusinessDetail")
	public String showBusinessDetail(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		
		if(params.get("id")!=null){
			mp = businessDetailWebService.getBusinessDetailById(params);
		}
		//查询业务列表
		List<Map<String,Object>> businessList= businessWebService.queryList();
		model.put("businessList", businessList);
		model.put("mp", mp);
		return "/businessDetail/BusinessDetail";
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
		businessDetailWebService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
