package com.taozhu.modules.web.excel.service;

import java.util.List;
import java.util.Map;

import com.taozhu.modules.web.excel.pojo.ColDefine;
import com.taozhu.modules.web.excel.pojo.FileDefine;


public interface IHeadDefineHandler {
	public List<ColDefine> execute(FileDefine fd,Object obj,Map<String, String> param);
}
