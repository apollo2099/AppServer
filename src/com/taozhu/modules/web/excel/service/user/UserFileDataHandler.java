package com.taozhu.modules.web.excel.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taozhu.modules.web.bankBranch.service.BankBranchService;
import com.taozhu.modules.web.excel.service.FileDataHandler;
import com.taozhu.modules.web.user.service.UserWebService;


/**
 * 数据验证
 * @author liaoxj
 *
 */
@Service("UserFileDataHandler")
public class UserFileDataHandler  extends FileDataHandler{

	@Resource
	private UserWebService userWebService;
	@Resource
	private BankBranchService bankBranchService;
	@Override
	public void validate(Map<String, Object> mp) {
       System.out.println("数据验证同通过="+mp);
       //数据验证同通过={batchNo=UserImport1418220209143_1001, templateCuid=UserImport}
		   	List<Map<String,Object>> userList= userWebService.queryTempUserList(mp);
			if(!StringUtils.isEmpty(userList)){
				for (Map<String, Object> map : userList) {
					//验证当前用户是否存在
					Map<String,Object> param=new HashMap<String, Object>();
					param.put("account", map.get("ACCOUNT"));
					Boolean isFlag=userWebService.isExistsUser(param);
					if(isFlag){
						//更新临时表错误日志信息
						param.clear();
						param.put("num", map.get("NUM"));
						param.put("batchNo", mp.get("batchNo"));
						param.put("templateCuid", mp.get("templateCuid"));
						param.put("msg", "该用户已经注册");
						userWebService.updateTempUserMsg(param);
						continue;
					}
					//验证当前网点是否存在
					param.put("branch_code", Integer.parseInt(map.get("BRANCH_CODE").toString()));
					isFlag=bankBranchService.isExistsBankBranch(param);
	                if(!isFlag){
						param.clear();
						param.put("num", map.get("NUM"));
						param.put("batchNo", mp.get("batchNo"));
						param.put("templateCuid", mp.get("templateCuid"));
						param.put("msg", "该网点不存在");
						userWebService.updateTempUserMsg(param);
	                	continue;
					}
					
					//验证所有数据是否符合规则
				   System.out.println("数据验证同通过="+map.get("account"));
				}
			}
	}

}
