 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>DefaultAuthRealm.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月29日-上午11:32:55<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.util.DigestUtils;

import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.reflect.BeanMapUtil;
import com.readinglife.tools.string.StringUtil;
import com.system.constants.SysConstants;
import com.system.constants.UserToken;
import com.system.model.po.SysCompanPO;
import com.system.model.po.SysDepartPO;
import com.system.model.po.SysPositiPO;
import com.system.model.po.SysPrivilPO;
import com.system.model.po.SysStaffPO;
import com.system.model.po.SysStaffPOExample;
import com.system.service.AuthRealmService;
import com.system.service.SysCompanService;
import com.system.service.SysDepartService;
import com.system.service.SysPositiService;
import com.system.service.SysStaffPositiService;
import com.system.service.SysStaffService;
import com.system.util.CachePrivilUtil;

 /**
 * <b>类名称：</b>DefaultAuthRealm<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月29日 上午11:32:55<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class DefaultAuthRealm extends AuthorizingRealm {
	
    @Resource 
    private SysStaffService sysStaffService;
    @Resource
    private AuthRealmService authRealmService;
    @Resource 
    private SysDepartService sysDepartService;
    @Resource 
    private SysCompanService sysCompanService;
    @Resource 
    private SysPositiService sysPositiService;
	@Resource
	private MemcachedClient memcachedClient;
	@Resource
	private SysStaffPositiService sysStaffPositiService;
	
	
	private int timeout = 30*60;//单位 s
	
	
    public DefaultAuthRealm() {
       super();
       // 设置认证token的实现类为用户名密码模式
       this.setAuthenticationTokenClass(UserToken.class);
       //设置验证方式，用户自行设定密码加密方式
       this.setCredentialsMatcher(new CredentialsMatcher() {   
           @SuppressWarnings("unchecked")
		public boolean doCredentialsMatch(AuthenticationToken token,AuthenticationInfo info) {
        	   UserToken upToken = (UserToken)token;
              String pwd = new String(upToken.getPassword());
              SysStaffPOExample example = new SysStaffPOExample();
              com.system.model.po.SysStaffPOExample.Criteria c = example.createCriteria();
              c.andLoginNameEqualTo(upToken.getUsername());
              c.andStatusEqualTo(SysConstants.PERSON_STATUS_ON);
              List<SysStaffPO> list = sysStaffService.selectByExample(example);
              SysStaffPO sysStaffVO = null;
              if (list !=null&& list.size()>0) {
            	  sysStaffVO = list.get(0);
              }
              
              if(StringUtil.isNotBlank(pwd)&&StringUtil.isNotBlank(sysStaffVO.getLoginPwd())&&
            		  DigestUtils.md5DigestAsHex(sysStaffVO.getLoginPwd().getBytes()).equals(DigestUtils.md5DigestAsHex(pwd.getBytes()))){
                  //用户名及密码验证通过
            	  try {
			    	 	////用户信息  & 用户权限 
						if(sysStaffVO!=null){
	            		    Map personMap = new HashMap();
	            		    personMap.put("personPO", BeanMapUtil.convertBean2Map(sysStaffVO));
							personMap.put("staffId", sysStaffVO.getStaffId());
							
							Map param = new HashMap();
							param.put("staffId", sysStaffVO.getStaffId());
							if(upToken!=null&&StringUtil.isNotBlank(upToken.getStaffId())){
								param.put("positiId", upToken.getStaffId());
							}
							JsonPager jPager = new JsonPager(0);
								jPager.setSize(Integer.MAX_VALUE);
								jPager = sysStaffPositiService.selectByPageMap(jPager, param);
							if(jPager.getRows()==null){
								return false ;
							}
							String positiId = "";
							List<SysPositiPO> sysPositis = jPager.getRows();
							for (SysPositiPO sysPositiPO : sysPositis) {
								positiId = sysPositiPO.getPositiId();
								personMap.put("positiPO", BeanMapUtil.convertBean2Map(sysPositiPO));
								if(upToken!=null&&StringUtil.isNotBlank(upToken.getStaffId())&&upToken.getStaffId().equals(sysPositiPO.getPositiId())){
									break;
								} 
							}
							
							//部门信息  
							SysDepartPO sysDepartPO = (SysDepartPO) sysDepartService.selectByPrimaryKey(sysStaffVO.getDepartId());
							personMap.put("sysDepartPO", BeanMapUtil.convertBean2Map(sysDepartPO));
							SysCompanPO sysCompanPO = (SysCompanPO) sysCompanService.selectByPrimaryKey(sysDepartPO.getCompanId());
							personMap.put("sysCompanPO", BeanMapUtil.convertBean2Map(sysCompanPO));
							//end
							
							List<SysPrivilPO> sysPrivilPOs = authRealmService.getPrivilByStaffId(sysStaffVO.getStaffId(),positiId);
							
							upToken.initJSESSIONID();//cookie策略
							memcachedClient.set(upToken.getJSESSIONID()+"_privilLst", timeout, sysPrivilPOs);
							memcachedClient.set(upToken.getJSESSIONID(), timeout, personMap);
							memcachedClient.set(sysStaffVO.getStaffId(), timeout, CachePrivilUtil.getPersonPrivilMap(sysPrivilPOs));
							
							long t = 1000*timeout;
							SecurityUtils.getSubject().getSession().setTimeout(t);  
						}
						
					} catch (TimeoutException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (MemcachedException e) {
						e.printStackTrace();
					}catch (Exception e) {
						e.printStackTrace();
					}
            	  
                  return true;
              }
              //用户名或密码不正确
              return false;
           }
       });
    }

 

    /**
     * doGetAuthenticationInfo(认证)<br/>
     * @param token
     * @return
     * @throws AuthenticationException  
     * @exception 
     * @since  1.0.0
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
           AuthenticationToken token) throws AuthenticationException {

       UserToken upToken = (UserToken) token;

       if(upToken != null) {
           return new SimpleAuthenticationInfo(upToken.getUsername(),String.valueOf(upToken.getPassword()), this.getName());
       }
       //认证没有通过
       return null;
    }

    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	System.out.println(principalCollection);
    	
//	    	Long loginId = (Long)principalCollection.fromRealm(getName()).iterator().next();
		  //取当前用户
		//  User student = studentService.getStudentById(loginId);
		  //添加角色及权限信息
		  SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		//  sazi.addRoles(student.getRolesAsString());
		//  sazi.addStringPermissions(student.getPermissionsAsString());
		  return sazi;
    }
    
    protected void clearCachedAuthorizationInfo(PrincipalCollection pc) {
       SimplePrincipalCollection principals= new SimplePrincipalCollection(pc, getName()); 
       super.clearCachedAuthorizationInfo(pc);

    }

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
    
}
