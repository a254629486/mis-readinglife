package com.system.service;


import java.util.List;

import com.system.model.po.SysAccessoryPO;



public interface AccessoryService {
	
	public void saveSysAccessoryPO(SysAccessoryPO sysAccessoryPO);
	
	public List<SysAccessoryPO> getSysAccessoryPOList(String orderId);
	
	public SysAccessoryPO geteSysAccessoryPO(String accessoryId);
	
	public void deleteSysAccessoryPO(String accessoryId);
}
