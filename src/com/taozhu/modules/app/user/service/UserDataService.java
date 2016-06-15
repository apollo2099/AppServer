package com.taozhu.modules.app.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.taozhu.modules.app.user.utils.UserConstants;

@Service("userDataService")
public class UserDataService {

   @Resource
   private UserService userService;
	
   @PostConstruct
	public void initUserData(){
	   List<Map<String,Object>> userList=userService.queryUserList(null);
	   if (!StringUtils.isEmpty(userList)) {
		  for (int i = 0; i <userList.size(); i++) {
			  Map<String,Object> map=userList.get(i);
			  if(!StringUtils.isEmpty(map)){
				  if(!StringUtils.isEmpty(map.get("user_id"))){
					  Integer user_id=Integer.parseInt(map.get("user_id").toString());
					  UserConstants.USER_ID_MAP.put(user_id.toString(), user_id);
				  }
			  }
		  }
	   }
	   
		
	}
}
