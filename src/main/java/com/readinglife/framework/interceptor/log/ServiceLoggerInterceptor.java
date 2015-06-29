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
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

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

@Aspect 
@Component
public class ServiceLoggerInterceptor{
	protected Log logger = LogFactory.getLog(getClass());
	
	@Pointcut("execution(* com.readinglife.*.service.*.*(..))") 
	public void loggerPointcut() {
	}
	
	@AfterReturning(pointcut="loggerPointcut()") 
	public void afterReturning(JoinPoint joinPoint,Object val) throws Throwable {
		if (logger.isDebugEnabled()) {
			String classType = joinPoint.getSignature().getDeclaringTypeName();
	    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			StringBuffer arguments = new StringBuffer();
			Object[] args = joinPoint.getArgs();
			for (Object object : args) {
				arguments.append(object.toString()).append(".");
			}
			logger.debug("After services method:" + method.getName() + ", Class:" + classType+", Arguments:" + arguments.toString());
		}
	}

	@Before("execution(* com.readinglife.*.service.*.*(..))") 
	public void before(JoinPoint joinPoint)
			throws Throwable {
		if (logger.isDebugEnabled()) {
			String classType = joinPoint.getSignature().getDeclaringTypeName();
	    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			StringBuffer arguments = new StringBuffer();
			Object[] args = joinPoint.getArgs();
			for (Object object : args) {
				arguments.append(object.toString()).append(".");
			}
			logger.debug("Before services  method:" + method.getName() + ", Class:" + classType+", Arguments:" + arguments.toString());
		}
	}

	
    @AfterThrowing(throwing="ex", pointcut="loggerPointcut()")  
	public void afterThrowing(JoinPoint joinPoint,Throwable ex) throws Throwable {
		if (logger.isDebugEnabled()) {
//			String classType = joinPoint.getSignature().getDeclaringTypeName();
//	    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//			Method method = signature.getMethod();
//			StringBuffer arguments = new StringBuffer();
//			Object[] args = joinPoint.getArgs();
//			for (Object object : args) {
//				arguments.append(object.toString()).append(".");
//			}
//			logger.debug("Before services  method:" + method.getName() + ", Class:" + classType+", Arguments:" + arguments.toString());
//			logger.error(classType + "." + method.getName()
//					+ " has an error:" + classType + ","
//					+ ex.getMessage());
		}
	}
	
}