package com.taozhu.modules.web.batch.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="batchWebService")
public class BatchWebService {

	@Resource
	private BaseDAO baseDao;
	
	/**********************************************************************************
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> queryTempBatchList(Map<String, Object> params){
		List<Map<String,Object>> list =baseDao.selectList("BatchMapper.queryTempBatchList",params);
		return list;
	}
	
	
	public List<Map<String,Object>> queryTempErrorDatas(Map<String, Object> params){
		List<Map<String,Object>> list =baseDao.selectList("BatchMapper.queryTempErrorDatas",params);
		return list;
	}	
	
	public Map<String,Object> calculate(Map<String, Object> params){
		Map<String,Object> map = (Map)baseDao.select("BatchMapper.calculate",params);
		return map;
	}	
	
	public Boolean updateTempBatchMsg(Map params){
		this.baseDao.update("BatchMapper.updateTempBatchMsg", params);
		return true;
	}
}
