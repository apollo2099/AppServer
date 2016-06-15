package com.taozhu.modules.app.message.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.taozhu.common.base.dao.BaseDAO;

@Service("messageAuthCodeService")
public class MessageAuthCodeService {

	@Resource
	private BaseDAO baseDao;
	
	
	public  List<Map<String,Object>> queryList(Map<String, Object> params){
		 List<Map<String,Object>> resultMap= ( List<Map<String,Object>>) baseDao.selectList("MessageAuthCodeMapper.queryList",params);
		 return resultMap;
	}
	
	
	public Map<String,Object> queryDetails(Map<String, Object> params){
		 Map<String,Object> resultMap= (Map<String, Object>) baseDao.select("MessageAuthCodeMapper.queryDetails",params);
		return resultMap;
	}
	
	public int saveMessageAuthCode(Map<String, Object> params){
		return baseDao.save("MessageAuthCodeMapper.saveMessageAuthCode", params);
	}
	
	public int updateMessageAuthStatus(Map<String, Object> params){
		return baseDao.update("MessageAuthCodeMapper.updateMessageAuthStatus", params);
	}
	
	public int updateMessageAuthStatusById(Map<String, Object> params){
		return baseDao.update("MessageAuthCodeMapper.updateMessageAuthStatusById", params);
	}
	
	

}
