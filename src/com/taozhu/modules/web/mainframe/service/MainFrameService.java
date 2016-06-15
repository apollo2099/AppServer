package com.taozhu.modules.web.mainframe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;



@Service("mainFrameService")
public class MainFrameService {
	
	@Autowired
	private BaseDAO baseDao;
	
	public List<Map> getMainFrameMenu(Map params){
		List<Map> meunList = this.baseDao.selectList("MainFrameMapper.commonQuery", params);
		return meunList;
	}
	
	public List<Map> getSecondMenu(Map params){
		//根据一级菜单的ID查找出二级菜单数据
		List<Map> firstMenuList = this.getMainFrameMenu(params);
		//将二级菜单数据转换成tree结构数据
		List secondMenuList = new ArrayList();;
		Map treeNode = null;
		for(Map map : firstMenuList){
			 treeNode = new HashMap();
			 treeNode.put("id", map.get("function_id"));
			 treeNode.put("text", map.get("function_name"));
			 Map attributes = new HashMap();
			 attributes.put("id", map.get("function_id"));
			 attributes.put("name", map.get("function_name"));
			 attributes.put("code", map.get("function_code"));
			 attributes.put("page", map.get("function_page"));
			 attributes.put("parentId", map.get("parent_id"));
			 treeNode.put("attributes", attributes);
			 secondMenuList.add(treeNode);
		}
		return secondMenuList;
	}
}
