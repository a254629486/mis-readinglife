package com.readinglife.framework.exceptions;

import com.readinglife.framework.exceptions.supper.BaseException;

/**
 * <b>类名称：</b>BaseException<br/>
 * <b>类描述：</b>异常消息，普通消息信息或在验证信息。<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月18日 上午11:40:43<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@SuppressWarnings("serial")
public class MessageException extends BaseException{
	
//	protected String messageType;
	
	public MessageException(String msg){
		super(msg);
		logger.debug(msg);
	}
	public MessageException(String msg,Throwable e){
		super(msg,e);
		logger.debug(msg,e);
	}
	public MessageException(Throwable e){
		super(e);
	}
	
//	public MessageException(String msg,String msgType){
//		super(msg);
//		this.messageType = msgType;
//		logger.debug(msg);
//	}
//	
//	public MessageException(String msg,Throwable e,String msgType){
//		super(msg,e);
//		this.messageType = msgType;
//		logger.debug(msg,e);
//	}
//	
//	public String getMessageType() {
//		return messageType;
//	}
//	public void setMessageType(String messageType) {
//		this.messageType = messageType;
//	}
}