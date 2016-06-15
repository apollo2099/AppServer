package com.taozhu.modules.web.excel.service;

import java.util.Map;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.mybatis.util.BaseDAOUtil;


public class ExcelModelBO {
	
	private final String sqlMap = "FileHandler";
	
	
	private BaseDAO baseDAO;
	
	
	public void setBaseDAO(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}


	@SuppressWarnings("unchecked")
	public String getFileName(String templateId){
		Map<String,String> map = (Map<String, String>) this.baseDAO.select(sqlMap+".getFileDefine", templateId);
		if(map==null)throw new RuntimeException("未定义当前模板【"+templateId+"】，请检查模板配置");
		String filepath= BaseDAOUtil.getStringValue(map, "FILE_PATH");
		return filepath;
	}
	
}
