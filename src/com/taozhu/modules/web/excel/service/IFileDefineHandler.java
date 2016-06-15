package com.taozhu.modules.web.excel.service;

import java.io.IOException;
import java.util.Map;

import com.taozhu.modules.web.excel.pojo.FileDefine;


public interface IFileDefineHandler {
	public FileDefine execute(Object obj,Map<String,String> param) throws IOException;
}
