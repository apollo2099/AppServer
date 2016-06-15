package com.taozhu.modules.web.excel.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taozhu.common.util.SpringContextUtil;
import com.taozhu.modules.web.excel.service.IFileErrorBO;

@Controller
public class FileExportController {

	@RequestMapping(value="/exporterrors.do")
	public void exportErrors(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("boName") String boName,
			@RequestParam("templateId") String relatedTemplateCuid,
			@RequestParam("batchNo") String batchNo) throws IOException {
		IFileErrorBO bo = (IFileErrorBO)SpringContextUtil.getBean(boName);
		String filepath = bo.exportErrorFile(batchNo, relatedTemplateCuid);
		if(StringUtils.isNotBlank(filepath)) {
			File file = new File(filepath);
			response.sendRedirect(request.getContextPath()+"/download.do?file="+URLEncoder.encode(URLEncoder.encode(filepath, "utf-8"), "utf-8")+"&fileName="+URLEncoder.encode(URLEncoder.encode(file.getName(), "utf-8"), "utf-8"));
		}
	}
	
}
