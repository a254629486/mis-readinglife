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
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.system.model.po.SysRolePO;



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
public interface SynchWorkflowDataService {
	public void postRole(SysRolePO sysRolePO);
	public void putRole(SysRolePO sysRolePO);
	public void delRole(String pk);
	public String getRole(String server,String pk) throws ClientProtocolException, IOException;
	
	public void postStaff(Map map);
	public void putStaff(Map map);
	public void delStaff(String pk);
	public String getStaff(String server,String pk) throws ClientProtocolException, IOException;
	
	public void postStaffCsmrol(Map map);
	public void delStaffCsmrol(String gpk,String pk);
	
}
