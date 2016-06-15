package com.taozhu.modules.web.excel.service.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taozhu.common.util.CodeUtils;
import com.taozhu.common.util.MD5Util;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.common.util.SysPropertyUtil;
import com.taozhu.modules.app.user.utils.UserCodeUtils;
import com.taozhu.modules.app.user.utils.UserConstants;
import com.taozhu.modules.web.bank.service.BankWebService;
import com.taozhu.modules.web.bankBranch.service.BankBranchService;
import com.taozhu.modules.web.banner.service.EnterBannerService;
import com.taozhu.modules.web.batch.service.BatchWebService;
import com.taozhu.modules.web.excel.pojo.FileDefine;
import com.taozhu.modules.web.excel.service.ITempDataHandler;
import com.taozhu.modules.web.user.service.UserWebService;

@Service("BatchTempDataHandler")
public class BatchTempDataHandler implements ITempDataHandler{

	@Resource
	private BatchWebService batchWebService;
	@Resource
	private UserWebService userWebService;
	@Resource
	private BankWebService bankWebService;
	@Resource
	private BankBranchService bankBranchService;
	@Resource
	private EnterBannerService enterBannerService;
	
	@Override
	public void execute(List<Map<String, Object>> datas, FileDefine df, Map<String, String> param) {
		// TODO Auto-generated method stub
		String global_path=  SysPropertyUtil.getInstance().getValue("global.path");
		Map<String,Object> pm = new HashMap<String,Object>();
		pm.put("batchNo", df.getBatchNo());
		pm.put("templateCuid", df.getTemplateCuid());
		List<Map<String,Object>> userList= batchWebService.queryTempBatchList(pm);
		if(!StringUtils.isEmpty(userList)){
			for (Map<String, Object> map : userList) {
				Map<String,Object> params=new HashMap<String, Object>();
				
				
				/********************************************************************************
				  * 保存银行信息
				  ********************************************************************************/
				params.put("bank_name",  map.get("BANK_NAME"));
				Map bankMap= bankWebService.queryBankByName(params);
				Integer bankCode=0;
				if(ObjectUtils.isEmpty(bankMap)){
					bankCode=bankWebService.bankCode();
					params.put("bank_name", map.get("BANK_NAME"));
					params.put("code", bankCode);
					bankWebService.saveBank(params);
				}else{
					bankCode=(Integer) bankMap.get("code");
				}

				
				 /********************************************************************************
				   * 保存银行网点信息
				   ********************************************************************************/
				params.clear();
				params.put("bank_name", map.get("BANK_NAME"));
				params.put("branch_name", map.get("BRANCH_NAME"));
				Map branchMap=bankBranchService.queryBranchByName(params);
				Integer branchCode;
				if(ObjectUtils.isEmpty(branchMap)){
					branchCode=bankBranchService.bankBranchCode();
					params.put("bank_code", bankCode);
					params.put("branch_name", map.get("BRANCH_NAME"));
					params.put("order_value", 0);
					params.put("branch_code", branchCode);
					bankBranchService.insertBankBranch(params);
					
					
			
					
					
					//银行网点信息详情
					params.clear();
					Integer branchDetailsCode=bankBranchService.bankBranchDetailsCode();
					params.put("branch_title", map.get("BRANCH_NAME"));
					params.put("branch_code", branchCode);
					
					if (ObjectUtils.isNotEmpty(map.get("ADDRESS"))) {// 网点地址
						params.put("branch_address", map.get("ADDRESS"));
					} else {
						params.put("branch_address", "");
					}
					if (ObjectUtils.isNotEmpty(map.get("POSTCODE"))) {// 网点联系邮编
						params.put("postcode", map.get("POSTCODE"));
					} else {
						params.put("postcode", "");
					}
					if (ObjectUtils.isNotEmpty(map.get("TELEPHONE"))) {// 网点联系电话
						params.put("telephone", map.get("TELEPHONE"));
					} else {
						params.put("telephone", "");
					}
					
					 
					
					params.put("branch_desc", "");
					params.put("code", branchDetailsCode);
					bankBranchService.insertBankBranchDetails(params);
					
					String bannerPathDefault=global_path+"/fileServer/branch_banner_default.png";//设置默默认网点banner
					Integer bannerCode= enterBannerService.branchBannerCode();
					//设置网点默认广告banner
					params.clear();
					params.put("banner_name", "默认广告");
					params.put("banner_path", bannerPathDefault);
					params.put("banner_desc", "");
					params.put("banner_type", 1);
					params.put("is_show", 1);
					params.put("web_url", "");
					params.put("order_value", 0);
					params.put("branch_code", branchCode);
					params.put("modify_name", "");
					params.put("code", bannerCode);
					enterBannerService.saveLv_branch_banner(params);
					
				}else{
					branchCode=(Integer) branchMap.get("code");
				}
				
				
				
				
				
				
				
				
				 /********************************************************************************
				   * 保存业务经理信息
				   ********************************************************************************/
				  //设置用户数据默认值
				  String password=MD5Util.getMD5String("123456");//设置用户的默认密码
				  String headImageDefault=global_path+"/fileServer/head_image_default.png";//设置默认头像
				  String cardImageDefault=global_path+"/fileServer/card_image_default.png"; //设置默认名片
				  
				  //组装用户数据
				  params.put("account", map.get("ACCOUNT"));
				  if(ObjectUtils.isNotEmpty(map.get("NICKNAME"))){//昵称
					  params.put("nick_name", map.get("NICKNAME"));
				  }else{
					  params.put("nick_name", "");
				  }
				  if(ObjectUtils.isNotEmpty(map.get("RECOMMEND"))){//主要推荐
					  params.put("recommend", map.get("RECOMMEND"));
				  }else{
					  params.put("recommend", "");
				  }
				  if(ObjectUtils.isNotEmpty(map.get("MOBILE"))){//电话
					  params.put("mobile",map.get( "MOBILE"));
				  }else{
					  params.put("mobile", "");
				  }
				  if(ObjectUtils.isNotEmpty(map.get("OFFICE"))){
					  params.put("office", map.get("OFFICE"));
				  }else{
					  params.put("office", "");
				  }
				  params.put("branch_code", branchCode);
				  
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
