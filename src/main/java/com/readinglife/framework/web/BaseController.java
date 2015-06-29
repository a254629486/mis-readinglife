package com.readinglife.framework.web;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.DigestUtils;

import com.readinglife.tools.cookie.CookieUtil;
import com.readinglife.tools.reflect.BeanMapUtil;
import com.readinglife.tools.string.StringUtil;
/**
 * <b>类名称：</b>BaseController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年8月23日 上午9:05:57<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class BaseController  {
    protected final static String SAVE_SUCCESS = "保存成功";
    protected final static String DELETE_SUCCESS = "删除成功";
    protected final static String DELETE_ERROR = "删除失败";
    
	protected Log logger = LogFactory.getLog(getClass());
	
	/**
	 * @Title getJsonPager
	 * @Description TODO 获取JsonPage实例   
	 * @param map page 当前页   rows 行数
	 * @return new JsonPage()
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年8月26日-上午9:31:51
	 * @update 
	 */
	@SuppressWarnings("rawtypes")
	public static JsonPager getJsonPager(Map map) {
		Integer page=1, size=10,total=0;
	 
		if(StringUtil.isNotNull(map.get("page"))){
			page = Integer.parseInt(map.get("page").toString());
		}
		if(StringUtil.isNotNull(map.get("rows"))){
			size = Integer.parseInt(map.get("rows").toString());
		}
		if(StringUtil.isNotNull(map.get("total"))){
			total = Integer.parseInt(map.get("total").toString());
		}
		
		return new JsonPager(page,size,total);
	}
	
	/**
	 * 将请求参数封装成Map对象
	 * @Title formatParam
	 * @Description TODO
	 * @param request
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年9月12日-下午1:05:09
	 * @update 
	 *
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,Object> formatParam(HttpServletRequest request){
		Map<String,Object> param = new HashMap<String, Object>();
		Enumeration e = request.getParameterNames();
		 while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if(null!=request.getParameter(name)&&!"".equals(request.getParameter(name).trim())){
				try {
					param.put(name, java.net.URLDecoder.decode(request.getParameter(name).toString(), "UTF-8").trim());
				} catch (UnsupportedEncodingException e1) {
					logger.error("UnsupportedEncodingException for search param :"+name+" 转码异常！");
				}
			} 
		}
		return param;
	}
	
	/**
	 * 将请求参数封装成Map对象与Bean的属性对应
	 * @Title formatParam
	 * @Description TODO
	 * @param request
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年9月12日-下午1:05:09
	 * @update 
	 *
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,Object> formatParamByBeanProperty(HttpServletRequest request,Class ...type){
		Map<String,Object> param = new HashMap<String, Object>();
		Enumeration e = request.getParameterNames();
		 while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			if(null!=request.getParameter(name)&&!"".equals(request.getParameter(name).trim())){
				try {
					param.put(name, java.net.URLDecoder.decode(request.getParameter(name).toString(), "UTF-8").trim());
				} catch (UnsupportedEncodingException e1) {
					logger.error("UnsupportedEncodingException for search param :"+name+" 转码异常！");
				}
			} 
		}
		for (Class clazz : type) {
			try {
				BeanMapUtil.forBeanProperty(clazz, param);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return param;
	}
	
	
	/**
	 *  根据Bean属性获取Map-value并赋值 
	 * @Title mapToBean
	 * @Description TODO
	 * @param javabean
	 * @param paraMap
	 * @return bean对象
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年9月12日-下午1:05:54
	 * @update 
	 *
	 */
	@SuppressWarnings("rawtypes")
	protected Object mapToBean(Class javabean, Map<String, Object> paraMap){
		try {
			return BeanMapUtil.convertMap2Bean(javabean, paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void returnParam(HttpServletRequest request,Map<String,Object> map){
		Iterator ite = map.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry<String, Object> entry = (Entry<String, Object>) ite.next();
			String key = entry.getKey();//map中的key
			Object value = entry.getValue();//上面key对应的value
			request.setAttribute(key, value);
		}
	}
	
	/**
	 * 返回json数据  <br>格式： {success：true,"msg":"操作成功"}
	 * @Title result
	 * @Description TODO
	 * @param success 提示状态
	 * @param msg  提示语
	 * @return json string 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年9月12日-下午1:07:26
	 * @update 
	 *
	 */
	protected String result(boolean success,String msg){
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("{\"success\":").append(success).append(",\"msg\":'").append(msg).append("'}");
		return sBuffer.toString();
	}
	
	
	/**
	 * @Title 获取用户信息
	 * @Description TODO
	 * @param request
	 * @param memcachedClient
	 * @return Map key :{staffId,personPO}
	 * @throws Exception 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年10月31日-下午3:56:15
	 * @update 
	 *
	 */
	protected Map<String,Object> getPerson(HttpServletRequest request,MemcachedClient memcachedClient) throws Exception {
		Cookie cookie =	CookieUtil.getCookieByName(request, DigestUtils.md5DigestAsHex(COOKIE_NAME.getBytes()));
		System.out.println("cookie.getValue()------:"+cookie.getValue());
		return memcachedClient.get(cookie.getValue());
	}
	
    /**
     * cookie 用户安全信息策略
     * @Title cookieStrategy
     * @Description TODO 
     * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
     * @date 2013年12月13日-上午10:39:02
     * @update 
     *
     */
	protected void cookieStrategy(HttpServletResponse response,String JSESSIONID) {
    	//key=随机数+用户登录名+登录时间
    	CookieUtil.addCookie(response, DigestUtils.md5DigestAsHex(COOKIE_NAME.getBytes()), JSESSIONID,
    			COOKIE_PASS);// 浏览器关闭cookie过期
	}
    
    /**
     * cookie 注销用户
     * @Title cancelCookieStrategy
     * @Description TODO 
     * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
     * @date 2013年12月13日-上午10:39:02
     * @update 
     *
     */
	protected void cancelCookieStrategy(HttpServletResponse response) {
    	CookieUtil.addCookie(response, DigestUtils.md5DigestAsHex(COOKIE_NAME.getBytes()),null,0);
	}
    
	 /**cookie名称*/
	protected final String COOKIE_NAME = "www.readinglife.com";//单位s
	 /**cookie过期时间*/
	protected final Integer COOKIE_PASS = 60*30;//单位s
}
