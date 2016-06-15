package com.taozhu.modules.chat.message.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taozhu.common.base.dao.BaseDAO;

@Service("messageService")
public class MessageService {
	
	@Resource
	private BaseDAO baseDao;
	
	public int saveMessage(Map<String, Object> params){
		return baseDao.save("MessageMapper.saveMessage", params);
	}

}
