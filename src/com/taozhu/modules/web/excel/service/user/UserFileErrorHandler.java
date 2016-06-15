package com.taozhu.modules.web.excel.service.user;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.service.FileErrorBO;
import com.taozhu.modules.web.user.service.UserWebService;



@Service("UserFileErrorHandler")
public class UserFileErrorHandler extends FileErrorBO{

	@Resource
	private UserWebService userWebService;
	
	@Override
	public Map<String, String> createCol() {
		 Map<String,String> exportErrorMap = new LinkedHashMap<String,String>();
		 exportErrorMap.put("ACCOUNT", "账号");
		 exportErrorMap.put("NICKNAME", "昵称");
		 exportErrorMap.put("MOBILE", "手机");
		 exportErrorMap.put("RECOMMEND", "主推业务");
		 exportErrorMap.put("BRANCH_CODE", "银行网点");
		 exportErrorMap.put("MSG","错误信息");
		 return exportErrorMap;
	}

	@Override
	public List<Map<String, Object>> getErrorDatas(Map<String, Object> mp) {
		List<Map<String,Object>> list =userWebService.queryTempErrorDatas(mp);
		return list;
	}
	
}