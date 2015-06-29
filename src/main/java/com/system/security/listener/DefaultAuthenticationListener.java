package com.system.security.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.WebSubject;

public class DefaultAuthenticationListener implements AuthenticationListener {

    private static final Log log = LogFactory.getLog(DefaultAuthenticationListener.class);

	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
	    WebSubject webSubject = (WebSubject) SecurityUtils.getSubject();
//        webSubject.getSession().setAttribute(AuthenticationException.class.getName(), ae);
        log.info("User " + token.getPrincipal() + " login failure!");
	}

	public void onLogout(PrincipalCollection principals) {
		log.info("User " + principals.getPrimaryPrincipal() + " logout success!");
	}

	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		 log.info("User " + token.getPrincipal() + " login success!");
	}
   
}
