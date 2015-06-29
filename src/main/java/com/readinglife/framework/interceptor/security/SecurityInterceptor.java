 package com.readinglife.framework.interceptor.security;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.readinglife.tools.cookie.CookieUtil;
import com.readinglife.tools.string.StringUtil;


/**
 * <b>类名称：</b>LoggerInterceptor<br/>
 * <b>类描述：</b>安全校验拦截器<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月26日 上午11:13:46<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 */
 public class SecurityInterceptor extends HandlerInterceptorAdapter   {
	protected Log logger = LogFactory.getLog(getClass());
	 
	private static final String CRLF= "\r\n";
	
	@Resource
	private MemcachedClient memcachedClient;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
	    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getRequestURI();
	    String basePathNoPort = request.getScheme()+"://"+request.getServerName()+ request.getRequestURI();
		
	    
		String topath = request.getRequestURI();
		if(StringUtil.isNotBlank(request.getContextPath())){
			topath = topath.substring(request.getContextPath().length(),topath.length());
		}
//		System.out.println(request.getRequestURL()+"========"+topath);
		
		String authres = memcachedClient.get("fixed_auth_res");
		String[] auth = authres.split(CRLF);
		for (String string : auth) {
			if(StringUtil.isNotBlank(string)&&string.indexOf("anon")!=-1){
				String[] path = string.split("=")[0].trim().split("/");
//				System.out.println(path[((path.length>2?2:path.length)-1)]);
				if(topath.indexOf("/"+path[((path.length>2?2:path.length)-1)])!=-1){
					return true;
				}
			}
		}
		
		Cookie cookie =	CookieUtil.getCookieByName(request, DigestUtils.md5DigestAsHex("www.readinglife.com".getBytes()));
		if(cookie==null){//未登录
//			response.sendRedirect("http://localhost:1010/b2b-sys-web/login");
			response.sendError(403);
			return false;
		}
		String usertokenValue = cookie.getValue();
		if (usertokenValue==null||"".equals(usertokenValue)) {//未登录
//			response.sendRedirect("http://localhost:1010/b2b-sys-web/login");
			response.sendError(403);
			return false;
		}
		Map<String,String> personMap = memcachedClient.get(usertokenValue);
		if(personMap==null){//无效用户
//			response.sendRedirect("http://localhost:1010/b2b-sys-web/login");
			response.sendError(403);
			return false;
		}
		Map<String,String> roles = memcachedClient.get(personMap.get("staffId").toString());
		if(roles==null){//无权限
//			response.sendRedirect("http://localhost:1010/b2b-sys-web/login");
			response.sendError(401);
			return false;
		}
		Map<String,String> sysprivil = memcachedClient.get("system_base_privil");//系统权限
		if(StringUtil.isNotNull(sysprivil.get(DigestUtils.md5DigestAsHex(topath.substring(1,topath.length()).getBytes())))
				||StringUtil.isNotNull(sysprivil.get(DigestUtils.md5DigestAsHex(basePath.getBytes())))
				||StringUtil.isNotNull(sysprivil.get(DigestUtils.md5DigestAsHex(basePathNoPort.getBytes())))){
			if(StringUtil.isNotNull(roles.get(DigestUtils.md5DigestAsHex(topath.substring(1,topath.length()).getBytes())))
					||StringUtil.isNotNull(roles.get(DigestUtils.md5DigestAsHex(basePath.getBytes())))
					||StringUtil.isNotNull(roles.get(DigestUtils.md5DigestAsHex(basePathNoPort.getBytes())))){
				
				//成功操作用户刷新session信息
				refreshCookie(usertokenValue);
				
				return true;
			}else {//权限不足
//				response.sendRedirect("http://localhost:1010/b2b-sys-web/login");
				response.sendError(405);
				return false;
			}
		}else {
			return true;
		}
		
	}
	
	private void refreshCookie(String usertokenValue) {
		try {
			Map<String,String> personMap = memcachedClient.get(usertokenValue);
			memcachedClient.replace(usertokenValue, 30*60*60, personMap);
			memcachedClient.replace(personMap.get("staffId").toString(), 30*60*60, memcachedClient.get(personMap.get("staffId").toString()));
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
	}
 
}
