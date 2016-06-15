package com.taozhu.modules.app.common;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 接口数据返回值公共类
 * @author liaoxj
 * @date 2014-12-03
 */
public class AppResultUtil {

	
	public static JSONObject resultPack(Boolean resultFlag,String msg,List<Map<String,Object>> alist){
		JSONObject retVal = new JSONObject();
		retVal.put("success", resultFlag);
		retVal.put("msg", msg);
		retVal.put("result", JSONObject.toJSON(alist));
		return retVal;
	}
	
	public static JSONObject resultPack(Boolean resultFlag,String msg,Map<String,Object> map){
		JSONObject retVal = new JSONObject();
		retVal.put("success", resultFlag);
		retVal.put("msg", msg);
		retVal.put("result", JSONObject.toJSON(map));
		return retVal;
	}
	
	public static JSONObject resultPack(Boolean resultFlag,String msg,String result){
		JSONObject retVal = new JSONObject();
		retVal.put("success", resultFlag);
		retVal.put("msg", msg);
		retVal.put("result", JSONObject.toJSON(result));
		return retVal;
	}


}
