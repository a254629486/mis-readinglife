package com.readinglife.framework.exceptions;

import com.readinglife.framework.exceptions.supper.BaseException;

/**
 * <b>类名称：</b>BaseException<br/>
 * <b>类描述：</b>系统异常,需要查明异常原因！<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月18日 上午11:40:43<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("serial")
public class SystemException extends BaseException{
 
	public SystemException(String msg){
		super(msg);
		logger.error(msg);
	}
	public SystemException(String msg,Throwable e){
		super(msg,e);
		logger.error(msg);
	}
	public SystemException(Throwable e){
		super(e);
	}
}