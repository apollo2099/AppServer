package com.taozhu.modules.web.base.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("maUserService")
public class MaUserService {
	
	@Autowired
	private BaseDAO baseDao;
	
	public Map getMaUser(Map params){
		Map mp = (Map) this.baseDao.select("MaUserMapper.commonQuery", params);
		return mp;
	}
	public Map getMaUserById(Map params){
		Map mp = (Map) this.baseDao.select("MaUserMapper.queryDetails", params);
		return mp;
	}
	
	public List<Map> getMaUserList(Map params){
		List<Map> meunList = this.baseDao.selectList("MaUserMapper.commonQuery", params);
		return meunList;
	}
	
	public boolean saveMaUser(Map params){
		this.baseDao.save("MaUserMapper.insertUser", params);
		return true;
	}
	
	public boolean updateMaUser(Map params){
		this.baseDao.update("MaUserMapper.updateUser", params);
		return true;
	}
	
	public boolean updateMaUserPwd(Map params){
		this.baseDao.update("MaUserMapper.updateUserPwd", params);
		return true;
	}

}
