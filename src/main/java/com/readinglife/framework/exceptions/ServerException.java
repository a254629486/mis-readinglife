package com.readinglife.framework.exceptions;

import com.readinglife.framework.exceptions.supper.BaseException;

 
/**
 * <b>类名称：</b>ServerLocalException<br/>
 * <b>类描述：</b>此异常为服务器解析异常，需要查明异常原因！<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月18日 上午11:38:14<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("serial")
public class ServerException extends BaseException{
	
	/**
	 * 功能描述：msg为记录日志时快速查找使用
	 *
	 * @constructor 方法
	 */
	public ServerException(String msg,Throwable e){
		super(msg,e);
		logger.error(msg, e);
	}
	public ServerException(Throwable e){
		super(e);
		logger.error(e, e);
	}
}
