package com.taozhu.modules.web.excel.service.batch;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.batch.service.BatchWebService;
import com.taozhu.modules.web.excel.service.FileErrorBO;

@Service("BatchFileErrorHandler")
public class BatchFileErrorHandler extends FileErrorBO{

	@Resource
	private BatchWebService batchWebService;
	@Override
	public Map<String, String> createCol() {
		 Map<String,String> exportErrorMap = new LinkedHashMap<String,String>();
		 exportErrorMap.put("BANK_NAME", "银行名称");
		 exportErrorMap.put("BRANCH_NAME", "网点名称");
		 exportErrorMap.put("ADDRESS", "地址");
		 exportErrorMap.put("POSTCODE", "邮编");
		 exportErrorMap.put("TELEPHONE", "联系电话");
		 exportErrorMap.put("ACCOUNT", "业务经理账号（只限于字母和数字且不能重复）");
		 exportErrorMap.put("NICKNAME", "业务经理昵称");
		 exportErrorMap.put("OFFICE", "职位");
		 exportErrorMap.put("MOBILE", "手机");
		 exportErrorMap.put("RECOMMEND", "主推业务");
		 exportErrorMap.put("MSG","错误信息");
		 return exportErrorMap;
	}

	@Override
	public List<Map<String, Object>> getErrorDatas(Map<String, Object> mp) {
		List<Map<String,Object>> list =batchWebService.queryTempErrorDatas(mp);
		return list;
	}

}
