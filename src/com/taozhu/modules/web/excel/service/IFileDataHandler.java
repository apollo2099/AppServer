package com.taozhu.modules.web.excel.service;

import java.util.Map;

import com.taozhu.modules.web.excel.pojo.FileDefine;


public interface IFileDataHandler {
	public void execute(Object obj,FileDefine df,Map<String,String> param);
}
