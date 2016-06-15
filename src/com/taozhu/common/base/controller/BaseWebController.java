package com.taozhu.common.base.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class BaseWebController {
	
	/**
	 * 获取req参数
	 * @param req
	 * @return
	 */
	public Map getRequestParams(HttpServletRequest req) {
		Map map = new HashMap();
		Enumeration e = req.getParameterNames();
		int count = 0;
		while (e.hasMoreElements()) {
			String paramName = (String) e.nextElement();
			String paramValue = req.getParameter(paramName);
			if (StringUtils.isNotBlank(paramValue)) {
				if (paramName.endsWith("time")) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					if(paramValue.indexOf(":")<0){
						df = new SimpleDateFormat("yyyy-MM-dd");
					}
					
					try {
						Date date = df.parse(paramValue);
						map.put(paramName, date);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}  else {
					map.put(paramName, paramValue);
				}
			}
		}
		req.setAttribute("contextPath", req.getContextPath());
		return map;
	}	

}
