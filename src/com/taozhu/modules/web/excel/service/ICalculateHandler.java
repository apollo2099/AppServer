package com.taozhu.modules.web.excel.service;

import java.util.Map;

import com.taozhu.modules.web.excel.pojo.FileDefine;


public interface ICalculateHandler {
	public FileDefine execute(FileDefine df,Map<String,String> param);
}
