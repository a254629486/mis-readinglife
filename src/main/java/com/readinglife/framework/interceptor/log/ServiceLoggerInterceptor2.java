/**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.framework.interceptor.log<br/>
 * <b>文件名：</b>ServiceLoggerInterceptor.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月9日-上午10:44:49<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.readinglife.framework.interceptor.log;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

/**
 * <b>类名称：</b>ServiceLoggerInterceptor<br/>
 * <b>类描述：</b>Service日志打印<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月9日 上午10:44:49<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
public class ServiceLoggerInterceptor2 implements MethodBeforeAdvice,
		AfterReturningAdvice, ThrowsAdvice {
	protected Log logger = LogFactory.getLog(getClass());

	public void afterReturning(Object result, Method method, Object[] args,
			Object target) throws Throwable {
		if (logger.isDebugEnabled()) {
			StringBuffer arguments = new StringBuffer();
			for (Object object : args) {
				arguments.append(object.toString()).append(".");
			}
			logger.debug("After services method:" + method.getName() + ", Class:" + target.getClass()+", Arguments:" + arguments.toString());
		}
	}

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		if (logger.isDebugEnabled()) {
			StringBuffer arguments = new StringBuffer();
			for (Object object : args) {
				arguments.append(object.toString()).append(".");
			}
			logger.debug("Before services  method:" + method.getName() + ", Class:" + target.getClass()+", Arguments:" + arguments.toString());
		}
	}

	public void afterThrowing(Method method, Object[] args, Object target,
			Exception ex) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.error(target.getClass() + "." + method.getName()
					+ " has an error:" + ex.getClass().getName() + ","
					+ ex.getMessage());
		}
	}
	
}