package com.taozhu.modules.web.lvInterface.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.taozhu.common.base.controller.BaseWebController;
import com.taozhu.common.easyui.base.AjaxJson;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.AjaxUtil;
import com.taozhu.common.util.CodeUtils;
import com.taozhu.modules.web.lvInterface.service.LvInterfaceService;



@Controller
public class LvInterfaceController extends BaseWebController{
	
	@Autowired
	private LvInterfaceService lvInterfaceService;
	
	/**
	 * 插入和更新 返回需要两个字段 success、msg
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveInterface.do")
	public void saveInterface(HttpServletRequest request,HttpServletResponse response){
		Map params = this.getRequestParams(request);
		int id = BaseDAOUtil.getIntValue(params, "id");
		if(id==0){
			params.put("create_time", new Date());
		lvInterfaceService.saveInterface(params);
		params.clear();
		params.put("msg", "插入成功！");
		}else{
			lvInterfaceService.updateInterface(params);	
			params.clear();
			params.put("msg", "更新成功！");
		}
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
	
	@RequestMapping(value="/interfaceGrid.do")
	public String interfaceList(HttpServletResponse response,HttpServletRequest request){
		return "/lvInterface/InterfaceGrid";
	}
	@RequestMapping(value="/addorupdate.do")
	public String addorupdate(HttpServletResponse response,HttpServletRequest request,Map<String,Object> model){
		Map params = this.getRequestParams(request);
		Map mp = new HashMap();
		if(params.get("id")!=null){
			mp = lvInterfaceService.getInterfaceById(params);
		}
		model.put("mp", mp);
		return "/lvInterface/Interface";
	}
	public void deleteALLSelect(HttpServletResponse response,HttpServletRequest request){
		Map params = this.getRequestParams(request);
		lvInterfaceService.deleteALLSelect(params);
		params.put("msg", "删除成功!");
		params.put("success", true);
		AjaxUtil.ajaxResponse(response, JSONArray.toJSONString(params), AjaxUtil.RESPONCE_TYPE_JSON);
	}
}
