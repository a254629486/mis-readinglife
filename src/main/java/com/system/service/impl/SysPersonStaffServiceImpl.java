/**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.service.impl<br/>
 * <b>文件名：</b>SysPersonStaffServiceImpl<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.readinglife.framework.exceptions.MessageException;
import com.readinglife.framework.service.BaseService;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.string.StringUtil;
import com.system.model.po.SysStaffPO;
import com.system.model.vo.SysStaffPositiSaveVO;
import com.system.service.SysPersonStaffService;

/**
 * <b>类名称：</b>SysPersonStaffServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * 
 */
@SuppressWarnings("rawtypes")
@Service("SysPersonStaffService")
public class SysPersonStaffServiceImpl extends BaseService implements
		SysPersonStaffService {

	public JsonPager selectByPageMap(JsonPager jPager, Map param) {
		if(StringUtil.isNotNull(param.get("companId"))){
			String[] list = param.get("companId").toString().split(",");
			param.put("companId", list);
		}
		return selectByPage(
				"com.system.dao.impl.SysPersonStaffDAO.selectId",
				"com.system.sys.dao.impl.SysPersonStaffDAO.countId",
				jPager, param);
	}

	public void save(SysStaffPO sysStaffPO, SysStaffPositiSaveVO sysStaffPositiSaveVO) throws MessageException {
//		validStaff(sysStaffPO,sysStaffPositiSaveVO);
		insert("com.system.dao.impl.SysStaffPOMapper.insert", sysStaffPO);
		
		String[] positiIds = sysStaffPositiSaveVO.getPositiId().split(",");
		for (int i = 0; i < positiIds.length; i++) {
			sysStaffPositiSaveVO = new SysStaffPositiSaveVO();
			sysStaffPositiSaveVO.setStaffId(sysStaffPO.getStaffId());
			sysStaffPositiSaveVO.setPositiId(positiIds[i]);
			insert("com.system.dao.impl.SysPersonStaffDAO.insertStaffPositi", sysStaffPositiSaveVO);
		}
	}

	public void update(SysStaffPO sysStaffPO, SysStaffPositiSaveVO sysStaffPositiSaveVO)  throws MessageException  {
//		validStaff(sysStaffPO,sysStaffPositiSaveVO);
		update("com.system.dao.impl.SysStaffPOMapper.updateByPrimaryKey", sysStaffPO);
		
		delete("com.system.dao.impl.SysPersonStaffDAO.deleteStaffPositiByPrimaryKey", sysStaffPositiSaveVO.getStaffId());
		String[] positiIds = sysStaffPositiSaveVO.getPositiId().split(",");
		for (int i = 0; i < positiIds.length; i++) {
			sysStaffPositiSaveVO = new SysStaffPositiSaveVO();
			sysStaffPositiSaveVO.setStaffId(sysStaffPO.getStaffId());
			sysStaffPositiSaveVO.setPositiId(positiIds[i]);
			insert("com.system.dao.impl.SysPersonStaffDAO.insertStaffPositi", sysStaffPositiSaveVO);
		}
	}
	
//	private void validStaff(SysStaffPO sysStaffPO,SysStaffPositiSaveVO sysStaffPositiSaveVO) throws MessageException {
//		//判断该员工岗位是否已存在
//		Map paramMap =  new HashMap<String, String>();
//		paramMap.put("positiId", sysStaffPositiSaveVO.getPositiId());
//		paramMap.put("departId", sysStaffPO.getDepartId());
//		SysPersonStaffVO vo = selectOne("com.readinglife.b2b.sys.dao.impl.SysPersonStaffDAO.getStaff", paramMap);
//		if(vo!=null&&sysStaffPO.getStaffId().equals(vo.getstaffId())){
//			//同一员工修改不处理
//		}else {
//			Integer have = selectOne("com.readinglife.b2b.sys.dao.impl.SysPersonStaffDAO.havePerson",paramMap);
//			if(have>0){
//				throw new MessageException("该员工的部分部门岗位已存在，请重新选择");
//			}
//		}
//		
//	}

	public void deleteByPrimaryKeys(String pk) {
		 String[] pks = pk.split(",");
		 for (String string : pks) {
			 delete("com.system.dao.impl.SysStaffPOMapper.deleteByPrimaryKey", string);
			 //删除人员岗位
			 delete("com.system.dao.impl.SysPersonStaffDAO.deleteStaffPositiByPrimaryKey", string);
		}
	}

	@Override
	public JsonPager selectStaffPositiByPageMap(JsonPager jPager, Map param) {
		return selectByPage(
				"com.system.dao.impl.SysPersonStaffDAO.selectStaffPositi",
				"com.system.dao.impl.SysPersonStaffDAO.selectStaffPositicountId",
				jPager, param);
	}

	@Override
	public void changeDepart(String staffId, String departId) {
		 Map map =  new HashMap();
		 	 map.put("staffId", staffId);
		 	 map.put("departId", departId);
		 update("com.system.dao.impl.SysPersonStaffDAO.changeDepart", map);
	}
	
	
	

}
