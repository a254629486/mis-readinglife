 /**
 * <b>项目名：</b>readinglife-common<br/>
 * <b>包名：</b>com.readinglife.framework.web.bean<br/>
 * <b>文件名：</b>ReturnJson.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年9月22日-下午1:41:13<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.readinglife.framework.web.bean;

import org.springframework.web.supper.Result;


 /**
 * <b>类名称：</b>ReturnJson<br/>
 * <b>类描述：</b>消息提示对象<br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年9月22日 下午1:41:13<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public class ReturnJson extends Result{
	
	private static final long serialVersionUID = -6010017121882253878L;
	
	private String success;
	private String msg;
	
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public void setMessage(String message) {
		this.msg = message;
	}
	@Override
	public void setState(String state) {
		this.success = state;
	}

}
