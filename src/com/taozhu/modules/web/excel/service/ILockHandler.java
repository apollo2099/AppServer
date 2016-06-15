package com.taozhu.modules.web.excel.service;

import java.util.List;
import java.util.Map;

import com.taozhu.modules.web.excel.pojo.FileDefine;


public interface ILockHandler {
	
	public void setLock(FileDefine df,List<Map<String,Object>> datas);
	
	public void releaseLock(FileDefine df,List<Map<String,Object>> datas);
}
