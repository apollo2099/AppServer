package com.taozhu.modules.web.base.menu.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("maFunctionService")
public class MaFunctionService {
	
	@Resource
	private BaseDAO baseDao;
	
	public Map getMaFunction(Map params){
		Map mp = (Map) this.baseDao.select("MaFunctionMapper.commonQuery", params);
		return mp;
	}
	public List<Map> getMaFunctionList(Map params){
		List<Map> meunList = this.baseDao.selectList("MaFunctionMapper.commonQuery", params);
		return meunList;
	}
	
	public boolean saveMaFunction (Map params){
		this.baseDao.save("MaFunctionMapper.saveMaFunction", params);
		return true;
	}
	
	public boolean updateMaFunction(Map params){
		this.baseDao.update("MaFunctionMapper.updateMaFunction", params);
		return true;
	}

}
