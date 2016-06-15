package com.taozhu.modules.web.loan.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service(value="loanWebService")
public class LoanWebService {
	@Autowired
	private BaseDAO baseDao;

	public void deleteALLSelect(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.delete("LoanMapper.deleteALLSelect", params);
	}

	public Map queryLoanById(Map params) {
		// TODO Auto-generated method stub
		Map<String,Object> mp = (Map<String, Object>) baseDao.select("LoanMapper.queryLoanList", params);
		return mp;
	}

	public void saveLoan(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.save("LoanMapper.saveLoan", params);
	}

	public void updateLoan(Map params) {
		// TODO Auto-generated method stub
		this.baseDao.update("LoanMapper.updateLoan", params);
	}
}
