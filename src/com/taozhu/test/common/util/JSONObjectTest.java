package com.taozhu.test.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.util.CodeUtils;

public class  JSONObjectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> returnlist =new ArrayList<Map<String,Object>>();
		Map<String,Object> map=new HashedMap();
		map.put("code", CodeUtils.getCode("USER"));
		returnlist.add(map);
		Object result= JSONObject.toJSON(returnlist);
		System.out.println(result);
		
		String resultStr="";
		System.out.println("TEST:"+JSONObject.toJSON(resultStr));
		JSONObject retVal = new JSONObject();
	
		retVal.put("success", true);
		retVal.put("msg", "返回成功");
		retVal.put("result", JSONObject.toJSON(resultStr));
		System.out.println(JSONObject.toJSON(retVal));
	}

}
