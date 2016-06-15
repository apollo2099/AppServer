package com.taozhu.modules.web.excel.service.user;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.service.HeadDefineHandler;

/**
 * 用户经理临时信息-表头
 * @author liaoxj
 *
 */
@Service("UserHeadDefineHandler")
public class UserHeadDefineHandler  extends HeadDefineHandler{

	private static Map<String,String> colMaps = new LinkedHashMap<String,String>();
	static{
		colMaps.put("账号", "ACCOUNT");
		colMaps.put("昵称", "NICKNAME");
		colMaps.put("手机", "MOBILE");
		colMaps.put("主推业务", "RECOMMEND");
		colMaps.put("银行网点", "BRANCH_CODE");
	}
	@Override
	protected Map<String, String> getMaps() {
		// TODO Auto-generated method stub
		return colMaps;
	}

}
