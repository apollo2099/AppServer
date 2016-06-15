package com.taozhu.modules.app.bank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="bankService")
public class BankService {
	private static final String sqlMap = "BankMapper";
	@Resource
	private BaseDAO baseDAO;
	public List<Map<String,Object>> queryList(){
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+".queryList");
		if(list==null){
			list =new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	public List<Map<String,Object>> queryBranchList(Map<String,Object> mp){
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+".queryBranchList",mp);
		if(list==null){
			list =new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	public Map queryBranchDetails(Map<String,Object> mp){
		Map<String,Object> branchMap = (Map)baseDAO.select(sqlMap+".queryBranchDetails",mp);
		if(branchMap==null){
			branchMap = new HashMap<String,Object>();
		}
		return branchMap;
	}
	public List<Map<String,Object>> queryBranchBanner(Map<String,Object> mp){
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+".queryBranchBanner",mp);
		if(list==null){
			list =new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	
	public Map<String,Object> queryBankBranch(Map<String,Object> mp){
		Map<String,Object> result = (Map<String, Object>) baseDAO.select("BankBranchMapper.queryBankBranchList",mp);
		if(result==null){
			result = new HashMap<String,Object>();
		}
		return result;
	}
}
