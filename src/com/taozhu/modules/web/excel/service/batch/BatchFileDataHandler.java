package com.taozhu.modules.web.excel.service.batch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taozhu.common.util.ObjectUtils;
import com.taozhu.modules.web.batch.service.BatchWebService;
import com.taozhu.modules.web.excel.service.FileDataHandler;
import com.taozhu.modules.web.user.service.UserWebService;

@Service("BatchFileDataHandler")
public class BatchFileDataHandler extends FileDataHandler {
	@Resource
	private BatchWebService batchWebService;
	@Resource
	private UserWebService userWebService;

	@Override
	public void validate(Map<String, Object> mp) {
		List<Map<String, Object>> batchList = batchWebService.queryTempBatchList(mp);
		if (ObjectUtils.isNotEmpty(batchList)) {
			for (Map<String, Object> map : batchList) {
				// 验证当前用户是否存在
				Map<String, Object> param = new HashMap<String, Object>();
				
				if(ObjectUtils.isEmpty(map.get("ACCOUNT"))){
					param.clear();
					param.put("num", map.get("NUM"));
					param.put("batchNo", mp.get("batchNo"));
					param.put("templateCuid", mp.get("templateCuid"));
					param.put("msg", "注册帐号不能为空");
					batchWebService.updateTempBatchMsg(param);
					continue;
				}else{
					param.put("account", map.get("ACCOUNT"));
					Boolean isFlag = userWebService.isExistsUser(param);
					if (isFlag) {
						// 更新临时表错误日志信息
						param.clear();
						param.put("num", map.get("NUM"));
						param.put("batchNo", mp.get("batchNo"));
						param.put("templateCuid", mp.get("templateCuid"));
						param.put("msg", "该用户已经注册");
						batchWebService.updateTempBatchMsg(param);
						continue;
					}
				}
				
				
			
				// //验证当前网点是否存在
				// param.put("branch_code",
				// Integer.parseInt(map.get("BRANCH_CODE").toString()));
				// isFlag=bankBranchService.isExistsBankBranch(param);
				// if(!isFlag){
				// param.clear();
				// param.put("num", map.get("NUM"));
				// param.put("batchNo", mp.get("batchNo"));
				// param.put("templateCuid", mp.get("templateCuid"));
				// param.put("msg", "该网点不存在");
				// userWebService.updateTempUserMsg(param);
				// continue;
				// }

			}
		}
	}
}
