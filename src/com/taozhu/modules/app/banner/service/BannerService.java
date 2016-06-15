package com.taozhu.modules.app.banner.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="bannerService")
public class BannerService {
	private static final String sqlMap ="BannerMapper";
	@Resource
	private BaseDAO baseDAO;
	
	public List<Map<String,Object>> queryList(){
		List<Map<String,Object>> list =baseDAO.selectList(sqlMap+".queryList");
		if(list==null){
			list =new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	
	public Map<String,Object> queryDetails(Map<String,Object> mp){
		Map<String,Object> map = (Map<String,Object>)baseDAO.select(sqlMap+".querybankBranchDetails", mp);
		if(map==null){
			map = new HashMap<String,Object>();
		}
		List<Map<String,Object>> list =baseDAO.selectList(sqlMap+".queryDetails",mp);
		if(list==null){
			list =new ArrayList<Map<String,Object>>();
		}
		map.put("bankBranchList", list);
		return map;
	}

	public Map<String, Object> queryBannerByCode(Map<String,Object> mp) {
		Map<String,Object> map = (Map)baseDAO.select(sqlMap+".queryBannerByCode", mp);
		if(map==null){
			map = new HashMap<String,Object>();
		}
 		return map;
	}

	public Map<String, Object> queryBankBannerByCode(
			Map<String, Object> paramMap) {
		Map<String,Object> map = (Map)baseDAO.select(sqlMap+".querybranchList", paramMap);
		if(map==null){
			map = new HashMap<String,Object>();
		}
 		return map;
	}
}
