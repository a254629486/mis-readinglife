 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>SysStaffCsmrolController.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.json.Jacksons;
import com.system.model.vo.SysStaffCsmdatVO;
import com.system.model.vo.SysStaffCsmrolVO;
import com.system.service.SynchWorkflowDataService;
import com.system.service.SysStaffCsmrolService;

/**
 * <b>类名称：</b>SysStaffCsmrolController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Controller
@RequestMapping("/sysStaffCsmrolController")
public class SysStaffCsmrolController extends BaseController{  
	
	private final String URL_MAOOING =  "/sysStaffCsmrolController/";
	
	@Autowired
	SysStaffCsmrolService sysStaffCsmrolService;
	
	@Autowired
	SynchWorkflowDataService synchWorkflowDataService;
	
	@RequestMapping("/toSearchList")
	public String toSearchList(HttpServletRequest request) {
		request.setAttribute("staffId", request.getParameter("staffId"));
	   return "//sys/person/sys_staff_csmrol_list";
	}
	
	@RequestMapping("/searchList")
	@ResponseBody
	public String searchList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmrolVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysStaffCsmrolService.selectByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/toDataSearchList")
	public String toDataSearchList(HttpServletRequest request) {
		request.setAttribute("staffId", request.getParameter("staffId"));
		return "//sys/person/sys_staff_csmrol_data_list";
	}
	
	@RequestMapping("/searchDataList")
	@ResponseBody
	public String searchDataList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmdatVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysStaffCsmrolService.selectDataByPageMap(jPager,param);
		return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchSelectedList")
	@ResponseBody
	public String searchSelectedList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmrolVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysStaffCsmrolService.selectSelectedByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	@RequestMapping("/searchSelectedDataList")
	@ResponseBody
	public String searchSelectedDataList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmdatVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysStaffCsmrolService.selectDataSelectedByPageMap(jPager,param);
		return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/toStaffPrivil")
	public String toStaffPrivil(HttpServletRequest request) {
		request.setAttribute("staffId", request.getParameter("staffId"));
		request.setAttribute("positiId", request.getParameter("positiId"));
	   return "//sys/person/sys_staff_privil_list";
	}
	
	@RequestMapping("/searchStaffPrivilList")
	@ResponseBody
	public String searchStaffPrivilList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmrolVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysStaffCsmrolService.selectPrivilByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchStaffHavePrivilList")
	@ResponseBody
	public String searchStaffHavePrivilList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmrolVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysStaffCsmrolService.selectHavePrivilByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/saveRole")
	@ResponseBody
	public String saveRole(HttpServletRequest request) {
		
		sysStaffCsmrolService.saveStaffRole(request.getParameter("staffId"), request.getParameter("roleId"));
		//同步工作流服务器
		Map map = new HashMap();
		map.put("userId", request.getParameter("staffId"));
		map.put("roleId", request.getParameter("roleId"));
		synchWorkflowDataService.postStaffCsmrol(map);
		return result(true, SAVE_SUCCESS);
	}
	@RequestMapping("/saveDataRole")
	@ResponseBody
	public String saveDataRole(HttpServletRequest request) {
		sysStaffCsmrolService.saveStaffDataRole(request.getParameter("staffId"), request.getParameter("roleId"));
		return result(true, SAVE_SUCCESS);
	}
	
	@RequestMapping("/savePrivil")
	@ResponseBody
	public String savePrivil(HttpServletRequest request) {
		
		sysStaffCsmrolService.saveStaffPrivil(request.getParameter("staffId"), request.getParameter("privilId"));
		
		return result(true, SAVE_SUCCESS);
	}
	
}
