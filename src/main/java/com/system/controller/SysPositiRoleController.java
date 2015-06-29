 /**
 * <b>项目名：</b><br/>
 * <b>包名：</b>com.readinglife.b2b.sys.controller<br/>
 * <b>文件名：</b>SysPositiRoleController.java<br/>
 * <b>描述：</b>TODO<br/>
 * <b>版本信息：</b>v1.0.0<br/>
 * <b>Copyright (c)</b> 2013新经典文化有限公司-版权所有<br/>
 *
 */
package com.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.ForBean;

import com.readinglife.framework.web.BaseController;
import com.readinglife.framework.web.JsonPager;
import com.readinglife.tools.json.Jacksons;
import com.system.model.po.SysPositiPO;
import com.system.model.vo.SysPositiDataRoleVO;
import com.system.model.vo.SysPositiRoleVO;
import com.system.model.vo.SysStaffCsmrolVO;
import com.system.service.SysPositiRoleService;

/**
 * <b>类名称：</b>SysPositiRoleController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>B2B framework by <a href="mailto:Q12_35@163.com">zhaosy</a><br/>
 * <b>修改人：</b><br/>
 * <b>修改备注：</b><br/>
 * <b>版本信息：</b>v1.0.0<br/>
 *
 */
@Controller
@RequestMapping("/sysPositiRoleController")
public class SysPositiRoleController extends BaseController{  
	
	private final String URL_MAOOING =  "/sysPositiRoleController/";
	
	@Autowired
	SysPositiRoleService sysPositiRoleService;
	
	
	@RequestMapping("/toSearchList")
	public String toSearchList(HttpServletRequest request) {
	   return "//sys/person/sys_positi_role_list";
	}
	
	@RequestMapping("/searchList")
	@ResponseBody
	public String searchList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysPositiRoleVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysPositiRoleService.selectByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchDbList")
	@ResponseBody
	public String searchDbList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysPositiDataRoleVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysPositiRoleService.selectDataByPageMap(jPager,param);
		return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchSelectedList")
	@ResponseBody
	public String searchSelectedList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysPositiRoleVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysPositiRoleService.selectedByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchSelectedDbList")
	@ResponseBody
	public String searchSelectedDbList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysPositiDataRoleVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysPositiRoleService.selectedDataByPageMap(jPager,param);
		return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/searchPositiPrivilList")
	@ResponseBody
	public String searchPositiPrivilList(HttpServletRequest request,Model model) {
		Map param = formatParamByBeanProperty(request,SysStaffCsmrolVO.class);
		JsonPager jPager = getJsonPager(param);
		jPager.setSize(Integer.MAX_VALUE);
		jPager = sysPositiRoleService.selectPrivilByPageMap(jPager,param);
	   return jPager.toJsonString(Jacksons.DATE_TIME_FORMAT);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(@ForBean SysPositiPO record,HttpServletRequest request) {
		sysPositiRoleService.save(request.getParameter("positiPrivilId"), request.getParameter("positiRoleId"),request.getParameter("positiRoleDataId"), request.getParameter("addorupdate"), record);
		return result(true, SAVE_SUCCESS);
	}
}
