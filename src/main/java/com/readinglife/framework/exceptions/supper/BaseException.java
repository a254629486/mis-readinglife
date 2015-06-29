package com.readinglife.framework.exceptions.supper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <b>类名称：</b>BaseException<br/>
 * <b>类描述：</b>基础异常<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月18日 上午11:40:43<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("serial")
public abstract class BaseException extends Exception{
	protected Log logger = LogFactory.getLog(getClass());
	
	public BaseException(String msg){
		super(msg);
	}
	public BaseException(String msg,Throwable e){
		super(msg,e);
	}
	public BaseException(Throwable e){
		super(e);
	}
}
