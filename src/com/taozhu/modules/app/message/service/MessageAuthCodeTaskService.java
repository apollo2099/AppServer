package com.taozhu.modules.app.message.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.taozhu.common.util.DateUtil;
import com.taozhu.common.util.ObjectUtils;

@Service("MessageAuthCodeTaskService")
public class MessageAuthCodeTaskService {
	
	@Resource
	private MessageAuthCodeService messageAuthCodeService;
	
	public void init(){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("status",1);
		List<Map<String,Object>> messageList=messageAuthCodeService.queryList(params);
		if(ObjectUtils.isNotEmpty(messageList)){
			for (Map<String, Object> map : messageList) {
				Date createTime=(Date) map.get("create_time");
				Date date=new Date();
				//计算验证码的创建时间和当前时间差（超过30分钟判断验证码失效）
				long day=  DateUtil.dateDiff(createTime,new Date(), "yyyy-MM-dd hh:mm:ss", "d");
				long hour=  DateUtil.dateDiff(createTime,new Date(), "yyyy-MM-dd hh:mm:ss", "h");
				long min=  DateUtil.dateDiff(createTime,new Date(), "yyyy-MM-dd hh:mm:ss", "m");
				if(day>=1||hour>=1||min>30){
					//修改验证码失效
					params.clear();
					params.put("status", 0);
					params.put("id", map.get("id"));
					messageAuthCodeService.updateMessageAuthStatusById(params);
				}
			}
		}
		
		System.out.println(" MessageAuthCodeTaskService test");
	}
}