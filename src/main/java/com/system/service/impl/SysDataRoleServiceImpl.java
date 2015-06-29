package com.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.SingleBaseService;
import com.system.model.po.SysDataRolePO;
import com.system.service.SysDataRoleService;

@Service("SysDataRoleService")
public class SysDataRoleServiceImpl extends SingleBaseService implements SysDataRoleService{

	@Override
	public String initNamespace() {
		return "com.system.dao.impl.SysDataRolePOMapper";
	}

	@Override
	public List<SysDataRolePO> getDataRoleList(Map map) {
		List<SysDataRolePO> dataRoleList=this.selectList("com.system.dao.impl.SysDataRoleDAO.getDataRoleList", map);
		return dataRoleList;
	}

	@Override
	public int getDataRoleListTotal(Map map) {
		int total=this.selectOne("com.system.dao.impl.SysDataRoleDAO.getDataRoleListTotal", map);
		return total;
	}

	@Override
	public int addDataRole(SysDataRolePO sysDataRolePO) {
		return this.insert(this.initNamespace()+".insert", sysDataRolePO);
	}

	@Override
	public int update(SysDataRolePO sysDataRolePO) {
		return this.update(this.initNamespace()+".updateByPrimaryKeySelective", sysDataRolePO);
	}

	@Override
	public int dataremove(String dataroleid) {
		return this.delete(this.initNamespace()+".deleteByPrimaryKey", dataroleid);
	}
	
}