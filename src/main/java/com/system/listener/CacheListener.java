package com.system.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.readinglife.common.spring.SpringBeanUtil;
import com.system.service.InitService;

public class CacheListener extends HttpServlet implements ServletContextListener {
	
	private static final long serialVersionUID = 6103709287229434277L;
	private static final Logger logger = Logger.getLogger(CacheListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		logger.debug("服务关闭！");	
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.debug("服务启动！");
		InitService initService;
		{
			initService=(InitService) SpringBeanUtil.getBean("initService");
			//初始化权限信息
			initService.initPrivil();
			//初始化码表信息
			initService.initBaseCode();
		}
		 
	}

	
	
}
