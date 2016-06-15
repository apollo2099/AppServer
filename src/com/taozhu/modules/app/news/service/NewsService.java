package com.taozhu.modules.app.news.service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;

@Service("newsService")
public class NewsService {
	private static final String sqlMap ="NewsMapper.";
	@Autowired
	private BaseDAO baseDAO;
	/**
	 * 查询新闻列表
	 * @return
	 */
	public List<Map<String,Object>> queryNewsList(Map<String,Object> mp){
		int pageNum =BaseDAOUtil.getIntValue(mp, "pageNum")==0?1:BaseDAOUtil.getIntValue(mp, "pageNum");
		int pageSize =BaseDAOUtil.getIntValue(mp, "pageSize")==0?10:BaseDAOUtil.getIntValue(mp, "pageSize");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = baseDAO.selectList(sqlMap+"queryAppNewsList",mp,pageNum,pageSize);
		return list;
	}
	/**
	 * 根据资讯ID查询相关信息
	 * @param mp
	 * @return
	 */
	public Map<String,Object> queryNewsById(Map<String,Object> mp){
		Map<String,Object> newsMap = new HashMap<String,Object>();
		newsMap = (Map<String, Object>) baseDAO.select(sqlMap+"queryNewsList",mp);
		return newsMap;
	}
}
