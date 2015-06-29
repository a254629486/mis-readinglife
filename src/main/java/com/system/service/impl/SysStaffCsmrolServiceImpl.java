/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysStaffCsmrolServiceImpl<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.service.BaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.string.StringUtil;
import com.system.model.vo.StaffDataRoleVO;
import com.system.model.vo.StaffPrivilVO;
import com.system.model.vo.StaffRoleVO;
import com.system.service.SysStaffCsmrolService;

/**
 * <b>类名称：</b>SysStaffCsmrolServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@SuppressWarnings("rawtypes")
@Service("SysStaffCsmrolService")
public class SysStaffCsmrolServiceImpl extends BaseService implements
		SysStaffCsmrolService {

	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysStaffCsmrolDAO.selectId",
				"com.system.dao.impl.SysStaffCsmrolDAO.countId",
				jPager, param);
	}
	public JsonPager selectDataByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysStaffCsmrolDAO.selectDataId",
				"com.system.dao.impl.SysStaffCsmrolDAO.countDataId",
				jPager, param);
	}

	public JsonPager selectSelectedByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysStaffCsmrolDAO.selectedId",
				"com.system.dao.impl.SysStaffCsmrolDAO.countselectedId",
				jPager, param);
	}
	
	public JsonPager selectDataSelectedByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysStaffCsmrolDAO.selectedDataId",
				"com.system.dao.impl.SysStaffCsmrolDAO.countselectedDataId",
				jPager, param);
	}

	public JsonPager selectPrivilByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysStaffCsmrolDAO.selectprivil",
				"com.system.dao.impl.SysStaffCsmrolDAO.countprivil",
				jPager, param);
	}

	public JsonPager selectHavePrivilByPageMap(JsonPager jPager, Map param) {
		param.put("positiId", param.get("positiId").toString().split(","));
		return selectByPage(
				"com.system.dao.impl.SysStaffCsmrolDAO.selecthaveprivil",
				"com.system.dao.impl.SysStaffCsmrolDAO.counthaveprivil",
				jPager, param);
	}

	public void saveStaffRole(String staffId, String roleId) {
		String[] roleIds = roleId.split(",");
		List<StaffRoleVO> list = new  ArrayList<StaffRoleVO>();
		StaffRoleVO vo = null;
		if(roleIds!=null&&StringUtil.isNotBlank(roleIds[0]))
		for (String rId : roleIds) {
			vo = new StaffRoleVO();
			vo.setRoleId(rId);
			vo.setStaffId(staffId);
			list.add(vo);
		}
		delete("com.system.dao.impl.SysStaffCsmrolDAO.deleteStaffRole", staffId);
		if(list.size()>0){
		insert("com.system.dao.impl.SysStaffCsmrolDAO.insertStaffRole", list);
		}
	}
	public void saveStaffDataRole(String staffId, String roleId) {
		String[] roleIds = roleId.split(",");
		List<StaffDataRoleVO> list = new  ArrayList<StaffDataRoleVO>();
		StaffDataRoleVO vo = null;
		if(roleIds!=null&&StringUtil.isNotBlank(roleIds[0]))
			for (String rId : roleIds) {
				vo = new StaffDataRoleVO();
				vo.setDataRoleId(rId);
				vo.setStaffId(staffId);
				list.add(vo);
			}
		delete("com.system.dao.impl.SysStaffCsmrolDAO.deleteStaffDataRole", staffId);
		if(list.size()>0){
			insert("com.system.dao.impl.SysStaffCsmrolDAO.insertStaffDataRole", list);
		}
	}

	public void saveStaffPrivil(String staffId, String privilId) {
		String[] privilIds = privilId.split(",");
		List<StaffPrivilVO> list = new  ArrayList<StaffPrivilVO>();
		StaffPrivilVO vo = null;
		if(privilIds!=null&&StringUtil.isNotBlank(privilIds[0]))
		for (String pId : privilIds) {
			vo = new StaffPrivilVO();
			vo.setPrivilId(pId);
			vo.setStaffId(staffId);
			list.add(vo);
		}
		delete("com.system.dao.impl.SysStaffCsmrolDAO.deleteStaffPrivil", staffId);
		if(list.size()>0){
		insert("com.system.dao.impl.SysStaffCsmrolDAO.insertStaffPrivil", list);
		}
	}

}
