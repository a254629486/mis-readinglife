/**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.framework.dao<br/>
 * <b>文件名：</b>MyBatisQueryInterceptor.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年8月26日-下午4:54:28<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.readinglife.framework.dao.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.reflect.ReflectUtil;

/**
 * <b>类名称：</b>MyBatisQueryInterceptor<br/>
 * <b>类描述：</b>分页查询拦截器<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年8月26日 下午4:54:28<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class MyBatisQueryInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation
				.getTarget();

		StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
		BoundSql boundSql = delegate.getBoundSql();
		Object obj = boundSql.getParameterObject();
			 if (obj instanceof RowBounds) {  
				   RowBounds rowBounds = (RowBounds) obj;  
		           String sql = boundSql.getSql();  
		           StringBuffer pageSql = new StringBuffer(sql);
		           pageSql.append(" limit ").append(rowBounds.getOffset()).append(",").append(rowBounds.getLimit());  
		           ReflectUtil.setFieldValue(boundSql, "sql", pageSql.toString());  
		       }  
	       if (obj instanceof JsonPager<?>) {  
	    	   JsonPager<?> page = (JsonPager<?>) obj;  
	           String sql = boundSql.getSql();  
	           StringBuffer pageSql = new StringBuffer(sql);
	           int offset = (page.getPage() - 1) * page.getSize();  
	           pageSql.append(" limit ").append(offset).append(",").append(page.getSize());  
	           ReflectUtil.setFieldValue(boundSql, "sql", pageSql.toString());  
	       }  
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
