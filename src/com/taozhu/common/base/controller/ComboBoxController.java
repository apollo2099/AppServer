package com.taozhu.common.base.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taozhu.common.base.service.IComboBoxService;
import com.taozhu.common.easyui.compont.ComboBox;
import com.taozhu.common.easyui.util.TagUtil;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.SpringContextUtil;
/**
 * combox公共方法
 * @author admin
 *
 */
@Controller
public class ComboBoxController extends BaseWebController {
	@RequestMapping(value="/getCombobox.do")
	public void getCombobox(HttpServletResponse response,HttpServletRequest request){
		Map<String,Object> mp=this.getRequestParams(request);
		String boName=BaseDAOUtil.getStringValue(mp, "boName");
		if(StringUtils.isBlank(boName)){
			boName = "comboBoxService";
		}
		IComboBoxService bo = (IComboBoxService) SpringContextUtil.getBean(boName);
		List<ComboBox> list = bo.getComboxDataByType(mp);
		TagUtil.comboBox(response, list);
	}
}
