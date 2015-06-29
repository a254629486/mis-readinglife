 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service<br/>
 * <b>文件名：</b>SynchWorkeWorkflowService.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年12月3日-下午4:32:12<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

 /**
 * <b>类名称：</b>SynchWorkeWorkflowService<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年12月3日 下午4:32:12<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
public interface SynchWorkflowService {
	public String getByUri(String uri) throws ClientProtocolException, IOException;
	public String postByUri(String uri,String postParam) throws UnsupportedEncodingException, ClientProtocolException, IOException;
	public String putByUri(String uri,String putParam) throws ClientProtocolException, IOException;
	public String delByUri(String uri) throws ClientProtocolException, IOException;
}
