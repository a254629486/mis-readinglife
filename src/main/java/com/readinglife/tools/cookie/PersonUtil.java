 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.tools.cookie<br/>
 * <b>文件名：</b>PersonUtil.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月31日-下午3:51:58<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.tools.cookie;

import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.util.DigestUtils;

 /**
 * <b>类名称：</b>PersonUtil<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月31日 下午3:51:58<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class PersonUtil {
	
	
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
	public static Map<String,Object> getPerson(HttpServletRequest request,MemcachedClient memcachedClient) throws Exception {
		Cookie cookie =	CookieUtil.getCookieByName(request, DigestUtils.md5DigestAsHex("www.readinglife.com".getBytes()));
		return memcachedClient.get(cookie.getValue());
	}
}
