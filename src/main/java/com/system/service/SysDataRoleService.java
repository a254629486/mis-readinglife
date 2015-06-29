package com.system.service;

import java.util.List;
import java.util.Map;

import com.system.model.po.SysDataRolePO;


public interface SysDataRoleService{

	List<SysDataRolePO> getDataRoleList(Map map);

	int getDataRoleListTotal(Map map);

	int addDataRole(SysDataRolePO sysDataRolePO);

	int update(SysDataRolePO sysDataRolePO);

	int dataremove(String dataroleid);
	
}