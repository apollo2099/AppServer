package com.taozhu.modules.web.excel.service.bankBranch;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.modules.web.excel.service.FileErrorBO;
import com.taozhu.modules.web.excel.service.IFileErrorBO;

@Service("BankBranchFileErrorHandler")
public class BankBranchFileErrorHandler extends FileErrorBO {
	@Resource
	private BaseDAO baseDAO;

	@Override
	public Map<String, String> createCol() {
		Map<String, String> exportErrorMap = new LinkedHashMap<String, String>();
		exportErrorMap.put("BANK_NAME", "银行名称");
		exportErrorMap.put("BRANCH_NAME", "网点名称");
		exportErrorMap.put("ADDRESS", "地址");
		exportErrorMap.put("POSTCODE", "邮编");
		exportErrorMap.put("TELEPHONE", "联系电话");
		exportErrorMap.put("MSG", "错误信息");
		return exportErrorMap;
	}

	@Override
	public List<Map<String, Object>> getErrorDatas(Map<String, Object> mp) {
		List<Map<String, Object>> list = baseDAO.selectList(
				"BankBranchMapper.queryErrorDatas", mp);
		return list;
	}

}
