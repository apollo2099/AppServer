package com.taozhu.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext ctx;

	public static Object getBean(String beanName) throws BeansException {
		return SpringContextUtil.ctx.getBean(beanName);
	}

	public static boolean containsBean(String beanName) {
		return SpringContextUtil.ctx.containsBean(beanName);
	}

	public static ApplicationContext getApplicationContext() {
		return SpringContextUtil.ctx;
	}

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param beanFactory
	 * @throws BeansException
	 */
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		SpringContextUtil.ctx = ctx;
	}
}
