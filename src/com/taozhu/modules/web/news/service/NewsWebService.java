package com.taozhu.modules.web.news.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
@Service("newsWebService")
public class NewsWebService {
	private static final String sqlMap = "NewsMapper";
	@Autowired
	private BaseDAO baseDAO;

	public Map queryNewsById(Map params) {
		// TODO Auto-generated method stub
		Map<String,Object> mp =(Map<String, Object>) baseDAO.select(sqlMap+".queryNewsList",params);
		return mp;
	}
	public boolean saveNews(Map params){
		this.baseDAO.save(sqlMap+".insertNews", params);
		return true;
	}
	
	public boolean updateNews(Map params){
		this.baseDAO.update(sqlMap+".updateNews", params);
		return true;
	}
	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDAO.delete(sqlMap+".deleteALLSelect", params);
	}
	
	
}
