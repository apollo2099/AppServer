package com.taozhu.modules.web.excel.service.bank;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.modules.web.excel.service.FileErrorBO;
import com.taozhu.modules.web.excel.service.IFileErrorBO;
@Service("BankFileErrorHandler")
public class BankFileErrorHandler extends FileErrorBO {
	@Resource
	private BaseDAO baseDAO;
	@Override
	public Map<String, String> createCol() {
		 Map<String,String> exportErrorMap = new LinkedHashMap<String,String>();
		 exportErrorMap.put("BANK_NAME", "银行名称");
		 exportErrorMap.put("MSG","错误信息");
		return exportErrorMap;
	}

	@Override
	public List<Map<String, Object>> getErrorDatas(Map<String, Object> mp) {
		List<Map<String,Object>> list = baseDAO.selectList("BankMapper.queryErrorDatas", mp);
		return list;
	}

	
}
