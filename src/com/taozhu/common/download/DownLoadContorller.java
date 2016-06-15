package com.taozhu.common.download;

import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taozhu.common.util.SpringContextUtil;

@Controller
public class DownLoadContorller {

	@RequestMapping(value="download.do")
	public void execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String file = request.getParameter("file");
		String fileName = request.getParameter("fileName");
		
		file = URLDecoder.decode(file, "UTF-8");
		file = "file:"+StringUtils.replace(file, "//", "/");
		ServletOutputStream os = response.getOutputStream();
		Resource res = SpringContextUtil.getApplicationContext().getResource(file);
		response.setContentType("application/octet-stream;charset=utf-8");
		if(StringUtils.isEmpty(fileName)){
			response.setHeader("Content-disposition","attachment;filename="+ new String(res.getFilename().getBytes("gb2312"), "iso8859-1"));
		}else{
			response.setHeader("Content-disposition","attachment;filename="+ new String(fileName.getBytes("gb2312"), "iso8859-1"));
		}
		InputStream in = res.getInputStream();
		byte[] b = new byte[4 * 1024];//缓冲区调小
		int i = 0;
		while ((i = in.read(b)) != -1) {
			os.write(b, 0, i);
		}
		os.flush();
		response.flushBuffer();	
		os.close();
		in.close();
	}

}
