package com.atguigu.atcrowdfunding.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atguigu.atcrowdfunding.util.Const;
/**
 * 监听application对象创建和销毁
 * @author Administrator
 *
 */
public class SystemUpInitListener implements ServletContextListener {
	
	Logger log = LoggerFactory.getLogger(SystemUpInitListener.class);
	
	//当application对象创建时执行此方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		log.debug("上下文路径={}",contextPath);
		application.setAttribute(Const.PATH, contextPath);
	}
	
	//当application销毁时执行此方法
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.debug("当前应用对象application被销毁了");
	}

}
