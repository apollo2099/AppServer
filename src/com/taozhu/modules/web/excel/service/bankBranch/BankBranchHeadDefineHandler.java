package com.taozhu.modules.web.excel.service.bankBranch;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.service.HeadDefineHandler;
@Service("BankBranchHeadDefineHandler")
public class BankBranchHeadDefineHandler extends HeadDefineHandler {
	private static Map<String,String> headMap = new LinkedHashMap<String, String>();
	static{
		headMap.put("银行名称", "BANK_NAME");
		headMap.put("网点名称", "BRANCH_NAME");
		headMap.put("地址", "ADDRESS");
		headMap.put("邮编", "POSTCODE");
		headMap.put("联系电话", "TELEPHONE");
	}
	@Override
	protected Map<String, String> getMaps() {
		// TODO Auto-generated method stub
		return headMap;
	}

}
