package com.taozhu.modules.app.user.utils;

import org.springframework.util.StringUtils;

public class UserCodeUtils {

	/**
	 * 创建用户随机码-排重用户已经存在的编码
	 * @return
	 */
	public synchronized static long createRandom(){
		long num=Math.round(Math.random()*89999999+10000000);
		while(true){
			Object obj= UserConstants.USER_ID_MAP.get(num);
			if(StringUtils.isEmpty(obj)){
				return num;
			}
		}
	}
	
	
}
