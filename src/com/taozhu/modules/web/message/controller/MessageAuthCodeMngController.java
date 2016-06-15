package com.taozhu.modules.web.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.taozhu.common.base.controller.BaseWebController;

@Controller
public class MessageAuthCodeMngController extends BaseWebController {

	@RequestMapping(value="/messageAuthCodeGrid.do")
	public String interfaceList(HttpServletResponse response,HttpServletRequest request){
		return "/message/messageAuthCodeGrid";
	}
}
