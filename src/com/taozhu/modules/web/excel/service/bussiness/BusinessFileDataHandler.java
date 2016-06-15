package com.taozhu.modules.web.excel.service.bussiness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taozhu.modules.web.business.service.BusinessWebService;
import com.taozhu.modules.web.businessDetail.service.BusinessDetailWebService;
import com.taozhu.modules.web.excel.service.FileDataHandler;

@Service("BusinessFileDataHandler")
public class BusinessFileDataHandler extends FileDataHandler{

	@Resource
	private BusinessWebService businessWebService;
	
	@Override
	public void validate(Map<String, Object> mp) {
		// TODO Auto-generated method stub
		
	   	List<Map<String,Object>> userList= businessWebService.queryTempUserList(mp);
				if(!StringUtils.isEmpty(userList)){
					for (Map<String, Object> map : userList) {
						//验证业务名称和业务详情名称是否存在
						Map<String,Object> param=new HashMap<String, Object>();
						param.put("business_name", map.get("BUSINESS_NAME"));
						param.put("sub_name", map.get("SUB_NAME"));
						Boolean isFlag=businessWebService.isExistsBusiness(param);
						if(isFlag){
							//更新临时表错误日志信息
							param.clear();
							param.put("num", map.get("NUM"));
							param.put("batchNo", mp.get("batchNo"));
							param.put("templateCuid", mp.get("templateCuid"));
							param.put("msg", "业务名称已经存在");
							businessWebService.updateBusinessUserMsg(param);
							continue;
						}						
						//验证所有数据是否符合规则
					   System.out.println("数据验证同通过="+map.get("BUSINESS_NAME"));
					}
				}
	}

}
