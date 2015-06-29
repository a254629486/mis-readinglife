 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>AuthServiceImpl.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年10月29日-上午11:17:04<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.readinglife.framework.service.BaseService;
import com.readinglife.tools.properties.OrderedProperties;
import com.system.service.AuthService;

 /**
 * <b>类名称：</b>AuthServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年10月29日 上午11:17:04<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Service("authService")
public class AuthServiceImpl extends BaseService implements AuthService {

    private static final Logger log= Logger.getLogger(AuthServiceImpl.class);

    

    //注意/r/n前不能有空格
    private static final String CRLF= "\r\n";
    private static final String LAST_AUTH_STR= "/** =authc"+CRLF;

    

    @Resource
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
	@Resource
	private MemcachedClient memcachedClient;


	public String loadFilterChainDefinitions() {

		StringBuffer sb = new StringBuffer();
		sb.append(getFixedAuthRule()).append(getDynaAuthRule())
				.append(getRestfulOperationAuthRule()).append(LAST_AUTH_STR);
		return sb.toString();

	}

    

    //生成restful风格功能权限规则
    private String getRestfulOperationAuthRule() {
  /*     List<Operation> operations = dao.queryEntitys("from Operation o", new Object[]{});
       Set<String> restfulUrls = new HashSet<String>();

       for(Operation op : operations) {
           restfulUrls.add(op.getUrl());
       }
       StringBuffer sb  = newStringBuffer("");
       for(Iterator<String> urls =  restfulUrls.iterator(); urls.hasNext(); ) {
           String url = urls.next();
           if(! url.startsWith("/")) {
              url = "/"+ url ;
           }
           sb.append(url).append("=").append("authc, rest[").append(url).append("]").append(CRLF);
       }*/

       return "".toString();
    }

    //根据角色，得到动态权限规则
	private String getDynaAuthRule() {
     /*  StringBuffer sb = new StringBuffer("");
       Map<String, Set<String>> rules = new HashMap<String,Set<String>>();
       List<Role> roles = dao.queryEntitys("from Role r left join fetch r.menus", new Object[]{});
       for(Role role: roles) {
           for(Iterator<Menu> menus =role.getMenus().iterator(); menus.hasNext();) {
              String url = menus.next().getUrl();
              if(!url.startsWith("/")) {
                  url = "/"+ url;
              }
              if(!rules.containsKey(url)) {
                  rules.put(url, new HashSet<String>());
              }
              rules.get(url).add((role.getRoleCode()));
           }
       }

       for(Map.Entry<String, Set<String>> entry :rules.entrySet()) {
           sb.append(entry.getKey()).append(" = ").append("authc,roleOrFilter").append(entry.getValue()).append(CRLF);
       }*/
       return "".toString();
    }

    //得到固定权限验证规则串
    private String getFixedAuthRule() {
       StringBuffer sb = new StringBuffer();
       ClassPathResource cp = new ClassPathResource("fixed_auth_res.properties");
       Properties properties = new OrderedProperties();
       try{
           properties.load(cp.getInputStream());
       } catch(IOException e) {
           log.error("loadfixed_auth_res.properties error!", e);
           throw new RuntimeException("load fixed_auth_res.properties error!");
       }
       for(Iterator its = properties.keySet().iterator();its.hasNext();) {
           String key = (String)its.next();
           sb.append(key).append(" = ").append(properties.getProperty(key).trim()).append(CRLF);
       }      
       
       try {
			memcachedClient.set("fixed_auth_res", 0, sb.toString());
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
       
       return sb.toString();
    }
    //此方法加同步锁
    public synchronized void reCreateFilterChains() {

       AbstractShiroFilter shiroFilter = null;
       try{
           shiroFilter = (AbstractShiroFilter)shiroFilterFactoryBean.getObject();
       } catch(Exception e) {
           log.error("getShiroFilter from shiroFilterFactoryBean error!", e);
           throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
       }
       PathMatchingFilterChainResolver filterChainResolver =(PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();
       DefaultFilterChainManager manager =(DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
       //清空老的权限控制
       manager.getFilterChains().clear();

       shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();

       shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());

       //重新构建生成

       Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();

        for(Map.Entry<String, String> entry :chains.entrySet()) {

            String url = entry.getKey();

            String chainDefinition =entry.getValue().trim().replace(" ", "");

            manager.createChain(url,chainDefinition);

        }

    }

 

}
