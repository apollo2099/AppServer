package com.taozhu.modules.web.excel.service.bussiness;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.business.service.BusinessWebService;
import com.taozhu.modules.web.excel.service.FileErrorBO;


@Service("BusinessFileErrorHandler")
public class BusinessFileErrorHandler extends FileErrorBO{

	@Resource
	private BusinessWebService businessWebService;
	
	@Override
	public Map<String, String> createCol() {
		 Map<String,String> exportErrorMap = new LinkedHashMap<String,String>();
		 exportErrorMap.put("BUSINESS_NAME", "业务名称");
		 exportErrorMap.put("SUB_NAME", "子业务名称	");
		 exportErrorMap.put("SUB_DESC", "子业务描述");
		 exportErrorMap.put("SUB_IMAGES", "子业务图片");
		 exportErrorMap.put("MSG","错误信息");
		 return exportErrorMap;
	}

	@Override
	public List<Map<String, Object>> getErrorDatas(Map<String, Object> mp) {
		List<Map<String,Object>> list =businessWebService.queryTempErrorDatas(mp);
		return list;
	}

}
