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
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.readinglife.common.spring.SpringBeanUtil;
import com.readinglife.framework.po.BasePO;
import com.readinglife.tools.reflect.ReflectUtil;


/**
 * <b>类名称：</b>MyBatisQueryInterceptor<br/>
 * <b>类描述：</b>校验拦截器<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年8月26日 下午4:54:28<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class MyBatisValidatorInterceptor implements Interceptor {

	public Object intercept(Invocation invocation) throws Throwable { 
		Validator validator = (Validator) SpringBeanUtil.getBean("validator");
		
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
		BoundSql boundSql = delegate.getBoundSql();
		Object obj = boundSql.getParameterObject();
		if(obj instanceof BasePO){
			 Set<ConstraintViolation<Object>> violations = validator.validate(obj);     
			 for (ConstraintViolation<Object> constraintViolation : violations) {
				 throw new ConstraintViolationException(constraintViolation.getMessage(), violations);
			 }
		}
		
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
