/**
 * <b>项目名：</b>b2b-sys-web<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>AuthController.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月29日-下午2:20:48<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.cookie.CookieUtil;
import com.readinglife.tools.json.Jacksons;
import com.system.constants.UserToken;
import com.system.model.po.SysPrivilPO;
import com.system.model.po.SysPrivilPOExample;
import com.system.model.po.SysStaffPO;
import com.system.service.AuthService;
import com.system.service.SysPrivilService;
import com.system.service.SysStaffService;

/**
 * <b>类名称：</b>AuthController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月29日 下午2:20:48<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@Controller
public class AuthController extends BaseController{

	@Resource
	private ShiroFilterFactoryBean shiroFilterFactoryBean;
	@Resource
	private AuthService authService;
	@Resource
	private SysStaffService sysStaffService;
	@Resource
	private MemcachedClient memcachedClient;
	
	@Autowired
	SysPrivilService sysPrivilService;
	
	/**
	 * 登录跳转
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String toLogin(HttpServletRequest request){
		return "/framework/login";
	}
	
	/**
	 * @Title 登录
	 * @Description TODO
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年10月31日-上午11:10:31
	 * @update 
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(
			@RequestParam(value = "loginName", defaultValue = "") String username,
			@RequestParam(value = "loginPwd", defaultValue = "") String password,
			HttpServletRequest request,HttpServletResponse response) {

		Subject currentUser = SecurityUtils.getSubject();

		boolean loginSuccess = false;
		// 用户已经登录过了，直接进主页面
		if (currentUser.isAuthenticated()) {
			loginSuccess = true;
		}
		UserToken token = null;
		try {
			token = new UserToken(username, password);
			currentUser.login(token);
			loginSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_info", "用户名或密码错，请核对！");
			return "/framework/login";
		}

		if (loginSuccess) {
			//登录成功
			cookieStrategy(response, token.getJSESSIONID());
			
			return "redirect:main";
		}
		return "/framework/login";
	}
	
	/**
	 * @Title 登录
	 * @Description TODO
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年10月31日-上午11:10:31
	 * @update 
	 */
	@RequestMapping(value="/loginOS",method=RequestMethod.POST)
	@ResponseBody
	public String loginOS( HttpServletRequest request,HttpServletResponse response) {
		 String username = request.getParameter("loginName");
		 String password = request.getParameter("loginPwd");
		Subject currentUser = SecurityUtils.getSubject();
		
		boolean loginSuccess = false;
		// 用户已经登录过了，直接进主页面
		if (currentUser.isAuthenticated()) {
			loginSuccess = true;
		}
		UserToken token = null;
		try {
			token = new UserToken(username, password);
			currentUser.login(token);
			loginSuccess = true;
		} catch (Exception e) {
			return "error";
		}
		
		if (loginSuccess) {
			return token.getJSESSIONID();
		}
		return "error"; 
	}

	/**
	 * @Title 退出
	 * @Description TODO
	 * @param request
	 * @param response
	 * @return 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年10月31日-上午11:11:09
	 * @update 
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		cancelCookieStrategy( response);
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:login";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/main")
	public String index(HttpServletRequest request)  {
		try {
			Map map = getPerson(request, memcachedClient);
			Map personPO = (Map) map.get("personPO");
			request.setAttribute("personId", personPO.get("staffId"));
			request.setAttribute("name", personPO.get("name"));
			Map positiPO = (Map) map.get("positiPO");
			request.setAttribute("positiId",positiPO.get("positiId"));
			request.setAttribute("positiName",positiPO.get("name"));
			
			//菜单
//			Map<String,Object> menuMap = PersonUtil.getPerson(request, memcachedClient);
			
			Cookie cookie =	CookieUtil.getCookieByName(request, DigestUtils.md5DigestAsHex(request.getServerName().getBytes()));
			System.out.println(">>>>>>>>>>>>>="+request.getServerName());
			System.out.println(">>>>>>>>>>>>>="+DigestUtils.md5DigestAsHex(request.getServerName().getBytes()));
//			List<SysPrivilPO> list =  (List<SysPrivilPO>) memcachedClient.get(cookie.getValue()+"_privilLst");
			SysPrivilPOExample example = new SysPrivilPOExample();
			com.system.model.po.SysPrivilPOExample.Criteria c = example.createCriteria();
			List<SysPrivilPO> list = sysPrivilService.selectByExample(example);
			List menuOneList = new ArrayList();
			Map menu = null;
			List<SysPrivilPO> menuTwoList = null;
			for (SysPrivilPO sysPrivilPO : list) {
				if(sysPrivilPO.getType().equals("2")){//一级菜单
					menu = new HashMap();
					menu.put("menuOne", sysPrivilPO);
					menuTwoList = new ArrayList<SysPrivilPO>();
					for (SysPrivilPO sysPrivilPO2 : list) {
						if(sysPrivilPO.getPrivilId().equals(sysPrivilPO2.getPrivilPid())){
							menuTwoList.add(sysPrivilPO2);
						}
					}
					menu.put("menuTwo", menuTwoList);//二级菜单
					menuOneList.add(menu);
				}
			}
			request.setAttribute("menuList",menuOneList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/framework/index";
	}
	
	/**
	 * 更换岗位角色
	 * @Title changePositi
	 * @Description TODO
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:Q12_35@163.com">zhaosy</a>
	 * @date 2013年12月13日-上午11:49:57
	 * @update 
	 *
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/changePositi")
	public String changePositi(HttpServletRequest request,Model model,HttpServletResponse response)  throws Exception {
		boolean loginSuccess = false;
		Subject currentUser = SecurityUtils.getSubject();
		Map map = getPerson(request, memcachedClient);
		Map personPO = (Map) map.get("personPO");
		UserToken token = null;
		try {
			token = new UserToken(personPO.get("loginName").toString(), personPO.get("loginPwd").toString(),request.getParameter("positiId"));
			currentUser.login(token);
			loginSuccess = true;
		} catch (Exception e) {
			return "/framework/login";
		}

		if (loginSuccess) {
			//登录成功
			cookieStrategy(response, token.getJSESSIONID());
			return "redirect:main";
		}
		return "/framework/login";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/leftmenu")
	@ResponseBody
	public String leftMenu(HttpServletRequest request){
		try {
			Cookie cookie =	CookieUtil.getCookieByName(request, DigestUtils.md5DigestAsHex(request.getServerName().getBytes()));
			List<SysPrivilPO> list =  (List<SysPrivilPO>) memcachedClient.get(cookie.getValue()+"_privilLst");
			//传递给页面 给用户  该岗位的所有权限 list
			JsonPager jPager = new JsonPager(Integer.MAX_VALUE);
			jPager.setRows(list);
			return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changePwd")
	@ResponseBody
	public String changePwd(HttpServletRequest request) {
		String oldPwd = request.getParameter("loginOldPwd");
		String loginPwd = request.getParameter("loginPwd");
		SysStaffPO po = (SysStaffPO) sysStaffService.selectByPrimaryKey(request.getParameter("personId"));
		if(oldPwd.equals(po.getLoginPwd())){
			po.setLoginPwd(loginPwd);
			sysStaffService.save(po);
		}else {
			return result(false, "原密码错误");
		}
		return result(true, "操作成功");
	}
	
	@RequestMapping(value = "/unauth")
	public String unauth() {
		return "/framework/unauth";
	}
	
	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request) {
	   return "/framework/welcome";
	}
}
