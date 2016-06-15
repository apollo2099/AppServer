package com.taozhu.modules.web.businessDetail.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="businessDetailWebService")
public class BusinessDetailWebService {
	private static final String sqlMap="BusinessDetailMapper.";
	@Resource
	private BaseDAO baseDAO;
	
	public List<Map<String,Object>> queryList(){
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+"queryWebList");
		return list;
	}
	public Map getBusinessDetailById(Map params){
		Map mp = (Map) this.baseDAO.select(sqlMap+"queryBusinessDetailById", params);
		return mp;
	}
	public boolean saveBusinessDetail(Map params){
		this.baseDAO.save(sqlMap+"insertBusinessDetail", params);
		return true;
	}
	
	public boolean updateBusinessDetail(Map params){
		this.baseDAO.update(sqlMap+"updateBusinessDetail", params);
		return true;
	}
	
	public synchronized int businessDetailCode(){
		int code = (Integer)baseDAO.select(sqlMap+"businessDetailCode");
		if(code<310000){
			code=310000;
		}else{
			code+=1;
		}
		return code;
	}
	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDAO.delete(sqlMap+"deleteALLSelect", params);
	}
}


