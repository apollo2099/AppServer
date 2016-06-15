package com.taozhu.modules.web.businessUser.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
@Service(value="businessUserService")
public class BusinessUserService {
	private static final String sqlMap="BusinessUserMapper.";
	@Resource
	private BaseDAO baseDAO;

	public Map getBusinessUserById(Map params) {
		Map mp = (Map) this.baseDAO.select(sqlMap+"queryBusinessUserById", params);
		return mp;
	}

	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDAO.delete(sqlMap+"deleteALLSelect", params);
	}
	
	public void saveBusinessUser(Map params){
		this.baseDAO.save(sqlMap+"insertBusinessUser", params);
	}
	
	public void updateBusinessUser(Map params){
		this.baseDAO.update(sqlMap+"updateBusinessUser", params);
	}
}
