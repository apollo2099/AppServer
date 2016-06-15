package com.taozhu.modules.app.moneyProduct.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;

@Service(value="moneyProductService")
public class MoneyProductService {
	@Autowired
	private BaseDAO baseDao;

	public List<Map<String, Object>> queryMoneyProductList(
			Map<String, Object> paramMap) {
		int pageNum =BaseDAOUtil.getIntValue(paramMap, "pageNum")==0?1:BaseDAOUtil.getIntValue(paramMap, "pageNum");
		int pageSize =BaseDAOUtil.getIntValue(paramMap, "pageSize")==0?10:BaseDAOUtil.getIntValue(paramMap, "pageSize");
		List<Map<String,Object>> list = baseDao.selectList("MoneyProductMapper.queryMoneyProduct",paramMap,pageNum,pageSize);
		return list;
	}

	public Map<String, Object> queryMoneyProductById(
			Map<String, Object> paramMap) {
		Map<String,Object> mp = (Map<String, Object>) baseDao.select("MoneyProductMapper.queryAppMoneyProductList", paramMap);
		return mp;
	}
}
