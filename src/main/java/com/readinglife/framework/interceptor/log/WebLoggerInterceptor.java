 package com.readinglife.framework.interceptor.log;

import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.readinglife.framework.exceptions.SystemException;

/**
 * <b>类名称：</b>LoggerInterceptor<br/>
 * <b>类描述：</b>日志打印拦截器<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月26日 上午11:13:46<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */
 public class WebLoggerInterceptor implements HandlerInterceptor  {
	protected Log logger = LogFactory.getLog(getClass());
	 
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("Pre-handle");
		
		if (logger.isDebugEnabled()) {
			try {
				/////////////////打印URL  start////////////////////////
				String paramString = "?";
				Map<?,?> paraMap = request.getParameterMap();
				Set<?> set = paraMap.keySet();
				for (Object object : set) {
					paramString+="&"+object.toString()+"="+request.getParameter(object.toString());
				}
				logger.debug("_____URL:"+request.getRequestURL()+paramString); 
				/////////////////打印URL  end////////////////////////
				
				if(handler instanceof HandlerMethod){
					HandlerMethod handlerMethod = (HandlerMethod) handler;
				    logger.debug(">>>>>"+handlerMethod.toString()); 
				}
			} catch (Exception e) {
				throw new SystemException("打印debug信息异常",e);
			}
		}
		
		return true;
	}
	
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("Post-handle");
	}
	
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("After completion handle");
		
		if (logger.isDebugEnabled()) {
			try {
				if(handler instanceof HandlerMethod){
					HandlerMethod handlerMethod = (HandlerMethod) handler;
				    logger.debug("<<<<<"+handlerMethod.toString()); 
				}
			} catch (Exception e) {
				throw new SystemException("打印debug信息异常",e);
			}
		}
		
	}
}
