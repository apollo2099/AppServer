package com.taozhu.common.base.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.service.IDatagridService;
import com.taozhu.common.easyui.util.TagUtil;
import com.taozhu.common.mybatis.page.vo.PageInfo;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.SpringContextUtil;
/**
 * 通用的datagrid方法
 * @author admin
 *
 */
@Controller
public class DataGridController extends BaseWebController{
	
	@RequestMapping(value="/DataGridController.do")
	public void dataGrid(HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> params=this.getRequestParams(request);
		try {
			String queryParams=BaseDAOUtil.getStringValue(params, "queryParams");
			String initParams = BaseDAOUtil.getStringValue(params, "initParams");
			String initQueryParams = BaseDAOUtil.getStringValue(params,"initQueryParams");
			Map<String,Object> queryMap=null;
			if(StringUtils.isNotBlank(queryParams)){
				queryParams=java.net.URLDecoder.decode(queryParams,"UTF-8");
				queryMap=JSONObject.parseObject(queryParams, HashMap.class);
			}else{
				queryMap = new HashMap<String,Object>();
			}
			params.putAll(queryMap);
			if(StringUtils.isNotBlank(initQueryParams)){
				Map<String,Object> initMap=JSONObject.parseObject(initQueryParams, HashMap.class);
				if(!initMap.isEmpty()){
					params.putAll(initMap);
				}
			}
			if(StringUtils.isNotBlank(initParams)){
				if(initParams.startsWith("[{")){
					List<HashMap> list = JSONObject.parseArray(initParams, HashMap.class);
					params.putAll(list.get(0));
				}else if(initParams.startsWith("{")){
					Map<String,Object> initMap=JSONObject.parseObject(initParams, HashMap.class);
					if(!initMap.isEmpty()){
						params.putAll(initMap);
					}
				}
			}
			String sqlMap = BaseDAOUtil.getStringValue(params, "sqlMap");
			if(StringUtils.isEmpty(sqlMap)){
				throw new RuntimeException("查询sql为空，请确认查询语句！");
			}
			String boName=BaseDAOUtil.getStringValue(params,"boName");
			if(StringUtils.isBlank(boName)){
				boName="datagridService";
			}
			IDatagridService bo = (IDatagridService)SpringContextUtil.getBean(boName);
			PageInfo page = bo.queryDataGrid(params);
			TagUtil.datagrid(response, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
