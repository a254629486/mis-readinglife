/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysPositiRoleServiceImpl<br/>
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
import com.system.model.po.SysPositiPO;
import com.system.model.vo.PositiRoleDataVO;
import com.system.model.vo.PositiRoleVO;
import com.system.model.vo.PositifPrivilVO;
import com.system.service.SysPositiRoleService;

/**
 * <b>类名称：</b>SysPositiRoleServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@SuppressWarnings("rawtypes")
@Service("SysPositiRoleService")
public class SysPositiRoleServiceImpl extends BaseService implements
		SysPositiRoleService {

	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysPositiRoleDAO.selectId",
				"com.system.dao.impl.SysPositiRoleDAO.countId",
				jPager, param);
	}

	public JsonPager selectedByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysPositiRoleDAO.selectedId",
				"com.system.dao.impl.SysPositiRoleDAO.countselectedId",
				jPager, param);
	}

	public JsonPager selectPrivilByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysPositiRoleDAO.selectPositiPrivil",
				"com.system.dao.impl.SysPositiRoleDAO.countPositiPrivil",
				jPager, param);
	}

	private void saveRolePositi(String positiId, String roleId) {
		String[] roleIds = roleId.split(",");
			List<PositiRoleVO> list = new  ArrayList<PositiRoleVO>();
			PositiRoleVO vo = null;
			if(roleIds!=null&&StringUtil.isNotBlank(roleIds[0]))
			for (String pId : roleIds) {
				vo = new PositiRoleVO();
				vo.setPositiId(positiId);
				vo.setroleId(pId);
				list.add(vo);
			}
			delete("com.system.dao.impl.SysPositiRoleDAO.deleteRolePositi", positiId);
			if(list.size()>0){
			insert("com.system.dao.impl.SysPositiRoleDAO.insertRolePositi", list);
			}
	}
	private void saveRolePositiData(String positiId, String roleId) {
		String[] roleIds = roleId.split(",");
		List<PositiRoleDataVO> list = new  ArrayList<PositiRoleDataVO>();
		PositiRoleDataVO vo = null;
		if(roleIds!=null&&StringUtil.isNotBlank(roleIds[0]))
			for (String pId : roleIds) {
				vo = new PositiRoleDataVO();
				vo.setPositiId(positiId);
				vo.setDataRoleId(pId);
				list.add(vo);
			}
		delete("com.system.dao.impl.SysPositiRoleDAO.deleteRolePositiData", positiId);
		if(list.size()>0){
			insert("com.system.dao.impl.SysPositiRoleDAO.insertRolePositiData", list);
		}
	}

	private void savePositiPrivil(String positiId, String privilId) {
		String[] privilIds = privilId.split(",");
		List<PositifPrivilVO> list = new  ArrayList<PositifPrivilVO>();
		PositifPrivilVO vo = null;
		if(privilIds!=null&&StringUtil.isNotBlank(privilIds[0]))
		for (String pId : privilIds) {
			vo = new PositifPrivilVO();
			vo.setPrivilId(pId);
			vo.setPositiId(positiId);
			list.add(vo);
		}
		delete("com.system.dao.impl.SysPositiRoleDAO.deletePositiPrivil", positiId);
		if(list.size()>0){
		insert("com.system.dao.impl.SysPositiRoleDAO.insertPositiPrivil", list);
		}
		
	}

	public void save(String positiPrivilId, String positiRoleId,String positiRoleDataId,
			String addorupdate, SysPositiPO sysPositiPO) {
		
		saveRolePositi(sysPositiPO.getPositiId(), positiRoleId);
		saveRolePositiData(sysPositiPO.getPositiId(), positiRoleDataId);
		savePositiPrivil(sysPositiPO.getPositiId(), positiPrivilId);
		
		if("add".equals(addorupdate)){
			insert("com.system.dao.impl.SysPositiPOMapper.insert", sysPositiPO);
		}else {
			update("com.system.dao.impl.SysPositiPOMapper.updateByPrimaryKey",sysPositiPO);
		}
		
		
	}

	@Override
	public JsonPager selectDataByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysPositiRoleDAO.selectDataId",
				"com.system.dao.impl.SysPositiRoleDAO.countDataId",
				jPager, param);
	}

	@Override
	public JsonPager selectedDataByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysPositiRoleDAO.selectedDataId",
				"com.system.dao.impl.SysPositiRoleDAO.countselectedDataId",
				jPager, param);
	}

}
