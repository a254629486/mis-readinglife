 package com.readinglife.framework.interceptor.log;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//这个注解用来说明这个类是切面
@Aspect
@Component
public class MyInterceptor {

	//定义切入点用来定义拦截的方法
	@Pointcut("execution(* com.readinglife.*.*.service(..))")
	private void anyMethod(){}//采用方法声明一个切入点	

	@Before("anyMethod()")//定义前置通知
	public void doAccessCheck() {
		System.out.println("前置通知");
	}	
	
	@After("anyMethod()")
	public void doAfter()//定义后置通知
	{
		System.out.println("最终通知");
	}

	@AfterReturning(pointcut="anyMethod()")
	public void doAfterReturning()//定义后置通知
	{
		System.out.println("后置通知");
	}
	//注意:如果出现异常则前置通知和最终通知执行，后置通知则不执行
	
	@AfterThrowing(pointcut="anyMethod()", throwing="ex")//定义异常通知
    public void doExceptionAction(Exception ex) {
	System.out.println("异常通知");
	}

	@Around("anyMethod()")
	//定义环绕通知，方法中的参数是固定的
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable
	{
		System.out.println("环绕通知之前");
		Object result=pjp.proceed();
		System.out.println("环绕通知之后");
		return result;
	}
	
	
}

 