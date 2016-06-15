package com.taozhu.modules.web.lvInterface.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("lvInterfaceService")
public class LvInterfaceService {
	
	@Autowired
	private BaseDAO baseDao;
	
	public List<Map> getInterfaceList(Map params){
		List<Map> meunList = this.baseDao.selectList("LvInterfaceMapper.queryInterfaceList", params);
		return meunList;
	}
	public Map getInterfaceById(Map params){
		Map mp = (Map) this.baseDao.select("LvInterfaceMapper.queryInterfaceById", params);
		return mp;
	}
	public boolean saveInterface(Map params){
		this.baseDao.save("LvInterfaceMapper.insert", params);
		return true;
	}
	
	public boolean updateInterface(Map params){
		this.baseDao.update("LvInterfaceMapper.update", params);
		return true;
	}
	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("LvInterfaceMapper.deleteALLSelect", params);
	}
}
