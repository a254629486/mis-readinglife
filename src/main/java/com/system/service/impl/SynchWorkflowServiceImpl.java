 /**
 * <b>项目名：</b>b2b-sys-service<br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SynchWorkeWorkflowServiceImpl.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>日期：</b>2013年12月3日-下午4:33:09<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
 package com.system.service.impl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.system.service.SynchWorkflowService;


 /**
 * <b>类名称：</b>SynchWorkeWorkflowServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b><a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改时间：</b>2013年12月3日 下午4:33:09<br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Service("SynchWorkeWorkflowService")
public class SynchWorkflowServiceImpl implements SynchWorkflowService {
	protected Log logger = LogFactory.getLog(getClass());

	public String getByUri(String uri) throws ClientProtocolException, IOException {
		uri = uriToUrl(uri);
		logger.debug("WORKFLOW:"+uri);
		HttpGet get = new HttpGet(uri);
		HttpResponse httpResponse = new DefaultHttpClient().execute(get);
		HttpEntity entity = httpResponse.getEntity();
		return EntityUtils.toString(entity);
	}

	public String postByUri(String uri, String postParam) throws ClientProtocolException, IOException {
		uri = uriToUrl(uri);
		logger.debug("WORKFLOW:"+uri+",param"+postParam);
		HttpPost post = new HttpPost(uri);
		StringEntity sEntity = new StringEntity(postParam, "UTF-8");  
		sEntity.setContentType("application/json");
		post.setEntity(sEntity);
		HttpResponse httpResponse = new DefaultHttpClient().execute(post);
		HttpEntity entity = httpResponse.getEntity();
		return  EntityUtils.toString(entity);
	}

	public String putByUri(String uri, String putParam) throws ClientProtocolException, IOException {
		uri = uriToUrl(uri);
		logger.debug("WORKFLOW:"+uri+",param"+putParam);
		HttpPut post = new HttpPut(uri);
		StringEntity sEntity = new StringEntity(putParam, "UTF-8");   // 中文乱码在此解决
		sEntity.setContentType("application/json");
		post.setEntity(sEntity);
		HttpResponse httpResponse = new DefaultHttpClient().execute(post);
		HttpEntity entity = httpResponse.getEntity();
		return EntityUtils.toString(entity);
	}

	public String delByUri(String uri) throws ClientProtocolException, IOException {
		uri = uriToUrl(uri);
		logger.debug("WORKFLOW:"+uri);
		HttpDelete get = new HttpDelete(uri);
		HttpResponse httpResponse = new DefaultHttpClient().execute(get);
		HttpEntity entity = httpResponse.getEntity();
		if(entity!=null){
			return EntityUtils.toString(entity);
		}else {
			return null;
		}
	}

	private String uriToUrl(String uri){
		uri = uri.replace("http://", "");
		return "http://"+uri.replaceAll("//*", "/");
	}
	
}
