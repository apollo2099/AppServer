package com.taozhu.modules.web.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.common.util.StringUtils;

@Service("userWebService")
public class UserWebService {

	@Resource
	private BaseDAO baseDao;
	
	
	
	public Boolean saveUser(Map params){
		this.baseDao.save("UserMapper.saveWebUser", params);
		return true;
		
	}
	public Boolean updateUser(Map params){
		this.baseDao.update("UserMapper.updateWebUser", params);
		return true;
	}
	
	public Boolean updateUserPwd(Map params){
		this.baseDao.update("UserMapper.updateUserPwd", params);
		return true;
	}
	public Map getUserById(Map params){
		Map mp = (Map) this.baseDao.select("UserMapper.getUserById", params);
		return mp;
	}
	public List<Map<String,Object>> queryUserManager(){
		List<Map<String,Object>> list = this.baseDao.selectList("UserMapper.queryUserManagerGrid");
		if(list==null){
			list =new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	/**
	 * 软删除用户信息
	 * @param params =ids选择的用户id列表
	 * @return
	 */
	public Boolean deleteUser(Map params){
		this.baseDao.update("UserMapper.updateUserDelStatus", params);
		return true;
	}
	
	public Boolean isExistsUser(Map params){
		Map mp = (Map) this.baseDao.select("UserMapper.getUserByAcccount", params);
		if(ObjectUtils.isNotEmpty(mp)){
			return true;
		}
		return false;
	}
	
	
	/**********************************************************************************
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryTempUserList(Map<String, Object> params){
		List<Map<String,Object>> list =baseDao.selectList("UserMapper.queryTempUserList",params);
		return list;
	}
	
	
	public List<Map<String,Object>> queryTempErrorDatas(Map<String, Object> params){
		List<Map<String,Object>> list =baseDao.selectList("UserMapper.queryTempErrorDatas",params);
		return list;
	}	
	
	public Boolean updateTempUserMsg(Map params){
		this.baseDao.update("UserMapper.updateTempUserMsg", params);
		return true;
	}
	
	
	
	
}
