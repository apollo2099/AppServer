package com.taozhu.modules.web.bank.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("BankWebService")
public class BankWebService {
	
	@Autowired
	private BaseDAO baseDao;
//	
	public List<Map<String,Object>> getBankList(Map params){
		List<Map<String,Object>> meunList = this.baseDao.selectList("BankMapper.queryList", params);
		return meunList;
	}
	public Map getBankById(Map params){
		Map mp = (Map) this.baseDao.select("BankMapper.queryBankById", params);
		return mp;
	}
	/**
	 * 根据银行编码（网点编码）查询银行详情
	 * @param params
	 * @return
	 */
	public Map queryBankByCode(Map params){
		Map mp = (Map) this.baseDao.select("BankMapper.queryBankByCode", params);
		return mp;
	}
	
	public Map queryBankByName(Map params){
		Map mp = (Map) this.baseDao.select("BankMapper.queryBankByName", params);
		return mp;
	}
	
	/**
	 * 根据银行编码查询网点列表
	 * @param params
	 * @return
	 */
	public List<Map> queryBranchList(Map params){
		List<Map> meunList = this.baseDao.selectList("BankMapper.queryBranchList", params);
		return meunList;
	}
	
	
	public boolean saveBank(Map params){
		this.baseDao.save("BankMapper.insertBank", params);
		return true;
	}
	
	public boolean updateBank(Map params){
		this.baseDao.update("BankMapper.updateBank", params);
		return true;
	}
	public void deleteBank(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("BankMapper.deleteBank", params);
	}
	/**
	 * 查询银行编码
	 * @return
	 */
	public synchronized int bankCode(){
		int bankCode = (Integer)baseDao.select("BankMapper.bankCode");
		if(bankCode<100000){
			bankCode=100000;
		}else{
			bankCode+=1;
		}
		return bankCode;
	}
}
