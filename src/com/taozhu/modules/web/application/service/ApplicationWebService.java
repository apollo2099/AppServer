package com.taozhu.modules.web.application.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.taozhu.common.base.dao.BaseDAO;

@Service("applicationWebService")
public class ApplicationWebService {

	
	@Resource
	private BaseDAO baseDao;
	
	
	
	public Boolean saveWebApp(Map params){
		this.baseDao.save("AppMapper.saveWebApp", params);
		return true;
		
	}
	public Boolean updateWebApp(Map params){
		this.baseDao.update("AppMapper.updateWebApp", params);
		return true;
	}
	

	public Map getAppById(Map params){
		Map mp = (Map) this.baseDao.select("AppMapper.getAppById", params);
		return mp;
	}
	
	/**
	 * 软删除用户信息
	 * @param params =ids选择的用户id列表
	 * @return
	 */
	public Boolean deleteApp(Map params){
		this.baseDao.update("AppMapper.deleteApp", params);
		return true;
	}
	
}
