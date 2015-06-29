package com.system.service;

import java.util.List;
import java.util.Map;

import com.readinglife.framework.web.JsonPager;
import com.system.model.po.SysDataPrivilPO;
import com.system.model.po.SysDataRolePO;

public interface SysDataService {
	Map<String,Object> dataSysList();

	List<Map<String, String>> tableList(String[] databases);
	List<Map<String,String>> columnsListByTable(String dbname,String tablename);
	List<SysDataRolePO> getRoleList();
	JsonPager<?> getRoleDataList(JsonPager<?> jPager,Map<String,Object> parmMap);
	void save(SysDataPrivilPO sysDataPrivilPO,String dataRoleId);
	void remove(String dataPrivId);
	void update(SysDataPrivilPO sysDataPrivilPO);
}
