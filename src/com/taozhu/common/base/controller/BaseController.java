package com.taozhu.common.base.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 提供app调用的接口类
 * @author admin
 *
 */
public class BaseController {

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		CustomDateEditor dateEditor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}

	protected SerializerFeature[] features = {SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullNumberAsZero,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteDateUseDateFormat};
	protected void printError(HttpServletResponse response, String message){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(message);
		} catch (IOException ie) {
			throw new RuntimeException(ie.getMessage());
		}
	}
	
	protected void printJson(HttpServletResponse response, Object jsonObj) {
		this.printJson(response, jsonObj, new HashMap<String, String>());
	}
	
	protected void printJson(HttpServletResponse response, Object jsonObj, Date expiresDate) {
		Map<String, String> header = new HashMap<String, String>();
		if(expiresDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);  
		    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			header.put("Expires", sdf.format(expiresDate));
		}
		this.printJson(response, jsonObj, header);
	}
	
	protected void printJson(HttpServletResponse response, Object jsonObj, int cacheTimeout) {
		Map<String, String> header = new HashMap<String, String>();
		if(cacheTimeout > 0) {
			header.put("Cache-control", "max-age="+cacheTimeout);
		} else {
			header.put("Cache-control", "no-cache");
		}
		this.printJson(response, jsonObj, header);
	}
	
	protected void printJson(HttpServletResponse response, Object jsonObj, Map<String, String> header) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setStatus(HttpStatus.SC_OK);
			if(header != null) {
				for(String key : header.keySet()) {
					response.setHeader(key, header.get(key));
				}
			}
			response.getWriter().write(JSON.toJSONString(jsonObj,features));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
