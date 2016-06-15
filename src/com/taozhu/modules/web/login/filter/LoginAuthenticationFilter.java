package com.taozhu.modules.web.login.filter;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.taozhu.modules.web.login.controller.LoginController;


/**
 * 登录认证Filter
 */
public class LoginAuthenticationFilter implements Filter {
	protected List<String> skipUrlList = new ArrayList<String>();
	public final static String  LOGIN_PAGE_PATH = "login.do?login";
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		httpResponse.addHeader("P3P","CP=CAO PSA OUR");
		HttpSession httpSession = httpRequest.getSession();
		String path = httpRequest.getRequestURI();
		boolean ignore = false;
		// 在Session中查找用户是否登录
		if(path.indexOf("login.do")!=-1){
			ignore = true;
		}
		for(String url:skipUrlList){
			if(path.indexOf(url)!=-1){
				ignore = true;
				break;
			}
		}
		if(ignore){
			chain.doFilter(request, response);
			return;
		}
		if (session.getAttribute("loginInfo") != null) {
			chain.doFilter(httpRequest, httpResponse);
			return;//执行doFilter方法后，必须执行 return语句，否则会产生多次请求。
		}
		String basePath = httpRequest.getScheme()+"://"+httpRequest.getServerName()+":"+httpRequest.getServerPort()+httpRequest.getContextPath()+"/";
		String requestPath = httpRequest.getRequestURL().toString();
		if(requestPath.indexOf(".do")>0){
			if(!(requestPath+"?login").equals(basePath+LOGIN_PAGE_PATH)
					&&!(requestPath+"?verify").equals(basePath+LOGIN_PAGE_PATH)){
				httpResponse.sendRedirect(basePath+LOGIN_PAGE_PATH);
			}
		}else if(httpRequest.getRequestURL().indexOf(".jsp")>0
				||httpRequest.getRequestURL().indexOf(".hmtl")>0){
			httpResponse.sendRedirect(basePath+LOGIN_PAGE_PATH);
		}
		chain.doFilter(httpRequest, httpResponse);
		return;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Enumeration enums = filterConfig.getInitParameterNames();
		if (enums == null)
			return;
		String argName = null;
		while (enums.hasMoreElements()) {
			argName = enums.nextElement().toString();
			if ("skip-url".equals(argName)) {
				skipUrlList = this.getSkipUrlList(filterConfig.getInitParameter(argName));
			}
		}
	}
	private List<String> getSkipUrlList(String xmlPath) {
		List<String> list = new ArrayList<String>();
		Document doc = null;
		SAXReader reader = new SAXReader();
	    try {
	    	File f = new File(this.getClass().getResource(xmlPath).toURI());
			doc = reader.read(f);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if(doc != null) {
			List<Element> eles = doc.selectNodes("//url");
			for(Element ele:eles){
				if(!"".equals(ele.getTextTrim()))
					list.add(ele.getTextTrim());
			}
		}
		return list;
	}
}
