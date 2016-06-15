package com.taozhu.modules.app.user.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;


@Service("userService")
public class UserService {
	@Autowired
	private BaseDAO baseDao;
	
	
	public Map<String,Object> queryUserById(Map<String,Object> params) throws Exception{
		Map<String,Object> resultMap= (Map<String, Object>)  baseDao.select("UserMapper.queryUserById", params);
		if(resultMap==null){
			resultMap = new HashMap<String,Object>();
		}
		return resultMap;
	}
	
	public List<Map<String,Object>> queryUserByIds(Map<String,Object> params) throws Exception{
		List<Map<String,Object>> resultMap=  baseDao.selectList("UserMapper.queryUserByIds", params);
		if(resultMap==null){
			resultMap = new ArrayList<Map<String,Object>>();
		}
		return resultMap;
	}
	/**
	 * 用户登录
	 * @param params={"account":"帐号","password":"密码"}
	 * @return {"resultFlag":"true/false","msg":"结果消息","result":"[]"}
	 */
	public Map<String,Object> userLogin(Map<String, Object> params){
		Map<String,Object> resultMap= (Map<String, Object>)  baseDao.select("UserMapper.queryUserDetails", params);
		if(resultMap==null){
			resultMap = new HashMap<String,Object>();
		}
		return resultMap;
	}
	
	/**
	 * 用户注册
	 * @param params
	 */
	public int userRegister(Map<String, Object> params){
		return baseDao.save("UserMapper.insertRegisterUser", params);
	}
	
	public Map<String,Object> userDetails(Map<String, Object> params){
		 Map<String,Object> resultMap= (Map<String, Object>) baseDao.select("UserMapper.queryUserDetails", params);
		 if(resultMap==null){
				resultMap = new HashMap<String,Object>();
			}
		 return resultMap;
	}
	
	public Map<String,Object> userPwdDetails(Map<String, Object> params){
		 Map<String,Object> resultMap= (Map<String, Object>) baseDao.select("UserMapper.queryUserPwdDetails", params);
		 if(resultMap==null){
				resultMap = new HashMap<String,Object>();
			}
		 return resultMap;
	}
	
	
	
	public List<Map<String,Object>> queryManagerList(Map<String, Object> params){
		List<Map<String,Object>> list =baseDao.selectList("UserMapper.queryManagerList",params);
		if(list==null){
			list = new ArrayList<Map<String, Object>>();
		}
		return list;
	}
	
	public Map queryUserAttionById(Map<String, Object> params){
		 Map<String,Object> resultMap= (Map<String, Object>) baseDao.select("UserMapper.queryUserAttionById", params);
		 if(resultMap==null){
				resultMap = new HashMap<String,Object>();
			}
		 return resultMap;
	}
	
	public List<Map<String, Object>> queryUserList(Map<String, Object> params){
		List<Map<String, Object>> resultMap= baseDao.selectList("UserMapper.queryList", params);
		if(resultMap==null){
			resultMap = new ArrayList<Map<String, Object>>();
		}
		return resultMap;
	}
	
	public List<Map<String,Object>> findUserAttention(Map<String, Object> params){
		List<Map<String,Object>> list =baseDao.selectList("UserMapper.queryUserAttention",params);
		if(list==null){
			list = new ArrayList<Map<String, Object>>();
		}
		return list;
	}
	
	public int saveUserAttention(Map<String, Object> params){
		int num=baseDao.update("UserMapper.saveUserAttention", params);
		return num;
	}
	
	public int deleteUserAttention(Map<String, Object> params){
		int num=baseDao.delete("UserMapper.deleteUserAttention", params);
		return num;
	}
	
	public void updateUser(Map<String, Object> params){
		baseDao.update("UserMapper.updateUser", params);
	}
	
	public void updateUserPwd(Map<String, Object> params){
		baseDao.update("UserMapper.updateUserPwd", params);
	}
	
	public void updateUserPwdByAccount(Map<String, Object> params){
		baseDao.update("UserMapper.updateUserPwdByAccount", params);
	}
	
	
	public void updateUserAllow(Map<String, Object> params){
		baseDao.update("UserMapper.updateUserAllow", params);
	}
	
	public void updateUserAttentionFlag(Map<String, Object> params){
		baseDao.update("UserMapper.updateUserAttentionFlag", params);
	}
	public void updateUserPosition(Map<String, Object> params){
		baseDao.update("UserMapper.updateUserPosition", params);
	}


}
