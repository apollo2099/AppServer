package com.taozhu.modules.web.excel.service.bussiness;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.service.HeadDefineHandler;

@Service("BusinessHeadDefineHandler")
public class BusinessHeadDefineHandler extends HeadDefineHandler{
	private static Map<String,String> colMaps = new LinkedHashMap<String,String>();
	static{
		colMaps.put("业务名称", "BUSINESS_NAME");
		colMaps.put("子业务名称", "SUB_NAME");
		colMaps.put("子业务图片", "SUB_IMAGES");
		colMaps.put("子业务描述", "SUB_DESC");
		
	}
	@Override
	protected Map<String, String> getMaps() {
		// TODO Auto-generated method stub
		return colMaps;
	}
}
