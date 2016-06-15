package com.taozhu.test.modules.message.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.taozhu.modules.chat.message.service.MessageTaskService;
import com.taozhu.test.common.util.BaseJunitCase;

public class MessageTaskServiceTest  extends BaseJunitCase{
	
	@Resource
	private MessageTaskService messageTaskService;
	
	@Test
	public void downloadTest(){
		messageTaskService.download();
	}

}
