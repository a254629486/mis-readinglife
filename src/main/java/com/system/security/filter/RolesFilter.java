/**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.security.filter<br/>
 * <b>文件名：</b>RolesFilter.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月29日-上午11:39:34<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.security.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * <b>类名称：</b>RolesFilter<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月29日 上午11:39:34<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
public class RolesFilter extends AuthorizationFilter {
	
	/**
	 * isAccessAllowed(只要有一个满足即通过)<br/>
	 * @param request
	 * @param response
	 * @param mappedValue
	 * @return
	 * @throws IOException  
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
				return true;
		}
		return false;
	}
	 
	
}
