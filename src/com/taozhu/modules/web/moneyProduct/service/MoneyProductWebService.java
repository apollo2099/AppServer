package com.taozhu.modules.web.moneyProduct.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="moneyProductWebService")
public class MoneyProductWebService {
	@Autowired
	private BaseDAO baseDao;

	public Map queryMoneyProductById(Map params) {
		// TODO Auto-generated method stub
		Map<String,Object> mp = (Map<String, Object>) baseDao.select("MoneyProductMapper.queryMoneyProductList", params);
		return mp;
	}

	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("MoneyProductMapper.deleteALLSelect", params);
	}

	public void saveMoneyProduct(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.save("MoneyProductMapper.saveMoneyProduct", params);
	}

	public void updateMoneyProduct(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.update("MoneyProductMapper.updateMoneyProduct", params);
	}
}
