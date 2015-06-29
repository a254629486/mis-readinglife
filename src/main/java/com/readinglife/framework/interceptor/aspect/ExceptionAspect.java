/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.readinglife.framework.interceptor.aspect;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * 异常处理拦截器
 * @author 黄鹏
 * @version 1.0
 */
@Aspect
public class ExceptionAspect implements ApplicationContextAware{
	private ApplicationContext mContext;
	
	/**
	 * 抛出异常拦截器方法.
	 * @param joinPoint  拦截点
	 * @param ex  异常
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
    public void afterThrowing(JoinPoint joinPoint,
            Exception ex) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
		System.out.println("抛出异常方法被拦截afterThrowing");
		 
    }
	
	/**
	 * 前置拦截方法.
	 * @param joinPoint  拦截点
	 */
	public void before(JoinPoint joinPoint){
		//增加每个service的返回值链条
//    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
////		Method method = signature.getMethod();
//		Method method = signature.getMethod();
		//method.
		//
		System.out.println("抛出异常方法被拦截before");
	}
	/**
	 * 方法调用返回拦截方法.
	 * @param joinPoint  拦截点
	 * @param val  返回值对象
	 * @throws Throwable
	 */
	public void afterReturing(JoinPoint joinPoint,Object val) throws Throwable{
		//判断是否需要排除业务异常.IBussinessExceptionRule
		System.out.println("抛出异常方法被拦截afterReturing");
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.mContext = applicationContext;
	}
}
