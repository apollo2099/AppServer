package com.taozhu.modules.app.business.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;

@Service(value="businessService")
public class BusinessService {
	private static final String sqlMap="BusinessMapper";
	@Resource
	private BaseDAO baseDAO;
	/**
	 * 查询业务列表以及业务列表的详情
	 * @return
	 */
	public List<Map<String,Object>> queryList(){
		List l = new ArrayList();
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+".queryList");
		if(list==null){
			list = new ArrayList<Map<String,Object>>();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		//DATO暂时先根据code在调用一次查询
		for(Map<String,Object> mp : list){
			String businessName = BaseDAOUtil.getStringValue(mp, "business_name");
			String business_code = BaseDAOUtil.getStringValue(mp, "code");
			map.put("businessCode", business_code);
			//标识只查询主页显示的子业务
			map.put("isVisible", 1);
			List<Map<String,Object>> alist =  baseDAO.selectList(sqlMap+".queryBusinessDetails", map);
			Map<String,Object> p =new HashMap<String,Object>();
			p.put("business_name", businessName);
			p.put("business_code", business_code);
			p.put("subBusinessList",alist);
			l.add(p);
		}
		return l;
	}
	public List<Map<String,Object>> queryBusinessDetails(Map<String,Object> paramMap){
		List<Map<String,Object>> mp = baseDAO.selectList(sqlMap+".queryBusinessDetails", paramMap);
		if(mp==null){
			mp = new ArrayList<Map<String,Object>>();
		}
		return mp;
	}
	/**
	 * 业务详情搜索
	 * 模糊查询
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> queryBusinessByName(
			Map<String, Object> paramMap) {
		List<Map<String,Object>> list = baseDAO.selectList(sqlMap+".queryBusinessByName", paramMap);
		if(list==null){
			list = new ArrayList<Map<String,Object>>();
		}
		return list;
	}
	public List<Map<String, Object>> queryBusinessList() {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = baseDAO.selectList(sqlMap+".queryList");
		return list;
	}
}
