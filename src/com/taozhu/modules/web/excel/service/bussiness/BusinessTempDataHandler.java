package com.taozhu.modules.web.excel.service.bussiness;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taozhu.common.util.MD5Util;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.modules.app.user.utils.UserCodeUtils;
import com.taozhu.modules.app.user.utils.UserConstants;
import com.taozhu.modules.web.business.service.BusinessWebService;
import com.taozhu.modules.web.businessDetail.service.BusinessDetailWebService;
import com.taozhu.modules.web.excel.pojo.FileDefine;
import com.taozhu.modules.web.excel.service.ITempDataHandler;

@Service("BusinessTempDataHandler")
public class BusinessTempDataHandler implements ITempDataHandler  {
	@Resource
	private BusinessWebService businessWebService;
	@Resource
	private BusinessDetailWebService businessDetailWebService;
	
	@Override
	public void execute(List<Map<String, Object>> datas, FileDefine df,Map<String, String> param) {
		Map<String,Object> pm = new HashMap<String,Object>();
		pm.put("batchNo", df.getBatchNo());
		pm.put("templateCuid", df.getTemplateCuid());
		List<Map<String,Object>> userList= businessWebService.queryTempUserList(pm);
		if(!StringUtils.isEmpty(userList)){
			for (Map<String, Object> map : userList) {
				  Map<String,Object> params=new HashMap<String, Object>();
				  params.put("business_name", map.get("BUSINESS_NAME"));
				  //根据业务名称判断业务是否存在
				  Map bmap=businessWebService.getBusinessByName(params);
				  int  businesCode=0;
				  if(ObjectUtils.isEmpty(bmap)){//保存业务分类信息
					  businesCode=businessWebService.businessCode();
					  //组装业务列表数据,保存业务列表信息
					  params.put("code", businesCode);
					  params.put("order_value", 0);
					  businessWebService.saveBusiness(params);
				  }else{
					  businesCode=(Integer) bmap.get("code");
				  }
				  
				  params.clear();
				  //保存业务详情信息
				  int businessDetailCode=businessDetailWebService.businessDetailCode();
				  params.put("sub_name", map.get("SUB_NAME"));
				  params.put("sub_images", map.get("SUB_IMAGES"));
				  params.put("sub_desc", map.get("SUB_DESC"));
				  params.put("sub_type", 2);
				  params.put("code", businessDetailCode);
				  params.put("business_code", businesCode);
				  params.put("order_value", 0);
				  params.put("is_visible", 0);
				  
				  businessDetailWebService.saveBusinessDetail(params);
			}
		}
		
	}

}
