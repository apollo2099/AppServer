package com.taozhu.modules.web.bankBranch.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.util.ObjectUtils;

@Service("bankBranchService")
public class BankBranchService {
	@Resource
	private BaseDAO baseDAO;
	
	public List<Map<String,Object>> queryBankBranchList(Map<String,Object> params){
	  return	baseDAO.selectList("BankBranchMapper.queryBankBranchList", params);
	}
	
	public List<Map<String,Object>> getBankBranchList(){
		List<Map<String,Object>>  alist= baseDAO.selectList("BankBranchMapper.getBankBranchList");
		return alist;
	}
	public Map<String,Object> getBankBranchById(Map<String,Object> params){
		Map<String,Object> mp = (Map<String, Object>) baseDAO.select("BankBranchMapper.queryBankBranchList", params);
		return mp;
	}
	
	public Map<String,Object> queryBranchByName(Map<String,Object> params){
		Map<String,Object> mp = (Map<String, Object>) baseDAO.select("BankBranchMapper.queryBranchByName", params);
		return mp;
	}

	public void insertBankBranch(Map params) {
		this.baseDAO.save("BankBranchMapper.insertBankBranch", params);
	}
	public void insertBankBranchDetails(Map params) {
		this.baseDAO.save("BankBranchMapper.insertBankBranchDetails", params);
	}

	public void updateBankBranch(Map params) {
		this.baseDAO.save("BankBranchMapper.updateBankBranch", params);
	}
	public void updateBankBranchDetails(Map params) {
		this.baseDAO.save("BankBranchMapper.updateBankBranchDetails", params);
	}
	public synchronized int bankBranchCode(){
		int code = (Integer)baseDAO.select("BankBranchMapper.bankBranchCode");
		if(code<200000){
			code = 200000;
		}else{
			code+=1;
		}
		return code;
	}

	public synchronized int bankBranchDetailsCode() {
		int code = (Integer)baseDAO.select("BankBranchMapper.bankBranchDetailsCode");
		if(code<210000){
			code = 210000;
		}else{
			code+=1;
		}
		return code;
	}
	
	public Boolean isExistsBankBranch(Map params){
		Map mp = (Map) this.baseDAO.select("BankBranchMapper.getBankBranchByCode", params);
		if(ObjectUtils.isNotEmpty(mp)){
			return true;
		}
		return false;
	}

	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDAO.delete("BankBranchMapper.deleteALLSelect", params);
	}
}
