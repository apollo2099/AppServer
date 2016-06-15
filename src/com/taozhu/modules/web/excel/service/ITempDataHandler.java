package com.taozhu.modules.web.excel.service;

import java.util.List;
import java.util.Map;
import com.taozhu.modules.web.excel.pojo.FileDefine;


public interface ITempDataHandler {
	public void execute(List<Map<String,Object>> datas,FileDefine df,Map<String,String> param);
}
