package com.taozhu.modules.web.task.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("taskWebService")
public class TaskWebService {
	
	@Resource
	private BaseDAO baseDao;
	public List<Map<String,Object>> queryList(Map params){
		List<Map<String,Object>> list= this.baseDao.selectList("TaskMapper.queryList", params);
		return list;
	}	
	
	
	public int saveTask(Map params){
		return this.baseDao.save("TaskMapper.insertTask", params);		
	}
	public Boolean updateTask(Map params){
		this.baseDao.update("TaskMapper.updateTask", params);
		return true;
	}	
	
	public Boolean updateTaskStatus(Map params){
		this.baseDao.update("TaskMapper.updateTaskStatus", params);
		return true;
	}
	/**
	 * 软删除用户信息
	 * @param params =ids选择的用户id列表
	 * @return
	 */
	public Boolean deleteTask(Map params){
		this.baseDao.update("TaskMapper.deleteTask", params);
		return true;
	}
	
	public Map getTaskById(Map params){
		Map mp = (Map) this.baseDao.select("TaskMapper.getTaskById", params);
		return mp;
	}
	

}
