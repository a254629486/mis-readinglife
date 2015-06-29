package com.system.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.readinglife.framework.service.BaseService;
import com.system.model.po.SysAccessoryPO;
import com.system.model.po.SysAccessoryPOExample;
import com.system.service.AccessoryService;

@Service("accessoryService")
public class AccessoryServiceImpl extends BaseService implements AccessoryService {
	Logger logger = Logger.getLogger(AccessoryServiceImpl.class);   
	public void saveSysAccessoryPO(SysAccessoryPO sysAccessoryPO) {
		logger.debug(">>>saveEpmAccessoryPO(epmAccessoryPO:"+sysAccessoryPO.getAccessoryId()+")");
		super.insert("com.readinglife.b2b.sys.dao.impl.SysAccessoryPOMapper.insertSelective", sysAccessoryPO);
		logger.debug("<<<saveEpmAccessoryPO(epmAccessoryPO:"+sysAccessoryPO.getAccessoryId()+")");
	}
	public List<SysAccessoryPO> getSysAccessoryPOList(String orderId) {
		SysAccessoryPOExample example=new SysAccessoryPOExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<SysAccessoryPO> ls=super.selectList("com.system.dao.impl.SysAccessoryPOMapper.selectByExample", example);
		return ls;
	}
	public SysAccessoryPO geteSysAccessoryPO(String accessoryId) {
		SysAccessoryPO sysAccessoryPO=super.selectOne("com.system.dao.impl.SysAccessoryPOMapper.selectByPrimaryKey", accessoryId);
		return sysAccessoryPO;
	}
	public void deleteSysAccessoryPO(String accessoryId) {
		super.delete("com.system.dao.impl.SysAccessoryPOMapper.deleteByPrimaryKey", accessoryId);
	}

}
