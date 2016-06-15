package com.taozhu.modules.web.business.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.util.ObjectUtils;

@Service(value="businessWebService")
public class BusinessWebService {
	private static final String sqlMap="BusinessMapper.";
	@Resource
	private BaseDAO baseDAO;
	
	public List<Map<String,Object>> queryList(){
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+"queryList");
		return list;
	}
	public Map getBusinessById(Map params){
		Map mp = (Map) this.baseDAO.select(sqlMap+"queryBusinessById", params);
		return mp;
	}
	public Map getBusinessByName(Map params){
		Map mp = (Map) this.baseDAO.select(sqlMap+"queryWebBusinessByName", params);
		return mp;
	}
	
	public boolean saveBusiness(Map params){
		this.baseDAO.save(sqlMap+"insertBusiness", params);
		return true;
	}
	
	public boolean updateBusiness(Map params){
		this.baseDAO.update(sqlMap+"updateBusiness", params);
		return true;
	}
	public synchronized int businessCode(){
		int code = (Integer)this.baseDAO.select(sqlMap+"queryBusinessCode");
		if(code<300000){
			code=300000;
		}else{
			code+=1;
		}
		return code;
	}
	
	
	/**********************************************************************************
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryTempUserList(Map<String, Object> params){
		List<Map<String,Object>> list =baseDAO.selectList("BusinessMapper.queryTempUserList",params);
		return list;
	}
	
	
	public List<Map<String,Object>> queryTempErrorDatas(Map<String, Object> params){
		List<Map<String,Object>> list =baseDAO.selectList("BusinessMapper.queryTempErrorDatas",params);
		return list;
	}	
	
	public Boolean updateBusinessUserMsg(Map params){
		this.baseDAO.update("BusinessMapper.updateBusinessUserMsg", params);
		return true;
	}
	
	
	public Boolean isExistsBusiness(Map params){
		Map mp = (Map) this.baseDAO.select("BusinessMapper.queryWebBusinessByName", params);
		if(ObjectUtils.isNotEmpty(mp)){
			return true;
		}
		return false;
	}
	public void deleteBusinessALLSelect(Map params) {
		//先删除子业务然后在删除业务
		this.baseDAO.delete(sqlMap+"deleteBusinessDetails", params);
		this.baseDAO.delete(sqlMap+"deleteBusiness", params);
	}
}


