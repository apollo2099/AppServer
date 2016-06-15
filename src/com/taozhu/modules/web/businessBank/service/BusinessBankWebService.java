package com.taozhu.modules.web.businessBank.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="businessBankWebService")
public class BusinessBankWebService {
	@Resource
	private BaseDAO baseDAO;

	public Map getBusinessBankById(Map params) {
		Map mp = (Map) this.baseDAO.select("BusinessBankMapper.queryBusinessBankById", params);
		return mp;
	}

	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDAO.delete("BusinessBankMapper.deleteALLSelect", params);
	}
	
	public void saveBusinessBank(Map params){
		this.baseDAO.save("BusinessBankMapper.insertBusinessBank", params);
	}
	
	public void updateBusinessBank(Map params){
		this.baseDAO.update("BusinessBankMapper.updateBusinessBank", params);
	}
}
