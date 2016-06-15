package com.taozhu.modules.web.excel.service.batch;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.service.HeadDefineHandler;

@Service("BatchHeadDefineHandler")
public class BatchHeadDefineHandler extends HeadDefineHandler{

	private static Map<String,String> colMaps = new LinkedHashMap<String,String>();
	static{
		colMaps.put("银行名称", "BANK_NAME");
		colMaps.put("网点名称", "BRANCH_NAME");
		colMaps.put("地址", "ADDRESS");
		colMaps.put("邮编", "POSTCODE");
		colMaps.put("联系电话", "TELEPHONE");
		colMaps.put("业务经理账号（只限于字母和数字且不能重复）", "ACCOUNT");
		colMaps.put("业务经理昵称", "NICKNAME");
		colMaps.put("职位", "OFFICE");
		colMaps.put("手机", "MOBILE");
		colMaps.put("主推业务", "RECOMMEND");   
	}
	@Override
	protected Map<String, String> getMaps() {
		// TODO Auto-generated method stub
		return colMaps;
	}

}
