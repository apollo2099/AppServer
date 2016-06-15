package com.taozhu.common.base.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.page.vo.PageInfo;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
@Service(value="datagridService")
public class DatagridService implements IDatagridService {
	@Resource
	private BaseDAO baseDAO;
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}
	@Override
	public PageInfo queryDataGrid(Map<String, Object> mp) {
		// TODO Auto-generated method stub
		String sqlId=BaseDAOUtil.getStringValue(mp, "sqlMap");
		int pageNum = BaseDAOUtil.getIntValue(mp, "page")==0?1:BaseDAOUtil.getIntValue(mp, "page");
		int pageSize = BaseDAOUtil.getIntValue(mp, "rows")==0?30:BaseDAOUtil.getIntValue(mp, "rows");
		return baseDAO.selectPageInfo(sqlId, mp, pageNum, pageSize);
	}

}
