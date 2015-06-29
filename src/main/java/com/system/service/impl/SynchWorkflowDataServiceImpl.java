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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.readinglife.tools.string.StringUtil;
import com.system.constants.SysConstants;
import com.system.model.po.SysActivitiServerPO;
import com.system.model.po.SysActivitiServerPOExample;
import com.system.model.po.SysRolePO;
import com.system.service.SynchWorkflowDataService;
import com.system.service.SynchWorkflowService;
import com.system.service.SysActivitiServerService;

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
@Service("SynchWorkflowDataService")
public class SynchWorkflowDataServiceImpl implements SynchWorkflowDataService {

	private final int PUT = 1, POST = 2, DEL = 4;
	
	@Resource
	private SynchWorkflowService synchWorkflowService;
	
	@Resource
	private SysActivitiServerService sysActivitiServerService;
	
	public void postRole(SysRolePO sysRolePO) {
		JSONObject obj = new JSONObject();
		obj.put("id", sysRolePO.getRoleId());
		obj.put("name", sysRolePO.getName());
		obj.put("type", "assignment");
		doAllServer(obj.toString(), POST,"/groups");
	}

	public void putRole(SysRolePO sysRolePO) {
		JSONObject obj = new JSONObject();
		obj.put("id", sysRolePO.getRoleId());
		obj.put("name", sysRolePO.getName());
		obj.put("type", "assignment");
		doAllServer(obj.toString(), PUT,"/groups");
	}

	public void delRole(String pk) {
		doAllServer(pk, DEL,"/groups/");
	}

	public String getRole(String server,String pk) throws ClientProtocolException, IOException {
		return  synchWorkflowService.getByUri(server+"/identity/groups/"+pk);
	}
	
	
	@SuppressWarnings("unchecked")
	private String doAllServer(String json,int type,String path){
		SysActivitiServerPOExample example = new SysActivitiServerPOExample();
		com.system.model.po.SysActivitiServerPOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(SysConstants.WORKFLOW_STATUS_ON);
		List<SysActivitiServerPO> list = sysActivitiServerService.selectByExample(example);
		if(list!=null&&list.size()>0){
			for (SysActivitiServerPO sysActivitiServerPO : list) {
				try {
					if(type==PUT){
						JSONObject object = JSONObject.fromObject(json);
						String string = "";
						if(path.indexOf("groups")!=-1){
							string = getRole(sysActivitiServerPO.getServerIp(),object.get("id").toString());
						}else if(path.indexOf("users")!=-1){
							string = getStaff(sysActivitiServerPO.getServerIp(),object.get("id").toString());
						}
						
						JSONObject jsonObj = JSONObject.fromObject(string);
						if(jsonObj==null||(StringUtil.isNotNull(jsonObj.get("statusCode"))&&404==Integer.parseInt(jsonObj.get("statusCode").toString()))){
							type = POST;
						}else {
							path = path+"/"+object.getString("id");
							object.remove("id");
							json = object.toString();
						}
					}
					switch (type) {
						case PUT:
							synchWorkflowService.putByUri(sysActivitiServerPO.getServerIp()+"/identity/"+path, json);
							break;
						case POST:
							synchWorkflowService.postByUri(sysActivitiServerPO.getServerIp()+"/identity/"+path, json);
							break;
						case DEL:
							synchWorkflowService.delByUri(sysActivitiServerPO.getServerIp()+"/identity/"+path+json);
							break;
					}
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}

	public void postStaff(Map map) {
		JSONObject obj = new JSONObject();
		obj.put("id", map.get("id"));
		obj.put("firstName", map.get("firstName"));
		obj.put("lastName", map.get("lastName"));
		obj.put("email", map.get("email"));
		obj.put("password", map.get("password"));
		doAllServer(obj.toString(), POST,"/users");
	}

	public void putStaff(Map map) {
		JSONObject obj = new JSONObject();
		obj.put("id", map.get("id"));
		obj.put("firstName", map.get("firstName"));
		obj.put("lastName", map.get("lastName"));
		obj.put("email", map.get("email"));
		obj.put("password", map.get("password"));
		doAllServer(obj.toString(), PUT,"/users");
	}

	public void delStaff(String pk) {
		doAllServer(pk, DEL,"/users/");
	}

	public String getStaff(String server,String pk) throws ClientProtocolException, IOException {
		return synchWorkflowService.getByUri(server+"/identity/users/"+pk);
	}

	@SuppressWarnings("unchecked")
	public void postStaffCsmrol(Map map) {
		String roleId = (String) map.get("roleId");
		String[] roleIds = roleId.split(",");

		SysActivitiServerPOExample example = new SysActivitiServerPOExample();
		com.system.model.po.SysActivitiServerPOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(SysConstants.WORKFLOW_STATUS_ON);
		List<SysActivitiServerPO> list = sysActivitiServerService.selectByExample(example);
		if(list!=null&&list.size()>0){
			for (SysActivitiServerPO sysActivitiServerPO : list) {
				try {
					String json = synchWorkflowService.getByUri(sysActivitiServerPO.getServerIp()+"/user/"+map.get("userId")+"/groups");
					 JSONObject jsonObj = JSONObject.fromObject(json);
					 JSONArray jsonArray = jsonObj.getJSONArray("data");
					 for (Object object : jsonArray) {
						String groupId = (String) JSONObject.fromObject(object).get("id");
						synchWorkflowService.delByUri(sysActivitiServerPO.getServerIp()+"/identity/groups/"+map.get("userId")+"/members/"+groupId);
					 }
					 for (String rId : roleIds) {
						JSONObject obj = new JSONObject();
						obj.put("userId", map.get("userId"));
						synchWorkflowService.postByUri(sysActivitiServerPO.getServerIp()+"/identity/groups/"+rId+"/members", obj.toString());
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delStaffCsmrol(String gpk,String pk) {
		doAllServer(pk, DEL,"groups/"+gpk+"/members/");
	}
}
