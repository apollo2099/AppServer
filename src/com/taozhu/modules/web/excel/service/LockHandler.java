package com.taozhu.modules.web.excel.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.taozhu.modules.web.excel.pojo.FileDefine;

@Service(value="LockHandler")
public class LockHandler implements ILockHandler {

	@SuppressWarnings("unchecked")
	private Map fileMap = new ConcurrentHashMap();
	
	
	@Override
	public void releaseLock(FileDefine fd, List<Map<String,Object>> para) {
		if(fd.getFileName()!=null){
			fileMap.remove(fd.getFileName());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setLock(FileDefine df, List<Map<String,Object>> datas) {
		// TODO Auto-generated method stub
		if(df.getFileName()!=null){
			if(fileMap.containsKey(df.getFileName()))throw new RuntimeException("当前"+df.getFileName()+"文件正在导入，请不要重复导入!");
			fileMap.put(df.getFileName(), 1);
		}
	}

}
