 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.framework.tag<br/>
 * <b>文件名：</b>PrivilTag.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月31日-下午4:25:53<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.framework.tag;

import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.DigestUtils;

import com.readinglife.common.spring.SpringBeanUtil;
import com.readinglife.tools.cookie.CookieUtil;
import com.readinglife.tools.string.StringUtil;

 /**
 * <b>类名称：</b>PrivilTag<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月31日 下午4:25:53<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class PrivilTag  extends TagSupport {
	protected Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = -1370666346605043238L;

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			boolean info = generateContent();
			if(!info){
				return SKIP_BODY;
			}
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}  
		return EVAL_BODY_INCLUDE;
	}

	private boolean generateContent() throws TimeoutException, InterruptedException, MemcachedException {
		
		MemcachedClient memcachedClient = (MemcachedClient) SpringBeanUtil.getBean("memcachedClient");
		
		Cookie cookie =	CookieUtil.getCookieByName(WebUtils.toHttp(pageContext.getRequest()), DigestUtils.md5DigestAsHex("www.readinglife.com".getBytes()));
		if(cookie==null||pid==null){//未登录
			return false;
		}
		String usertoken = cookie.getValue();
		if (usertoken==null||"".equals(usertoken)) {//未登录
			return false;
		}
		Map<String,String> personMap = memcachedClient.get(usertoken);
		if(personMap==null){//无效用户
			return false;
		}
		Map<String,String> roles = memcachedClient.get(personMap.get("staffId").toString());
		if(roles==null){//无权限
			return false;
		}
		if(StringUtil.isNotNull(roles.get(pid.trim()))){
			return true ;
		}else {//权限不足
			return false;
		}
	}
	
	private String pid;
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
