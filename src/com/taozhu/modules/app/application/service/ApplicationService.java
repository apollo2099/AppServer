package com.taozhu.modules.app.application.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="applicationService")
public class ApplicationService {

	@Resource
	private BaseDAO baseDAO;
	
	public Map queryDetails(Map<String,Object> mp){
		Map<String,Object> appMap = (Map)baseDAO.select("AppMapper.queryAppByVersion",mp);
		if(appMap==null){
			appMap = new HashMap<String,Object>();
		}
		return appMap;
	}
}
