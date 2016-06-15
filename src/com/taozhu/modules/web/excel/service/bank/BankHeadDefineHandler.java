package com.taozhu.modules.web.excel.service.bank;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.service.HeadDefineHandler;
/**
 * 表头
 * @author admin
 *
 */
@Service("BankHeadDefineHandler")
public class BankHeadDefineHandler extends HeadDefineHandler {
	private static Map<String,String> colMaps = new LinkedHashMap<String,String>();
	static{
		colMaps.put("银行名称", "BANK_NAME");
	}
	@Override
	protected Map<String, String> getMaps() {
		// TODO Auto-generated method stub
		return colMaps;
	}

}
