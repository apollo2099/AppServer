package com.taozhu.modules.web.excel.service.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.taozhu.common.base.dao.BaseDAO;
import com.taozhu.common.util.MD5Util;
import com.taozhu.common.util.SysPropertyUtil;
import com.taozhu.modules.app.user.utils.UserCodeUtils;
import com.taozhu.modules.app.user.utils.UserConstants;
import com.taozhu.modules.web.excel.pojo.FileDefine;
import com.taozhu.modules.web.excel.service.ITempDataHandler;
import com.taozhu.modules.web.user.service.UserWebService;

/**
 * 
 * 临时表入正式库
 * @author liaoxj
 *
 */
@Service("UserTempDataHandler")
public class UserTempDataHandler implements ITempDataHandler {
	
    @Resource
    private UserWebService userWebService;
	
	@Override
	public void execute(List<Map<String, Object>> datas, FileDefine df,
			Map<String, String> param) {
		// TODO Auto-generated method stub
		String global_path=  SysPropertyUtil.getInstance().getValue("global.path");
		Map<String,Object> pm = new HashMap<String,Object>();
		pm.put("batchNo", df.getBatchNo());
		pm.put("templateCuid", df.getTemplateCuid());
		List<Map<String,Object>> userList= userWebService.queryTempUserList(pm);
		if(!StringUtils.isEmpty(userList)){
			for (Map<String, Object> map : userList) {
				  Map<String,Object> params=new HashMap<String, Object>();
				  
				  //设置用户数据默认值
				  String password=MD5Util.getMD5String("123456");//设置用户的默认密码
				  String headImageDefault=global_path+"/fileServer/head_image_default.png";//设置默认头像
				  String cardImageDefault=global_path+"/fileServer/card_image_default.png"; //设置默认名片
				  
				  //组装用户数据
				  params.put("account", map.get("ACCOUNT"));
				  params.put("nick_name", map.get("NICKNAME"));
				  params.put("recommend", map.get("RECOMMEND"));
				  params.put("mobile",map.get( "MOBILE"));
				  params.put("branch_code", map.get("BRANCH_CODE"));
				  
				  params.put("head_image", headImageDefault);
				  params.put("user_card_path", cardImageDefault);
				  params.put("introduction", "我就是我");
				  params.put("tel", "");
				  params.put("is_allow", UserConstants.USER_IS_ALLOW_FALSE);
				  params.put("sex", UserConstants.SEX_UN_KNOWN);
				  params.put("password", MD5Util.getMD5String(password));
				  params.put("create_time", new Date());
				  params.put("user_id", UserCodeUtils.createRandom());
				  params.put("status", 1);
				  params.put("longitude", 0.0);
				  params.put("latitude", 0.0);
				  params.put("user_type", UserConstants.USER_TYPE_MANAGER);
				  userWebService.saveUser(params);
			}
		}
	}
	
	

}
